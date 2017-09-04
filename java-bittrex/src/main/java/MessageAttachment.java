import java.util.List;

/**
 * This class represents the message attachment for slack notification
 */
public class MessageAttachment {
    public String color;
    public String author_name;
    public String title;
    public String text;
    public long ts;
    public List<String> mrkdwn_in;
}