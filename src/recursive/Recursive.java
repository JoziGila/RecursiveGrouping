package recursive;

import java.util.ArrayList;

public class Recursive {
    public static void main(String[] args) {
        new Grouper().solve();
    }
}

class Grouper {
    int[][] matrix;
    ArrayList<Group> groups;
    
    Grouper() {
        matrix = new int[6][6];
        groups = new ArrayList<>();
        
        // Setup
        matrix[1][1] = 1;
        matrix[1][2] = 1;
        matrix[2][2] = 1;
        
        matrix[4][4] = 1;
        matrix[4][5] = 1;
        matrix[5][5] = 1;
    }
    
    public void solve(){
        for (int i = 0; i < matrix.length; i++){
            for (int j = 0; j < matrix[i].length; j++){
                if (matrix[i][j] == 1){
                    Person p = new Person(i, j);
                    Group g = new Group();
                    recursiveGrouping(p, g);
                    groups.add(g);
                }
            }
        }
        
        System.out.println("Ka gjithsej " + groups.size() + " grupe\n");
        for (int i = 0; i < groups.size(); i++){
            Group g = groups.get(i);
            System.out.println("Grupi " + (i + 1) + ": " + g.members.size() + " studente");
            for (Person p : g.members){
                System.out.println(p.x + " " + p.y);
            }
        }
    }
    
    public void recursiveGrouping(Person p, Group g){
        if (matrix[p.x][p.y] == 1) {
            g.members.add(p);
            this.matrix[p.x][p.y] = 2;
        } else return;
        
        for (Direction dir : Direction.values()){
            int nX = p.x + dir.dx;
            int nY = p.y + dir.dy;
            if (nX < 0 || nY < 0 || nX > matrix.length - 1 || nY > matrix[nX].length - 1) continue;
            
            Person n = new Person(nX, nY);
            recursiveGrouping(n, g);
        }
    }
    
    class Person {
        public int x;
        public int y;
        
        Person(int x, int y){
            this.x = x;
            this.y = y;
        }
    }
    
    class Group {
        ArrayList<Person> members = new ArrayList<>();
    }
    
    enum Direction {
        UP(-1, 0),
        RIGHT(0, 1),
        DOWN(1, 0),
        LEFT(0, -1);
        
        final int dx;
        final int dy;
        
        Direction(int dx, int dy){
            this.dx = dx;
            this.dy = dy;
        }
    }
}
