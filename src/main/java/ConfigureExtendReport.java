import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.ViewName;

public class ConfigureExtendReport {
    public static void main(String[] args) {
        ExtentReports extentReports=new ExtentReports();
        ExtentSparkReporter sparkReporter_All=new ExtentSparkReporter("C:\\Users\\91956\\IdeaProjects\\PageObjectModule\\Extentreports\\all.html");
        ExtentSparkReporter sparkReporter_failed=new ExtentSparkReporter("C:\\Users\\91956\\IdeaProjects\\PageObjectModule\\Extentreports\\failed.html");
        ExtentSparkReporter sparkReporter_skip=new ExtentSparkReporter("C:\\Users\\91956\\IdeaProjects\\PageObjectModule\\Extentreports\\passed.html");
        sparkReporter_failed.filter().statusFilter().as(new Status[]{Status.FAIL}).apply();
        sparkReporter_skip.filter().statusFilter().as(new Status[]{Status.WARNING,Status.SKIP}).apply();
//        sparkReporter.viewConfigurer().viewOrder().as(new ViewName[]{
//                ViewName.TEST,
//                ViewName.AUTHOR,
//                ViewName.CATEGORY,
//                ViewName.DEVICE
//        }).apply();
        extentReports.attachReporter(sparkReporter_All,sparkReporter_failed,sparkReporter_skip);


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
                .warning("this is failed test");
        extentReports.flush();
    }
}
