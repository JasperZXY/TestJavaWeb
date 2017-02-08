package zxy.weixin.qyh.domain.receive;

/**
 * 基础消息类型
 */
public class BaseMessage extends BaseReceiveObject {
    protected Long msgId;

    public Long getMsgId() {
        return msgId;
    }

    public void setMsgId(Long msgId) {
        this.msgId = msgId;
    }
}
