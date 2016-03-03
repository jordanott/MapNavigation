
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Created by Jordan on 2/26/2016.
 */
public class path implements Comparable<path>{
    double lenght =0;
    int pathCost;
    List<pair> pathList = new ArrayList<pair>();
    path(double i, pair newPair){
        this.lenght = i;
        pathList.add(newPair);

    }
    path(path copy){
        this.lenght = copy.lenght;
        for (pair elem :
                copy.pathList) {
            this.pathList.add(elem);
        }
        this.pathCost = copy.pathCost;
    }
    @Override
    public int compareTo(path o) {
        if (this.lenght > o.lenght){
            return 1;
        }else if(this.lenght == o.lenght) {
            return 0;
        }
        else{
            return -1;
        }
    }


}
