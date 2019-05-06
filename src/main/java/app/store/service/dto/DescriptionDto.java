package app.store.service.dto;

import app.store.service.util.TextHelper;

public class DescriptionDto {

    private String language;
    private String name;
    private String shortDescribe;
    private String longDescribe;

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getName() {
        return TextHelper.toStandardPersian(name);
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShortDescribe() {
        return TextHelper.toStandardPersian(shortDescribe);
    }

    public void setShortDescribe(String shortDescribe) {
        this.shortDescribe = shortDescribe;
    }

    public String getLongDescribe() {
        return TextHelper.toStandardPersian(longDescribe);
    }

    public void setLongDescribe(String longDescribe) {
        this.longDescribe = longDescribe;
    }

}
