package DataProviders;

import org.testng.annotations.DataProvider;

public class DataProviders {
    @DataProvider
    public Object[][] simpleDataProvider() {
        return new Object[][]{
                {"String1", 1},
                {"String2", 2},
                {"String3", 3}
        };
    }

    @DataProvider(parallel = true)
    public Object[][] textsBelowPictures() {
        return new Object[][]{
                {"To include good practices", "To include good practices\nand ideas from successful\nEPAM project"},
                {"To be flexible and", "To be flexible and\ncustomizable"},
                {"To be multiplatform", "To be multiplatform"},
                {"Already have good base", "Already have good base\n(about 20 internal and\nsome external projects),\nwish to get more…"}
        };
    }
}
