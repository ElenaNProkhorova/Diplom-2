import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Random;

@DisplayName("Получение заказов пользователя")
public class TestGetOrder {

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
    @DisplayName("Получение заказов конкретного пользователя")
    @Description("Получение заказов авторизованного пользователя")
    public void getOrderTest () {
        LoginUser newLogin = new LoginUser().loginUserRequest(email, password);
        String accessToken = newLogin.getLoginUserAccessToken();
        new CreateOrder(Constants.INGREDIENT_LIST)
                .createOrderRequest(accessToken);
        new GetOrders().getOrderRequest2(accessToken)
                .checkStatusCode(200)
                .checkBodyMessage("success",true);
    }
    @Test
    @DisplayName("Получение заказов конкретного пользователя")
    @Description("Получение заказов для неавторизованного пользователя")
    public void getOrderWithoutAuthTest () {
        new CreateOrder(Constants.INGREDIENT_LIST)
                .createOrderRequest();
        new GetOrders().getOrderRequest2()
                .checkStatusCode(401)
                .checkBodyMessage("success",false);
    }

    @After
    public void delete() {new DeleteUser().deleteUserRequest(email, password);}
}
