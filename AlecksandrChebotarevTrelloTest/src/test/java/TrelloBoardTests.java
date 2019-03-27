import api.TrelloBoardApi;
import beans.board.Board;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static api.TrelloBoardApi.*;
import static enums.BoardTestData.*;
import static enums.RequestParameters.DESCRIPTION;
import static enums.RequestParameters.PREFS_BACKGROUND;
import static enums.ResponseParameters.INVALID_ID;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static utils.BoardFromResponse.CreateBoardFromResponse;

public class TrelloBoardTests {

    private static Response response;
    private static String boardId;

    @Test(description = "Create board and check response status")
        public void testCrateBoard() {
        response = TrelloBoardApi.with()
                .addParam(DESCRIPTION, TEST_DESCRIPTION_TEXT.value)
                .addParam(PREFS_BACKGROUND, TEST_BACKGROUND_COLOR_BLUE.value)
                .createBoard(TEST_BOARD_NAME.value);
        response.then().specification(successResponse());
        boardId = CreateBoardFromResponse(response, Board.class).getId();
    }

    @Test(description = "Delete created board and check correct response status")
    public void testDeleteBoardByCorrectID() {
        TrelloBoardApi
                .with()
                .deleteBoard(boardId)
                .then()
                .specification(successResponse());
    }

    @Test(description = "GET created board and check correct: response status, name, background")
    public void testGetBoard() {

        response = TrelloBoardApi.with()
                .addParam(DESCRIPTION, TEST_DESCRIPTION_TEXT.value)
                .addParam(PREFS_BACKGROUND, TEST_BACKGROUND_COLOR_BLUE.value)
                .createBoard(TEST_BOARD_NAME.value);

        boardId = CreateBoardFromResponse(response, Board.class).getId();
        response = TrelloBoardApi.with().getBoard(boardId);
        response.then().specification(successResponse());
        Board board = CreateBoardFromResponse(response, Board.class);

        assertThat(board.getName(), is(TEST_BOARD_NAME.value));
        assertThat(board.getDesc(), is(TEST_DESCRIPTION_TEXT.value));
        assertThat(board.getPrefs().getBackground(), is(TEST_BACKGROUND_COLOR_BLUE.value));

        TrelloBoardApi
                .with()
                .deleteBoard(boardId)
                .then()
                .specification(successResponse());
    }

    @Test(description = "Create board with incorrect background and check response status")
    public void testDeleteBoardByIncorrectID() {
        response = TrelloBoardApi
                .with()
                .addParam(DESCRIPTION, TEST_DESCRIPTION_TEXT.value)
                .addParam(PREFS_BACKGROUND, TEST_BACKGROUND_COLOR_GOLDEN.value)
                .createBoard(TEST_BOARD_NAME.value);
        response.then().specification(serverErrorRequest());
    }

    @Test(description = "Check can't get deleted board")
    public void testGetDeletedBoard() {
        TrelloBoardApi.with()
                .getBoard(boardId)
                .then()
                .specification(boardNotFound());
    }

    @Test(description = "Check can't get board with wrong id")
    public void testGetBoardByIncorrectID() {
        TrelloBoardApi
                .with()
                .getBoard(TEST_INCORRECT_ID.value)
                .then()
                .specification(badRequest())
                .assertThat()
                .body(is(INVALID_ID.value));
    }
}
