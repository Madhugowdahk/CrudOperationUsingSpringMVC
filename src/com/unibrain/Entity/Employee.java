package com.unibrain.Entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@SuppressWarnings("serial")
@Entity
@Table(name="EmployeDetails")
public class Employee implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", columnDefinition = "serial")
	private Integer EMP_id;

	@Column(name="Employe_name")
	private String EMP_name;

	@Column(name="Employe_email")
	private String EMP_email;

	@Column(name="Employe_phoneno")
	private String EMP_phoneno;
	
	@Column(name="Employe_gender")
	private String EMP_gender;
	
	@Column(name="Employe_adress")
	private String EMP_adress;
	
	@Column(name="document_name")
	private String document_name;
	
	@Column(name="password_hash")
	private String password;

	public String getPassword() {
		return password;
	}


	public Employee(Integer eMP_id, String eMP_name, String eMP_email, String eMP_phoneno, String eMP_gender,
			String eMP_adress, String document_name, String password) {
		super();
		EMP_id = eMP_id;
		EMP_name = eMP_name;
		EMP_email = eMP_email;
		EMP_phoneno = eMP_phoneno;
		EMP_gender = eMP_gender;
		EMP_adress = eMP_adress;
		this.document_name = document_name;
		this.password = password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getdocument_name() {
		return document_name;
	}


	public void setdocument_name(String document_name) {
		this.document_name = document_name;
	}


	public Integer getEMP_id() {
		return EMP_id;
	}


	public void setEMP_id(Integer eMP_id) {
		EMP_id = eMP_id;
	}


	public String getEMP_name() {
		return EMP_name;
	}


	public void setEMP_name(String eMP_name) {
		EMP_name = eMP_name;
	}


	public String getEMP_email() {
		return EMP_email;
	}


	public void setEMP_email(String eMP_email) {
		EMP_email = eMP_email;
	}


	public String getEMP_phoneno() {
		return EMP_phoneno;
	}


	public void setEMP_phoneno(String eMP_phoneno) {
		EMP_phoneno = eMP_phoneno;
	}


	public String getEMP_gender() {
		return EMP_gender;
	}


	public void setEMP_gender(String eMP_gender) {
		EMP_gender = eMP_gender;
	}


	public String getEMP_adress() {
		return EMP_adress;
	}


	public void setEMP_adress(String eMP_adress) {
		EMP_adress = eMP_adress;
	}




	public Employee()
	{
		
	}




	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((EMP_adress == null) ? 0 : EMP_adress.hashCode());
		result = prime * result + ((EMP_email == null) ? 0 : EMP_email.hashCode());
		result = prime * result + ((EMP_gender == null) ? 0 : EMP_gender.hashCode());
		result = prime * result + ((EMP_id == null) ? 0 : EMP_id.hashCode());
		result = prime * result + ((EMP_name == null) ? 0 : EMP_name.hashCode());
		result = prime * result + ((EMP_phoneno == null) ? 0 : EMP_phoneno.hashCode());
		result = prime * result + ((document_name == null) ? 0 : document_name.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Employee other = (Employee) obj;
		if (EMP_adress == null) {
			if (other.EMP_adress != null)
				return false;
		} else if (!EMP_adress.equals(other.EMP_adress))
			return false;
		if (EMP_email == null) {
			if (other.EMP_email != null)
				return false;
		} else if (!EMP_email.equals(other.EMP_email))
			return false;
		if (EMP_gender == null) {
			if (other.EMP_gender != null)
				return false;
		} else if (!EMP_gender.equals(other.EMP_gender))
			return false;
		if (EMP_id == null) {
			if (other.EMP_id != null)
				return false;
		} else if (!EMP_id.equals(other.EMP_id))
			return false;
		if (EMP_name == null) {
			if (other.EMP_name != null)
				return false;
		} else if (!EMP_name.equals(other.EMP_name))
			return false;
		if (EMP_phoneno == null) {
			if (other.EMP_phoneno != null)
				return false;
		} else if (!EMP_phoneno.equals(other.EMP_phoneno))
			return false;
		if (document_name == null) {
			if (other.document_name != null)
				return false;
		} else if (!document_name.equals(other.document_name))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "Employee [EMP_id=" + EMP_id + ", EMP_name=" + EMP_name + ", EMP_email=" + EMP_email + ", EMP_phoneno="
				+ EMP_phoneno + ", EMP_gender=" + EMP_gender + ", EMP_adress=" + EMP_adress + ", document_name="
				+ document_name + ", password=" + password + "]";
	}


	
	

}
