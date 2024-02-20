package project;

public class Node {

	Data data;
	Node next;

	public Node(double intArrival, double oldArrival, double service, double workTimeA, double workTimeB) {
		data = new Data(intArrival, oldArrival, service, workTimeA, workTimeB);
	}

	public double getIntArrival() {
		return data.intArrival;
	}

	public double getArrival() {
		return data.arrival;
	}

	public double getService() {
		return data.service;
	}

	public double getDelay() {
		return data.delay;
	}

	public double getWorkTimeA() {
		return data.workTimeA;
	}

	public double getWorkTimeB() {
		return data.workTimeB;
	}

	public boolean getServA() {
		return data.servA;
	}

	public boolean getServB() {
		return data.servB;
	}
}