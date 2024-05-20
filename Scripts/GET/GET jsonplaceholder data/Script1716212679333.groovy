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
import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.RequestObject
import com.kms.katalon.core.testobject.TestObjectProperty
import groovy.json.JsonSlurper
import static org.junit.Assert.*
import com.kms.katalon.core.logging.KeywordLogger

// Create a logger
KeywordLogger logger = new KeywordLogger()

// Function to create a dynamic request object
RequestObject dynamicRequestObject(String method, String url) {
    RequestObject request = new RequestObject()
    request.setRestUrl(url)
    request.setRestRequestMethod(method)
    return request
}

// Define URLs
String baseUrl = 'https://jsonplaceholder.typicode.com/posts'
String invalidUrl = 'https://jsonplaceholder.typicode.com/invalid_endpoint'
String firstPostUrl = 'https://jsonplaceholder.typicode.com/posts/1'
String lastPostUrl = 'https://jsonplaceholder.typicode.com/posts/100'

// Send GET Request
def response = WS.sendRequest(dynamicRequestObject('GET', baseUrl))

// Log and verify Status Code
logger.logInfo("GET Request to ${baseUrl} - Status Code: ${response.getStatusCode()}")
WS.verifyResponseStatusCode(response, 200)

// Parse JSON response
def jsonResponse = new JsonSlurper().parseText(response.getResponseText())

// Log response for debugging
logger.logInfo("Response JSON: ${jsonResponse}")

	// Positive Test Case: Verify data types for each post
	jsonResponse.each { post ->
	    assertTrue("userId is not an Integer", post.userId instanceof Integer)
	    assertTrue("id is not an Integer", post.id instanceof Integer)
	    assertTrue("title is not a String", post.title instanceof String)
	    assertTrue("body is not a String", post.body instanceof String)
	}

		// Negative Test Case: Verify response for an invalid endpoint
		def invalidResponse = WS.sendRequest(dynamicRequestObject('GET', invalidUrl))
		logger.logInfo("GET Request to ${invalidUrl} - Status Code: ${invalidResponse.getStatusCode()}")
		WS.verifyResponseStatusCode(invalidResponse, 404)

			// Additional Test Case: Verify response for first and last post IDs
			def firstPostResponse = WS.sendRequest(dynamicRequestObject('GET', firstPostUrl))
			logger.logInfo("GET Request to ${firstPostUrl} - Status Code: ${firstPostResponse.getStatusCode()}")
			WS.verifyResponseStatusCode(firstPostResponse, 200)
			
			def firstPostJson = new JsonSlurper().parseText(firstPostResponse.getResponseText())
			logger.logInfo("First Post JSON: ${firstPostJson}")
			assertTrue("userId is not an Integer", firstPostJson.userId instanceof Integer)
			assertEquals("First post id is incorrect", 1, firstPostJson.id)
			assertTrue("title is not a String", firstPostJson.title instanceof String)
			assertTrue("body is not a String", firstPostJson.body instanceof String)
			
			def lastPostResponse = WS.sendRequest(dynamicRequestObject('GET', lastPostUrl))
			logger.logInfo("GET Request to ${lastPostUrl} - Status Code: ${lastPostResponse.getStatusCode()}")
			WS.verifyResponseStatusCode(lastPostResponse, 200)
			
			def lastPostJson = new JsonSlurper().parseText(lastPostResponse.getResponseText())
			logger.logInfo("Last Post JSON: ${lastPostJson}")
			assertTrue("userId is not an Integer", lastPostJson.userId instanceof Integer)
			assertEquals("Last post id is incorrect", 100, lastPostJson.id)
			assertTrue("title is not a String", lastPostJson.title instanceof String)
			assertTrue("body is not a String", lastPostJson.body instanceof String)
