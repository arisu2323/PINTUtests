import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable as GlobalVariable
import com.kms.katalon.core.logging.KeywordLogger

// Create a logger
KeywordLogger logger = new KeywordLogger()

// Define request body
def requestBody = '''
{
    "title": "recommendation",
    "body": "motorcycle",
    "userId": 12
}
'''

// Send POST request with request body
def postRequest = findTestObject('POST/POST jsonplaceholder')
postRequest.setRestRequestMethod('POST')
postRequest.setHttpBody(requestBody)

response = WS.sendRequest(postRequest)

// Validate status code
def statusCode = response.getStatusCode()
logger.logInfo("Status code: " + statusCode)

// Print response to log
logger.logInfo("Response: " + response.getResponseBodyContent())

	//
	// Negative test case
	//
	// Define invalid request body (missing userId) expected = throw error, but looks like it will always response with 201 created even with empty body
	def invalidRequestBody = '''
	{
	    "title": "recommendation",
	    "body": "motorcycle"
	}
	'''
	
	// Send POST request with invalid request body
	def postRequestInvalid = findTestObject('POST/POST jsonplaceholder')
	postRequestInvalid.setRestRequestMethod('POST')
	postRequestInvalid.setHttpBody(invalidRequestBody)
	
	responseInvalid = WS.sendRequest(postRequestInvalid)
	
	// Validate status code
	def statusCodeInvalid = responseInvalid.getStatusCode()
	logger.logInfo("Status code for negative test case: " + statusCodeInvalid)
	
	// Print response to log
	logger.logInfo("Response for negative test case: " + responseInvalid.getResponseBodyContent())
	
		//
		// Define large request body
		//
		def largeRequestBody = '''
		{
		    "title": "recommendation",
		    "body": "Lorem ipsum dolor sit amet, consectetur adipiscing elit. 
					Sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. 
					Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. 
					Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. 
					Excepteur sint occaecat cupidatat non proident, 
					sunt in culpa qui officia deserunt mollit anim id est laborum.",
		    "userId": 12
		}
		'''
		
		// Send POST request with large request body
		def postRequestLarge = findTestObject('POST/POST jsonplaceholder')
		postRequestLarge.setRestRequestMethod('POST')
		postRequestLarge.setHttpBody(largeRequestBody)
		
		responseLarge = WS.sendRequest(postRequestLarge)
		
		// Validate status code
		def statusCodeLarge = responseLarge.getStatusCode()
		logger.logInfo("Status code for boundary test case: " + statusCodeLarge)
		
		// Print response to log
		logger.logInfo("Response for boundary test case: " + responseLarge.getResponseBodyContent())
