package Dao;

import java.sql.ResultSet;

import Insurance.Confirm;

public class ConfirmDaoImpl extends Dao implements ConfirmDao{
	public ConfirmDaoImpl() {
		try {
			super.connect();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Override
	public void create(Confirm confirm) {
		String query = "insert into Confirm ( insuranceID , confirmed , confirmDate , confirmRefuse , insuranceProblem ) values ( "+
		confirm.getInsuranceID() + ", " + 
		confirm.getconfirmed() + ", "+
		" '" + confirm.getConfirmDate() + "', "+
		" '" + confirm.getConfirmRefuse() +"', "+
		" '" + confirm.getInsuranceProblem() +"') ";
		try {
			this.execute(query);
			System.out.println("confirm 완료 ");
		}catch (Exception e) {
			e.printStackTrace();
		}
 		
	}
	@Override
	public Confirm retrieveByInsuranceID(int insuranceID) {
		String query = "select * from Confirm where insuranceID = "+insuranceID ;
		try {
			ResultSet resultSet = this.retrieve(query);
			Confirm confirm = new Confirm();
			while(resultSet.next()) {
				confirm.setConfirmDate(resultSet.getString("confirmDate"));
				confirm.setConfirmed(resultSet.getInt("confirmed"));
				confirm.setConfirmRefuse(resultSet.getString("confirmRefuse"));
				confirm.setInsuranceProblem(resultSet.getString("insuranceProblem"));
				confirm.setInsuranceID(resultSet.getInt("insuranceID"));
			}
			return confirm;
		}catch (Exception e) {
			e.printStackTrace();
		}return null;
	}

}
