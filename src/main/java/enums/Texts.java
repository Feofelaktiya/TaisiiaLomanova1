package enums;

public class Texts {

    public enum pageTitles {
        PAGE_TITLES("Home Page", "Different Elements");
        public String homePage;
        public String differentElements;

        pageTitles(String titleOne, String titleTwo) {
            this.homePage = titleOne;
            this.differentElements = titleTwo;
        }
    }

    public enum sectionTitles {
        SECTION_TITLES("HOME", "CONTACT FORM", "SERVICE", "METALS & COLORS");
        public String home;
        public String contactForm;
        public String service;
        public String metalsAndColors;

        sectionTitles(String firstTab, String secondTab, String thirdTab, String forthTab) {
            this.home = firstTab;
            this.contactForm = secondTab;
            this.service = thirdTab;
            this.metalsAndColors = forthTab;
        }
    }

    public enum serviceOptions {
        SERVICE_OPTIONS("SUPPORT", "DATES", "COMPLEX TABLE", "SIMPLE TABLE", "USER TABLE", "TABLE WITH PAGES", "DIFFERENT ELEMENTS", "PERFORMANCE");
        public String support;
        public String dates;
        public String complexTable;
        public String simpleTable;
        public String userTable;
        public String tableWithWages;
        public String differentElements;
        public String performance;

        serviceOptions(String one, String two, String three, String four, String five, String six, String seven, String eight) {
            this.support = one;
            this.dates = two;
            this.complexTable = three;
            this.simpleTable = four;
            this.userTable = five;
            this.tableWithWages = six;
            this.differentElements = seven;
            this.performance = eight;
        }
    }

    public enum leftSectionServiceOptions {
        LEFT_SECTION_SERVICE_OPTIONS("Support", "Dates", "Complex Table", "Simple Table", "User Table", "Table with pages", "Different elements", "Performance");
        public String support;
        public String dates;
        public String complexTable;
        public String simpleTable;
        public String userTable;
        public String tableWithWages;
        public String differentElements;
        public String performance;

        leftSectionServiceOptions(String one, String two, String three, String four, String five, String six, String seven, String eight) {
            this.support = one;
            this.dates = two;
            this.complexTable = three;
            this.simpleTable = four;
            this.userTable = five;
            this.tableWithWages = six;
            this.differentElements = seven;
            this.performance = eight;
        }
    }

    public enum checkboxTexts {
        CHECKBOX_TEXTS("Water", "Earth", "Wind", "Fire");
        public String water;
        public String earth;
        public String wind;
        public String fire;

        checkboxTexts(String one, String two, String three, String four) {
            this.water = one;
            this.earth = two;
            this.wind = three;
            this.fire = four;
        }
    }

    public enum radiobuttonTexts {
        RADIOBUTTON_TEXTS("Gold", "Silver", "Bronze", "Selen");
        public String gold;
        public String silver;
        public String bronze;
        public String selen;

        radiobuttonTexts(String one, String two, String three, String four) {
            this.gold = one;
            this.silver = two;
            this.bronze = three;
            this.selen = four;
        }
    }

    public enum dropdownColors {
        DROPDOWN_COLORS("Red", "Green", "Blue", "Yellow");
        public String red;
        public String green;
        public String blue;
        public String yellow;

        dropdownColors(String one, String two, String three, String four) {
            this.red = one;
            this.green = two;
            this.blue = three;
            this.yellow = four;
        }
    }

    public enum rangeLogs {
        RANGE_LOGS("To", "From");
        public String to;
        public String from;

        rangeLogs(String one, String two) {
            this.to = one;
            this.from = two;
        }
    }

    public enum usersInTable {
        USERS_IN_TABLE("Roman", "Sergey Ivan", "Vladzimir", "Helen Bennett", "Yoshi Tannamuri", "Giovanni Rovelli");
        public String roman;
        public String ivan;
        public String vlad;
        public String helen;
        public String yoshi;
        public String giovanni;

        usersInTable(String one, String two, String three, String four, String five, String six) {
            this.roman = one;
            this.ivan = two;
            this.vlad = three;
            this.helen = four;
            this.yoshi = five;
            this.giovanni = six;
        }


    }
}