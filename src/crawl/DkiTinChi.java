/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crawl;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;

/**
 *
 * @author ducth_000
 */
public class DkiTinChi {
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
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
        driver.get("http://qldt.ptit.edu.vn");
    }
    
    public void GetCapcha()
    {
        String capcha = driver.findElement(By.id("ctl00_ContentPlaceHolder1_ctl00_lblCapcha")).getText();
        System.out.println(capcha);
        driver.findElement(By.id("ctl00_ContentPlaceHolder1_ctl00_txtCaptcha")).sendKeys(capcha);
        driver.findElement(By.id("ctl00_ContentPlaceHolder1_ctl00_btnXacNhan")).click();
    }
    public void SignIn(String username,String pass)
    {
        //driver.findElement(By.id("ctl00_ContentPlaceHolder1_ctl00_btnXacNhan")).click();
        driver.findElement(By.id("ctl00_ContentPlaceHolder1_ctl00_ucDangNhap_txtTaiKhoa")).sendKeys(username);
        driver.findElement(By.id("ctl00_ContentPlaceHolder1_ctl00_ucDangNhap_txtMatKhau")).sendKeys(pass);
        driver.findElement(By.id("ctl00_ContentPlaceHolder1_ctl00_ucDangNhap_btnDangNhap")).click();
    }
    
    public void CheckFriendRequest()
    {
        //WebElement element = (WebElement) ((JavascriptExecutor)driver).executeScript("return $('._8o _8s lfloat _ohe')[0].attr('href');");
        //System.out.println("Element: "+ element.toString());
        driver.findElement(By.id("fbRequestsJewel")).click();
        System.out.println("ok");
        driver.findElement(By.className("_s0 _rw img")).click();
        System.out.println("ok");
    }
    
    public void SelectObject()
    {
        driver.findElement(By.id("ctl00_menu_lblDangKyMonHoc")).click();
        Select dropdown = new Select(driver.findElement(By.id("selectMonHoc")));
        //An toan ung dung web + csdl
        dropdown.selectByIndex(0);
        driver.findElement(By.id("chk_INT14105    02  02")).click();
        //Quan ly an toan thong tin
        dropdown.deselectByIndex(0);
        dropdown.selectByIndex(1);
        driver.findElement(By.id("chk_INT14106    03  02")).click();
        //Lap trinh mang
        dropdown.deselectByIndex(1);
        dropdown.selectByIndex(2);
        driver.findElement(By.id("chk_INT1433     10  02")).click();
        //An toan mang
        dropdown.deselectByIndex(2);//Ngay mai khi he thong fix thi bo comment dong nay
        dropdown.selectByIndex(3);//Ngay mai khi he thong fix thi bo comment dong nay
        driver.findElement(By.id("chk_INT1482     01  02")).click();//Ngay mai khi he thong fix thi bo comment dong nay
        //Mat ma hoc nang cao
        dropdown.deselectByIndex(3);//Ngay mai khi he thong fix thi bo comment dong nay
        //dropdown.deselectByIndex(2);//Ngay mai khi he thong fix thi xoa dong nay di
        dropdown.selectByIndex(4);
        driver.findElement(By.id("chk_INT1491     02  02")).click();
        //Phuong phap luan nghien cuu khoa hoc
        dropdown.deselectByIndex(4);
        dropdown.selectByIndex(5);
        driver.findElement(By.id("chk_SKD1108     07")).click();
        //Luu
        driver.findElement(By.id("IDchk_all")).click();
        driver.findElement(By.id("btnLuu")).click();
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        DkiTinChi crawl = new DkiTinChi();
        crawl.invokeBrowser();
        crawl.GetCapcha();
        crawl.SignIn("B14DCAT271", "18011996aa");//Thay doi ten tai khoan va mat khau
        crawl.SelectObject();
    }
    
}
