package app.store.persistence.domain;

import java.io.Serializable;

public class Variant implements Serializable {

    private static final long serialVersionUID = 1L;

    private String specType;             // COLOR, KIND
    private String title;                // Name of the Spec


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSpecType() {
        return specType;
    }

    public void setSpecType(String specType) {
        this.specType = specType;
    }


    @Override
    public String toString() {
        return "Variant{" +
                "title='" + title + '\'' +
                ", specType='" + specType + '\'' +
                '}';
    }
}
