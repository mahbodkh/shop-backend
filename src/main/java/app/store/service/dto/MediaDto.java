package app.store.service.dto;

import app.store.service.dto.enums.AssetTypeDto;
import app.store.service.util.TextHelper;

public class MediaDto {

    private String id;
    private AssetTypeDto type;
    private String name;
    private String url;
    private Long capacity;
    private Long width;
    private Long height;
    private Double duration;

    public MediaDto() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public AssetTypeDto getType() {
        return type;
    }

    public void setType(AssetTypeDto type) {
        this.type = type;
    }

    public String getName() {
        return TextHelper.toStandardPersian(name);
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
}
