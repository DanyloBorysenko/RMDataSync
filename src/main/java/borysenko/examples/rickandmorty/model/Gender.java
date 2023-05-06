package borysenko.examples.rickandmorty.model;

public enum Gender {
    FEMALE("female"),
    MALE("male"),
    GENDERLESS("genderless"),
    UNKNOWN("unknown");
    private String value;

    Gender(String value) {
        this.value = value;
    }
}
