import java.util.*;

public class Optimal{
	public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter page reference string(0-9): ");
        String input = scanner.nextLine();
        System.out.print("Enter number of frames: ");
        int frameCount = scanner.nextInt();
        simulateOptimal(input, frameCount);
    }
    private static void simulateOptimal(String pageRef, int frameCount) {
    	int faults = 0;
        int hits = 0;
        int stored = 0;
        int []frame = new int[frameCount];
        Arrays.fill(frame, -1);
        boolean hit = false;
        for (int i = 0; i < pageRef.length(); i++) {
        	char c = pageRef.charAt(i);
        	int pageNum = c - '0';
        	hit = Arrays.stream(frame).anyMatch(x -> x == pageNum);       
        	if(hit){
            	hits++;
            	printCommaSeparated(frame , pageNum);
            }
            else if (stored < frameCount && hit == false){
                frame[stored] = pageNum;
                stored ++;
                faults ++;
                printCommaSeparated(frame , pageNum);
            }
            else if (stored == frameCount && hit == false) { // if the array is full, replace with one used in the farthest future
                int targetIndex = 0;
                int farthest = -1;
                for (int j = 0; j < frameCount; j++) {

                    int nextUse = Integer.MAX_VALUE;

                    for (int k = i + 1; k < pageRef.length(); k++) {
                        if (frame[j] == pageRef.charAt(k) - '0') {
                            nextUse = k;
                            break;
                        }
                    }

                    if (nextUse == Integer.MAX_VALUE) {
                        targetIndex = j;
                        break;
                    }

                    if (nextUse > farthest) {
                        farthest = nextUse;
                        targetIndex = j;
                    }
                }

                frame[targetIndex] = pageNum;
                faults++;
                printCommaSeparated(frame , pageNum);
            }
            hit = false;
        }
        System.out.print("faults: " + faults + "\n");
        System.out.print("hits: " + hits + "\n");
    }
    public static void printCommaSeparated(int[] array, int num) {
        String result = Arrays.toString(array).replace("[", "").replace("]", "").replace("-1"," ");
        System.out.println(result + " | " + num);
    }
}