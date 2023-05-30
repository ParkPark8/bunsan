package Dao;

import Customer.Customer;
import Customer.CustomerList;

public interface CustomerDao {
	public void create(Customer customer);
	public CustomerList retrieve();
	public Customer retrieveByID(int ID);
	public void update(String updateQeury,String column, Customer customer);
	public CustomerList retrieveSCustomer();
	
	}
//Customer 먼저 만들고 . . .
//pCustomer .. 
// pCustomer : Not Interviewed . ..
// 가입 시  -> 면담 신청 
// 면담 ok -> Interviewed OK 
// 보험 처음 가입 시 :: 고객과 연결된 Car DB 가 있는지 묻고 
//--> 없으면 만들도록 함 .
// Interview는 0 , 1 상태 .. 0 : 대기 1 : OK 
// 로그인 시 Interview 상태 묻고 .. 0 : 불가 1 : 허용
/* 이후 계약 체결 ... 체결시에는 영업사원 - > uw 순으로.
 * uw는 인수 심사 ... 인수심사: 나이차량성별차
 * 
 * 
 */