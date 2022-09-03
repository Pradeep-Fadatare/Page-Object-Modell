import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.io.IOException;

public class ScreenshotAttach {

    public static WebDriver driver;

    public static void main(String[] args) throws IOException {

        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("https://www.google.co.in/");
        String base64code=CaptureScreenshot();
        String path=CaptureScreenshot("google.jpg");


        ExtentReports extentReports = new ExtentReports();
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter("C:\\Users\\91956\\IdeaProjects\\PageObjectModule\\Extentreports");
        extentReports.attachReporter(sparkReporter);

        extentReports.createTest("This is screenshot test 1","this is to attaching the screenshot to the test")
                .info("this is info msg")
                .addScreenCaptureFromBase64String("base64code");

        extentReports.createTest("This is screenshot test 2","this is to attaching the screenshot to the test")
                .info("this is info msg")
                .addScreenCaptureFromBase64String("base64code","This is base 64 msg");

        extentReports.createTest("This is screenshot test 3","this is to attaching the screenshot to the test")
                .info("this is info msg")
                .addScreenCaptureFromPath(path,"This is path msg");

        extentReports.createTest("This is screenshot test 4","this is to attaching the screenshot to the test")
                .info("this is info msg")
                .addScreenCaptureFromPath(path,"This is path msg");

        extentReports.createTest("This is screenshot test 5","this is to attaching the screenshot to the log")
                .info("this is info msg")
                .fail(MediaEntityBuilder.createScreenCaptureFromBase64String(base64code).build())
                .fail(MediaEntityBuilder.createScreenCaptureFromBase64String(base64code,"Second build statement").build());


        extentReports.createTest("This is screenshot test 6","this is to attaching the screenshot to the log")
                .info("this is info msg")
                .fail(MediaEntityBuilder.createScreenCaptureFromPath(path).build())
                .fail(MediaEntityBuilder.createScreenCaptureFromBase64String(path,"Second build statement").build());

        extentReports.createTest("This is screenshot test 7","this is to attaching the screenshot to the log")
                .info("this is info msg")
                .fail("first details",MediaEntityBuilder.createScreenCaptureFromPath(path).build());


        extentReports.createTest("This is screenshot test 8","this is to attaching the screenshot to the log")
                .info("this is info msg")
                .fail("First details",MediaEntityBuilder.createScreenCaptureFromBase64String(path,"google page").build());


        Throwable t=new Throwable("this is thrrowable");

        extentReports.createTest("This is screenshot test 9","this is to attaching the screenshot to the log")
                .info("this is info msg")
                .fail(t,MediaEntityBuilder.createScreenCaptureFromPath(base64code).build());

        extentReports.createTest("This is screenshot test 10","this is to attaching the screenshot to the log")
                .info("this is info msg")
                .fail(t,MediaEntityBuilder.createScreenCaptureFromBase64String(path,"google page").build());

        extentReports.flush();
        driver.quit();
    }

    public static String CaptureScreenshot(String Filename) throws IOException {

        File file=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        File src=new File("C:\\Users\\91956\\IdeaProjects\\PageObjectModule\\src\\test\\ScreenShots\\" + Filename);
        FileUtils.copyFile(file,src);
        System.out.println("ScreenshotCaptured");
        return src.getAbsolutePath();
    }

    public static String CaptureScreenshot() {
        TakesScreenshot takesScreenshot = ((TakesScreenshot) driver);
        String base64code = takesScreenshot.getScreenshotAs(OutputType.BASE64);
        System.out.println("Base64 captured successfully");
        return base64code;
    }
}
