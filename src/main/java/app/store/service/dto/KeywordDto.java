package app.store.service.dto;

import app.store.service.util.TextHelper;

public class KeywordDto {

    private String id;
    private String name;
    private String language;
    private String description;

    public KeywordDto() {
    }

    public String getId() {
        return TextHelper.toStandardPersian(id);
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

    public String getLanguage() {
        return TextHelper.toStandardPersian(language);
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getDescription() {
        return TextHelper.toStandardPersian(description);
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
