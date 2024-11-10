package root;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int empId;

	private String empName;

	@OneToMany(mappedBy = "employee", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<Address> addresses;

	public Employee() {
		// TODO Auto-generated constructor stub
	}

	public Employee(String empName, List<Address> addresses) {
		this.empName = empName;
		this.addresses = addresses;
	}

	public int getEmpId() {
		return empId;
	}

	public void setEmpId(int empId) {
		this.empId = empId;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public List<Address> getAddresses() {
		return addresses;
	}

	public void setAddresses(List<Address> addresses) {
		this.addresses = addresses;
	}

	@Override
	public String toString() {
		StringBuffer br = new StringBuffer();
		List<Address> list = this.addresses;
		for (Address address : list) {
			br.append(address + "\n");
		}
		return "Employee [empId=" + empId + ", empName=" + empName + ", addresses=" + br;

		// return "Employee: " + "empid : " + this.empId + " empname: " + this.empName +
		// "address" + this.addresses;
	}

}
