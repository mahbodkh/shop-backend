package app.store.web.rest.error;

import java.net.URI;

public final class ErrorConstants {

    public static final String ERR_CONCURRENCY_FAILURE = "error.concurrencyFailure";
    public static final String ERR_VALIDATION = "error.validation";
    public static final String PROBLEM_BASE_URL = "https://www.jhipster.tech/problem";

    public static final URI DEFAULT_TYPE = URI.create(PROBLEM_BASE_URL + "/problem-with-message");
    public static final URI CONSTRAINT_VIOLATION_TYPE = URI.create(PROBLEM_BASE_URL + "/constraint-violation");
    public static final URI PARAMETERIZED_TYPE = URI.create(PROBLEM_BASE_URL + "/parameterized");
    public static final URI ENTITY_NOT_FOUND_TYPE = URI.create(PROBLEM_BASE_URL + "/entity-not-found");
    public static final URI INVALID_PASSWORD_TYPE = URI.create(PROBLEM_BASE_URL + "/invalid-password");

    public static final URI LOGIN_ALREADY_USED_TYPE = URI.create(PROBLEM_BASE_URL + "/login-already-used");

    public static final URI MOBILE_NOT_FOUND_TYPE = URI.create(PROBLEM_BASE_URL + "/mobile-not-found");
    public static final URI MOBILE_ALREADY_USED_TYPE = URI.create(PROBLEM_BASE_URL + "/mobile-already-used");

    public static final URI EMAIL_NOT_FOUND_TYPE = URI.create(PROBLEM_BASE_URL + "/email-not-found");
    public static final URI EMAIL_ALREADY_USED_TYPE = URI.create(PROBLEM_BASE_URL + "/email-already-used");

    public static final URI CART_NOT_FOUND_TYPE = URI.create(PROBLEM_BASE_URL + "/cart-not-found");
    public static final URI CARD_ALREADY_USED_TYPE = URI.create(PROBLEM_BASE_URL + "/cart-already-used");

    public static final URI USER_NOT_FOUND_TYPE = URI.create(PROBLEM_BASE_URL + "/user-not-found");
    public static final URI USER_ALREADY_USED_TYPE = URI.create(PROBLEM_BASE_URL + "/user-already-used");

    public static final URI PRODUCT_NOT_FOUND_TYPE = URI.create(PROBLEM_BASE_URL + "/product-not-found");
    public static final URI PRODUCT_ALREADY_USED_TYPE = URI.create(PROBLEM_BASE_URL + "/product-already-used");
    public static final URI PRODUCT_INVALID_DATA = URI.create(PROBLEM_BASE_URL + "/product-invalid");

    public static final URI CATEGORY_NOT_FOUND_TYPE = URI.create(PROBLEM_BASE_URL + "category-not-found");
    public static final URI CATEGORY_ALREADY_USED_TYPE = URI.create(PROBLEM_BASE_URL + "category-already-used");
    public static final URI CATEGORY_NOT_DELETED_TYPE = URI.create(PROBLEM_BASE_URL + "category-already-category");


    private ErrorConstants() {
    }
}
