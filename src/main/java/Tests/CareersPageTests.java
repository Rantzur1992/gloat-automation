package Tests;

import Pages.CareersPage;
import Utils.Utils;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.util.ArrayList;


public class CareersPageTests extends BaseTest {

    private static CareersPage careersPage;
    private static final int COUNTRY_COUNT = 4;
    private static final String[] CITIES = {"Tel Aviv", "London", "New York", "Melbourne"};

    @BeforeClass
    public static void init() {
        careersPage = new CareersPage(driver);
    }

    @Test
    public void CheckIsraelClockTest() {
        String countryTitle = careersPage.getCountryTitle();
        if(careersPage.isCountryTextPresent()) {
            String currentTime = Utils.getCurrentTime();
            String timeFromUI = careersPage.getTimeFromUI();
            Assertions.assertEquals(currentTime, timeFromUI);
            logger.info("Time from UI: {}\n Current Time: {}", currentTime, timeFromUI);
        } else {
            Assertions.fail("Expected ISRAEL text to be present");
            logger.error("ISRAEL was not present, did not check for time");
        }
    }

    @Test
    public void CheckAllVisibleCountries() {
        int count = careersPage.countAllVisibleCountries();
        Assertions.assertEquals(COUNTRY_COUNT, count);
        logger.info("Country Count: {}\n Expected: {}", count, COUNTRY_COUNT);
    }


    @Test
    public void CheckTotalAmountOfPositions() {
        int totalCountUI = careersPage.getNumberOfOpenPositionsUI();
        for(String city : CITIES) {
            careersPage.selectSpecificCity(city);
        }

    }



}
