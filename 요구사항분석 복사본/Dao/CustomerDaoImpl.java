package Dao;

import java.sql.ResultSet;

import Customer.Customer;
import Customer.CustomerList;
import Customer.CustomerListImpl;

public class CustomerDaoImpl extends Dao implements CustomerDao{
	public CustomerDaoImpl() {
		try {
			super.connect();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Override
	public void create(Customer customer) {
		String query = "insert into Customer (customerID , address , age , job , name , phoneNumber , sex , SID , isInterview ) values ( "+
	" '" +customer.getCustomerID() + "', "+
	" '" +customer.getAddress() + "', "+
	" '" +customer.getAge() + "', "+
	" '" +customer.getJob() + "', "+
	" '" +customer.getName() + "', "+
	" '" +customer.getPhoneNumber() + "', "+
	" '" +customer.getSex() + "', "+
	" '" +customer.getSID() + "', "+
	" '" + customer.getIsInterview() + "') "
	;	
	try {
		this.execute(query);
	}catch (Exception e) {
		e.printStackTrace();
	}
	}
	@Override
	public CustomerList retrieve() {
		String query = "select * from Customer where isInterview = '1' OR '2'" ;
		CustomerList customerList= new CustomerListImpl();
		
		try {
			ResultSet resultSet = this.retrieve(query);
			while(resultSet.next()) {
				Customer customer = new Customer();
				customer.setCustomerID(resultSet.getString("customerID"));
				customer.setAddress(resultSet.getString("address"));
				customer.setAge(resultSet.getString("age"));
				customer.setJob(resultSet.getString("job"));
				customer.setName(resultSet.getString("name"));
				customer.setPhoneNumber(resultSet.getString("phoneNumber"));
				customer.setSex(resultSet.getString("sex"));
				customer.setSID(resultSet.getString("SID"));
				customer.setIsInterview(resultSet.getString("isInterview"));
				customer.setIsBank(resultSet.getString("isBank"));
				customerList.add(customer);
			}
			return customerList;
		}catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	@Override
	public CustomerList retrieveSCustomer() {
		String query = "select * from Customer where isInterview = '0'";
		CustomerList customerList= new CustomerListImpl();
		
		try {
			ResultSet resultSet = this.retrieve(query);
			while(resultSet.next()) {
				Customer customer = new Customer();
				customer.setCustomerID(resultSet.getString("customerID"));
				customer.setAddress(resultSet.getString("address"));
				customer.setAge(resultSet.getString("age"));
				customer.setJob(resultSet.getString("job"));
				customer.setName(resultSet.getString("name"));
				customer.setPhoneNumber(resultSet.getString("phoneNumber"));
				customer.setSex(resultSet.getString("sex"));
				customer.setSID(resultSet.getString("SID"));
				customer.setIsInterview(resultSet.getString("isInterview"));
				customer.setIsBank(resultSet.getString("isBank"));
				customerList.add(customer);
			}
			return customerList;
		}catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	@Override
	public Customer retrieveByID(int ID) {
		return null;
	}

	@Override
	public void update(String column,String updateQeury, Customer customer) {
		String customerID = customer.getCustomerID();
		String query = "UPDATE Customer SET "+ column+" = "+updateQeury+" WHERE customerID = '" + customerID+ "'";
		//UPDATE `new_schema`.`Customer` SET isInterview = '1' WHERE (`customerID` = 'pdh');		
		try {
				this.update(query);
			}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	

}
