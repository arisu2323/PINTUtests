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

// Generate random number
def randomNumber = new Random().nextInt(10000)
def name = 'harris' + randomNumber
def email = 'harris'+randomNumber+'@yahoo.com'

// Perform Registration steps
Mobile.startApplication(GlobalVariable.APK_PATH, true)
Mobile.tap(findTestObject('Object Repository/Android/Register/TextView_no account yet create one'), 0)
Mobile.setText(findTestObject('Object Repository/Android/Register/EditText_name'), name, 0)
Mobile.setText(findTestObject('Object Repository/Android/Register/EditText_email'), email, 0)
Mobile.setText(findTestObject('Object Repository/Android/Register/EditText_password'), 'password123', 0)
Mobile.delay(1)
Mobile.setText(findTestObject('Object Repository/Android/Register/EditText_confirmPassword'), 'password123', 0)
Mobile.hideKeyboard()
Mobile.delay(1)

Mobile.tap(findTestObject('Object Repository/Android/Register/button_register'), 0)

// Take screenshot for registration successfull message since it cannot be inspected
Mobile.delay(1)
Mobile.takeScreenshot()
//Mobile.closeApplication()

// Set parameters as global variables for use in other test cases
GlobalVariable.REGISTER_NAME = name
GlobalVariable.REGISTER_EMAIL = email