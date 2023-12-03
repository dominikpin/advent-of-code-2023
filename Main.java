import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        if (args.length != 2) {
            System.err.println("Wrong amount of arguments! Intended use:\njava Main [day] [star]");
            System.exit(1);
        }

        String day = args[0];
        if (Integer.parseInt(day) < 10) {
            day = "0" + day;
        }
        String star = args[1];

        String className = "day" + day + ".Problem_" + star;

        try {
            Class<?> problemClass = Class.forName(className);
            problemClass.getDeclaredConstructor().newInstance();
        } catch (ReflectiveOperationException e) {
            System.err.printf("day %d, problem %s not found.", Integer.parseInt(day), star);
        }
    }
}