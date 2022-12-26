package ru.kpfu.itis.iskander.tasks;

import ru.kpfu.itis.iskander.Printer;

import java.sql.Timestamp;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.TextStyle;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Воспользуйтесь данными двух месяцев, соседних Вашему варианту.
 * Постройте статистический ряд количества заявок в течение понедельника - воскресенья за три месяца
 */
public class Third {

    public void avgAndDispersion(List<Timestamp> dates) {
        Map<LocalDate, Long> applications = new HashMap<>();

        Arrays.asList(DayOfWeek.values()).forEach(
                day -> {
                    var november = dates.stream().map(Timestamp::toLocalDateTime)
                            .filter(t -> t.getYear() == 2020)
                            .filter(t -> t.getMonth() == Month.OCTOBER)
                            .filter(t -> t.getDayOfWeek() == day)
                            .collect(Collectors.groupingBy(LocalDateTime::toLocalDate, Collectors.counting()));
                    var december = dates.stream().map(Timestamp::toLocalDateTime)
                            .filter(t -> t.getYear() == 2020)
                            .filter(t -> t.getMonth() == Month.NOVEMBER)
                            .filter(t -> t.getDayOfWeek() == day)
                            .collect(Collectors.groupingBy(LocalDateTime::toLocalDate, Collectors.counting()));
                    var january = dates.stream().map(Timestamp::toLocalDateTime)
                            .filter(t -> t.getYear() == 2020)
                            .filter(t -> t.getMonth() == Month.DECEMBER)
                            .filter(t -> t.getDayOfWeek() == day)
                            .collect(Collectors.groupingBy(LocalDateTime::toLocalDate, Collectors.counting()));

                    applications.putAll(november);
                    applications.putAll(december);
                    applications.putAll(january);

                    int i = 1;
                    for (var monday : applications.values()) {
                        Printer.print("{} №{}; Кол-во заявок = {}\n", day.getDisplayName(TextStyle.FULL, Locale.ROOT), i, monday);
                        i++;
                    }
                    System.out.println("РЕЗУЛЬТАТ");
                    getDispersion(getAverage(applications.values()), applications.values());

                    applications.clear();
                }
        );
    }

    protected void getDispersion(double avg, Collection<Long> applicationAmountInDay) {
        var sum = applicationAmountInDay.stream().map(
                        l -> Math.pow((l - avg), 2)
                )
                .reduce(0.0, Double::sum);
        var dispersion = Math.sqrt(sum / (applicationAmountInDay.size()));
        Printer.print("Дисперсия = {} (кол. заявок/час)\n", dispersion / 24);
    }

    protected double getAverage(Collection<Long> applicationAmountInDay) {
        var avg = (double) applicationAmountInDay.stream()
                .reduce(0L, Long::sum) / applicationAmountInDay.size();
        Printer.print("Среднее значение = {} (кол. заявок/час)\n", avg / 24);
        return avg;
    }
}
