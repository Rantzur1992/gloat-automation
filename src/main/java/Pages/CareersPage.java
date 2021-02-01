package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

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
    @FindBy(xpath = "//p[text()='All locations']'")
    private WebElement allLocationsBtn;



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

    public int countAllVisibleCountries() {
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


}
