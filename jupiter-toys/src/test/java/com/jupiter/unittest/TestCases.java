package com.jupiter.unittest;

import java.io.FileInputStream;
import java.time.Duration;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.AssertJUnit;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.Reporter;

public class TestCases {

	private WebDriver driver;
	private Properties obj;
	private static final String URL = "https://jupiter.cloud.planittesting.com/#/";

	@BeforeMethod
	@Parameters("browser")
	public void setUp(String browser) throws Exception {
		obj = new Properties();
		FileInputStream objfile = new FileInputStream(
				System.getProperty("user.dir") + "//src//test//resources//selectors.properties");
		obj.load(objfile);
		if (browser.equalsIgnoreCase("firefox")) {
			System.setProperty("webdriver.gecko.driver", ".\\geckodriver.exe");
			driver = new FirefoxDriver();
		} else if (browser.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver", ".\\chromedriver.exe");
			driver = new ChromeDriver();
		}

		else {
			// If no browser passed throw exception
			throw new Exception("Browser is not correct");
		}
		Reporter.log("Browser is set to" + browser);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));
		driver.get(URL);
		Reporter.log(URL + " is launced");
		driver.manage().window().maximize();

	}

	@Test(priority = 1)
	// Validate Visa card
	public void happyDayScenarioVisaCard() {

		driver.findElement(By.cssSelector(obj.getProperty("landingPage"))).click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		Reporter.log("Navigated to Shopping Page");
		WebElement anchors = driver.findElement(By.id(obj.getProperty("product1")));
		anchors.findElement(By.linkText(obj.getProperty("buy"))).click();
		Reporter.log("Product one is clicked");
		driver.findElement(By.cssSelector(obj.getProperty("cartLink"))).click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		Reporter.log("Navigated to cart page");
		driver.findElement(By.xpath(obj.getProperty("checkoutButton"))).click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		Reporter.log("Checkout button is clicked");
		String forename = "Shaliha";
		driver.findElement(By.id(obj.getProperty(obj.getProperty("forename")))).sendKeys(forename);
		driver.findElement(By.id(obj.getProperty("email"))).sendKeys("gshaliha99@gmail.com");
		driver.findElement(By.id(obj.getProperty("address"))).sendKeys("collins street, melboure");
		driver.findElement(By.name(obj.getProperty("cardType"))).click();
		driver.findElement(By.cssSelector(obj.getProperty("visa"))).click();
		driver.findElement(By.id(obj.getProperty("card"))).sendKeys("4111111111111111");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		Reporter.log("Mandatory fields are entered");
		driver.findElement(By.id((obj.getProperty("submitButton")))).click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		Reporter.log("clicked on submit");
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector(obj.getProperty("successMsg")))));
		String expectedOrderConfMsg = "Thanks " + forename + ", your order has been accepted. Your order nuumber is JT";
		String originalOrderConfMsg = driver.findElement(By.cssSelector(obj.getProperty("successMsg"))).getText();
		Assert.assertTrue(originalOrderConfMsg.contains(expectedOrderConfMsg));
		Reporter.log("Product ordered succesfully by Visa card");

	}

	@Test(priority = 2)
	// Validate Master card
	public void happyDayScenarioMasterCard() {

		driver.findElement(By.cssSelector(obj.getProperty("landingPage"))).click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		Reporter.log("Navigated to Shopping Page");
		WebElement anchors = driver.findElement(By.id(obj.getProperty("product1")));
		anchors.findElement(By.linkText(obj.getProperty("buy"))).click();
		Reporter.log("Product one is clicked");
		driver.findElement(By.cssSelector(obj.getProperty("cartLink"))).click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		Reporter.log("Navigated to cart page");
		driver.findElement(By.xpath(obj.getProperty("checkoutButton"))).click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		Reporter.log("Checkout button is clicked");
		String forename = "Shaliha";
		driver.findElement(By.id(obj.getProperty(obj.getProperty("forename")))).sendKeys(forename);
		driver.findElement(By.id(obj.getProperty("email"))).sendKeys("gshaliha99@gmail.com");
		driver.findElement(By.id(obj.getProperty("address"))).sendKeys("collins street, melboure");
		driver.findElement(By.name(obj.getProperty("cardType"))).click();
		driver.findElement(By.cssSelector(obj.getProperty("master"))).click();
		driver.findElement(By.id(obj.getProperty("card"))).sendKeys("4111111111111111");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		Reporter.log("Mandatory fields are entered");
		driver.findElement(By.id((obj.getProperty("submitButton")))).click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		Reporter.log("clicked on submit");
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector(obj.getProperty("successMsg")))));
		String expectedOrderConfMsg = "Thanks " + "Shaliha"
				+ ", your order has been accepted. Your order nuumber is JT";
		String originalOrderConfMsg = driver.findElement(By.cssSelector(obj.getProperty("successMsg"))).getText();
		Assert.assertTrue(originalOrderConfMsg.contains(expectedOrderConfMsg));
		Reporter.log("Product ordered succesfully by Master card");

	}

	@Test(priority = 3)
	// validate Empty cart
	public void emptyCart() {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		driver.findElement(By.cssSelector(obj.getProperty("landingPage"))).click();
		Reporter.log("Navigated to Shopping Page");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		driver.findElement(By.cssSelector(obj.getProperty("buyProd1"))).click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		driver.findElement(By.cssSelector(obj.getProperty("buyProd2"))).click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		driver.findElement(By.cssSelector(obj.getProperty("buyProd3"))).click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		driver.findElement(By.cssSelector(obj.getProperty("buyProd4"))).click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		driver.findElement(By.cssSelector(obj.getProperty("buyProd5"))).click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		driver.findElement(By.cssSelector(obj.getProperty("buyProd6"))).click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		driver.findElement(By.cssSelector(obj.getProperty("buyProd7"))).click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		driver.findElement(By.cssSelector(obj.getProperty("buyProd8"))).click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		Reporter.log("All Products are clicked");
		driver.findElement(By.cssSelector(obj.getProperty("cartLink"))).click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		Reporter.log("Navigated to Cart page");

		List<WebElement> rows = driver.findElements(By.xpath(obj.getProperty("cartItems")));
		// System.out.println("No of rows are : " + rows.size());
		int noofCartItemsExpected = 8;
		int noofCartItemsActual = rows.size();
		AssertJUnit.assertEquals(noofCartItemsExpected, noofCartItemsActual);
		Reporter.log("All Products are added to cart");

		// click Emptycart
		driver.findElement(By.xpath(obj.getProperty("emptyCart"))).click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		Reporter.log("Emptycart button is clicked");

		// wait to let the modal box be visible
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(obj.getProperty("popup"))));
		Reporter.log("Model box pops up");
		driver.findElement(By.xpath(obj.getProperty("emptyCartSubmit"))).click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));

		String expectedEmptyCartTitle = "Your cart is empty";
		String orignalEmptyCartTitle = driver.findElement(By.tagName(obj.getProperty("strong"))).getText();
		// System.out.println("expectedemptycartTitle:" + expectedEmptyCartTitle);
		// System.out.println("originalemptycartTitle:" + orignalEmptyCartTitle);
		AssertJUnit.assertEquals(expectedEmptyCartTitle, orignalEmptyCartTitle);
		Reporter.log("Cart is Empty");

	}

	@Test(priority = 4)
	// validating success on changing quantity of the item
	public void changeItemQuantity() {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.findElement(By.cssSelector(obj.getProperty("landingPage"))).click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		Reporter.log("Navigated to shopping page");
		WebElement anchors = driver.findElement(By.id(obj.getProperty("product1")));
		anchors.findElement(By.linkText(obj.getProperty("buy"))).click();
		Reporter.log("product one is clicked");

		driver.findElement(By.cssSelector(obj.getProperty("cartLink"))).click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		Reporter.log("Navigated to cart page");

		driver.findElement(By.xpath(obj.getProperty("inputQuantity"))).clear();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.findElement(By.xpath(obj.getProperty("inputQuantity"))).sendKeys("2");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		Reporter.log("Product quantity is changed");

		driver.findElement(By.xpath(obj.getProperty("checkoutButton"))).click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		Reporter.log("Checkout button is clicked");

		String forename = "Sarfraz";
		driver.findElement(By.id(obj.getProperty("forename"))).sendKeys(forename);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		driver.findElement(By.id(obj.getProperty("email"))).sendKeys("gshaliha99@gmail.com");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		driver.findElement(By.id(obj.getProperty("address"))).sendKeys("collins street, melboure");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		driver.findElement(By.name(obj.getProperty("cardType"))).click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		driver.findElement(By.cssSelector(obj.getProperty("master"))).click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		driver.findElement(By.id(obj.getProperty("card"))).sendKeys("123456789");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		driver.findElement(By.id((obj.getProperty("submitButton")))).click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		Reporter.log("Mandatory fields are entered");

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector(obj.getProperty("successMsg")))));
		String expectedOrderConfMsg = "Thanks " + forename + ", your order has been accepted. Your order nuumber is JT";
		String originalOrderConfMsg = driver.findElement(By.cssSelector(obj.getProperty("successMsg"))).getText();
		Assert.assertTrue(originalOrderConfMsg.contains(expectedOrderConfMsg));
		Reporter.log("Product order placed succussfully");

	}

	@Test(priority = 5)
	// validating success on removing item
	public void removeCartItem() {
		driver.findElement(By.cssSelector(obj.getProperty("landingPage"))).click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		Reporter.log("Navigated to shopping page");
		driver.findElement(By.cssSelector(obj.getProperty("buyProd1"))).click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		Reporter.log("Product one is clicked");

		driver.findElement(By.cssSelector(obj.getProperty("buyProd2"))).click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		Reporter.log("Product two is clicked");

		driver.findElement(By.cssSelector(obj.getProperty("buyProd3"))).click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		Reporter.log("Product three is clicked");

		driver.findElement(By.cssSelector(obj.getProperty("buyProd4"))).click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		Reporter.log("Produt four is clicked");

		driver.findElement(By.cssSelector(obj.getProperty("cartLink"))).click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		Reporter.log("Navigated to cart page");
		driver.findElement(By.xpath(obj.getProperty("removeItem1"))).click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(4));
		driver.findElement(By.xpath(obj.getProperty("emptyCartSubmit"))).click();
		Reporter.log("Product one removed from cart");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		driver.findElement(By.xpath(obj.getProperty("removeItem2"))).click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(4));
		driver.findElement(By.xpath(obj.getProperty("emptyCartSubmit"))).click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		Reporter.log("Product two removed from cart");
		driver.findElement(By.xpath(obj.getProperty("checkoutButton"))).click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		Reporter.log("Checkout button is clicked");

		String forename = "name";
		driver.findElement(By.id(obj.getProperty("forename"))).sendKeys(forename);
		driver.findElement(By.id(obj.getProperty("email"))).sendKeys("gshaliha99@gmail.com");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		driver.findElement(By.id(obj.getProperty("address"))).sendKeys("collins street, melboure");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		driver.findElement(By.name(obj.getProperty("cardType"))).click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		driver.findElement(By.cssSelector(obj.getProperty("master"))).click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		driver.findElement(By.id(obj.getProperty("card"))).sendKeys("123456789");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));
		Reporter.log("Mandatory fields are entered");

		driver.findElement(By.id((obj.getProperty("submitButton")))).click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		Reporter.log("Submit button is clicked");

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector(obj.getProperty("successMsg")))));
		String expectedOrderConfMsg = "Thanks " + forename + ", your order has been accepted. Your order nuumber is JT";
		String originalOrderConfMsg = driver.findElement(By.cssSelector(obj.getProperty("successMsg"))).getText();
		Assert.assertTrue(originalOrderConfMsg.contains(expectedOrderConfMsg));
		Reporter.log("Product ordered sucessfully on removing item");

	}

	@Test(priority = 6)
	// Validate Field error
	public void validateFieldErrorMsg() {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		driver.findElement(By.cssSelector(obj.getProperty("landingPage"))).click();
		Reporter.log("Navigated to shopping page");

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		WebElement anchors = driver.findElement(By.id(obj.getProperty("product1")));
		anchors.findElement(By.linkText(obj.getProperty("buy"))).click();
		Reporter.log("Product one is clicked");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.findElement(By.cssSelector(obj.getProperty("cartLink"))).click();
		Reporter.log("Navigated to Cart page");

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.findElement(By.xpath(obj.getProperty("checkoutButton"))).click();
		Reporter.log("Checkout button is clicked");

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		driver.findElement(By.xpath(obj.getProperty("submitOrder"))).click();
		Reporter.log("Submitting empty form");
		// System.out.print("Submitting empty form\n");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));

		// capture forname error message
		String actualNameErrMsg = driver.findElement(By.id(obj.getProperty("forename-err"))).getText();
		String expectedNameErrMsg = "Forename is required";
		// Verify error message
		Assert.assertEquals(expectedNameErrMsg, actualNameErrMsg);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		Reporter.log("Displayed Forname error");

		// capture email error message
		String actualEmailErrMsg = driver.findElement(By.id(obj.getProperty("email-err"))).getText();
		String expectedEmailErrMsg = "Email is required";
		// Verify error message
		Assert.assertEquals(expectedEmailErrMsg, actualEmailErrMsg);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		Reporter.log("Displayed Email error");

		// capture address error message
		String actualAddressErrMsg = driver.findElement(By.id(obj.getProperty("address-err"))).getText();
		String expectedAddressErrMsg = "Address is required";
		// Verify error message
		Assert.assertEquals(expectedAddressErrMsg, actualAddressErrMsg);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		Reporter.log("Displayed Address error");

		// capture card error message
		String actualCardErrMsg = driver.findElement(By.id(obj.getProperty("card-err"))).getText();
		String expectedCardErrMsg = "Credit Card is required";
		// Verify error message
		Assert.assertEquals(expectedCardErrMsg, actualCardErrMsg);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		Reporter.log("Displayed card error");

		// capture forname error message
		String actualFormErrMsg = driver.findElement(By.xpath(obj.getProperty("formIncompleteMsg"))).getText();
		String expectedFormErrMsg = "Almost there - but we can't send your items unless you complete the form correctly.";
		// Verify error message
		Assert.assertEquals(expectedFormErrMsg, actualFormErrMsg);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		Reporter.log("Field error messages are Displayed successfully");

	}

	@Test(priority = 7)
	// Validate Field error
	public void validateLoginFailure() {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		driver.findElement(By.linkText(obj.getProperty("login"))).click();
		Reporter.log("Login page is clicked");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		driver.findElement(By.id(obj.getProperty("username"))).sendKeys("Shaliha");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		driver.findElement(By.id(obj.getProperty("password"))).sendKeys("1234");
		Reporter.log("Entered username and password");

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		driver.findElement(By.xpath(obj.getProperty("loginButton"))).click();
		Reporter.log("Login button is clicked");

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		String actualMessage = driver.findElement(By.id(obj.getProperty("loginError"))).getText();
		String expectedMsg = "Your login details are incorrect";
		Assert.assertEquals(expectedMsg, actualMessage);
		Reporter.log("Login error displayed");

	}

	@Test(priority = 8)
	// Validate Error message in contact page
	public void contactPageNavigation() {
		driver.findElement(By.cssSelector(obj.getProperty("contactPage"))).click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		Reporter.log("Navigating to contact page");
		// System.out.print("Navigating to contact page\n");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.findElement(By.linkText(obj.getProperty("submit"))).click();
		Reporter.log("Submit empty form");
		// System.out.print("Submitting empty form\n");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		String expectedTitle = "We welcome your feedback - but we won't get it unless you complete the form correctly.";
		String originalTitle = driver.findElement(By.xpath(obj.getProperty("formIncompleteMsg"))).getText();
		Reporter.log(expectedTitle);

		// System.out.println(expectedTitle);
		AssertJUnit.assertEquals(originalTitle, expectedTitle);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));

		// click submit on filling mandatory fields and validate success
		driver.findElement(By.id(obj.getProperty("forename"))).sendKeys("Shaliha");
		driver.findElement(By.id(obj.getProperty("email"))).sendKeys("gshaliha99@gmail.com");
		driver.findElement(By.id(obj.getProperty("message"))).sendKeys("Best product");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		Reporter.log("Filling out mandatory fields");

		// System.out.print("Filling out mandatory fields\n");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		driver.findElement(By.linkText(obj.getProperty("submit"))).click();
		Reporter.log("click on submit");

		// System.out.print("validate success\n");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		String expectedsuccessTitle = "We welcome your feedback";
		String originalsuccessTitle = driver.findElement(By.tagName(obj.getProperty("strong"))).getText();
		Reporter.log(expectedsuccessTitle);

		// System.out.println(expectedsuccessTitle);
		AssertJUnit.assertEquals(originalsuccessTitle, expectedsuccessTitle);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		Reporter.log("Feedback submitted successfully");

	}

	@Test(priority = 9)
	// Validate Item name in cart is same as item name in remove item message box
	public void validatecartName() {
		driver.findElement(By.cssSelector(obj.getProperty("landingPage"))).click();
		Reporter.log("Navigated to shopping page");

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		WebElement anchors = driver.findElement(By.id(obj.getProperty("product1")));
		anchors.findElement(By.linkText(obj.getProperty("buy"))).click();
		Reporter.log("product one is clicked");

		// Clicking on the link "cart"
		driver.findElement(By.cssSelector(obj.getProperty("cartLink"))).click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		Reporter.log("Navigated to cart page");

		// Getting name of product in cart
		String productName = driver.findElement(By.xpath(obj.getProperty("teddyTable"))).getText();

		driver.findElement(By.xpath(obj.getProperty("removeAlert"))).click();
		Reporter.log("Selected product name is displayed");

		// Getting product name displayed in message box
		String cartItemname = driver.findElement(By.xpath(obj.getProperty("teddyCart"))).getText();
		driver.findElement(By.xpath(obj.getProperty("emptyCartSubmit"))).click();
		AssertJUnit.assertEquals(productName, cartItemname);
		Reporter.log(" Procuct name in cart is same as product name in remove item message box");

	}

	@Test(priority = 10)
	// Validate product total
	public void validateCartProductTotal() {
		driver.findElement(By.cssSelector(obj.getProperty("landingPage"))).click();
		Reporter.log("Navigated to shopping page");

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		WebElement anchors = driver.findElement(By.id(obj.getProperty("product1")));
		anchors.findElement(By.linkText(obj.getProperty("buy"))).click();
		Reporter.log("product one is clicked");

		// Clicking on the link "cart"
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		driver.findElement(By.cssSelector(obj.getProperty("cartLink"))).click();
		Reporter.log("Navigated to cart page");

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		String expectedTotal = "Total: 12.99";
		Reporter.log("Cost of product is displayed");
		String actualTotal = driver.findElement(By.xpath(obj.getProperty("cartTotal"))).getText();

		AssertJUnit.assertEquals(expectedTotal, actualTotal);
		Reporter.log("Product total displayed successfully");

	}


	@AfterMethod
	public void cleanUp() {

		driver.quit();

	}

}
