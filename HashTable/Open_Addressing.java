import java.io.*;
import java.util.*;
/**
 * Kristen Peng
 * 260782511
 *
 * collaborated with Grace Hu
 */
public class Open_Addressing {
     public int m; // number of SLOTS AVAILABLE
     public int A; // the default random number
     int w;
     int r;
     public int[] Table;

     protected Open_Addressing(int w, int seed, int A) {

         this.w = w;
         this.r = (int) (w-1)/2 +1;
         this.m = power2(r);

         if (A==-1){
            this.A = generateRandom((int) power2(w-1), (int) power2(w),seed);
         }
        else{
            this.A = A;
        }
         this.Table = new int[m];
         for (int i =0; i<m; i++) {
             Table[i] = -1;
         }
         
     }
     
                 /** Calculate 2^w*/
     public static int power2(int w) {
         return (int) Math.pow(2, w);
     }
     public static int generateRandom(int min, int max, int seed) {     
         Random generator = new Random(); 
                 if(seed>=0){
                    generator.setSeed(seed);
                 }
         int i = generator.nextInt(max-min-1);
         return i+min+1;
     }
        /**Implements the hash function g(k)*/
        public int probe(int key, int i) {
            //TODO: implement this function and change the return statement.
            //inplment h(k) first by using multiplication
            int h_k=((A*key)%((int) power2(w)))>>(w-r);
            int g_k=(h_k+i)%m;
            return g_k;

     }
     
     
     /**Inserts key k into hash table. Returns the number of collisions encountered*/
        public int insertKey(int key){
            //TODO : implement this and change the return statement.
            int i=0; //i start from 0??
            int index=probe(key,i);
            //if collision encountered
            while(Table[index]!=-1){
                i++;
                if (i>Table.length) {
                    System.out.println("the table is full");
                    return i;
                }
                index=probe(key,i);
            }
            Table[index]=key;
            return i;
        }
        
        /**Sequentially inserts a list of keys into the HashTable. Outputs total number of collisions */
        public int insertKeyArray (int[] keyArray){
            //TODO
            int collision = 0;
            for (int key: keyArray) {
                collision += insertKey(key);
            }
            return collision;
        }
            
         /**Inserts key k into hash table. Returns the number of collisions encountered*/
        public int removeKey(int key){
            //TODO: implement this and change the return statement
            int i=0;
            int index=probe(key,i);
            while(Table[index]!=key){
                i++;
                index=probe(key,i);
            }
            Table[index]=-2;
            return i;
        }
}
