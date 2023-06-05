package AppConfig;

import java.beans.beancontext.BeanContextMembershipEvent;
import java.sql.Time;
import java.text.BreakIterator;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

import javax.imageio.spi.RegisterableService;
import javax.swing.plaf.ActionMapUIResource;
import javax.swing.text.DefaultEditorKit.CutAction;

import com.apple.eawt.event.GestureUtilities;
import com.mysql.cj.exceptions.StatementIsClosedException;
import com.mysql.cj.x.protobuf.MysqlxDatatypes.Array;
import com.mysql.cj.xdevapi.DbDoc;
import com.mysql.cj.xdevapi.SqlUpdateResult;

import Accident.Accident;
import Accident.AccidentList;
import Contract.Contract;
import Contract.ContractList;
import Contract.ContractListImpl;
import Customer.Bank;
import Customer.Car;
import Customer.Customer;
import Customer.CustomerList;
import Customer.CustomerListImpl;
import Dao.AccidentDao;
import Dao.AccidentDaoImpl;
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
import Dao.JudgeDao;
import Dao.JudgeDaoImpl;
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
import Judge.Judge;
import Judge.judgeList;
import Mail.Mail;
import Mail.MailList;
import Mail.MailListImpl;
import Sales.sales;

public class Main {
	private static int cnt;
	private static int form;

	private static Employee employee;
	private static InsuranceList insuranceList;
	private static EmployeeList employeeList;
	private static CustomerList customerList;
	private static CustomerList sCustomerList;
	private static InterviewList interviewList;
	private static InterviewList sInterviewList;
	private static ContractList contractList;
	private static MailList mailList;
	private static AccidentList accidentList;
	private static judgeList judgeList;
	// dao
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
	private static AccidentDao accidentDao;
	private static JudgeDao judgeDao;

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
		accidentDao = new AccidentDaoImpl();
		judgeDao = new JudgeDaoImpl();
	}

	public static void setDB() {
		employeeList = employeeDao.retrieve();
		insuranceList = insuranceDao.retrieve();
		customerList = customerDao.retrieve();
		sCustomerList = customerDao.retrieveSCustomer();
		interviewList = interviewDao.retrieveProcessed();
		sInterviewList = interviewDao.retrieveNoProcessed(); // 예비 인터뷰 리스트
		contractList = contractDao.retrieve();
		accidentList = accidentDao.retrieve();
		judgeList = judgeDao.retrieve();
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
		switch (loginNum) {
		case 1:
			System.out.println(dd.DD);
			runEmployeeSystem();
			break;
		case 2:
			System.out.println(dd.DD);
			runCustomerSystem();
			break;
		default:
			break;
		}
	}

	private void runEmployeeSystem() {
		boolean end = false;
		System.out.println("직원 아이디를 입력하세요 . ");
		employee = employeeList.search(Integer.parseInt(sc.nextLine()));
		while (employee == null) {
			System.out.println("잘못된 ID를 입력하셨습니다. 다시 입력해주세요 . ");
			employee = employeeList.search(Integer.parseInt(sc.nextLine()));
		}
		System.out.println(employee.getName().split("_")[1] + "님 반갑습니다 .");

		int menu = checkEmployee(employee);
		for (int i = 0; i < 6; i++) {
			if (end)
				break;
			switch (menu) {
			case 1:
				// 보험설계
				System.out.println(dd.DD);
				System.out.println("< 보험 설계 메뉴 >");
				System.out.println("1. 보험 설계하기 ");
				System.out.println("2. 종료하기 ");
				int designSubmenu = Integer.parseInt(sc.nextLine());
				System.out.println(dd.DD);
				switch (designSubmenu) {
				case 1:
					insuranceDesign();
					break;
				case 2:
					end = true; // end = true :시스템 종ㄹ
					System.out.println("보험사 시스템을 종료합니다 . ");
					break;
				default:
					break;
				}
				break;
			case 2:
				// 보험확정
				System.out.println(dd.DD);
				System.out.println("< 보험 확정 및 반려 메뉴 >");
				System.out.println("1. 보험 확정/반려하기 ");
				System.out.println("2. 종료하기 ");
				int confirmSubmenu = Integer.parseInt(sc.nextLine());
				switch (confirmSubmenu) {
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
				// 영업사원
				System.out.println(dd.DD);
				System.out.println("< 보험 영업 메뉴 >");
				System.out.println("1. 보험 리스트 확인하기 ");
				System.out.println("2. 고객 면담하기 ");
				System.out.println("3. 계약 체결하기 ");
				System.out.println("4. 실적 확인하기 ");
				System.out.println("5. 종료하기 ");
				int salesSubmenu = Integer.parseInt(sc.nextLine());
				switch (salesSubmenu) {
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
					System.out.println(dd.DD);
					checkPerformance(employee);
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
				// UW
				System.out.println(dd.DD);
				System.out.println("< Under Writer 메뉴 > ");
				System.out.println("1. 인수 심사하기 ");
				System.out.println("2. 종료하기 ");
				switch (Integer.parseInt(sc.nextLine())) {
				case 1:
					System.out.println(dd.DD);
					insuranceExam(employee);
					break;
				case 2:
					end = true;
					System.out.println("보험사 시스템을 종료합니다 . ");
					break;
				default:
					break;
				}
				break;
			case 5:
				// ContractHandler
				System.out.println(dd.DD);
				System.out.println("< 계약 관리자 메뉴 > ");
				System.out.println("1. 보험료 미납고객 관리하기 ");
				System.out.println("2. 만기계약 관리하기 ");
				System.out.println("3. 보험상품 판매실적 작성하기 "); // 보험상품 사후처리 -> 보험 상품 판매 실적 작성하기로 변경
				System.out.println("4. 종료하기");
				switch (Integer.parseInt(sc.nextLine())) {
				case 1:
					System.out.println(dd.DD);
					handleUnpaied(employee);
					break;
				case 2:
					System.out.println(dd.DD);
					handleEndContract(employee);
					break;
				case 3:
					System.out.println(dd.DD);
					handleInsurance(employee);
					break;
				default:

					break;
				}
				break;
			case 7:
				System.out.println(dd.DD);
				System.out.println("< 면책 담당자 메뉴 >");
				System.out.println("1. 손해 조사 및 면책 판단하기");
				System.out.println("2. 종료하기");
				if(sc.nextLine().equals("1")) {
					System.out.println(dd.DD);
					immunityHandler(employee);
				}else {
					System.out.println("시스템을 종료합니다 . ");
					end = true;
				}
				break;

			case 8:
				accidentHandler(employee);
				end = true;
				break;
			default:
				break;
			}
		}
	}
	private void handleInsurance(Employee employee) { // 판매실적 작성한다 
		// 보험 Goal , numSelled 기반으로 달성률(statement) 작성 , 미달성 , 달성 , 초과달성
		// 6개월 지난 보험의 statement 가 미달일 시 -> 보험 문제점 작성 .
		ArrayList<Insurance> insurances = insuranceDao.retrieveConfirm().getInsuranceList();
		System.out.println("- 보험 목록 - \n");
		String insuranceConfirmDate[] = new String[100];
		for (Insurance insurance : insurances) {
			System.out.println("보험 ID : " + insurance.getInsuranceID() + " | 보험명 : " + insurance.getInsuranceName()
					+ " | 보험 확정일 : [ " + confirmDao.retrieveByInsuranceID(insurance.getInsuranceID()).getConfirmDate()
					+ " ]");
			insuranceConfirmDate[insurance.getInsuranceID()] = confirmDao
					.retrieveByInsuranceID(insurance.getInsuranceID()).getConfirmDate();
		}
		System.out.println("\n판매 실적 작성 및 수정을 원하시는 보험의 보험ID를 입력해주세요 . ");
		int selecNum = Integer.parseInt(sc.nextLine()); // NumberFormatException
		while (insuranceConfirmDate[selecNum] == null) {
			System.out.println("존재하지 않는 보험 ID입니다 . 다시 입력해 주세요 . ");
			selecNum = Integer.parseInt(sc.nextLine());
		}
		System.out.println(dd.DD);
		// 보험상품 사후처리를 한다 E2
		try {
			Insurance insurance = insuranceList.search(selecNum);
			Result result = resultDao.retrieveByInsuranceID(selecNum + "");
			System.out.println("< 보험 상세 >");
			System.out.println("보험 ID : " + insurance.getInsuranceID());
			System.out.println("보험 명 : " + insurance.getInsuranceName());
			System.out.println("보험 확정일 : [ " + insuranceConfirmDate[selecNum] + " ]");
			System.out.println("판매 실적 / 목표 실적 : [ " + result.getNumSelled() + " / " + result.getGoal() + " ]");
			String stmt = result.getStatement();
			if (!stmt.equals("미달성") || !stmt.equals("달성") || !stmt.equals("초과달성")) stmt = "미기입";
			System.out.println("달성 구분 : " + stmt);
			// stmt : 미달성 이면서 6개월 이상 ... 
			System.out.println("판단 문제점 : "+ result.getProblem());
			System.out.println(dd.DD);
			System.out.println("[ "+insurance.getInsuranceName() +" ]보험의 달성률을 기입 해주세요 .( 기입 양식 : '미달성' , '달성' , '초과달성' )");
			stmt = sc.nextLine();
			while(!stmt.equals("미달성") && !stmt.equals("달성")  && !stmt.equals("초과달성")) { //예외흐름
				System.out.println("올바른 값을 다시 기입해주세요 . ");
				stmt = sc.nextLine();
			}
			// 현재 날짜와 보험이 확정된 날짜 비교 후 , (6개월 이상 && 달성구분 : 미달) 인 보험의 문제점 작성
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			Date time = new Date();
			String nowDate = format.format(time);
			String confirmDate = insuranceConfirmDate[selecNum];
			LocalDate date1 = LocalDate.parse(nowDate);
			LocalDate date2 = LocalDate.parse(confirmDate);
			long daysBetween = ChronoUnit.DAYS.between(date1, date2);
			String problem = "미기입";
			if(stmt.equals("미달성") && daysBetween < -180 ) {// 6개월 이상 '미달' 인 보험인 경우 
				System.out.println("6개월 이상 보험 달성목표를 달성하지 못한 보험입니다 . 판단 문제점을 작성해주세요 . ");
				problem = sc.nextLine();
			}
			resultDao.update("statement",stmt,result);
			resultDao.update("problem",problem,result);
			System.out.println("해당 보험의 판매실적 작성 및 수정이 완료되었습니다 . \n");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("예기치 못한 오류가 발생했습니다. 다시 진행해주시기 바랍니다.");
			handleInsurance(employee);
		}
	}
	private void handleEndContract(Employee employee) {
		/*
		 * / 만기일 지난 보험 (1) 보험 연장 : 메일 발송 , 1.2.3 년 연장 택 (2) 보험 해지 : 보험 해지 및 메일 발송
		 */
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date time = new Date();
		String nowDate = format.format(time);
		ArrayList<Contract> contracts = contractDao.retrieveContractByProcess("3").getContractList();
		int ends[] = new int[100];
		System.out.println("< 계약 만료 보험 리스트 > \n");
		// 만기계약관리한다 E1
		try {
			for (Contract contract : contracts) {
				String endDate = makeDateForm(contract.getEndDate());
				LocalDate date1 = LocalDate.parse(nowDate);
				LocalDate date2 = LocalDate.parse(endDate);
				long daysBetween = ChronoUnit.DAYS.between(date1, date2);
				if (daysBetween < 0) {
					System.out.println("계약 ID : " + contract.getContractID() + " | 계약자 명 : "
							+ customerList.search(contract.getCustomerID()).getName() + " | 만기일 : [ " + endDate + " ]");
					ends[contract.getContractID()] = 1;
				}
			}
		} catch (Exception e) {
			System.out.println("죄송합니다. 현재 데이터를 읽어올 수 없습니다. 프로그램을 종료합니다.");
			System.exit(0);
		}
		System.out.println("\n관리하실 만기 계약의 ID를 입력해주세요 . ");
		int selecNum = Integer.parseInt(sc.nextLine());
		Contract contract = contractList.search(selecNum);
		while (contract == null || ends[selecNum] == 0) { // 존재하지 않는 계약 ID를 입력하였을 시 . OR 만료된 계약이 아닐 시
			System.out.println("존재하지 않거나 만료되지 않은 계약입니다 . 올바른 값을 입력해주세요 . ");
			selecNum = Integer.parseInt(sc.nextLine());
			contract = contractList.search(selecNum);
		}
		System.out.println(dd.DD);
		System.out.println("[ " + contract.getContractID() + " ] 계약을 선택하셨습니다 . 관리하실 메뉴를 선택해주세요 . \n ");
		System.out.println("(1) 보험 계약 해지 (2) 보험 재계약  ");
		String selectString = sc.nextLine();
		if (selectString.equals("1")) {
			System.out.println(
					"정말로 [ " + insuranceList.search(Integer.parseInt(contract.getInsuranceID())).getInsuranceName()
							+ " ] 보험의 계약을 해지하시겠습니까 ? \n(1) 예 (2) 아니오  ");
			try {
			String fireString = sc.nextLine();
			if (fireString.equals("1")) { // contractDao에서 삭제 , Mail 발송
				System.out.println("해당 보험 계약을 해지가 완료되었습니다 . ");
				String title = "만기 계약 해지 안내 ";
				String content = "[ "
						+ insuranceList.search(Integer.parseInt(contract.getInsuranceID())).getInsuranceName()
						+ " ] 보험 계약의 만료로 [ " + nowDate + " ] 부로 계약이 해지되었음을 알려드립니다 . ";
				Mail mail = new Mail();
				// 우편물 발송한다
				try {
					mail.setTitle(title);
					mail.setContent(content);
					mail.setCustomerID(contract.getCustomerID());
					mail.setDate(nowDate);
					mail.setEmployeeID(employee.getName());
					mailDao.create(mail);
					contractDao.delete(contract);
				} catch (Exception e) {
					System.out.println("죄송합니다. 현재 정보를 전송할 수 없습니다. 잠시 뒤에 다시 시도해 주십시오.");
					System.out.println(e.getMessage());
				}
			} else if (fireString.equals("2")) {
				System.out.println("해당 보험 계약의 해지를 취소합니다 . ");
			} else {
				System.out.println("올바른 입력이 아닙니다 . 계약관리자 메뉴로 돌아갑니다 .  ");
			}
			}catch(Exception e){
				System.out.println( "전산상 문제로 해지가 진행되지 않았습니다. 잠시 후 다시 시도해 주세요 ");
				System.out.println("시스템을 종료합니다.");
				System.exit(0);
			}

		} else if (selectString.equals("2")) {
			String title = "[ " + insuranceList.search(Integer.parseInt(contract.getInsuranceID())).getInsuranceName()
					+ " ] 보험 계약 만기 연장 안내 ";
			String content = "";
			System.out.println("해당 보험을 재계약합니다 . 계약 기간을 설정해주세요 . ");
			System.out.println("(1) 1년 (2) 2년 (3) 3년 ");
			// 만기계약 관리한다. E2
			try {
				while (true) {
					String contractNum = sc.nextLine();
					if (contractNum.equals("1")) {
						System.out.println("해당 보험의 계약을 1년 연장합니다 . ");
						String nowEndDate = makeDateForm(contract.getEndDate());
						String endDate = setEndDate(nowEndDate, 1);
						endDate = endDate.split("-")[0] + endDate.split("-")[1] + endDate.split("-")[2];
						contractDao.update("endDate", endDate, contract);
						content = "[ "
								+ insuranceList.search(Integer.parseInt(contract.getInsuranceID())).getInsuranceName()
								+ " ] 보험의 계약 만료일이 [ " + makeDateForm(endDate) + " ] 로 연장되었음을 알려드립니다 . ";
						break;
					} else if (contractNum.equals("2")) {
						System.out.println("해당 보험의 계약을 2년 연장합니다 . ");
						String nowEndDate = makeDateForm(contract.getEndDate());
						String endDate = setEndDate(nowEndDate, 2);
						endDate = endDate.split("-")[0] + endDate.split("-")[1] + endDate.split("-")[2];
						contractDao.update("endDate", endDate, contract);
						content = "[ "
								+ insuranceList.search(Integer.parseInt(contract.getInsuranceID())).getInsuranceName()
								+ " ] 보험의 계약 만료일이 [ " + makeDateForm(endDate) + " ] 로 연장되었음을 알려드립니다 . ";
						break;
					} else if (contractNum.equals("3")) {
						System.out.println("해당 보험의 계약을 3년 연장합니다 . ");
						String nowEndDate = makeDateForm(contract.getEndDate());
						String endDate = setEndDate(nowEndDate, 3);
						endDate = endDate.split("-")[0] + endDate.split("-")[1] + endDate.split("-")[2];
						contractDao.update("endDate", endDate, contract);
						content = "[ "
								+ insuranceList.search(Integer.parseInt(contract.getInsuranceID())).getInsuranceName()
								+ " ] 보험의 계약 만료일이 [ " + makeDateForm(endDate) + " ] 로 연장되었음을 알려드립니다 . ";
						break;
					} else {
						System.out.println("올바른 값이 아닙니다 . 다시 입력해주세요 . ");
					}
				}
			} catch (Exception e) {
				System.out.println("죄송합니다. 현재 데이터를 읽어올 수 없습니다. 프로그램을 종료합니다.");
				System.exit(0);
			}
			Mail mail = new Mail();
			try {
				mail.setTitle(title);
				mail.setContent(content);
				mail.setDate(nowDate);
				mail.setEmployeeID(employee.getName());
				mail.setCustomerID(contract.getCustomerID());
				mailDao.create(mail);
			} catch (Exception e) {
				System.out.println("죄송합니다. 현재 정보를 전송할 수 없습니다. 잠시 뒤에 다시 시도해 주십시오.");
				System.out.println(e.getMessage());
			}

		} else {
			System.out.println("올바른 값이 아닙니다 .  계약 리스트로 돌아갑니다  . \n");
			System.out.println(dd.DD);
			handleEndContract(employee);
		}

	}

	private void handleUnpaied(Employee employee) { // 3일 이상 미납 : 독촉 메일 발송 , 한달 이상 미납 : 보험 해지 시키기 및 메일 발송
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date time = new Date();
		String nowDate = format.format(time);
		ArrayList<Contract> contracts = contractDao.retrieveContractByProcess("3").getContractList();
		int days3[][] = new int[100][2];
		int days30[][] = new int[100][2];
		// 보험료 미납고객 관리한다. E1
		try {
			System.out.println("< 3일 이상 미납 고객 리스트 > \n");
			for (Contract contract : contracts) {
				String payDate = makeDateForm(contract.getPayDate());
				LocalDate date1 = LocalDate.parse(nowDate);
				LocalDate date2 = LocalDate.parse(payDate);
				long daysBetween = ChronoUnit.DAYS.between(date1, date2);
				if (daysBetween <= -3 && daysBetween > -30) {
					System.out.println("계약 ID : " + contract.getContractID() + " | 계약자 명 : "
							+ customerList.search(contract.getCustomerID()).getName() + " | 미납 기간 : "
							+ Math.abs(daysBetween) + "일");
					days3[contract.getContractID()][0] = 1; // days[계약번호][0] : 1일 시 해당 계약은 미납기간이 3일 이상인 계약 .
					days3[contract.getContractID()][1] = (int) Math.abs(daysBetween);// days[계약번호][1] : 미납 일수 삽입 .
				}
			}
			System.out.println("\n< 30일 이상 미납 고객 리스트 >\n"); // 해지대상
			for (Contract contract : contracts) {
				String payDate = makeDateForm(contract.getPayDate());
				LocalDate date1 = LocalDate.parse(nowDate);
				LocalDate date2 = LocalDate.parse(payDate);
				long daysBetween = ChronoUnit.DAYS.between(date1, date2);
				if (daysBetween <= -30) {
					System.out.println("계약 ID : " + contract.getContractID() + " | 계약자 명 : "
							+ customerList.search(contract.getCustomerID()).getName() + " | 미납 기간 : "
							+ Math.abs(daysBetween) + "일");
					days30[contract.getContractID()][0] = 1; // days[계약번호] : 1
					days30[contract.getContractID()][1] = (int) Math.abs(daysBetween);
				}
			}
		} catch (Exception e) {
			System.out.println("죄송합니다. 현재 데이터를 읽어올 수 없습니다.");
			runEmployeeSystem();
		}
		System.out.println("\n관리하실 미납 계약 번호를 선택해주세요 . ");
		int selectNum = Integer.parseInt(sc.nextLine()); // exception :Numberformat
		Contract contract = contractList.search(selectNum);
		while (contract == null) {
			System.out.println("올바른 계약 번호를 입력해주세요 . ");
			selectNum = Integer.parseInt(sc.nextLine());
			contract = contractList.search(selectNum);
		}
		if (days3[contract.getContractID()][0] == 1) {
			System.out.println(dd.DD);
			System.out.println("\n계약 ID : " + contract.getContractID() + " \n계약자 명 : "
					+ customerList.search(contract.getCustomerID()).getName() + " \n미납 기간 : "
					+ days3[contract.getContractID()][1] + "일" + " \n");
			System.out.println("해당 보험은 미납 기간이 3일 이상인 상태입니다 ." + customerList.search(contract.getCustomerID()).getName()
					+ " 고객에게 체납 독촉 메일을 발송합니다 . ");
			String title = insuranceList.search(Integer.parseInt(contract.getInsuranceID())).getInsuranceName()
					+ " 보험 보험료 납입 안내 ";
			String content = insuranceList.search(Integer.parseInt(contract.getInsuranceID())).getInsuranceName()
					+ " 보험의 미납 기간이 3일 이상인 상태입니다 . 장기 미납 시 계약 해지 또는 휴면 등록 될 수 있으니 빠른 시일 내에 납입 바랍니다 .  ";
			Mail mail = new Mail();
			try {
				mail.setContent(content);
				mail.setTitle(title);
				mail.setCustomerID(contract.getCustomerID());
				mail.setDate(nowDate);
				mail.setEmployeeID(employee.getName() + "");
				mailDao.create(mail);
			} catch (Exception e) {
				System.out.println("죄송합니다. 현재 정보를 전송할 수 없습니다. 잠시 뒤에 다시 시도해 주십시오.");
				System.out.println(e.getMessage());
			}
		} else if (days30[contract.getContractID()][0] == 1) {
			System.out.println(dd.DD);
			System.out.println("\n계약 ID : " + contract.getContractID() + " \n계약자 명 : "
					+ customerList.search(contract.getCustomerID()).getName() + " \n미납 기간 : "
					+ days30[contract.getContractID()][1] + "일\n");
			System.out.println("해당 계약은 미납 기간이 30일 이상인 상태입니다 . 휴면 계약 등록 혹은 계약 해지를 진행해주세요. ");
			System.out.println("(1) 계약 해지  (2) 휴면 계약 등록 ");
			if (sc.nextLine().equals("1")) { // 계약파기 (Delete) & Mail
				System.out.println("해당 계약을 해지합니다 . ");
				String title = insuranceList.search(Integer.parseInt(contract.getInsuranceID())).getInsuranceName()
						+ " 보험 계약 해지 안내 ";
				String content = insuranceList.search(Integer.parseInt(contract.getInsuranceID())).getInsuranceName()
						+ " 보험 미납 기간 30일 초과로 해당 보험이 <해지> 되었음을 알려드립니다 . ";
				Mail mail = new Mail();
				try {
					mail.setContent(content);
					mail.setTitle(title);
					mail.setCustomerID(contract.getCustomerID());
					mail.setDate(nowDate);
					mail.setEmployeeID(employee.getName() + "");
					mailDao.create(mail);
					contractDao.delete(contract);
				} catch (Exception e) {
					System.out.println("죄송합니다. 현재 정보를 전송할 수 없습니다. 잠시 뒤에 다시 시도해 주십시오.");
					System.out.println(e.getMessage());
				}
			} else if (sc.nextLine().equals("2")) { // 계약 휴면 등록 후 contract table ( ProcessNum) -> 5로 변경 . & Mail
				System.out.println("해당 계약을 휴면상태로 변경합니다 . ");
				String title = insuranceList.search(Integer.parseInt(contract.getInsuranceID())).getInsuranceName()
						+ " 보험 계약 휴면 상태 등록 안내 ";
				String content = insuranceList.search(Integer.parseInt(contract.getInsuranceID())).getInsuranceName()
						+ " 보험 미납 기한 30일 초과로 해당 보험이 <휴면> 상태로 변환되었음을 알려드립니다 . ";
				Mail mail = new Mail();
				try {
					mail.setContent(content);
					mail.setTitle(title);
					mail.setCustomerID(contract.getCustomerID());
					mail.setDate(nowDate);
					mail.setEmployeeID(employee.getName() + "");
					mailDao.create(mail);
					contractDao.update("processNum", "5", contract);
				} catch (Exception e) {
					System.out.println("죄송합니다. 현재 정보를 전송할 수 없습니다. 잠시 뒤에 다시 시도해 주십시오.");
					System.out.println(e.getMessage());
				}
			} else {
				System.out.println("기입 오류입니다 .");
			}

		} else {// contract DB의 정상 납부 계약을 선택했을 때
			System.out.println("서버 오류입니다 . 다음에 다시 시도해주세요 . ");
		}

	}

	private void checkPerformance(Employee employee) { // 실적순으로 나열 / ID 입력으로 상세보기,체결한 계약 출력
		System.out.println("< 보험사 직원 계약 체결 실적 >\n");
		HashMap<String, Integer> employeeMap = new HashMap<>();
		ArrayList<Employee> employees = employeeDao.retrieve().getEmployeeList();
		ArrayList<Contract> contracts = contractDao.retrieve().getContractList();
		for (Employee e : employees) {
			String eID = e.getEmployeeID() + "";
			if (eID.subSequence(0, 1).equals("2")) { // 첫 자리수가 2이면 영업사원 //모든 영업사원을 employeeHashMap에 추가
				employeeMap.put(eID, 0); // V 는 0으로 시작 .
			} else {
				// 실적확인한다 A1
				System.out.println("ID가 틀렸습니다. 다시 시도해 주세요.");
				runEmployeeSystem();
			}
		}
		for (Contract contract : contracts) {
			String eID = contract.getEmployeeID(); // 계약에서 employeeID를 불러온 후 ,해당 계약 체결한 employeehashMap(Key) 벨류 ++
			if (employeeMap.containsKey(eID)) { // 체결된 계약이 아니면 , 불러온 eID가 Null 이므로 ,contains 로 확인
				int sellCnt = employeeMap.get(eID) + 1;
				employeeMap.replace(eID, sellCnt);
			}
		}
		// 정렬
		List<Map.Entry<String, Integer>> entryList = new ArrayList<>(employeeMap.entrySet());
		entryList.sort((entry1, entry2) -> entry2.getValue().compareTo(entry1.getValue()));
		LinkedHashMap<String, Integer> sortedEmpMap = new LinkedHashMap<>();
		for (Map.Entry<String, Integer> entry : entryList)
			sortedEmpMap.put(entry.getKey(), entry.getValue());
		form = 0; // 순위
		sortedEmpMap.forEach((k, v) -> {// 람다로 출력 .
			System.out.println("판매 순위 : " + getForm() + " | 직원명 : "
					+ employeeList.search(Integer.parseInt(k)).getName().split("_")[1] + " | " + "직원 ID : " + k + " | "
					+ "체결 보험 수 : " + v);
		});
		form = 0;
		System.out.println("\n상세보기를 원하시는 직원의 ID를 입력해주세요 . ");
		int selectNum = Integer.parseInt(sc.nextLine()); // (예외흐름)NumberFormat ExceptioN 처리
		employee = employeeList.search(selectNum);
		while (employee == null) { // 잘못 입력 시
			System.out.println("입력 오류입니다 . 올바른 ID를 입력해 주세요 . ");
			employee = employeeList.search(Integer.parseInt(sc.nextLine()));
		}
		System.out.println(dd.DD);
		System.out.println("- " + employee.getName().split("_")[1] + " 체결 계약 목록 -\n");
		for (Contract contract : contracts) {
			if (contract.getEmployeeID().equals(employee.getEmployeeID() + "")
					&& contract.getProcessNum().equals("3")) {
				System.out.println("보험 계약자 명 : " + customerList.search(contract.getCustomerID()).getName()
						+ " | 계약 ID : " + contract.getContractID() + " | 보험명 : "
						+ insuranceList.search(Integer.parseInt(contract.getInsuranceID())).getInsuranceName()
						+ " | 체결일 : " + contract.getStartDate() + " | 만기일 : " + makeDateForm(contract.getEndDate())
						+ " | ");
			}
		}
		System.out.println("\n(1) 확인 \n");
		sc.nextLine();
	}

	private int getForm() {
		form += 1;
		return form;
	}

	private void insuranceExam(Employee employee) { // UW - 인수 심사하다
		/*
		 * 보험의 Result / 직원의 numSelled / Mail ... 인수 & 반려 후 변경 사항 - 인수 시 - 1. 고객에게 메일 보내기
		 * 2. Contract DB ( ProcessNum -> 3 으로 Update ) 3. Employee NumSelled - > cnt++
		 * 4. Insurance NumSelled -> cnt++ -반려 시 - 1. 고객에게 메일 보내기 2. Contract DB (
		 * ProcessNum -> 4 로 Update )
		 */
		ArrayList<Contract> contracts = contractDao.retrieveContractByProcess("2").getContractList();
		System.out.println("인수 심사를 진행할 계약을 선택해주세요 . ");
		System.out.println("< 인수 대기 계약 목록 > ");
		for (Contract contract : contracts) {
			System.out.println("| 계약 번호 : " + contract.getContractID() + " | 고객 ID : " + contract.getCustomerID()
					+ " | 보험 ID : " + contract.getInsuranceID() + " |");
		}
		System.out.println("0. 뒤로가기 ");
		if(sc.nextLine().equals("0")) {
			runEmployeeSystem();
			return;
		}
		Contract contract = contractList.search(Integer.parseInt(sc.nextLine()));
		while (contract == null) {
			System.out.println("존재하지 않는 계약 ID 입니다 . ");
			contract = contractList.search(Integer.parseInt(sc.nextLine()));
		}
		System.out.println(dd.DD);
		Insurance insurance = null;
		Customer customer = null;
		// 인수 심사한다 E1
		try {
			System.out.println("< 계약 정보 > ");
			insurance = insuranceList.search(Integer.parseInt(contract.getInsuranceID()));
			customer = customerList.search(contract.getCustomerID());
			System.out.println("계약 당사자 명 : " + customer.getName());
			System.out.println("계약 당사자 나이 : " + customer.getAge());
			System.out.println("계약 당사자 직업 : " + customer.getJob());
			System.out.println("계약 당사자 성별 : " + customer.getSex());
			System.out.println("계약 당사자 주민등록번호 : " + customer.getSID());
			System.out.println("책정 보험 가입료 : " + contract.getRegistFee());
			System.out.println("책정 월 납부액 : " + contract.getMonthFee());
			Interview interview = interviewList.search(customer.getCustomerID());
			Employee iEmployee = employeeList.search(Integer.parseInt(interview.getEmployeeID()));
			System.out.println("면담 여부 : O ");
			System.out.println("면담 일시 : " + interview.getProcessedDate());
			System.out.println("담당 직원 명 : " + iEmployee.getName().split("_")[1]);
			try {// 면담 리스트를 작성한다. E2
			System.out.println("면담 내용: " + interview.getContent());
			}catch(Exception e) {
				System.out.println("죄송합니다. 시스템에서 면담 내용이 출력되지 않습니다.");
				System.exit(0);
			}
		} catch (Exception e) {
			System.out.println("서버와의 연결이 끊어졌습니다.");
		}
		System.out.println(dd.DD);
		int uwNum = calculateFactors(customer, insurance);
		// Time
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date time = new Date();
		//
		String startDate = contract.getStartDate();

		if (uwNum < 5) {
			System.out.println("인수 심사 결과 계약을 체결할 수 있는 고객입니다 . ");
			System.out.println("계약 인수를 진행하시려면 (1) , 대기를 원하시면 (2) 를 눌러주세요 . ");
			if (Integer.parseInt(sc.nextLine()) == 2)
				return;
			else {
				// 납입일 , 만기일 설정 ; Contract DB Update
				System.out.println("보험 계약 인수를 진행합니다 . 해당 계약의 만기설정을 해주세요 .   ");
				System.out.println("(1) 1년 (2) 2년 (3) 3년 ");
				int selectNum = Integer.parseInt(sc.nextLine());
				if (selectNum > 3) {
					System.out.println("잘못 입력하셨습니다 . 다시 입력해주세요 . ");
					selectNum = Integer.parseInt(sc.nextLine());
				}
				String endDate = setEndDate(startDate, selectNum);
				contractDao.update("endDate", endDate.split("-")[0] + endDate.split("-")[1] + endDate.split("-")[2],
						contract); // endDate
				String payDate = setPayDate(startDate);
				contractDao.update("payDate", payDate.split("-")[0] + payDate.split("-")[1] + payDate.split("-")[2],
						contract);// payDate
				contractDao.update("processNum", "3", contract);// processNum
				// Insurance result , employee NumSelled Update.
				Result result = resultDao.retrieveByInsuranceID(insurance.getInsuranceID() + "");
				int numSelled = Integer.parseInt(result.getNumSelled());
				numSelled++; // 판매 횟수 +
				resultDao.update("numSelled", numSelled + "", result);

				Employee salesPerson = employeeList.search(Integer.parseInt(contract.getEmployeeID()));
				numSelled = Integer.parseInt(salesPerson.getNumSelled());
				numSelled++; // 직원 실적 +
				employeeDao.update("numSelled", numSelled + "", salesPerson);
				// Mail -> Customer ;
				Mail mail = new Mail();
				try {
					String title = "보험 계약 체결 안내 ";
					String content = customer.getName() + " 님의 " + insurance.getInsuranceName()
							+ " 보험 계약 인수가 정상 처리되었습니다 . ";
					mail.setContent(content);
					mail.setTitle(title);
					mail.setCustomerID(customer.getCustomerID());
					mail.setEmployeeID(employee.getName());
					mail.setDate(format.format(time));
					mailDao.create(mail);
				} catch (Exception e) {
					System.out.println("죄송합니다. 현재 정보를 전송할 수 없습니다. 잠시 뒤에 다시 시도해 주십시오.");
					System.out.println(e.getMessage());
				}
				System.out.println("[ " + format.format(time) + " ] 에 " + customer.getName() + " 고객의 "
						+ insurance.getInsuranceName() + " 보험 계약 인수를 승인하였습니다. ");
				System.out.println("계약 인수 즉시 효력이 발생하며 해당 계약의 만료일은 " + endDate + "입니다 . ");
			}
		} else {
			System.out.println("인수 심사 결과 계약 체결이 불가능한 고객입니다 . ");
			// Mail
			Mail mail = new Mail();

			String title = "보험 계약 체결 반려 안내 ";
			String content = customer.getName() + " 님의 계약의 인수 심사 결과 체결 불가로 판단되었습니다 . ";
			String date = format.format(time);
			try {
				mail.setContent(content);
				mail.setDate(date);
				mail.setTitle(title);
				mail.setCustomerID(customer.getCustomerID());
				mail.setEmployeeID(employee.getName() + "");
				mailDao.create(mail);
				// contract -> 4 ( 인수 X )
				contractDao.update("processNum", "4", contract);
			} catch (Exception e) {
				System.out.println("죄송합니다. 현재 정보를 전송할 수 없습니다. 잠시 뒤에 다시 시도해 주십시오.");
				System.out.println(e.getMessage());
			}
		}

	}

	private String setEndDate(String format, int tmp) {
		String year = format.split("-")[0];
		int cnt = Integer.parseInt(year);
		cnt += tmp;
		year = cnt + "";
		String endDate = year + "-" + format.split("-")[1] + "-" + format.split("-")[2];
		return endDate;
	}

	private String setPayDate(String format) {
		LocalDate date = LocalDate.parse(format, DateTimeFormatter.ISO_DATE);
		LocalDate oneMonth = date.plusMonths(1);
		return oneMonth + " ";
	}

	private int calculateFactors(Customer customer, Insurance insurance) {
		System.out.println("인수 심사를 시작합니다 ...\n");
		int age = Integer.parseInt(customer.getAge());
		String job = customer.getJob();
		Car car = carDao.retrieve(customer);
		String madeYear = car.getMadeYear();
		String carType = car.getCarType();
		int uwNum = 0;
		switch (job) { // 직업 별 요소
		case "학생":
			break;
		case "버스기사":
			uwNum += 1;
			break;
		case "화물차운전자":
			uwNum += 2;
			break;
		case "배달종사자":
			uwNum += 2;
			break;
		case "기타사무직":
			break;
		default:
			break;
		}
		System.out.println("계약자 직업 : " + job + " ( uwRate : " + uwNum + " / 5 )\n");
		if (Integer.parseInt(madeYear) < 2018) { // 제조년도 별 요소
			uwNum += 1;
		}
		System.out.println("차량 제조 년도 : " + madeYear + " ( uwRate : " + uwNum + " / 5 )\n");
		// 차종 별 요소
		if (carType.equals("motorCycle") || carType.equals("freight"))
			uwNum += 1;
		System.out.println("계약자 소유 차종 : " + carType + " ( uwRate : " + uwNum + " / 5 )\n");
		// 나이 별 요소
		if (age < 30 || age > 60) {
			if (age > 70)
				uwNum += 2;
			else
				uwNum += 1;
		}
		System.out.println("계약자 나이 : " + age + " ( uwRate : " + uwNum + " / 5 )\n");
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
			// 계약을 체결한다. A1
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
			try {//계약을 체결한다 E1
			contractDao.update("employeeID", employee.getEmployeeID()+"", contract);
			contractDao.update("processNum", "2", contract);
			break;
			}catch(Exception e) {
				System.out.println("죄송합니다. 해당 페이지에 문제가 있습니다.");
				try {
				Thread.sleep(3000);
				run();
				}catch(InterruptedException ie) {
					System.out.println("페이지의 오류로 실행을 종료합니다.");
					System.exit(0);
				}
			}
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
		String customers[] = new String[interviews.size() + 1];
		for (Interview interview : interviews) {
			String customerID = interview.getCustomerID();
			Customer customer = customerList.search(customerID);
			System.out.println(cnt + ". " + customer.getName() + " " + interview.getAskDate());
			customers[cnt] = customerID;
			cnt++;
		}
		System.out.println("면담을 진행할 고객의 ID를 선택해 주세요 . ");
		int selecNum = Integer.parseInt(sc.nextLine());
		while (selecNum > interviews.size() + 1) {
			System.out.println("잘못 입력하셨습니다 . 다시 입력해 주세요 . ");
			selecNum = Integer.parseInt(sc.nextLine());
		}
		Interview interview = sInterviewList.search(customers[selecNum]);
		Customer customer = customerList.search(customers[selecNum]);
		System.out.println(dd.DD);
		System.out.println(" - 고객정보 - ");
		// 가입자 리스트 확인한다. E1
		try {
			System.out.println("이름 : " + customer.getName());
			System.out.println("나이 : " + customer.getAddress());
			System.out.println("성별 : " + customer.getSex());
			System.out.println("면담 신청일 : " + interview.getAskDate());
		} catch (Exception e) {
			System.out.println("죄송합니다. 현재 데이터를 읽어올 수 없습니다.");
		}
		System.out.println(dd.DD);
		System.out.println("면담 내용을 작성해주세요 . ");
		try {// 면담 리스트를 작성한다. E1,E3,E4
		String contents = sc.nextLine();
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date time = new Date();

		interviewDao.update("employeeID", "" + employee.getEmployeeID(), customer.getCustomerID());
		interviewDao.update("isProcessed", "1", customer.getCustomerID());
		interviewDao.update("processedDate", "'" + format.format(time) + "'", customer.getCustomerID());
		interviewDao.update("content", "'" + contents + "'", customer.getCustomerID());

		customerDao.update("isInterview", "2", customer);

		System.out.println("완료 ");
		}catch(Exception e) {
			System.out.println("현재 시스템상 문제로 이용이 불가능 합니다. 고객센터에 문의 바랍니다.");
			System.out.println("고객센터 02-1234-5678");
			System.out.println("시스템을 죵료합니다.");
			System.exit(0);
		}

	}

	private void immunityHandler(Employee employee) {
		System.out.println("< 손해 조사 및 면책 판단 메뉴 > ");
		System.out.println("- 접수된 사고 목록 - ");
		ArrayList<Accident> accidents = accidentDao.retrieveAccidentByProcess("1").getAccidentList();
		for(Accident accident : accidents) {
			System.out.println("사고 번호 : "+accident.getAccidentID()
			+" | 접수자 명 : " + customerList.search(accident.getCustomerID()).getName()); 
		}
		System.out.println("\n접수할 사고의 사고 번호를 입력해주세요 ");
		String accidentNum;
		while(true) {
			 accidentNum = sc.nextLine();
			if(!accidentNum.matches("[+-]?\\d*(\\.\\d+)?")) { //문자 입력 시 
				System.out.println("잘못된 입력입니다 . 숫자만 입력해주세요 . ");
				continue;
			}
			if(accidentList.search(Integer.parseInt(accidentNum))==null) {
				System.out.println("존재하지 않는 사고 번호입니다 . 다시 입력해주세요 . ");
				continue;
			}break;
		}
		Accident accident = accidentList.search(Integer.parseInt(accidentNum));
		Contract contract = contractList.search(Integer.parseInt(accident.getContractID()));
		Insurance insurance = insuranceList.search(Integer.parseInt(contract.getInsuranceID()));
		System.out.println(dd.DD);
		System.out.println("< 사고 상세  >\n");
		System.out.println("*사고 정보*");
		System.out.println("사고 번호 : "+accident.getAccidentID()+
		"\n사고 날짜 : [ "+ accident.getDate()+" ]"+
		"\n사고 시간 : "+ accident.getTime()+
		"\n사고 장소 : "+accident.getLocation()+
		"\n사고 유형 : "+accident.getAccidentType()+"\n\n*사건 현장 정보*"+
		"\n사건 녹취록 : "+accident.getMp3()+
		"\n영상 : "+accident.getVideo()+
		"\n사진 : " + accident.getPhoto());
		System.out.println(dd.DD);
		String accidentLevel ;
		int sum = 0;
		String damage;
		System.out.println("사고 피해 정도를 기입해주세요 .\n");
		System.out.println("대인 피해 정도를 입력해주세요 .( 0 - 100 ) ");
		while (true) {// 예외흐름
			damage = sc.nextLine();
			if (!damage.matches("[+-]?\\d*(\\.\\d+)?")) {
				System.out.println("숫자만 입력해주세요 . ");
				continue;
			}
			if (Integer.parseInt(damage) > 100 || Integer.parseInt(damage) < 0) {
				System.out.println("올바른 숫자를 입력해주세요 .");
				continue;
			}
			break;
		}
		sum += Integer.parseInt(damage);
		System.out.println("대물 파손 정도를 입력해주세요 . ( 0 - 100 )");
		while (true) {// 예외흐름
			damage = sc.nextLine();
			if (!damage.matches("[+-]?\\d*(\\.\\d+)?")) {
				System.out.println("숫자만 입력해주세요 . ");
				continue;
			}
			if (Integer.parseInt(damage) > 100 || Integer.parseInt(damage) < 0) {
				System.out.println("올바른 숫자를 입력해주세요 .");
				continue;
			}
			break;
		}
		sum += Integer.parseInt(damage);
		System.out.println("차량 파손 정도를 입력해주세요 . ( 0 - 100 ) ");
		while (true) {// 예외흐름
			damage = sc.nextLine();
			if (!damage.matches("[+-]?\\d*(\\.\\d+)?")) {
				System.out.println("숫자만 입력해주세요 . ");
				continue;
			}
			if (Integer.parseInt(damage) > 100 || Integer.parseInt(damage) < 0) {
				System.out.println("올바른 숫자를 입력해주세요 .");
				continue;
			}
			break;
		}
		sum += Integer.parseInt(damage);
		int coverageCost;
		if(sum>200) {
			accidentLevel = "고";
			 coverageCost = insurance.getHCoverage().getCoverageCost();
		}else if(sum>100) {
			accidentLevel = "중";
			 coverageCost = insurance.getMCoverage().getCoverageCost();
		}else {
			accidentLevel = "저";
			 coverageCost = insurance.getLCoverage().getCoverageCost();
		}
		System.out.println("피해 정도 총 합 [ "+sum+" ]  으로 , 배상 보장 ["+accidentLevel+"] 에 해당합니다 . ");
		System.out.println(dd.DD);
		System.out.println("사고의 판단 정보를 선택해주세요 .\n\n"
				+ "1. 고객의 경미한 과실 ( 실수 ) 로 사고발생 \n"
				+ "2. 상대방 과실로 인한 사고발생\n"
				+ "3. 고객이 고의로 사고를 일으킨 경우\n"
				+ "4. 고객이 음주 및 무면허 운전\n"
				+ "5. 고객의 중대 과실로 인한 사고발생");
		String judgeNum ;
		while(true) {//예외흐름
			judgeNum = sc.nextLine();
			if(!judgeNum.matches("[+-]?\\d*(\\.\\d+)?")) { 
				System.out.println("숫자만 입력해주세요 . ");
				continue;
			}
			if(Integer.parseInt(judgeNum)>5 || Integer.parseInt(judgeNum)<1) {
				System.out.println("올바른 숫자를 입력해주세요 .");
				continue;
			}
			break;
		}
		if(judgeNum.equals("1") || judgeNum.equals("2")) {
			System.out.println("해당 판단 정보는 보험사 부책사항입니다 . 해당 사고를 손해 사정 리스트에 등록합니다 . ");
			accidentDao.update("processNum", "2", accident);
			Judge judge = new Judge();
			judge.setAccidentID(accident.getAccidentID()+"");
			judge.setCustomerID(accident.getCustomerID());
			judge.setReason(J.judgement[Integer.parseInt(judgeNum)]);
			judge.setResponsibility("유");
			judge.setCoverage(coverageCost+"");
		judgeDao.create(judge);
		}else {//판단 결과 면책사항일 시 
			System.out.println("해당 판단 정보는 보험사 면책사항입니다 . ");
			accidentDao.update("processNum", "2", accident);
		}
		
//		E1 ( “ 고객의 경미한 과실 ( 실수) 로 발생했을 경우 “ , True ) 
//		E2 (“상대방 과실로 발생했을 경우 “.  True) 
//		E3 ( “고객이 고의로 사고를 일으킨 경우 “ , false)
//		E4 ( “고객이 음주 및 무면허 운전을 했을 경우 “ , false ) 
//		E5 ( “ 고객의 중대 과실로 발생했을 경우 “ , false ) 
	}

	private void accidentHandler(Employee employee) {}

	private void runCustomerSystem() {
		System.out.println("< 보험사 고객 메뉴 >");
		System.out.println("1. 로그인");
		System.out.println("2. 고객 등록 ");
		int menu = Integer.parseInt(sc.nextLine());
		switch (menu) {
		// 회원 로그인
		case 1:
			System.out.println(dd.DD);
			loginCustomer();
			break;
		// 가입
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
		while (isExistInCustomerList == null && isExistInSCustomerList == null) {
			System.out.println("! 존재하지 않는 ID입니다 . 다시 입력해주세요 !");
			id = sc.nextLine();
			isExistInCustomerList = customerList.search(id);
			isExistInSCustomerList = sCustomerList.search(id);
		}
		if (isExistInCustomerList == null)
			customer = isExistInSCustomerList;
		else
			customer = isExistInCustomerList;
		for (int i = 0; i < 5; i++) {
			System.out.println(dd.DD);
			System.out.println("[ " + customer.getName() + " ]님 반갑습니다 . ");
			System.out.println("1. 가입할 보험 리스트 확인하기 ");
			System.out.println("2. 면담 신청하기 ");
			System.out.println("3. 가입한 보험 확인하기 ");
			System.out.println("4. 사고 접수하기 ");
			System.out.println("5. 메일 확인하기 ");
			System.out.println("6. 종료 ");
			switch (Integer.parseInt(sc.nextLine())) {
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
				System.out.println(dd.DD);
				takeAccident(customer);
				break;
			case 5:
				System.out.println(dd.DD);
				checkMailBox(customer);
				break;
			case 6:
				break;
			default:
				break;
			}

		}
	}

	private void takeAccident(Customer customer) {
		ArrayList<Contract> contracts = contractDao.retrieveByCustomerID(customer).getContractList();
		int contractCnt  = 0; // 가입한 보험의 수 
		for(Contract contract : contracts	) {
			if(contract.getProcessNum().equals("3")) contractCnt++;
		}
		if(contractCnt ==0 ) {//E 가입한 보험이 없을 시 
			System.out.println("현재 가입하신 보험이 없습니다 .\n메인 화면으로 돌아갑니다 .  ");
			return;
		}
		System.out.println("< 고객 사고 접수 메뉴 >");
		System.out.println("\n목록에서 사고 접수하실 보험을 선택해주세요 . \n");
		System.out.println("- 가입 보험 목록 - ");
		int tmp = 1;
		int selecContract[] = new int[20];
		for(Contract contract : contracts) {
			if(contract.getProcessNum().equals("3")) { // 가입 보험이 존재할 시 
				Insurance insurance = insuranceList.search(Integer.parseInt(contract.getInsuranceID()));
				System.out.println(tmp+". "+insurance.getInsuranceName()+" | 보험유형 : "+insurance.getProductType()+" | 최대보장금액 : "+insurance.getHCoverage().getCoverageCost());
				selecContract[tmp] = contract.getContractID();
				tmp++;
			}
		}
		int selectNumber = 0;
		System.out.println();
		try {
		selectNumber = Integer.parseInt(sc.nextLine());
		while(selectNumber>=tmp) {  //E 잘못된 번호 입력 시
			System.out.println("잘못된 입력입니다 . 다시 입력해주세요 . ");
			selectNumber = Integer.parseInt(sc.nextLine());}
		}catch (NumberFormatException e) {//E 문자 입력 시 
			System.out.println("문자 입력 오류입니다 . 목록으로 돌아갑니다 . ");
		}
		Contract contract = contractList.search(selecContract[selectNumber]);
		System.out.println(dd.DD);
		Accident accident = new Accident();
		System.out.println("* 사고 정보를 기입해주세요 *");
		System.out.println("사고 날짜를 입력해주세요 . (기입 양식 : yyyy-mm-dd)");
		String date = sc.nextLine();
		if(date.length() != 10) { // 기입 양식에 맞지 않을 
			System.out.println("올바른 양식으로 기입해주세요 . (기입 양식 : yyyy-mm-dd)");
			date = sc.nextLine();
		}		
		while (true) {
			try {
				int dateCalc = dateCalculate(date);
			} catch (Exception e) {// Exception . 날짜 형식이 맞지 않을 시 ( Ex : 2023-13-04 )
				System.out.println("올바른 날짜 형식이 아닙니다 . 다시 기입해주세요 . (기입 양식 : yyyy-mm-dd) ");
				date = sc.nextLine();
				continue;
			}break;
		}
		accident.setDate(date);
		if(dateCalculate(date)>-180) { //6개월이 지난 사고가 아닐 시 .
			System.out.println("사고 발생 시간을 입력해주세요 . ( 예 : 18:35 ) ");
			accident.setTime(sc.nextLine());
			System.out.println("사고 발생 장소를 입력해주세요 . ( 예 : 서울 ) ");
			accident.setLocation(sc.nextLine());
			System.out.println("사고 유형을 선택해주세요 . ");
			System.out.println("(1) 대인 상해 (2) 대물 접촉사고 (3) 자차 파손 (4) 자기신체상해 ");
			String selecNum = sc.nextLine();
			while(!selecNum.matches("[+-]?\\d*(\\.\\d+)?")) {//잘못된 입력 시   
				System.out.println("숫자만 입력해주세요 . ");
				selecNum = sc.nextLine();
			}
			String type = "";
			switch(Integer.parseInt(selecNum)) {
			case 1:
				type = "대인 상해";
				break;
			case 2:
				type = "대물 접촉사고";
				break;
			case 3:
				type = "자차 파손";
				break;
			case 4:
				type = "자기신체상해";
				break;
			default:break;
			}
			accident.setAccidentType(type);
			accident.setContractID(contract.getContractID()+"");
			accident.setCustomerID(customer.getCustomerID());
			accident.setProcessNum("1"); // 접수 : 1
			System.out.println("*면책 판단을 위한 추가 현장 정보를 등록해주세요*");
			System.out.println("사건 현장 사진이 있다면 (1)번을 , 아니면 아무 키나 눌러주세요 .");
			if(sc.nextLine().equals("1")) accident.setPhoto("photo.file");
			else accident.setPhoto("없음");
			System.out.println("사건 현장 음성 녹음 파일이 있으시다면 (1)번을 , 아니면 아무 키나 눌러주세요 . ");
			if(sc.nextLine().equals("1")) accident.setMp3("mp3.file");
			else accident.setMp3("없음");
			System.out.println("사건 현장 영상 파일이 있으시다면 (1)번을 , 아니면 아무 키나 눌러주세요 . ");
			if(sc.nextLine().equals("1")) accident.setVideo("video.file");
			else accident.setVideo("없음");
			
			accidentDao.create(accident);
			System.out.println(customer.getName()+" 님의 사고 접수가 정상 처리되었습니다 . ");
			
		}else { //사고 발생으로부터 6개월이 지났을 경우 
			System.out.println("사고 발생으로부터 6개월이 지난 경우 접수가 불가능합니다 . ");
		}
		
	}

	private int dateCalculate(String date) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date time = new Date();
		String nowDate = format.format(time);
		LocalDate date1 = LocalDate.parse(nowDate);
		LocalDate date2 = LocalDate.parse(date);
		return (int) ChronoUnit.DAYS.between(date1, date2);
	}

	private void checkRegistedInsuranceList(Customer customer) {
		try {
		ArrayList<Contract> contracts = contractDao.retrieveByCustomerID(customer).getContractList();
		ArrayList<Contract> contractList = new ArrayList<>();
		int cnt = 1;
		for (Contract contract : contracts) {
			if (contract.getProcessNum().equals("3")) {
				Insurance insurance = insuranceList.search(Integer.parseInt(contract.getInsuranceID()));
				System.out.println(cnt + ". " + insurance.getInsuranceName());
				cnt++;
				contractList.add(contract);
			}
		}
		System.out.println("확인하실 보험을 선택해주세요 . ");
		int selectNum = Integer.parseInt(sc.nextLine());
		while (selectNum > contractList.size()) { // Exception : 없는 번호를 선택시
			System.out.println("다시 선택해 주세요 .");
			selectNum = Integer.parseInt(sc.nextLine());
		}
		System.out.println(dd.DD);
		Contract contract = contractList.get(selectNum - 1);
		Insurance insurance = insuranceList.search(Integer.parseInt(contract.getInsuranceID()));
		System.out.println("< " + insurance.getInsuranceName() + " >");
		System.out.println("보험 가입일 : " + contract.getStartDate());
		System.out.println("보험 만료일 : " + makeDateForm(contract.getEndDate()));
		System.out.println("보험료 납부일 : " + makeDateForm(contract.getPayDate()));
		System.out.println("월 납입료 : " + contract.getMonthFee());
		// 체결자 : Employee
		employee = employeeList.search(Integer.parseInt(contract.getEmployeeID()));
		System.out.println("계약 체결자 : " + employee.getName().split("_")[1] + ", 체결 일 : " + contract.getStartDate());
		System.out.println("보험 보장금 ( 저 ) : " + insurance.getLCoverage().getCoverageCost());
		System.out.println("보험 보장금 ( 중 ) : " + insurance.getMCoverage().getCoverageCost());
		System.out.println("보험 보장금 ( 고 ) : " + insurance.getHCoverage().getCoverageCost());
		System.out.println(dd.DD);
		System.out.println("(1) 확인  (2) 보험료 납부  (0) 보험 해지 ");
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date now = new Date();
		String nowDate = format.format(now);
		switch (Integer.parseInt(sc.nextLine())) {
		case 1:
			break;
		case 2: // 현 날짜와 보험료 납부일 날짜 비교 후 납부 의무가 있는지 판단, 있다면 고객 계좌에서 계좌 비밀번호 확인 후 납부 .
			String payDate = makeDateForm(contract.getPayDate());
			LocalDate date1 = LocalDate.parse(nowDate);
			LocalDate date2 = LocalDate.parse(payDate);
			long daysBetween = ChronoUnit.DAYS.between(date1, date2);
			if (daysBetween > 0) {
				System.out.println(
						"보험료 납입 기간이 아닙니다 . " + customer.getName() + " 고객님의 다음 월 보험료 납입일은 [ " + payDate + " ] 입니다 . ");
			} else {
				String nextPayDate = setPayDate(payDate);
				Bank bank = bankDao.retrieve(customer);
				System.out.println("보험료 납입을 위한 고객님의 계좌 비밀번호 4자리를 입력해주세요 . ");
				String pw = sc.nextLine();
				int tmp = 0;
				while (!pw.equals(bank.getPassword())) {
					if (tmp == 5) {
						System.out.println("비밀번호 입력 5회 오류입니다 . 다음에 다시 시도해주세요 . ");
						return;
					}
					System.out.println("계좌 비밀번호 입력 오류입니다 . 다시 입력해 주세요 . ");
					pw = sc.nextLine();
					tmp++;
				}
				System.out.println("보험사에 등록된 고객님의 " + bank.getBankCompany() + "은행 계좌에서 " + contract.getMonthFee()
						+ " 원을 납입하였습니다 . 고객님의 다음 납입일은 [ " + nextPayDate + "] 입니다 . ");
				// PayDate -> 한 달 후 (NextPayDate) 로 변경
				payDate = nextPayDate.split("-")[0] + nextPayDate.split("-")[1] + nextPayDate.split("-")[2];
				contractDao.update("payDate", payDate, contract);
				// Bank 에서 MonthFee 만큼 차감
				int updateAmount = Integer.parseInt(bank.getMoneyAmount()) - Integer.parseInt(contract.getMonthFee());
				bankDao.update("moneyAmount", updateAmount + "", customer);
			}
			break;
		case 0: // Contract DB에서 해당 계약삭제 .
			System.out.println(insurance.getInsuranceName() + " 보험 계약을 해지하시겠습니까 ? ");
			System.out.println("(1) 예 (2) 아니오 ");
			String selecNum = sc.nextLine();
			// 예외흐름 : 고객의 DB에서 해당 계약을 삭제하지 못했을 떄
			if (selecNum.equals("1")) {
				System.out.println(nowDate + " 에 " + insurance.getInsuranceName() + " 보험의 해지가 완료되었습니다 . ");
				contractDao.delete(contract);
			} else if (selecNum.equals("2")) {
				System.out.println("계약 해지를 취소합니다 . ");
			} else {
				System.out.println("잘못된 입력으로 계약 해지가 진행되지 않았습니다 . 다음에 다시 시도해주세요. ");
			}
			break;
		default:
			break;
		}
		}catch(Exception e) {
			System.out.println("현재 시스템상 문제로 이용이 불가능합니다. 고객센터에 문의 바랍니다. 고객센터 02-1234-5678");
			System.out.println("시스템을 종료합니다.");
			System.exit(0);
		}
	}

	private String makeDateForm(String payDate) {
		String returnValue = "";
		returnValue += payDate.substring(0, 4) + "-" + payDate.substring(4, 6) + "-" + payDate.substring(6, 8);
		return returnValue;
	}

	private void checkMailBox(Customer customer) {
		ArrayList<Mail> mails = mailDao.retrieveByCustomerID(customer.getCustomerID()).getMailList();
		int cnt = 1;
		ArrayList<Mail> mailList = new ArrayList<>();
		for (Mail mail : mails) {
			System.out.println(cnt + ". " + mail.getTitle() + " [ " + mail.getDate() + " ]");
			mailList.add(mail);
			cnt++;
		}
		System.out.println("확인하실 메일을 선택해주세요 . ");
		int selectNum = Integer.parseInt(sc.nextLine());
		while (selectNum > mails.size()) { // Exception : 없는 번호 선택 시
			System.out.println("다시 선택해 주세요 . ");
			selectNum = Integer.parseInt(sc.nextLine());
		}
		System.out.println(dd.DD);
		Mail mail = mailList.get(selectNum - 1);
		System.out.println("발신자 : " + mail.getEmployeeID().split("_")[1]);
		System.out.println(mail.getContent());

	}

	private void checkInsuranceList(Customer customer) {
		System.out.println("< 가입 가능 보험 리스트 > ");
		// 해당 고객이 이미 가입한 보험 제외 해야 함 . (아직 x)
		System.out.println("- 보험목록 - ");
		ArrayList<Insurance> insurances = insuranceDao.retrieveConfirm().getInsuranceList();
		ArrayList<Contract> contracts = contractDao.retrieve().getContractList();
		for (Insurance insurance : insurances) { // < 추가할 사항 : 이미 신청된 보험 제외 >
			System.out.println(insurance.getInsuranceID() + " " + insurance.getInsuranceName());

		}
		System.out.println("가입하실 보험의 ID를 입력해주세요 . ");
		System.out.println("0. 돌아가기 ");
		int selecNum = Integer.parseInt(sc.nextLine());
		if (selecNum == 0)
			return;
		Insurance insurance = insuranceList.search(selecNum);
		// Exception : 존재하지 않는 보험 ID를 입력하였을 때
		while (insurance == null) {
			System.out.println("존재하지 않는 보험 ID를 입력하셨습니다  . 다시 입력해 주세요 . ");
			insurance = insuranceList.search(Integer.parseInt(sc.nextLine()));
		}
		// Exception : 이미 체결된 보험을 가입하려 할 때
		while (contractList.searchIsContract(insurance.getInsuranceID() + "", customer.getCustomerID()) != null) {
			System.out.println("해당 보험은 이미 체결 되었거나 진행 중인 보험입니다 . ");
			return;
		}

		System.out.println(dd.DD);
		System.out.println("< 보험정보 >");
		System.out.println("보험 유형 : " + insurance.getProductType());
		System.out.println("보험 ID : " + insurance.getInsuranceID());
		System.out.println("보험명 : " + insurance.getInsuranceName());
		System.out.println("보험 기본 가입비 : " + insurance.getInsuranceFee());
		System.out.println("보험 보장 ( 상 ) : " + insurance.getHCoverage().getCoverageCost());
		System.out.println("보험 보장 ( 중 ) : " + insurance.getMCoverage().getCoverageCost());
		System.out.println("보험 보장 ( 하 ) : " + insurance.getLCoverage().getCoverageCost());
		System.out.println(dd.DD);
		System.out.println("(1) 보험 가입 신청 (2) 확인 ");
		try {
		switch (Integer.parseInt(sc.nextLine())) { // 면담 진행 고객인지 아닌지 구분
		case 1:
			Interview interview = interviewList.search(customer.getCustomerID());
			if (customer.getIsInterview().equals("2")) {
				System.out.println(dd.DD);
				System.out.println("< 보험 가입 메뉴 > ");
				// 계좌 등록된 고객 / 등록되지 않은 고객 구분
				if (customer.getIsBank().equals("0")) {
					makeAccount(customer); // 고객 계좌 db에등록
				}
				System.out.println(dd.DD);
				System.out.println(insurance.getInsuranceName() + " 보험의 보험료율 시산작업을 시작합니다 . ");
				System.out.println();
				System.out.println("시산작업 진행중 . . .");
				System.out.println();
				float rate = Float.parseFloat(getRate(insurance, customer));
				float monthRate = (float) 0.0020 * rate * (insurance.getHCoverage().getCoverageCost());// 월납부 보험
				float registFee = insurance.getInsuranceFee() * rate; // 보험 가입비
				String dbMonthRate = (int) monthRate + "";
				String dbRegistFee = (int) registFee + "";

				System.out.println("고객님의 보험 가입료는 ( " + dbRegistFee + " ) , 월 납부액은 ( " + dbMonthRate + " )입니다.\n ");
				Bank bank = bankDao.retrieve(customer);
				System.out.println("보험 체결을 위한 가입비 납부 절차입니다 . 보험사에 등록된 계좌의 결제 비밀번호를 입력해 주세요 . ");
				String pw = sc.nextLine();
				int pwCnt = 0;
				while (!pw.equals(bank.getPassword())) { // 예외흐름 : 5회이상 틀렸을 시 종료
					System.out.println(bank.getPassword());
					if (cnt == 4) {
						System.out.println("5회 입력 실패입니다 . 다음에 다시 시도해주세요 . ");
						return;
					}
					System.out.println("비밀번호가 틀렸습니다 . 다시 입력해 주세요 . ");
					cnt++;
					pw = sc.nextLine();
				}
				System.out.println("가입비 납부가 완료되었습니다 . 보험의 효력은 직원의 보험 체결 이후 실현됩니다 . 감사합니다 .");
				// ContractDao 생성
				Contract contract = new Contract();
				contract.setCustomerID(customer.getCustomerID());
				contract.setProcessNum("1");
				contract.setInsuranceID(insurance.getInsuranceID() + "");
				contract.setRegistFee(dbRegistFee);
				contract.setMonthFee(dbMonthRate);
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
				Date time = new Date();
				String date = format.format(time);
				contract.setStartDate(date);
				contractDao.create(contract);
				customerDao.update("isBank", "1", customer);// 고객의 isBank -> 1로 update
				// 고객 Bank Account -> - registFee
				int setMoney = Integer.parseInt(bank.getMoneyAmount()) - Integer.parseInt(dbRegistFee);
				bank.setMoneyAmount(setMoney + "");
				bankDao.update("moneyAmount", setMoney + "", customer);
				///////////////////////////////////////////////////////// d///////////////
			} else { // Exception : 면담 진행 X 고객일 시
				System.out.println("! 직원과의 면담이 진행중이거나 처리되지 않은 상태입니다 . 면담 후 다시 신청해주세요 !");
			}
			break;
		case 2:
			break;
		default:
			break;
		}
		}catch(Exception e) {
			System.out.println("서버와의 연결이 끊어졌습니다. 잠시 후에 다시 이용해주세요.");
			System.out.println("시스템을 종료합니다.");
			System.exit(0);
		}

	}

	private String getRate(Insurance insurance, Customer customer) {
		Ratio ratio = ratioDao.retrieveByInsuranceID(insurance.getInsuranceID());
		Car car = carDao.retrieve(customer);

		float rate = 1.0f;

		String carType = car.getCarType();
		String madeYear = car.getMadeYear();
		String sex = customer.getSex();
		String age = customer.getAge();
		String job = customer.getJob();
		// CarType
		switch (carType) {
		case "small":
			rate = rate * Float.parseFloat(ratio.getSmallRatio());
			break;
		case "sedan":
			rate = rate * Float.parseFloat(ratio.getSedanRatio());
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
		// madeYear
		int year = Integer.parseInt(madeYear);
		if (year >= 2018) {
			rate = rate * Float.parseFloat(ratio.getAfter2018());
		} else {
			rate = rate * Float.parseFloat(ratio.getBefore2018());
		}
		// sex
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
		// age
		int customerAge = Integer.parseInt(age);
		if (customerAge > 30) {
			rate = rate * Float.parseFloat(ratio.getRatio20());
		} else if (customerAge > 40) {
			rate = rate * Float.parseFloat(ratio.getRatio30());
		} else if (customerAge > 50) {
			rate = rate * Float.parseFloat(ratio.getRatio40());
		} else if (customerAge > 60) {
			rate = rate * Float.parseFloat(ratio.getRatio50());
		} else {
			rate = rate * Float.parseFloat(ratio.getRatio60());
		}
		// job
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
		return rate + "";
	}

	private void sisaning() {
		cnt = 0;
		Timer timer = new Timer();
		TimerTask task = new TimerTask() {
			@Override
			public void run() {
				if (cnt >= 3) {
					timer.cancel();
					return;
				}
				System.out.println("시산작업 진행중... ");
				cnt++;
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
		switch (Integer.parseInt(sc.nextLine())) {
		case 1:
			bank.setBankCompany("농협");
			break;
		case 2:
			bank.setBankCompany("신한");
			break;
		case 3:
			bank.setBankCompany("제일");
			break;
		case 4:
			bank.setBankCompany("국민");
			break;
		case 5:
			bank.setBankCompany("기업");
			break;
		default:
			break;
		}
		System.out.println("등록하실 계좌의 계좌번호를 입력해 주세요 . ( 기입양식 : ooo-oooooo-oo-ooo "); // 기입 양식 exception 처리 추가
		String ac = sc.nextLine();
		bank.setAccountNum(ac);
		System.out.println("보험료 납부를 위한 결제 비밀번호를 입력해 주세요 . ( 기입양식 : oooo )  ");
		bank.setPassWord(sc.nextLine());
		bank.setMoneyAmount("1000000");
		bankDao.create(bank);
		System.out.println("계좌가 등록되었습니다 . ");
	}

	private Customer askInterview(Customer customer) {
		if (customer.getIsInterview().equals("1") || customer.getIsInterview().equals("2")) {
			// Exception : 이미 면담 처리가 완료된 고객일 시
			System.out.println("이미 면담이 진행중이거나 면담 처리가 완료된 상태입니다 . ");
			return customer;
		}
		System.out.println("< 면담 신청 메뉴 > ");

		String customerID = customer.getCustomerID();
		Car car = new Car();
		Interview interview = new Interview();

		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date time = new Date();
		
		try {// 면담을 신청한다. E1
		interview.setAskDate(format.format(time));
		interview.setCustomerID(customerID);
		interview.setIsProcessed("0");
		// Car 등록 및 Customer _ isInterview 업데이트 및 Interview_isProcessed 업데이트
		car.setCustomerID(customerID);
		System.out.println("면담 신청을 위해 고객 자차 정보를 등록해주세요 . ");
		System.out.println("차량번호를 입력해주세요 . ( 기입양식 : xxxx ) ");
		car.setCarNum(sc.nextLine());
		System.out.println("차량의 생산년도를 입력해주세요 . ( 기입양식 : xxxx ) ");
		car.setMadeYear(sc.nextLine());
		System.out.println("차량의 종류를 선택해주세요 . ");
		System.out.println("(1) 소형차 (2) 중형차 (3) 버스 (4) 화물차 (5) 이륜차 ");
		switch (Integer.parseInt(sc.nextLine())) {
		case 1:
			car.setCarType("small");
			break;
		case 2:
			car.setCarType("sedan");
			break;
		case 3:
			car.setCarType("bus");
			break;
		case 4:
			car.setCarType("freight");
			break;
		case 5:
			car.setCarType("motorCycle");
			break;
		default:
			break;
		}
		carDao.create(car);
		interviewDao.create(interview);
		customerDao.update("isInterview", "1", customer);
		customer.setIsInterview("1");

		System.out.println(format.format(time) + " 에 [" + customer.getName() + "] 님의 면담 신청이 완료되었습니다 .");
		return customer;
		}catch(Exception e) {
			System.out.println("죄송합니다. 현재 면담 신청이 불가능합니다. 오류가 계속 될 경우 고객센터로 문의해주세요");
			System.out.println("시스템을 종료합니다.");
			System.exit(0);
			return null;
		}
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
			while (isExistInCustomerList != null || isExistInSCustomerList != null) {
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
			switch (Integer.parseInt(sc.nextLine())) {
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
			default:
				break;
			}

			System.out.println("가입하실 고객의 전화번호를 입력하세요 . ( 기입양식 : OOO-OOOO-OOOO) "); // 기입양식 예외처리 추가
			customer.setPhoneNumber(sc.nextLine());

			System.out.println("가입하실 고객의 성별을 선택하세요 . ");
			System.out.println("(1) 남성 (2) 여성 ");
			switch (Integer.parseInt(sc.nextLine())) {
			case 1:
				customer.setSex("남성");
				break;
			case 2:
				customer.setSex("여성");
				break;
			default:
				break;
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
		System.out.println("2. 초기화면으로");

		int select = Integer.parseInt(sc.nextLine());
		switch (select) {
		case 2:
			System.out.println("초기화면으로 이동합니다.");
			run();
		}

		if (select == 1)
			insurance.setProductType("Car Insurance");

		System.out.println("보험명을 입력해 주세요 . ");
		insurance.setInsuranceName(sc.nextLine());

		System.out.println("보험 기본 가입비를 입력해 주세요 . ");
		insurance.setInsuranceFee(Integer.parseInt(sc.nextLine()));

		int insuranceID = insuranceDao.create(insurance);
		if (insuranceID == 0)
			return;

		insurance.setInsuranceID(insuranceID);
		// Coverage
		try {
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
		} catch (Exception e) {
			System.out.println("미입력된 정보가 있습니다.");
			insuranceDesign();
		}
		// Result
		Result result = new Result();
		result.setGoal("5");
		result.setInsuranceID(insuranceID + "");
		result.setNumSelled("0");
		result.setProblem("미기입");
		result.setStatement("미기입");
		insurance.setResult(result);
		resultDao.create(result);

		// Ratio
		Ratio ratio = new Ratio();
		ratio.setInsuranceID(insuranceID);
		boolean end = false;
		System.out.println("보험료율 설정을 변경하려면 (1)을, 변경사항 없이 종료하시려면 (2)를 누르세요 .");
		if (Integer.parseInt(sc.nextLine()) == 1)
			end = false;
		else
			end = true;
		System.out.println(dd.DD);
		while (!end) {
			System.out.println("< 보험료율 설정 메뉴 >");
			System.out.println("설정하실 보험요율표를 선택하세요 . ");
			System.out.println("1.차종별 보험료율 2.직업별 보험료율 3.나이별 보험료율 4.성별 보험료율 5.요율설정 종료");
			switch (Integer.parseInt(sc.nextLine())) {
			case 1:
				ratio = setCarRatio(ratio, sc);
				break;
			case 2:
				ratio = setJobRatio(ratio, sc);
				break;
			case 3:
				ratio = setAgeRatio(ratio, sc);
				break;
			case 4:
				ratio = setSexRatio(ratio, sc);
				break;
			case 5:
				end = true;
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
		System.out.println("1.소형차 : " + ratio.getSmallRatio());
		System.out.println("2.중형차 : " + ratio.getSedanRatio());
		System.out.println("3.화물차 : " + ratio.getFreightRatio());
		System.out.println("4.버스 : " + ratio.getBusRatio());
		System.out.println("5.이륜차 : " + ratio.getMotorCycleRatio());

		switch (Integer.parseInt(sc.nextLine())) {
		case 1:
			System.out.println("설정하실 < 소형차 > 의 보험료율을 입력해주세요 .");
			ratio.setSmallRatio(sc.nextLine());
			if (ratio.getSmallRatio().equals("")) {
				System.out.println("미입력된 정보가 있습니다.");
				setCarRatio(ratio, sc);
			}
			;
			break;
		case 2:
			System.out.println("설정하실 < 중형차 > 의 보험료율을 입력해주세요 . ");
			ratio.setSedanRatio(sc.nextLine());
			if (ratio.getSedanRatio().equals("")) {
				System.out.println("미입력된 정보가 있습니다.");
				setCarRatio(ratio, sc);
			}
			;
			break;
		case 3:
			System.out.println("설정하실 < 화물차 > 의 보험료율을 입력해주세요 . ");
			ratio.setFreightRatio(sc.nextLine());
			if (ratio.getFreightRatio().equals("")) {
				System.out.println("미입력된 정보가 있습니다.");
				setCarRatio(ratio, sc);
			}
			;
			break;
		case 4:
			System.out.println("설정하실 < 버스 > 의 보험료율을 입력해주세요 . ");
			ratio.setBusRatio(sc.nextLine());
			if (ratio.getBusRatio().equals("")) {
				System.out.println("미입력된 정보가 있습니다.");
				setCarRatio(ratio, sc);
			}
			;
			break;
		case 5:
			System.out.println("설정하실 < 이륜차 > 의 보험료율을 입력해주세요 .");
			ratio.setMotorCycleRatio(sc.nextLine());
			if (ratio.getMotorCycleRatio().equals("")) {
				System.out.println("미입력된 정보가 있습니다.");
				setCarRatio(ratio, sc);
			}
			;
			break;
		default:
			break;
		}
		return ratio;
	}

	private Ratio setJobRatio(Ratio ratio, Scanner sc) {
		System.out.println("- 직업별 보험요율표 -");
		System.out.println("1.학생 : " + ratio.getStudentRatio());
		System.out.println("2.버스기사 : " + ratio.getBusDriverRatio());
		System.out.println("3.배달종사자 : " + ratio.getDeliverRatio());
		System.out.println("4.화물기사 : " + ratio.getFreightDriverRatio());
		System.out.println("5.기타사무직 : " + ratio.getEtcRatio());

		switch (Integer.parseInt(sc.nextLine())) {
		case 1:
			System.out.println("설정하실 < 학생 > 의 보험료율을 입력해주세요 . ");
			ratio.setStudentRatio(sc.nextLine());
			if (ratio.getStudentRatio().equals("")) {
				System.out.println("미입력된 정보가 있습니다.");
				setJobRatio(ratio, sc);
			}
			;
			break;
		case 2:
			System.out.println("설정하실 < 버스기사 > 의 보험료율을 입력해주세요 . ");
			ratio.setBusDriverRatio(sc.nextLine());
			if (ratio.getBusDriverRatio().equals("")) {
				System.out.println("미입력된 정보가 있습니다.");
				setJobRatio(ratio, sc);
			}
			;
			break;
		case 3:
			System.out.println("설정하실 < 배달종사자 > 의 보험료율을 입력해주세요 . ");
			ratio.setDeliverRatio(sc.nextLine());
			if (ratio.getDeliverRatio().equals("")) {
				System.out.println("미입력된 정보가 있습니다.");
				setJobRatio(ratio, sc);
			}
			;
			break;
		case 4:
			System.out.println("설정하실 < 화물기사 > 의 보험료율을 입력해주세요 . ");
			ratio.setFreightDriverRatio(sc.nextLine());
			if (ratio.getFreightDriverRatio().equals("")) {
				System.out.println("미입력된 정보가 있습니다.");
				setJobRatio(ratio, sc);
			}
			;
		case 5:
			System.out.println("설정하실 < 기타사무직 > 의 보험료율을 입력해주세요 . ");
			ratio.setEtcRatio(sc.nextLine());
			if (ratio.getEtcRatio().equals("")) {
				System.out.println("미입력된 정보가 있습니다.");
				setJobRatio(ratio, sc);
			}
			;
			break;
		default:
			break;
		}
		return ratio;
	}

	private Ratio setAgeRatio(Ratio ratio, Scanner sc) {
		System.out.println("- 나이별 보험요율표 -");
		System.out.println("1.20대 : " + ratio.getRatio20());
		System.out.println("2.30대 : " + ratio.getRatio30());
		System.out.println("3.40대 : " + ratio.getRatio40());
		System.out.println("4.50대 : " + ratio.getRatio50());
		System.out.println("5.60대 이상 : " + ratio.getRatio60());

		switch (Integer.parseInt(sc.nextLine())) {
		case 1:
			System.out.println("20세 이상 30세 미만의 보험료율을 설정해주세요 . ");
			ratio.setRatio20(sc.nextLine());
			if (ratio.getRatio20().equals("")) {
				System.out.println("미입력된 정보가 있습니다.");
				setAgeRatio(ratio, sc);
			}
			;
			break;
		case 2:
			System.out.println("30세 이상 40세 미만의 보험료율을 설정해주세요 . ");
			ratio.setRatio30(sc.nextLine());
			if (ratio.getRatio30().equals("")) {
				System.out.println("미입력된 정보가 있습니다.");
				setAgeRatio(ratio, sc);
			}
			;
			break;
		case 3:
			ratio.setRatio40(sc.nextLine());
			System.out.println("40세 이상 50세 미만의 보험료율을 설정해주세요 . ");
			if (ratio.getRatio40().equals("")) {
				System.out.println("미입력된 정보가 있습니다.");
				setAgeRatio(ratio, sc);
			}
			;
			break;
		case 4:
			ratio.setRatio50(sc.nextLine());
			System.out.println("50세 이상 60세 미만의 보험료율을 설정해주세요 . ");
			if (ratio.getRatio50().equals("")) {
				System.out.println("미입력된 정보가 있습니다.");
				setAgeRatio(ratio, sc);
			}
			;
			break;
		case 5:
			ratio.setRatio60(sc.nextLine());
			System.out.println("60세 이상의 보험료율을 설정해주세요 . ");
			if (ratio.getRatio60().equals("")) {
				System.out.println("미입력된 정보가 있습니다.");
				setAgeRatio(ratio, sc);
			}
			;
			break;
		default:
			break;
		}
		return ratio;
	}

	private Ratio setSexRatio(Ratio ratio, Scanner sc) {
		System.out.println("- 성별 보험요율표 -");
		System.out.println("남성 : " + ratio.getMaleRatio());
		System.out.println("여성 : " + ratio.getFemaleRatio());

		switch (Integer.parseInt(sc.nextLine())) {
		case 1:
			System.out.println("남성의 보험료율을 입력해주세요 . ");
			ratio.setMaleRatio(sc.nextLine());
			if (ratio.getMaleRatio().equals("")) {
				System.out.println("미입력된 정보가 있습니다.");
				setSexRatio(ratio, sc);
			}
			;
			break;
		case 2:
			System.out.println("여성의 보험료율을 입력해주세요 . ");
			ratio.setFemaleRatio(sc.nextLine());
			if (ratio.getFemaleRatio().equals("")) {
				System.out.println("미입력된 정보가 있습니다.");
				setSexRatio(ratio, sc);
			}
			;
			break;
		default:
			break;
		}

		return ratio;
	}

	private void insuranceConfirm() {
		ArrayList<Insurance> insurances = insuranceDao.retrieveNoConfirm().getInsuranceList();

		System.out.println("- 보험목록 -");
		for (Insurance insurance : insurances) {
			System.out.println(insurance.getInsuranceID() + " " + insurance.getInsuranceName());
		}
		System.out.println("확정을 진행할 보험 ID를 선택해주세요 . ");
		Insurance insurance = insuranceList.search(Integer.parseInt(sc.nextLine()));
		while (insurance == null) {
			System.out.println(" 존재하지 않는 보험 ID입니다 . ");
			insurance = insuranceList.search(Integer.parseInt(sc.nextLine()));
		}
		System.out.println(dd.DD);
		System.out.println("- 보험정보 -");
		System.out.println("보험 유형 : " + insurance.getProductType());
		System.out.println("보험 ID : " + insurance.getInsuranceID());
		System.out.println("보험명 : " + insurance.getInsuranceName());
		System.out.println("보험 기본 가입비 : " + insurance.getInsuranceFee());
		System.out.println("보험 보장 ( 상 ) : " + insurance.getHCoverage().getCoverageCost());
		System.out.println("보험 보장 ( 중 ) : " + insurance.getMCoverage().getCoverageCost());
		System.out.println("보험 보장 ( 하 ) : " + insurance.getLCoverage().getCoverageCost());
		System.out.println(dd.DD);

		System.out.println(" 1. 보험 확정 2. 보험 반려 ");

		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date time = new Date();
		Confirm confirm = new Confirm();
		// 반려 확인하기 보험상품을 확정한다 E1
		int choiceConfirm = 0;
		try {
			choiceConfirm = Integer.parseInt(sc.nextLine());
		} catch (Exception e) {
			System.out.println("보험상품 확정 버튼이 클릭되지 않았습니다. 다시 시도해주세요");
			insuranceConfirm();
		}
		switch (choiceConfirm) {
		case 1:
			confirm.setInsuranceID(insurance.getInsuranceID());
			confirm.setConfirmed(1);
			confirm.setConfirmDate(format.format(time));
			System.out.println("! " + format.format(time) + "에 " + insurance.getInsuranceName() + " 보험이 확정되었습니다 !");
			insurance.setConfirm(confirm);
			insuranceList.delete(insurance.getInsuranceID());
			confirmDao.create(confirm);
			break;
		case 2:
			// 반려 확인하기 보험상품을 확정한다 A1,A2
			System.out.println("해당 보험의 개설 승인을 반려하시겠습니까? y n");
			String choice = sc.nextLine();
			if (choice.equals("n") || choice.equals("N")) {
				System.out.println("반려를 취소하였습니다. 시스템이 종료됩니다.");
				System.exit(0);
			}
			try {
				System.out.println("< 보험을 반려하셨습니다 >");
				confirm.setInsuranceID(insurance.getInsuranceID());
				confirm.setConfirmed(0);
				confirm.setConfirmDate(format.format(time));
				System.out.println("반려 사유를 입력해주세요 . ");
				confirm.setConfirmRefuse(sc.nextLine());
				System.out
						.println("! " + format.format(time) + " 에 " + insurance.getInsuranceName() + " 보험을 반려하였습니다 !");
				insuranceList.delete(insurance.getInsuranceID());
				confirmDao.create(confirm);
				break;
			} catch (Exception e) {
				// 반려 확인하기 보험상품을 확정한다 E2
				System.out.println("보험 개설 승인 요청 중 요류가 발생하였습니다. 다시 시도해주세요.");
				insuranceConfirm();
			}
		default:
			break;
		}
	}

	private void salesInsuranceList() {
		System.out.println(dd.DD);
		System.out.println("1. 확정된 보험리스트 확인 2. 반려된 보험리스트 확인 ");
		try {
		switch (Integer.parseInt(sc.nextLine())) {
		case 1:
			System.out.println("- 보험목록 -");
			ArrayList<Insurance> insurances = insuranceDao.retrieveConfirm().getInsuranceList();
			for (Insurance insurance : insurances) {
				System.out.println(insurance.getInsuranceID() + " " + insurance.getInsuranceName());
			}
			System.out.println(" 확인하실 보험의 ID를 입력하세요 . ");
			Insurance insurance = insuranceList.search(Integer.parseInt(sc.nextLine()));
			while (insurance == null) {
				System.out.println("존재하지 않는 보험 ID를 입력하셨습니다. 다시 입력해주세요.");
				insurance = insuranceList.search(Integer.parseInt(sc.nextLine()));
			}
			System.out.println(dd.DD);
			System.out.println("< 보험정보 >");
			System.out.println("보험 유형 : " + insurance.getProductType());
			System.out.println("보험 ID : " + insurance.getInsuranceID());
			System.out.println("보험명 : " + insurance.getInsuranceName());
			System.out.println("보험 기본 가입비 : " + insurance.getInsuranceFee());
			System.out.println("보험 보장 ( 상 ) : " + insurance.getHCoverage().getCoverageCost());
			System.out.println("보험 보장 ( 중 ) : " + insurance.getMCoverage().getCoverageCost());
			System.out.println("보험 보장 ( 하 ) : " + insurance.getLCoverage().getCoverageCost());
			Confirm confirm = confirmDao.retrieveByInsuranceID(insurance.getInsuranceID());
			System.out.println("보험 확정일 : " + confirm.getConfirmDate());
			break;
		case 2:
			System.out.println("- 보험목록 -");
			ArrayList<Insurance> insurances2 = insuranceDao.retrieveNotConfirmed().getInsuranceList();
			for (Insurance insurance2 : insurances2) {
				System.out.println(insurance2.getInsuranceID() + " " + insurance2.getInsuranceName());
			}
			System.out.println("확인하실 보험의 ID를 입력하세요 . ");
			Insurance insurance2 = insuranceList.search(Integer.parseInt(sc.nextLine()));
			while (insurance2 == null) {
				System.out.println("존재하지 않는 보험 ID를 입력하셨습니다. 다시 입력해주세요.");
				insurance2 = insuranceList.search(Integer.parseInt(sc.nextLine()));
			}
			System.out.println(dd.DD);
			System.out.println("< 보험정보 >");
			System.out.println("보험 유형 : " + insurance2.getProductType());
			System.out.println("보험 ID : " + insurance2.getInsuranceID());
			System.out.println("보험명 : " + insurance2.getInsuranceName());
			System.out.println("보험 기본 가입비 : " + insurance2.getInsuranceFee());
			System.out.println("보험 보장 ( 상 ) : " + insurance2.getHCoverage().getCoverageCost());
			System.out.println("보험 보장 ( 중 ) : " + insurance2.getMCoverage().getCoverageCost());
			System.out.println("보험 보장 ( 하 ) : " + insurance2.getLCoverage().getCoverageCost());
			Confirm notConfirm = confirmDao.retrieveByInsuranceID(insurance2.getInsuranceID());
			System.out.println("보험 반려일 : " + notConfirm.getConfirmDate());
			System.out.println("반려 사유 : " + notConfirm.getConfirmRefuse());
		default:
			break;
		}
		}catch(Exception e) {
			System.out.println("죄송합니다. 보험 정보를 불러오지 못했습니다. 시스템을 종료합니다.");
			System.exit(0);
		}

	}

	private int checkEmployee(Employee emp) {
		String strToken[] = emp.getName().split("_");
		String str = strToken[0];
		if (str.equals("InsuranceDesigner"))
			return 1;
		else if (str.equals("InsuranceConfirmer"))
			return 2;
		else if (str.equals("SalesPerson"))
			return 3;
		else if (str.equals("UW"))
			return 4;
		else if (str.equals("ContractHandler"))
			return 5;
		else if (str.equals("ImmunityHandler"))
			return 7;
		else if (str.equals("AccidentHandler"))
			return 8;
		return 0;
	}
}
