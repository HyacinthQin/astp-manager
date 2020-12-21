package com.qa.test;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.IsNot.not;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;

import java.util.*;

public class TestUI {

  private WebDriver driver;
  private Map<String, Object> vars;
  JavascriptExecutor js;
  @Before
  public void setUp() {
    //System.setProperty("webdriver.firefox.bin","C:\\Program Files\\Mozilla Firefox\\firefox.exe");
    System.setProperty("webdriver.chrome.driver","D:\\pro\\chromedriver.exe");
    driver = new ChromeDriver();
    js = (JavascriptExecutor) driver;
    vars = new HashMap<String, Object>();
  }
  @After
  public void tearDown() {
    driver.quit();
  }
  @Test
  public void void1() {
    driver.get("https://www.baidu.com/");
    driver.manage().window().setSize(new Dimension(1550, 838));
    driver.findElement(By.id("kw")).click();
    driver.findElement(By.id("kw")).sendKeys("孝道科技");
    driver.findElement(By.id("su")).click();
    driver.findElement(By.xpath("//*[@id=\"su\"]")).click();
    driver.findElement(By.linkText("孝道科技 - 首页")).click();

  }
}
