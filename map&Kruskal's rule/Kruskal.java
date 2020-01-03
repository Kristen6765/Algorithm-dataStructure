import java.util.*;

public class Kruskal{

    public static WGraph kruskal(WGraph g){
        WGraph MST= new WGraph();
        DisjointSets disjointSets=new DisjointSets(g.getNbNodes());
        ArrayList<Edge> edges=g.listOfEdgesSorted();
        for(int i=0; i<edges.size();i++){
            if(IsSafe(disjointSets,edges.get(i))==true){
                disjointSets.union(edges.get(i).nodes[0],edges.get(i).nodes[1]);
                MST.addEdge(edges.get(i));
            }
        }
        /* Fill this method (The statement return null is here only to compile) */
        
        return MST;
    }

    public static Boolean IsSafe(DisjointSets p, Edge e){

        /* Fill this method (The statement return 0 is here only to compile) */
        int node1=e.nodes[0];
        int node2=e.nodes[1];
        //*if representitvaes are same means that the 2 vertices of the edge are connnected already
        if(p.find(node1)==p.find(node2)){
            return false;
        }
        return true;
    
    }

    public static void main(String[] args){

        String file = args[0];
        WGraph g = new WGraph(file);
        WGraph t = kruskal(g);
        System.out.println(t);

   } 
}
