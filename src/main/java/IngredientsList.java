import io.qameta.allure.Step;
import io.restassured.RestAssured;

import java.util.Random;

public class IngredientsList {
    public int random4Numbers () {
        return new Random().nextInt(8999)+1000;
    }

    @Step("Обновление списка ингредиентов")
    public String[] updateIngredientList () {

        RestAssured.baseURI = Constants.BASE_URL;

        String email = random4Numbers() + Constants.DEFAULT_EMAIL;
        String password = Constants.DEFAULT_PASSWORD;
        String name = Constants.DEFAULT_NAME;

        //создание пользователя
        new CreateUser().createUser(email, password, name);

        //логин пользователя
        LoginUser newLogin = new LoginUser().loginUserRequest(email, password);

        //получение токена
        String accessToken = newLogin.getLoginUserAccessToken();

        //отправка запроса GET для получения списка ингредиентов
        GetIngredientsList getList =  new GetIngredientsList();
        GetIngredientsListDeserialization ingredientListDeserialization  =  getList.getIngredientListRequest(accessToken);

        //формирование списка из 2-х ингредиентов
        String[] freshIngredientList = {
                ingredientListDeserialization.getData().get(0).get_id(),
                ingredientListDeserialization.getData().get(1).get_id()
        };

        //удаление созданного пользователя
        new DeleteUser().deleteUserRequest(accessToken);

        return freshIngredientList;
    }
}
