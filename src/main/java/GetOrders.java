import io.qameta.allure.Step;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class GetOrders {
    private Response response;
    public Response getResponse() {
        return response;
    }

    @Step("Отправка GET запроса для получения списка заказов")
    //запрос с авторизацией
    public Response getOrderRequest(String accessToken) {
        return given()
                .header("Authorization", accessToken)
                .get(Constants.GET_ORDER_API);
    }

    @Step("Отправка GET запроса для получения списка заказов")
    //запрос без авторизации (accessToken отсутствует)
    public Response getOrderRequest() {
        return given()
                .get(Constants.GET_ORDER_API);
    }

    @Step("Отправка GET запроса для получения списка заказов")
    //запрос с авторизацией
    public GetOrders getOrderRequest2(String accessToken) {
        GetOrders getOrders = new GetOrders();
        response = given()
                .header("Authorization", accessToken)
                .get(Constants.GET_ORDER_API);
        return this;
    }

    @Step("Отправка GET запроса для получения списка заказов")
    //запрос с авторизацией
    public GetOrders getOrderRequest2() {
        GetOrders getOrders = new GetOrders();
        response = given()
                .get(Constants.GET_ORDER_API);
        return this;
    }

    @Step("Проверка статус-кода")
    //запрос с авторизацией
    public GetOrders checkStatusCode(int statusCode) {
        new CheckResponse().checkStatusCode(response, statusCode);
        return this;
    }
    @Step("Проверка тела ответа")
    //запрос с авторизацией
    public GetOrders checkBodyMessage(String keyName, boolean expectedMessage) {
        new CheckResponse().checkBodyMessage(response, keyName, expectedMessage);
        return this;
    }
}
