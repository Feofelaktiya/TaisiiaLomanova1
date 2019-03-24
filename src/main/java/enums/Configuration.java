package enums;

public class Configuration {

    // ==========USERS===========
    public enum userOne {
        USER_ONE("PITER CHAILOVSKII", "epam", "1234");

        public String name;
        public String login;
        public String password;


        userOne(String name, String login, String pass) {
            this.name = name;
            this.login = login;
            this.password = pass;

        }
    }


}
