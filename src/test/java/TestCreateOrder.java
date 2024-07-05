import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import java.util.Random;

@DisplayName("Создание заказа")
public class TestCreateOrder {
    @Before
    public void setUptest() {
        RestAssured.baseURI = Constants.BASE_URL;
    }
    public int random4Numbers () {
        return new Random().nextInt(8999)+1000;
    }


    @Test
    @DisplayName("Создание заказа")
    @Description("Создание заказа c авторизацией и наличием ингредиентов")
    public void createOrderTest () {

        String email = random4Numbers()+ Constants.DEFAULT_EMAIL;
        String password = Constants.DEFAULT_PASSWORD;
        String name = Constants.DEFAULT_NAME;

        //создание пользователя
        new CreateUser().createUser(email, password, name);

        //логин пользователя
        LoginUser newLogin = new LoginUser().loginUserRequest(email, password);

        //получение токена
        String accessToken = newLogin.getLoginUserAccessToken();

        //создание заказа, проверка статус кода и тела запроса
        new CreateOrder(Constants.INGREDIENT_LIST)
                .createOrderRequest(accessToken)
                .checkStatusCode(200)
                .checkBodyMessage("success",true);

        //удаление созданного пользователя
        new DeleteUser().deleteUserRequest(accessToken);
    }

    @Test
    @DisplayName("Создание заказа")
    @Description("Попытка создания заказа c авторизацией и без ингредиентов")
    public void createOrderWithoutIngredientsTest () {

        String email = random4Numbers()+ Constants.DEFAULT_EMAIL;
        String password = Constants.DEFAULT_PASSWORD;
        String name = Constants.DEFAULT_NAME;

        //создание пользователя
        new CreateUser().createUser(email, password, name);

        //логин пользователя
        LoginUser newLogin = new LoginUser().loginUserRequest(email, password);

        //получение токена
        String accessToken = newLogin.getLoginUserAccessToken();

        //создание заказа, проверка статус кода и тела запроса
        new CreateOrder()
                .createOrderRequest(accessToken)
                .checkStatusCode(400)
                .checkBodyMessage(Constants.KEY_NAME_SUCCESS,false);

        //удаление созданного пользователя
        new DeleteUser().deleteUserRequest(accessToken);
    }

    @Test
    @DisplayName("Создание заказа")
    @Description("Попытка создания заказа c авторизацией и с неверным хешем ингредиентов")
    public void createOrderWithInvalidIngredientListTest () {

        String email = random4Numbers()+ Constants.DEFAULT_EMAIL;
        String password = Constants.DEFAULT_PASSWORD;
        String name = Constants.DEFAULT_NAME;

        //создание пользователя
        new CreateUser().createUser(email, password, name);

        //логин пользователя
        LoginUser newLogin = new LoginUser().loginUserRequest(email, password);

        //получение токена
        String accessToken = newLogin.getLoginUserAccessToken();

        //создание заказа, проверка статус кода
        new CreateOrder(Constants.INVALID_INGREDIENT_LIST)
                .createOrderRequest(accessToken)
                .checkStatusCode(500);

        //удаление созданного пользователя
        new DeleteUser().deleteUserRequest(accessToken);
    }

    @Test
    @DisplayName("Создание заказа")
    @Description("Создание заказа без авторизации")
    public void createOrderWithoutAuthTest () {

        //создание заказа, проверка статус кода и тела запроса
        new CreateOrder(Constants.INGREDIENT_LIST)
                .createOrderRequest()
                .checkStatusCode(200)
                .checkBodyMessage(Constants.KEY_NAME_SUCCESS,true);
    }
}
