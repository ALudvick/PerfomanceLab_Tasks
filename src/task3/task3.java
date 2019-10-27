package task3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/*
В магазине 5 касс, в каждый момент времени к кассе стоит очередь некоторой длины.
Каждые 30 минут измеряется средняя длина очереди в каждую кассу (число с плавающей запятой)
и для каждой кассы это значение записывается в соответствующий ей файл (всего 5 файлов).
Каждое значение заканчивается символом новой строки.
Магазин работает 8 часов в день.
Рассматривается только один день.
На момент запуска приложения все значения уже находятся в файлах.
Написать программу, которая по данным замеров определяет интервал времени, когда в магазине
было наибольшее количество посетителей за день.
Аргумент программы - путь к каталогу с файлами.
В каталоге будут 5 файлов: Cash1.txt, Cash2.txt ... Cash5.txt
 */

public class task3 {
    public static void main(String[] args) {

        File cashFile1 = new File(args[0]);
        File cashFile2 = new File(args[1]);
        File cashFile3 = new File(args[2]);
        File cashFile4 = new File(args[3]);
        File cashFile5 = new File(args[4]);

        ArrayList<Float> cash1 = fileToListConvert(cashFile1);
        ArrayList<Float> cash2 = fileToListConvert(cashFile2);
        ArrayList<Float> cash3 = fileToListConvert(cashFile3);
        ArrayList<Float> cash4 = fileToListConvert(cashFile4);
        ArrayList<Float> cash5 = fileToListConvert(cashFile5);

        ArrayList<Float> sumList = new ArrayList<>();
        int maxIndex = 0;
        float maxValue = 0f;

        for (int i = 0; i < cash1.size(); i++) {
            sumList.add(cash1.get(i) + cash2.get(i) + cash3.get(i) + cash4.get(i) + cash5.get(i));
        }

        for (int i = 0; i < sumList.size(); i++) {
            if (sumList.get(i) > maxValue) {
                maxValue = sumList.get(i);
                maxIndex = i;
            }
        }

        System.out.println(maxIndex + 1);

    }

    private static ArrayList<Float> fileToListConvert(File file) {
        ArrayList<Float> list = new ArrayList<>();

        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String str = scanner.nextLine();
                list.add(Float.parseFloat(str));
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return list;
    }
}
