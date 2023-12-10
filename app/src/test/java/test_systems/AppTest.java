package test_systems;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AppTest {

    private WebDriver driver;

    @BeforeEach
    void setUp() {
        driver = new ChromeDriver();
    }

    @Test
    void testVerifyHomePageTitle() {
        driver.get("https://demoqa.com");

        String pageTitle = driver.getTitle();
        assertEquals("ToolsQA", pageTitle);
    }

    @Test
    void testFillOutRegistrationForm() {
        driver.get("https://demoqa.com/automation-practice-form");

        driver.findElement(By.id("firstName")).sendKeys("John");
        driver.findElement(By.id("lastName")).sendKeys("Doe");
        driver.findElement(By.id("userEmail")).sendKeys("john.doe@example.com");
        driver.findElement(By.id("userNumber")).sendKeys("1234567890");
        driver.findElement(By.id("submit")).click();

        WebElement successMessage = driver.findElement(By.className("modal-content"));
        assertEquals("Thanks for submitting the form", successMessage.getText());
    }

    @Test
    void testInteractWithElements() {
        driver.get("https://demoqa.com/elements");

        WebElement button = driver.findElement(By.id("doubleClickBtn"));
        button.click();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        assertEquals("You have done a double click", driver.findElement(By.id("doubleClickMessage")).getText());
    }

    @Test
    void testSeleniumSubmit() {
        driver.get("https://demoqa.com/text-box");

        WebElement submitButton = driver.findElement(By.id("submit"));
        submitButton.submit();

    }

    @Test
    void testSeleniumSelectByIndex() {
        driver.get("https://demoqa.com/select-menu");

        WebElement dropdown = driver.findElement(By.id("oldSelectMenu"));
        Select select = new Select(dropdown);
        select.selectByIndex(2);

    }

    @Test
    void testInteractWithCheckBox() {
        driver.get("https://demoqa.com/checkbox");

        WebElement checkbox = driver.findElement(By.id("tree-node-home"));
        checkbox.click();

        assertTrue(checkbox.isSelected());
    }

    @Test
    void testVerifyPageUrl() {
        driver.get("https://demoqa.com/elements");

        assertEquals("https://demoqa.com/elements", driver.getCurrentUrl());
    }

    @Test
    void testInteractionWithRadioButtons() {
        driver.get("https://demoqa.com/radio-button");

        WebElement radioButton = driver.findElement(By.xpath("//label[text()='Yes']/preceding-sibling::input"));
        radioButton.click();

        assertTrue(radioButton.isSelected());
    }

    @Test
    void testNavigateToDifferentSections() {
        driver.get("https://demoqa.com");

        driver.findElement(By.xpath("//h5[text()='Interactions']")).click();

        assertEquals("https://demoqa.com/interactions", driver.getCurrentUrl());
    }

    @Test
    void testDropDownSelection() {
        driver.get("https://demoqa.com/select-menu");

        WebElement dropdown = driver.findElement(By.id("oldSelectMenu"));
        Select select = new Select(dropdown);
        select.selectByVisibleText("Purple");

        WebElement selectedOption = select.getFirstSelectedOption();
        assertEquals("Purple", selectedOption.getText());
    }

}
