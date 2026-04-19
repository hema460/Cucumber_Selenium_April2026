package stepDefinition;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import io.cucumber.java.After;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import rahulshettyacademy.AbstractComponent.BaseTest;
import rahulshettyacademy.PageObjects.CheckoutPage;
import rahulshettyacademy.PageObjects.LandingPage;
import rahulshettyacademy.PageObjects.OrderConfirmationPage;
import rahulshettyacademy.PageObjects.PaymentPage;
import rahulshettyacademy.PageObjects.ProductPage;


public class PurchaseorderStepDef extends BaseTest{
	ProductPage productPage;
	
	CheckoutPage checkoutPage;

	OrderConfirmationPage orderConfirmationPage;

	public PurchaseorderStepDef() throws IOException {
		super();
		
	}
	
	@Given("Logged in with username {string} and password {string}")
	public void logged_in_with_username_and_password(String email, String password) {

		productPage=landingPage.loginApplication(email,password);

	}
	@Then("verify that the {string} is displayed on the page.")
	public void verify_that_the_is_displayed_on_the_page(String header) {
		String loginCheck=productPage.verifyLoginSuccessfull();

		Assert.assertEquals(loginCheck,header);
	}

	@When("I add product {string} to Cart")
	public void i_add_product_to_cart(String productName) throws InterruptedException {
		List<WebElement>products=productPage.getProductList();
		productPage.getProductByName(productName);
		productPage.addProductToCart(productName);
		WebElement successmessage=productPage.verifySuccessMessage();
		Assert.assertTrue(successmessage.isDisplayed());
		Thread.sleep(3000);
		checkoutPage=productPage.clickOnCart();

	}
	@When("Checkout {string} and submit the order")
	public void checkout_and_submit_the_order(String productName) {
		List<WebElement> cartProducts=checkoutPage.getCartProduct();
		Boolean match=checkoutPage.verifyProductDisplay(productName);
		Assert.assertTrue(match);
		PaymentPage paymentPage=checkoutPage.clickOnCheckOut();
		paymentPage.clickOnCountry("India");

		orderConfirmationPage=paymentPage.clickOnPlaceOrderBtn();

	}
	@Then("{string} message is displayed on ConfirmationPage")
	public void message_is_displayed_on_confirmation_page(String successMessage) {
		String orderPlacedmessage=orderConfirmationPage.verifyOrderConfirmationMessage();
		Assert.assertEquals(orderPlacedmessage,successMessage);
	}







}
