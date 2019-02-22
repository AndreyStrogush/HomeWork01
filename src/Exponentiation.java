import java.util.Scanner;

/*
1.3 Возвести число в квадрат, куб, степень
 */
public class Exponentiation {
    int number, exponent;

    public void runExp() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Введите число для возведения в степень: ");
        number = sc.nextInt();
        System.out.println("Введите степень, в которую необходимо возвести число: ");
        exponent = sc.nextInt();
        System.out.println(number + " в степени " + exponent + " = " + exp(number, exponent));
    }

    private int exp(int number, int exponent) {
        int rezult;
        switch (exponent) {
            case 0:
                rezult = 1;
                break;
            default:
                rezult = 1;
                for (int i = 1; i <= exponent; i++){
                    rezult = rezult * number;
                }
        }
        return rezult;
    }
}
