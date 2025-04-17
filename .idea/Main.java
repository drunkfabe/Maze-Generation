import java.util.*;

public class Main {

    private static final char WALL = '#';
    private static final char PATH = ' ';
    private static final char VISITED = '.';

    private static char[][] maze;
    private static boolean[][] visited;
    private static int rows;
    private static int cols;
    private static List<int[]> path = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter maze rows (4-20): ");
        rows = scanner.hasNextInt() ? scanner.nextInt() : 20;
        System.out.print("Enter maze cols (4-20): ");
        cols = scanner.hasNextInt() ? scanner.nextInt() : 20;

        if (rows < 3 || cols < 3) {
            System.out.println("Invalid dimensions. Minimum size is 3x3.");
            return;
        }

        generateMaze();
        System.out.println("Generated Maze:");
        printMaze();

        int[] start = {0, 1};
        int[] end = {rows - 1, cols - 2};


    }
}