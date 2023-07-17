import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Main {
    public static void main(String[] args) {

        WebDriver driver = new ChromeDriver();
        driver.get("https://www.google.com");

        WebElement input = driver.findElement(By.xpath("//textarea[@aria-label='Найти']"));

        input.click();

    }
}
