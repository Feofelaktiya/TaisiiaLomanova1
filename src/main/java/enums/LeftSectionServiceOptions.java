package enums;

public enum LeftSectionServiceOptions {
    SUPPORT("Support"),
    DATES("Dates"),
    SEARCH("Search"),
    COMPLEX_TABLE("Complex Table"),
    SIMPLE_TABLE("Simple Table"),
    USER_TABLE("User Table"),
    TABLE_WITH_PAGES("Table with pages"),
    DIIFFERENT_ELEMENTS("Different elements"),
    PERFORMANCE("Performance");

    public String option;

    LeftSectionServiceOptions(String text) {
        this.option = text;
    }
}