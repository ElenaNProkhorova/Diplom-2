public class Constants {
    public static final String BASE_URL = "https://stellarburgers.nomoreparties.site";
    public static final String DEFAULT_EMAIL = "test@test.ru";
    public static final String DEFAULT_PASSWORD = "123456";
    public static final String DEFAULT_NAME = "Testing";
    public static final int STATUS_CODE_200 = 200;
    public static final int STATUS_CODE_202 = 202;
    public static final int STATUS_CODE_400 = 400;
    public static final int STATUS_CODE_401 = 401;
    public static final int STATUS_CODE_403 = 403;
    public static final int STATUS_CODE_500 = 500;
    public static final String KEY_NAME_SUCCESS = "success";



    // константы класса CreateUser
    public static final String CREATE_USER_ENDPOINT = "/api/auth/register";

    // константы класса LoginUser
    public static final String LOGIN_USER_ENDPOINT = "/api/auth/login";

    //константы класа UpdateUserInfo
    public static final String USER_INFO_ENDPOINT = "/api/auth/user";

    //константы класа CreateOrder
    public static final String CREATE_ORDER_ENDPOINT = "/api/orders";
    public static final String[] INGREDIENT_LIST = new IngredientsList().updateIngredientList();
    public static final String[] INVALID_INGREDIENT_LIST = new String[] {"1111000invalid0000001111","22220000invalid000002222"};

    //константы класа GetOrders
    public static final String GET_ORDER_ENDPOINT = "/api/orders";

    //константы класа GetIngredientList
    public static final String GET_INGREDIENT_LIST_ENDPOINT = "/api/ingredients";
}
