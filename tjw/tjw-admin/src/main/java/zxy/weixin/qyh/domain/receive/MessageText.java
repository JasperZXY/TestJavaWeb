package zxy.weixin.qyh.domain.receive;

public class MessageText extends BaseMessage {
    public static final String MSG_TYPE = "text";
    private String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
