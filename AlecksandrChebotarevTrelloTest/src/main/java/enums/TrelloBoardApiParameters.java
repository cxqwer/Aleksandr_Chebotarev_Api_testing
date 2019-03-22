package enums;

public enum TrelloBoardApiParameters {
    KEEP_ALIVE ("keep-alive"),
    CLOSE("close");

    public String value;

    TrelloBoardApiParameters(String value) {
        this.value = value;
    }

}
