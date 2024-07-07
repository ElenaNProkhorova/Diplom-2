import io.qameta.allure.Step;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class DeleteUser {
    @Step("Отправка Delete запроса для удаления пользователя")
    public void deleteUserRequest(String email, String password) {
        String accessToken = new LoginUser()
                .loginUserRequest(email, password)
                .getLoginUserAccessToken();
        deleteUserRequest(accessToken);
    }

    @Step("Отправка Delete запроса для удаления пользователя")
    public void deleteUserRequest(String accessToken) {
        Response response =  given()
                .header("Authorization", accessToken)
                .delete(Constants.USER_INFO_API);
        response.then()
                .assertThat()
                .statusCode(202);
    }
}
