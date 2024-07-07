public class Constants {
    public static final String BASE_URL = "https://stellarburgers.nomoreparties.site";
    public static final String DEFAULT_EMAIL = "test@test.ru";
    public static final String DEFAULT_PASSWORD = "123456";
    public static final String DEFAULT_NAME = "Testing";

    public static final String CREATE_USER_API = "/api/auth/register";

    public static final String LOGIN_USER_API = "/api/auth/login";

    public static final String USER_INFO_API = "/api/auth/user";

    public static final String CREATE_ORDER_API = "/api/orders";
    public static final String[] INGREDIENT_LIST = new IngredientsList().updateIngredientList();
    public static final String[] INVALID_INGREDIENT_LIST = new String[] {"1111000invalid0000001111","22220000invalid000002222"};

    public static final String GET_ORDER_API = "/api/orders";

    public static final String GET_INGREDIENT_LIST_API = "/api/ingredients";
}
