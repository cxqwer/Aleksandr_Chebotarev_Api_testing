package enums;

public enum BoardTestData {
    TEST_CORRECT_BACKGROUND_COLOR("blue"),
    TEST_INCORRECT_BACKGROUND_COLOR("golden"),
    TEST_NAME("new board"),
    TEST_DESCRIPTION("description text"),
    TEST_INCORRECT_ID("wrongIDBoard");

    public final String value;

    BoardTestData(String value) {
        this.value = value;
    }
}
