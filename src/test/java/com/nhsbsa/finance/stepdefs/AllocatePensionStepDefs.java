package com.nhsbsa.finance.stepdefs;

import static org.assertj.core.api.Assertions.assertThat;

import org.openqa.selenium.WebDriver;

import com.nhsbsa.finance.driver.Config;
import com.nhsbsa.finance.pageobjects.AllocatePensionPage;
import com.nhsbsa.finance.pageobjects.Page;
import com.nhsbsa.finance.properties.PropertyReader;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class AllocatePensionStepDefs {

	private WebDriver driver = Config.getDriver();
	private String baseUrl = PropertyReader.getProperty("base.server");

	private AllocatePensionPage allocatePensionPage;

	@Given("^I am on do you want to allocate page$")
	public void iAmOnDoYouWantToAllocatePage() {
		new Page(driver).navigateToUrl(baseUrl + "/pension-allocation-details/do-you-want-to-allocate");
	}

	@Given("^I go to do you want to allocate page$")
	public void iGoToDoYouWantToAllocatePage() {
		new Page(driver).navigateToUrl(baseUrl + "/pension-allocation-details/do-you-want-to-allocate");
		allocatePensionPage = new AllocatePensionPage(driver);
		assertThat(allocatePensionPage.getHeading()).contains("Do you want to allocate part of your pension?");
	}

	@Then("^the default value for allocate your pension will be blank$")
	public void theDefaultValueForAllocateYourPensionWillBeBlank() {
		assertThat(allocatePensionPage.isAllocatePensionRadioButtonSelected()).isFalse();
	}

	@And("^do you want to allocate page error message '(.*)' will be displayed$")
	public void doYouWantToAllocatePageErrorMessageWillBeDisplayed(String errorMessage) {
		allocatePensionPage = new AllocatePensionPage(driver);
		assertThat(allocatePensionPage.getAllocatePensionFieldErrorMessage()).matches(errorMessage);
		assertThat(allocatePensionPage.doesAllocatePensionErrorMessageHaveAnchor()).isTrue();
		assertThat(allocatePensionPage.getAllocatePensionAnchoredErrorMessage()).matches(errorMessage);

	}

	@When("^I select yes on do you want to allocate page$")
	public void iSelectYesOnDoYouWantToAllocatePage() {
		allocatePensionPage = new AllocatePensionPage(driver);
		allocatePensionPage.selectYesButton();
	}

	@When("^I select no on do you want to allocate page$")
	public void iSelectNoOnDoYouWantToAllocatePage() {
		allocatePensionPage = new AllocatePensionPage(driver);
		allocatePensionPage.selectNoButton();
	}

	@When("^I dont select anything on do you want to allocate page$")
	public void iDontSelectAnythingOnDoYouWantToAllocatePage() {
		allocatePensionPage = new AllocatePensionPage(driver);
		allocatePensionPage.allocatePensionIsNotSelected();
	}

	@When("^I click on the link what is allocation$")
	public void iClickOnTheLinkWhatIsAllocation() {
		allocatePensionPage = new AllocatePensionPage(driver);
		allocatePensionPage.verifyAllocationLink();
	}

	@Then("^the allocation information text will be displayed$")
	public void theAllocationInformationTextWillBeDisplayed() {
		allocatePensionPage = new AllocatePensionPage(driver);
		assertThat(allocatePensionPage.verifyAllocationText()
				.contains("To apply, you'll have to pay for a medical. We can send you guidance on this."));
	}
}