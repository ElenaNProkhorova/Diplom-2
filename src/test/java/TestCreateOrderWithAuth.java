import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Random;

@DisplayName("Создание заказа")
public class TestCreateOrderWithAuth {
    String email = random4Numbers()+ Constants.DEFAULT_EMAIL;
    String password = Constants.DEFAULT_PASSWORD;
    String name = Constants.DEFAULT_NAME;
    @Before
    public void setUptest() {

        RestAssured.baseURI = Constants.BASE_URL;
        new CreateUser().createUser(email, password, name);
    }
    public int random4Numbers () {
        return new Random().nextInt(8999)+1000;
    }


    @Test
    @DisplayName("Создание заказа")
    @Description("Создание заказа c авторизацией и наличием ингредиентов")
    public void createOrderTest () {
        LoginUser newLogin = new LoginUser().loginUserRequest(email, password);
        String accessToken = newLogin.getLoginUserAccessToken();
        new CreateOrder(Constants.INGREDIENT_LIST)
                .createOrderRequest(accessToken)
                .checkStatusCode(200)
                .checkBodyMessage("success",true);
    }

    @Test
    @DisplayName("Создание заказа")
    @Description("Попытка создания заказа c авторизацией и без ингредиентов")
    public void createOrderWithoutIngredientsTest () {
        LoginUser newLogin = new LoginUser().loginUserRequest(email, password);
        String accessToken = newLogin.getLoginUserAccessToken();
        new CreateOrder()
                .createOrderRequest(accessToken)
                .checkStatusCode(400)
                .checkBodyMessage("success",false);
    }

    @Test
    @DisplayName("Создание заказа")
    @Description("Попытка создания заказа c авторизацией и с неверным хешем ингредиентов")
    public void createOrderWithInvalidIngredientListTest () {
        LoginUser newLogin = new LoginUser().loginUserRequest(email, password);
        String accessToken = newLogin.getLoginUserAccessToken();
        new CreateOrder(Constants.INVALID_INGREDIENT_LIST)
                .createOrderRequest(accessToken)
                .checkStatusCode(500);
    }

    @After
    public void delete() {new DeleteUser().deleteUserRequest(email, password);}
}
