import java.util.*;
import java.io.*;




/**
 *  *
 *  * Kristen Peng 260782511
 *  * collaborate wiith Grace Wu
 */
public class Multiply1 {

    private static int randomInt(int size) {
        Random rand = new Random();
        int maxval = (1 << size) - 1;
        return rand.nextInt(maxval + 1);
    }
    
    public static int[] naive(int size, int x, int y) {

        // YOUR CODE GOES HERE  (Note: Change return statement)

        x=x%(int)Math.pow(2, size);
        y=y%(int)Math.pow(2, size);

        if(size==1) {
            int[] result=new int[2];

            int cost=1;
            result[0]=x*y;
            result[1]=cost;
            return result;
        }else {
            int m=size/2+size%2;

            int a=x>>m;
            int b=x-(a<<m);
            int c=y>>m;
            int d=y-(c<<m);

            int e=naive(m,a,c)[0];
            int f=naive(m,b,d)[0];
            int g=naive(m,b,c)[0];
            int h=naive(m,a,d)[0];




            int[] result=new int[2];
            result[0]=(e<<(2*m))+((g+h)<<m)+f;
            result[1]=3*m+naive(m,a,c)[1]+naive(m,b,d)[1]+naive(m,b,c)[1]+naive(m,a,d)[1];
            return result;
        }


        
    }

    public static int[] karatsuba(int size, int x, int y) {
        
       // YOUR CODE GOES HERE  (Note: Change return statement)



        x=x%(int)Math.pow(2, size);
        y=y%(int)Math.pow(2, size);


        if(size==1) {
            int[] result=new int[2];
            result[0]=x*y;
            result[1]=1;
            return result;
        }else {
            int m=size/2+size%2;

            int a=x/(int)Math.pow(2, m);
            int c=y/(int)Math.pow(2, m);
            int b=x%(int)Math.pow(2, m);
            int d=y%(int)Math.pow(2, m);

            int e=karatsuba(m,a,c)[0];
            int f=karatsuba(m,b,d)[0];
            int g=karatsuba(m,a-b,c-d)[0];

            int[] result=new int[2];

            result[0]=(e*((int)Math.pow(2, 2*m)))+((e+f-g)*(int)Math.pow(2, m))+f;
            result[1]=6*m+karatsuba(m,a,c)[1]+karatsuba(m,b,d)[1]+karatsuba(m,a-b,c-d)[1];

            return result;
        }


    }
    
    public static void main(String[] args){

        try{
            int maxRound = 20;
            int maxIntBitSize = 16;
            for (int size=1; size<=maxIntBitSize; size++) {
                int sumOpNaive = 0;
                int sumOpKaratsuba = 0;

                for (int round=0; round<maxRound; round++) {
                    int x = randomInt(size);

                    int y = randomInt(size);

                    int[] resNaive = naive(size,x,y);
                    int[] resKaratsuba = karatsuba(size,x,y);
            
                    if (resNaive[0] != resKaratsuba[0]) {
                        System.out.println("size "+size);
                        throw new Exception("Return values do not match! (x=" + x + "; y=" + y + "; Naive=" + resNaive[0] + "; Karatsuba=" + resKaratsuba[0] + ")");
                    }
                    
                    if (resNaive[0] != (x*y)) {
                        int myproduct = x*y;
                        throw new Exception("Evaluation is wrong! (x=" + x + "; y=" + y + "; Your result=" + resNaive[0] + "; True value=" + myproduct + ")");
                    }
                    
                    sumOpNaive += resNaive[1];
                    sumOpKaratsuba += resKaratsuba[1];

                }
                int avgOpNaive = sumOpNaive / maxRound;
                int avgOpKaratsuba = sumOpKaratsuba / maxRound;

                System.out.println("size: "+size + "," + "  avgOpNaive: "+avgOpNaive + "," + "  avgOpKaratsuba: "+avgOpKaratsuba);
            }
        }
        catch (Exception e){
            System.out.println(e);
        }

   } 
}
