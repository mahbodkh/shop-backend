package app.store.persistence.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.*;

@org.springframework.data.mongodb.core.mapping.Document(collection = "category")
public class Category extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private ObjectId id;

    @Field
    private Integer level;

    @Indexed
    @Field
    @Size(min = 2, max = 100)
    private String name;

    @Field
    @Size(min = 5, max = 2000)
    private String description;

    @Field
    private Integer member = 0;

    @Field
    private Set<Category> parent = new HashSet<>();

    @Field
    private Set<Category> child = new HashSet<>();

    @Field
    private List<Asset> assetList = new ArrayList<>();

    @JsonIgnore
    private MultipartFile data;


    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
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

    public Integer getMember() {
        return member;
    }

    public void setMember(Integer member) {
        this.member = member;
    }

    public Set<Category> getParent() {
        return parent;
    }

    public void setParent(Set<Category> parent) {
        this.parent = parent;
    }

    public Set<Category> getChild() {
        return child;
    }

    public void setChild(Set<Category> child) {
        this.child = child;
    }

    public List<Asset> getAssetList() {
        return assetList;
    }

    public void setAssetList(List<Asset> assetList) {
        this.assetList = assetList;
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
                ", level=" + level +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", member=" + member +
                ", parent=" + parent +
                ", child=" + child +
                ", assetList=" + assetList +
                ", data=" + data +
                '}';
    }
}
