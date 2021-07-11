public class LoopBreakContinue {
    public static void main(String[] args) {
        int[] array = new int[10];
        array[0] = 0;
        array[1] = 1;
        array[2] = 4;
        array[3] = 9;
        array[4] = 10;
        array[5] = 15;
        array[6] = 2;
        array[7] = 20;
        array[8] = 21;
        array[9] = 22;

        printOnlyEven(array);
        System.out.println("---------");
        printOnlyEven2(array);
    }

    /**
     * Exit the loop when the value is a prime number.
     * Print all even, Skip all odd.
     */
    public static void printOnlyEven(int[] array) {
        for(int n: array) {
            if(isPrime(n)) {
                break;
            } else if(n % 2 != 0) {
                continue;
            }
            System.out.println(n);
        }
    }

    // Break Continue alternative
    public static void printOnlyEven2(int[] array) {
        for(int i = 0; i < array.length && !isPrime(array[i]); i++) {
            if(array[i] % 2 == 0) {
                System.out.println(array[i]);
            }
        }
    }

    public static boolean isPrime(int n) {
        int absN = java.lang.Math.abs(n);
        if(absN >= 2) {
            for(int i = 2; i <= absN/2; i++) {
                if(absN % i == 0) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }
}
