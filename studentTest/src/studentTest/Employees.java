package studentTest;

import java.io.Serializable;
import java.sql.Date;
import java.util.Objects;

public class Employees implements Serializable {
	private int employeeId;   
	private String firstName;
	private String lastName;
	private String email; 
	private String phoneNumber;
	private Date hireDate;        
	private String jobId; 
	private int salary; 
	private double commissionPCT;  
	private int managerId;    
	private int departmentId;
	
	//생성자 오버로딩
	
	public Employees(int employeeId, String firstName, int salary) {
		
		this.employeeId = employeeId;
		this.firstName = firstName;
		this.salary = salary;
	}

	public Employees(int employeeId, String firstName, String lastName, String email, String phoneNumber, Date hireDate,
			String jobId, int salary, double commissionPCT, int managerId, int departmentId) {
		super();
		this.employeeId = employeeId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.hireDate = hireDate;
		this.jobId = jobId;
		this.salary = salary;
		this.commissionPCT = commissionPCT;
		this.managerId = managerId;
		this.departmentId = departmentId;
	}

	
	//갯터 셋터
	public int getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Date getHireDate() {
		return hireDate;
	}

	public void setHireDate(Date hireDate) {
		this.hireDate = hireDate;
	}

	public String getJobId() {
		return jobId;
	}

	public void setJobId(String jobId) {
		this.jobId = jobId;
	}

	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}

	public double getCommissionPCT() {
		return commissionPCT;
	}

	public void setCommissionPCT(double commissionPCT) {
		this.commissionPCT = commissionPCT;
	}

	public int getManagerId() {
		return managerId;
	}

	public void setManagerId(int managerId) {
		this.managerId = managerId;
	}

	public int getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(int departmentId) {
		this.departmentId = departmentId;
	}
	//객체비교

	@Override
	public int hashCode() {
		return Objects.hash(employeeId);
	
	}

	@Override
	public boolean equals(Object obj) {
		
		if(!(obj instanceof Employees)) {
			return false;
		}
		
		return this.employeeId == ((Employees)obj).employeeId;
	}
	//to String

	@Override
	public String toString() {
		return "[employeeId=" + employeeId + ", firstName=" + firstName + ", salary=" + salary+"]";
	}
	
	
}
