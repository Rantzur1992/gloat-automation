package Tests;

import Pages.CareersPage;
import Utils.Utils;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;


public class CareersPageTests extends BaseTest {

    private static CareersPage careersPage;
    private static final int CITY_COUNT = 4;
    private static final String[] CITIES = {"Tel Aviv", "London", "New York", "Melbourne"};

    @BeforeClass
    public static void init() {
        careersPage = new CareersPage(driver);
    }

    // ----- JUNIT Tests ----- //
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
    public void CheckAllVisibleCities() {
        int count = careersPage.countAllVisibleCities();
        Assertions.assertEquals(CITY_COUNT, count);
        logger.info("Country Count: {}\n Expected: {}", count, CITY_COUNT);
    }


    @Test
    public void CheckTotalAmountOfPositions() {
        int totalCountUI = careersPage.getNumberOfOpenPositionsUI();
        int totalCount = 0;
        for(String city : CITIES) {
            careersPage.selectSpecificCity(city);
            totalCount += careersPage.countGoToJobIcon();
        }
        Assertions.assertEquals(totalCount, totalCountUI);
        logger.info("Expect Count: {}\nActual Count: {}", totalCountUI, totalCount);
    }

    @Test
    public void ClickObIsraelAndValidateLocationsCount() {
        careersPage.clickOnIsraelParagraph();
        int jobsAmount = careersPage.countGoToJobIcon();
        int telAvivLocations = careersPage.countTelAvivLocations();
        Assertions.assertEquals(jobsAmount, telAvivLocations);
        logger.info("Tel Aviv Count should be {}\nActual Amount is: {}", jobsAmount, telAvivLocations);
    }

    // ------ Rest Assured Tests ----- //
    public void CheckTitleOfWebPage() {

    }
}
