import javafx.util.Pair;
import java.lang.Math;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;
import java.util.concurrent.Callable;

/**
 * Created by Jordan on 2/24/2016.
 */
public class RobotNavigation {
    static int N;
    static char[][] map = null;
    static pair initial = null;
    static pair goal = null;
    static List<pair> visited = null;
    static int type;
   static Scanner s = new Scanner(System.in);
    static List<path> queue = new ArrayList<path>();

    public static boolean inVisited(pair check){
        for (pair elem:visited ) {
            if (check.equals(elem)){
                return true;
            }
        }
        return false;
    }

    // Constructor 
    public RobotNavigation(String fileName, int t) {
        visited = new ArrayList<pair>();
        type = t;
        try(BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line = br.readLine();
            int counter = 0;
            while (line != null) {
                if (counter == 0){
                    line = line.trim();
                    N = Integer.parseInt(line);
                    map = new char[N][N];
                }
                // initializing map and filing it with input from the file
                else {
                    for (int i = 0; i < N; i++) {
                        map[counter - 1][i] = line.charAt(i);
                        if (line.charAt(i) == 'i') {
                            initial = new pair(counter - 1, i);
                        } else if (line.charAt(i) == 'g') {
                            goal = new pair(counter - 1, i);
                        }
                    }
                }
                line = br.readLine();
                counter++;
            }
        }catch(Exception e) {
            System.out.println("uh oh");
            System.out.println(e.getMessage());
        }

        queue.add(new path(0,initial));
        visited.add(initial);
    }
    // expands all possible nodes to be visited from current node
    public static void exapndNode(){
        // removing the current best path to use
        path top = queue.remove(0);
        // taking the most recent node off to expand
        pair current = top.pathList.get(top.pathList.size()-1);

        pair newPair = null;
        // can only move up,down,left or right
        if (current.row -1 != -1 && map[current.row-1][current.column]!='+'){
            path one = new path(top);
            newPair = new pair(current.row-1,current.column);
            if (!inVisited(newPair)){
                queue.add(calc(one,newPair));
            }
        }
        // can not move into a space occupied by an obstacle
        if (current.column -1 != -1 && map[current.row][current.column-1]!='+'){
            newPair = new pair(current.row,current.column-1);
            path two = new path(top);
            if (!inVisited(newPair)){

                queue.add(calc(two, newPair));
            }
        }
        if (current.row +1 != N && map[current.row+1][current.column]!='+'){
            newPair = new pair(current.row+1,current.column);
            path three = new path(top);
            if (!inVisited(newPair)){

                queue.add(calc(three,newPair));
            }
        }
        if (current.column +1 != N && map[current.row][current.column+1]!='+'){

            newPair = new pair(current.row,current.column+1);
            path four = new path(top);
            if (!inVisited(newPair)){
                queue.add(calc(four,newPair));
            }
        }

    }
    // calculates the current length of the path
    // priority of best path is based off of this
    public static path calc(path p, pair newPair){
        visited.add(newPair);
        p.pathCost++;
        switch (type){
            case 1:
                p.lenght = manhattanDistance(newPair,goal);
                break;
            case 2:
                p.lenght = euclideanDistance(newPair,goal);
                break;
            case 3:
                p.lenght = p.pathCost + manhattanDistance(newPair,goal); // A*
                break;
            case 4:
                p.lenght = p.pathCost + euclideanDistance(newPair,goal); // A*
                break;
        }
        p.pathList.add(newPair);

        Collections.sort(queue);
        return p;
    }
    // euclidean distance
    public static double euclideanDistance(pair i, pair j){
        return Math.sqrt(Math.pow((double)(i.row-j.row),2) + Math.pow((double)(i.column-j.column),2));
    }
    // manhattan distance 
    public static int manhattanDistance(pair i, pair j){
        return Math.abs(i.row - j.row) + Math.abs(i.column - j.column);
    }

}
