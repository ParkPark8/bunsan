package Accident;



/**
 * @author dong2
 * @version 1.0
 * @created 01-5-2023 오전 8:20:42
 */
public class Accident {

	private String accidentTime;
	private String accidentType;
	private int accidentUniqueNumber;
	private String dateOfTheAccident;
	private enum degreeOfAccident{저, 중 , };
	private String locationOfAccident;
	private boolean processingStatus;
	public accidentSiteInformation m_accidentSiteInformation;

	public Accident(){

	}

	public void finalize() throws Throwable {

	}

}