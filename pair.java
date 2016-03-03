
/**
 * Created by Jordan on 2/26/2016.
 */
public class pair {
    int row, column;
    pair(){}
    pair(int row, int column){
        this.row = row;
        this.column = column;
    }

    public boolean equals(pair obj) {
        if (this.row == obj.row && this.column == obj.column){
            return true;
        }else{
            return false;
        }
    }
    @Override
    public String toString() {
        return "(" + this.row + ", " + this.column + ")";
    }
}
