import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;


import java.util.Random;

@DisplayName("Создание пользователя")
public class TestCreateUser {
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
        String email = random4Numbers() + Constants.DEFAULT_EMAIL;
        String password = Constants.DEFAULT_PASSWORD;
        String name = Constants.DEFAULT_NAME;

        CreateUser newUser = new CreateUser()
                .createUser(email, password, name)
                .checkStatusCode(200)
                .checkBodyMessage("success", true);;

        //удаление созданного пользователя
        new DeleteUser().deleteUserRequest(email, password);
    }

    @Test
    @DisplayName("Создание пользователя")
    @Description("Попытка создания пользователя, который уже зарегистрирован")
    public void createUserTwinTest () {
        String email = random4Numbers() + Constants.DEFAULT_EMAIL;
        String password = Constants.DEFAULT_PASSWORD;
        String name = Constants.DEFAULT_NAME;

        CreateUser newUser = new CreateUser()
                .createUser(email, password, name)
                .createUser(email, password, name)
                .checkStatusCode(403)
                .checkBodyMessage("success", false);

        //удаление созданного пользователя
        new DeleteUser().deleteUserRequest(email, password);

    }

    @Test
    @DisplayName("Создание пользователя")
    @Description("Попытка создания пользователя без заполнения одного из обязательных полей")
    public void createInvalidUserTest() {

        // без email
        String email = null;
        String password = Constants.DEFAULT_PASSWORD;
        String name = Constants.DEFAULT_NAME;
        new CreateUser()
                .createUser(email, password, name)
                .checkStatusCode(403)
                .checkBodyMessage("success", false);


        // без password
        email = random4Numbers() + Constants.DEFAULT_EMAIL;
        password = null;
        new CreateUser()
                .createUser(email, password, name)
                .checkStatusCode(403)
                .checkBodyMessage("success", false);


        //без name
        email = random4Numbers()+ Constants.DEFAULT_EMAIL;
        password = Constants.DEFAULT_PASSWORD;
        name = null;
        new CreateUser()
                .createUser(email, password, name)
                .checkStatusCode(403)
                .checkBodyMessage("success", false);

    }
}
