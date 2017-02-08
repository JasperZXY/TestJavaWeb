package zxy.weixin.qyh.domain.receive;

public class EventLocation extends BaseEvent {
    public static final String EVENT = "LOCATION";
    private Double latitude;    // 地理位置纬度
    private Double longitude;   // 地理位置经度
    private Double precision;   // 地理位置精度

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getPrecision() {
        return precision;
    }

    public void setPrecision(Double precision) {
        this.precision = precision;
    }
}
