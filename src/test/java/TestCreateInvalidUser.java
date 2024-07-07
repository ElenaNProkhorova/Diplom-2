import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import java.util.Random;

@DisplayName("Создание пользователя")
public class TestCreateInvalidUser {
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
    @Description("Попытка создания пользователя без заполнения одного из обязательных полей: email")
    public void createInvalidUserNoEmailTest() {

        email = null;
        new CreateUser()
                .createUser(email, password, name)
                .checkStatusCode(403)
                .checkBodyMessage("success", false);
    }

    @Test
    @DisplayName("Создание пользователя")
    @Description("Попытка создания пользователя без заполнения одного из обязательных полей: password")
    public void createInvalidUserNoPasswordTest() {
        password = null;
        new CreateUser()
                .createUser(email, password, name)
                .checkStatusCode(403)
                .checkBodyMessage("success", false);
    }

    @Test
    @DisplayName("Создание пользователя")
    @Description("Попытка создания пользователя без заполнения одного из обязательных полей: name")
    public void createInvalidUserNoNameTest() {
        name = null;
        new CreateUser()
                .createUser(email, password, name)
                .checkStatusCode(403)
                .checkBodyMessage("success", false);
    }
}
