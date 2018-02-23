package com.nhsbsa.finance.stepdefs;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;

import org.openqa.selenium.WebDriver;

import com.nhsbsa.finance.driver.Config;
import com.nhsbsa.finance.pageobjects.IntendToWorkForNHSPage;
import com.nhsbsa.finance.pageobjects.NavBarPage;
import com.nhsbsa.finance.pageobjects.Page;
import com.nhsbsa.finance.properties.PropertyReader;
import com.nhsbsa.finance.shared.SharedData;
import com.nhsbsa.finance.shared.SharedMethods;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class IntendToWorkForNHSStepDefs {

	private WebDriver driver = Config.getDriver();
	private String baseUrl = PropertyReader.getProperty("base.server");

	private IntendToWorkForNHSPage intendToWorkForNHSPage;

	@Given("^I am on intend to work for  NHS page$")
	public void iAmOnIntenToWorkForNHSPage() {
		new Page(driver).navigateToUrl(baseUrl + "/employment-details/do-you-intend-to-work-for-nhs");
	}

	@Given("^I go to intend to work for NHS page$")
	public void iGoToIntendToWorkForNHSPage() {
		new Page(driver).navigateToUrl(baseUrl + "/employment-details/do-you-intend-to-work-for-nhs");
		intendToWorkForNHSPage = new IntendToWorkForNHSPage(driver);
		assertThat(intendToWorkForNHSPage.getHeading()).contains("Do you plan to work for the NHS after you get your pension?");
	}

	@Then("^the default value for intend to work for NHS will be blank$")
	public void theDefaultValueForIntendToWorkForNHSWillBeBlank() {
		assertThat(intendToWorkForNHSPage.intendToWorkForNHSRadioButtonSelected()).isFalse();
	}

	@And("^intend to work for NHS Page error message '(.*)' will be displayed$")
	public void intendToWorkForNhsPageErrorMessageWillBeDisplayed(String errorMessage) {
		intendToWorkForNHSPage = new IntendToWorkForNHSPage(driver);
		assertThat(intendToWorkForNHSPage.getNhsFieldErrorMessage()).matches(errorMessage);
		assertThat(intendToWorkForNHSPage.doesNhsErrorMessageHaveAnchor()).isTrue();
		assertThat(intendToWorkForNHSPage.getNhsAnchoredErrorMessage()).matches(errorMessage);

	}

	@When("^I choose Yes$")
	public void iChooseYes() {
		intendToWorkForNHSPage = new IntendToWorkForNHSPage(driver);
		intendToWorkForNHSPage.submitValidYesNhsDetails();
	}

	@When("^I choose No$")
	public void iChooseNo() {
		intendToWorkForNHSPage = new IntendToWorkForNHSPage(driver);
		intendToWorkForNHSPage.submitValidNoDetails();
	}

	@When("^I dont choose anything$")
	public void iDontChooseAnything() {
		intendToWorkForNHSPage = new IntendToWorkForNHSPage(driver);
		intendToWorkForNHSPage.intendToWorkForNHSIsNotSelected();
	}
	

	@When("^the date you return to work fields will be displayed$")
	public void theDateYouReturnToWorkfieldsWillBeDisplayed() {
	
		intendToWorkForNHSPage = new IntendToWorkForNHSPage(driver);
	assertThat(intendToWorkForNHSPage.getDateHeading().contains("What date will you be returning work?"));
	assertThat(intendToWorkForNHSPage.getExampleHint().matches("For example, 31 3 1980"));

	}
	
	
	@When("^I enter the valid date$")
	public void IenterTheValidDate() {
		String randomDateString = SharedMethods.randomDateGenerator();
		LocalDate localDate = LocalDate.parse(randomDateString);
		SharedData.day = SharedMethods.formatDay(localDate);
		SharedData.month = SharedMethods.formatMonth(localDate);
		SharedData.year = SharedMethods.formatYear(localDate);
		intendToWorkForNHSPage = new IntendToWorkForNHSPage(driver);
		intendToWorkForNHSPage.submitValidDate(SharedData.day, SharedData.month, SharedData.year);
	}

	@When("^I enter invalid date details using the day '(.*)', month '(.*)' and year '(.*)$")
	public void iEnterInvalidDateDetailsUsingTheDayMonthAndYear(String day, String month, String year) {

		intendToWorkForNHSPage = new IntendToWorkForNHSPage(driver);
		intendToWorkForNHSPage.enterDateDetails(day, month, year);
		intendToWorkForNHSPage.submitInValidDateDetails();

	}

	@And("^the date you return to work error message '(.*)' will be displayed$")
	public void theDateYouReturnToWorkErrorMessageWillBeDisplayed(String errorMessage) {
		intendToWorkForNHSPage = new IntendToWorkForNHSPage(driver);
		assertThat(intendToWorkForNHSPage.doesDateErrorMessageHaveAnchor()).isTrue();
		assertThat(intendToWorkForNHSPage.getDateAnchoredErrorMessage()).matches(errorMessage);
		assertThat(intendToWorkForNHSPage.getDateFieldErrorMessage()).matches(errorMessage);
	}

	
	@Then("^the intend to work for NHS submission will be unsuccessful$")
	public void theIntendToWorkForNHSSubmissionWillBeUnsuccessful() {
		intendToWorkForNHSPage = new IntendToWorkForNHSPage(driver);
		assertThat(intendToWorkForNHSPage.getErrorHeadingErrorMessage())
				.matches("Some questions have not been answered correctly:");
		assertThat(intendToWorkForNHSPage.getErrorsBelowErrorMessage()).matches("Please see the errors below.");
	}

	
	@Then("^the intend to work for NHS submission will be successful$")
	public void theIntendToWorkForNHSSubmissionWillBeSuccessful() {
		new NavBarPage(driver);
	}
	
	
}