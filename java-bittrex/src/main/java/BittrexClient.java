import com.google.common.collect.MinMaxPriorityQueue;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.math.RoundingMode;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import static com.sun.tools.javac.util.Constants.format;

public class BittrexClient {
    static String[] currencies = {
            "CVC", "OMG", "NEO", "STORJ", "QTUM", "PAY", "VRM", "XZC",
            "BLITZ", "FUN", "XZC", "ADX", "PART", "PTOY", "CFI", "SWT",
            "LUN", "WAVES", "XLM", "SC", "XMY", "VOX", "STEEM", "QWARK",
            "SLS", "PART"
    };

    public static void main(String[] args)
    {
        DecimalFormat dfEight = new DecimalFormat("#.########");
        DecimalFormat dfTwo = new DecimalFormat("#.##");
        dfEight.setRoundingMode(RoundingMode.FLOOR);
        dfTwo.setRoundingMode(RoundingMode.FLOOR);

        Bittrex bittrex = new Bittrex(1, 15);
        bittrex.setAuthKeysFromTextFile("key.properties");
//        callMethods(bittrex,Methods.GET_MARKETS,"");
//        callMethods(bittrex,Methods.GET_MARKET_SUMMARIES,"");
//        callMethods(bittrex,Methods.GET_MARKET_SUMMARY,"BTC-XMG");
//        callMethods(bittrex,Methods.GET_BALANCES,"");
//        callMethods(bittrex,Methods.GET_BALANCE,"BTC");
//        sellCurrency(bittrex, "BTC-STRAT", "STRAT", 2, 10);
//        sellCurrency(bittrex, "BTC-OMG", "OMG", 2, 10);
//        placeSellOrder(bittrex, "BTC-NEO", "NEO", 15);
//          placeSellOrder(bittrex, "BTC-PAY", "PAY", 15);
//          placeSellOrder(bittrex, "BTC-EDG", "EDG", 6);
//          placeSellOrder(bittrex, "BTC-SC", "SC", 10);
//          placeSellOrder(bittrex, "BTC-HKG", "HKG", 10);
//          placeSellOrder(bittrex, "BTC-XLM", "XLM", 2);
//          placeSellOrder(bittrex, "BTC-AMP", "AMP", 2);
//          placeSellOrder(bittrex, "BTC-WINGS", "WINGS", 15);
//          placeSellOrder(bittrex, "BTC-XMY", "XMY", 11);
//          placeSellOrder(bittrex, "BTC-WAVES", "WAVES", 10);
        //        watchMarketFor(bittrex, "BTC-BCC");
        //        String currency = "STORJ";
        //        String currency = "FUN";
        //        String currency = "";



        double spendPercent = 30;
        double minDesiredProfit =  1;
        double demandIncrease = 5;
        double commission = 0.25;
        String currency ="";

        String[] options = {
                "Sell currency",
                "buy and sell currency",
                "watch market"
        };

        int option = getOption(options);
        switch (option) {
        case 0:
            currency = getCurrency();
            sellCurrency(minDesiredProfit, currency, bittrex, dfTwo);
            break;
        case 1:
            while (true) {
                currency = getCurrency();
                validateSpendPercent(spendPercent,currency);

                Map<String, String> marketMap = getCurrentViewOfMarket(bittrex, "BTC-"+currency);
                double lastTradedPrice = Double.valueOf(marketMap.get("Last"));
                double basePrice = Double.valueOf(marketMap.get("PrevDay"));

                buyAndSellCurrency(bittrex, currency, spendPercent, minDesiredProfit, demandIncrease,
                        dfEight, dfTwo, "DEMAND", lastTradedPrice, basePrice, commission);
            }
        case 2:
            currency = getCurrency();
            validateSpendPercent(spendPercent,currency);
            watchAndSellIfProfitable(bittrex, currency, spendPercent, commission, minDesiredProfit, dfEight, dfTwo);
            break;
        default:
            System.out.println("Option not available");
            break;
        }
    }
    private static void validateSpendPercent(double spendpercent, String currency) {
        System.out.println(String.format("Do u want to spend %s%% of available BTC on %s.", spendpercent, currency));
        System.out.print("Press -1 to continue. Press any other number to exit:");
        Scanner in = new Scanner(System.in);
         int option = in.nextInt();
         if(option != -1) {
             System.exit(0);
         }
    }

    private static int getOption(String[] options) {
        for (int i=0;i<options.length;i++) {
            System.out.println(String.format("[%s]: %s",i,options[i]));
        }
        System.out.print("\nChoose option:");
        Scanner in = new Scanner(System.in);
        return in.nextInt();
    }

    private static String getCurrency() {
        for (int i = 0; i < currencies.length; i++) {
            System.out.println(String.format("[ %s ] -> [ %s ]", i, currencies[i]));
        }
        System.out.print("\nEnter currency No:");
        Scanner in = new Scanner(System.in);
        int index = in.nextInt();

        return currencies[index];
    }

    private static void buyAndSellCurrency(Bittrex bittrex, String currency, double spendPercent, double desiredProfit,
            double demandIncrease, DecimalFormat dfEight, DecimalFormat dfTwo, String factor, double lastTradedPrice,
            double basePrice, double commission) {

        String market = "BTC-" + currency;

        double change = ((lastTradedPrice - basePrice)/basePrice)*100;

        System.out.println(String.format("%s is trading at %s pecent higher than base price",market, change));

        double buyPrice = 0;
        double buyQuantity = 0;

        buyPrice = getBidRate(bittrex, market, dfEight);

        double totalBtcAmount = getAvailableBTC(bittrex);
        double tradingBtcAmount = totalBtcAmount * spendPercent / 100;

        buyQuantity = getBuyQuantity(dfEight, commission, buyPrice, tradingBtcAmount);

        change = ((buyPrice - basePrice)/basePrice)*100;
        System.out.println(String.format("[%s] Placing buy order to execute when the market reaches %s",market, change));
        placeBuyOrderAndWait(bittrex, dfEight, currency, market, buyPrice, buyQuantity);

        String sellPrice = "";
        if (factor.equals("DEMAND")) {
            sellPrice = getDesiredSellPrice(buyPrice, basePrice, demandIncrease, dfEight);
        } else {
            sellPrice = getDesiredSellPrice(bittrex, market, desiredProfit);
        }

        change = ((Double.valueOf(sellPrice) - basePrice)/basePrice)*100;
        System.out.println(String.format("[%s] Placing sell order to execute when the market reaches %s",market, change));
        String sellQuantity = getSellQuantity(bittrex, currency);

        placeSellOrder(bittrex, market, currency, sellPrice, sellQuantity);

        double expectedProfit = getExpectedProfit(String.valueOf(buyQuantity), String.valueOf(buyPrice), sellQuantity, sellPrice, commission);

        System.out.println("Expected profit = " + expectedProfit);
    }

    private static void sellCurrency(double desiredProfit, String currency, Bittrex bittrex, DecimalFormat dfTwo) {
        String market = "BTC-" + currency;

        String sellPrice = getDesiredSellPrice(bittrex, market, desiredProfit);

        String sellQuantity = getSellQuantity(bittrex, currency);

        Map<String, String> marketMap = getCurrentViewOfMarket(bittrex, market);
        double prevDayPrice = Double.valueOf(marketMap.get("PrevDay"));

        double lastTradedPrice = Double.valueOf(marketMap.get("Last"));
        System.out.println(String.format("[%s] currently trading at %s%%  higher than closing price",market, dfTwo.format(((lastTradedPrice - prevDayPrice)/prevDayPrice)*100)));


        placeSellOrder(bittrex, market, currency, sellPrice, sellQuantity);
        System.out.println(String.format("[%s] Sell order will be executed when market reaches %s%% above closing price",market, dfTwo.format(((Double.valueOf(sellPrice) - prevDayPrice)/prevDayPrice)*100)));
    }


    private static double getBuyQuantity(DecimalFormat dfEight, double commission, double buyPrice,
            double tradingBtcAmount) {
        double buyQuantity;
        double availableBTCForTrading = tradingBtcAmount * (1 - commission/100);

        availableBTCForTrading = Double.valueOf(dfEight.format(availableBTCForTrading));
        buyQuantity = availableBTCForTrading/buyPrice;
        return buyQuantity;
    }

    private static void placeBuyOrderAndWait(Bittrex bittrex, DecimalFormat df, String currency,
            String market, double buyPrice, double buyQuantity) {
        long sleepTime = 500;
        String buyOrderJsonStr = buyOrder(bittrex, market, currency, buyQuantity, buyPrice, df);
        System.out.println(buyOrderJsonStr);

        List<HashMap<String, String>> buyOrderLists = Bittrex.getMapsFromResponse(buyOrderJsonStr);
        String uuid = buyOrderLists.get(0).get("uuid");

        boolean isOpen = true;
        while (isOpen) {
            String orderHistoryJsonStr = bittrex.getOrder(uuid);
            isOpen = Boolean.valueOf(Bittrex.getMapsFromResponse(orderHistoryJsonStr).get(0).get("IsOpen"));
            if (!isOpen) {
                System.out.println(String.format("[%s] Buy order is complete.", currency));
                break;
            }

            try {
                System.out.println(String.format("[%s] Waiting %sms for buy order to complete... ",currency, sleepTime));
                Thread.sleep(sleepTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private static double getExpectedProfit(String buySize, String buyPrice, String sellSize, String sellPrice, double commision) {
        double buyQuantity = Double.valueOf(buySize);
        double buyRate = Double.valueOf(buyPrice);
        double sellQuantity = Double.valueOf(sellSize);
        double  sellRate = Double.valueOf(sellPrice);

        double totalBuyAmount = buyQuantity * buyRate * (1 + commision/100);
        double totalSellAmount = sellQuantity * sellRate * (1 - commision/100);
        System.out.println(String.format("totalSellBTC = %s, totalBuyBTC = %s", totalSellAmount, totalBuyAmount));
        double profit = ((totalSellAmount - totalBuyAmount)/totalBuyAmount)*100;

        DecimalFormat df = new DecimalFormat("#.##");
        df.setRoundingMode(RoundingMode.FLOOR);
        profit = Double.valueOf(df.format(profit));
        return profit;
    }

    private static double getExpectedNetProfit(double todaysIncrease, String requiredIncrease,
            double commision, DecimalFormat dfTwo) {

        double desiredIncrease = Double.valueOf(requiredIncrease);
        todaysIncrease = Double.valueOf(dfTwo.format(todaysIncrease));

        double t1 = 100 + todaysIncrease;
        double t2 = 100 + commision;
        double t3 = 100 - commision;

        double numerator = (desiredIncrease * t3 - 2 * commision * t1) * 100;
        double denominator = t1 * t2;
        double profit = numerator / denominator;

        return Double.valueOf(dfTwo.format(profit));
    }

    private static double getTodaysChange(Map<String, String> marketMap) {
        double lastValue = Double.valueOf(marketMap.get("Last"));
        return getChangeFromPrevDay(lastValue, marketMap);
    }

    private static double getChangeFromPrevDay(double value, Map<String, String > marketMap) {
        double prevDayValue = Double.valueOf(marketMap.get("PrevDay"));
        return ((value - prevDayValue)/prevDayValue) *100;
    }

    private static double getPreviousDayClosingPriceInMarket(Bittrex bittrex, String market) {
        Map<String, String> marketMap = getCurrentViewOfMarket(bittrex, market);
        double prevDay = Double.valueOf(marketMap.get("PrevDay"));
        return prevDay;
    }

    private static String buyOrder(Bittrex bittrex, String market, String currency, double bidQuantity, double bidRate, DecimalFormat df)
    {
        String quantity = df.format(bidQuantity);
        String rate = String.valueOf(bidRate);

        System.out.println(String.format("[%s] Placing the buy order at buyPrice = %s, buyQuantity = %s",market, rate, quantity));
        String buyOrderJsonStr = bittrex.buyLimit(market, quantity, rate);
        System.out.println(String.format("[%s]: buy order successful.", currency));

        return buyOrderJsonStr;
    }

    /**
     *  The caller should make sure that the desiredProfitPercent is a double with upto two decimal points.
     *  Places the sell order for all the available units of the currency to achieve the  desired profit as calclulated from desiredProfitPercent.
     */
    private static void placeSellOrder(Bittrex bittrex, String market, String currency, String sellPrice, String sellQuantity) {

        System.out.println(String.format("[%s]: Placing the sell order. units: %s, rate: %s", currency, sellQuantity, sellPrice));

        String sellOrderResponse = bittrex.sellLimit(market, sellQuantity, sellPrice);

        System.out.println(String.format("[%s]: sell order successful.", currency));

        //        Utils.prettyPrintJsonStr(sellOrderResponse);
        //        Utils.writeToFile(sellOrderResponse, market + "-sell-" + date.getTime());
    }

    private static double getBidQuantity(double spendAmout, double bidRate) {
        return spendAmout/bidRate;
    }

    private static double getAvailableBTC(Bittrex bittrex) {
        String BTCBalanceJsonStr = bittrex.getBalance("BTC");
        Map<String, String> bitCoinBalanceMap = Bittrex.getMapsFromResponse(BTCBalanceJsonStr).get(0);
        double availableBTC = Double.valueOf(bitCoinBalanceMap.get("Balance"));
        return availableBTC;
    }

    private static double getBidRate(Bittrex bittrex, String market, DecimalFormat df) {
        double bidRate = getHighestBidRate(bittrex,market,"both");
        bidRate = Double.valueOf(df.format(bidRate));
        bidRate = bidRate + 0.00000001;
        return bidRate;
    }



    private static double getHighestBidRate(Bittrex bittrex, String market, String type) {
        String marketOrderBookJsonStr = bittrex.getOrderBook(market, type);
        Gson gson = new Gson();
        JsonObject jsonObject = gson.fromJson(marketOrderBookJsonStr, JsonObject.class);
        JsonObject resultJsonObject = jsonObject.get("result").getAsJsonObject();
        JsonArray buyOrders = resultJsonObject.getAsJsonArray("buy");

        JsonObject buyOrderJsonObject = buyOrders.get(0).getAsJsonObject();
        double quantity = buyOrderJsonObject.get("Quantity").getAsDouble();
        double rate = buyOrderJsonObject.get("Rate").getAsDouble();
        return rate;
    }

    private static void callMethods(Bittrex bittrex, Methods method, String input1) {
        String response = "";
        switch (method) {
        case GET_MARKETS:
            response = bittrex.getMarkets();
            break;
        case GET_BALANCES:
            response = bittrex.getBalances();
            break;
        case GET_CURRENCIES:
            response = bittrex.getCurrencies();
            break;
        case GET_BALANCE:
            response = bittrex.getBalance(input1);
            break;
        case GET_MARKET_SUMMARIES:
            response = bittrex.getMarketSummaries();
            break;
        case GET_MARKET_SUMMARY:
            response = bittrex.getMarketSummary(input1);
            break;
        default:
            System.out.println("No matching option");
            break;
        }
        String jsonResponse = Utils.prettyPrintJsonStr(response);
        Utils.writeToFile(jsonResponse, method.getName()+"_"+input1);
    }

    public void computeRequiredProfitPercentage()
    {
        double requiredPercentage = getRequiredProfitPercentage(2, 5.2);
        NumberFormat formatter = new DecimalFormat("#0.00");
        System.out.format(formatter.format(requiredPercentage));
    }

    private static void watchAndSellIfProfitable(Bittrex bittrex, String currency,
            double spendPercent, double commission, double minProfit, DecimalFormat dfEight,
            DecimalFormat dfTwo) {
        int n = 1000;
        String market = "BTC-" + currency;
        LinkedList<Double> tradePriceWindow = new LinkedList<>();
        MinMaxPriorityQueue<Double> pq = MinMaxPriorityQueue.create();

        Map<String, String> marketMap = getCurrentViewOfMarket(bittrex, market);
        double basePrice = Double.valueOf(marketMap.get("PrevDay")) * 100000000;

        while (true) {
            marketMap = getCurrentViewOfMarket(bittrex, market);
            double recentTradePrice = Double.valueOf(marketMap.get("Last")) * 100000000;

            double prevTradePriceInQ = recentTradePrice;

            int currentWindowSize = tradePriceWindow.size();
            if (currentWindowSize == 0) {
                tradePriceWindow.add(recentTradePrice);
                pq.add(recentTradePrice);
            } else {

                prevTradePriceInQ = tradePriceWindow.peekLast();

                if (Double.compare(recentTradePrice, prevTradePriceInQ) != 0) {
                    if (currentWindowSize == n) {
                        double firstTradeInWindow = tradePriceWindow.remove();
                        if (!pq.remove(firstTradeInWindow)) {
                            System.out.println("remove Failed");
                        }
                    }
                    tradePriceWindow.add(recentTradePrice);
                    pq.add(recentTradePrice);
                }
            }

            currentWindowSize = tradePriceWindow.size();
            if (currentWindowSize != pq.size()) {
                System.out.println("size not matching .........");
            }
            double changeFromLastDay = ((recentTradePrice - basePrice) / basePrice) * 100;
            double changeFromPrevTrade =
                    ((recentTradePrice - prevTradePriceInQ) / basePrice) * 100;
            double highestPrice = pq.peekLast();
            double highestPercentageInWindow = ((highestPrice - basePrice) / basePrice) * 100;
            double lowestPrice = pq.peekFirst();
            double lowestPercentageInWindow = ((lowestPrice - basePrice) / basePrice) * 100;

            //            System.out.print("\r");
            System.out.println(String.format(
                    "[%s] Window Size = %s, From closing price = %s%%. From last trade = %s%%\t\t\t. Highest=%s%%. Lowest=%s%%. currentTradePrice=%s. prevTradePrice=%s. HighestPrice=%s. LowestPrice=%s. closingPrice=%s",
                    market, currentWindowSize, dfTwo.format(changeFromLastDay), changeFromPrevTrade,
                    dfTwo.format(highestPercentageInWindow), dfTwo.format(lowestPercentageInWindow),
                    recentTradePrice, prevTradePriceInQ, highestPrice, lowestPrice, basePrice));

            double requestedIncrease = (highestPercentageInWindow - lowestPercentageInWindow) / 2;
            if (Double.compare(highestPercentageInWindow - changeFromLastDay, requestedIncrease)
                    >= 0) {
                System.out.println(
                        String.format("[%s] currently operating in the lower half of profit window",
                                market));
                double expectedProfit = getExpectedNetProfit(changeFromLastDay,
                        String.valueOf(requestedIncrease), commission, dfTwo);
                if (Double.compare(expectedProfit, minProfit) >= 0) {
                    buyAndSellCurrency(bittrex, currency, minProfit, spendPercent,
                            requestedIncrease, dfEight, dfTwo, "DEMAND", recentTradePrice, basePrice, commission);
                    break;
                } else {
                    System.out.println(String.format(
                            "[%s] It is risky to trade because the expectedProfit=%s and desiredProfit=%s",
                            market, expectedProfit, minProfit));
                }
            }
            try {
                Thread.sleep(750);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private static void sellCurrency(Bittrex bittrex, String market, String currency,
            double desiredBitCoinProfit, double percentageAtBuyTime) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        String message = "";
//        while (true) {
            Date date = new Date();
            String time = dateFormat.format(date);
            Map<String, String> marketMap = getCurrentViewOfMarket(bittrex, market);
//            if (isSellable(marketMap, market, desiredBitCoinProfit, percentageAtBuyTime)) {
                message = String
                        .format("%s is sellable in market %s at desired profit of %.2f. Time:%s",
                                currency, market, desiredBitCoinProfit, time);
                String balanceJsonStr = bittrex.getBalance(currency);
                HashMap<String, String> balanceMap = Bittrex.getMapsFromResponse(balanceJsonStr).get(0);
                String availableQuantity = balanceMap.get("Available");
                String lastTradedValue = marketMap.get("Last");
                System.out.println(availableQuantity);
                System.out.println(String.format("Place the bid for %s units of %s at the rate of %s", availableQuantity, currency, lastTradedValue));
//                String uuid = bittrex.sellLimit(market, availableQuantity, lastTradedValue);
                String uuid = bittrex.sellLimit(market, availableQuantity, "0.00100488");
                Utils.prettyPrintJsonStr(uuid);
                Utils.writeToFile(uuid, market + "-sell-" + date.getTime());

//                String uuid = bittrex.sellMarket(market, availableQuantity);
//            } else {
//                message = String.format("%s is not sellable.Time:%s", currency, time);
//            }
            System.out.println(message);
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
//        }
    }


    private static String getSellQuantity(Bittrex bittrex, String currency) {
        String balanceJsonStr = bittrex.getBalance(currency);
        HashMap<String, String> balanceMap = Bittrex.getMapsFromResponse(balanceJsonStr).get(0);
        return balanceMap.get("Available");
    }

    private static String getDesiredSellPrice(double buyPrice, double basePrice, double desiredDemandIncrease, DecimalFormat df) {

        double sellPrice = buyPrice + basePrice * desiredDemandIncrease/100;
        return df.format(sellPrice);

    }

    private static String getDesiredSellPrice(Bittrex bittrex, String market,
            double desiredProfitPercent) {
        String orderHistoryJsonStr = bittrex.getOrderHistory(market);

//        String marketOrderHistoryPrettyStr = Utils.prettyPrintJsonStr(orderHistoryJsonStr);
//        Utils.writeToFile(marketOrderHistoryPrettyStr,market+"_order_history");

        List<HashMap<String, String>> orderHistoryMapsList = Bittrex.getMapsFromResponse(orderHistoryJsonStr);
        Map<String, String> mostRecentBuyOrder = getRecentBuyOrderHistory(orderHistoryMapsList);
        String buyPriceStr = mostRecentBuyOrder.get("Limit");

        System.out.println(String.format("[%s] My most recent buy-order price: %s", market, buyPriceStr ));

        double buyPrice = Double.valueOf(buyPriceStr);

        double bidPrice = buyPrice * (1 + desiredProfitPercent/100);
        bidPrice = (double) Math.round(bidPrice * 100000000) / 100000000;
        String bidPriceStr = String.valueOf(bidPrice);
        return bidPriceStr;
    }


    private static Map<String, String> getRecentBuyOrderHistory(List<HashMap<String, String>> orderHistoryMapsList) {
        return orderHistoryMapsList.stream().filter(map -> map.get("OrderType").equals("LIMIT_BUY")).findFirst().orElse(null);
    }

    private static boolean isSellable(Map<String, String> marketMap, String market, double desiredBitCoinProfitPercent, double percentageChangeAtBuyTime) {
        double y = desiredBitCoinProfitPercent;
        double z = percentageChangeAtBuyTime;
        double x = getRequiredProfitPercentage(y,z);

        double currentChange = getTodaysChange(marketMap);
        //TODO: properly compare double values. Look java documentation for correct comparison.
        //TODO: Otherwise you might lose money.
        if(currentChange >= x) {
            return true;
        } else {
            String message = String.format("%s is sellable at %.2f. Current change is %.2f", market, x, currentChange);
            System.out.println(message);
            return false;
        }
    }



    private static Map<String, String> getCurrentViewOfMarket(Bittrex bittrex, String market) {
        String response = bittrex.getMarketSummary(market);
        List<HashMap<String, String>> marketMaps = Bittrex.getMapsFromResponse(response);
        return marketMaps.get(0);
    }

    /**
     *
     * @param y Desired profit percentage
     * @param z Percentage change at the time of buying.
     * @return sell % to achieve the desired profit.
     */
    public static double getRequiredProfitPercentage(double y, double z) {
        double x = (25 + y * (100 + z) + 100 * z) / 99.75;
        return x;
    }

    public enum Methods
    {
        GET_MARKETS("getmarkets"),
        GET_CURRENCIES("getcurrencies"),
        GET_BALANCES("getbalances"),
        GET_MARKET_SUMMARIES("getmarketsummaries"),
        GET_MARKET_SUMMARY("getmarketsummary"),
        GET_BALANCE("getbalance");

        public String name;
        public String value;

        public String getName() {
            return this.name;
        }

        Methods(String name) {
            this.name = name;
        }
    }
}
