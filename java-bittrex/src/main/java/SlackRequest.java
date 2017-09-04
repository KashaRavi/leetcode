import java.util.List;

/**
 * This class represents slack notification request
 */
public class SlackRequest {
    public String username;
    public String icon_url;
    //channel is filled by the slack provider service when sending to slack service
    public String channel;
    public List<MessageAttachment> attachments;
}