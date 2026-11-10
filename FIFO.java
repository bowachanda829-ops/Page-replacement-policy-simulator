import java.util.*;

public class FIFO{
	public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter page reference string: ");
        String input = scanner.nextLine();
        System.out.print("Enter number of frames: ");
        int frameCount = scanner.nextInt();
        simulateFIFO(input, frameCount);
    }
    private static void simulateFIFO(String pageRef, int frameCount){
    	int faults = 0;
        int hits = 0;
        int stored = 0;
        Queue<Integer>frame = new ArrayDeque<>();
        for (int i = 0; i < pageRef.length(); i++) {
        	char c = pageRef.charAt(i);
        	int pageNum = c - '0';
            boolean found = false;
            found = frame.contains(pageNum);
            if (found){
                hits ++;
                printCommaSeparated(frame, pageNum);
            }
            else if (!found){
                frame.add(pageNum);
                faults ++;
                if(stored == frameCount){
                    frame.remove();
                    printCommaSeparated(frame, pageNum);
                    continue;
                } 
                stored ++;  
                printCommaSeparated(frame, pageNum);                                         
            }
        }       
        System.out.print("faults: " + faults + "\n");
        System.out.print("hits: " + hits + "\n");
    }
	public static void printCommaSeparated(Queue<Integer> queue, int num) {
        String result = queue.toString().replace("[", "").replace("]", "");
        System.out.println(result + " | " + num);
    }
}