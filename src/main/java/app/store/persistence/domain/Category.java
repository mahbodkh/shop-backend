package app.store.persistence.domain;


import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.*;

@org.springframework.data.mongodb.core.mapping.Document(collection = "category")
public class Category extends AbstractAuditingEntity implements Serializable {
    private static final long serialVersionUID = 1L;


    @Id
    private ObjectId id;

    @Indexed
    @Field
    @Size(min = 2, max = 100)
    private String name;
    @Field
    @Size(min = 5, max = 2000)
    private String description;
    @Field
    private ObjectId parent;
    @Field
    private Integer member = 0;
    @Field
    private String path;            // path of the categories
    @Field
    private String cover;           // url of the image
    @Field
    private List<ObjectId> ancestors = new ArrayList<>();
    @Field
    private List<String> facets = new ArrayList<>();


    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ObjectId getParent() {
        return parent;
    }

    public void setParent(ObjectId parent) {
        this.parent = parent;
    }

    public Integer getMember() {
        return member;
    }

    public void setMember(Integer member) {
        this.member = member;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public List<ObjectId> getAncestors() {
        return ancestors;
    }

    public void setAncestors(List<ObjectId> ancestors) {
        this.ancestors = ancestors;
    }

    public List<String> getFacets() {
        return facets;
    }

    public void setFacets(List<String> facets) {
        this.facets = facets;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Category category = (Category) o;
        return Objects.equals(id, category.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }


    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", parent=" + parent +
                ", member=" + member +
                ", path='" + path + '\'' +
                ", cover='" + cover + '\'' +
                ", ancestors=" + ancestors +
                ", facets=" + facets +
                '}';
    }
}
