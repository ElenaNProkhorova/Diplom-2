import io.qameta.allure.Step;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class UpdateUserInfo {
    private String email;
    private String password;
    private String name;
    private Response response;

    public UpdateUserInfo() {
    }

    public Response getResponse() {
        return response;
    }


    public UpdateUserInfo(String email, String password, String name) {
        this.email = email;
        this.password = password;
        this.name = name;
    }

    @Step("Отправка PATCH запроса для Обновления информации о пользователе")
    //запрос с авторизацией
    public UpdateUserInfo updateUserRequest(String email, String password, String name, String accessToken) {
        UpdateUserInfo newUpdateInfo = new UpdateUserInfo(email, password, name);
        response = given()
                .header("Content-type", "application/json")
                .header("Authorization", accessToken)
                .body(newUpdateInfo)
                .patch(Constants.USER_INFO_API);
        return this;
    }

    @Step("Отправка PATCH запроса для Обновления информации о пользователе")
    //запрос без авторизации (accessToken отсутствует)
    public UpdateUserInfo updateUserRequest(String email, String password, String name) {
        UpdateUserInfo newUpdateInfo = new UpdateUserInfo(email, password, name);
        response = given()
                .header("Content-type", "application/json")
                .body(newUpdateInfo)
                .patch(Constants.USER_INFO_API);
        return this;
    }

    @Step("Проверка статус-кода")
    //запрос с авторизацией
    public UpdateUserInfo checkStatusCode(int statusCode) {
        new CheckResponse().checkStatusCode(response, statusCode);
        return this;
    }
    @Step("Проверка тела ответа")
    //запрос с авторизацией
    public UpdateUserInfo checkBodyMessage(String keyName, boolean expectedMessage) {
        new CheckResponse().checkBodyMessage(response, keyName, expectedMessage);
        return this;
    }
}
