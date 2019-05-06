package app.store.persistence.domain;

public class Specification {

    private String name;
    private String description;
    private boolean isTitle;
    private String language;

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

    public boolean isTitle() {
        return isTitle;
    }

    public void setTitle(boolean title) {
        isTitle = title;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }



    @Override
    public String toString() {
        return "SpecificationDto{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", isTitle=" + isTitle +
                ", language='" + language + '\'' +
                '}';
    }
}
