import java.math.BigInteger;
import java.util.Scanner;

public class Fibonachi {

    public void runFibonachi(){
        int size;
        Scanner sc = new Scanner(System.in);
        System.out.println("Введите размер ряда для рассчета");
        size = sc.nextInt();
        long n0 = 1;
        long n1 = 1;
        long n = 0;
        System.out.print(n0 + ", " + n1 + ", ");
        for (int i = 3; i <= size; i++ ){
            n = n0 + n1;
            System.out.print(n + ", ");
            n0 = n1;
            n1 = n;
        }
        System.out.println("...");
    }
    public void runFactorial(){
        int number;
        Scanner sc = new Scanner(System.in);
        System.out.println("Введите число, факториал которого необходимо вычислить");
        number = sc.nextInt();

        System.out.println("Факториал числа " + number + " = " + factorial(number));
    }

     private static long factorial(int number){
        long rezult = 1;
        for (int i = 1; i <= number; i ++){
            rezult = rezult*i;
        }
        return rezult;
    }
}
