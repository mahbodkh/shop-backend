package app.store.persistence.domain;

import org.bson.types.ObjectId;

import java.io.Serializable;

@org.springframework.data.mongodb.core.mapping.Document(collection = "brand")
public class Brand implements Serializable {

    private static final long serialVersionUID = 1L;

    private ObjectId id;
    private String cover;
    private String name;
    private Description description;

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Description getDescription() {
        return description;
    }

    public void setDescription(Description description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Brand{" +
                "id=" + id +
                ", cover='" + cover + '\'' +
                ", name='" + name + '\'' +
                ", description=" + description +
                '}';
    }
}
