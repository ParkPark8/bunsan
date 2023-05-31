package AppConfig;

import java.beans.beancontext.BeanContextMembershipEvent;import java.sql.Time;
import java.text.BreakIterator;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

import javax.imageio.spi.RegisterableService;
import javax.swing.plaf.ActionMapUIResource;
import javax.swing.text.DefaultEditorKit.CutAction;

import com.mysql.cj.x.protobuf.MysqlxDatatypes.Array;
import com.mysql.cj.xdevapi.DbDoc;
import com.mysql.cj.xdevapi.SqlUpdateResult;

import Contract.Contract;
import Contract.ContractList;
import Contract.ContractListImpl;
import Customer.Bank;
import Customer.Car;
import Customer.Customer;
import Customer.CustomerList;
import Customer.CustomerListImpl;
import Dao.BankDao;
import Dao.BankDaoImpl;
import Dao.CarDao;
import Dao.CarDaoImpl;
import Dao.ConfirmDao;
import Dao.ConfirmDaoImpl;
import Dao.ContractDao;
import Dao.ContractDaoImpl;
import Dao.CoverageDao;
import Dao.CoverageDaoImpl;
import Dao.CustomerDao;
import Dao.CustomerDaoImpl;
import Dao.EmployeeDao;
import Dao.EmployeeDaoImpl;
import Dao.InsuranceDao;
import Dao.InsuranceDaoImpl;
import Dao.InterviewDao;
import Dao.InterviewDaoImpl;
import Dao.MailDao;
import Dao.MailDaoImpl;
import Dao.RatioDao;
import Dao.RatioDaoImpl;
import Dao.ResultDao;
import Dao.ResultDaoImpl;
import Employee.Employee;
import Employee.EmployeeList;
import Employee.EmployeeListImpl;
import Employee.InsuranceConfirmer;
import Employee.InsuranceDesigner;
import Employee.SalesPerson;
import Insurance.Confirm;
import Insurance.Coverage;
import Insurance.Insurance;
import Insurance.InsuranceList;
import Insurance.InsuranceListImpl;
import Insurance.Ratio;
import Insurance.Result;
import Interview.Interview;
import Interview.InterviewList;
import Mail.Mail;
import Mail.MailList;
import Mail.MailListImpl;
import Sales.sales;
import apple.laf.JRSUIUtils.Tree;

public class Main {
	private static int cnt;
	
	private static Employee employee;
	private static InsuranceList insuranceList;
	private static EmployeeList employeeList;
	private static CustomerList customerList;
	private static CustomerList sCustomerList;
	private static InterviewList interviewList;
	private static InterviewList sInterviewList;
	private static ContractList contractList;
	private static MailList mailList;

	//dao
	private static EmployeeDao employeeDao;
	private static InsuranceDao insuranceDao;
	private static ConfirmDao confirmDao;
	private static CoverageDao coverageDao;
	private static RatioDao ratioDao;
	private static CustomerDao customerDao;
	private static InterviewDao interviewDao;
	private static CarDao carDao;
	private static BankDao bankDao;
	private static ContractDao contractDao;
	private static ResultDao resultDao;
	private static MailDao mailDao;
	
	private static Scanner sc;
	public Main() {
		sc = new Scanner(System.in);
		employeeDao = new EmployeeDaoImpl();
		insuranceDao = new InsuranceDaoImpl();
		coverageDao = new CoverageDaoImpl();
		confirmDao = new ConfirmDaoImpl();
		ratioDao = new RatioDaoImpl();
		customerDao = new CustomerDaoImpl();
		interviewDao = new InterviewDaoImpl();
		carDao = new CarDaoImpl();
		bankDao = new BankDaoImpl();
		contractDao = new ContractDaoImpl();
		resultDao = new ResultDaoImpl();
		mailDao = new MailDaoImpl();
	}
	public static void setDB() {
		employeeList = employeeDao.retrieve();
		insuranceList = insuranceDao.retrieve();
		customerList = customerDao.retrieve();
		sCustomerList = customerDao.retrieveSCustomer();
		interviewList = interviewDao.retrieveProcessed();
		sInterviewList = interviewDao.retrieveNoProcessed(); //예비 인터뷰 리스트 
		contractList = contractDao.retrieve();
	}
	public static void main(String[] args) {
		Main main = new Main();
		main.run();
	}
	private void run() {
		setDB();
		
		System.out.println("< 보험사 전산 시스템 >");
		System.out.println("(1) 직원 로그인 (2) 고객 로그인 ");
		int loginNum = Integer.parseInt(sc.nextLine());
		switch(loginNum) {
		case 1 : 
			System.out.println(dd.DD);
			runEmployeeSystem();
			break;
		case 2 :
			System.out.println(dd.DD);
			runCustomerSystem();
			break;
		default : break;
		}
		}
	private void runEmployeeSystem() {
		boolean end = false;
		System.out.println("직원 아이디를 입력하세요 . ");
		employee = employeeList.search(Integer.parseInt(sc.nextLine()));
		while( employee == null ) {
			System.out.println("잘못된 ID를 입력하셨습니다. 다시 입력해주세요 . ");
			employee = employeeList.search(Integer.parseInt(sc.nextLine()));
		}
		System.out.println(employee.getName().split("_")[1]+"님 반갑습니다 .");
		
		int menu = checkEmployee(employee);
		for (int i = 0 ; i< 6 ; i++) {
			if (end) break;
			switch (menu) {
			case 1:
				//보험설계
				System.out.println(dd.DD);
				System.out.println("< 보험 설계 메뉴 >");
				System.out.println("1. 보험 설계하기 ");
				System.out.println("2. 종료하기 ");
				int designSubmenu = Integer.parseInt(sc.nextLine());
				System.out.println(dd.DD);
				switch(designSubmenu) {
				case 1:
					insuranceDesign();
					break;
				case 2:
					end = true; //end = true :시스템 종ㄹ
					System.out.println("보험사 시스템을 종료합니다 . ");
					break;
				default:
					break;
				}
				break;
			case 2:
				//보험확정
				System.out.println(dd.DD);
				System.out.println( "< 보험 확정 및 반려 메뉴 >" );
				System.out.println("1. 보험 확정/반려하기 ");
				System.out.println("2. 종료하기 ");
				int confirmSubmenu = Integer.parseInt(sc.nextLine());
				switch(confirmSubmenu) {
				case 1:
					insuranceConfirm();
					break;
				case 2:
					end = true;
					System.out.println("보험사 시스템을 종료합니다 . ");
					break;
				default:
					break;
				}
				break;
			case 3:
				//영업사원
				System.out.println(dd.DD);
				System.out.println("< 보험 영업 메뉴 >");
				System.out.println("1. 보험 리스트 확인하기 ");
				System.out.println("2. 고객 면담하기 ");
				System.out.println("3. 계약 체결하기 ");
				System.out.println("4. 실적 확인하기 ");
				System.out.println("5. 종료하기 ");
				int salesSubmenu = Integer.parseInt(sc.nextLine());
				switch(salesSubmenu) {
				case 1:
					salesInsuranceList();
					break;
				case 2:
					System.out.println(dd.DD);
					salesInterview(employee);
					break;
				case 3:
					System.out.println(dd.DD);
					contractInsurance(employee);
					break;
				case 4:
					break;
				case 5:
					end = true;
					System.out.println("보험사 시스템을 종료합니다 . ");
					break;
				default:
					break;
				}
				break;
			case 4: 
				//UW
				System.out.println(dd.DD);
				System.out.println("1. 인수 심사하기 ");
				System.out.println("2. 종료하기 ");
				switch(Integer.parseInt(sc.nextLine())) {
				case 1 :
					System.out.println(dd.DD);
					insuranceExam(employee);
					break;
				case 2:
					end = true;
					System.out.println("보험사 시스템을 종료합니다 . ");
					break;
					default:break;
				}
				break;
			default:
				break;
			}
		}
	}
	private void insuranceExam(Employee employee) { // UW - 인수 심사하다 
		/* 보험의 Result / 직원의 numSelled / Mail ... 
		 * 인수 & 반려 후 변경 사항
		 * - 인수 시 -
		 *  1. 고객에게 메일 보내기 
		 *  2. Contract DB ( ProcessNum -> 3 으로 Update ) 
		 *  3. Employee NumSelled - > cnt++
		 *  4. Insurance NumSelled -> cnt++
		 *  -반려 시 -
		 *  1. 고객에게 메일 보내기 
		 *  2. Contract DB ( ProcessNum -> 4 로 Update )
		 */
		ArrayList<Contract> contracts = contractDao.retrieveContractByProcess("2").getContractList();
		System.out.println("인수 심사를 진행할 계약을 선택해주세요 . ");
		System.out.println("< 인수 대기 계약 목록 > ");
		for(Contract contract : contracts) {
			System.out.println("| 계약 번호 : "+contract.getContractID() + " | 고객 ID : " + contract.getCustomerID()+" | 보험 ID : " + contract.getInsuranceID()+" |");
			}
		Contract contract = contractList.search(Integer.parseInt(sc.nextLine()));
		while(contract ==null) {
			System.out.println("존재하지 않는 계약 ID 입니다 . ");
			contract = contractList.search(Integer.parseInt(sc.nextLine()));
		}
		System.out.println(dd.DD);
		System.out.println("< 계약 정보 > ");
		Insurance insurance = insuranceList.search(Integer.parseInt(contract.getInsuranceID()));
		Customer customer = customerList.search(contract.getCustomerID());
		System.out.println( "계약 당사자 명 : "+ customer.getName());
		System.out.println("계약 당사자 나이 : "+customer.getAge());
		System.out.println("계약 당사자 직업 : " + customer.getJob());
		System.out.println("계약 당사자 성별 : " + customer.getSex());
		System.out.println("계약 당사자 주민등록번호 : " + customer.getSID());
		System.out.println("책정 보험 가입료 : "+contract.getRegistFee());
		System.out.println("책정 월 납부액 : "+contract.getMonthFee());
		Interview interview = interviewList.search(customer.getCustomerID());
		Employee iEmployee = employeeList.search(Integer.parseInt(interview.getEmployeeID()));
		System.out.println("면담 여부 : O ");
		System.out.println("면담 일시 : "+interview.getProcessedDate());
		System.out.println("담당 직원 명 : " +iEmployee.getName().split("_")[1]);
		System.out.println("면담 내용: " + interview.getContent());
		System.out.println(dd.DD);
		int uwNum = calculateFactors(customer,insurance);
		//Time
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date time = new Date();
		//
		String startDate = contract.getStartDate();
		
		if(uwNum < 5) {
			System.out.println("인수 심사 결과 계약을 체결할 수 있는 고객입니다 . ");
			System.out.println("계약 체결을 진행하시려면 (1) , 대기를 원하시면 (2) 를 눌러주세요 . ");
			if(Integer.parseInt(sc.nextLine())==2)  return;
			else {
				// 납입일 , 만기일 설정 ; Contract DB Update 
				String endDate = setEndDate(startDate);
				contractDao.update("endDate", endDate+"", contract); //endDate
				String payDate = setPayDate(startDate);
				contractDao.update("payDate", payDate+"", contract);//payDate
				contractDao.update("processNum", "3", contract);//processNum
				// Insurance result , employee NumSelled Update. 
				Result result = resultDao.retrieveByInsuranceID(insurance.getInsuranceID()+"");
				int numSelled = Integer.parseInt(result.getNumSelled());
				numSelled++; //판매 횟수 +
				resultDao.update("numSelled", numSelled+"", result);
				
				Employee salesPerson = employeeList.search(Integer.parseInt(contract.getEmployeeID())); 
				numSelled = Integer.parseInt(salesPerson.getNumSelled());
				numSelled++; // 직원 실적 + 
				employeeDao.update("numSelled", numSelled+"", salesPerson);
				//  Mail -> Customer ; 
				Mail mail = new Mail();
				String title = "보험 계약 체결 안내 ";
				String content = customer.getName()+" 님의 " +insurance.getInsuranceName()+" 보험 계약 인수가 정상 처리되었습니다 . ";
				mail.setContent(content);
				mail.setTitle(title);
				mail.setCustomerID(customer.getCustomerID());
				mail.setEmployeeID(employee.getName());
				mail.setDate(format.format(time));
				mailDao.create(mail);
				
				System.out.println(customer.getName()+" 고객의 " +insurance.getInsuranceName() + " 보험 계약 인수를 승인하였습니다. ");
				System.out.println("계약 인수 즉시 효력이 발생하며 해당 계약의 만료일은 "+endDate+"입니다 . ");


			}
		}else {
			System.out.println("인수 심사 결과 계약 체결이 불가능한 고객입니다 . ");
			//Mail
			Mail mail = new Mail();
			
			String title = "보험 계약 체결 반려 안내 ";
			String content =customer.getName()+" 님의 계약의 인수 심사 결과 체결 불가로 판단되었습니다 . ";
			String date = format.format(time);
			
			mail.setContent(content);
			mail.setDate(date);
			mail.setTitle(title);
			mail.setCustomerID(customer.getCustomerID());
			mail.setEmployeeID(employee.getName()+"");
			mailDao.create(mail);
			//contract -> 4 ( 인수 X ) 
			contractDao.update("processNum", "4", contract);
		}
		
	}
	private String setEndDate(String format) {
		String year = format.split("-")[0];
		int cnt = Integer.parseInt(year); cnt+=2;
		year = cnt+"";
		String endDate = year+"-"+format.split("-")[1]+format.split("-")[2];
		return endDate;
	}
	private String setPayDate(String format) {
		String month = format.split("-")[1];
		if(month.equals("12")) month = "1";
		else {
			int cnt = Integer.parseInt(month);
			cnt +=1;
			month = cnt+"";
		}
		String payDate = format.split("-")[0]+"-"+month+"-"+format.split("-")[2];
		return payDate;
	}
	private int calculateFactors(Customer customer, Insurance insurance) {
		System.out.println("인수 심사를 시작합니다 ...\n");
		int age = Integer.parseInt(customer.getAge());
		String job = customer.getJob();
		Car car = carDao.retrieve(customer);
		String madeYear = car.getMadeYear();
		String carType = car.getCarType();
		int uwNum = 0;
		switch(job) { //직업 별 요소
		case "학생":
			break;
		case "버스기사": uwNum+=1;
			break;
		case "화물차운전자":uwNum+=2;
			break;
		case "배달종사자":uwNum+=2;
			break;
		case "기타사무직":
			break;
		default:break;
		}
		System.out.println("계약자 직업 : "+job+" ( uwRate : " + uwNum +" / 5 )\n");
		if(Integer.parseInt(madeYear)<2018) { // 제조년도 별 요소
			uwNum+=1;
			}
		System.out.println("차량 제조 년도 : "+madeYear+" ( uwRate : " + uwNum +" / 5 )\n");
		//차종 별 요소
		if(carType.equals("motorCycle") || carType.equals("freight")) uwNum +=1;
		System.out.println("계약자 소유 차종 : "+carType+" ( uwRate : " + uwNum +" / 5 )\n");
		//나이 별 요소
		if(age<30 || age > 60 ) {
			if(age>70) uwNum +=2;
			else uwNum+=1;
		}
		System.out.println("계약자 나이 : "+age+" ( uwRate : " + uwNum +" / 5 )\n");
		System.out.println(dd.DD);
		return uwNum;
	}
	private void contractInsurance(Employee employee) {
		// 리스트 : 고객 아이디 , 이름 , 계약하는 보험 이름
		// 상세 : 고객 이름 , 정보  , 보험료  
		System.out.println("< 체결하실 계약의 ID를 입력해주세요 .  >  ");
		System.out.println("- 계약 목록 - ");
		ArrayList<Contract> contracts = contractDao.retrieveContractByProcess("1").getContractList();
		for(Contract contract : contracts) {
			System.out.println("| 계약 번호 : "+contract.getContractID() + " | 고객 ID : " + contract.getCustomerID()+" | 보험 ID : " + contract.getInsuranceID()+" |");
		}
		Contract contract = contractList.search(Integer.parseInt(sc.nextLine()));
		while(contract ==null) {
			System.out.println("존재하지 않는 계약 ID 입니다 . ");
			contract = contractList.search(Integer.parseInt(sc.nextLine()));
		}
		System.out.println(dd.DD);
		System.out.println("< 계약 정보 > ");
		Insurance insurance = insuranceList.search(Integer.parseInt(contract.getInsuranceID()));
		Customer customer = customerList.search(contract.getCustomerID());
		System.out.println( "계약 당사자 명 : "+ customer.getName());
		System.out.println("계약 당사자 나이 : "+customer.getAge());
		System.out.println("계약 당사자 직업 : " + customer.getJob());
		System.out.println("계약 당사자 성별 : " + customer.getSex());
		System.out.println("계약 당사자 주민등록번호 : " + customer.getSID());
		System.out.println("책정 보험 가입료 : "+contract.getRegistFee());
		System.out.println("책정 월 납부액 : "+contract.getMonthFee());
		Interview interview = interviewList.search(customer.getCustomerID());
		Employee iEmployee = employeeList.search(Integer.parseInt(interview.getEmployeeID()));
		System.out.println("면담 여부 : O ");
		System.out.println("면담 일시 : "+interview.getProcessedDate());
		System.out.println("담당 직원 명 : " +iEmployee.getName().split("_")[1]);
		System.out.println("면담 내용: " + interview.getContent());
		System.out.println(dd.DD);
		System.out.println("(1) 계약 체결  (2) 체결 보류 ");
		switch(Integer.parseInt(sc.nextLine())) {
		case 1:
			System.out.println("해당 계약의 체결을 신청하였습니다 . 인수 심사 후 계약이 실현됩니다 . ");
			contractDao.update("employeeID", employee.getEmployeeID()+"", contract);
			contractDao.update("processNum", "2", contract);
			break;
		case 2:
			System.out.println("이전 페이지로 돌아갑니다 . ");
			break;
		default:break;
		}
	}
	private void salesInterview(Employee employee) {

		System.out.println("< 면담 대기 고객 리스트 >");
		ArrayList<Interview> interviews = interviewDao.retrieveNoProcessed().getInterviewList();
		int cnt = 1;
		String customers[] = new String[interviews.size()+1];
		for(Interview interview : interviews) {
			String customerID = interview.getCustomerID();
			Customer customer = customerList.search(customerID);
			System.out.println(cnt+". "+customer.getName() + " "+interview.getAskDate() );
			customers[cnt] = customerID;
			cnt++;
		}
		System.out.println("면담을 진행할 고객의 ID를 선택해 주세요 . ");
		int selecNum = Integer.parseInt(sc.nextLine());
		while(selecNum > interviews.size()+1) {
			System.out.println("잘못 입력하셨습니다 . 다시 입력해 주세요 . ");
			selecNum = Integer.parseInt(sc.nextLine());
		}
		Interview interview = sInterviewList.search(customers[selecNum]);
		Customer customer = customerList.search(customers[selecNum]);
		System.out.println(dd.DD);
		System.out.println(" - 고객정보 - ");
		System.out.println("이름 : "+customer.getName());
		System.out.println("나이 : "+customer.getAddress());
		System.out.println("성별 : "+customer.getSex());
		System.out.println("면담 신청일 : "+interview.getAskDate());
		System.out.println(dd.DD);
		System.out.println("면담 내용을 작성해주세요 . ");
		String contents = sc.nextLine();
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date time = new Date();		
		
		interviewDao.update("employeeID", ""+employee.getEmployeeID(), customer.getCustomerID());
		interviewDao.update("isProcessed", "1", customer.getCustomerID());
		interviewDao.update("processedDate", "'"+format.format(time)+"'" , customer.getCustomerID());
		interviewDao.update("content", "'"+contents+"'", customer.getCustomerID());
		
		customerDao.update("isInterview", "2", customer);
		
		System.out.println("완료 ");

	}
	private void runCustomerSystem() {
		System.out.println("< 보험사 고객 메뉴 >");
		System.out.println("1. 로그인");
		System.out.println("2. 고객 등록 ");
		int menu = Integer.parseInt(sc.nextLine());
		switch(menu) {
		//회원 로그인 
		case 1:
			System.out.println(dd.DD);
			loginCustomer();
			break;
		//가입
		case 2:
			System.out.println(dd.DD);
			createCustomer();
			break;
		default:
			break;
		}
	}
	private void loginCustomer() {
		System.out.println("< 고객 로그인 메뉴 > ");
		Customer customer;
		System.out.println("가입하신 ID를 입력해주세요 . ");
		String id = sc.nextLine();
		Customer isExistInCustomerList = customerList.search(id);
		Customer isExistInSCustomerList = sCustomerList.search(id);
		while(isExistInCustomerList == null && isExistInSCustomerList == null) {
			System.out.println("! 존재하지 않는 ID입니다 . 다시 입력해주세요 !");
			id = sc.nextLine();
			isExistInCustomerList = customerList.search(id);
			isExistInSCustomerList = sCustomerList.search(id);
		}
		if( isExistInCustomerList == null)  customer = isExistInSCustomerList;
		else customer = isExistInCustomerList;
		for (int i = 0; i < 5; i++) {
			System.out.println(dd.DD);
			System.out.println("[ " + customer.getName() + " ]님 반갑습니다 . ");
			System.out.println("1. 가입할 보험 리스트 확인하기 ");
			System.out.println("2. 면담 신청하기 ");
			System.out.println("3. 가입한 보험 확인하기 ");
			System.out.println("4. 사고 접수하기 ");
			System.out.println("5. 메일 확인하기 ");
			System.out.println("6. 종료 ");
			switch(Integer.parseInt(sc.nextLine())) {
			case 1:
				System.out.println(dd.DD);
				checkInsuranceList(customer);
				break;
			case 2:
				System.out.println(dd.DD);
				askInterview(customer);
				break;
			case 3:
				System.out.println(dd.DD);
				checkRegistedInsuranceList(customer);
				break;
			case 4:
				break;
			case 5:
				System.out.println(dd.DD);
				checkMailBox(customer);
				break;
			case 6:
				break;
			default:break;
			}
			
		}
	}
	private void checkRegistedInsuranceList(Customer customer) {
		ArrayList<Contract> contracts = contractDao.retrieveByCustomerID(customer).getContractList();
		ArrayList<Contract> contractList = new ArrayList<>();
		int cnt = 1;
		for(Contract contract : contracts) {
			Insurance insurance = insuranceList.search(Integer.parseInt(contract.getInsuranceID()));
			System.out.println(cnt + ". "+insurance.getInsuranceName()); cnt++;
			contractList.add(contract);
		}System.out.println("확인하실 보험을 선택해주세요 . ");
		int selectNum = Integer.parseInt(sc.nextLine());
		while(selectNum > contractList.size()) { // Exception : 없는 번호를 선택시 
			System.out.println("다시 선택해 주세요 .");
			selectNum = Integer.parseInt(sc.nextLine());
		}
		System.out.println(dd.DD);
		Contract contract = contractList.get(selectNum-1);
		Insurance insurance = insuranceList.search(Integer.parseInt(contract.getInsuranceID()));
		System.out.println("< "+ insurance.getInsuranceName()+" >");
		System.out.println("보험 가입일 : " + contract.getStartDate());
		System.out.println("보험 만료일 : " + contract.getEndDate());
		System.out.println("보험료 납부일 : "+contract.getPayDate() );
		System.out.println("보험 보장금 ( 저 ) : " +insurance.getLCoverage().getCoverageCost());
		System.out.println("보험 보장금 ( 중 ) : "+insurance.getMCoverage().getCoverageCost());
		System.out.println("보험 보장금 ( 고 ) : " +insurance.getHCoverage().getCoverageCost());

	}
	private void checkMailBox(Customer customer) {
		ArrayList<Mail> mails = mailDao.retrieveByCustomerID(customer.getCustomerID()).getMailList();
		int cnt = 1;
		ArrayList<Mail> mailList = new ArrayList<>();
		for(Mail mail : mails) {
			System.out.println(cnt+". "+mail.getTitle() +" [ "+ mail.getDate()+" ]");
			mailList.add(mail);
			cnt++;
		}
		System.out.println("확인하실 메일을 선택해주세요 . ");
		int selectNum = Integer.parseInt(sc.nextLine());
		while(selectNum>mails.size()){ //Exception : 없는 번호 선택 시 
			System.out.println("다시 선택해 주세요 . ");
			selectNum = Integer.parseInt(sc.nextLine());
		}
		System.out.println(dd.DD);
		Mail mail = mailList.get(selectNum-1);
		System.out.println("발신자 : "+mail.getEmployeeID().split("_")[1]);
		System.out.println(mail.getContent());
		
	}
	private void checkInsuranceList(Customer customer) {
		System.out.println("< 가입 가능 보험 리스트 > ");
		//해당 고객이 이미 가입한 보험 제외 해야 함 . (아직 x)
		System.out.println("- 보험목록 - ");
		ArrayList<Insurance> insurances = insuranceDao.retrieveConfirm().getInsuranceList();
		ArrayList<Contract> contracts = contractDao.retrieve().getContractList();
		for(Insurance insurance : insurances) { // < 추가할 사항 : 이미 신청된 보험 제외 > 
					System.out.println(insurance.getInsuranceID() + " " + insurance.getInsuranceName());
			
		}
		System.out.println("가입하실 보험의 ID를 입력해주세요 . ");
		System.out.println("0. 돌아가기 ");
		int selecNum = Integer.parseInt(sc.nextLine());
		if(selecNum == 0 ) return;
		Insurance insurance = insuranceList.search(selecNum);
		//Exception : 존재하지 않는 보험 ID를 입력하였을 때 
		while(insurance == null) {
			System.out.println("존재하지 않는 보험 ID를 입력하셨습니다  . 다시 입력해 주세요 . ");
			insurance = insuranceList.search(Integer.parseInt(sc.nextLine()));
		}
		//Exception : 이미 체결된 보험을 가입하려 할 때 
		while(contractList.searchIsContract(insurance.getInsuranceID()+"", customer.getCustomerID()) != null) {
			System.out.println("해당 보험은 이미 체결 되었거나 진행 중인 보험입니다 . ");
			return;
		}

		System.out.println(dd.DD); 
		System.out.println("< 보험정보 >");
		System.out.println("보험 유형 : "+insurance.getProductType()) ;
		System.out.println("보험 ID : "+ insurance.getInsuranceID());
		System.out.println("보험명 : " + insurance.getInsuranceName());
		System.out.println("보험 기본 가입비 : "+insurance.getInsuranceFee());
		System.out.println("보험 보장 ( 상 ) : " + insurance.getHCoverage().getCoverageCost());
		System.out.println("보험 보장 ( 중 ) : " + insurance.getMCoverage().getCoverageCost());
		System.out.println("보험 보장 ( 하 ) : " + insurance.getLCoverage().getCoverageCost());
		System.out.println(dd.DD);
		System.out.println("(1) 보험 가입 신청 (2) 확인 ");
		switch(Integer.parseInt(sc.nextLine())) { // 면담 진행 고객인지 아닌지 구분 
		case 1:
			Interview interview = interviewList.search(customer.getCustomerID());
			if(customer.getIsInterview().equals("2")) {
				System.out.println(dd.DD);
				System.out.println("< 보험 가입 메뉴 > ");
				// 계좌 등록된 고객 / 등록되지 않은 고객 구분 
				if(customer.getIsBank().equals("0")) {
					makeAccount(customer); //고객 계좌 db에등록
				}
				System.out.println(dd.DD);
				System.out.println(insurance.getInsuranceName()+" 보험의 보험료율 시산작업을 시작합니다 . ");
				System.out.println();
				System.out.println("시산작업 진행중 . . .");
				System.out.println();
				float rate = Float.parseFloat(getRate(insurance, customer)) ;
				float monthRate = (float)0.0020 * rate * (insurance.getHCoverage().getCoverageCost()) ;// 월납부 보험
				float registFee = insurance.getInsuranceFee() * rate ; // 보험 가입비 
				String dbMonthRate = (int)monthRate+"";
				String dbRegistFee = (int)registFee+"";
				
				System.out.println("고객님의 보험 가입료는 ( "+dbRegistFee+" ) , 월 납부액은 ( "+dbMonthRate+" )입니다. ");
				System.out.println("보험 실현은 직원의 보험 체결 이후 진행됩니다 . 감사합니다 .");
				//ContractDao 만들기  고객의 isBank -> 1로 update 해주기 
				Contract contract = new Contract();
				contract.setCustomerID(customer.getCustomerID());
				contract.setProcessNum("1");
				contract.setInsuranceID(insurance.getInsuranceID()+"");
				contract.setRegistFee(dbRegistFee);
				contract.setMonthFee(dbMonthRate);
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
				Date time = new Date();
				String date = format.format(time);
				contract.setStartDate(date);
				contractDao.create(contract);
				customerDao.update("isBank", "1", customer);
				////////////////////////////////////////////////////////////////////////
			}else { //Exception : 면담 진행 X 고객일 시 
				System.out.println("! 직원과의 면담이 진행중이거나 처리되지 않은 상태입니다 . 면담 후 다시 신청해주세요 !");
			}
			break;
		case 2:
			break;
		default : break;
		}
		
	}
	private String getRate(Insurance insurance, Customer customer) {
		Ratio ratio= ratioDao.retrieveByInsuranceID(insurance.getInsuranceID());
		Car car = carDao.retrieve(customer);
		
		float rate = 1.0f ;
		
		String carType = car.getCarType();
		String madeYear = car.getMadeYear();
		String sex = customer.getSex();
		String age = customer.getAge();
		String job = customer.getJob();
		// CarType 
		switch (carType){
		case "small":
			rate = rate * Float.parseFloat(ratio.getSmallRatio());
			break;
		case "sedan":
			rate =rate * Float.parseFloat(ratio.getSedanRatio());
			break;
		case "bus":
			rate = rate * Float.parseFloat(ratio.getBusRatio());
			break;
		case "motorCycle":
			rate = rate * Float.parseFloat(ratio.getMotorCycleRatio());
			break;
		case "freight":
			rate = rate * Float.parseFloat(ratio.getFreightRatio());
			break;
		default:
			break;
		}
		//madeYear
		int year = Integer.parseInt(madeYear);
		if(year>=2018) {
			rate =rate *  Float.parseFloat(ratio.getAfter2018());
		}else{
			rate =rate *  Float.parseFloat(ratio.getBefore2018());
		}
		//sex
		switch (sex) {
		case "남성":
			rate = rate * Float.parseFloat(ratio.getMaleRatio());
			break;
		case "여성":
			rate = rate * Float.parseFloat(ratio.getFemaleRatio());
			break;
		default:
			break;
		}
		//age
		int customerAge = Integer.parseInt(age);
		if(customerAge>30) {
			rate = rate * Float.parseFloat(ratio.getRatio20());
		}else if(customerAge>40) {
			rate = rate * Float.parseFloat(ratio.getRatio30());
		}else if(customerAge>50) {
			rate = rate * Float.parseFloat(ratio.getRatio40());
		}else if(customerAge>60) {
			rate =rate *  Float.parseFloat(ratio.getRatio50());
		}else {
			rate = rate * Float.parseFloat(ratio.getRatio60());
		}
		//job
		switch (job) {
		case "학생":
			rate = rate * Float.parseFloat(ratio.getStudentRatio());
			break;
		case "배달종사자":
			rate = rate * Float.parseFloat(ratio.getDeliverRatio());
			break;
		case "버스기사":
			rate = rate * Float.parseFloat(ratio.getBusDriverRatio());
			break;
		case "화물차운전자":
			rate = rate * Float.parseFloat(ratio.getFreightDriverRatio());
			break;
		case "기타사무직":
			rate = rate * Float.parseFloat(ratio.getEtcRatio());
			break;
		default:
			break;
		}
		return rate +"";
	}
	private void sisaning() {
		cnt = 0;
		Timer timer = new Timer();
		TimerTask task = new TimerTask() {
			@Override
			public void run() {
				if(cnt>=3) {
					timer.cancel();
					return;
				}
				System.out.println("시산작업 진행중... "); cnt++;
			}
		};
		timer.scheduleAtFixedRate(task, 0, 700); 
	}
	private void makeAccount(Customer customer) {
		System.out.println(" 현재 시스템에 고객님의 계좌가 등록되지 않았습니다 . 계좌 정보를 등록해주세요 .");
		Bank bank = new Bank();
		bank.setCustomerID(customer.getCustomerID());
		System.out.println("등록하실 계좌의 예금주명을 입력해 주세요 .");
		bank.setName(sc.nextLine());
		System.out.println("등록하실 계좌의 은행사를 선택해 주세요 .");
		System.out.println("(1) 농렵 (2) 신한 (3) 제일 (4) 국민 (5) 기업 ");
		switch(Integer.parseInt(sc.nextLine())) {
		case 1: bank.setBankCompany("농협");
			break;
		case 2:bank.setBankCompany("신한");
			break;
		case 3:bank.setBankCompany("제일");
			break;
		case 4:bank.setBankCompany("국민");
			break;
		case 5:bank.setBankCompany("기업");
			break;
		default :break;
		}
		System.out.println("등록하실 계좌의 계좌번호를 입력해 주세요 . ( 기입양식 : ooo-oooooo-oo-ooo ");		// 기입 양식 exception 처리 추가  

		String ac = sc.nextLine();
		bank.setAccountNum(ac);
		bank.setMoneyAmount("10000000");
		bankDao.create(bank);
		System.out.println("계좌가 등록되었습니다 . ");
	}
	private Customer askInterview(Customer customer) {
		if(customer.getIsInterview().equals("1")) {
			System.out.println("이미 면담이 진행중이거나 면담 처리가 완료된 상태입니다 . ");
			return customer;
		}
		System.out.println("< 면담 신청 메뉴 > ");
		
		String customerID = customer.getCustomerID();
		Car car = new Car();
		Interview interview = new Interview();
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date time = new Date();
		
		interview.setAskDate(format.format(time));
		interview.setCustomerID(customerID);
		interview.setIsProcessed("0");
		//Car 등록 및 Customer _ isInterview 업데이트 및 Interview_isProcessed 업데이트
		car.setCustomerID(customerID);
		System.out.println("면담 신청을 위해 고객 자차 정보를 등록해주세요 . ");
		System.out.println("차량번호를 입력해주세요 . ( 기입양식 : xxxx ) ");
		car.setCarNum(sc.nextLine());
		System.out.println("차량의 생산년도를 입력해주세요 . ( 기입양식 : xxxx ) ");
		car.setMadeYear(sc.nextLine());
		System.out.println("차량의 종류를 선택해주세요 . ");
		System.out.println("(1) 소형차 (2) 중형차 (3) 버스 (4) 화물차 (5) 이륜차 ");
		switch(Integer.parseInt(sc.nextLine())) {
		case 1:car.setCarType("small");break;
		case 2:car.setCarType("sedan");break;
		case 3:car.setCarType("bus"); break;
		case 4:car.setCarType("freight"); break;
		case 5:car.setCarType("motorCycle"); break;
		default: break;
		}
		carDao.create(car);
		interviewDao.create(interview);
		customerDao.update("isInterview", "1", customer);
		customer.setIsInterview("1");
		
		System.out.println(format.format(time)+" 에 ["+customer.getName()+"] 님의 면담 신청이 완료되었습니다 .");
		return customer;
	}
	private void createCustomer() {
		System.out.println("< 고객 등록 메뉴 >");
		System.out.println("다음 약관을 읽고 동의하시면 1번을 , 그렇지 않으면 2번을 눌러주세요 . ");
		System.out.println("- 약관 - ");
		System.out.println("~~약관내용~~");
		System.out.println(dd.DD);
		System.out.println("(1) 동의 (2) 동의X");
		
		switch (Integer.parseInt(sc.nextLine())) {
		case 1:
			Customer customer = new Customer();
			System.out.println(dd.DD);
			System.out.println("< 항목에 맞는 정보를 기입해주세요 >");
			System.out.println("가입하실 아이디의 ID를 입력해주세요 . ");	
			String id = sc.nextLine();
			Customer isExistInCustomerList = customerList.search(id);
			Customer isExistInSCustomerList = sCustomerList.search(id);
			while(isExistInCustomerList != null || isExistInSCustomerList != null) {
				System.out.println(" ! 이미 존재하는 ID입니다. 다시 입력해주세요 ! ");
				id = sc.nextLine();
				isExistInCustomerList = customerList.search(id);
				isExistInSCustomerList = sCustomerList.search(id);
			}
			customer.setCustomerID(id);
			System.out.println("가입하실 고객의 이름을 입력하세요 . "); 
			customer.setName(sc.nextLine());
			System.out.println("가입하실 고객의 주소를 입력하세요 . ");
			customer.setAddress(sc.nextLine());
			System.out.println("가입하실 고객의 나이를 입력하세요 . ");
			customer.setAge(sc.nextLine());
		
			System.out.println("가입하실 고객의 직업을 선택하세요 . ");
			System.out.println("(1) 학생 (2) 버스기사 (3) 화물차운전자 (4)배달종사자 (5)기타사무직");
			switch(Integer.parseInt(sc.nextLine())) {
			case 1:
				customer.setJob("학생");
				break;
			case 2:
				customer.setJob("버스기사");
				break;
			case 3:
				customer.setJob("화물차운전자");
				break;
			case 4:
				customer.setJob("배달종사자");
				break;
			case 5:
				customer.setJob("기타사무직");
				break;
			default : break;
			}
			
			System.out.println("가입하실 고객의 전화번호를 입력하세요 . ( 기입양식 : OOO-OOOO-OOOO) "); // 기입양식 예외처리 추가
			customer.setPhoneNumber(sc.nextLine());
			
			System.out.println("가입하실 고객의 성별을 선택하세요 . ");
			System.out.println("(1) 남성 (2) 여성 ");
			switch(Integer.parseInt(sc.nextLine())) {
			case 1:
				customer.setSex("남성");
				break;
			case 2:
				customer.setSex("여성");
				break;
			default : break;
			}
			
			System.out.println("가입하실 고객의 주민등록번호를 입력하세요 .( 기입양식 : OOOOOO-OOOOOOO ) "); // 기입양식 예외처리 추가
			customer.setSID(sc.nextLine());
			// isInterview : 0 at first
			customer.setIsInterview("0");
			customerDao.create(customer);
			sCustomerList.add(customer);
			System.out.println(dd.DD);
			System.out.println("가입 처리가 정상적으로 완료되었습니다 !");
			break;
		case 2:
			break;
		default:
			break;
		}
	}
	private void insuranceDesign() {
		
		Insurance insurance = new Insurance();
		
		System.out.println("< 보험 설계를 시작합니다 > ");
		
		System.out.println("설계하고자 하는 보험상품의 유형을 선택해 주세요 . ");
		System.out.println("1. 자동차 종합보험 ");
		
		int select = Integer.parseInt(sc.nextLine());
		if(select == 1) insurance.setProductType("Car Insurance");
		
		System.out.println("보험명을 입력해 주세요 . ");
		insurance.setInsuranceName(sc.nextLine());
		
		System.out.println("보험 기본 가입비를 입력해 주세요 . ");
		insurance.setInsuranceFee( Integer.parseInt(sc.nextLine()));
		
		int insuranceID = insuranceDao.create(insurance);
		if ( insuranceID == 0 ) return;
		
		insurance.setInsuranceID(insuranceID);
		//Coverage
		Coverage hCoverage = new Coverage();
		hCoverage.setCoverageCondition("high");
		System.out.println("사고위험 정도가 높은 경우의 보장금액을 입력해 주세요 . ");
		hCoverage.setCoverageCost(Integer.parseInt(sc.nextLine()));
		hCoverage.setInsuranceID(insuranceID);
		int hCoverageID = coverageDao.create(hCoverage);
		hCoverage.setCoverageID(hCoverageID);
		insurance.setHCoverage(hCoverage);
		
		Coverage mCoverage = new Coverage();
		mCoverage.setCoverageCondition("middle");
		System.out.println("사고위험 정도가 중간인 경우의 보장금액을 입력해 주세요 . ");
		mCoverage.setCoverageCost(Integer.parseInt(sc.nextLine()));
		mCoverage.setInsuranceID(insuranceID);
		int mCoverageID = coverageDao.create(mCoverage);
		mCoverage.setCoverageID(mCoverageID);
		insurance.setMCoverage(mCoverage);
		
		Coverage lCoverage = new Coverage();
		lCoverage.setCoverageCondition("low");
		System.out.println("사고위험 정도가 낮은 경우의 보장금액을 입력해 주세요 . ");
		lCoverage.setCoverageCost(Integer.parseInt(sc.nextLine()));
		lCoverage.setInsuranceID(insuranceID);
		int lCoverageID = coverageDao.create(lCoverage);
		lCoverage.setCoverageID(lCoverageID);
		insurance.setLCoverage(lCoverage);
		//Result
		Result result = new Result();
		result.setGoal("5");
		result.setInsuranceID(insuranceID+"");
		result.setNumSelled("0");
		result.setProblem(null);
		result.setStatement(null);
		insurance.setResult(result);
		resultDao.create(result);
		//Ratio 
		Ratio ratio = new Ratio();
		ratio.setInsuranceID(insuranceID);
		boolean end = false;
		System.out.println("보험료율 설정을 변경하려면 (1)을, 변경사항 없이 종료하시려면 (2)를 누르세요 .");
		if(Integer.parseInt(sc.nextLine())==1) end = false;
		else end = true;
		System.out.println(dd.DD);
		while (!end) {
			System.out.println("< 보험료율 설정 메뉴 >");
			System.out.println("설정하실 보험요율표를 선택하세요 . ");
			System.out.println("1.차종별 보험료율 2.직업별 보험료율 3.나이별 보험료율 4.성별 보험료율 5.요율설정 종료");
			switch (Integer.parseInt(sc.nextLine())) {
			case 1:
				ratio = setCarRatio(ratio,sc );
				break;
			case 2:
				ratio = setJobRatio(ratio,sc);
				break;
			case 3:
				ratio = setAgeRatio(ratio,sc);
				break;
			case 4:
				ratio = setSexRatio(ratio,sc);
				break;
			case 5: end = true;
				break;
			default:
				break;
			}
		}
		System.out.println(" ! 보험 설계가 완료되었습니다 ! ");
		ratioDao.create(ratio);
		insurance.setRatio(ratio);
		
		insuranceList.add(insurance);
	}
	private Ratio setCarRatio(Ratio ratio, Scanner sc) {

		System.out.println("- 차종별 보험요율표 -");
		System.out.println("1.소형차 : "+ratio.getSmallRatio());
		System.out.println("2.중형차 : "+ratio.getSedanRatio());
		System.out.println("3.화물차 : "+ratio.getFreightRatio());
		System.out.println("4.버스 : "+ratio.getBusRatio());
		System.out.println("5.이륜차 : "+ratio.getMotorCycleRatio());
		
		switch(Integer.parseInt(sc.nextLine())){
		case 1:
			System.out.println("설정하실 < 소형차 > 의 보험료율을 입력해주세요 .");
			ratio.setSmallRatio(sc.nextLine());
			break;
		case 2:
			System.out.println("설정하실 < 중형차 > 의 보험료율을 입력해주세요 . ");
			ratio.setSedanRatio(sc.nextLine());
			break;
		case 3:
			System.out.println("설정하실 < 화물차 > 의 보험료율을 입력해주세요 . ");
			ratio.setFreightRatio(sc.nextLine());
			break;
		case 4:
			System.out.println("설정하실 < 버스 > 의 보험료율을 입력해주세요 . ");
			ratio.setBusRatio(sc.nextLine());
			break;
		case 5:
			System.out.println("설정하실 < 이륜차 > 의 보험료율을 입력해주세요 .");
			ratio.setMotorCycleRatio(sc.nextLine());
			break;
		default:
			break;
		}
		return ratio;
	}
	private Ratio setJobRatio(Ratio ratio, Scanner sc) {
		System.out.println("- 직업별 보험요율표 -");
		System.out.println("1.학생 : "+ratio.getStudentRatio());
		System.out.println("2.버스기사 : "+ratio.getBusDriverRatio());
		System.out.println("3.배달종사자 : "+ratio.getDeliverRatio());
		System.out.println("4.화물기사 : "+ratio.getFreightDriverRatio());
		System.out.println("5.기타사무직 : "+ratio.getEtcRatio());
		
		switch(Integer.parseInt(sc.nextLine())) {
		case 1:
			System.out.println("설정하실 < 학생 > 의 보험료율을 입력해주세요 . ");
			ratio.setStudentRatio(sc.nextLine());
			break;
		case 2:
			System.out.println("설정하실 < 버스기사 > 의 보험료율을 입력해주세요 . ");
			ratio.setBusDriverRatio(sc.nextLine());
			break;
		case 3:
			System.out.println("설정하실 < 배달종사자 > 의 보험료율을 입력해주세요 . ");
			ratio.setDeliverRatio(sc.nextLine());
			break;
		case 4:
			System.out.println("설정하실 < 화물기사 > 의 보험료율을 입력해주세요 . ");
			ratio.setFreightDriverRatio(sc.nextLine());
		case 5:
			System.out.println("설정하실 < 기타사무직 > 의 보험료율을 입력해주세요 . ");
			ratio.setEtcRatio(sc.nextLine());
			break;
		default : break;
		}
		return ratio;
	}
	private Ratio setAgeRatio(Ratio ratio , Scanner sc) {
		System.out.println("- 나이별 보험요율표 -");
		System.out.println("1.20대 : "+ratio.getRatio20());
		System.out.println("2.30대 : "+ratio.getRatio30());
		System.out.println("3.40대 : "+ratio.getRatio40());
		System.out.println("4.50대 : "+ratio.getRatio50());
		System.out.println("5.60대 이상 : "+ratio.getRatio60());
		
		switch(Integer.parseInt(sc.nextLine())) {
		case 1:
			System.out.println("20세 이상 30세 미만의 보험료율을 설정해주세요 . ");
			ratio.setRatio20(sc.nextLine());
			break;
		case 2:
			System.out.println("30세 이상 40세 미만의 보험료율을 설정해주세요 . ");
			ratio.setRatio30(sc.nextLine());
			break;
		case 3:
			ratio.setRatio40(sc.nextLine());
			System.out.println("40세 이상 50세 미만의 보험료율을 설정해주세요 . ");
			break;
		case 4:
			ratio.setRatio50(sc.nextLine());
			System.out.println("50세 이상 60세 미만의 보험료율을 설정해주세요 . ");
			break;
		case 5:
			ratio.setRatio60(sc.nextLine());
			System.out.println("60세 이상의 보험료율을 설정해주세요 . ");
			break;
		default : 
			break;
		}
		return ratio;
	}
	private Ratio setSexRatio(Ratio ratio, Scanner sc) {
		System.out.println("- 성별 보험요율표 -");
		System.out.println("남성 : "+ratio.getMaleRatio());
		System.out.println("여성 : "+ratio.getFemaleRatio());
		
		switch(Integer.parseInt(sc.nextLine())) {
		case 1:
			System.out.println("남성의 보험료율을 입력해주세요 . ");
			ratio.setMaleRatio(sc.nextLine());
			break;
		case 2:
			System.out.println("여성의 보험료율을 입력해주세요 . ");
			ratio.setFemaleRatio(sc.nextLine());
			break;
		default:break;
		}
		
		return ratio;
	}
	private void insuranceConfirm() {
		ArrayList<Insurance> insurances = insuranceDao.retrieveNoConfirm().getInsuranceList();
		
		System.out.println("- 보험목록 -");
		for(Insurance insurance : insurances) {
			System.out.println(insurance.getInsuranceID() + " " +insurance.getInsuranceName());
		}
		System.out.println("확정을 진행할 보험 ID를 선택해주세요 . ");
		Insurance insurance = insuranceList.search(Integer.parseInt(sc.nextLine()));
		while(insurance == null) {
			System.out.println(" 존재하지 않는 보험 ID입니다 . ");
			insurance = insuranceList.search(Integer.parseInt(sc.nextLine()));
		}
		System.out.println(dd.DD);
		System.out.println("- 보험정보 -");
		System.out.println("보험 유형 : "+insurance.getProductType()) ;
		System.out.println("보험 ID : "+ insurance.getInsuranceID());
		System.out.println("보험명 : " + insurance.getInsuranceName());
		System.out.println("보험 기본 가입비 : "+insurance.getInsuranceFee());
		System.out.println("보험 보장 ( 상 ) : " + insurance.getHCoverage().getCoverageCost());
		System.out.println("보험 보장 ( 중 ) : " + insurance.getMCoverage().getCoverageCost());
		System.out.println("보험 보장 ( 하 ) : " + insurance.getLCoverage().getCoverageCost());
		System.out.println(dd.DD);
		
		System.out.println(" 1. 보험 확정 2. 보험 반려 ");
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date time = new Date();
		Confirm confirm = new Confirm();
		
		switch(Integer.parseInt(sc.nextLine())) {
		case 1: 
			confirm.setInsuranceID(insurance.getInsuranceID());
			confirm.setConfirmed(1);
			confirm.setConfirmDate(format.format(time));
			System.out.println("! "+format.format(time)+"에 "+insurance.getInsuranceName() + " 보험이 확정되었습니다 !");
			insurance.setConfirm(confirm);
			insuranceList.delete(insurance.getInsuranceID());
			confirmDao.create(confirm);
			break;
		case 2:
			System.out.println("< 보험을 반려하셨습니다 >");
			confirm.setInsuranceID(insurance.getInsuranceID());
			confirm.setConfirmed(0);
			confirm.setConfirmDate(format.format(time));
			System.out.println("반려 사유를 입력해주세요 . ");
			confirm.setConfirmRefuse(sc.nextLine());
			System.out.println("! "+format.format(time)+" 에 "+insurance.getInsuranceName()+" 보험을 반려하였습니다 !");
			insuranceList.delete(insurance.getInsuranceID());
			confirmDao.create(confirm);
			break;
		default:break;
		}
	}
	private void salesInsuranceList() {
		System.out.println(dd.DD);
		System.out.println("1. 확정된 보험리스트 확인 2. 반려된 보험리스트 확인 ");
		switch(Integer.parseInt(sc.nextLine())) {
		case 1:
			System.out.println("- 보험목록 -");
			ArrayList<Insurance> insurances = insuranceDao.retrieveConfirm().getInsuranceList();
			for(Insurance insurance : insurances) {
				System.out.println(insurance.getInsuranceID() + " " + insurance.getInsuranceName());
			}
			System.out.println(" 확인하실 보험의 ID를 입력하세요 . ");
			Insurance insurance = insuranceList.search(Integer.parseInt(sc.nextLine()));
			while ( insurance == null ) {
				System.out.println("존재하지 않는 보험 ID를 입력하셨습니다. 다시 입력해주세요.");
				insurance = insuranceList.search(Integer.parseInt(sc.nextLine()));
			}
			System.out.println(dd.DD);
			System.out.println("< 보험정보 >");
			System.out.println("보험 유형 : "+insurance.getProductType()) ;
			System.out.println("보험 ID : "+ insurance.getInsuranceID());
			System.out.println("보험명 : " + insurance.getInsuranceName());
			System.out.println("보험 기본 가입비 : "+insurance.getInsuranceFee());
			System.out.println("보험 보장 ( 상 ) : " + insurance.getHCoverage().getCoverageCost());
			System.out.println("보험 보장 ( 중 ) : " + insurance.getMCoverage().getCoverageCost());
			System.out.println("보험 보장 ( 하 ) : " + insurance.getLCoverage().getCoverageCost());
			Confirm confirm = confirmDao.retrieveByInsuranceID(insurance.getInsuranceID());
			System.out.println("보험 확정일 : "+confirm.getConfirmDate());
			break;
		case 2:
			System.out.println("- 보험목록 -");
			ArrayList<Insurance> insurances2 = insuranceDao.retrieveNotConfirmed().getInsuranceList();
			for(Insurance insurance2 : insurances2) {System.out.println(insurance2.getInsuranceID()+ " " + insurance2.getInsuranceName());}
			System.out.println("확인하실 보험의 ID를 입력하세요 . ");
			Insurance insurance2 = insuranceList.search(Integer.parseInt(sc.nextLine()));
			while ( insurance2 == null ) {
				System.out.println("존재하지 않는 보험 ID를 입력하셨습니다. 다시 입력해주세요.");
				insurance2 = insuranceList.search(Integer.parseInt(sc.nextLine()));
			}
			System.out.println(dd.DD);
			System.out.println("< 보험정보 >");
			System.out.println("보험 유형 : "+insurance2.getProductType()) ;
			System.out.println("보험 ID : "+ insurance2.getInsuranceID());
			System.out.println("보험명 : " + insurance2.getInsuranceName());
			System.out.println("보험 기본 가입비 : "+insurance2.getInsuranceFee());
			System.out.println("보험 보장 ( 상 ) : " + insurance2.getHCoverage().getCoverageCost());
			System.out.println("보험 보장 ( 중 ) : " + insurance2.getMCoverage().getCoverageCost());
			System.out.println("보험 보장 ( 하 ) : " + insurance2.getLCoverage().getCoverageCost());
			Confirm notConfirm = confirmDao.retrieveByInsuranceID(insurance2.getInsuranceID());
			System.out.println("보험 반려일 : "+notConfirm.getConfirmDate());
			System.out.println("반려 사유 : " +notConfirm.getConfirmRefuse());
		default:break;
		}
		
		
	}
	private int checkEmployee(Employee emp) {
		String strToken[] = emp.getName().split("_");
		String str = strToken[0];
		if(str.equals("InsuranceDesigner")) return 1;	
		else if(str.equals("InsuranceConfirmer")) return 2;
		else if(str.equals("SalesPerson")) return 3;
		else if(str.equals("UW")) return 4; 
 		return 0;
	}
}
