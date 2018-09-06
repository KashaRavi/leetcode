import com.google.gson.Gson;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.AutoRetryHttpClient;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class SlackService {
    String webhook;
    String username;
    String icon_url;
    CloseableHttpClient httpclient;
    HttpPost httpPost;
    Gson gson;


    public static void main(String[] args)
    {
        SlackService slackService = new SlackService();
        slackService.notifyOnChannel("Test","PRICE_UP", "PIVX Price up", "#a20000");
    }

    SlackService() {
        this.webhook = getWebhookFromTextFile("slack.properties");
        this.username = "Bittrex";
        this.icon_url = "https://slack.com/img/icons/app-57.png";
        this.httpclient = HttpClients.createDefault();
        this.httpPost = new HttpPost(this.webhook);
        this.gson = new Gson();
    }

    public String getWebhookFromTextFile(String textFile) {

        String webhook = null;

        try (Scanner scan = new Scanner(getClass().getResourceAsStream(textFile))) {

            String webhookLine = scan.nextLine();

            webhook = webhookLine
                    .substring(webhookLine.indexOf("\"") + 1, webhookLine.lastIndexOf("\""));

        } catch (NullPointerException | IndexOutOfBoundsException e) {

            System.err.println(
                    "Text file not found or corrupted - please attach key & secret in the format provided.");
        }

        return webhook;
    }

    public void notifyOnChannel(String username, String title, String text, String color) {
        SlackRequest notificationRequest = new SlackRequest();
        notificationRequest.username = username;
        notificationRequest.icon_url = this.icon_url;
        MessageAttachment attachment = new MessageAttachment();
        attachment.color = color;
        attachment.title = title;
        attachment.text = text;
        attachment.mrkdwn_in = new ArrayList<>();
        attachment.mrkdwn_in.add("text");

        notificationRequest.attachments = new ArrayList<MessageAttachment>() {
            {
                add(attachment);
            }
        };

        sendNotification(notificationRequest);
    }

    public void sendNotification(SlackRequest notification) {
        try {
            String str = this.gson.toJson(notification);
            this.httpPost.setEntity(new StringEntity(str));
            CloseableHttpResponse response= httpclient.execute(httpPost);
            response.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

