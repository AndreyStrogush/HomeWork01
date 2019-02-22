import java.util.Scanner;

/*
1.1 С помощью тернарного оператора написать функцию,
которая будет считать исход матча.
Метод будет принимать 4 параметра и
возвращать целое число. Футбольный матч.
4 параметра метода - результат матча и то,
что поставил пользователь. Пример: 0,2,1,1.
Что значит: команды сыграли в счёт 0:2, а пользователь ставил 1:1.
Задача: если пользователь угадал 100% результат - возвращать 2,
если угадал, что выиграла какая-либо команда
(пр. сыграли 1:2, а ставил 0:3 - то пользователь у выиграше), то возвращать 1,
если не угадал ничего - возвращать 0.
*/
public class Ternar {
    int rezult1, rezult2, forecast1, forecast2;

    public void runTernarFunction (){
        Scanner sc = new Scanner(System.in);                    //включаем сканнер, собираем информацию для работы программы

        System.out.println("Введите результат первого матча: ");        //результаты
        rezult1 = sc.nextInt();
        System.out.println("Введите результат второго матча: ");
        rezult2 = sc.nextInt();
        System.out.println("Введите ставку/прогноз на первый матч: ");      //прогнозы
        forecast1 = sc.nextInt();
        System.out.println("Введите ставку/прогноз на второй матч");
        forecast2 = sc.nextInt();

        //в зависимости от результатов работы основной функции выводим сообщения в консоль:
        switch (rezultFunction(rezult1, rezult2, forecast1, forecast2)){
            case 2:
                System.out.println("Команды сыграли со счетом " + rezult1 + ":" + rezult2 +
                        " , ваш прогноз был " + forecast1 + ":" + forecast2);
                System.out.println("Вы точно предсказали итог матча");
                break;
            case 1:
                System.out.println("Команды сыграли со счетом " + rezult1 + ":" + rezult2 +
                        " , ваш прогноз был " + forecast1 + ":" + forecast2);
                System.out.println("Вы угадали исход матча но не предсказали точный счет");
                break;
            case 0:
                System.out.println("Команды сыграли со счетом " + rezult1 + ":" + rezult2 +
                        " , ваш прогноз был " + forecast1 + ":" + forecast2);
                System.out.println("Вы не угадали ничего ");
                break;
        }
    }
    //основная функция - принимает данные о матчах и прогнозах - возвращает результат - числа 2,1,0
    private  int rezultFunction(int rezult1, int rezult2, int forecast1, int forecast2) {

        int r = (rezult1 == forecast1 && rezult2 == forecast2) ? 2 :                //если результаты совпадают с прогнозами возвращаем 2
                ((rezult1 < rezult2 && forecast1 < forecast2) ||                    //если угадали проигрыш 1 команды
                        (rezult1 > rezult2 && forecast1 > forecast2) ||             //или выигрыш 1 команды
                        (rezult1 == rezult2 && forecast1 == forecast2)) ? 1 : 0;    //или ничью - возвращаем 1
                                                                                    //во всех остальных случаях возвращаем 0
        return r;
    }


}

