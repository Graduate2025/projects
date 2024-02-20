package project;

public class Data {
	protected double intArrival; // time taken to arrive in queue after last user
	protected double arrival; // time of arrival relative to server start time
	protected double service; // time needed to finish a given task
	protected double delay; // time the user needed to wait in queue
	protected double workTimeA; // time need for computer A to be available again
	protected double workTimeB; // time need for computer B to be available again
	protected boolean servA; // shows whether computer A is available for use without waiting or not
	protected boolean servB; // shows whether computer B is available for use without waiting or not

	public Data(double intArrival, double oldArrival, double service, double oldWorkTimeA, double oldWorkTimeB) {
		this.intArrival = intArrival;
		this.service = service;
		arrival = this.intArrival + oldArrival;
		
		if (oldWorkTimeA <= this.intArrival) {    //checks if computer A is unused
			servA = true;
			if (oldWorkTimeB <= this.intArrival) {   // checks if computer B is also unused
				servB = true;
			} else {
				servB = false;
				this.workTimeB = oldWorkTimeB - this.intArrival;    // if B is used then this checks when will it be free
			}
			delay = 0;
			this.workTimeA = this.service;                      //recalculates time needed for A to be available for use after the given user finishes
			
			
		} else if (oldWorkTimeB <= this.intArrival) { // checks if computer B is unused
			servA = false;
			servB = true;
			delay = 0;
			this.workTimeB = this.service;          //recalculates time needed for B to be available for use after the given user finishes
			this.workTimeA = oldWorkTimeA - this.intArrival;  //recalculates time needed for A to be available for use
			
			
		} else if (oldWorkTimeA <= oldWorkTimeB) {  // if both computer are used this check if computer A becomes available before computer B
			servA = false;
			servB = false;
			delay = oldWorkTimeA - this.intArrival;       //calculates the time the user needs to wait in queue
			this.workTimeA = delay + this.service;  // recalculates time needed for A to be available for user after the given user finishes
			this.workTimeB = oldWorkTimeB - this.intArrival; //recalculates time needed for B to be available for use
			
			
		} else if (oldWorkTimeA > oldWorkTimeB) {  //the last possible situation in which both computers are being used but B becomes available quicker
			servA = false;
			servB = false;
			delay = oldWorkTimeB - this.intArrival;       //calculates the time the user needs to wait in queue    
			this.workTimeB = delay + this.service;             // recalculates time needed for B to be available for user after the given user finishes
			this.workTimeA = oldWorkTimeA - this.intArrival;  //recalculates time needed for A to be available for use
		}

	}
}
