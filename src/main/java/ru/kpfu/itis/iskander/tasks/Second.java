package ru.kpfu.itis.iskander.tasks;

import ru.kpfu.itis.iskander.Printer;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Постройте статистический ряд количества заявок в течение дня за месяц
 */
public class Second {

    public void avgAndDispersion(List<Timestamp> dates) {
        Map<Integer, Long> map = dates.stream().map(Timestamp::toLocalDateTime)
                .filter(t -> t.getYear() == 2020)
                .filter(t -> t.getMonth() == Month.OCTOBER)
                .collect(Collectors.groupingBy(LocalDateTime::getDayOfMonth, Collectors.counting()));
        map
                .forEach((key, value) -> Printer.print("День месяца = {}; Кол-во заявок = {}\n", key, value));

        getDispersion(getAverage(map.values()), map.values());
    }

    protected void getDispersion(double avg, Collection<Long> values) {
        var sum = values.stream().map(
                        l -> Math.pow((l - avg), 2)
                )
                .reduce(0.0, Double::sum);
        var dispersion = Math.sqrt(sum / (values.size()));

        Printer.print("Дисперсия = {} (кол. заявок/час)\n", dispersion / 24);
    }

    protected double getAverage(Collection<Long> values) {
        var avg = (double) values.stream()
                .reduce(0L, Long::sum) / values.size();
        Printer.print("Среднее значение = {} (кол. заявок/час)\n", avg / 24);
        return avg;
    }
}