import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import java.io.File;

public class ExtendReportInformation {

    public static void main(String[] args) {
        ExtentReports extentReports=new ExtentReports();
        File file=new File("C:\\Users\\91956\\IdeaProjects\\PageObjectModule\\Extentreports\\report.html");
        ExtentSparkReporter sparkReporter=new ExtentSparkReporter(file);
        sparkReporter.config().setTheme(Theme.DARK);
        sparkReporter.config().setReportName("Report Name");
        sparkReporter.config().setDocumentTitle("Document title");
        sparkReporter.config().setTimeStampFormat("dd-MM-yyyy hh:mm:ss");
        sparkReporter.config().setCss(".badge-primary {background-color: #a94671 }");
        extentReports.attachReporter(sparkReporter);

        extentReports.createTest("test 1")
                .assignAuthor("pradeep")
                .assignCategory("smoke")
                .assignDevice("chrome 99")
                .pass("this is passed test");

        extentReports.createTest("test 2","This is the new msg")
                .assignAuthor("pravin")
                .assignCategory("sanity")
                .assignDevice("firefox")
                .fail("this is failed test");

        extentReports.createTest("test 2","reverse method")
                .assignCategory("sanity")
                .assignDevice("firefox")
                .assignAuthor("prakash")
                .fail("this is failed test");
        extentReports.flush();
     }
}
