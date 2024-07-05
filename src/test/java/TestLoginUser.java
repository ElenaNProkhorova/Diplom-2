import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import java.util.Random;

@DisplayName("Логин пользователя")
public class TestLoginUser {
    @Before
    public void setUp() {
        RestAssured.baseURI = Constants.BASE_URL;
    }
    public int random4Numbers () {
        return new Random().nextInt(8999)+1000;
    }


    @Test
    @DisplayName("Логин пользователя")
    @Description("Логин под существующим пользователем")
    public void loginUserTest () {

        String email = random4Numbers()+ Constants.DEFAULT_EMAIL;
        String password = Constants.DEFAULT_PASSWORD;
        String name = Constants.DEFAULT_NAME;

        //создание пользователя
        CreateUser newUser = new CreateUser().createUser(email, password, name);

        //логин пользователя, проверка кода и тела ответа
        LoginUser newLogin = new LoginUser().loginUserRequest(email, password)
                .checkStatusCode(200)
                .checkBodyMessage("success", true);

        //удаление созданного пользователя
        new DeleteUser().deleteUserRequest(newLogin.getLoginUserAccessToken());
    }

    @Test
    @DisplayName("Логин пользователя")
    @Description("Попытка логина с неверным логином и паролем")
    public void loginInvalidUserTest () {

        String email = random4Numbers()+ Constants.DEFAULT_EMAIL;
        String password = Constants.DEFAULT_PASSWORD;

        //логин пользователя, проверка кода и тела ответа
        LoginUser newLogin = new LoginUser().loginUserRequest(email, password)
                .checkStatusCode(401)
                .checkBodyMessage("success", false);

    }
}
