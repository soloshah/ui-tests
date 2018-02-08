package com.nhsbsa.finance.stepdefs;

import static org.assertj.core.api.Assertions.assertThat;

import org.openqa.selenium.WebDriver;

import com.nhsbsa.finance.driver.Config;
import com.nhsbsa.finance.pageobjects.JobTitlePage;
import com.nhsbsa.finance.pageobjects.NavBarPage;
import com.nhsbsa.finance.pageobjects.Page;
import com.nhsbsa.finance.properties.PropertyReader;
import com.nhsbsa.finance.shared.SharedData;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class JobTitleStepDefs {

  private WebDriver driver = Config.getDriver();
  private String baseUrl = PropertyReader.getProperty("base.server");
  private JobTitlePage  jobTitlePage;


  @Given("^I am on the job title page$")
  public void iAmOnTheJobTitlePage() {
    new Page(driver).navigateToUrl(baseUrl+ "/awards/employment/job");
  }

  @Then("^the job title submission will be successful$")
  public void theJobTitleSubmissionWillBeSuccessful() {
	 new NavBarPage(driver);
  }
  
  @When("^I enter valid job title details$")
  public void IenterValidJobTitleDetails(){
	  SharedData.jobTitle = "Sr Midwife";
	  jobTitlePage = new JobTitlePage(driver); 
	  jobTitlePage.submitValidJobTitleDetails(SharedData.jobTitle);
  }	
  
  @Then("^the job title submission will be unsuccessful$")
  public void theJobTitleSubmissionWillBeUnsuccessful() {
	  jobTitlePage = new JobTitlePage(driver);
    assertThat(jobTitlePage.getErrorHeadingErrorMessage())
        .matches("Some questions have not been answered correctly.");
    assertThat(jobTitlePage
    		.getErrorsBelowErrorMessage())
        .matches("Please see the errors below.");
  }
  
  @When("^I enter job title details using the title '(.*)'$")
  public void iEnterJobTitleDetailsUsingTheTitle(
      String jobTitle) {
	  jobTitlePage = new JobTitlePage(driver);
	  jobTitlePage
        .enterJobDetails(SharedData.jobTitle);
      jobTitlePage.submitInValidJobTitleDetails();
}
  
  @And("^the job title error message '(.*)' will be displayed$")
  public void theJobTitleErrorMessageWillBeDisplayed(String errorMessage) {
	  jobTitlePage = new JobTitlePage(driver);
    assertThat(jobTitlePage.doesJobTitleErrorMessageHaveAnchor()).isTrue();
    assertThat(jobTitlePage.getJobTitleAnchoredErrorMessage()).matches(errorMessage);
    assertThat(jobTitlePage.getJobTitleFieldErrorMessage()).matches(errorMessage);
  }
 
}