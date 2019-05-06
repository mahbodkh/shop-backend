package app.store.persistence.domain;

import app.store.persistence.domain.enums.TrackingStatus;
import org.bson.types.ObjectId;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

public class Tracking implements Serializable {
    private static final long serialVersionUID = 1L;

    private ObjectId id;
    private TrackingStatus status;
    private Instant time;

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public TrackingStatus getStatus() {
        return status;
    }

    public void setStatus(TrackingStatus status) {
        this.status = status;
    }

    public Instant getTime() {
        return time;
    }

    public void setTime(Instant time) {
        this.time = time;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tracking tracking = (Tracking) o;
        return Objects.equals(id, tracking.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Tracking{" +
                "id=" + id +
                ", status=" + status +
                ", time=" + time +
                '}';
    }
}
