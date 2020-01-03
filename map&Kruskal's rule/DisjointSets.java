import java.io.*;
import java.util.*;



public class DisjointSets {

    private int[] par;//*implement trees
    private int[] rank;
    
    /* contructor: creates a partition of n elements. */
    /* Each element is in a separate disjoint set */
    DisjointSets(int n) {
        if (n>0) {
            par = new int[n];
            rank = new int[n];
            for (int i=0; i<this.par.length; i++) {
                par[i] = i;
            }
        }
    }
    
    public String toString(){
        int pari,countsets=0;
        String output = "";
        String[] setstrings = new String[this.par.length];
        /* build string for each set */
        for (int i=0; i<this.par.length; i++) {
            pari = find(i); //* pari is the representative of i
            if (setstrings[pari]==null) {
                setstrings[pari] = String.valueOf(i);
                countsets+=1;
            } else {
                setstrings[pari] += "," + i;
            }
        }
        /* print strings */
        output = countsets + " set(s):\n";
        for (int i=0; i<this.par.length; i++) {
            if (setstrings[i] != null) {
                output += i + " : " + setstrings[i] + "\n";
            }
        }
        return output;
    }
    
    /* find resentative of element i */
    public int find(int i) {
       // return par[i];
        /* Fill this method (The statement return 0 is here only to compile) */

       if (par[i]==i){
           return i;

        }else {
           return find(par[i]);
        }
    }

    /* merge sets containing elements i and j */
    public int union(int i, int j) {

        //implement rank
        int ranki=0;
        for(int k=0; k<this.par.length;k++){
            rank[k]=0;
            int find_k=find(k);
            if (k!= find_k){
                rank[find_k]++;
                rank[k]=-1;
            }
        }
        if (find(i)!=find(j)){
            if(rank[find(i)]>rank[find(j)]){
                //merge set contains j into set contains i
                //change representative of j to the one i has
                int find_i=find(i);
                int find_j=find(j);
                for(int h=0; h<par.length;h++){
                    if(par[h]==find_j)
                        par[h]=find_i;
                }
            }else if(rank[find(i)]<=rank[find(j)]){
                //merge set contains i into set contains j
                int find_i=find(i);
                int find_j=find(j);
                for(int h=0; h<par.length;h++){
                    if(par[h]==find_i)
                        par[h]=find_j;
                }
            }
        }
        /* Fill this method (The statement return 0 is here only to compile) */
        return 0;
        
    }
    
    public static void main(String[] args) {
        
        DisjointSets myset = new DisjointSets(6);
        System.out.println(myset);

        System.out.println("-> Union 2 and 3");
        myset.union(2,3);
        System.out.println(myset);
        System.out.println("-> Union 2 and 3");
        myset.union(2,3);
        System.out.println(myset);
        System.out.println("-> Union 2 and 1");
        myset.union(2,1);
        System.out.println(myset);
        System.out.println("-> Union 4 and 5");
        myset.union(4,5);
        System.out.println(myset);
        System.out.println("-> Union 3 and 1");
        myset.union(3,1);
        System.out.println(myset);
        System.out.println("-> Union 2 and 4");
        myset.union(2,4);
        System.out.println(myset);


    }

}
