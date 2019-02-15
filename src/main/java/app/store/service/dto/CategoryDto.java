package app.store.service.dto;

import app.store.service.util.TextHelper;

import java.util.ArrayList;
import java.util.List;

public class CategoryDto {

    public CategoryDto() {
    }

    private String id;
    private String name;
    private String description;
    private String parent;
    private Integer member = 0;
    private String path;
    private String cover;
    private List<String> ancestors = new ArrayList<>();
    private List<String> facets = new ArrayList<>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return TextHelper.toStandardPersian(name);
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return TextHelper.toStandardPersian(description);
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
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

    public List<String> getAncestors() {
        return ancestors;
    }

    public void setAncestors(List<String> ancestors) {
        this.ancestors = ancestors;
    }

    public List<String> getFacets() {
        return facets;
    }

    public void setFacets(List<String> facets) {
        this.facets = facets;
    }
}
