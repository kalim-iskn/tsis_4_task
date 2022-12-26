package ru.kpfu.itis.iskander.tasks;

import java.sql.Timestamp;
import java.time.Month;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import ru.kpfu.itis.iskander.Printer;

/**
 * Постройте статистический ряд количества заявок в течение часа за три дня
 */
public class First {

    public void avgAndDispersion(List<Timestamp> dates) {
        Map<Integer, Long> map = new HashMap<>();

        var firstDay = dates.stream().map(Timestamp::toLocalDateTime)
                .filter(t -> t.getYear() == 2020)
                .filter(t -> t.getMonth() == Month.OCTOBER)
                .filter(localDateTime -> localDateTime.getDayOfMonth() == 1)
                .map(localDateTime -> localDateTime.getHour() + 1)
                .collect(Collectors.groupingBy(Integer::intValue, Collectors.counting()));
        var secondDay = dates.stream().map(Timestamp::toLocalDateTime)
                .filter(t -> t.getYear() == 2020)
                .filter(t -> t.getMonth() == Month.OCTOBER)
                .filter(localDateTime -> localDateTime.getDayOfMonth() == 2)
                .map(localDateTime -> localDateTime.getHour() + 25)
                .collect(Collectors.groupingBy(Integer::intValue, Collectors.counting()));
        var thirdDay = dates.stream().map(Timestamp::toLocalDateTime)
                .filter(t -> t.getYear() == 2020)
                .filter(t -> t.getMonth() == Month.OCTOBER)
                .filter(localDateTime -> localDateTime.getDayOfMonth() == 3)
                .map(localDateTime -> localDateTime.getHour() + 49)
                .collect(Collectors.groupingBy(Integer::intValue, Collectors.counting()));

        map.putAll(firstDay);
        map.putAll(secondDay);
        map.putAll(thirdDay);

        map.forEach((key, value) -> Printer.print("Номер часа = {}; Кол-во заявок = {}\n", key, value));

        System.out.println("РЕЗУЛЬТАТ");
        getDispersion(getAverage(map.values()), map.values());
    }

    protected void getDispersion(double avg, Collection<Long> values) {
        var sum = values.stream()
                .map(l -> Math.pow((l - avg), 2))
                .reduce(0.0, Double::sum);
        var dispersion = Math.sqrt(sum / (values.size()));
        Printer.print("Дисперсия = {}\n", dispersion);
    }

    protected double getAverage(Collection<Long> values) {
        var avg = (double) values.stream()
                .reduce(0L, Long::sum) / values.size();
        Printer.print("Среднее значение = {}\n", avg);
        return avg;
    }
}