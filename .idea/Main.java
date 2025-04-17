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

        visited = new boolean[rows][cols];
        if (dfs(start[0], start[1], end)) {
            markPath();
            System.out.println("Path found:");
        } else {
            System.out.println("No path found.");
        }

        printMaze();
    }

    private static void generateMaze() {
        maze = new char[rows][cols];

        for (int r = 0; r < rows; r++)
            Arrays.fill(maze[r], WALL);

        maze[1][1] = PATH;
        carve(1, 1);

        maze[0][1] = PATH;
        maze[rows - 1][cols - 2] = PATH;
    }
    private static void carve(int r, int c) {
        int[][] directions = {{0, 2}, {2, 0}, {0, -2}, {-2, 0}};
        Collections.shuffle(Arrays.asList(directions), new Random());

        for (int[] dir : directions) {
            int nr = r + dir[0], nc = c + dir[1];
            if (isValidCell(nr, nc) && maze[nr][nc] == WALL) {
                maze[nr][nc] = PATH;
                maze[r + dir[0] / 2][c + dir[1] / 2] = PATH;
                carve(nr, nc);
            }
        }
    }
    private static boolean isValidCell(int r, int c) {
        return r > 0 && r < rows - 1 && c > 0 && c < cols - 1;
    }

    private static boolean dfs(int r, int c, int[] end) {
        if (r < 0 || r >= rows || c < 0 || c >= cols)
            return false;

        if (visited[r][c] || maze[r][c] != PATH)
            return false;

        visited[r][c] = true;
        path.add(new int[]{r, c});

        if (r == end[0] && c == end[1])
            return true;

        if (dfs(r - 1, c, end) || dfs(r + 1, c, end) || dfs(r, c - 1, end) || dfs(r, c + 1, end))
            return true;

        path.remove(path.size() - 1); // backtrack
        return false;
    }


}