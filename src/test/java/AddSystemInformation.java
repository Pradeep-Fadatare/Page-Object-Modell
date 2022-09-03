import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class AddSystemInformation {

    public static void main(String[] args) throws IOException {
        //india is my country
        //All brothers are my sisters
        //India is contry
        

        FileReader fr = new FileReader("C:\\Users\\91956\\IdeaProjects\\PageObjectModule\\src\\main\\java\\com\\newproject\\config\\config.properties");
        Properties prop = new Properties();
        prop.load(fr);

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        Capabilities capabilities = ((RemoteWebDriver) driver).getCapabilities();
        //System.getProperties().list(System.out);
//        System.out.println(System.getProperty("os.name"));
//        System.out.println("java" +System.getProperty("java.version"));

       ExtentReports extentReports = new ExtentReports();
       File file = new File("C:\\Users\\91956\\IdeaProjects\\PageObjectModule\\Extentreports\\newreport.html");
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter(file);
        extentReports.setSystemInfo("Browser", capabilities.getBrowserName() + capabilities.getBrowserVersion());
        extentReports.setSystemInfo("OS", System.getProperty("os.name"));
        extentReports.setSystemInfo("Appurl", prop.getProperty("testurl"));
        extentReports.attachReporter(sparkReporter);

        extentReports.createTest("test 1")
                .assignAuthor("pradeep")
                .assignCategory("smoke")
                .assignDevice("chrome 99")
                .pass("this is passed test");

        extentReports.flush();
        driver.quit();

    }
}
