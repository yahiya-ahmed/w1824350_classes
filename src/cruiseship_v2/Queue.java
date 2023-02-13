package cruiseship_v2;

import java.util.Scanner;

/* Adapted from the study notes PDF from Blackboard */
public class Queue {
    public String qItems[] = new String[11];
    public String qDetails;
    int front = 0, end = 0;
    
    public void addQueue() {
        if (end == 10) {
            /* Queue limit reached */
            System.out.println("Queue limit reached.");
        } else {
            Scanner input = new Scanner(System.in);
            System.out.print("Enter forename to queue: ");
            qItems[end] = input.next(); /* Add forename to queue */
            end++; /* Moved end right */
            System.out.print("Enter surname to queue: ");
            qItems[end] = input.next(); /* Add surname to queue */
            end++;
        }
    }
    
    public void takeQueue() {
        if (end > front) {
            System.out.println("Details taken from queue: " + qItems[front]);
            qDetails = qItems[front]; /* Get first value from queue before removing from queue */
            front++; /* Current first item removed from queue and moved front right */
        } else {
        System.out.println("Empty queue");
        }
    }
    
    public String getFromQueue() {
        /* Get value from first item in queue that was taken out to main program */
        return qDetails;
    }
    
}