$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("file:src/test/resources/Features/Ecommerce.feature");
formatter.feature({
  "name": "Title of your feature",
  "description": "  I want to use this template for my feature file",
  "keyword": "Feature",
  "tags": [
    {
      "name": "@Test1"
    }
  ]
});
formatter.scenario({
  "name": "Ecommerce login application",
  "description": "",
  "keyword": "Scenario",
  "tags": [
    {
      "name": "@Test1"
    },
    {
      "name": "@Test"
    }
  ]
});
formatter.before({
  "status": "passed"
});
formatter.before({
  "status": "passed"
});
formatter.step({
  "name": "I login to ecommerce app",
  "keyword": "Given "
});
formatter.match({
  "location": "com.qa.automation.stepdefinations.LoginStepDefination.i_login_to_ecommerce_app()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "I click on \"womens\" section",
  "keyword": "And "
});
formatter.match({
  "location": "com.qa.automation.stepdefinations.EcommerceStepDefinnation.i_click_on_section(java.lang.String)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "I sign out from website",
  "keyword": "And "
});
formatter.match({
  "location": "com.qa.automation.stepdefinations.LoginStepDefination.iSignOutWebsite()"
});
formatter.result({
  "status": "passed"
});
formatter.after({
  "status": "passed"
});
formatter.after({
  "status": "passed"
});
});