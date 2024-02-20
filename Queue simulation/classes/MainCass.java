package project;
import java.util.*;
public class MainCass {

	public static void main(String[] args) {
		Queue q = new Queue();
		Scanner input = new Scanner(System.in);
//		System.out.print("simulate server for (seconds) : ");
//		q.timedEnqueue(input.nextDouble());
		q.timedEnqueue(10000);
		q.print();
		System.out.println("\nTotal Users = " + (Queue.getNum()));
		System.out.println("Total Delay = " + q.delaySum());
		System.out.println("Average Delay = " + (q.delaySum() / (Queue.getNum())));
		input.close();
	}

}
