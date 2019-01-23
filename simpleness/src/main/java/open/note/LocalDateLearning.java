package open.note;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @author songyang
 */
public final class LocalDateLearning {

    private final static DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy.MM");
    private final static LocalDate START_DATE = LocalDate.of(2019, 1, 1);

    private LocalDateTime localDateTime;
    private LocalDate localDate;
    private LocalTime localTime;
    // https://stackoverflow.com/questions/25113579/java-8-localdate-mapping-with-mybatis
    // https://www.cnblogs.com/jaycekon/p/6179700.html

    public static void main(String[] args) {

        LocalDateLearning learning = new LocalDateLearning();
      //  learning.toStringL();
      //  learning.now();

        String str = "2019-01-11";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.parse(str, formatter);

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = simpleDateFormat.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        LocalDateTime dateTime = LocalDateTime.now();
        System.out.println(System.currentTimeMillis());


        System.out.println(dateTime.toInstant(ZoneOffset.ofHours(8)).toEpochMilli());
        System.out.println(dateTime.toInstant(ZoneOffset.of("+08:00")).toEpochMilli());
        System.out.println(dateTime.toInstant(ZoneOffset.UTC).toEpochMilli());
        System.out.println(dateTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli());





        LocalDate date = LocalDate.now();


        date.minusWeeks(1);
        date.getDayOfMonth();
        DayOfWeek dayOfWeek = LocalDate.now().getDayOfWeek();
        dayOfWeek.getValue();
    }


    private void now() {
        localDateTime = LocalDateTime.now();
        localDate = LocalDate.now();
        localTime = LocalTime.now();
    }

    private void toStringL() {
        Date date = new Date();
        System.out.println(date.toString());
        LocalDateTime dateTime = LocalDateTime.now();
        System.out.println(dateTime.toString());
        System.out.println(dateTime.toLocalDate().toString());
        System.out.println(dateTime.toLocalTime().toString());
    }


    private static String[] last3MonthByCalendar() {
        final List<String> indices = new ArrayList<>(4);
        Calendar cal = Calendar.getInstance();
        //要先+1,才能把本月的算进去
        cal.set(Calendar.MONTH, cal.get(Calendar.MONTH) + 1);
        for (int i = 0; i < 3; i++) {
            indices.add(cal.get(Calendar.YEAR) + "-" + (cal.get(Calendar.MONTH) + 1));
            //逐次往前推1个月
            cal.set(Calendar.MONTH, cal.get(Calendar.MONTH) - 1);
        }
        final String[] array = new String[indices.size()];
        return indices.toArray(array);
    }

    private static String[] last3Months(LocalDate date) {
        final List<String> indices = new ArrayList<>(4);
        do {
            indices.add(FORMATTER.format(date));
            if (indices.size() > 2) {
                break;
            }
            date = date.minusMonths(1);
        } while (date.compareTo(START_DATE) >= 0);
        final String[] array = new String[indices.size()];
        return indices.toArray(array);
    }

}
