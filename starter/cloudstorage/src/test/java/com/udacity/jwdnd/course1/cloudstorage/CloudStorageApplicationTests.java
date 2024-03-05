package com.udacity.jwdnd.course1.cloudstorage;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import java.io.File;
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CloudStorageApplicationTests {

	@LocalServerPort
	private int port;

	private WebDriver driver;

	@BeforeAll
	static void beforeAll() {
		WebDriverManager.chromedriver().clearDriverCache();
		WebDriverManager.chromedriver().clearResolutionCache();
		WebDriverManager.chromedriver().setup();
	}

	@BeforeEach
	public void beforeEach() {
		this.driver =  new ChromeDriver();
	}

	@AfterEach
	public void afterEach() {
		if (this.driver != null) {
			driver.quit();
		}
	}

	@Test
	public void getLoginPage() {
		driver.get("http://localhost:" + this.port + "/login");
		Assertions.assertEquals("Login", driver.getTitle());
	}

	/**
	 * PLEASE DO NOT DELETE THIS method.
	 * Helper method for Udacity-supplied sanity checks.
	 **/
	private void doMockSignUp(String firstName, String lastName, String userName, String password){
		// Create a dummy account for logging in later.

		// Visit the sign-up page.
		WebDriverWait webDriverWait = new WebDriverWait(driver, 2);
		driver.get("http://localhost:" + this.port + "/signup");
		webDriverWait.until(ExpectedConditions.titleContains("Sign Up"));

		// Fill out credentials
		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("inputFirstName")));
		WebElement inputFirstName = driver.findElement(By.id("inputFirstName"));
		inputFirstName.click();
		inputFirstName.sendKeys(firstName);

		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("inputLastName")));
		WebElement inputLastName = driver.findElement(By.id("inputLastName"));
		inputLastName.click();
		inputLastName.sendKeys(lastName);

		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("inputUsername")));
		WebElement inputUsername = driver.findElement(By.id("inputUsername"));
		inputUsername.click();
		inputUsername.sendKeys(userName);

		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("inputPassword")));
		WebElement inputPassword = driver.findElement(By.id("inputPassword"));
		inputPassword.click();
		inputPassword.sendKeys(password);

		// Attempt to sign up.
		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("buttonSignUp")));
		WebElement buttonSignUp = driver.findElement(By.id("buttonSignUp"));
		buttonSignUp.click();

		/* Check that the sign up was successful.
		// You may have to modify the element "success-msg" and the sign-up
		// success message below depening on the rest of your code.
		*/
//		Assertions.assertTrue(driver.findElement(By.id("success-msg")).getText().contains("You successfully signed up!"));
	}

	
	
	/**
	 * PLEASE DO NOT DELETE THIS method.
	 * Helper method for Udacity-supplied sanity checks.
	 **/
	private void doLogIn(String userName, String password)
	{
		// Log in to our dummy account.
		driver.get("http://localhost:" + this.port + "/login");
		WebDriverWait webDriverWait = new WebDriverWait(driver, 2);

		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("inputUsername")));
		WebElement loginUserName = driver.findElement(By.id("inputUsername"));
		loginUserName.click();
		loginUserName.sendKeys(userName);

		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("inputPassword")));
		WebElement loginPassword = driver.findElement(By.id("inputPassword"));
		loginPassword.click();
		loginPassword.sendKeys(password);

		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("login-button")));
		WebElement loginButton = driver.findElement(By.id("login-button"));
		loginButton.click();

		webDriverWait.until(ExpectedConditions.titleContains("Home"));

	}

	/**
	 * PLEASE DO NOT DELETE THIS TEST. You may modify this test to work with the 
	 * rest of your code. 
	 * This test is provided by Udacity to perform some basic sanity testing of 
	 * your code to ensure that it meets certain rubric criteria. 
	 * 
	 * If this test is failing, please ensure that you are handling redirecting users 
	 * back to the login page after a succesful sign up.
	 * Read more about the requirement in the rubric: 
	 * https://review.udacity.com/#!/rubrics/2724/view 
	 */
//	@Test
//	public void testRedirection() {
//		// Create a test account
//		doMockSignUp("Redirection","Test","RT","123");
//
//		// Check if we have been redirected to the log in page.
//		Assertions.assertEquals("http://localhost:" + this.port + "/login", driver.getCurrentUrl());
//	}

	/**
	 * PLEASE DO NOT DELETE THIS TEST. You may modify this test to work with the 
	 * rest of your code. 
	 * This test is provided by Udacity to perform some basic sanity testing of 
	 * your code to ensure that it meets certain rubric criteria. 
	 * 
	 * If this test is failing, please ensure that you are handling bad URLs 
	 * gracefully, for example with a custom error page.
	 * 
	 * Read more about custom error pages at: 
	 * https://attacomsian.com/blog/spring-boot-custom-error-page#displaying-custom-error-page
	 */
//	@Test
//	public void testBadUrl() {
//		// Create a test account
//		doMockSignUp("URL","Test","UT","123");
//		doLogIn("UT", "123");
//
//		// Try to access a random made-up URL.
//		driver.get("http://localhost:" + this.port + "/some-random-page");
//        Assertions.assertFalse(driver.getPageSource().contains("Whitelabel Error Page"));
//	}


	/**
	 * PLEASE DO NOT DELETE THIS TEST. You may modify this test to work with the 
	 * rest of your code. 
	 * This test is provided by Udacity to perform some basic sanity testing of 
	 * your code to ensure that it meets certain rubric criteria. 
	 * 
	 * If this test is failing, please ensure that you are handling uploading large files (>1MB),
	 * gracefully in your code. 
	 * 
	 * Read more about file size limits here: 
	 * https://spring.io/guides/gs/uploading-files/ under the "Tuning File Upload Limits" section.
	 */
	@Test
	public void testLargeUpload() {
		// Create a test account
		doMockSignUp("Large File","Test","LFT","123");
		doLogIn("LFT", "123");

		// Try to upload an arbitrary large file
		WebDriverWait webDriverWait = new WebDriverWait(driver, 2);
		String fileName = "upload5m.zip";

		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("fileUpload")));
		WebElement fileSelectButton = driver.findElement(By.id("fileUpload"));
		fileSelectButton.sendKeys(new File(fileName).getAbsolutePath());

		WebElement uploadButton = driver.findElement(By.id("uploadButton"));
		uploadButton.click();
		try {
			webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("success")));
		} catch (org.openqa.selenium.TimeoutException e) {
			System.out.println("Large File upload failed");
		}
		Assertions.assertFalse(driver.getPageSource().contains("HTTP Status 403 – Forbidden"));

	}

	@Test
	public void testUnauthorizedAccess() {
		driver.get("http://localhost:" + this.port + "/login");
		Assertions.assertEquals("Login", driver.getTitle());

		driver.get("http://localhost:" + this.port + "/signup");
		Assertions.assertEquals("Sign Up", driver.getTitle());

		driver.get("http://localhost:" + this.port + "/home");
		Assertions.assertEquals("Login", driver.getTitle());
	}

	@Test
	public void testSignupThenLogin() throws InterruptedException {
		doMockSignUp("Huy","BVA","username","password");
		doLogIn("username", "password");

		WebElement logoutButton= driver.findElement(By.id("logout-button"));
		logoutButton.click();
        Assertions.assertNotEquals("Home", driver.getTitle());
		Assertions.assertEquals("Login", driver.getTitle());
		Thread.sleep(2000);
	}

	@Test
	public void testAddNote() {
		doMockSignUp("Huy","BVA","username","password");
		doLogIn("username", "password");

		WebElement notesTab= driver.findElement(By.id("nav-notes-tab"));
		notesTab.click();
		WebDriverWait wait = new WebDriverWait(this.driver, 10);

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("nav-notes")));

		WebElement buttonAdd = driver.findElement(By.id("addNote"));
		buttonAdd.click();

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("note-title")));
		WebElement inputTitle = driver.findElement(By.id("note-title"));
		inputTitle.click();
		inputTitle.sendKeys("This is test add note title");

		WebElement inputDescription = driver.findElement(By.id("note-description"));
		inputDescription.click();
		inputDescription.sendKeys("This is test add note description");

		WebElement buttonSubmit = driver.findElement(By.id("exeFuncNote"));
		buttonSubmit.click();

		WebDriverWait driverWait = new WebDriverWait(driver, 10);
		driver.get("http://localhost:" + this.port + "/home");
		driverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("nav-notes-tab")));
		driver.findElement(By.id("nav-notes-tab")).click();
	}

	@Test
	public void testEditNote() {

		testAddNote();

		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("edit-button")));

		WebElement buttonEdit = driver.findElement(By.id("edit-button"));
		buttonEdit.click();

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("note-title")));
		WebElement inputTitle = driver.findElement(By.id("note-title"));
		inputTitle.click();
		inputTitle.sendKeys("This is test update note title");

		WebElement inputDescription = driver.findElement(By.id("note-description"));
		inputDescription.click();
		inputDescription.sendKeys("This is test update note description");

		WebElement buttonSubmit = driver.findElement(By.id("exeFuncNote"));
		buttonSubmit.click();

		WebDriverWait driverWait = new WebDriverWait(driver, 10);
		driver.get("http://localhost:" + this.port + "/home");
		driverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("nav-notes-tab")));
		driver.findElement(By.id("nav-notes-tab")).click();


	}

	@Test
	public void deleteNote(){

		testAddNote();

		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("delete")));

		WebElement buttonDelete = driver.findElement(By.id("delete"));
		buttonDelete.click();
		driver.switchTo().alert().accept();

		WebDriverWait driverWait = new WebDriverWait(driver, 10);
		driver.get("http://localhost:" + this.port + "/home");
		driverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("nav-notes-tab")));
		driver.findElement(By.id("nav-notes-tab")).click();

	}

	@Test
	public void testAddCredential() {
		doMockSignUp("Huy", "BVA", "username", "password");
		doLogIn("username", "password");

		WebElement notesTab = driver.findElement(By.id("nav-credentials-tab"));
		notesTab.click();
		WebDriverWait wait = new WebDriverWait(this.driver, 10);

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("nav-credentials")));

		WebElement buttonAdd = driver.findElement(By.id("addCredential"));
		buttonAdd.click();

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("credential-url")));
		WebElement inputUrl = driver.findElement(By.id("credential-url"));
		inputUrl.click();
		inputUrl.sendKeys("This is test add credential url");

		WebElement inputUsername = driver.findElement(By.id("credential-username"));
		inputUsername.click();
		inputUsername.sendKeys("This is test add credential username");

		WebElement inputPassword = driver.findElement(By.id("credential-password"));
		inputPassword.click();
		inputPassword.sendKeys("This is test add credential password");

		WebElement buttonSubmit = driver.findElement(By.id("exeFuncCredential"));
		buttonSubmit.click();

		WebDriverWait driverWait = new WebDriverWait(driver, 10);
		driver.get("http://localhost:" + this.port + "/home");
		driverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("nav-credentials-tab")));
		driver.findElement(By.id("nav-credentials-tab")).click();
	}

	@Test
	public void testUdpCredential() {

		testAddCredential();

		WebDriverWait wait = new WebDriverWait(this.driver, 10);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("edit-button2")));

		WebElement buttonUpd = driver.findElement(By.id("edit-button2"));
		buttonUpd.click();

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("credential-url")));
		WebElement inputUrl = driver.findElement(By.id("credential-url"));
		inputUrl.click();
		inputUrl.sendKeys("This is test update credential url");

		WebElement inputUsername = driver.findElement(By.id("credential-username"));
		inputUsername.click();
		inputUsername.sendKeys("This is test update credential username");

		WebElement inputPassword = driver.findElement(By.id("credential-password"));
		inputPassword.click();
		inputPassword.sendKeys("This is test update credential password");

		WebElement buttonSubmit = driver.findElement(By.id("exeFuncCredential"));
		buttonSubmit.click();

		WebDriverWait driverWait = new WebDriverWait(driver, 10);
		driver.get("http://localhost:" + this.port + "/home");
		driverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("nav-credentials-tab")));
		driver.findElement(By.id("nav-credentials-tab")).click();

	}

	@Test
	public void deleteCredential(){

		testAddCredential();

		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("delete2")));

		WebElement buttonDelete = driver.findElement(By.id("delete2"));
		buttonDelete.click();
		driver.switchTo().alert().accept();

		WebDriverWait driverWait = new WebDriverWait(driver, 10);
		driver.get("http://localhost:" + this.port + "/home");
		driverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("nav-notes-tab")));
		driver.findElement(By.id("nav-notes-tab")).click();

	}

}
