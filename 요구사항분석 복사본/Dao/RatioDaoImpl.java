package Dao;

import java.sql.ResultSet;

import Insurance.Confirm;
import Insurance.Ratio;

public class RatioDaoImpl extends Dao implements RatioDao{
	public RatioDaoImpl() {
		try {
			super.connect();
		}catch (Exception e) {
			e.printStackTrace();
		}
	
	}
	@Override
	public void create(Ratio ratio) {
		String query = "insert into Ratio ( insuranceID , smallRatio , freightRatio , sedanRatio , busRatio , motorCycleRatio , studentRatio , deliverRatio , busDriverRatio , freightDriverRatio , etcRatio , ratio20 , ratio30 , ratio40 , ratio50 , ratio60 , maleRatio , femaleRatio ,before2018 , after2018 )"
				+ "values ( "+
				ratio.getInsuranceID()+", "+
				" '" +ratio.getSmallRatio() + "', "+
				" '" +ratio.getFreightRatio() +"', "+
				" '" + ratio.getSedanRatio()+"', "+
				" '" +ratio.getBusRatio() +"', "+
				" '" +ratio.getMotorCycleRatio() +"', "+
				" '" +ratio.getStudentRatio() +"', "+
				" '" +ratio.getDeliverRatio() +"', "+
				" '" + ratio.getBusDriverRatio()+"', "+
				" '" + ratio.getFreightDriverRatio()+"', "+
				" '" + ratio.getEtcRatio()+"', "+
				" '" +ratio.getRatio20() +"', "+
				" '" +ratio.getRatio30() +"', "+
				" '" +ratio.getRatio40() +"', "+
				" '" + ratio.getRatio50()+"', "+
				" '" + ratio.getRatio60()+"', "+
				" '" +ratio.getMaleRatio() +"', "+
				" '" +ratio.getFemaleRatio() +"', "+
				" '" +ratio.getBefore2018() +"', "+
				" '" +ratio.getAfter2018()+"') ";
		try {
			this.execute(query);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public Ratio retrieveByInsuranceID(int insuranceID) {
		String query = "select * from Ratio where insuranceID = "+insuranceID ;
		try {
			ResultSet resultSet = this.retrieve(query);
			Ratio ratio= new Ratio();
			while(resultSet.next()) {
				//id
				ratio.setInsuranceID(insuranceID);
				//car
				ratio.setSmallRatio(resultSet.getString("smallRatio"));
				ratio.setSmallRatio(resultSet.getString("freightRatio"));
				ratio.setSmallRatio(resultSet.getString("sedanRatio"));
				ratio.setSmallRatio(resultSet.getString("busRatio"));
				ratio.setSmallRatio(resultSet.getString("motorCycleRatio"));
				//job
				ratio.setSmallRatio(resultSet.getString("studentRatio"));
				ratio.setSmallRatio(resultSet.getString("deliverRatio"));
				ratio.setSmallRatio(resultSet.getString("busDriverRatio"));
				ratio.setSmallRatio(resultSet.getString("freightDriverRatio"));
				ratio.setSmallRatio(resultSet.getString("etcRatio"));
				//age
				ratio.setSmallRatio(resultSet.getString("ratio20"));
				ratio.setSmallRatio(resultSet.getString("ratio30"));
				ratio.setSmallRatio(resultSet.getString("ratio40"));
				ratio.setSmallRatio(resultSet.getString("ratio50"));
				ratio.setSmallRatio(resultSet.getString("ratio60"));
				//sex
				ratio.setSmallRatio(resultSet.getString("maleRatio"));
				ratio.setSmallRatio(resultSet.getString("femaleRatio"));
				//madeYear
				ratio.setAfter2018(resultSet.getString("after2018"));
				ratio.setBefore2018(resultSet.getString("before2018"));
			}
			return ratio;
		}catch (Exception e) {
			e.printStackTrace();
		}return null;
	}
	

}
