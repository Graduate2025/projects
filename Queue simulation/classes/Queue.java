package project;

import java.util.Random;

public class Queue {
	Node front;
	Node rear;
	static int num = 0;

	public Queue() {           // initializes the server
		front = rear = new Node(0.0, 0.0, 0.0, 0.0, 0.0);
	}

	public void enqueue(double intArrival, double service) {  // adds a user to the server
		Node nNode = new Node(intArrival, rear.getArrival(), service, rear.getWorkTimeA(), rear.getWorkTimeB());
		rear.next = nNode;
		rear = nNode;
		num++;
	}

	public void timedEnqueue(double time) {  //runs the servers for a given time
		Node nNode;
		Random u = new Random();
		
		while (rear.getArrival() < time) {
			double intArrival = Queue.getRandom(u, 20);
			double service = Queue.getRandom(u, 25);
			
			if ((intArrival + rear.getArrival()) > time) {  //checks if last user comes after the server closes
				break;
			}
			nNode = new Node(intArrival, rear.getArrival(), service, rear.getWorkTimeA(), rear.getWorkTimeB());
			rear.next = nNode;
			rear = nNode;
			num++;
		}

	}

	public void print() {
		Node temp = front.next;
		for (int i = 0; i < Queue.num; i++) {
			System.out.println("interarrivalT = " + Math.round(temp.getIntArrival() * 10.0) / 10.0 
					+ "   ArrivalT =  "	+ Math.round(temp.getArrival() * 10.0) / 10.0 
					+ "   Service = "   + Math.round(temp.getService() * 10.0) / 10.0 
					+ "   Delay = "     + Math.round(temp.getDelay() * 10.0) / 10.0 
					+ "   Serv A = "    + temp.getServA() 
					+ "   Serv B = "    + temp.getServB() 
					+ "   WorkT A = "   + Math.round(temp.getWorkTimeA() * 10.0) / 10.0
					+ "   WorkT B = "   + Math.round(temp.getWorkTimeB() * 10.0) / 10.0
					);
			temp = temp.next;
		}
	}

	public double delaySum() {
		Node temp = front.next;
		
		double sum = 0;
		for (int i = 0; i < Queue.num; i++) {
			sum += temp.getDelay();
			temp = temp.next;
		}
		return sum;
	}

	public static int getNum() {
		return num;
	}

	public static double getRandom(Random u, double mean) { // generates an exponentially distribution random variable using the inversion method
		return -(Math.log(u.nextDouble()) * mean);
	}
}
