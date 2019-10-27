package task1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/*
Напишите программу, которая рассчитывает и подает в стандартный вывод следующие значения:
- 90 перцентиль
- медиана
- максимальное значение
- минимальное значение
- среднее значение

Входные данные:
Данные для расчетов считываются из файла, путь к которому подается в виде аргумента.
Числа в файле целые в пределах от -32 768 до 32 767.
Каждое число с новой строки.
В файле не более 1000 строк.

Вывод:
Вывод значений в указанной последовательности, каждое значение заканчивается символом новой
строки.
Все значения с точностью до сотых: 2.50 2.00 0.03.
*/

public class task1 {
    public static void main(String[] args) {

        File file = new File(args[0]);
        List<Double> numbersList = new ArrayList<>();

        try {
            Scanner scannerSrc = new Scanner(file);

            while (scannerSrc.hasNextLine()) {
                String str = scannerSrc.nextLine();
                numbersList.add(Double.parseDouble(str));
            }

            System.out.println(String.format("%.2f", percentile(numbersList, 90.0)).replace(',', '.'));
            System.out.println(String.format("%.2f", median(numbersList)).replace(',', '.'));
            System.out.println(String.format("%.2f", maximum(numbersList)).replace(',', '.'));
            System.out.println(String.format("%.2f", minimum(numbersList)).replace(',', '.'));
            System.out.println(String.format("%.2f", average(numbersList)).replace(',', '.'));

            scannerSrc.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static Double percentile(List<Double> numbers, double percent) {
        Collections.sort(numbers);

        double size = numbers.size() - 1;
        double intervalWidth = (1/size)*100;
        int passedIntervals = (int) (percent/intervalWidth);
        int percentilePositionBefore = passedIntervals + 1;
        double percentileBefore = numbers.get(percentilePositionBefore - 1);
        double followingPercentile = numbers.get(percentilePositionBefore);
        double totalWidthIntervals = ((int) (percent/intervalWidth))*intervalWidth;
        double incompleteInterval = ((percent - totalWidthIntervals)/intervalWidth)*100;
        double incompleteIntervalAbsolute = ((followingPercentile-percentileBefore)*(incompleteInterval/100));
        double percentile = incompleteIntervalAbsolute + percentileBefore;

        return percentile;
    }

    private static Double average(List<Double> numbers) {
        double sum = 0.0;

        for (int i = 0; i < numbers.size(); i++) {
            sum = sum + numbers.get(i);
        }

        return sum/numbers.size();
    }

    private static Double minimum(List<Double> numbers) {
        Collections.sort(numbers);
        return numbers.get(0);
    }

    private static Double maximum(List<Double> numbers) {
        Collections.sort(numbers);
        return numbers.get(numbers.size() - 1);
    }

    private static Double median(List<Double> numbers) {
        Collections.sort(numbers);
        if (numbers.size() % 2 == 0) {
            return (numbers.get(numbers.size()/2) + numbers.get((numbers.size()/2)-1)) / 2;
        } else {
            return numbers.get(numbers.size()/2);
        }
    }
}
