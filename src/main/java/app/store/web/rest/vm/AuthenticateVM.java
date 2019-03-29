package app.store.web.rest.vm;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class AuthenticateVM {

    public static final int ACTIVATION_CODE_MIN_LENGTH = 4;

    public static final int ACTIVATION_CODE_MAX_LENGTH = 20;

    @NotNull
    private String login;
    @NotNull
    @Size(min = ACTIVATION_CODE_MIN_LENGTH, max = ACTIVATION_CODE_MAX_LENGTH)
    private String code;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "AuthenticateVM{" +
                "login='" + login + '\'' +
                ", code='" + code + '\'' +
                '}';
    }
}
