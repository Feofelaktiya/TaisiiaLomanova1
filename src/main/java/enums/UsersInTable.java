package enums;

public enum UsersInTable {
    ROMAN("Roman"),
    SERGEY_IVAN("Sergey Ivan"),
    VLADZIMIR("Vladzimir"),
    HELEN_BENNETT("Helen Bennett"),
    YOSHI_TANNAMURI("Yoshi Tannamuri"),
    GIOVANNI_ROVELLI("Giovanni Rovelli");

    public String user;

    UsersInTable(String user) {
        this.user = user;
    }
}
