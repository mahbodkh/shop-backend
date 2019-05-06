package app.store.service.dto;

public class AuthorityDto {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "AuthorityDto{" +
                "name='" + name + '\'' +
                '}';
    }
}
