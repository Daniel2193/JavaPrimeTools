package special;

import basic.Generate;

public class largestWIthDifferentDigitsOnly {

    public static Runtime runtime = Runtime.getRuntime();
    public static int cp = 0;
    public static long last = 0;
    public static long start = 0;

    public static void main(String[] args) {
        start = System.currentTimeMillis();
        last = start;

        //We only need to analyse all numbers up to 9 digits since all numbers with exactly 10 different digits are dividable by 3
        int range = (int)Math.pow(10, 9);
        int largest = -1;

        boolean[] isPrime = Generate.getPrimes(range);
        for (int i = 0; i < isPrime.length; i++) {
            //Progress report every 10%
            if((i+1) % (range/10) == 0)
                report();

            if(isPrime[i])
                if(check(i))
                    largest = i;

        }
        System.out.println("\n\n----------Final report----------");
        report();
        System.out.println("\n\nResult: " + largest);
        System.out.println("Test " + (test(largest) ? "successful" : "failed"));


    }

    public static boolean check(int i){
        String s = String.valueOf(i);
        boolean result = true;
        for(int j = 0; j < 10; j++){
            if(countMatch(s, String.valueOf(j)) > 1){
                result = false;
                break;
            }
        }
        return result;
    }

    public static int countMatch(String input, String pattern){
        int tmp = 0;
        int idx = input.indexOf(pattern);
        if(idx == -1)
            return 0;
        while(idx >= 0){
            tmp++;
            input = input.substring(idx+1);
            idx = input.indexOf(pattern);
        }
        return tmp;
    }

    public static void report(){
        long now = System.currentTimeMillis();
        long delta = now - last;
        last = now;
        boolean c1 = (now - start) > 5000;
        boolean c2 = delta > 5000;
        now -= start;
        System.out.println("Checkpoint " + cp + " " + (cp < 11 ? ("(" + cp*10 + "% Analysis)") : "") + " reached after " + (c1 ? now/1000: now) + (c1 ? "sec" : "ms") + " (Segment: " + (c2 ? delta/1000: delta) + (c2 ? "sec": "ms") + ") ");
        System.out.println("Memory in use: " + (int)((runtime.totalMemory() - runtime.freeMemory())/Math.pow(10, 6)) + "MB\nMemory assigned: " + (int)(runtime.totalMemory()/Math.pow(10, 6)) + "MB\nMemory available: " + (int)(runtime.maxMemory()/Math.pow(10, 6)) + "MB\n");

        cp++;
    }

    public static boolean test(int target){
        boolean check = true;
        for(int i = 3; i <= target + 1; i+=2){
            if(target % i == 0 && target != i){
                check = false;
                break;
            }
        }
        return check;
    }
}
