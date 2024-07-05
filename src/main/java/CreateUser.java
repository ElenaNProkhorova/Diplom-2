import io.qameta.allure.Step;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class CreateUser {
    private String email;
    private String password;
    private String name;

    private Response response;
    public Response getResponse() {
        return response;
    }


    public CreateUser(String email, String password, String name) {
        this.email = email;
        this.password = password;
        this.name = name;
    }

    public CreateUser() {
    }

    @Step("Отправка POST запроса для Создания пользователя")
    public CreateUser createUser(String email, String password, String name) {
        CreateUser newCreatedUser = new CreateUser(email, password, name);
        response = given()
                .header("Content-type", "application/json")
                .body(newCreatedUser)
                .post(Constants.CREATE_USER_ENDPOINT);
        return this;
    }

    @Step("Проверка статус-кода")
    //запрос с авторизацией
    public CreateUser checkStatusCode(int statusCode) {
        new CheckResponse().checkStatusCode(response, statusCode);
        return this;
    }
    @Step("Проверка тела ответа")
    //запрос с авторизацией
    public CreateUser checkBodyMessage(String keyName, boolean expectedMessage) {
        new CheckResponse().checkBodyMessage(response, keyName, expectedMessage);
        return this;
    }
}
