package FunctionsAndCommands;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Objects;
import java.util.Random;

public class Graph extends Puzzle {
    private final char item;
    private final ArrayList<Graph> connections;
    private Graph end;

    public Graph(char val){
        this.item = val;
        this.connections = new ArrayList<>();
        this.end = null;
    }

    public void connect(Graph other){
        this.connections.add(other);
    }

    public void set_solution(Graph obj) {
        this.end = obj;
    }

    public String toString(){
        StringBuilder sr = new StringBuilder(this.item + ": ");
        for (Graph v : this.connections){
            sr.append(v.item).append(",");
        }
        return sr.toString();
    }

    public Graph[] extensions(){
    Graph[] data = new Graph[connections.size()];
    for (int s = 0; s < this.connections.size(); s++){
        data[s] = this.connections.get(s);
    }
    for (Graph it : data){
        it.set_solution(this.end);
    }
    return data;
    }

    public boolean fail_fast() {
        return this.connections.equals(new ArrayList<>());
    }

    public boolean is_solved(){
        return Objects.equals(this.end.item, this.item);
    }

    public static void main(String[] args) {
        String alpha = "BCDEFGHIJKLMNOPQRSTUVWXYZ";
        Graph a1 = new Graph('A');
        ArrayList<Graph> nodes = new ArrayList<>();
        nodes.add(a1);
        for (int i = 0; i < alpha.length(); i++){
            nodes.add(new Graph(alpha.charAt(i)));
        }


        for (int q = 0; q < 100; q++){
            Random r1 = new Random();
            Random r2 = new Random();
            int a = r1.nextInt(nodes.size());
            int b = r2.nextInt(nodes.size());
            nodes.get(a).connect(nodes.get(b));
        }

//        ArrayList<Graph> nodes = new ArrayList<>();
//
//        Graph a1 = new Graph('A');
//        Graph a2 = new Graph('B');
//        Graph a3 = new Graph('C');
//
//        a1.connect(a2);
//        a2.connect(a3);
//        a3.connect(a1);
//
//        nodes.add(a1);
//        nodes.add(a2);
//        nodes.add(a3);
//
        a1.set_solution(nodes.get(nodes.size() - 1));

        Solver s = new DfsSearch();
        ArrayList<Puzzle> sol = s.solve(a1, new HashSet<>());
        for (Puzzle item : sol) {
            System.out.println(item);
        }
    }
}
