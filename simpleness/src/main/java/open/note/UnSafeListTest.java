package open.note;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * 不安全的线程测试
 *
 * 测试结果
 * 100000
 * 99978
 * 100000
 * 99990
 * 100000
 * 100000
 * 99900
 * 100000
 * 99943
 * 100000
 */
public class UnSafeListTest {

    public static void main(String[] args) {

        for (int h = 0; h < 10; h++) {
            List<Object> list = new ArrayList<>();
            int threadCount = 1000;
            CountDownLatch countDownLatch = new CountDownLatch(threadCount);
            for(int i = 0; i < threadCount; i++)
            {
                new Thread(()->{
                    for(int j = 0; j < 100; j++)
                    {
                        list.add(new Object());
                    }
                    countDownLatch.countDown();
                }).start();
            }
            try
            {
                // 主线程等待所有子线程执行完成，再向下执行
                countDownLatch.await();
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }

            // List的size
            System.out.println(list.size());
        }
    }
}
