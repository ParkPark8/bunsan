package Dao;

import java.sql.ResultSet;

import Customer.Car;
import Customer.Customer;

public class CarDaoImpl extends Dao implements CarDao {
	public CarDaoImpl() {
		try {
			super.connect();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Override
	public void create(Car car) {
		String query = "insert into Car ( carType , madeYear , carNum , customerID ) values ("+
	" '" +car.getCarType() + "', "+
	" '" +car.getMadeYear() + "', "+
	" '" + car.getCarNum()+ "', "+
	" '" + car.getCustomerID()+ "') ";
		try {
			this.execute(query);
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	@Override
	public Car retrieve(Customer customer) {
		String query = "select * from Car where customerID = '"+customer.getCustomerID()+"'";
		Car car = new Car();
		try {
			ResultSet resultSet = this.retrieve(query);
			while(resultSet.next()) {
				car.setCarNum(resultSet.getString("carNum"));
				car.setCarType(resultSet.getString("carType"));
				car.setCustomerID(customer.getCustomerID());
				car.setMadeYear(resultSet.getString("madeYear"));
			}
			return car;
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}



}
