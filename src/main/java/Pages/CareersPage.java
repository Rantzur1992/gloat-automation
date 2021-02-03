package Pages;

import Utils.Utils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class CareersPage {

    private final WebDriver driver;

    public CareersPage(WebDriver driver) {
        this.driver = driver;
        driver.navigate().to("https://x.gloat.com/careers/all");
        PageFactory.initElements(driver, this);
    }


    @FindBy(xpath = "//h2[text() ='TEL AVIV']")
    private WebElement telAvivTitle;
    @FindBy(xpath = "//a[contains(@href, 'tlv')]//i[contains(text(), 'schedule')]/../p")
    private WebElement telAvivTitleTime;
    @FindBy(xpath = "(//p[contains(., 'positions')])[1]")
    private WebElement allOpenPositionsUI;
    @FindBy(xpath = "//p[text()='ISRAEL']")
    private WebElement israelText;
    @FindBy(xpath = "//div[@data-name='location-filter']")
    private WebElement allLocationsBtn;


    public int  countTelAvivLocations() {
        return driver.findElements(By.xpath("//p[contains(text(),'Tel-Aviv-Yafo, Israel') or contains(text(), 'Derech Menachem Begin')]")).size();
    }
    public void clickOnIsraelParagraph() {
        israelText.click();
    }

    public String getTimeFromUI () {
        return telAvivTitleTime.getText();
    }

    public String getCountryTitle() {
        return telAvivTitle.getText();
    }

    public String getOpenPositionsUI() {
        return allOpenPositionsUI.getText();
    }

    public int getNumberOfOpenPositionsUI() {
        return driver.findElements(By.xpath("//div[contains(@class, 'job-button')]")).size();
    }

    public int countAllVisibleCities() {
        return driver.findElements(By.xpath("//h2[contains(@class, 'box-title')]")).size();
    }

    public boolean isCountryTextPresent() {
        return israelText.isDisplayed();
    }

    public void clickOnAllLocations() {
        allLocationsBtn.click();
    }

    public void selectRandomCityFromLocationList() {
        List<WebElement> menuItems = driver.findElements(By.xpath("//section[contains(@class, 'box-menu-popover')]//div[contains(@class, 'menu-item')]"));
        int count = menuItems.size();
        menuItems.get(ThreadLocalRandom.current().nextInt(0, count + 1)).click();
    }


    public void selectSpecificCity(String city) {
        clickOnAllLocations();
        List<WebElement> menuItems = driver.findElements(By.xpath("//section[contains(@class, 'box-menu-popover')]//div[contains(@class, 'menu-item')]"));
        HashMap<String, WebElement> elementsMap = new HashMap<>();
        for(WebElement element: menuItems)
            elementsMap.put(element.getText(), element);
        elementsMap.get(city).click();
    }

    public int countGoToJobIcon() {
        Utils.sleep(4000); // The page loads the elements in sequence, this sleep waits for all this elements to load before Counting. (webDriver wait did not help here)
        List<WebElement> elements = driver.findElements(By.xpath("//i[text()='chevron_right']"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", elements.get(0));
        return elements.size() - 1;
    }



}
