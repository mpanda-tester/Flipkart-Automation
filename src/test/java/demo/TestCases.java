package demo;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;


// import io.github.bonigarcia.wdm.WebDriverManager;
import demo.wrappers.Wrappers;

public class TestCases {
    ChromeDriver driver;

    /*
     * TODO: Write your tests here with testng @Test annotation. 
     * Follow `testCase01` `testCase02`... format or what is provided in instructions
     */

     
    /*
     * Do not change the provided methods unless necessary, they will help in automation and assessment
     */
    @BeforeTest
    public void startBrowser()
    {
        System.setProperty("java.util.logging.config.file", "logging.properties");

        // NOT NEEDED FOR SELENIUM MANAGER
        // WebDriverManager.chromedriver().timeout(30).setup();

        ChromeOptions options = new ChromeOptions();
        LoggingPreferences logs = new LoggingPreferences();

        logs.enable(LogType.BROWSER, Level.ALL);
        logs.enable(LogType.DRIVER, Level.ALL);
        options.setCapability("goog:loggingPrefs", logs);
        options.addArguments("--remote-allow-origins=*");

        System.setProperty(ChromeDriverService.CHROME_DRIVER_LOG_PROPERTY, "build/chromedriver.log"); 

        driver = new ChromeDriver(options);

        driver.manage().window().maximize();
    }

    @AfterTest
    public void endTest()
    {
        driver.close();
        driver.quit();

    }
    @Test
    public void testCase01() throws InterruptedException{
        System.out.println("Start of testCase01");
        driver.get("https://www.flipkart.com/");
        String url=driver.getCurrentUrl();
        if(url.contains("flipkart")){
            System.out.println("Navigated successfully to flipkart website");
        }
        WebElement searchBoxArea=driver.findElement(By.xpath("//input[contains(@placeholder,'Search for Products, Brands and More')]"));
        searchBoxArea.sendKeys("Washing Machine");
        searchBoxArea.sendKeys(Keys.ENTER);

        WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.urlContains("Washing"));

        WebElement searchIcon = driver.findElement(By.xpath("//div[contains(text(),'Popularity')]"));
        searchIcon.click();

        //wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(),'Popularity')]")));
        Thread.sleep(5000);

        List<WebElement> totalItem= driver.findElements(By.xpath("//div[@class='XQDdHH']"));
        Thread.sleep(3000);
        int count=0;
        for(WebElement item:totalItem){
            String num = item.getText();
            if(!num.isEmpty()){
                float n=Float.parseFloat(num);
                if(n<=4){
                    count++;
                }
            }
           
        }
        System.out.println("Total number of washing machine is : "+count);
        System.out.println("End of testCase01");
    }
    @Test
    public void testCase02() throws InterruptedException{
        System.out.println("Start of testCase02");
        driver.get("https://www.flipkart.com/");
        String url=driver.getCurrentUrl();
        if(url.contains("flipkart")){
            System.out.println("Navigated successfully to flipkart website");
        }
        // WebElement crossBtn=driver.findElement(By.xpath("//div[@class='JFPqaw']/span"));
        // crossBtn.click();
         Thread.sleep(3000);
        WebElement searchBoxArea=driver.findElement(By.xpath("//input[contains(@placeholder,'Search for Products, Brands and More')]"));
        searchBoxArea.clear();
        Thread.sleep(2000);
        searchBoxArea.sendKeys("iPhone");
        Thread.sleep(2000);
        searchBoxArea.sendKeys(Keys.ENTER);

        WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.urlContains("iPhone"));

        WebElement searchIcon = driver.findElement(By.xpath("//div[contains(text(),'Popularity')]"));
        searchIcon.click();
        Thread.sleep(5000);

        List<WebElement> title=driver.findElements(By.xpath("//div[contains(@class,'KzDlHZ')]"));
        List<WebElement> discountElement=driver.findElements(By.xpath("//div[@class='UkUFwK']/span"));

        for(int i=0;i< discountElement.size();i++){
            WebElement discountval=discountElement.get(i);
            String discountVal=discountval.getText();

            String discountNumber = discountVal.replaceAll("[^0-9]", "");
            int discountPercentage = Integer.parseInt(discountNumber);

            if(discountPercentage>17){
                WebElement titlename=title.get(i);
                String titletext =titlename.getText();
                System.out.println("Title is : "+titletext);
                System.out.println("Discount is : "+discountPercentage);
            }
        }
        System.out.println("End of testCase02");

    }
    @Test
    public void testCase03() throws InterruptedException{
         System.out.println("Start of testCase03");
        driver.get("https://www.flipkart.com/");
        String url=driver.getCurrentUrl();
        if(url.contains("flipkart")){
            System.out.println("Navigated successfully to flipkart website");
        }
        // WebElement crossBtn=driver.findElement(By.xpath("//div[@class='JFPqaw']/span"));
        // crossBtn.click();
        Thread.sleep(3000);
        WebElement searchBoxArea=driver.findElement(By.xpath("//input[contains(@placeholder,'Search for Products, Brands and More')]"));
        searchBoxArea.clear();
        Thread.sleep(2000);
        searchBoxArea.sendKeys("Coffee Mug");
        Thread.sleep(2000);
        searchBoxArea.sendKeys(Keys.ENTER);
        List<WebElement> checkBoxElem = driver.findElements(By.xpath("//div[@class='_6i1qKy']"));
        Thread.sleep(3000);
        for(WebElement checkBox: checkBoxElem){
            String text=checkBox.getText();
            if(text.contains("4")){
                checkBox.click();
                break;
            }
        }
        Thread.sleep(4000);

        List<WebElement> productDetails = driver.findElements(By.xpath("//div[@class='slAVV4']"));//40
        List<Integer> ratings = new ArrayList<>();
        List<String> titles = new ArrayList<>();
        List<String> imageUrls = new ArrayList<>();

        for (WebElement product : productDetails) {
            try {
                WebElement ratingElement = product.findElement(By.xpath(".//span[@class='Wphh3N']"));//25
                WebElement titleElement = product.findElement(By.xpath(".//a[@class='wjcEIp']"));//40
                WebElement imageElement = product.findElement(By.xpath(".//img[@class='DByuf4']"));//40

                String ratingText = ratingElement.getText().split(" ")[0].replaceAll(",", "");
                int rating = Integer.parseInt(ratingText);
                String title = titleElement.getText();
                String imageUrl = imageElement.getAttribute("src");

                ratings.add(rating);
                titles.add(title);
                imageUrls.add(imageUrl);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        List<Integer> sortedRatings = new ArrayList<>(ratings);
        Collections.sort(sortedRatings, Collections.reverseOrder());

        for (int i = 0; i<sortedRatings.size(); i++) {
            int index = sortedRatings.get(i);
            System.out.println("Title: " + titles.get(index));
            System.out.println("Image URL: " + imageUrls.get(index));
        }

        System.out.println("End of testCase03");
    }
}