package Judge;

import java.util.ArrayList;

public class JudgeListImpl implements judgeList{
	private ArrayList<Judge> judgeList;
	public Judge judge;
	public JudgeListImpl() {
		this.judgeList = new ArrayList<Judge>();
	}
	@Override
	public boolean add(Judge judge) {
		if(this.judgeList.add(judge))return true;
		return false;
	}
	@Override
	public Judge search(int judgeID) {
		for(Judge judge : this.judgeList) {
			if(judge.getJudgeID() == judgeID) return judge;
		}
		return null;
	}
	@Override
	public ArrayList<Judge> getJudgeList() {
		return this.judgeList;
	}
	
	public Judge searchByAccidentID(String ai) {
		for(Judge judge : this.judgeList) {
			if(judge.getAccidentID().equals(ai)) return judge;
		}
		return null;
	}
}
