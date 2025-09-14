package io.loop.step_definitions;



import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.loop.pages.POM;

    public class ProductStepDefs {


        POM pages = new POM();

        @Given("User is on the HomePage")
        public void user_is_on_the_home_page() {

        }
        @Then("User should be able to see expected prices in the following products")
        public void user_should_be_able_to_see_expected_prices_in_the_following_products(io.cucumber.datatable.DataTable dataTable) {

        }
    }

