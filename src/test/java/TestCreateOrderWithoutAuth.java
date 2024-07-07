import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import java.util.Random;

@DisplayName("Создание заказа")
public class TestCreateOrderWithoutAuth {
    @Before
    public void setUptest() {

        RestAssured.baseURI = Constants.BASE_URL;
    }

    @Test
    @DisplayName("Создание заказа")
    @Description("Создание заказа без авторизации")
    public void createOrderWithoutAuthTest () {
        new CreateOrder(Constants.INGREDIENT_LIST)
               .createOrderRequest()
                .checkStatusCode(200)
               .checkBodyMessage("success",true);
    }
}
