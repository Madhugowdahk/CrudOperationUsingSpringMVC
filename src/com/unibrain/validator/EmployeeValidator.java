package com.unibrain.validator;

import java.util.regex.Pattern;

import org.springframework.web.servlet.ModelAndView;

import com.unibrain.Entity.Employee;

public class EmployeeValidator {
	public int employeeValidator(Employee employee,ModelAndView modelandview) {
		int errorCount=0;
		if(employee.getEMP_name() == null || employee.getEMP_name().isEmpty()) {
			String error="Employee name is required."; 
			modelandview.addObject("errorEMP_name",error);
			modelandview.addObject("emp", employee);	
			errorCount++;
		}


		if(employee.getEMP_name() != null) {
			Pattern empnamePattern = Pattern.compile("^[a-zA-Z][\\sa-zA-Z]*{3,50}",Pattern.CASE_INSENSITIVE);
			if(!(empnamePattern.matcher(employee.getEMP_name()).matches())) {

				String error="Please enter valid employee name";
				modelandview.addObject("errorEMP_name",error);
				modelandview.addObject("emp", employee);	
				errorCount++;
			}
		}

		if(employee.getEMP_email() == null || employee.getEMP_email().isEmpty()) {
			String error="Employee id is required."; 
			modelandview.addObject("errorEMP_email",error);
			modelandview.addObject("emp", employee);			  
			errorCount++;
		}	
		if(employee.getEMP_email() != null) {
			Pattern emailIdPattern = Pattern.compile("[a-zA-Z0-9.@_-]+@[a-z0-9.-]+\\.[a-z]{2,3}$",Pattern.CASE_INSENSITIVE);
			if(!(emailIdPattern.matcher(employee.getEMP_email()).matches())) {
				String error="Please enter valid employee emailId ";
				modelandview.addObject("errorEMP_email",error);
				modelandview.addObject("emp", employee);	
				errorCount++;
			}
			//validation for MobileNumber
			if(employee.getEMP_phoneno() == null || employee.getEMP_phoneno().isEmpty()) {
				String error="Employee mobileNumber is required."; 
				modelandview.addObject("errorEMP_phoneno",error);
				modelandview.addObject("emp", employee);			  
				errorCount++;
			}
			if(employee.getEMP_phoneno() != null) {
				Pattern mobileNumberPattern = Pattern.compile("[0-9]{10}",Pattern.CASE_INSENSITIVE);
				if(!(mobileNumberPattern.matcher(employee.getEMP_phoneno()).matches())) {

					System.out.println("Insiddee..... mobile"+employee.getEMP_phoneno());
					String error="Please enter valid employee mobileNumber";
					modelandview.addObject("errorEMP_phoneno",error);
					modelandview.addObject("emp", employee);	
					errorCount++;
				}
			}


			//validation for address
			if(employee.getEMP_adress() == null || employee.getEMP_adress().isEmpty()) {
				String error="Employee address is required."; 
				modelandview.addObject("errorAddress",error);
				modelandview.addObject("emp", employee);			  
				errorCount++;
			}

			if(employee.getEMP_adress() != null) {
				Pattern addressPattern = Pattern.compile("[a-zA-Z0-9\\s#&,]{5,200}",Pattern.CASE_INSENSITIVE);
				if(!(addressPattern.matcher(employee.getEMP_adress()).matches())) {

					System.out.println("Insiddee.....");
					String error="Plese enter valid employee address";
					modelandview.addObject("errorEMP_adress",error);
					modelandview.addObject("emp", employee);	
					errorCount++;
				}
			}
			if (employee.getEMP_gender() != null) {
			    Pattern genderPattern = Pattern.compile("[MFT]", Pattern.CASE_INSENSITIVE);
			    if (!genderPattern.matcher(employee.getEMP_gender()).matches()) {
			        System.out.println("Invalid gender");
			        String error = "Please select a valid gender";
			        modelandview.addObject("errorEMP_gender", error);
			        modelandview.addObject("emp", employee);
			        errorCount++;
			    }
			}
			if (employee.getPassword() != null) {
			    Pattern passwordPattern = Pattern.compile("^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$");
			    if (!passwordPattern.matcher(employee.getPassword()).matches()) {
			        String error = "Please enter a valid password";
			        modelandview.addObject("errorPassword", error);
			        modelandview.addObject("emp", employee);
			        errorCount++;
			    }
			}

		
		}
		return errorCount;
	}

}

