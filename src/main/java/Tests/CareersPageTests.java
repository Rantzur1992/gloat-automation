package Tests;

import Pages.CareersPage;
import Utils.Utils;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import static io.restassured.RestAssured.get;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItem;

public class CareersPageTests extends BaseTest {

    private static CareersPage careersPage;
    private static final int CITY_COUNT = 4;
    private static final String[] CITIES = {"Tel Aviv", "London", "New York", "Melbourne"};
    private static final String PAGE_TITLE = "Careers - Gloat";

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
            logger.info("\nTime from UI: {}\n Current Time: {}", currentTime, timeFromUI);
            Assertions.assertEquals(currentTime, timeFromUI);
        } else {
            logger.error("ISRAEL was not present, did not check for time");
            Assertions.fail("\nExpected ISRAEL text to be present");
        }
    }

    @Test
    public void CheckAllVisibleCities() {
        int count = careersPage.countAllVisibleCities();
        logger.info("\nCountry Count: {}\n Expected: {}", count, CITY_COUNT);
        Assertions.assertEquals(CITY_COUNT, count);
    }


    @Test
    public void CheckTotalAmountOfPositions() {
        int totalCountUI = careersPage.getNumberOfOpenPositionsUI();
        int totalCount = 0;
        for(String city : CITIES) {
            careersPage.selectSpecificCity(city);
            totalCount += careersPage.countGoToJobIcon();
        }
        logger.info("\nExpect Count: {}\nActual Count: {}", totalCountUI, totalCount);
        Assertions.assertEquals(totalCount, totalCountUI);
    }

    @Test
    public void ClickObIsraelAndValidateLocationsCount() {
        careersPage.clickOnIsraelParagraph();
        int jobsAmount = careersPage.countGoToJobIcon();
        int telAvivLocations = careersPage.countTelAvivLocations();
        logger.info("\nTel Aviv Count should be {}\nActual Amount is: {}", jobsAmount, telAvivLocations);
        Assertions.assertEquals(jobsAmount, telAvivLocations);
    }

    // ------ Rest Assured Tests ----- //
    // This are mock tests for the backend API of the Careers page
    /*
        Title of web page in the UI should be equal to the same title from the response in pageData
    */
    @Test
    public void CheckTitleOfWebPage() {
        logger.info("\nPage title should be {}", PAGE_TITLE);
        get("/gloat/internal/api/pageData")
                .then()
                .assertThat()
                .body("page.title", equalTo(PAGE_TITLE))
                .and()
                .statusCode(200);
    }

    /*
        Front end developer in tel aviv response should include frontend in the job_desc as as well as salary should be equal to X
    */
    @Test
    public void CheckFrontEndDeveloperInIsraelResponse() {
        int salary = 0;
        logger.info("\nFront end developer job in israel should be detailed in the response as jobdesc");
        get("/gloat/internal/api/page?job=frontend&city=telaviv")
                .then()
                .assertThat()
                .body("$", hasItem("jobdesc"))
                .and()
                .assertThat()
                .body("frontend.salary", equalTo(salary))
                .and()
                .statusCode(200);
    }
}
