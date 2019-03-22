package enums;

public enum ResponseParameters {
    INCORRECT_ID("invalid id");

    public final String value;

    ResponseParameters(String value) {
        this.value = value;
    }

}
