package com.newproject.pages;

import com.newproject.base.DriverInitialising;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.IOException;

public class SighupPage extends DriverInitialising {

    public SighupPage() throws IOException {
        PageFactory.initElements(driver,this);
    }

    @FindBy(linkText ="Sign in")
    WebElement signinbutton;
    @FindBy(id = "login_id")WebElement username;
    @FindBy(xpath = "//*[@id=\'nextbtn\']") WebElement nextbutton;
    @FindBy(xpath = "//*[@id=\'password\']") WebElement password;

    public  void signintozaho(String appusername, String apppassword){
        signinbutton.click();
        extentTest.info("Element Clicked");
        username.sendKeys(appusername);
        nextbutton.click();
        password.sendKeys(apppassword);
        extentTest.info("Password entered");
        nextbutton.click();
    }
}
