package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CareersPage {

    private final WebDriver driver;

    public CareersPage(WebDriver driver) {
        this.driver = driver;
        driver.navigate().to("https://x.gloat.com/careers/all");
        PageFactory.initElements(driver, this);
    }


    @FindBy(xpath = "//h2[text() ='TEL AVIV']")
    private WebElement telAvivTitle;
    @FindBy(xpath = "//a[contains(@href, 'tlv')]//p[contains(text(), 'AM') or contains(text(), 'PM')]")
    private WebElement telAvivTitleTime;
    @FindBy(xpath = "(//p[contains(., 'positions')])[1]")
    private WebElement allOpenPositionsUI;
    @FindBy(xpath = "//p[text()='ISRAEL']")
    private WebElement israelText;



    public String getTimeFromUI () {
        return telAvivTitleTime.getText();
    }

    public String getCountryTitle() {
        return telAvivTitle.getText();
    }

    public String getOpenPositionsUI() {
        return allOpenPositionsUI.getText();
    }

    public int calculateOpenPositions() {
        return driver.findElements(By.xpath("//div[contains(@class, 'job-button')]")).size();
    }

    public boolean isCountryTextPresent() {
        return israelText.isDisplayed();
    }


}
