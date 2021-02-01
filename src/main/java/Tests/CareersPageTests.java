package Tests;

import Pages.CareersPage;
import Utils.Utils;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;


public class CareersPageTests extends BaseTest {

    private static CareersPage careersPage;


    @BeforeClass
    public static void init() {
        careersPage = new CareersPage(driver);
    }

    @Test
    public void CheckIsraelClockTest() {
        String countryTitle = careersPage.getCountryTitle();
        if(careersPage.isCountryTextPresent()) {
            String currentTime = Utils.getCurrentTime();
            Assertions.assertEquals(currentTime, careersPage.getTimeFromUI());
        } else {
            Assertions.fail("Expected ISRAEL text to be present");
        }
    }

}
