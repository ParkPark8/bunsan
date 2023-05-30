package Employee;

import java.util.Scanner;

import AppConfig.dd;
import Insurance.Coverage;
import Insurance.Insurance;

/**
 * @author 82105
 * @version 1.0
 * @created 01-5-2023 ���� 8:20:44
 */
public class InsuranceDesigner extends Employee {
	public InsuranceDesigner(){
	}
	public int calcRate(){
		return 0;
	}
	public Insurance designProduct(){
		Scanner sc = new Scanner(System.in);
		Insurance insurance = new Insurance();
		System.out.println(dd.DD);
		System.out.println("보험 설계를 시작합니다 . ");
		System.out.println("설계하고자 하는 보험 상품 유형을 선택해 주세요 . ");
		System.out.println("1. 자동차 종합 보험 ");
		int menu = sc.nextInt();
		switch (menu) {
		case 1: {
			System.out.println("보험명을 입력해주세요.");
			insurance.setInsuranceName(sc.next());
			System.out.println("보험 ID를 입력해 주세요 . ");
			int insID = sc.nextInt();
			insurance.setInsuranceID(insID);
			System.out.println("보험 기본 가입비를 입력해주세요. ");
			insurance.setInsuranceFee(sc.nextInt());
			int coverage[] = new int[3];
			System.out.println("사고위험 정도가 낮을 경우의 보장가능 금액을 입력해주세요.");
			Coverage LCoverage = new Coverage();
			LCoverage.setCoverageCondition("Low");
			LCoverage.setCoverageCost(sc.nextInt());
			LCoverage.setInsuranceID(insID);
			insurance.setLCoverage(LCoverage);
			System.out.println("사고위험 정도가 중간일 경우의 보장가능 금액을 입력해주세요.");
			Coverage mCoverage = new Coverage();
			mCoverage.setCoverageCondition("Middle");
			mCoverage.setCoverageCost(sc.nextInt());
			mCoverage.setInsuranceID(insID);
			insurance.setMCoverage(mCoverage);
			System.out.println("사고위험 정도가 높은 경우의 보장가능 금액을 입력해주세요.");
			Coverage hCoverage = new Coverage();
			hCoverage.setCoverageCondition("High");
			hCoverage.setCoverageCost(sc.nextInt());
			hCoverage.setInsuranceID(insID);
			insurance.setHCoverage(hCoverage);
			
			break;
		}
		default:
			System.out.println("잘못된 값을 입력하셨습니다 .");
		}
		System.out.println("보험 설계가 완료되었습니다.");
		return insurance;
	}

}