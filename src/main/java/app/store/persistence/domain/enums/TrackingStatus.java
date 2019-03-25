package app.store.persistence.domain.enums;

public enum TrackingStatus {

    PREPARING,
    REGISTERED,
    ONTRUCK,
    COMPLETE,
    DELIVERED;

    TrackingStatus() {
    }

    private String value;

    public String getValue() {
        return value;
    }
}
