package enums;

public enum BoardTestData {
    TEST_BACKGROUND_COLOR_BLUE("blue"),
    TEST_BACKGROUND_COLOR_GOLDEN("golden"),
    TEST_BOARD_NAME("new board"),
    TEST_DESCRIPTION_TEXT("description text"),
    TEST_INCORRECT_ID("wrongIDBoard");

    public final String value;

    BoardTestData(String value) {
        this.value = value;
    }
}
