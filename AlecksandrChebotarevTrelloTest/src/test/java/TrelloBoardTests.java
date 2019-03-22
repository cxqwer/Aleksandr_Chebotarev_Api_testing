import api.TrelloBoardApi;
import beans.board.Board;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static api.TrelloBoardApi.*;
import static enums.BoardTestData.*;
import static enums.RequestParameters.DESCRIPTION;
import static enums.RequestParameters.PREFS_BACKGROUND;
import static enums.ResponseParameters.INCORRECT_ID;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static utils.BoardFromResponse.CreateBoardFromResponse;

public class TrelloBoardTests {
    @Test
    public void simpleBoardTests() {

        Response response;

        //1 Create board and check response status
        response = TrelloBoardApi.with()
                .addParam(DESCRIPTION, TEST_DESCRIPTION.value)
                .addParam(PREFS_BACKGROUND, TEST_CORRECT_BACKGROUND_COLOR.value)
                .createBoard(TEST_NAME.value);
        response.then().specification(successResponse());


        //2 GET created board and check correct: response status, name, background
        String boardId = CreateBoardFromResponse(response, Board.class).getId();
        response = TrelloBoardApi.with().getBoard(boardId);
        response.then().specification(successResponse());
        Board board = CreateBoardFromResponse(response, Board.class);
        assertThat(board.getName(), is(TEST_NAME.value));
        assertThat(board.getDesc(), is(TEST_DESCRIPTION.value));
        assertThat(board.getPrefs().getBackground(), is(TEST_CORRECT_BACKGROUND_COLOR.value));

        //3 Delete created board and check correct response status
        TrelloBoardApi.with()
                .deleteBoard(boardId)
                .then()
                .specification(successResponse());

        //4 Create board with incorrect background and check response status
        response = TrelloBoardApi.with()
                .addParam(DESCRIPTION, TEST_DESCRIPTION.value)
                .addParam(PREFS_BACKGROUND, TEST_INCORRECT_BACKGROUND_COLOR.value)
                .createBoard(TEST_NAME.value);
        response.then().specification(serverErrorRequest());

        //5 Check can't get deleted board
        TrelloBoardApi.with()
                .getBoard(boardId)
                .then()
                .specification(boardNotFound());

        //6 Check can't get board with wrong id
        TrelloBoardApi.with()
                .getBoard(TEST_INCORRECT_ID.value)
                .then()
                .specification(badRequest())
                .assertThat()
                .body(is(INCORRECT_ID.value));
    }
}