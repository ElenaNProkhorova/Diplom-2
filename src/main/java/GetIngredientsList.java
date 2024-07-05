import io.qameta.allure.Step;

import static io.restassured.RestAssured.given;

public class GetIngredientsList {
    @Step("Отправка GET запроса для получения списка ингредиентов")
    public GetIngredientsListDeserialization getIngredientListRequest(String accessToken) {
        return given()
                .header("Authorization", accessToken)
                .get(Constants.GET_INGREDIENT_LIST_ENDPOINT)
                .body().as(GetIngredientsListDeserialization.class);
    }
}
