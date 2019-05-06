package web.rest;

import app.store.Application;
import app.store.persistence.domain.User;
import app.store.persistence.repository.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.query.TextCriteria;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

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

    @Autowired
    private UserRepository userRepository;

    @Before
    public void before() {
        userRepository.deleteAll();
    }


    @Test
    public void createUserWithAddress() {
        User horst = new User();
        horst.setFirstName("Horst");
        horst.setLastName("Mustermann");
        horst.setLogin("horstm");
//        Address address = new Address();
//        address.setCity("Frankfurt");
//        address.setCountry("Germany");
//        address.setStreet("Zeil 3");
//        address.setZipCode("60384");
//
//        horst.setHomeAddress(address);

        User horstDb = userRepository.save(horst);
        assertNotNull(horstDb);
        assertNotNull(horstDb.getId());
//        assertEquals(horst.getFirstname(), horstDb.getFirstname());
    }


    @Test
    public void searchUser() {
        createUserWithAddress();
        TextCriteria search = TextCriteria.forDefaultLanguage().matching("horst");
//        List<User> r = userRepository.findAllBy(search);

//        assertNotNull(r);
//        assertFalse(r.isEmpty());
//        r.stream().forEach(u -> System.out.println(u.getUsername() + " " + u.getTextScore()));

    }
}
