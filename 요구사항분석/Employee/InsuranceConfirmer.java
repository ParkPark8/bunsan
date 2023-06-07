package Employee;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import Insurance.Confirm;
import Insurance.Insurance;
import Insurance.InsuranceListImpl;
import main.dd;

/**
 * @author 82105
 * @version 1.0
 * @created 01-5-2023 ���� 8:20:44
 */
public class InsuranceConfirmer extends Employee {

	public InsuranceConfirmer(){
	}
	public Insurance decision(InsuranceListImpl insuranceList){
		System.out.println(dd.AA+" 보험 목록 "+dd.AA);
		insuranceList.showUnpassed();
		System.out.println("상품 확정을 진행하실 보험 ID를 선택하세요.");
		Scanner sc = new Scanner(System.in);
		Insurance insurance = insuranceList.search(sc.nextInt());
		while(insurance==null) {
			System.out.println("존재하지 않는 보험 ID를 선택하셨습니다. 다시 입력해 주세요 .");
			insurance = insuranceList.search(sc.nextInt());
		}
		System.out.println(dd.AA+" 보험정보 "+dd.AA);
		System.out.println("보험 유형 : "+insurance.getProductType()) ;
		System.out.println("보험 ID : "+ insurance.getInsuranceID());
		System.out.println("보험명 : " + insurance.getInsuranceName());
		System.out.println("보험 기본 가입비 : "+insurance.getInsuranceFee());
		System.out.println("보험 보장 ( 상 ) : " + insurance.getHCoverage().getCoverageCost());
		System.out.println("보험 보장 ( 중 ) : " + insurance.getMCoverage().getCoverageCost());
		System.out.println("보험 보장 ( 하 ) : " + insurance.getLCoverage().getCoverageCost());
		System.out.println(dd.AA+dd.AA+dd.AA);
		
		System.out.println("상품을 확정하시려면 1을, 반려하시려면 2를 입력해주세요.");
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date time = new Date();
		Confirm confirm = new Confirm();
		
		switch (sc.nextInt()) {
		case 1: 
			confirm.setInsuranceID(insurance.getInsuranceID());
			confirm.setConfirmed(1);
			confirm.setConfirmDate(format.format(time));
			System.out.println(format.format(time)+"에 "+ insurance.getInsuranceName() + " 보험이 확정되었습니다. ");
			insurance.setPassed(true);
			insurance.setConfirm(confirm);
			break;
		case 2:
			insurance.setPassed(true);
			confirm.setInsuranceID(insurance.getInsuranceID());
			confirm.setConfirmed(0);
			confirm.setConfirmDate(format.format(time));
			System.out.println("보험의 반려 사유를 입력해세요 . ");
			confirm.setConfirmRefuse(sc.next());
			System.out.println("보험의 문제점을 입력해 주세요 .");
			confirm.setInsuranceProblem(sc.next());
			System.out.println(format.format(time)+"에 "+ insurance.getInsuranceName() + " 보험이 반려었습니다. ");
			insurance.setConfirm(confirm);
			break;

		default:
			System.out.println("올바른 값을 입력해주세요.");
			break;
		}
		return insurance;
	}

}