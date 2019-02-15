package app.store.service.dto;

import app.store.service.dto.enums.AssetTypeDto;
import app.store.service.util.TextHelper;

public class MediaDto {

    private String id;
    private AssetTypeDto type;
    private String name;
    private String url;
    private Double capacity;
    private Double width;
    private Double height;
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

    public Double getCapacity() {
        return capacity;
    }

    public void setCapacity(Double capacity) {
        this.capacity = capacity;
    }

    public Double getWidth() {
        return width;
    }

    public void setWidth(Double width) {
        this.width = width;
    }

    public Double getHeight() {
        return height;
    }

    public void setHeight(Double height) {
        this.height = height;
    }

    public Double getDuration() {
        return duration;
    }

    public void setDuration(Double duration) {
        this.duration = duration;
    }
}
