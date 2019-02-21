package app.store.persistence.domain;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;

@org.springframework.data.mongodb.core.mapping.Document(collection = "configuration")
public class Configuration implements Serializable {

    @Field
    private ObjectId id;
    @Field
    private String license;
    @Field
    private String theme;
    @Field
    private String imageSize;


    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getLicense() {
        return license;
    }

    public void setLicense(String license) {
        this.license = license;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public String getImageSize() {
        return imageSize;
    }

    public void setImageSize(String imageSize) {
        this.imageSize = imageSize;
    }
}
