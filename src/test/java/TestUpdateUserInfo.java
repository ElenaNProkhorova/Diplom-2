import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Random;

@DisplayName("Изменение данных пользователя")
public class TestUpdateUserInfo {
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
    @DisplayName("Изменение данных пользователя")
    @Description("Изменение данных пользователя с авторизацией")
    public void updateUserInfoTest () {LoginUser newLogin = new LoginUser().loginUserRequest(email, password);
        //получение токена
        String accessToken = newLogin.getLoginUserAccessToken();

        email = random4Numbers() + random4Numbers() +  Constants.DEFAULT_EMAIL;
        password = random4Numbers() + Constants.DEFAULT_PASSWORD;
        name = random4Numbers() + Constants.DEFAULT_NAME;

        //обновление информации о пользователе, проверка статус кода и тела запроса
        new UpdateUserInfo()
                .updateUserRequest(email, password, name, accessToken)
                .checkStatusCode(200)
                .checkBodyMessage("success",true);
    }

    @Test
    @DisplayName("Изменение данных пользователя")
    @Description("Изменение данных пользователя без авторизации")
    public void updateInvalidUserInfoTest () {UpdateUserInfo newUpdate = new UpdateUserInfo().updateUserRequest(email, password, name)
                .checkStatusCode(401)
                .checkBodyMessage("success",false);
    }

    @After
    public void delete() {new DeleteUser().deleteUserRequest(email, password);}
}
