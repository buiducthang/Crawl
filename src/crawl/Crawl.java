/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crawl;

import static crawl.ExtractIntegerFromString.ExtractIntegerFromString;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import org.apache.commons.lang3.StringUtils;
import org.eclipse.jetty.util.StringUtil;
import static org.openqa.grid.common.SeleniumProtocol.WebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

/**
 *
 * @author ducth_000
 */
public class Crawl {
    WebDriver driver;
    
    public void invokeBrowser()
    {
        ChromeOptions options = new ChromeOptions();
        //Turn-off notification
        options.addArguments("--disable-notifications");
        //turn-off save password dialog
        Map<String, Object> prefs = new HashMap<String, Object>();
        prefs.put("credentials_enable_service", false);
        prefs.put("profile.password_manager_enabled", false);

        options.setExperimentalOption("prefs", prefs);
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\ducth_000\\Desktop\\Crawl\\chromedriver.exe");
        driver = new ChromeDriver(options);
        //driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
        driver.get("https://www.facebook.com");
    }
    
    public void SignIn(String username,String pass)
    {
        driver.findElement(By.id("email")).sendKeys(username);
        driver.findElement(By.id("pass")).sendKeys(pass);
        driver.findElement(By.id("loginbutton")).click();
    }
    
    
    public void MutalFriends()
    {
        //Get link of mutal friends
        List<WebElement> mutalFriends = driver.findElements(By.className("_39g5"));
        
        //Get name of mutal friends
        
        for(WebElement mutalFriend : mutalFriends)
        {
            String href = mutalFriend.getAttribute("href");
            
            if(href.contains("friends_mutual"))
            {
                driver.get(href);
                List<WebElement> nameOfFriends = driver.findElements(By.xpath("//img[@class='_s0 _rv img']"));
                
                JavascriptExecutor jse = (JavascriptExecutor)driver;
                jse.executeScript("window.scrollBy(0,25000)", "");
                
                System.out.println("So ban chung: " + nameOfFriends.size());
                for(WebElement nameOfFriend : nameOfFriends)
                {
                    System.out.println("Ten: " + nameOfFriend.getAttribute("aria-label"));
                }
            }
        }
        
    }
    
    public void CheckProfile()
    {
        System.out.println("Profile:");
        
        String profile = null;
        //if(driver.findElements(By.xpath("//div[@class='_3c-4 _2a57 _3-8w']")).size() == 0)
        //    return;
        
        if(driver.findElements(By.xpath("//div[@class='_3c-4 _2a57 _3-8w']")).size() != 0){
            profile = driver.findElement(By.xpath("//div[@class='_3c-4 _2a57 _3-8w']")).getText();
            System.out.println(profile);
            return;
        }
        
        //if(driver.findElements(By.xpath("//div[@class='_3c-4 _2a57 _3-8w _46ye']")).size() == 0)
        //    return;
        
        if(driver.findElements(By.xpath("//div[@class='_3c-4 _2a57 _3-8w _46ye']")).size() != 0)
        {
            profile = driver.findElement(By.xpath("//div[@class='_3c-4 _2a57 _3-8w _46ye']")).getText();
        
            System.out.println(profile);
            return;
        }
        return;
    }
    
    public void CheckFriendRequest()
    {
        //WebElement element = (WebElement) ((JavascriptExecutor)driver).executeScript("return $('._8o _8s lfloat _ohe')[0].attr('href');");
        //System.out.println("Element: "+ element.toString());
        //driver.findElement(By.id("fbRequestsJewel")).click();
        //Click friend request2
        //driver.findElement(By.xpath("//img[@class='_s0 _rw img']")).click();
        //String id = driver.findElement(By.xpath("//a[@class='coverWrap coverImage']")).getAttribute("data-referrerid");
        //System.out.println("id: "+id);
        driver.get("https://www.facebook.com/friends/requests/?fcref=jwl");
        int countRequest = 0;
        ArrayList<String> links = new ArrayList<String>();
        List<WebElement> listRequests = driver.findElements(By.xpath("//div[@class='clearfix ruUserBox _3-z friendRequestItem']"));
        for(WebElement request : listRequests)
        {
            countRequest++;
        }
        
        int countLink = 0;
        List<WebElement> list = driver.findElements(By.xpath("//a[@class='_8o _8t lfloat _ohe']"));
        for(WebElement item : list)
        {
            countLink++;
            links.add(item.getAttribute("href"));
            if(countLink == countRequest) break;
        }

        for(String link : links)
        {
            driver.get(link);
            JavascriptExecutor jse = (JavascriptExecutor)driver;
            jse.executeScript("window.scrollBy(0,2500)", "");
            List<WebElement> likes = driver.findElements(By.xpath("//span[@class='_4arz']"));
            String name = driver.findElement(By.id("fb-timeline-cover-name")).getText();
            System.out.println("Name: " + name);
            for(WebElement like : likes)
            {
                System.out.println(ExtractIntegerFromString(like.getText()));
            }
            CheckProfile();
            MutalFriends();
            
        }
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Crawl crawl = new Crawl();
        crawl.invokeBrowser();
        crawl.SignIn("0969664623", "DucThang@12345");
        crawl.CheckFriendRequest();
    }
    
}
