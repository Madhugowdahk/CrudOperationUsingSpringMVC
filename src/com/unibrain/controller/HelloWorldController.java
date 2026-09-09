package com.unibrain.controller;

import com.unibrain.Entity.Employee;
import com.unibrain.Model.RequestData;
import com.unibrain.Model.ResponseData;
import com.unibrain.Model.Student;
import com.unibrain.service.EmployeeService;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.httpclient.URI;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

/**
 * @author UCS-BLR-05
 * in page below, there is a points how to hit these api methods from postman ,, you can check it from there.
 */
@Controller
@RequestMapping("/webservice")
public class HelloWorldController {

	@Autowired
	private EmployeeService EmployeeService_Imp;
    private static final String AUTH_TOKEN = "my-secret-token";

	
	@GetMapping("/hell")
	@ResponseBody
	public ResponseData helloWorld() {
	    System.out.println("HELLOOOOOOO");
	    ResponseData responseData = new ResponseData();
//	    responseData.setMessage("Hello, World!");
//	    responseData.setAge(22);
//	    responseData.setEmail("madhugowdahk70@gmail.com");
//	    responseData.setName("MadhuGowdaHk");
//	    responseData.setNumber(9964338671L);

	    // URL of the /helloo endpoint
	    String apiUrl = "http://localhost:8080/CRUD_operaationproject/webservice/helloo";

	    // Making a GET request to /helloo endpoint
	    ResponseEntity<ResponseData> responseEntity = sendGetRequest(apiUrl, ResponseData.class);

	    // Process the response if needed
	    ResponseData response = responseEntity.getBody();
	    System.out.println("Response from /helloo: " + response);

	    return response;
	}


	@GetMapping(path = "/helloo", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseData helloWorldGet(HttpServletResponse response,HttpServletRequest request) throws IOException {
	
		String token = request.getHeader("Authorization");
		System.out.println("token is:"+token);
		boolean validation = checkValidateAuthorization(token, response);
        ResponseData responseData = new ResponseData();
        if(validation) {
        responseData.setMessage("Hello, World!!");
        responseData.setAge(22);
        responseData.setEmail("madhugowdahk70@gmail.com....");
        responseData.setName("MadhuGowdaHk");
        responseData.setNumber(9964338671l);
        
        
        
        return responseData;
        }
		System.out.println("Oops..! Credential Validation Failed in Validate Method...");
		response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		responseData.setMessage("Oops..! Credential Validation Failed in Validate Method.");
		return responseData;

    }

	@GetMapping(path = "/student", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Student> getStudent() {
        Student student = new Student();
        student.setAge(20);
        student.setCity("Dubai");
        student.setEmail("email@example.com");
        student.setName("Chintu");
        student.setPhoneno(234567890);
        
        return ResponseEntity.status(HttpStatus.OK).body(student);
    }

	@GetMapping(path = "/hellooi/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseData helloWorldPost(@PathVariable("id") Integer id) {
	    ResponseData responseData = new ResponseData();
	    if(id==1) {
	    responseData.setMessage("Hello, World!");
	    responseData.setAge(25); // Set some random data
	    responseData.setEmail("example@example.com"); // Set some random data
	    responseData.setName("John Doe"); // Set some random data
	    responseData.setNumber(1234567890L); 
	    return responseData;
	    }
	    else
	    {
	    	 responseData.setMessage("Hello, java");
	 	    responseData.setAge(266665); // Set some random data
	 	    responseData.setEmail("example@example.com"); // Set some random data
	 	    responseData.setName("John cena"); // Set some random data
	 	    responseData.setNumber(1234567890L); 
	 	    return responseData;
	    }
        

	   
	}
	@RequestMapping(path = "/hel", produces = MediaType.APPLICATION_JSON_VALUE, method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public ResponseData receiveUserData() {
	    // Add logging statements
	    System.out.println("Received request in receiveUserData");
	    ResponseData responseData = new ResponseData();
	    responseData.setMessage("I LOVE YOU ");
	    responseData.setAge(12);
	    responseData.setEmail("ILOVEYOU@gmail.com");
	    responseData.setName("IHATEYOU");
	    responseData.setNumber(123);
	    return responseData;
	    // Rest of your method...
	}
	private static final Logger logger = LoggerFactory.getLogger(HelloWorldController.class);

	
	//In This Method,Assume I have given My API end points to send some json data or Response from xyz
	//In this method ,we know about how to receive json data
//	Open Postman and create a new request.
//	Set the request type to POST.
//	Enter the URL of your application followed by /processJsonData (e.g., http://localhost:8080/processJsonData).
//	In the Headers section, add a key-value pair with key Content-Type and value application/json.
//	In the Body section, select the raw option and choose JSON from the dropdown.
//	Enter a JSON object in the request body. For example:
//	json
//	Copy code
//	{
//	    "message": "Hello, world!"
//	}

    /**
     * @param requestData
     * @return
     */
    @PostMapping(path = "/processJsonData", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseData processJsonData(@RequestBody ResponseData requestData) {
    	try {
			JSONObject convertedJSONData = new JSONObject(requestData);
			String message = String.valueOf(convertedJSONData.getString("message"));
			System.out.println("message is :"+message);
		} catch (JSONException e) {
			e.printStackTrace();
		}
        ResponseData responseData = new ResponseData();
        responseData.setMessage("Data Received Successfully!");
        System.out.println(responseData.getMessage());
        return responseData;
    }



    private <T> ResponseEntity<T> sendGetRequest(String apiUrl, Class<T> responseType) {
        RestTemplate restTemplate = new RestTemplate();
        try {
        	
          
        	return restTemplate.exchange(apiUrl, HttpMethod.GET, null, responseType);
        	
        } catch (HttpClientErrorException e) {
            System.out.println("Client error: " + e.getStatusCode());
            System.out.println("Response body: " + e.getResponseBodyAsString());
        } catch (HttpServerErrorException e) {
            System.out.println("Server error: " + e.getStatusCode());
            System.out.println("Response body: " + e.getResponseBodyAsString());
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
        }

        // Return a default or error response if needed
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
    // to call String 
    @GetMapping("/hel")
    @ResponseBody
    public String helloWorld1() {
        RestTemplate restTemplate = new RestTemplate();

        // URL of the /hello endpoint
        String apiUrl = "http://localhost:8080/PKIPROJECT/hello";
        // Making a GET request to /hello endpoint
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(apiUrl, String.class);

        // Extracting response body
        String responseBody = responseEntity.getBody();

        // Printing the response body
        System.out.println("Response from /hello: " + responseBody);
        return responseBody;
    }
//	@GetMapping(path = "/hello", produces = MediaType.APPLICATION_JSON_VALUE)
//    @ResponseBody
//    public String get() {  
//        return "THINK LIKE A BOSS";
//    }
    @GetMapping("/checkNumber")
    @ResponseBody
    public String checkNumber() {
        // Call the API to check if the number is 1
        boolean isNumberOne = isNumberOne(); // Assuming this method calls the API and returns the result

        // Return true or false as plain text
        return String.valueOf(isNumberOne);
    }

    private boolean isNumberOne() {
        // Call your API here to check if the number is 1
        // For simplicity, we'll return true for the sake of this example
        return true;
    }
    
	private boolean checkValidateAuthorization(String token, HttpServletResponse response) throws IOException {

		if (token != null && token.toLowerCase().startsWith("basic")) {
			String base64Credentials = token.substring("Basic".length()).trim();
			byte[] credDecoded = Base64.getDecoder().decode(base64Credentials);
			String credentials = new String(credDecoded, StandardCharsets.UTF_8);
			final String[] authentic = credentials.split(":");
			String receivedUsername = authentic[0];
			String receivedPassword = authentic[1];
			String neftUserName ="madhu";
			String neftPassword ="admin@123";

			if (neftUserName.equals(receivedUsername) && neftPassword.equals(receivedPassword)) {
				return true;
			} else {
				return false;
			}
		}
		return false;
	}
	
    @GetMapping("/status/{paymentNumber}")
    @ResponseBody
    public ResponseEntity<String> getPaymentStatus(
            @PathVariable("paymentNumber") String paymentNumber,
            HttpServletRequest request,
            HttpServletResponse response) {
    	System.out.println("the value becomes old now...");

        // Read Authorization header
        String authHeader = request.getHeader("Authorization");
        
        System.out.println("authHeader:"+authHeader);

        if (authHeader == null) {
            return new ResponseEntity<>("Unauthorized - Invalid token", HttpStatus.UNAUTHORIZED);
        }

        // Simulate payment check (dummy example)
        boolean paymentSuccess = checkPaymentStatus(paymentNumber);

        if (paymentSuccess) {
            return new ResponseEntity<>("Payment Success", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Payment Failed", HttpStatus.OK);
        }
    }

    // Dummy logic to simulate payment success/failure
    private boolean checkPaymentStatus(String paymentNumber) {
        // Example: mark even numbers as success
        return Integer.parseInt(paymentNumber) % 2 == 0;
    }



}
