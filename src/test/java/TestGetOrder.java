import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import java.util.Random;

@DisplayName("Получение заказов пользователя")
public class TestGetOrder {
    @Before
    public void setUp() {
        RestAssured.baseURI = Constants.BASE_URL;
    }

    public int random4Numbers () {
        return new Random().nextInt(8999)+1000;
    }

    @Test
    @DisplayName("Получение заказов конкретного пользователя")
    @Description("Получение заказов авторизованного пользователя")
    public void getOrderTest () {

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

        //создание заказа
        new CreateOrder(Constants.INGREDIENT_LIST)
                .createOrderRequest(accessToken);

        //получение списка заказов пользователя
        new GetOrders().getOrderRequest2(accessToken)
                .checkStatusCode(200)
                .checkBodyMessage("success",true);

        //удаление созданного пользователя
        new DeleteUser().deleteUserRequest(accessToken);

    }
    @Test
    @DisplayName("Получение заказов конкретного пользователя")
    @Description("Получение заказов для неавторизованного пользователя")
    public void getOrderWithoutAuthTest () {

        //создание заказа
        new CreateOrder(Constants.INGREDIENT_LIST)
                .createOrderRequest();

        //получение списка заказов пользователя
        new GetOrders().getOrderRequest2()
                .checkStatusCode(401)
                .checkBodyMessage("success",false);

    }
}
