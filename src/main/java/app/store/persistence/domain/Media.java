package app.store.persistence.domain;


import app.store.persistence.domain.enums.AssetType;
import org.bson.types.ObjectId;

import java.io.Serializable;
import java.util.Objects;

public class Media implements Serializable {
    private static final long serialVersionUID = 1L;

    private ObjectId id;
    private AssetType type;
    private String name;
    private String url;
    private String extension;
    private Long capacity;
    private Long width;
    private Long height;
    private Double duration;


    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public AssetType getType() {
        return type;
    }

    public void setType(AssetType type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public Long getCapacity() {
        return capacity;
    }

    public void setCapacity(Long capacity) {
        this.capacity = capacity;
    }

    public Long getWidth() {
        return width;
    }

    public void setWidth(Long width) {
        this.width = width;
    }

    public Long getHeight() {
        return height;
    }

    public void setHeight(Long height) {
        this.height = height;
    }

    public Double getDuration() {
        return duration;
    }

    public void setDuration(Double duration) {
        this.duration = duration;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Media media = (Media) o;
        return Objects.equals(id, media.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Media{" +
                "id=" + id +
                ", type=" + type +
                ", name='" + name + '\'' +
                ", url='" + url + '\'' +
                ", capacity=" + capacity +
                ", width=" + width +
                ", height=" + height +
                '}';
    }
}
