package enums.cucumber;

public enum ElementsCucumber {

    ELEMENTS("Water", "Wind");

    public String water;
    public String wind;

    ElementsCucumber(String water, String wind){
        this.water = water;
        this.wind = wind;
    }
}
