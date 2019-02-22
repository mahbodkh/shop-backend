package app.store.service.dto.enums;

public enum TrackingStatusDto {

    PREPARING,
    REGISTERD,
    ONTRUCK,
    COMPLETE,
    DELIVERED;

    TrackingStatusDto() {
    }

    private String value;

    public String getValue() {
        return value;
    }

}
