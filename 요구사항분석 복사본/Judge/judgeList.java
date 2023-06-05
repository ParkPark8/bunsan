package Judge;

import java.util.ArrayList;

public interface judgeList {
	public boolean add(Judge judge);
	public Judge search(int judgeID);
	public ArrayList<Judge> getJudgeList();
}
