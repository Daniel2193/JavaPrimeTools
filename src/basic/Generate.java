package basic;

import java.util.Arrays;

public class Generate {

    public static void main(String[] args) {

        //Starting point to calculate how long it took
        long start = System.currentTimeMillis();

        //Specifying the range that should be analysed
        int range = (int)Math.pow(10, 9);

        getPrimes(range);

        System.out.println("Elapsed time: " + (System.currentTimeMillis() - start) + "ms");
    }

    public static boolean[] getPrimes(int range){

        //Using a boolean array to reduce memory usage
        //The array to store if a number (index of the array) is a prime
        boolean[] isPrime = new boolean[range];
        Arrays.fill(isPrime, true);

        //Using the Sieve of Eratosthenes
        isPrime[0] = false;
        isPrime[1] = false;
        for(int i = 2; i < Math.sqrt(range); i++){
            if(isPrime[i]){
                for(int j = (int)Math.pow(i, 2); j < range; j+=i){
                    isPrime[j] = false;
                }
            }
        }
        return isPrime;
    }
}
