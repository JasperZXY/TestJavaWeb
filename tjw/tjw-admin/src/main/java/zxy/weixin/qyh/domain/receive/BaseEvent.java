package zxy.weixin.qyh.domain.receive;

/**
 * 基础事件
 */
public class BaseEvent extends BaseReceiveObject {
    public static final String MSG_TYPE = "event";
    protected String event;
    protected String eventKey;

    public BaseEvent() {
        this.msgType = MSG_TYPE;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public String getEventKey() {
        return eventKey;
    }

    public void setEventKey(String eventKey) {
        this.eventKey = eventKey;
    }
}
