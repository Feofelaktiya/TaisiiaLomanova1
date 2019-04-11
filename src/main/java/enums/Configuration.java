package enums;

// Refactored
public enum Configuration {
    NAME("PITER CHAILOVSKII"),
    LOGIN("epam"),
    PASSWORD("1234");

    public String text;


    Configuration(String text) {
        this.text = text;

    }
}