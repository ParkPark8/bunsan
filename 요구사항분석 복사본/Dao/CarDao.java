package Dao;

import Customer.Car;
import Customer.Customer;

public interface CarDao {
	public Car retrieve(Customer customer);
	public void create(Car car);
}
