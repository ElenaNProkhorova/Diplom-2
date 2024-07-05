import io.qameta.allure.Step;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class LoginUser {
    private String email;
    private String password;
    private Response response;
    public Response getResponse() {
        return response;
    }


    public LoginUser(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public LoginUser() {
    }

    @Step("Отправка POST запроса для Авторизации пользователя")
    public LoginUser loginUserRequest(String email, String password) {
        LoginUser newLoginUser = new LoginUser(email, password);
        response = given()
                .header("Content-type", "application/json")
                .body(newLoginUser)
                .post(Constants.LOGIN_USER_ENDPOINT);
        return this;
    }

    @Step("Получение AccessToken залогиненного пользователя")
    public String getLoginUserAccessToken() {
        LoginUserDeserialization loggedUser = response.as(LoginUserDeserialization.class);
        return loggedUser.getAccessToken();
    }

    @Step("Проверка статус-кода")
    //запрос с авторизацией
    public LoginUser checkStatusCode(int statusCode) {
        new CheckResponse().checkStatusCode(response, statusCode);
        return this;
    }
    @Step("Проверка тела ответа")
    //запрос с авторизацией
    public LoginUser checkBodyMessage(String keyName, boolean expectedMessage) {
        new CheckResponse().checkBodyMessage(response, keyName, expectedMessage);
        return this;
    }
}
