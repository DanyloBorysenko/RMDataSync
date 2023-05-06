package borysenko.examples.rickandmorty.model;

public enum Status {
    ALIVE("alive"),
    DEAD("dead"),
    UNKNOWN("unknown");
    private String value;

    Status(String value) {
        this.value = value;
    }
}
