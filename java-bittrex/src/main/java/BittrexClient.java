import com.google.common.collect.MinMaxPriorityQueue;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.math.RoundingMode;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

public class BittrexClient {
    public static int SATOSHI_CONST = 100000000;
    static List<String> currencies = new ArrayList<>();

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


        String currencyFile = "currencies.txt";
        Utils.loadCurrencies(currencyFile, currencies);
        Map<String, String> customProperties = new HashMap<>();
        Utils.loadCustomProperties("config.txt",customProperties);

        String currency ="";

        String[] options = {
                "Sell currency",
                "buy and sell now",
                "buy and sell ater watch",
                "get Asset State",
                "Monitor Currency"
        };


        int option = getOption(options);
        switch (option) {
        case 0:
            currency = getCurrency(currencyFile);
            validateTradeConfiguration(currency, customProperties);
            double minDesiredProfit =  Double.valueOf(customProperties.get("btc.minDesiredProfit"));
            sellCurrency(minDesiredProfit, currency, bittrex, dfTwo);
            break;
        case 1:
            currency = getCurrency(currencyFile);
            double totalBtcAmount = getAvailableBTC(bittrex);
            System.out.println("Total Available BTC=" + totalBtcAmount);
            validateTradeConfiguration(currency, customProperties);

            double spendPercent = Double.valueOf(customProperties.get("btc.spendPercent"));
            minDesiredProfit = Double.valueOf(customProperties.get("btc.minDesiredProfit"));
            double demandIncrease = Double.valueOf(customProperties.get("btc.demandIncrease"));
            double commission = Double.valueOf(customProperties.get("btc.commission"));
            double totalBTCInvestment = totalBtcAmount * spendPercent / 100;

            System.out.println("Amount for trading "+currency+" is:" + totalBTCInvestment );

            Map<String, String> marketMap = getCurrentViewOfMarket(bittrex, "BTC-" + currency);
            double lastTradedPrice = Double.valueOf(marketMap.get("Last"));
            double basePrice = Double.valueOf(marketMap.get("PrevDay"));

            String market = "BTC-" + currency;
            JsonObject resultJsonObject = getOrderBookJson(bittrex, market, "both");
            JsonArray buyOrders = getOrdersByType(resultJsonObject, "buy");

            double bidPrice = getHighestRate(buyOrders);
            double buyPrice = bidPrice + 0.00000001;

            buyAndSellCurrency(bittrex, currency, minDesiredProfit, demandIncrease,
                    dfEight, dfTwo, "DEMAND", lastTradedPrice, basePrice, buyPrice, totalBTCInvestment, commission);
            break;
        case 2:
            currency = getCurrency(currencyFile);
            totalBtcAmount = getAvailableBTC(bittrex);
            System.out.println("Total Available BTC=" + totalBtcAmount);
            validateTradeConfiguration(currency, customProperties);

            spendPercent = Double.valueOf(customProperties.get("btc.spendPercent"));
            minDesiredProfit = Double.valueOf(customProperties.get("btc.minDesiredProfit"));
            commission = Double.valueOf(customProperties.get("btc.commission"));

            totalBTCInvestment = totalBtcAmount * spendPercent / 100;
            System.out.println("Amount for trading " + currency + " is:" + totalBTCInvestment );

            watchAndSellIfProfitable(bittrex, currency, totalBTCInvestment, commission, minDesiredProfit, dfEight, dfTwo, customProperties);
            break;
        case 3:
            getAssetStates(bittrex,dfTwo, dfEight);
            break;
        case 4:
            currency = getCurrency(currencyFile);
            validateNotificationConfiguration(currency, customProperties);
            monitorAndNotify(bittrex, currency, dfTwo, customProperties);
            break;
        default:
            System.out.println("Option not available");
            break;
        }
    }

    private static void monitorAndNotify(Bittrex bittrex, String currency,
            DecimalFormat dfTwo, Map<String, String> customProperties) {

        double crashFallInPercent = Double.valueOf(customProperties.get("monitor.crashFallInPercent"));
        int notificationIntervals = Integer.valueOf(customProperties.get("monitor.notificationIntervals"));
        long pollIntervalInMillis = Long.valueOf(customProperties.get("monitor.pollIntervalInMillis"));
        
        String market = "BTC-" + currency;
        SlackService slackService = new SlackService();

        Map<String, String> marketMap = getCurrentViewOfMarket(bittrex, market);
        double basePriceInBtc = Double.valueOf(marketMap.get("PrevDay"));
        String initialTradePriceStr = marketMap.get("Last");
        double initialTradePriceInBtc = Double.valueOf(initialTradePriceStr);

        double priceAtStartInBtc = initialTradePriceInBtc;
        String priceAtStartStr = initialTradePriceStr;
        double currentPriceInBtc = priceAtStartInBtc;
        String currentPriceStr = priceAtStartStr;
        int currentIteration = 0;
        boolean hasNotified =false;
        String positiveColor = "#0bb000";
        String negativeColor = "#a20000";
        String color = "";

        System.out.println(String .format("Monitoring price action of %s....",currency));

        while (true) {

            double changePercent = ((currentPriceInBtc - priceAtStartInBtc) * 100) / basePriceInBtc;
            changePercent = Double.valueOf(dfTwo.format(changePercent));

            if (Double.compare((-1 * changePercent), crashFallInPercent) > 0) {
                color = (Double.compare(changePercent, 0) >= 0) ? positiveColor : negativeColor;
                String notificationMsg  = String.format("Price crashed by %7s%% from base_price=%s to current_price=%s", changePercent,
                        priceAtStartStr, currentPriceStr);
                System.out.println(notificationMsg);
                slackService.notifyOnChannel(currency, market, notificationMsg, color);
                currentIteration = 0;
                priceAtStartInBtc = currentPriceInBtc;
                priceAtStartStr = currentPriceStr;
                hasNotified = true;
            }

            if(currentIteration == notificationIntervals) {
                if(!hasNotified) {
                    color = (Double.compare(changePercent, 0) >= 0) ? positiveColor : negativeColor;
                    String notificationMsg  = String.format("Price changed by %7s%% from base_price=%s to current_price=%s", changePercent,
                            priceAtStartStr, currentPriceStr);
                    System.out.println(notificationMsg);
                    slackService.notifyOnChannel(currency, market, notificationMsg, color);
                }
                currentIteration = 0;
                priceAtStartInBtc = currentPriceInBtc;
                priceAtStartStr = currentPriceStr;
                hasNotified =false;
            }

            try {
                Thread.sleep(pollIntervalInMillis);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            marketMap = getCurrentViewOfMarket(bittrex, market);
            try {
                currentPriceStr = marketMap.get("Last");
                currentPriceInBtc = Double.valueOf(currentPriceStr);
            } catch (Exception e) {
                e.printStackTrace();
            }
            currentIteration++;
        }
    }

    private static void validateTradeConfiguration(String currency,
            Map<String, String> customProperties) {
        System.out.println();
        customProperties.keySet().forEach(
                a -> System.out.println(String.format("%s=%s", a, customProperties.get(a))));
        System.out.println();
        System.out.println(String.format("Check above configuration before proceeding to trade %s.",
                currency));
        System.out.println("");
        Scanner configScanner = new Scanner(System.in);
        System.out.print("Do u want to change spend percent:");
        String readSpendPercent = configScanner.nextLine();
        if (readSpendPercent.equals("y")) {
            System.out.print("Enter value for btc.spendPercent:");
            String spendPercent = configScanner.nextLine();
            customProperties.put("btc.spendPercent", spendPercent);
            System.out.println("value="+customProperties.get("btc.spendPercent"));
        }
        System.out.print("Do u want to change minProfit:");
        String readMinProfit = configScanner.nextLine();
        if (readMinProfit.equals("y")) {
            System.out.print("Enter value for btc.minDesiredProfit:");
            String minProfit = configScanner.nextLine();
            customProperties.put("btc.minDesiredProfit", minProfit);
            System.out.println("value="+customProperties.get("btc.minDesiredProfit"));
        }

        System.out.print("Do u want to change demandIncrease:");
        String readDemandIncrease = configScanner.nextLine();
        if (readDemandIncrease.equals("y")) {
            System.out.print("Enter value for btc.demandIncrease:");
            String demandIncrease = configScanner.nextLine();
            customProperties.put("btc.demandIncrease", demandIncrease);
            System.out.println("value="+customProperties.get("btc.demandIncrease"));
        }

        System.out.print("Press -1 to continue. Press any other number to exit:");
        Scanner in = new Scanner(System.in);
        int option = in.nextInt();
        if (option != -1) {
            System.exit(0);
        }
    }

    private static void validateNotificationConfiguration(String currency,
            Map<String, String> customProperties) {
        System.out.println();
        customProperties.keySet().forEach(
                a -> {
                    if (a.startsWith("monitor")) {
                        System.out.println(String.format("%s=%s", a, customProperties.get(a)));
                    }
                }
        );
        System.out.println();
        System.out.println(String.format("Check above notification settings for monitoring currency %s",
                currency));
        System.out.println("");

        System.out.print("Press -1 to continue. Press any other number to exit:");
        Scanner in = new Scanner(System.in);
        int option = in.nextInt();
        if (option != -1) {
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

    private static String getCurrency(String filenName) {
        int j=0;
        int rowSize = 6;
        for (int i = 0; i < currencies.size(); i++) {
            String separator =j<rowSize ?"\t":"\n";
            j = j<rowSize?++j:0;
            System.out.print(String.format("[ %2s:%-5s ]%s", i, currencies.get(i), separator));
        }
        System.out.print("\nEnter currency No:");
        Scanner in = new Scanner(System.in);

        int index = in.nextInt();
        in.nextLine();
        if(index < 0) {
            System.out.print("Enter the new currency:");
            String newCurrency = in.nextLine();

            for(int i=0;i<currencies.size();i++) {
                if(currencies.get(i).equals(newCurrency)){
                    index = i;
                    break;
                }
            }

            if(index < 0) {
                Utils.appendCurrency(filenName, newCurrency);
                currencies.add(newCurrency);
                index = currencies.size()-1;
            } else {
                System.out.println("Currency is available in the file:"+filenName);
            }
        }

        return currencies.get(index);
    }

    private static void getStateOfHoldings() {

    }

    private static void buyAndSellCurrency(Bittrex bittrex, String currency, double desiredProfit,
            double demandIncrease, DecimalFormat dfEight, DecimalFormat dfTwo, String tradeCriteria, double lastTradedPrice,
            double basePrice, double buyPrice, double totalBTCInvestment, double commission) {

        String market = "BTC-" + currency;

        double change = ((lastTradedPrice - basePrice)/basePrice)*100;

        System.out.println(String.format("%s is trading at %s pecent higher than base price",market, change));

        double buyQuantity = getBuyQuantity(dfEight, commission, buyPrice, totalBTCInvestment);

        change = ((buyPrice - basePrice) / basePrice) * 100;
        System.out.println(String.format("[%s] Placing buy order to execute when the market reaches %s",
                        market, change));
        placeBuyOrderAndWait(bittrex, dfEight, currency, market, buyPrice, buyQuantity);

        String sellPrice = "";
        if (tradeCriteria.equals("DEMAND")) {
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
            double totalBtcInvestment) {
        double buyQuantity;
        double tradingBTCAmount = totalBtcInvestment * (1 - commission/100);

        tradingBTCAmount = Double.valueOf(dfEight.format(tradingBTCAmount));
        buyQuantity = tradingBTCAmount/buyPrice;
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

    private static void getAssetStates(Bittrex bittrex, DecimalFormat dfTwo, DecimalFormat dfEight) {
        String BTC_MARKET ="BTC";
        String USDT_MARKET="USDT";
        List<HashMap<String, String >> positiveBalances = getPositiveBalances(bittrex);
        System.out.println(String.format("%-8s\t\t%-9s\t\t%-10s\t\t%8s\t\t%-6s%%\t\t%-15s\t\t%-9s\t\t%-8s","currency","buy_price","last_price","diff(SAT)","profit","volume","24HR HIGH","24HR LOW"));
        for(HashMap<String, String> balanceMap : positiveBalances) {
            String currency = balanceMap.get("Currency");
            if(currency.equals(BTC_MARKET) || currency.equals(USDT_MARKET)) {
                continue;
            }
            String market = BTC_MARKET+"-" +currency;
            String buyPrice  = getMostRecentBuyPrice(bittrex, market );
            Map<String, String> marketMap = getCurrentViewOfMarket(bittrex, market);
            String lastTradedPrice = marketMap.get("Last");
            String closingPrice = marketMap.get("PrevDay");
            String high = marketMap.get("High");
            String low = marketMap.get("Low");
            String bidPrice = marketMap.get("Bid");
            String AskPrice = marketMap.get("Ask");
            String baseVolume = marketMap.get("BaseVolume");

            double lastTradeValueInDouble = Double.valueOf(dfEight.format(Double.valueOf(lastTradedPrice)));
            double buyPriceInDouble = Double.valueOf(dfEight.format(Double.valueOf(buyPrice)));
            int diff = (int)(lastTradeValueInDouble*SATOSHI_CONST) - (int)(buyPriceInDouble*SATOSHI_CONST);

            double currentProfit = Double.valueOf(dfTwo.format(((lastTradeValueInDouble-buyPriceInDouble)*100)/buyPriceInDouble));
            System.out.println(String.format("%-8s\t\t%-9s\t\t%-10s\t\t%8s\t\t%6s%%\t\t%-15s\t\t%-9s\t\t%-8s",currency,buyPrice,lastTradedPrice,diff,currentProfit, baseVolume, high,low));
        }
    }

    private static List<HashMap<String, String >> getPositiveBalances(Bittrex bittrex) {
        String balancesStr = bittrex.getBalances();
        List<HashMap<String, String >> balancesList = Bittrex.getMapsFromResponse(balancesStr);
        List<HashMap<String, String >> positiveBalancesList = balancesList.stream().filter( currencyMap -> {
            double balance = Double.valueOf(currencyMap.get("Balance"));
            if(Double.compare(balance, 0) >0) {
                return true;
            } else {
                return false;
            }
        }
        ).collect(Collectors.toList());
        return positiveBalancesList;
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

    private static double getHighestRate(JsonArray buyOrders) {
        JsonObject buyOrderJsonObject = buyOrders.get(0).getAsJsonObject();
        double quantity = buyOrderJsonObject.get("Quantity").getAsDouble();
        return buyOrderJsonObject.get("Rate").getAsDouble();
    }

    private static JsonArray getOrdersByType(JsonObject resultJsonObject, String type) {
        return resultJsonObject.getAsJsonArray(type);
    }

    private static JsonObject getOrderBookJson(Bittrex bittrex, String market, String type) {
        String marketOrderBookJsonStr = bittrex.getOrderBook(market, type);
        Gson gson = new Gson();
        JsonObject jsonObject = gson.fromJson(marketOrderBookJsonStr, JsonObject.class);
        return jsonObject.get("result").getAsJsonObject();
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
             double totalBTCInvestment, double commission, double minProfit, DecimalFormat dfEight,
            DecimalFormat dfTwo, Map<String, String> customProperties) {

        double tolerance = Double.valueOf(customProperties.get("btc.tolerance"));
        int n = Integer.valueOf(customProperties.get("btc.queueSize"));
        double maxSpread = Double.valueOf(customProperties.get("btc.maxSpread"));;

        String market = "BTC-" + currency;
        LinkedList<Double> tradePriceWindow = new LinkedList<>();
        MinMaxPriorityQueue<Double> pq = MinMaxPriorityQueue.create();

        Map<String, String> marketMap = getCurrentViewOfMarket(bittrex, market);
        double basePriceInBtc = Double.valueOf(marketMap.get("PrevDay"));
        double initialTradePriceInBtc = Double.valueOf(marketMap.get("Last"));
        double basePriceInSatoshi = basePriceInBtc * SATOSHI_CONST;

        while (true) {
            marketMap = getCurrentViewOfMarket(bittrex, market);

            double recentTradePriceInSatoshi = 0;
            double recentTradePriceInBtc =0;
            try {
             recentTradePriceInBtc = Double.valueOf(marketMap.get("Last"));
             recentTradePriceInSatoshi =  recentTradePriceInBtc * SATOSHI_CONST;
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Caught Exception while getting last traded price. Continuing with next iteration...");
                continue;
            }

            double prevTradePriceInQ = recentTradePriceInSatoshi;

            int currentWindowSize = tradePriceWindow.size();
            if (currentWindowSize == 0) {
                tradePriceWindow.add(recentTradePriceInSatoshi);
                pq.add(recentTradePriceInSatoshi);
            } else {

                prevTradePriceInQ = tradePriceWindow.peekLast();

                if (Double.compare(recentTradePriceInSatoshi, prevTradePriceInQ) != 0) {
                    if (currentWindowSize == n) {
                        double firstTradeInWindow = tradePriceWindow.remove();
                        if (!pq.remove(firstTradeInWindow)) {
                            System.out.println("remove Failed");
                        }
                    }
                    tradePriceWindow.add(recentTradePriceInSatoshi);
                    pq.add(recentTradePriceInSatoshi);
                }
            }

            currentWindowSize = tradePriceWindow.size();
            if (currentWindowSize != pq.size()) {
                System.out.println("size not matching .........");
            }
            double changeFromLastDay = ((recentTradePriceInSatoshi - basePriceInSatoshi) / basePriceInSatoshi) * 100;
            double changeFromPrevTrade =
                    ((recentTradePriceInSatoshi - prevTradePriceInQ) / basePriceInSatoshi) * 100;
            double highestPriceInSatoshi = pq.peekLast();
            double highestPercentageInWindow = ((highestPriceInSatoshi - basePriceInSatoshi) / basePriceInSatoshi) * 100;
            double lowestPriceInSatoshi = pq.peekFirst();
            double lowestPercentageInWindow = ((lowestPriceInSatoshi - basePriceInSatoshi) / basePriceInSatoshi) * 100;

            //            System.out.print("\r");
            System.out.println(String.format(
                    "[%s] QueueSize=%s. Change=%s%%. rel_prev_trade=%22s%%. Highest=%s%%. Lowest=%s%%. LastPrice=%s. prevToLastPrice=%s. HighestPrice=%s. LowestPrice=%s. closingPrice=%s",
                    market, currentWindowSize, dfTwo.format(changeFromLastDay), changeFromPrevTrade,
                    dfTwo.format(highestPercentageInWindow), dfTwo.format(lowestPercentageInWindow),
                    recentTradePriceInSatoshi, prevTradePriceInQ, highestPriceInSatoshi, lowestPriceInSatoshi, basePriceInSatoshi));

            double changeFromInitialTrade = ((initialTradePriceInBtc - recentTradePriceInBtc) * 100)/basePriceInBtc;
            if(Double.compare(changeFromInitialTrade,tolerance) >= 0) {
                System.out.println(String.format("[%s] current trading price is down by more than %s%% from the initial observed price. Quitting the trade....", market, tolerance));
                break;
            }

            if(Double.compare((-1*changeFromInitialTrade),tolerance) >= 0) {
                System.out.println(String.format("[%s] current trading price is up by more than %s%% from the initial observed price. Quitting the trade....", market, tolerance));
                break;
            }
            double requestedIncrease = (highestPercentageInWindow - lowestPercentageInWindow) / 2;
            if (Double.compare(highestPercentageInWindow - changeFromLastDay, requestedIncrease)
                    >= 0) {
                System.out.println(
                        String.format("[%s] currently operating in the lower half of profit window",
                                market));
                double achievableProfit = getExpectedNetProfit(changeFromLastDay,
                        String.valueOf(requestedIncrease), commission, dfTwo);
                if (Double.compare(achievableProfit, minProfit) >= 0) {
                    if (Double.compare(changeFromPrevTrade, 0) > 0){
                        double buyPrice = 0;

                        JsonObject resultJsonObject = getOrderBookJson(bittrex, market, "both");

                        JsonArray buyOrders = getOrdersByType(resultJsonObject, "buy");
                        double bidPrice = getHighestRate(buyOrders);
                        //        bidPrice = Double.valueOf(dfEight.format(bidPrice));


                        JsonArray sellOrders = getOrdersByType(resultJsonObject, "sell");
                        double askPrice = getHighestRate(sellOrders);

                        double spread = Double.valueOf(dfTwo.format(((askPrice-bidPrice)*100)/recentTradePriceInSatoshi));

                        if(Double.compare(spread, maxSpread) > 0) {
                            System.out.println(String.format("[%s] Too much spread.Current spread=%s. Required spread<=%s", market, spread, maxSpread));
                        } else {
                            buyPrice = bidPrice + 0.00000001;
                            buyAndSellCurrency(bittrex, currency, minProfit,
                                    requestedIncrease, dfEight, dfTwo, "DEMAND",
                                    recentTradePriceInBtc,
                                    basePriceInBtc, buyPrice, totalBTCInvestment, commission);
                            break;
                        }
                } else {
                        System.out.println(String.format("[%s] Minimum Profit is realizable. Market is not moving up. Continuing the watch...", market));
                    }
                } else {
                    System.out.println(String.format("[%s] Min profit not achievable at current price. Predicted profit=%s. Min profit=%s", market, achievableProfit, minProfit));
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
        double buyPrice = Double.valueOf(getMostRecentBuyPrice(bittrex, market));

        System.out.println(String.format("[%s] My most recent buy-order price: %s", market, buyPrice ));

        double bidPrice = buyPrice * (1 + desiredProfitPercent/100);
        bidPrice = (double) Math.round(bidPrice * 100000000) / 100000000;
        String bidPriceStr = String.valueOf(bidPrice);
        return bidPriceStr;
    }

    private static String getMostRecentBuyPrice(Bittrex bittrex, String market) {
        String orderHistoryJsonStr = bittrex.getOrderHistory(market);

        //        String marketOrderHistoryPrettyStr = Utils.prettyPrintJsonStr(orderHistoryJsonStr);
        //        Utils.writeToFile(marketOrderHistoryPrettyStr,market+"_order_history");

        List<HashMap<String, String>> orderHistoryMapsList = Bittrex.getMapsFromResponse(orderHistoryJsonStr);
        Map<String, String> mostRecentBuyOrder = getRecentBuyOrderHistory(orderHistoryMapsList);
        String buyPriceStr = mostRecentBuyOrder.get("Limit");
        return buyPriceStr;
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
