import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


import java.util.Random;

@DisplayName("Создание пользователя")
public class TestCreateUser {
    String email = random4Numbers() + Constants.DEFAULT_EMAIL;
    String password = Constants.DEFAULT_PASSWORD;
    String name = Constants.DEFAULT_NAME;
    @Before
    public void setUp() {
        RestAssured.baseURI = Constants.BASE_URL;
    }
    public int random4Numbers () {
        return new Random().nextInt(8999)+1000;
    }

    @Test
    @DisplayName("Создание пользователя")
    @Description("Создание уникального пользователя")
    public void createUniqueUserTest () {
        new CreateUser()
                .createUser(email, password, name)
                .checkStatusCode(200)
                .checkBodyMessage("success", true);
    }

    @Test
    @DisplayName("Создание пользователя")
    @Description("Попытка создания пользователя, который уже зарегистрирован")
    public void createUserTwinTest () {
        new CreateUser()
                .createUser(email, password, name)
                .createUser(email, password, name)
                .checkStatusCode(403)
                .checkBodyMessage("success", false);
    }

    @After
    public void delete() {new DeleteUser().deleteUserRequest(email, password);}
}
