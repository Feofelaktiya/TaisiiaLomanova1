package enums;

public enum Users {
    PITER_CHAILOVSKII("epam", "1234", "PITER CHAILOVSKII");
    public String name;
    public String password;
    public String user;

    Users (String name, String password, String user) {
        this.name = name;
        this.password = password;
        this.user = user;
    }

}
