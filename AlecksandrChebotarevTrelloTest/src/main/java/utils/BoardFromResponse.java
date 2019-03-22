package utils;

import com.google.gson.Gson;
import io.restassured.response.Response;

public class BoardFromResponse {
    public static <T> T CreateBoardFromResponse(Response response, Class<T> beanClass) {
        return new Gson().fromJson(response.asString().trim(), beanClass);
    }
}

