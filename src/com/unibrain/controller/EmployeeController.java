package com.unibrain.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import com.unibrain.Entity.GenderEnum;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.regex.Pattern;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.persistence.criteria.CriteriaBuilder.In;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import com.unibrain.controller.*;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.itextpdf.text.pdf.codec.Base64.InputStream;
import com.itextpdf.text.pdf.codec.Base64.OutputStream;
import com.unibrain.Entity.Employee;
import com.unibrain.service.EmployeeService;

import com.unibrain.validator.EmployeeValidator;
import com.unibrain.validator.PBKDF2HMACSHA512;

import io.undertow.server.handlers.resource.Resource;
import net.sf.jasperreports.repo.InputStreamResource;
import okhttp3.internal.http.HttpHeaders;
import sun.security.util.Password;

@Controller
public class EmployeeController {

	@Autowired
	private PBKDF2HMACSHA512 passwordHashingUtility;
	@Autowired
	private PBKDF2HMACSHA512 pbkdf2hmacsha512;
	@Autowired
	private EmployeeService EmployeeService_Imp;
	private int start;

	@RequestMapping("/")
	public ModelAndView employeeSummary(HttpServletRequest req) {
		
		
		String page = null; 
		String pageNumber = req.getParameter("page");
		System.out.println("pageNumber"+pageNumber);

		if(pageNumber!=null) 
		{ page = pageNumber;
		} 
		int total = 4; 
		int start = 1;
		if(page != null)
		{
			start = (Integer.parseInt(page)-1)*total+1; 
			} 
		else
		{
			page= "1"; 
		}
		 
		
		ModelAndView modelandview=new ModelAndView(); 
		List<Employee> listEmp1 = EmployeeService_Imp.loadEmp(start); 
		System.err.println("listEmp1::"+listEmp1);
		
		Long employeeCount=EmployeeService_Imp.employeeCount();
		System.err.println("employeeCount::"+employeeCount);
		
		Map<String ,Object> paginationDetails=new Pagination().defaultMethod(Integer.parseInt(page),employeeCount);
		System.err.println("paginationDetails::"+paginationDetails);
		
		modelandview.addObject("paginationDetails",paginationDetails); 
		modelandview.setViewName("EmployeLogin");
		//List<Employee> listEmp = EmployeeService_Imp.loadEmp();
		System.err.println("listEmp:::::" + listEmp1);
		
		modelandview.addObject("Employee", listEmp1);
		return modelandview;
	}
	/*
	 * @RequestMapping("/getEmployee") public ModelAndView getEmploye() {
	 * ModelAndView modelandview=new ModelAndView();
	 * modelandview.setViewName("DATA_RETRIEVE"); return modelandview; }
	 */
//	@PostMapping("/processData")
//	  @ResponseBody
//	  public Map<String, Object> processData(@RequestBody Map<String, Object> data) {
//	    // Process the data received from the client
//	    // You can perform any business logic and return a response
//
//	    Map<String, Object> response = new HashMap<String, Object>();
//	    response.put("message", "Data processed successfully");
//	    System.out.println("response is: "+response);
//	    return response;
//	  }
	
	@GetMapping(value = "/view/{id}")
	public ModelAndView view(@PathVariable("id") Integer id, RedirectAttributes attributes) {
		
	    Employee emp = EmployeeService_Imp.getEmployee(id);
	    ModelAndView modelAndView = new ModelAndView();
	    modelAndView.addObject("emp", emp);
	    modelAndView.setViewName("image_view");
	    return modelAndView;
	}
@RequestMapping("/login")
	public ModelAndView login()
	{
		ModelAndView modelAndView=new ModelAndView();
		Employee employee = new Employee();
		modelAndView.addObject("emp",employee);
		modelAndView.setViewName("login");
		return modelAndView;
	}
	@RequestMapping("/newEmployee")
	public ModelAndView newEmployee()
	{				
		ModelAndView modelandview=new ModelAndView();
		Employee employee = new Employee();
		modelandview.addObject("emp",employee);
		modelandview.setViewName("newsignup");	
		
		return modelandview;	
	}
	@RequestMapping("/newjsp")
	public ModelAndView example()
	{				
		System.out.println("new jsp");
		ModelAndView modelandview=new ModelAndView();
		modelandview.setViewName("example");	
		
		return modelandview;	
	}

	@RequestMapping("/SearchEmployeeDetails")
	public ModelAndView findAll()
	{
		ModelAndView modelAndView=new ModelAndView();
		modelAndView.setViewName("update");
		return modelAndView;
	}

	@SuppressWarnings("unused")
	@RequestMapping(value="/saveEmployee",headers = "Content-Type=multipart/form-data" ,method = {RequestMethod.POST })
	public ModelAndView saveEmployee( @ModelAttribute("employee")  Employee employee,
			@RequestParam("DD_filename")  MultipartFile file,
			HttpServletRequest req,Model model,HttpSession session ) {
             System.out.println("save method called");
		     ModelAndView modelandview = new ModelAndView();
		     System.out.println("file.getOriginalFilename()==="+file.getOriginalFilename());

		     if (file.isEmpty()) {
		    	 model.addAttribute("error", "Please select a file to upload.");
		    	 modelandview.setViewName("newsignup");
		    	 return modelandview;
		     }
		   try {
	            String uploadDir = session.getServletContext().getRealPath("/") + "WEB-INF" + File.separator + "resources" + File.separator + "images";
	            String fileName = StringUtils.cleanPath(file.getOriginalFilename());
	            String filePath = uploadDir + File.separator + fileName;
	            System.out.println("filePath===="+filePath);
	            File directory = new File(uploadDir);
	            if (!directory.exists()) {
	                directory.mkdirs();
	            }
	           
	            
	            File destination = new File(filePath);
	            file.transferTo(destination);
	            System.out.println("File uploaded successfully");
	            System.out.println("destination is::"+destination);

	            model.addAttribute("msg", "Successfully uploaded");
	            model.addAttribute("filename", fileName);
			
			  if (!(file.getContentType().equals("image/jpeg") ||
			  file.getContentType().equals("image/png"))) {
			  System.out.println("Invalid file type (not JPG or PNG)");
			  model.addAttribute("error", "Invalid file type (not JPG or PNG)");
			  modelandview.setViewName("newsignup"); return modelandview; }
			  
			  if (file.getSize() > 5 * 1024 * 1024) { // 5 MB
			  System.out.println("File size exceeds 5 MB");
			  model.addAttribute("error1",
			  "File size exceeds 5 MB"); 
			  modelandview.setViewName("newsignup");
			  return  modelandview;
			  }
			 
	    		employee.setdocument_name(fileName);
	        
	       } catch (IOException e) {
	            e.printStackTrace();
            model.addAttribute("error", "Failed to upload the file. Please try again.");
            System.out.println("Uploading error");
           modelandview.setViewName("newsignup");
           return modelandview;
	       }
	
        String password=req.getParameter("password");
		    String securepassword=pbkdf2hmacsha512.getEncodedHash(password);

		EmployeeValidator employeeValidator = new EmployeeValidator();
		int errorCount  = employeeValidator.employeeValidator(employee, modelandview);

		if(errorCount > 0) {
			modelandview.setViewName("newsignup");		 
			return modelandview;
		}	
		if(employee.getEMP_id()!= null) 
		{
			Boolean isExist = EmployeeService_Imp.isDataExistForEmpId(employee.getEMP_id());
			System.err.println("isExist======="+isExist);

			if (isExist) {
				String error = "Employee id is already exist";
				modelandview.addObject("errorEMP_id", error);
				modelandview.addObject("emp", employee);
				
				modelandview.setViewName("error");
				return modelandview;
			} else {
				
				System.out.println("saved succesfully");
				employee.setPassword(securepassword);
				System.out.println("employee:"+employee);
				boolean add = EmployeeService_Imp.saveEmployee(employee);
			}
		}
		return  new ModelAndView("redirect:/");

	}

	@GetMapping(value = "/modify/{id}")
	public ModelAndView modify(@PathVariable Integer id,RedirectAttributes redirectAttributes) {
		System.err.println("EMPLOYEE:::::" + id);
		Employee employee = new Employee();
		employee.setEMP_id(id);

		ModelAndView modelandview = new ModelAndView();
		// EmployeeService_Imp.updateEmployee(employee);
		List<Employee> listEmp = EmployeeService_Imp.loadEmp(start);
		 redirectAttributes.addFlashAttribute("success1", "Updated successfully");
		System.err.println("listEmp:::::" + listEmp);
		modelandview.addObject("Employee", listEmp);

		System.out.println("updated succesflly");
		// return new ModelAndView("update");
		modelandview.setViewName("EmployeLogin");
		return modelandview;
	}

	@PostMapping(value = "/updateEmployee")
	public ModelAndView editEmployee(@ModelAttribute Employee employee, HttpServletRequest req) {

		ModelAndView modelandview = new ModelAndView();
		EmployeeValidator employeeValidator = new EmployeeValidator();
		int errorCount  = employeeValidator.employeeValidator(employee, modelandview);

		if(errorCount > 0) {
			modelandview.setViewName("EmployeLogin");		 
			return modelandview;
		}	

		EmployeeService_Imp.updateEmployee(employee);
		List<Employee> listEmp = EmployeeService_Imp.loadEmp(start);
		System.err.println("listEmp:::::" + listEmp);
		modelandview.addObject("Employee", listEmp);
		System.out.println("updated succesfullyy");
		modelandview.setViewName("EmployeLogin");
		return modelandview;
	}

	@RequestMapping(value = "/getemployee/{id}")
	public ModelAndView getEmployee(@PathVariable Integer id, RedirectAttributes attributes) {
		Employee emp = EmployeeService_Imp.getEmployee(id);
		
		ModelAndView modelandview = new ModelAndView();
		System.out.println("employee details......................" + emp);
		// attributes.addFlashAttribute("emp", emp);
		modelandview.addObject("emp", emp);
		
		modelandview.setViewName("Employesummary");
		return modelandview;
	}

	@GetMapping(value = "/delete/{id}")
	public String deleteEmployee(@PathVariable String id,RedirectAttributes redirectAttributes) {

		System.err.println("Deleted succesfully");
	    redirectAttributes.addFlashAttribute("success", "Deleted successfully");
		EmployeeService_Imp.deleteEmployee(Integer.parseInt(id));
		return "redirect:/";
	}
	@SuppressWarnings("unchecked")
	@PostMapping(value = {"/search","/search/{page}"})
	public ModelAndView search(@ModelAttribute Employee employee,HttpServletRequest req, HttpSession session,
			@PathVariable Optional<String> page) {
		System.out.println("search method is called");
		Integer pg = null;
		int total = 4;
		int start = 1;
		if(page.isPresent()) {
			start = (Integer.parseInt(page.get())-1)*total+1; 
			pg = Integer.parseInt(page.get());
		}else {
			session.setAttribute("searchMap", null);
			pg = 1;
		}	

		
		ModelAndView modelandview=new ModelAndView();

		String  EMP_id= req.getParameter("EMP_id");

		String EMP_name = req.getParameter("EMP_name");

		System.err.println("EMP "+EMP_id+"==="+EMP_name);

		String EMP_email = req.getParameter("EMP_email").trim();
		System.err.println("EMP"+EMP_email);

		String EMP_phoneno = req.getParameter("EMP_phoneno").trim();

		String EMP_adress = req.getParameter("EMP_adress").trim();
		String EMP_gender = req.getParameter("EMP_gender").trim();
		String sortby = req.getParameter("sortby");
    	String sortorder = req.getParameter("sortorder");
		

		HashMap<String,String> searchMap = new HashMap<String, String>();		
searchMap = session.getAttribute("searchMap") != null ? (HashMap<String, String>) session.getAttribute("searchMap") : searchMap;

		if(EMP_id == null && EMP_name == null  && EMP_email == null && EMP_phoneno == null && EMP_adress == null ){
			searchMap =  (HashMap<String, String>) session.getAttribute("searchMap");
			
		}
		
		if( EMP_id!= null && !EMP_id.isEmpty()) {
			searchMap.put("EMP_id", EMP_id);
		}
		if(EMP_name != null && !EMP_name.isEmpty()) {
			searchMap.put("EMP_name", EMP_name);
		}
		if(EMP_email != null && !EMP_email.isEmpty()) {
			searchMap.put("EMP_email", EMP_email);
		}

		if(EMP_phoneno!= null && !EMP_phoneno.isEmpty()) {
			searchMap.put("EMP_phoneno", EMP_phoneno);
		}
		if(EMP_adress != null && !EMP_adress.isEmpty()) {
			searchMap.put("EMP_adress", EMP_adress);
		}    
		if(EMP_gender != null && !EMP_gender.isEmpty()) {
			searchMap.put("EMP_gender", EMP_gender);
		}   

	    if(sortby != null && !sortby.isEmpty()) {
		searchMap.put("sortby", sortby);
	    }

	    if(sortorder != null && !sortorder.isEmpty()) {
		searchMap.put("sortorder", sortorder);
	    }

		System.out.println("searchMap before=="+searchMap.toString());

		session.setAttribute("searchMap", searchMap);
		List<Employee> listEmployees = EmployeeService_Imp.serach(searchMap,start);
		long employeeCount =EmployeeService_Imp.countForSearch(searchMap);
		
		Map<String ,Object> paginationDetails=new Pagination().defaultMethod(pg,employeeCount);
	   modelandview.addObject("paginationDetails",paginationDetails); 
		System.err.println("listEmployees=="+listEmployees);
		
		modelandview.addObject("id", EMP_id);
		modelandview.addObject("employee",listEmployees);
		modelandview.addObject("isPostMethod", true);
		modelandview.addObject("searchUrl", "http://localhost:8080/CRUD_operaationproject/search");
		modelandview.setViewName("update");
		return modelandview;
	}
	@SuppressWarnings("unchecked")
	@PostMapping(value = {"/search1","/search/{page}"})
	public ModelAndView search1(@ModelAttribute Employee employee,HttpServletRequest req, HttpSession session,
			@PathVariable Optional<String> page) {
		System.out.println("search1 method is called");
		Integer pg = null;
		int total = 4;
		int start = 1;
		if(page.isPresent()) {
			start = (Integer.parseInt(page.get())-1)*total+1; 
			pg = Integer.parseInt(page.get());
		}else {
			session.setAttribute("searchMap", null);
			pg = 1;
		}	

		
		ModelAndView modelandview=new ModelAndView();

		String  EMP_id= req.getParameter("EMP_id");

		String EMP_name = req.getParameter("EMP_name");

		System.err.println("EMP "+EMP_id+"==="+EMP_name);

		String EMP_email = req.getParameter("EMP_email").trim();
		System.err.println("EMP"+EMP_email);

		String EMP_phoneno = req.getParameter("EMP_phoneno").trim();

		String EMP_adress = req.getParameter("EMP_adress").trim();
		String EMP_gender = req.getParameter("EMP_gender").trim();
		String sortby = req.getParameter("sortby");
    	String sortorder = req.getParameter("sortorder");
		

		HashMap<String,String> searchMap = new HashMap<String, String>();		
		searchMap = session.getAttribute("searchMap") != null ? (HashMap<String, String>) session.getAttribute("searchMap") : searchMap;

		if(EMP_id == null && EMP_name == null  && EMP_email == null && EMP_phoneno == null && EMP_adress == null ){
			searchMap =  (HashMap<String, String>) session.getAttribute("searchMap");
			
		}
		
		if( EMP_id!= null && !EMP_id.isEmpty()) {
			searchMap.put("EMP_id", EMP_id);
		}
		if(EMP_name != null && !EMP_name.isEmpty()) {
			searchMap.put("EMP_name", EMP_name);
		}
		if(EMP_email != null && !EMP_email.isEmpty()) {
			searchMap.put("EMP_email", EMP_email);
		}

		if(EMP_phoneno!= null && !EMP_phoneno.isEmpty()) {
			searchMap.put("EMP_phoneno", EMP_phoneno);
		}
		if(EMP_adress != null && !EMP_adress.isEmpty()) {
			searchMap.put("EMP_adress", EMP_adress);
		}    
		if(EMP_gender != null && !EMP_gender.isEmpty()) {
			searchMap.put("EMP_gender", EMP_gender);
		}   

	    if(sortby != null && !sortby.isEmpty()) {
		searchMap.put("sortby", sortby);
	    }

	    if(sortorder != null && !sortorder.isEmpty()) {
		searchMap.put("sortorder", sortorder);
	    }

		System.out.println("searchMap before=="+searchMap.toString());

		session.setAttribute("searchMap", searchMap);
		List<Employee> listEmployees = EmployeeService_Imp.search(searchMap);
		
		
		 
		System.err.println("listEmployees=="+listEmployees);
		
		modelandview.addObject("id", EMP_id);
		modelandview.addObject("employee",listEmployees);
		modelandview.addObject("isPostMethod", true);
		modelandview.addObject("searchUrl", "http://localhost:8080/CRUD_operaationproject/search1");
		modelandview.setViewName("update");
		return modelandview;
	}

	@RequestMapping("usingazax")
	public String Azax()
	{
		return "usingAzax";
	}
	@RequestMapping(value = "/getemployee1/{id}")
	public ModelAndView getEmployee1(@PathVariable Integer id, RedirectAttributes attributes) {
		System.out.println("id is "+id);
		Employee emp = EmployeeService_Imp.getEmployee1(id);
		System.out.println("yeah it is working+" );
		ModelAndView modelandview = new ModelAndView();
		System.out.println("employee details  "+ emp);
	
		modelandview.addObject("emp", emp);
		
		modelandview.setViewName("usingAzax");
		return modelandview;
	}
	
	@ModelAttribute
	public void setAttributes(Model model, HttpServletRequest request) {
		/*
		 * GenderEnum[] genderEnums = GenderEnum.values(); List<GenderEnum>
		 * genderEnumList = Arrays.asList(genderEnums); model.addAttribute("genderEnum",
		 * genderEnumList);
		 */
		model.addAttribute("genderEnum",GenderEnum.values());
	}
	//to download the image 
	
	@GetMapping(value = "/download/{id}")
	public void downloadFile(@PathVariable Integer id, HttpServletResponse response) {
	    Employee emp = EmployeeService_Imp.getEmployee(id);
	    String filename = emp.getdocument_name();
	    String filePath = "F:/Practice/.metadata/.plugins/org.eclipse.wst.server.core/tmp1/wtpwebapps/CRUD_operaationproject/WEB-INF/resources/images/" + filename;
	    File file = new File(filePath);

	    if (file.exists() && file.isFile()) {
	        String contentType = getContentTypeFromFileName(filename);
	        response.setContentType(contentType);
	        response.setHeader("Content-Disposition", "attachment; filename=\"" + filename + "\"");

	        try (FileInputStream inputStream = new FileInputStream(file);
	             ServletOutputStream outputStream = response.getOutputStream()) {

	            byte[] buffer = new byte[1024];
	            int bytesRead;
	            while ((bytesRead = inputStream.read(buffer)) != -1) {
	                outputStream.write(buffer, 0, bytesRead);
	            }
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    } else {
	        response.setStatus(HttpServletResponse.SC_NOT_FOUND);
	    }
	}

	private String getContentTypeFromFileName(String fileName) {
	    if (fileName.endsWith(".jpg") || fileName.endsWith(".jpeg")) {
	        return "image/jpeg";
	    } else if (fileName.endsWith(".png")) {
	        return "image/png";
	    } else if (fileName.endsWith(".gif")) {
	        return "image/gif";
	    } else if (fileName.endsWith(".bmp")) {
	        return "image/bmp";
	    } else if (fileName.endsWith(".pdf")) {
	        return "application/pdf";
	    } else {
	        return "application/octet-stream";
	    }
	}


	
	@RequestMapping(value = "/validatelogin",method = {RequestMethod.POST})
	public ModelAndView validate( @ModelAttribute("employee") Employee employee,HttpServletRequest request,Model model,HttpSession session)
	{
		
		ModelAndView view=new ModelAndView();
		view.setViewName("success");
		String password=request.getParameter("password");
		String confirmpassword=request.getParameter("password1");
		
		Integer id=employee.getEMP_id();
		if(employee.getEMP_id()!= null) 
		{
			Boolean isExist = EmployeeService_Imp.isDataExistForEmpId(employee.getEMP_id());
			System.err.println("isExist======="+isExist);

			if (!isExist) {
				String error = "Employee id is doesn't exist";
				view.addObject("errorEMP_id", error);
				view.addObject("emp", employee);
				
				view.setViewName("login");
				return view;
			}
		
			
			if(!password.equals(confirmpassword))
			{
				String error=" Passwords do not match. Please try again. ";
				view.addObject("error",error);
				view.setViewName("login");
				return view;
			}
		  System.out.println("inside validate+"   );
			Employee emp = EmployeeService_Imp.getEmployee(id);
			System.err.println(emp.getPassword());
			String storedpassword=emp.getPassword();
			
			 String[] params = storedpassword.split(":::");
			    byte[] salt = passwordHashingUtility.decodeBase64(params[0].trim());
			    byte[] storedPasswordHash = passwordHashingUtility.decodeBase64(params[1].trim());
			 // Convert the entered password to a character array
			    char[] enteredPasswordChars =password.toCharArray();

			    // Generate the hash of the entered password using the retrieved salt
			    byte[] enteredPasswordHash = passwordHashingUtility.generateHash(enteredPasswordChars, salt);
			    
			
			    boolean passwordsMatch = passwordHashingUtility.validatePassword(enteredPasswordChars, storedpassword);
			    if (passwordsMatch) {
			        model.addAttribute("successmsg", "Passwords matched successfully!");
			    	return view;
			    } else {
			        // Passwords do not match
			    	String error = "Passwords do not match. Please try again";
					view.addObject("error", error);
					view.addObject("emp", employee);
					
					view.setViewName("login");
					return view;
			    }
		
		
		}
		return view;

}
	@PostMapping("/startAuctionItem")
    @ResponseBody
    public String callStartMethod(@RequestParam String itemId, @RequestParam String dataSourceName) {
        System.out.println("Welcome Home,,,,,,,,,,,,,,,");
        System.out.println("Inside this method:" + itemId);

        // Process the itemId and dataSourceName
        String responseData = "Data for itemId: " + itemId + ", dataSourceName: " + dataSourceName;

        return responseData;
    }
	
	}



 



