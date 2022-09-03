package com.newproject.testcases;

import com.newproject.base.DriverInitialising;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.testng.annotations.Test;


public class LoginTestCases  {

    public static WebDriver driver;
    @Test
    public static void login() throws InterruptedException {
        driver.findElement(By.linkText("Sign in")).click();
        Thread.sleep(3000);
        driver.findElement(By.id("login_id")).sendKeys("padyafadatare@gmail.com");
        Thread.sleep(3000);
        driver.findElement(By.xpath("//*[@id=\'nextbtn\']")).click();
        Thread.sleep(3000);
        driver.findElement(By.xpath("//*[@id=\'password\']")).sendKeys("TL7Rrkzn@v$j4#g");
        Thread.sleep(3000);
        driver.findElement(By.xpath("//*[@id=\'nextbtn\']")).click();
        Thread.sleep(3000);
        driver.close();
    }
}
