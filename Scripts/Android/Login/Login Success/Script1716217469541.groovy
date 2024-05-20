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

// Call register test case first so we can login with registered account
Mobile.callTestCase(findTestCase('Android/Register/Register Success'), [:], FailureHandling.STOP_ON_FAILURE)

// Use parameters for login
//Mobile.startApplication(GlobalVariable.APK_PATH, true)
Mobile.pressBack()
Mobile.setText(findTestObject('Android/Login/EditText_email'), GlobalVariable.REGISTER_EMAIL, 0)
Mobile.setText(findTestObject('Android/Login/EditText_password'), 'password123', 0)
Mobile.hideKeyboard()
Mobile.delay(1)

// Take SS after success login
Mobile.tap(findTestObject('Android/Login/button_login'), 0)

Mobile.delay(2)
Mobile.takeScreenshot()

// Verify data after success login with registered account
String verify_name = Mobile.getText(findTestObject('Android/Login/verify_name'), 0)
Mobile.verifyEqual(verify_name, GlobalVariable.REGISTER_NAME.toString())

String verify_email = Mobile.getText(findTestObject('Android/Login/verify_email'), 0)
Mobile.verifyEqual(verify_email, GlobalVariable.REGISTER_EMAIL.toString())

String verify_password = Mobile.getText(findTestObject('Android/Login/verify_password'), 0)
Mobile.verifyEqual(verify_password, 'password123')

Mobile.closeApplication()