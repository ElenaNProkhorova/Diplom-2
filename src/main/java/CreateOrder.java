import io.qameta.allure.Step;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class CreateOrder {
    private String[] ingredients;
    private Response response;
    public Response getResponse() {
        return response;
    }

    public CreateOrder() {
    }
    public CreateOrder(String[] ingredients) {
        this.ingredients = ingredients;
    }

    @Step("Отправка POST запроса для Создания заказ")
    public CreateOrder createOrderRequest(String accessToken) {
        CreateOrder newCreateOrder = new CreateOrder(ingredients);
        response = given()
                .header("Content-type", "application/json")
                .header("Authorization", accessToken)
                .body(newCreateOrder)
                .post(Constants.CREATE_ORDER_API);
        return this;
    }

    @Step("Отправка POST запроса для Создания заказ")
    //запрос без авторизации (accessToken отсутствует)
    public CreateOrder createOrderRequest() {
        CreateOrder newCreateOrder = new CreateOrder(ingredients);
        response = given()
                .header("Content-type", "application/json")
                .body(newCreateOrder)
                .post(Constants.CREATE_ORDER_API);
        return this;
    }
    @Step("Проверка статус-кода")
    //запрос с авторизацией
    public CreateOrder checkStatusCode(int statusCode) {
        new CheckResponse().checkStatusCode(response, statusCode);
        return this;
    }
    @Step("Проверка тела ответа")
    //запрос с авторизацией
    public CreateOrder checkBodyMessage(String keyName, boolean expectedMessage) {
        new CheckResponse().checkBodyMessage(response, keyName, expectedMessage);
        return this;
    }
}
