package com.unibrain.Dao;


import java.sql.PreparedStatement;


import java.util.HashMap;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.unibrain.Entity.Employee;

@Repository
public class EmployeeDaoImp implements EmployeeDao {

	@Autowired
	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}




	@Override
	public boolean saveEmployee(Employee employee) {
		boolean save =false;
		try(Session session = sessionFactory.openSession()){

			Transaction tr = session.beginTransaction();
			session.save(employee);
			save =true;
			tr.commit();
		}
		return false;
	}



	public void deleteEmployee(Integer id) {
		try(Session session = sessionFactory.openSession()){
			Transaction transaction = session.beginTransaction();
			session.createQuery("DELETE FROM Employee WHERE id = " + id).executeUpdate();
			transaction.commit();
		}

	}



	@Override
	public Employee updateEmployee(Employee employee) {
		try(Session session = sessionFactory.openSession()){
			Transaction tx = session.beginTransaction();
			Employee emp = session.load(Employee.class,employee.getEMP_id());
			emp.setEMP_id(employee.getEMP_id());
			emp.setEMP_email(employee.getEMP_email());
			emp.setEMP_name(employee.getEMP_name());
			emp.setEMP_phoneno(employee.getEMP_phoneno());
			emp.setEMP_adress(employee.getEMP_adress());
			emp.setEMP_gender(employee.getEMP_gender());
			emp.setPassword(employee.getPassword());
			session.update(emp);
			tx.commit();
		}
		return employee;
	}

	@Override
	public Employee getEmployee(Integer id) {
		try(Session session = sessionFactory.openSession()){
			Employee emp = (Employee) session.createQuery("FROM Employee WHERE id = " + id).getSingleResult();
			//emp=(Employee)session.get(Employee.class, 1);
			return emp;
		}	
	}

	/*
	 * public List<Employee> loadEmp() { try(Session session =
	 * sessionFactory.openSession()){
	 * 
	 * Query<Employee> query =
	 * session.createQuery("from Employee order by id desc",Employee.class);
	 * 
	 * return query.getResultList(); } }
	 */

	@SuppressWarnings("unchecked")
	public List<Employee> serach(HashMap<String, String> searchMap, int start) {
		try(Session session = sessionFactory.openSession()){

			StringBuilder sb=new StringBuilder("from Employee where id > 0");	


			/*
			 * Integer EMP_id = Integer.parseInt(searchMap.get("EMP_id")) ; String EMP_name=
			 * searchMap.get("EMP_name") ; String EMP_email=searchMap.get("EMP_email");
			 * System.err.println("EMP"+EMP_email); String
			 * EMP_phoneno=searchMap.get("EMP_phoneno");
			 * 
			 * String EMP_adress=searchMap.get("EMP_adress"); String sortby
			 * =searchMap.get("sortby"); String sortorder = searchMap.get("sortorder");
			 */
			
			  Integer EMP_id =searchMap.get("EMP_id") != null ?Integer.parseInt(searchMap.get("EMP_id")) : null; 
			  String EMP_name= searchMap.get("EMP_name") != null ?searchMap.get("EMP_name") : null; 
			  String  EMP_email=searchMap.get("EMP_email")!=null?searchMap.get("EMP_email") : null;
			  System.err.println("EMP"+EMP_email); 
			  String  EMP_phoneno=searchMap.get("EMP_phoneno")!=null?searchMap.get("EMP_phoneno") :null;
			  
			  String EMP_adress=searchMap.get("EMP_adress")!=null?searchMap.get("EMP_adress") :null; 
			  String EMP_gender=searchMap.get("EMP_gender")!=null?searchMap.get("EMP_gender") :null; 
			  String sortby =searchMap.get("sortby"); String sortorder = searchMap.get("sortorder");
			 

			if(EMP_id !=null)
			{
				 sb.append(" and EMP_id = "+EMP_id); 
			


			}
			if(EMP_name!=null   && !(EMP_name.isEmpty()))
			{
				 sb.append(" and lower(EMP_name) like '%"+EMP_name.toLowerCase()+"%' "); 
				/*
				 * sb.append(" and lower(CAST(EMP_name AS text)) like '%" +
				 * EMP_name.toLowerCase() + "%' ");
				 */


			}
			if(EMP_email!=null   && !(EMP_email.isEmpty()))
			{
				sb.append(" and lower(EMP_email) like '%"+EMP_email.toLowerCase()+"%' ");

			}


			if(EMP_phoneno!=null   && !(EMP_phoneno.isEmpty()))
			{
				sb.append(" and EMP_phoneno like '%"+EMP_phoneno+"%' ");

			}
			if(EMP_adress!=null   && !(EMP_adress.isEmpty()))
			{
				sb.append(" and lower(EMP_adress) like '%"+EMP_adress.toLowerCase()+"%'  ");

			}
			if(EMP_gender!=null   && !(EMP_gender.isEmpty()))
			{
				sb.append(" and lower(EMP_gender) like '%"+EMP_gender.toLowerCase()+"%'  ");

			}

			
			  if(sortby!=null && !(sortby.isEmpty()) && sortorder !=null &&
			  !(sortorder.isEmpty())) { 
				  sb.append(" order by "+sortby+" "+sortorder);
				  }
			 


			System.err.println("query"+sb.toString());

			Query<Employee> query = session.createQuery(sb.toString());
			query.setFirstResult(start-1);
			query.setMaxResults(4);
			List<Employee> lEmployees = query.getResultList();
			return lEmployees;    
		}
	}

	@Override
	public Boolean isDataExistForEmpId(Integer id) {
		try(Session session = sessionFactory.openSession()){
			Transaction transaction = session.beginTransaction();
			Query query=session.createQuery("FROM Employee WHERE id = '" + id +"'");
			transaction.commit();
			return query.getResultList().size()>0;
		}
	}

	@Override
	public long employeeCount() {
		try(Session session = sessionFactory.openSession()){
			long emp =(long)session.createQuery("select count(id) from Employee").getSingleResult();
			return emp;
		}
	}

	@Override
	public List<Employee> loadEmp(int start) {
try(Session session = sessionFactory.openSession()){
			
			Query<Employee> query = session.createQuery("from Employee order by id desc",Employee.class);
			query.setFirstResult(start-1);
			query.setMaxResults(4);
			return query.getResultList();
		}
	}

	@Override
	public long countForSearch(HashMap<String, String> searchMap) {

        try(Session session = sessionFactory.openSession()){
        	 Transaction transaction = session.beginTransaction();
        		StringBuilder sb=new StringBuilder("select count(id) from Employee where id > 0 ");	
        		String EMP_name = searchMap.get("EMP_name");
        		String EMP_id=searchMap.get("EMP_id");
        		String EMP_email=searchMap.get("EMP_email");
        	
        		String EMP_phoneno=searchMap.get("EMP_phoneno");
        		
        		String EMP_adress=searchMap.get("EMP_adress");
        		String EMP_gender=searchMap.get("EMP_gender");
        		String document_name=searchMap.get("document_name");
        		String password=searchMap.get("password");
        		String sortby =searchMap.get("sortby");
        		String sortorder = searchMap.get("sortorder");

        		if(EMP_name!=null   && !(EMP_name.isEmpty()))
        		{
        			sb.append(" and lower(EMP_name) like '%"+EMP_name.toLowerCase()+"%'  ");
        		}
        		if (EMP_id != null && !(EMP_id.isEmpty())) {
        		    sb.append(" and EMP_id = " + EMP_id);
        		}


        		if(EMP_email!=null   && !(EMP_email.isEmpty()))
        		{
        			sb.append(" and lower(EMP_email) like '%"+EMP_email.toLowerCase()+"%' ");
        			
        		}
//        	
//        		if(EMP_phoneno!=null   && !(EMP_phoneno.isEmpty()))
//        		{
//        			sb.append(" and EMP_phoneno = "+EMP_phoneno+" ");
//        			
//        		}
        		if (EMP_phoneno != null && !EMP_phoneno.isEmpty()) {
        		    sb.append(" and EMP_phoneno = '" + EMP_phoneno.toString() + "' ");
        		}


        	
        		if(EMP_adress!=null   && !(EMP_adress.isEmpty()))
        		{
        			sb.append(" and lower(EMP_adress) like '%"+EMP_adress.toLowerCase()+"%' ");
        			
        		}
        		if(EMP_gender!=null   && !(EMP_gender.isEmpty()))
        		{
        			sb.append(" and lower(EMP_gender) like '%"+EMP_gender.toLowerCase()+"%' ");
        			
        		}
        		if(document_name!=null   && !(document_name.isEmpty()))
        		{
        			sb.append(" and lower(document_name) like '%"+document_name.toLowerCase()+"%' ");
        			
        		}
   	 
           Query query = session.createQuery(sb.toString());
           long  count = (long) query.getSingleResult();
         	
           return count;    
        }
	}
	@Override
	public Employee getEmployee1(Integer id) {
		try(Session session = sessionFactory.openSession()){
			Employee emp = (Employee) session.createQuery("FROM Employee WHERE id = " + id).getSingleResult();
			//emp=(Employee)session.get(Employee.class, 1);
		
			return emp;
		}	
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Employee> search(HashMap<String, String> searchMap) {

		
		try(Session session = sessionFactory.openSession()){

			StringBuilder sb=new StringBuilder("from Employee where id > 0");	


			/*
			 * Integer EMP_id = Integer.parseInt(searchMap.get("EMP_id")) ; String EMP_name=
			 * searchMap.get("EMP_name") ; String EMP_email=searchMap.get("EMP_email");
			 * System.err.println("EMP"+EMP_email); String
			 * EMP_phoneno=searchMap.get("EMP_phoneno");
			 * 
			 * String EMP_adress=searchMap.get("EMP_adress"); String sortby
			 * =searchMap.get("sortby"); String sortorder = searchMap.get("sortorder");
			 */
			
			  Integer EMP_id =searchMap.get("EMP_id") != null ?Integer.parseInt(searchMap.get("EMP_id")) : null; 
			  String EMP_name= searchMap.get("EMP_name") != null ?searchMap.get("EMP_name") : null; 
			  String  EMP_email=searchMap.get("EMP_email")!=null?searchMap.get("EMP_email") : null;
			  System.err.println("EMP"+EMP_email); 
			  String  EMP_phoneno=searchMap.get("EMP_phoneno")!=null?searchMap.get("EMP_phoneno") :null;
			  
			  String EMP_adress=searchMap.get("EMP_adress")!=null?searchMap.get("EMP_adress") :null; 
			  String EMP_gender=searchMap.get("EMP_gender")!=null?searchMap.get("EMP_gender") :null; 
			  String sortby =searchMap.get("sortby"); String sortorder = searchMap.get("sortorder");
			 

			if(EMP_id !=null)
			{
				 sb.append(" and EMP_id = "+EMP_id); 
			


			}
			if(EMP_name!=null   && !(EMP_name.isEmpty()))
			{
				 sb.append(" and lower(EMP_name) like '%"+EMP_name.toLowerCase()+"%' "); 
				/*
				 * sb.append(" and lower(CAST(EMP_name AS text)) like '%" +
				 * EMP_name.toLowerCase() + "%' ");
				 */


			}
			if(EMP_email!=null   && !(EMP_email.isEmpty()))
			{
				sb.append(" and lower(EMP_email) like '%"+EMP_email.toLowerCase()+"%' ");

			}


			if(EMP_phoneno!=null   && !(EMP_phoneno.isEmpty()))
			{
				sb.append(" and EMP_phoneno like '%"+EMP_phoneno+"%' ");

			}
			if(EMP_adress!=null   && !(EMP_adress.isEmpty()))
			{
				sb.append(" and lower(EMP_adress) like '%"+EMP_adress.toLowerCase()+"%'  ");

			}
			if(EMP_gender!=null   && !(EMP_gender.isEmpty()))
			{
				sb.append(" and lower(EMP_gender) like '%"+EMP_gender.toLowerCase()+"%'  ");

			}

			
			  if(sortby!=null && !(sortby.isEmpty()) && sortorder !=null &&
			  !(sortorder.isEmpty())) { 
				  sb.append(" order by "+sortby+" "+sortorder);
				  }
			 


			System.err.println("query"+sb.toString());

			Query<Employee> query = session.createQuery(sb.toString());
			
		
			List<Employee> lEmployees = query.getResultList();
			return lEmployees;  
			}
		}
	}













