package app.store.service.dto;

import app.store.service.dto.enums.TrackingStatusDto;

import java.time.Instant;

public class TrackingDto {

    private String id;
    private TrackingStatusDto status;
    private Instant time;

    public TrackingDto() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public TrackingStatusDto getStatus() {
        return status;
    }

    public void setStatus(TrackingStatusDto status) {
        this.status = status;
    }

    public Instant getTime() {
        return time;
    }

    public void setTime(Instant time) {
        this.time = time;
    }
}
