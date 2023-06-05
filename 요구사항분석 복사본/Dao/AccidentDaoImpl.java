package Dao;

import java.sql.ResultSet;

import Accident.Accident;
import Accident.AccidentList;
import Accident.AccidentListImpl;

public class AccidentDaoImpl extends Dao implements AccidentDao {
	public AccidentDaoImpl() {
		try {
			this.connect();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void create(Accident accident) {
		String query = "insert into Accident (accidentType, processNum, customerID, contractID, date, time, location, video, photo, mp3) values ("
				+ "'" + accident.getAccidentType() + "', " + "'" + accident.getProcessNum() + "', " + "'"
				+ accident.getCustomerID() + "', " + "'" + accident.getContractID() + "', " + "'" + accident.getDate()
				+ "', " + "'" + accident.getTime() + "', " + "'" + accident.getLocation() + "', " + "'"
				+ accident.getVideo() + "', " + "'" + accident.getPhoto() + "', " + "'" + accident.getMp3() + "') ";
		try {
			this.execute(query);
			int accidentID = 0;
			query = "select LAST_INSERT_ID() as ID";
			ResultSet resultSet = this.retrieve(query);
			if (resultSet.next()) {
				accidentID = resultSet.getInt("ID");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public AccidentList retrieveAccidentByProcess(String processNum) {
		String query = "select * from Accident WHERE processNum = '" + processNum + "'";
		AccidentList accidentList = new AccidentListImpl();

		try {
			ResultSet resultSet = this.retrieve(query);
			while (resultSet.next()) {
				Accident accident = new Accident();
				accident.setAccidentID(resultSet.getInt("accidentID"));
				accident.setAccidentType(resultSet.getString("accidentType"));
				accident.setProcessNum(resultSet.getString("processNum"));
				accident.setCustomerID(resultSet.getString("customerID"));
				accident.setContractID(resultSet.getString("contractID"));
				accident.setDate(resultSet.getString("date"));
				accident.setTime(resultSet.getString("time"));
				accident.setLocation(resultSet.getString("location"));
				accident.setVideo(resultSet.getString("video"));
				accident.setPhoto(resultSet.getString("photo"));
				accident.setMp3(resultSet.getString("mp3"));
				accidentList.add(accident);
			}
			return accidentList;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}


	@Override
	public AccidentList retrieve() {
		String query = "select * from Accident";
		AccidentListImpl accidentList = new AccidentListImpl();

		try {
			ResultSet resultSet = this.retrieve(query);
			while (resultSet.next()) {
				Accident accident = new Accident();
				accident.setAccidentID(resultSet.getInt("accidentID"));
				accident.setAccidentType(resultSet.getString("accidentType"));
				accident.setProcessNum(resultSet.getString("processNum"));
				accident.setCustomerID(resultSet.getString("customerID"));
				accident.setContractID(resultSet.getString("contractID"));
				accident.setDate(resultSet.getString("date"));
				accident.setTime(resultSet.getString("time"));
				accident.setLocation(resultSet.getString("location"));
				accident.setVideo(resultSet.getString("video"));
				accident.setPhoto(resultSet.getString("photo"));
				accident.setMp3(resultSet.getString("mp3"));
				accidentList.add(accident);
			}
			return accidentList;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return accidentList;
	}

	@Override
	public void update(String column, String updateQuery, Accident accident) {
		int accidentNumber = accident.getAccidentID();
		String query = "UPDATE Accident SET " + column + " = " + updateQuery + " WHERE accidentID = '" 
				+ accidentNumber+"'";
		try {
			this.update(query);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
