package enums;

public enum RequestParameters {

    PATH("path"),
    KEY("key"),
    TOKEN("token"),
    NAME("name"),
    DESCRIPTION("desc"),
    PREFS_BACKGROUND("prefs_background");

    public final String value;

    RequestParameters(String value) {
        this.value = value;
    }

}
