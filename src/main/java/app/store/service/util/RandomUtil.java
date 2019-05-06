package app.store.service.util;

import org.apache.commons.lang3.RandomStringUtils;


public final class RandomUtil {

    private static final int DEF_COUNT = 20;
    private static final int SMS_COUNT = 6;

    private RandomUtil() {
    }

    public static String generatePassword() {
        return RandomStringUtils.randomAlphanumeric(DEF_COUNT);
    }

    public static String generateActivationKey() {
        return RandomStringUtils.randomNumeric(SMS_COUNT);
    }

    public static String generateResetKey() {
        return RandomStringUtils.randomNumeric(SMS_COUNT);
    }
}
