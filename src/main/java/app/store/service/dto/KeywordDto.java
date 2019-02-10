package app.store.service.dto;

public class KeywordDto {

    private String id;
    private String name;
    private DescriptionDto descriptionDto;

    public KeywordDto() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public DescriptionDto getDescriptionDto() {
        return descriptionDto;
    }

    public void setDescriptionDto(DescriptionDto descriptionDto) {
        this.descriptionDto = descriptionDto;
    }
}
