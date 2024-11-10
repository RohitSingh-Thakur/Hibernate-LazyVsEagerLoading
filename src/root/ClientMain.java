package root;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class ClientMain {

	public static void main(String[] args) {
		Configuration cfg = new Configuration();
		cfg.configure().addAnnotatedClass(Employee.class).addAnnotatedClass(Address.class);
		SessionFactory sessionFactory = cfg.buildSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();

		try {

			// Fetching data (Eager) <Employee -> Eager, Address -> Lazy>

			Employee employee = session.get(Employee.class, 1); // fire  SELECT query for employee and address 
			
/*			Hibernate: 
			    select
			        employee0_.empId as empId1_1_0_,
			        employee0_.empName as empName2_1_0_,
			        addresses1_.emp_id as emp_id6_0_1_,
			        addresses1_.addressId as addressI1_0_1_,
			        addresses1_.addressId as addressI1_0_2_,
			        addresses1_.city as city2_0_2_,
			        addresses1_.emp_id as emp_id6_0_2_,
			        addresses1_.postalCode as postalCo3_0_2_,
			        addresses1_.state as state4_0_2_,
			        addresses1_.street as street5_0_2_ 
			    from
			        Employee employee0_ 
			    left outer join
			        Address addresses1_ 
			            on employee0_.empId=addresses1_.emp_id 
			    where
			        employee0_.empId=? */
			

			List<Address> empAddresses = employee.getAddresses(); // no additional query will be fired already address data is loaded 

			System.out.println("Employee : " + "\n" + "Employee Id: " + employee.getEmpId() + "\n" + "Employee Name: "
					+ employee.getEmpName() + "\n" + "Employee Adreeses -> " + "\n");
			int count = 0;
			for (Address address : empAddresses) {
				count++;
				System.out.println("Address " + count + ": " + address);
			}

			session.close();

		} catch (Exception e) {
			e.printStackTrace();
			session.close();
		} finally {
			session.close();
		}
	}

}
