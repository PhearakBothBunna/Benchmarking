import java.io.*;
import java.util.Scanner;

public class Benchmarking {
	public static void main(String[] args) throws FileNotFoundException {

		// Create a singly linked list
		SingLinkedList linkLst = new SingLinkedList();

		//Prompt the user to input the file name if not specified in command line
		if(args.length < 1) {
			System.out.println("Run the command again and enter the file name!  ");
			System.exit(1);
		}

		// Use scanner to read the data from the given files
		Scanner scan = new Scanner(new FileInputStream(args[0]));

		// Keep the loop running until all the elements has been inserted
		long stTime = System.nanoTime();
		while(scan.hasNextInt()){
			linkLst.sortInsert(scan.nextInt());
		}
		long time_insert = System.nanoTime() - stTime;

		// Display the sorted list, min, max and median
		linkLst.sortPrint();
		System.out.println("\nTotal time to insert: " + time_insert + " ns");

		System.out.println("\nTotal time to find max is: " + linkLst.time_max + " ns");
		System.out.println("Max in the list is: " + linkLst.getMax());

		System.out.println("\nTotal time to find min is: " + linkLst.time_min + " ns");
		System.out.println("Min in the list is: " + linkLst.getMin());

		System.out.println("\nTotal time to get median is: " + linkLst.time_med + " ns");
		linkLst.getMedian();
	}

	// Create a new singly linked list class
	static class SingLinkedList {
		// Head of my linked list
		Node head;

		static class Node {
			// Declaration
			Node next;
			int data;

			// Constructor
			public Node(int data) {
				this.data = data;
			}
		}

		// Function to insert and always keep the linked list sorted
		public void sortInsert(int data) {

			// Create new node to point to
			Node newNode = new Node(data);
			Node temp = null;
			Node current = head;

			// Traversing it and place the smaller numbers first (in front)
			// Keep it sorted in ascending order
			while (current != null && current.data < data) {
				temp = current;
				current = current.next;
			}
			newNode.next = current;
			if (temp == null) {
				head = newNode;
			} else {
				temp.next = newNode;
			}
		}

		// A function to print the sorted linked list above after adding all the data
		public void sortPrint() {
			Node temp = head;
			while (temp != null) {
				System.out.print(temp.data + ", ");
				temp = temp.next;
			}
		}

		// A function to get the largest # in the sorted list
		// In this case, it's the last element
		long stTime2 = System.nanoTime();
		public int getMax() {
			Node temp = head;

			// Run while loop until we reach the last element
			while (temp.next != null) {
				temp = temp.next;
			}
			//Return the value
			return temp.data;
		}
		// Time to execute
		long time_max = System.nanoTime() - stTime2;

		// Return the smallest # in the sorted list
		// In this case, it's the 1st element
		long stTime3 = System.nanoTime();
		public int getMin() {
			return head.data;
		}
		// Time to execute
		long time_min = System.nanoTime() - stTime3;

		// A function to get the median (middle #) of the linked list
		long stTime4 = System.nanoTime();
		public void getMedian() {
			// Initializing with head
			Node temp1 = head;
			Node temp2 = head;
			Node temp3 = head;

			// While loop runs until temp2 and temp2.next is null
			// Each time it runs, temp3 will become temp1,
			// temp1 moves by 1 and temp2 moves by 2
			while (temp2 != null && temp2.next != null) {
				temp3 = temp1;
				temp1 = temp1.next;
				temp2 = temp2.next.next;
			}
			// temp1 is not null
			assert temp1 != null;
			// If the length of the list is odd, then median will be the one in temp1
			if (temp2 != null) {
				System.out.println("Median is: " + temp1.data);
			}

			// But if it's even, then median will be the result of
			// (value in temp1 + value in temp3) divides by 2
			// Result can be a float number
			else {
				System.out.println("Median is: " + (float) (temp1.data + temp3.data) / 2);
			}
		}

		// Time to execute
		long time_med = System.nanoTime() - stTime4;
	}
}






















