package com.newproject.testcases;

import com.newproject.base.DriverInitialising;
import com.newproject.pages.SighupPage;
import com.newproject.utility.TestDataZoho;
import org.testng.annotations.Test;

import java.io.IOException;

public class SignInTestCase extends DriverInitialising{

    public SignInTestCase() throws IOException {
        super();
    }

    @Test(dataProvider = "logindata",dataProviderClass = TestDataZoho.class,testName = "login test")
    public void signin(String username,String password) throws IOException {

        SighupPage sighupPage=new SighupPage();
        sighupPage.signintozaho(username, password);
        extentTest.info("login");
    }

//    @DataProvider(name = "test1")
//    public Object[][] tData() {
//        return new Object[][]{
//                {"padyafadatare@gail.com","TLRrkzn@v$j4#g"},
//                {"padyafadatare@gail.com","TL7Rrkzn@v$j4#g"},
//                {"padyafadatare@mail.com","TL7Rrn@v$j4#g"},
//                {"padyafadatare@gmail.com","TL7Rrkzn@v$j4#g"}
//        };
//
//    }
}
