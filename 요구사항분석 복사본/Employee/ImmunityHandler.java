package Employee;

import Accident.Accident;

/**
 * @author 82105
 * @version 1.0
 * @created 01-5-2023 ¿ÀÀü 8:20:43
 */
public class ImmunityHandler extends Employee {

	public ImmunityHandler(){

	}

	public void finalize() throws Throwable {
		super.finalize();
	}

	/**
	 * 
	 * @param accident
	 */
	public void investigate(Accident accident){

	}

	public int investigateLoss(){
		return 0;
	}

	public boolean judge(){
		return false;
	}

}