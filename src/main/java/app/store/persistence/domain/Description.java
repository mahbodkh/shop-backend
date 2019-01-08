package app.store.persistence.domain;

import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.validation.constraints.Size;
import java.io.Serializable;

public class Description implements Serializable {
    private static final long serialVersionUID = 1L;

    private String language;

    @Indexed
    @Field
    @Size(min = 2, max = 120)
    private String name;

    @Field
    @Size(min = 5, max = 2000)
    private String shortDescribe;
    @Field
    @Size(min = 5, max = 5000)
    private String longDescribe;

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShortDescribe() {
        return shortDescribe;
    }

    public void setShortDescribe(String shortDescribe) {
        this.shortDescribe = shortDescribe;
    }

    public String getLongDescribe() {
        return longDescribe;
    }

    public void setLongDescribe(String longDescribe) {
        this.longDescribe = longDescribe;
    }

    @Override
    public String toString() {
        return "Description{" +
                "language='" + language + '\'' +
                ", name='" + name + '\'' +
                ", shortDescribe='" + shortDescribe + '\'' +
                ", longDescribe='" + longDescribe + '\'' +
                '}';
    }
}
