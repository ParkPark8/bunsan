package Dao;

import Accident.Accident;
import Accident.AccidentList;
import Judge.Judge;
import Judge.judgeList;

public interface JudgeDao {
	public judgeList retrieve();
	public void update(String column , String updateQuery , Judge judge);
	public int create(Judge judge);

}
