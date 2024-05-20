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

// Start app and perform negative case for empty field
Mobile.startApplication('C:\\Users\\Harris\\Desktop\\pintu\\PINTUtests\\Sample Android App - Login Tes_4.0_APKPure.apk', 
    true)
Mobile.tap(findTestObject('Object Repository/Android/Register/TextView_no account yet create one'), 0)

	// Empty fullname and verify
	Mobile.tap(findTestObject('Android/Register/button_register'), 0)
	String alert_fullname = Mobile.getText(findTestObject('Object Repository/Android/Register_Empty Field/alert_Enter Full Name'), 0)
	Mobile.verifyEqual(alert_fullname, 'Enter Full Name')
	
	
		// Input invalid email format
		Mobile.setText(findTestObject('Android/Register/EditText_name'), 'asdasd', 0)
		Mobile.hideKeyboard()
		Mobile.setText(findTestObject('Android/Register/EditText_email'), 'asdasd6', 0)
		
	// Empty email and verify
	Mobile.hideKeyboard()
	Mobile.tap(findTestObject('Android/Register/button_register'), 0)
	String alert_email_invalid = Mobile.getText(findTestObject('Object Repository/Android/Register_Empty Field/alert_Enter Valid Email'), 0)
	Mobile.verifyEqual(alert_email_invalid, 'Enter Valid Email')
	String alert_email = Mobile.getText(findTestObject('Object Repository/Android/Register_Empty Field/alert_Enter Valid Email'), 0)
	Mobile.verifyEqual(alert_email, 'Enter Valid Email')
	
	// Empty password and verify
	Mobile.setText(findTestObject('Android/Register/EditText_email'), 'asdasd6@yahoo.com', 0)
	Mobile.hideKeyboard()
	Mobile.tap(findTestObject('Android/Register/button_register'), 0)
	String alert_password = Mobile.getText(findTestObject('Object Repository/Android/Register_Empty Field/alert_Enter Password'), 0)
	Mobile.verifyEqual(alert_password, 'Enter Password')
	
	// Empty confirm password and verify
	Mobile.setText(findTestObject('Android/Register/EditText_password'), 'password', 0)
	Mobile.hideKeyboard()
	Mobile.tap(findTestObject('Android/Register/button_register'), 0)
	String alert_confirmPassword = Mobile.getText(findTestObject('Object Repository/Android/Register_Empty Field/alert_Password Does Not Matches'), 0)
	Mobile.verifyEqual(alert_confirmPassword, 'Password Does Not Matches')
	
		// Input password and confirm password that doesn't match
		Mobile.setText(findTestObject('Android/Register/EditText_password'), 'password', 0)
		Mobile.setText(findTestObject('Android/Register/EditText_confirmPassword'), 'passwordpassword', 0)
		String alert_confirmPasswordNotMatch = Mobile.getText(findTestObject('Object Repository/Android/Register_Empty Field/alert_Password Does Not Matches'), 0)
		Mobile.verifyEqual(alert_confirmPasswordNotMatch, 'Password Does Not Matches')
		
	Mobile.closeApplication()
