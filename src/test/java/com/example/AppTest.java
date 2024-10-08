package com.example;

import io.cucumber.java.en.*;
import io.cucumber.messages.types.Duration;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.Dimension;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.AppiumW3CHttpCommandCodec;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebElement;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

public class AppTest {
    public AppiumDriver driver;
    
    @SuppressWarnings("deprecation")
    @Given("I launch the SwagLabs apk")
    public void iLaunchTheSwagLabsApk() throws MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("appium:deviceName", "My Device");
        capabilities.setCapability("appium:app", "/Users/rizalkurniawan/Downloads/Android.SauceLabs.Mobile.Sample.app.2.7.1.apk");
        capabilities.setCapability("appium:appPackage", "com.swaglabsmobileapp");
        capabilities.setCapability("appium:appActivity", "com.swaglabsmobileapp.SplashActivity");
        capabilities.setCapability("appium:automationName", "UiAutomator2");

        driver = new AppiumDriver(new URL("http://127.0.0.1:4723/"), capabilities);
    }

    @When("I enters a valid username and valid password")
    public void iEntersAValidUsernameAndValidPassword() {
        driver.findElement(By.xpath("//*[@content-desc='test-Username']")).sendKeys("standard_user");
        driver.findElement(By.xpath("//*[@content-desc='test-Password']")).sendKeys("secret_sauce");
    }

    @And("I tap the Login button")
    public void iTapTheLoginButton() {
        driver.findElement(By.xpath("//*[@content-desc='test-LOGIN']")).click();
    }

    @Then("I should see the products page")
    public void iShouldSeeTheProductsPage() throws InterruptedException {
        Thread.sleep(2000);
        WebElement product = driver.findElement(By.xpath("//*[@content-desc='test-PRODUCTS']"));
        org.junit.Assert.assertTrue(product.isDisplayed());
    }

    @When("I add a product to the cart")
    public void iAddAProductToTheCart() {
        driver.findElement(By.xpath("(//*[@content-desc='test-ADD TO CART'])[2]")).click();
    }

    @And("I navigate to the cart page")
    public void iNavigateToTheCartPage() {
        driver.findElement(By.xpath("//*[@content-desc='test-Cart']")).click();
    }

    @And("I tap the Checkout button")
    public void iTapTheCheckoutButton() throws InterruptedException {
        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[@content-desc='test-CHECKOUT']")).click();
    }

    @And("I enter valide  first name, last name, and postal code")
    public void iEnterValideFirstNameLastNamePostalCode() throws InterruptedException {
        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[@content-desc='test-First Name']")).sendKeys("Rizal");
        driver.findElement(By.xpath("//*[@content-desc='test-Last Name']")).sendKeys("Kurniawan");
        driver.findElement(By.xpath("//*[@content-desc='test-Zip/Postal Code']")).sendKeys("65039");
    }

    @And("I tap the Continue button")
    public void iTapTheContinueButton() {
        driver.findElement(By.xpath("//*[@content-desc='test-CONTINUE']")).click();
    }

    @Then("I should see the checkout overview page")
    public void iShouldSeeTheCheckoutOverviewPage() throws InterruptedException {
        Thread.sleep(2000);
        WebElement overview = driver.findElement(By.xpath("//*[@content-desc='test-CHECKOUT: OVERVIEW']"));
        org.junit.Assert.assertTrue(overview.isDisplayed());
    }
    public void scrollDown() {
        WebElement element = driver.findElement(
            AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector().scrollable(true)).scrollForward()")
        );
        element.click();
    }
    @When("I tap the Finish button")
    public void iTapTheFinishButton() {
        scrollDown();
        driver.findElement(By.xpath("//*[@content-desc='test-FINISH']")).click();
    }

    @Then("I should see checkout complete page")
    public void iShouldSeeCheckoutCompletePage() throws InterruptedException {
        Thread.sleep(2000);
        WebElement complete = driver.findElement(By.xpath("//*[@content-desc='test-CHECKOUT: COMPLETE!']"));
        org.junit.Assert.assertTrue(complete.isDisplayed());
        driver.quit();
    }
}
