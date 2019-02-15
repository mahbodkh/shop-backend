package web.rest;

import app.store.Application;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class UserResourceTest {

    private static final String DEFAULT_LOGIN = "bijan";
    private static final String UPDATED_LOGIN = "mahbod";

    private static final String DEFAULT_ID = "id1";

    private static final String DEFAULT_PASSWORD = "passbijan";
    private static final String UPDATED_PASSWORD = "passmahbod";

    private static final String DEFAULT_EMAIL = "bijan@localhost";
    private static final String UPDATED_EMAIL = "mahbod@localhost";

    private static final String DEFAULT_FIRSTNAME = "Bijan";
    private static final String UPDATED_FIRSTNAME = "Mahbod";

    private static final String DEFAULT_LASTNAME = "kh";
    private static final String UPDATED_LASTNAME = "khosravani";

    private static final String DEFAULT_IMAGEURL = "http://placehold.it/50x50";
    private static final String UPDATED_IMAGEURL = "http://placehold.it/40x40";

    private static final String DEFAULT_LANGKEY = "en";
    private static final String UPDATED_LANGKEY = "fa";
}
