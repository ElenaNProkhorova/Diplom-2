import io.qameta.allure.Step;
import io.restassured.response.Response;

import static org.hamcrest.Matchers.equalTo;

public class CheckResponse {
    @Step("Проверка статус кода ответа")
    public CheckResponse checkStatusCode(Response response, int statusCode) {
        response.then()
                .assertThat()
                .statusCode(statusCode);
        return this;
    }

    @Step("Проверка тела сообщения по ключу и значению")
    public CheckResponse checkBodyMessage(Response response, String keyName, boolean expectedMessage) {
        response.then()
                .assertThat()
                .body(keyName, equalTo(expectedMessage));
        return this;

    }
}
