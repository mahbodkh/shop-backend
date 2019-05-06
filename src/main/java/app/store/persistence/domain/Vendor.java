package app.store.persistence.domain;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;
import java.util.Objects;

@org.springframework.data.mongodb.core.mapping.Document(collection = "vendor")
public class Vendor implements Serializable {
    private static final long serialVersionUID = 1L;

    @Field
    private ObjectId id;
    @Field
    private String cover;
    @Field
    private String name;
    @Field
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vendor vendor = (Vendor) o;
        return Objects.equals(id, vendor.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Vendor{" +
                "id=" + id +
                ", cover='" + cover + '\'' +
                ", name='" + name + '\'' +
                ", description=" + description +
                '}';
    }
}
