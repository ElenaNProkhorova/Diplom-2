import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import java.util.Random;

@DisplayName("Изменение данных пользователя")
public class TestUpdateUserInfo {
    @Before
    public void setUp() {
        RestAssured.baseURI = Constants.BASE_URL;
    }
    public int random4Numbers () {
        return new Random().nextInt(8999)+1000;
    }


    @Test
    @DisplayName("Изменение данных пользователя")
    @Description("Изменение данных пользователя с авторизацией")
    public void updateUserInfoTest () {

        //создание пользователя
        String email = random4Numbers()+ Constants.DEFAULT_EMAIL;
        String password = Constants.DEFAULT_PASSWORD;
        String name = Constants.DEFAULT_NAME;

        //создание пользователя
        new CreateUser().createUser(email, password, name);

        //логин пользователя
        LoginUser newLogin = new LoginUser().loginUserRequest(email, password);
        //получение токена
        String accessToken = newLogin.getLoginUserAccessToken();

        email = random4Numbers() + random4Numbers() +  Constants.DEFAULT_EMAIL;
        password = random4Numbers() + Constants.DEFAULT_PASSWORD;
        name = random4Numbers() + Constants.DEFAULT_NAME;

        //обновление информации о пользователе, проверка статус кода и тела запроса
        UpdateUserInfo newUpdate = new UpdateUserInfo()
                .updateUserRequest(email, password, name, accessToken)
                .checkStatusCode(200)
                .checkBodyMessage("success",true);

        //удаление созданного пользователя
        new DeleteUser().deleteUserRequest(accessToken);

    }

    @Test
    @DisplayName("Изменение данных пользователя")
    @Description("Изменение данных пользователя без авторизации")
    public void updateInvalidUserInfoTest () {

        //обновление информации о пользователе, проверка статус кода и тела запроса
        String email = Constants.DEFAULT_EMAIL;
        String password = Constants.DEFAULT_PASSWORD;
        String name = Constants.DEFAULT_NAME ;

        UpdateUserInfo newUpdate = new UpdateUserInfo().updateUserRequest(email, password, name)
                .checkStatusCode(401)
                .checkBodyMessage("success",false);
    }
}
