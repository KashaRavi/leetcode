
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import org.apache.http.HttpEntity;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpResponseException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import sun.misc.BASE64Encoder;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.text.DecimalFormat;
import java.text.NumberFormat;

public class Bittrex {
    public static final String SELF_LINK = "";
    public static void main(String[] args)
    {
//        CloseableHttpClient httpclient = HttpClients.createDefault();
        Bittrex bittrex = new Bittrex();
//        bittrex.computeRequiredProfitPercentage();
        try {
            bittrex.getCall();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void getCall()  throws IOException{
        String apiKey="";
        String apiSecret="";
        String nonce = String.valueOf(System.currentTimeMillis());
        String getOpenOrders=String.format("https://bittrex.com/api/v1.1/market/getopenorders?apikey=%s&nonce=%s", apiKey, nonce);
        String getBalancesUrl = String.format("https://bittrex.com/api/v1.1/account/getbalances?apikey=%s&nonce=%s", apiKey, nonce);
        String getMarkets = String.format("https://bittrex.com/api/v1.1/public/getmarkets?apikey=%s&nonce=%s", apiKey, nonce);

        String uri = getBalancesUrl;
//        String uri = getMarkets;

        System.out.println(uri);
        String apiSign = null;
        BASE64Encoder encoder = new BASE64Encoder();
        try{
            byte [] byteKey = apiSecret.getBytes("UTF-8");
            final String HMAC_SHA512 = "HmacSHA512";

            Mac sha512_HMAC = Mac.getInstance(HMAC_SHA512);
            SecretKeySpec keySpec = new SecretKeySpec(byteKey, HMAC_SHA512);

            sha512_HMAC.init(keySpec);
            byte [] mac_data = sha512_HMAC.doFinal(uri.getBytes("UTF-8"));

//            apiSign = encoder.encode(mac_data);
            apiSign = mac_data.toString();
//            result = Base64.encode(mac_data);
            System.out.println("Signed api:" + apiSign);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        }finally{
//            System.out.println("Done");
        }

        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpGet httpget = new HttpGet(uri);
        httpget.addHeader("apisign", apiSign);
        CloseableHttpResponse response = null;
        try {
            response = httpclient.execute(httpget);
        } catch (IOException e) {
            e.printStackTrace();
        }

        StatusLine statusLine = response.getStatusLine();
//        System.out.println(statusLine.getStatusCode());
        HttpEntity entity = response.getEntity();
        if (statusLine.getStatusCode() >= 300) {
            try {
                throw new HttpResponseException(
                        statusLine.getStatusCode(),
                        statusLine.getReasonPhrase());
            } catch (HttpResponseException e) {
                e.printStackTrace();
            }
        }
        if (entity == null) {
            throw new ClientProtocolException("Response contains no content");
        }
        Gson gson = new GsonBuilder().create();
        ContentType contentType = ContentType.getOrDefault(entity);
        Charset charset = contentType.getCharset();
        Reader reader = new InputStreamReader(entity.getContent(), charset);
         JsonObject obj = gson.fromJson(reader, JsonObject.class);
        System.out.println("Output:" +obj.toString());
        try {
        } finally {
            response.close();
        }
    }

    public void computeRequiredProfitPercentage()
    {
        double requiredPercentage = getRequiredProfitPercentage(2, 5.2);
        NumberFormat formatter = new DecimalFormat("#0.00");
        System.out.format(formatter.format(requiredPercentage));
    }

    /**
     *
     * @param y Desired profit percentage
     * @param z Percentage change at the time of buying.
     * @return sell % to achieve the desired profit.
     */
    public double getRequiredProfitPercentage(double y, double z) {
        double x = (25 + y * (100 + z) + 100 * z) / 99.75;
        return x;
    }


}
