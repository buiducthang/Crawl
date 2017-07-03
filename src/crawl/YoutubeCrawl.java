/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crawl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
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
public class YoutubeCrawl {
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
        driver.get("https://www.youtube.com/");
    }
    
    public void search(String keyword)
    {
        driver.findElement(By.id("masthead-search-term")).sendKeys(keyword);
        driver.findElement(By.id("search-btn")).click();
    }
    
    public void CrawlComment()
    {
        List<WebElement> videos = driver.findElements(By.xpath("//a[@class='yt-uix-tile-link yt-ui-ellipsis yt-ui-ellipsis-2 yt-uix-sessionlink      spf-link ']"));
        ArrayList<String> links = new ArrayList<>();
        for(WebElement video : videos)
        {
            links.add(video.getAttribute("href"));
        }
        
        for(String link : links)
        {
            //String href = video.getAttribute("href");
            System.out.println("Href: " + link);
            driver.get(link);
            
            JavascriptExecutor jse = (JavascriptExecutor)driver;
            jse.executeScript("window.scrollBy(0,25000)", "");
            
            //Button show all comt-reply
            List<WebElement> btnCmtReplies = driver.findElements(By.xpath("//button[@class='yt-uix-button yt-uix-button-size-default yt-uix-button-default load-more-button yt-uix-load-more comment-replies-renderer-paginator comment-replies-renderer-expander-down yt-uix-button-link']"));
            if(btnCmtReplies.size() > 0)
            {
                for(WebElement btnCmtReply : btnCmtReplies)
                {
                    btnCmtReply.click();
                }
            }
            
            List<WebElement> cmtRenders = driver.findElements(By.className("comment-renderer-text"));
            for(WebElement cmtRender : cmtRenders)
            {
                //Crawl Comment
                //String cmtRender = cmtRender.getText();
                System.out.println("Cmt Render: " + cmtRender.getText());
            }
        }
    }
    
    public void Test()
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
        driver.get("https://www.youtube.com/watch?v=heySIypnWXw");
        
        
            JavascriptExecutor jse = (JavascriptExecutor)driver;
            jse.executeScript("window.scrollBy(0,250000)", "");
            
            
        List<WebElement> btnCmtReplies = driver.findElements(By.xpath("//button[@class='yt-uix-button yt-uix-button-size-default yt-uix-button-default load-more-button yt-uix-load-more comment-replies-renderer-paginator comment-replies-renderer-expander-down yt-uix-button-link']"));
        //JavascriptExecutor jse = (JavascriptExecutor)driver;
            //jse.executeScript("window.scrollBy(0,250000)", "");
        if(btnCmtReplies.size() > 0)
            {
                for(WebElement btnCmtReply : btnCmtReplies)
                {
                    btnCmtReply.click();
                    //JavascriptExecutor jse = (JavascriptExecutor)driver;
            //jse.executeScript("window.scrollBy(0,250000)", "");
                }
            }
            
            List<WebElement> cmtRenders = driver.findElements(By.className("comment-renderer-text"));
            for(WebElement cmtRender : cmtRenders)
            {
                //Crawl Comment
                //String cmtRender = cmtRender.getText();
                System.out.println("Cmt Render: " + cmtRender.getText());
            }
    }
    public static void main(String[] args) {
        YoutubeCrawl ytb = new YoutubeCrawl();
        ytb.invokeBrowser();
        ytb.search("tôn giáo");
        ytb.CrawlComment();
    }
    
    
}
