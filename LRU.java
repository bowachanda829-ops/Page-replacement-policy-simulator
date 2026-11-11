import java.util.*;
public class LRU {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter page reference string: ");
        String input = scanner.nextLine();
        System.out.print("Enter number of frames: ");
        int frameCount = scanner.nextInt();
        simulateLRU(input, frameCount);
    }
    private static void simulateLRU(String pageRef, int frameCount) {
        int faults = 0;
        int hits = 0;
        int stored = 0;
        int []frame = new int[frameCount];
        boolean hit = false;
        for (int i = 0; i < pageRef.length(); i++) {
            char c = pageRef.charAt(i);
            int pageNum = c - '0';
            hit = Arrays.stream(frame).anyMatch(x -> x == pageNum);
            if(hit == true){
                hits++;
                hit = true;
                printCommaSeparated(frame, pageNum);
            }
            else if (stored < frameCount){
                frame[stored] = pageNum;
                stored ++;
                faults ++;
                printCommaSeparated(frame, pageNum);
            }
            else if(stored == frameCount){
                int lruIndex = -1;
                int farthestBack = Integer.MAX_VALUE;
                for (int f = 0; f < frameCount; f++) {
                    int lastUsed = -1;
                    // Search backwards through the reference string
                    for (int x = i - 1; x >= 0; x--) {
                        if ((pageRef.charAt(x) - '0') == frame[f]) {
                            lastUsed = x;
                            break;
                        }
                    }
                    // Smallest index = used longest ago
                    if (lastUsed < farthestBack) {
                        farthestBack = lastUsed;
                        lruIndex = f;
                    }
                }
                frame[lruIndex] = pageNum;
                faults++;
                printCommaSeparated(frame, pageNum);
            }
        }
        System.out.print("faults: " + faults + "\n");
        System.out.print("hits: " + hits + "\n");
    }
    public static void printCommaSeparated(int[] array, int num) {
        String result = Arrays.toString(array).replace("[", "").replace("]", "");
        System.out.println(result + " | " + num);
    }
}