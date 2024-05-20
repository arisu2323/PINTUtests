import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys

Mobile.startApplication(GlobalVariable.APK_PATH, true)

// Case 1: Empty Email or Password
Mobile.tap(findTestObject('Android/Login/button_login'), 0)
Mobile.delay(1)
error_message_emptyEmail = Mobile.getText(findTestObject('Android/Login/alert_emailEmpty'), 0)

Mobile.setText(findTestObject('Android/Login/EditText_email'), 'example@example.com', 0)
Mobile.tap(findTestObject('Android/Login/button_login'), 0)
Mobile.delay(1)
error_message_emptyPassword = Mobile.getText(findTestObject('Android/Login/alert_passwordEmpty'), 0)
	// Verify error message for empty Email or Password
	Mobile.verifyEqual(error_message_emptyEmail, 'Enter Valid Email')
	// Verifying empty password has a wrong error message
	Mobile.verifyEqual(error_message_emptyPassword, 'Enter Valid Password', FailureHandling.CONTINUE_ON_FAILURE)
	Mobile.takeScreenshot()
	Mobile.closeApplication()
	
// Case 2: Incorrect email
Mobile.startApplication(GlobalVariable.APK_PATH, true)
Mobile.setText(findTestObject('Android/Login/EditText_email'), 'incorrect@example.com', 0)
Mobile.setText(findTestObject('Android/Login/EditText_password'), 'password123', 0)
Mobile.hideKeyboard()
Mobile.tap(findTestObject('Android/Login/button_login'), 0)
Mobile.delay(1)
	// Verify error message for incorrect email
	String error_message_incorrect_email = Mobile.getText(findTestObject('Android/Login/alert_regis_login'), 0)
	Mobile.verifyEqual(error_message_incorrect_email, 'Wrong Email or Password')
	Mobile.takeScreenshot()
	Mobile.closeApplication()

// Case 3: Incorrect password
Mobile.startApplication(GlobalVariable.APK_PATH, true)
Mobile.setText(findTestObject('Android/Login/EditText_email'), 'asdasd6@yahoo.com', 0)
Mobile.setText(findTestObject('Android/Login/EditText_password'), 'wrongpassword', 0)
Mobile.hideKeyboard()
Mobile.tap(findTestObject('Android/Login/button_login'), 0)
Mobile.delay(1)
	// Verify error message for incorrect password
	error_message_incorrect_password = Mobile.getText(findTestObject('Android/Login/alert_regis_login'), 0)
	Mobile.verifyEqual(error_message_incorrect_password, 'Wrong Email or Password')
	Mobile.takeScreenshot()
	Mobile.closeApplication()

// Case 4: Incorrect email and password
Mobile.startApplication(GlobalVariable.APK_PATH, true)
Mobile.setText(findTestObject('Android/Login/EditText_email'), 'wrong@example.com', 0)
Mobile.setText(findTestObject('Android/Login/EditText_password'), 'wrongpassword', 0)
Mobile.hideKeyboard()
Mobile.tap(findTestObject('Android/Login/button_login'), 0)
Mobile.delay(1)
	// Verify error message for incorrect email and password
	error_message_incorrect_emailpassword = Mobile.getText(findTestObject('Android/Login/alert_regis_login'), 0)
	Mobile.verifyEqual(error_message_incorrect_emailpassword, 'Wrong Email or Password')
	Mobile.takeScreenshot()
	Mobile.closeApplication()