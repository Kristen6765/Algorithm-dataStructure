/**
 * Kristen Peng
 * 260782511
 *
 * collaborated with Grace Hu
 */

public class main {     

     
    public static void main(String[] args) {
    //TODO:build the hash table and insert keys using the insertKeyArray function.
        int w=10;
        int seed=123;
        Chaining chaining1=new Chaining(w, seed,683);
        Chaining chaining2=new Chaining( w,seed,554);


        int[] listX = {12, 14, 77, 74, 63, 21, 69, 13, 84, 93, 35, 89, 45, 60, 15, 57, 51, 18, 42, 62};
        int[] listY = {86, 85, 6, 97, 19, 66, 26, 14, 15, 49, 75, 64, 35, 54, 31, 9, 82, 29, 81, 13};


        System.out.println( "the collision for listX by chainning is: "+chaining1.insertKeyArray(listX));
        System.out.println( "the collision for listY by chainning is: "+chaining2.insertKeyArray(listY));


        Open_Addressing open_addressing1=new Open_Addressing(w, seed, 683);
        Open_Addressing open_addressing2=new Open_Addressing(w, seed, 554);
        System.out.println("the collision for listX by open_addressing is: "+ open_addressing1.insertKeyArray(listX));
        System.out.println("the collision for listY by open_addressing is: "+ open_addressing2.insertKeyArray(listY));




    }
}