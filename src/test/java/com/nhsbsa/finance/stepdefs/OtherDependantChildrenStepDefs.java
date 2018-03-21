package com.nhsbsa.finance.stepdefs;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.openqa.selenium.WebDriver;

import com.nhsbsa.finance.driver.Config;
import com.nhsbsa.finance.pageobjects.OtherDependantChildrenPage;
import com.nhsbsa.finance.pageobjects.Page;
import com.nhsbsa.finance.pageobjects.SharedYesNoPage;
import com.nhsbsa.finance.properties.PropertyReader;
import com.nhsbsa.finance.shared.SharedData;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

public class OtherDependantChildrenStepDefs {

	private WebDriver driver = Config.getDriver();
	private String baseUrl = PropertyReader.getProperty("base.server");

	private OtherDependantChildrenPage otherDependantChildrenPage;
	SharedYesNoPage sharedYesNoPage;

	@Given("^I am on other dependant children page$")
	public void iAmOnOtherDependantChildrenPage() {
		new Page(driver).navigateToUrl(baseUrl + "/partner-and-child/do-you-have-any-other-dependant-children");
	}

	@Given("^I go to other dependant children page$")
	public void iGoToOtherDependantChildrenPage() {
		new Page(driver).navigateToUrl(baseUrl + "/partner-and-child/do-you-have-any-other-dependant-children");
		otherDependantChildrenPage = new OtherDependantChildrenPage(driver);
		assertThat(otherDependantChildrenPage.getHeading().contains("Do you have any other dependant children?"));
	}

	@Then("^do you have any other depandant children page will be displayed$")
	public void doYouHaveAnyOtherDependantPageWillBeDisplayed() {
		otherDependantChildrenPage = new OtherDependantChildrenPage(driver);
		assertThat(otherDependantChildrenPage.getHeading()).contains("Do you have any other dependant children?");
	}

	@Then("^the default value for any other dependant children page will be blank$")
	public void theDefaultValueForAnyOtherDependantChildrenPageWillBeBlank() {
		assertThat(otherDependantChildrenPage.isOtherDependantChildrenRadioButtonSelected()).isFalse();
	}

	@Then("^the dynamic value of child first and last name will be displayed$")
	public void theDynamicValueOfCildFirstAndLastNameWillBeDisplayed() {
		otherDependantChildrenPage = new OtherDependantChildrenPage(driver);
		List<String> enteredFullNames = SharedData.fullName;
		for (int i = 0; i < enteredFullNames.size(); i++) {
			System.out.println("Name from list : " + enteredFullNames.get(enteredFullNames.size() - 1 - i));
			System.out.println("Name from page : " + otherDependantChildrenPage.readDynamicNameDetails(i));
			assertThat(otherDependantChildrenPage.readDynamicNameDetails(i))
					.matches(enteredFullNames.get(enteredFullNames.size() - 1 - i));
		}
	}

	@And("^the other dependant children page error message '(.*)' will be displayed$")
	public void theOtherDependantChildrenPageErrorMessageWillBeDisplayed(String errorMessage) {
		otherDependantChildrenPage = new OtherDependantChildrenPage(driver);
		sharedYesNoPage = new SharedYesNoPage(driver);
		assertThat(sharedYesNoPage.getSharedFieldErrorMessage()).matches(errorMessage);
		assertThat(sharedYesNoPage.doesSharedErrorMessageHaveAnchor()).isTrue();
		assertThat(sharedYesNoPage.getSharedAnchoredErrorMessage()).matches(errorMessage);

	}

	@And("^I try to enter more than 6 dependant children details$")
	public void iTryToEnterMoreThan6DependantChildrenDetails() {
		sharedYesNoPage = new SharedYesNoPage(driver);
		sharedYesNoPage.selectYes();

	}

}