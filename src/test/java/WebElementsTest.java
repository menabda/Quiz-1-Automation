import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class WebElementsTest {

    WebDriver driver;
    @BeforeMethod
    public void open() {
        WebDriverManager.edgedriver().setup();
        driver = new EdgeDriver();
        driver.manage().window().maximize();
    }
    @Test
    public void Task1 () {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        driver.get("http://the-internet.herokuapp.com/dynamic_controls");
        String title = driver.getTitle();
        Assert.assertEquals(title,"The Internet");
        WebElement remove = driver.findElement(By.xpath("//*[@id='checkbox-example']/button"));
        Assert.assertTrue(remove.isEnabled());
        remove.click();
        wait.until(ExpectedConditions.textToBe(By.xpath("//*[@id='checkbox-example']/button"),"Add"));
        Assert.assertEquals(remove.getText(),"Add");
        WebElement message = driver.findElement(By.id("message"));
        Assert.assertTrue(message.isDisplayed());
    }

    @Test
    public void Task2() {
        driver.get("http://the-internet.herokuapp.com/add_remove_elements/");
        WebElement add = driver.findElement(By.xpath("//*[@onclick='addElement()']"));
        add.click();
        add.click();
        add.click();
        WebElement lastDelete = driver.findElement(By.cssSelector("button[class='added-manually']:last-child"));
        System.out.println(lastDelete.getText());
        List<WebElement> deletes= driver.findElements(By.cssSelector("button[class='added-manually']"));
        System.out.println(deletes.size());
        WebElement lastDeleted = driver.findElements(By.xpath("(//*[contains(@class,'added')])")).get(2);
        lastDeleted.click();
        List<WebElement> deleteElementsNumber = driver.findElements(By.xpath("(//*[contains(@class,'added')])"));
        Assert.assertEquals(deleteElementsNumber.size(),2);
    }
}
