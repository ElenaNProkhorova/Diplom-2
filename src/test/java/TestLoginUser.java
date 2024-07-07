import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Random;

@DisplayName("Логин пользователя")
public class TestLoginUser {
    String email = random4Numbers()+ Constants.DEFAULT_EMAIL;
    String password = Constants.DEFAULT_PASSWORD;
    String name = Constants.DEFAULT_NAME;
    @Before
    public void setUp() {

        RestAssured.baseURI = Constants.BASE_URL;
        new CreateUser().createUser(email, password, name);
    }
    public int random4Numbers () {
        return new Random().nextInt(8999)+1000;
    }


    @Test
    @DisplayName("Логин пользователя")
    @Description("Логин под существующим пользователем")
    public void loginUserTest () {
        new LoginUser().loginUserRequest(email, password)
                .checkStatusCode(200)
                .checkBodyMessage("success", true);
    }

    @After
    public void delete() {new DeleteUser().deleteUserRequest(email, password);}
}
