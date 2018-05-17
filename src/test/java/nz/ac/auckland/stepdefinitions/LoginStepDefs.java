package nz.ac.auckland.stepdefinitions;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import nz.ac.auckland.accountmanagement.User;
import nz.ac.auckland.accountmanagement.LoginService;
import nz.ac.auckland.accountmanagement.UserType;
import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.equalTo;

public class LoginStepDefs {
	
	private LoginService _loginService;
	
	@Given("^a new (?:User|Administrator) wants to sign up to the system$")
	public void a_new_User_or_Administrator_wants_to_sign_up_to_the_system() throws Exception {
	    _loginService = new LoginService();
	}

	@When("^the (User|Administrator) provides (valid|invalid) details for signing up$")
	public void the_User_or_Administrator_provides_valid_or_invalid_details_for_signing_up(String roleType, String validity) throws Exception {
	    String username = (validity.equals("valid")) ? "JohnSmith1" : null;
	    String password = (validity.equals("valid")) ? "pass1" : null;
	    User credentials = new User(username, password);
	    UserType userType = (roleType.equals("User")) ? UserType.USER : UserType.ADMINISTRATOR;
	    _loginService.register(credentials, userType);
	}

	@Then("^the (User|Administrator) (is|is not) succesfully registered with the system$")
	public void the_User_or_Administrator_is_or_is_not_succesfully_registered_with_the_system(String roleType, String success) throws Exception {
	    boolean registered = (success.equals("is"));
	    UserType userType = (roleType.equals("User")) ? UserType.USER : UserType.ADMINISTRATOR;
	    assertThat(_loginService.getRegistered(userType).contains("JohnSmith1"), equalTo(registered));
	}

	@Given("^(a User|an Administrator) exists in the system$")
	public void a_User_or_Administrator_exists_in_the_system(String roleType) throws Exception {
		_loginService = new LoginService();
		UserType userType = (roleType.contains("User")) ? UserType.USER : UserType.ADMINISTRATOR;
		String username = (roleType.contains("User")) ? "User1" : "Admin1";
		User credentials = new User(username, "pass1");
		_loginService.register(credentials, userType);
	}

	@When("^the (User|Administrator) provides (valid|invalid) details for signing in$")
	public void the_User_or_Administrator_provides_valid_or_invalid_details_for_signing_in(String roleType, String validity) throws Exception {
	    String password = (validity.equals("valid")) ? "pass1" : "anIncorrectPassword88";
	    String username = (roleType.equals("User")) ? "User1" : "Admin1";
	    UserType userType = (roleType.equals("User")) ? UserType.USER : UserType.ADMINISTRATOR;
	    User credentials = new User(username, password);
	    _loginService.signIn(credentials, userType);
	}

	@Then("^the (User|Administrator) (is|is not) succesfully signed in$")
	public void the_User_or_Administrator_is_or_is_not_succesfully_signed_in(String roleType, String success) throws Exception {
	    boolean signedIn = (success.equals("is"));
	    String username = (roleType.equals("User")) ? "User1" : "Admin1";
	    UserType userType = (roleType.equals("User")) ? UserType.USER : UserType.ADMINISTRATOR;
	    assertThat(_loginService.getActive(userType).contains(username), equalTo(signedIn));
	}

	@Given("^(a User|an Administrator) is already signed into the system$")
	public void a_User_or_Administrator_is_already_signed_into_the_system(String roleType) throws Exception {
		_loginService = new LoginService();
		UserType userType = (roleType.contains("User")) ? UserType.USER : UserType.ADMINISTRATOR;
	    User credentials = new User("test1", "pass1");
	    _loginService.register(credentials, userType);
	    _loginService.signIn(credentials, userType);
	}

	@When("^the (User|Administrator) signs out of the system$")
	public void the_User_or_Administrator_signs_out_of_the_system(String roleType) throws Exception {
		UserType userType = (roleType.contains("User")) ? UserType.USER : UserType.ADMINISTRATOR;
		User credentials = new User("test1", "pass1");
		_loginService.signOut(credentials, userType);
	}

	@Then("^the (User|Administrator) is no longer signed in to the system$")
	public void the_User_or_Administrator_is_no_longer_signed_in_to_the_system(String roleType) throws Exception {
		UserType userType = (roleType.contains("User")) ? UserType.USER : UserType.ADMINISTRATOR;
		assertThat(_loginService.getActive(userType).contains("test1"), equalTo(false));
	}
	
}
