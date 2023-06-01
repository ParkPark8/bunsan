package Employee;

import java.util.Scanner;

import AppConfig.dd;
import Contract.Contract;
import Insurance.Insurance;
import Insurance.InsuranceList;
import Insurance.InsuranceListImpl;
import Interview.Interview;
import Interview.InterviewList;

/**
 * @author 82105
 * @version 1.0
 * @created 01-5-2023 ���� 8:20:45
 */
public class SalesPerson extends Employee {

	public SalesPerson(){}

	public void checkList(InsuranceListImpl insuranceList){
		System.out.println(dd.AA+" 보험 목록 "+dd.AA);
		insuranceList.show();
		System.out.println("확인하실 보험의 ID를 선택해주세요.");
		Scanner scanner= new Scanner(System.in);
		Insurance insurnace = insuranceList.search(scanner.nextInt());
		while(insurnace == null) {
			System.out.println("존재하지 않는 보험 ID입니다. 다시 입력해주세요 . ");
			insurnace = insuranceList.search(scanner.nextInt());
		}
		System.out.println(dd.AA+" 보험 정보 " + dd.AA);
		System.out.println("보험 ID : " + insurnace.getInsuranceID());
		System.out.println("보험명 : "+insurnace.getInsuranceName());
		System.out.println("보험 유형 : "+insurnace.getProductType());
		System.out.println("보험 기본 가입비 : "+insurnace.getInsuranceFee());
		System.out.println("보험 보장 ( 상 ) : " +insurnace.getHCoverage().getCoverageCost());
		System.out.println("보험 보장 ( 중 ) : "+insurnace.getMCoverage().getCoverageCost());
		System.out.println("보험 보장 ( 하 ) : "+insurnace.getLCoverage().getCoverageCost());
		if(insurnace.isPassed()) {
			if(insurnace.getConfirm().getconfirmed()==0) {
				System.out.println(" <Not Confirmed> ");
				System.out.println("반려 사유 : "+insurnace.getConfirm().getConfirmRefuse());
				System.out.println("보험 문제점 : "+insurnace.getConfirm().getInsuranceProblem());
				System.out.println("보험 반려 일자 : "+insurnace.getConfirm().getConfirmDate());
				
			}else if(insurnace.getConfirm().getconfirmed()==1) {
				System.out.println("<Confirmed>");
				System.out.println("보험 확정 일자 : " + insurnace.getConfirm().getConfirmDate());
			}
		}else {
			System.out.println(dd.AA+" 아직 보험 확정 처리가 되지 않은 보험입니다 "+dd.AA);
		}
		
	}

	public void checkPerform(){}
	public void Conclude(Contract contract){}

	public Interview write(){return null;}

	public void writeList(InterviewList InterviewList){

	}

}