package open.note;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.concurrent.CountDownLatch;
import java.util.function.Consumer;

public class UnSafeTest {

    private static String time = "2019-01-11 11:11:11";
    private static long timestamp = 1547176271000L;
    private static LocalDateTime dateTime = LocalDateTime.of(2019,1,11,11,11,11);
    private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static void main(String[] args) {
        dateFormatTest((obj)->{
            try {
                Date date = dateFormat.parse(time);
                if (date.getTime() != timestamp){
                    System.out.println(date);
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        });
        System.out.println("---------------");
        dateFormatTest((obj)->{
            try {
                LocalDateTime dateTime = LocalDateTime.parse(time,formatter);
                if (!dateTime.isEqual(UnSafeTest.dateTime)){
                    System.out.println(dateTime);
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        });
    }
    private static void dateFormatTest(Consumer runnable){
        CountDownLatch countDownLatch = new CountDownLatch(1000);
        for (int i = 0; i < 1000; i++) {
            new Thread(()->{
                runnable.accept(null);
                countDownLatch.countDown();
            }).start();
        }
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
