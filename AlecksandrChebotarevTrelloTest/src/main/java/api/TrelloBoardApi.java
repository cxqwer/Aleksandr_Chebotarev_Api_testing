package api;

import enums.RequestParameters;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpStatus;

import java.util.HashMap;
import java.util.Map;

import static enums.BoardParameters.BOARD_PATH;
import static enums.RequestParameters.*;
import static enums.TrelloBoardApiParameters.CLOSE;
import static enums.TrelloBoardApiParameters.KEEP_ALIVE;
import static io.restassured.http.ContentType.JSON;
import static io.restassured.http.ContentType.TEXT;
import static org.hamcrest.Matchers.lessThan;
import static utils.TestPropertiesProvider.getPropertyByName;

public class TrelloBoardApi {

    private static final String ROOT_URL = getPropertyByName(PATH.value);
    private static final String PROPERTY_TOKEN = getPropertyByName(TOKEN.value);
    private static final String PROPERTY_KEY = getPropertyByName(KEY.value);

    private Map<String, String> params = new HashMap<>();

    public static class RequestBuilder {
        TrelloBoardApi resource;

        private RequestBuilder(TrelloBoardApi resource) {
            this.resource = resource;
        }

        public RequestBuilder addParam(RequestParameters param, String value) {
            this.resource.params.put(param.value, value);
            return this;
        }

        public Response createBoard(String name) {
            return RestAssured
                    .given(requestSpecification())
                    .with()
                    .queryParam(NAME.value, name)
                    .queryParams(this.resource.params)
                    .log().all()
                    .post(ROOT_URL + BOARD_PATH.value)
                    .prettyPeek();
        }

        public Response getBoard(String id) {
            return RestAssured
                    .given(requestSpecification())
                    .with()
                    .queryParams(this.resource.params)
                    .log().all()
                    .get(ROOT_URL + BOARD_PATH.value + id)
                    .prettyPeek();
        }

        public Response deleteBoard(String id) {
            return RestAssured
                    .given(requestSpecification())
                    .with()
                    .log().all()
                    .delete(ROOT_URL + BOARD_PATH.value + id)
                    .prettyPeek();
        }
    }

    public static RequestBuilder with() {
        TrelloBoardApi api = new TrelloBoardApi();
        return new RequestBuilder(api);
    }

    public static RequestSpecification requestSpecification() {
        return new RequestSpecBuilder()
                .setContentType(JSON)
                .setAccept(JSON)
                .addQueryParam(KEY.value, PROPERTY_KEY)
                .addQueryParam(TOKEN.value, PROPERTY_TOKEN)
                .build();
    }

    public static ResponseSpecification successResponse() {
        return new ResponseSpecBuilder()
                .expectContentType(JSON)
                .expectHeader(HttpHeaders.CONNECTION, KEEP_ALIVE.value)
                .expectResponseTime(lessThan(20000L))
                .expectStatusCode(HttpStatus.SC_OK)
                .build();
    }

    public static ResponseSpecification boardNotFound() {
        return new ResponseSpecBuilder()
                .expectContentType(TEXT)
                .expectHeader(HttpHeaders.CONNECTION, KEEP_ALIVE.value)
                .expectResponseTime(lessThan(20000L))
                .expectStatusCode(HttpStatus.SC_NOT_FOUND)
                .build();
    }

    public static ResponseSpecification badRequest() {
        return new ResponseSpecBuilder()
                .expectContentType(TEXT)
                .expectHeader(HttpHeaders.CONNECTION, KEEP_ALIVE.value)
                .expectResponseTime(lessThan(20000L))
                .expectStatusCode(HttpStatus.SC_BAD_REQUEST)
                .build();
    }

    public static ResponseSpecification serverErrorRequest() {
        return new ResponseSpecBuilder()
                .expectContentType(TEXT)
                .expectHeader(HttpHeaders.CONNECTION, CLOSE.value)
                .expectResponseTime(lessThan(20000L))
                .expectStatusCode(HttpStatus.SC_INTERNAL_SERVER_ERROR)
                .build();
    }
}