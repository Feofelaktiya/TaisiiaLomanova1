package enums;

public enum DropdownColors {
    RED("Red"),
    GREEN("Green"),
    BLUE("Blue"),
    YELLOW("Yellow");

    public String color;

    DropdownColors(String text) {
        this.color = text;
    }
}
