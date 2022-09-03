import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import java.util.*;

public class report {

    public static void main(String[] args) {
        ExtentReports extentReports = new ExtentReports();
        ExtentSparkReporter spark = new ExtentSparkReporter("C:\\Users\\91956\\IdeaProjects\\PageObjectModule\\Extentreports");
        extentReports.attachReporter(spark);
//        ExtentTest test1 = extentReports.createTest("Test1");
//        test1.pass("this is passed test");
//
//        ExtentTest test2 = extentReports.createTest("Test2");
//        test2.log(Status.FAIL, "This is failed test");

//        extentReports.createTest("Test2").log(Status.FAIL,"this is failed test");
//        extentReports.createTest("Test2").log(Status.INFO,"Info1");
//        extentReports.createTest("Test2").log(Status.SKIP,"this is skipped test");
//        extentReports.createTest("Test2").log(Status.PASS,"This is passed test");
//        extentReports.createTest("Test2").log(Status.FAIL,"This is also Failed");

        List<String> listdata=new ArrayList<>();
        listdata.add("pradeep");
        listdata.add("pramod");
        listdata.add("pravin");

        Map<Integer,String> mapdata=new HashMap<>();
        mapdata.put(101,"sdfg");
        mapdata.put(102,"sderrg");
        mapdata.put(103,"sdfrodjg");

        Set<Integer> setdata=mapdata.keySet();

        extentReports
                .createTest("List data")
                .info(MarkupHelper.createOrderedList(listdata))
                .info(MarkupHelper.createUnorderedList(listdata));
        extentReports
                .createTest("set data")
                .info(MarkupHelper.createOrderedList(setdata))
                .info(MarkupHelper.createUnorderedList(setdata));
        extentReports
                .createTest("Map data")
                .info(MarkupHelper.createOrderedList(mapdata))
                .info(MarkupHelper.createUnorderedList(mapdata));

        extentReports
                .createTest("Highlight Map")
                .info(MarkupHelper.createLabel("this is me", ExtentColor.YELLOW))
                .info("This is not highlighted");

        Throwable t=new RuntimeException("This is custom exception");
        extentReports
                .createTest("custom Exception")
                .info(t);

        extentReports.flush();
    }
}
