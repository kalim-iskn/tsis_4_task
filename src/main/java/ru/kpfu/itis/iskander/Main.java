package ru.kpfu.itis.iskander;

import ru.kpfu.itis.iskander.tasks.*;

import java.sql.Timestamp;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        CsvReader csvReader = new CsvReader();
        List<Timestamp> dates = csvReader.read();
        First task1 = new First();
        System.out.println("ПЕРВОЕ ЗАДАНИЕ");
        task1.avgAndDispersion(dates);
        Second task2 = new Second();
        System.out.println("ВТОРОЕ ЗАДАНИЕ");
        task2.avgAndDispersion(dates);
        Third task3 = new Third();
        System.out.println("ТРЕТЬЕ ЗАДАНИЕ");
        task3.avgAndDispersion(dates);
        Fourth task4 = new Fourth();
        System.out.println("ЧЕТВЕРТОЕ ЗАДАНИЕ");
        task4.avgAndDispersion(dates);
        Fifth task5 = new Fifth();
        System.out.println("ПЯТОЕ ЗАДАНИЕ");
        task5.avgAndDispersion(dates);
        System.out.println("ШЕСТОЕ ЗАДАНИЕ");
        System.out.println("Минимальная дисперсия = 14.42073271547885 (кол. заявок/час) в 3 задании: По средам");
        System.out.println("Среднее значение, соответствующее минимальной дисперсии = 114.79166666666667 (кол. заявок/час)");
        System.out.println();
    }
}