package ru.kpfu.itis.iskander;

public class Printer {

    public static void print(Object... args) {
        String res = null;
        for (Object arg : args) {
            if (res == null) {
                res = (String) arg;
            } else {
                res = res.replaceFirst("\\{}", arg.toString());
            }
        }
        System.out.print(res);
    }
}
