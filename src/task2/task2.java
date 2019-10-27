package task2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/*
Координаты фигуры считываются из файла №1. Это вершины четырехугольника, которые
располагаются в порядке обхода фигуры по часовой стрелке.

Координаты точек считываются из файла №2.

Файлы передаются программе в качестве аргументов:
Файл с координатами четырехугольника - 1 аргумент;
Файл с координатами точек - 2 аргумент.

Количество точек от 1 до 100.
Координаты четырехугольника и точек – в диапазоне float.
Вывод каждого положения точки заканчивается символом новой строки.
 */

public class task2 {
    public static void main(String[] args) {

        File rectangleFile = new File(args[0]);
        File dotsFile = new File(args[1]);

        List<String> rectangleDots = new ArrayList<>();

        String[] rectangleDotA;
        String[] rectangleDotB;
        String[] rectangleDotC;
        String[] rectangleDotD;

        try {
            Scanner rectangleSrc = new Scanner(rectangleFile);
            while (rectangleSrc.hasNextLine()) {
                String str = rectangleSrc.nextLine();
                rectangleDots.add(str);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        rectangleDotA = rectangleDots.get(0).split(" ");
        rectangleDotB = rectangleDots.get(1).split(" ");
        rectangleDotC = rectangleDots.get(2).split(" ");
        rectangleDotD = rectangleDots.get(3).split(" ");

        try {
            Scanner dotsSrc = new Scanner(dotsFile);

            while (dotsSrc.hasNextLine()) {
                String str = dotsSrc.nextLine();
                String[] dot = str.split(" ");
                float x = Float.parseFloat(dot[0]);
                float y = Float.parseFloat(dot[1]);

                System.out.println(result(x, y, Float.parseFloat(rectangleDotA[0]), Float.parseFloat(rectangleDotA[1]),
                                                Float.parseFloat(rectangleDotB[0]), Float.parseFloat(rectangleDotB[1]),
                                                Float.parseFloat(rectangleDotC[0]), Float.parseFloat(rectangleDotC[1]),
                                                Float.parseFloat(rectangleDotD[0]), Float.parseFloat(rectangleDotD[1])));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    private static float pointAboutLine(float x, float y, float ax, float ay, float bx, float by) {
        return (((x - ax) * (by - ay)) - ((y - ay) * (bx - ax)));
    }

    private static boolean isPointIn(float x, float y, float ax, float ay, float bx, float by, float cx, float cy, float dx, float dy) {
        return pointAboutLine(x, y, ax, ay, bx, by) >= 0 &&
                pointAboutLine(x, y, bx, by, cx, cy) >= 0 &&
                pointAboutLine(x, y, cx, cy, dx, dy) >= 0 &&
                pointAboutLine(x, y, dx, dy, ax, ay) >= 0;
    }

    private static int result(float x, float y, float ax, float ay, float bx, float by, float cx, float cy, float dx, float dy) {
        if (isPointIn(x, y, ax, ay, bx, by, cx, cy, dx, dy)) {
            if ((x == ax && y == ay) || (x == bx && y == by) || (x == cx && y == cy) || (x == dx && y == dy)) {
                return 0;
            }

            if (((((bx - ax) * (y - ay)) - ((x - ax) * (by - ay))) == 0) ||
                    ((((cx - bx) * (y - by)) - ((x - bx) * (cy - by))) == 0) ||
                        ((((dx - cx) * (y - cy)) - ((x - cx) * (dy - cy))) == 0) ||
                            ((((ax - dx) * (y - dy)) - ((x - dx) * (ay - dy))) == 0)) {
                return 1;
            }
            return 2;
        } else {
            return 3;
        }
    }
}
