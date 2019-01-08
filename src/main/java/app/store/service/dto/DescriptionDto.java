package app.store.service.dto;

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

}
