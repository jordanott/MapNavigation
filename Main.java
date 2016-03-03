import java.io.PrintWriter;

public class Main {

    public static void main(String[] args) {
	// initializing robot nav object
        // command line input for name of map file
        RobotNavigation r = new RobotNavigation(args[0],1);
        try {
            PrintWriter writer = new PrintWriter("output.txt", "UTF-8");
            // loops 4 times to test the 4 different heuristics
            for (int x = 1; x < 5; x++) {
                // run while the current node is not the goal node
                while(!RobotNavigation.queue.get(0).pathList.get(RobotNavigation.queue.get(0).pathList.size()-1).equals(RobotNavigation.goal)){
                    RobotNavigation.exapndNode();
                }
                for (pair elem :
                        RobotNavigation.queue.get(0).pathList) {
                    if (!elem.equals(RobotNavigation.initial) && !elem.equals(RobotNavigation.goal)) {
                        RobotNavigation.map[elem.row][elem.column] = 'o';
                    }
                }
                for (int i = 0; i <RobotNavigation.N; i++) {
                    for (int j = 0; j < RobotNavigation.N; j++) {
                        writer.print(RobotNavigation.map[i][j]);
                    }
                    writer.println();
                }
                writer.println("Number of steps taken: " + RobotNavigation.queue.get(0).pathCost );
                writer.println("Number of nodes: " + RobotNavigation.queue.size());
                writer.println();
                writer.println("*********************************************");
                writer.println();
                r.type++;
                // resets the queue and list of visited nodes
                RobotNavigation.queue.clear();
                RobotNavigation.queue.add(new path(0,RobotNavigation.initial));
                RobotNavigation.visited.clear();
                RobotNavigation.visited.add(RobotNavigation.initial);
                for (int i = 0; i <RobotNavigation.N; i++) {
                    for (int j = 0; j < RobotNavigation.N; j++) {
                        if(RobotNavigation.map[i][j] == 'o'){
                            RobotNavigation.map[i][j] = '.';
                        }

                    }
                }
            }
            writer.close();
        }catch (Exception e){
            System.out.println("ERROR");
        }


    }
}
