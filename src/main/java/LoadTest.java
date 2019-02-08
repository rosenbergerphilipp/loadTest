import java.lang.management.ManagementFactory;
import java.util.Random;

import com.sun.management.OperatingSystemMXBean;
import sun.misc.ThreadGroupUtils;

public class LoadTest {
    public static void main(String[] args) throws InterruptedException {
        final int timeout = 1000;
        int count =0;
        com.sun.management.OperatingSystemMXBean operatingSystemMXBean = (OperatingSystemMXBean)ManagementFactory.getOperatingSystemMXBean();
        Thread t = new Thread(new UselessThread());
        ThreadGroup tg = t.getThreadGroup();
        long startmillis = 0;
        long endmillis=0;
        while(true){
            if(tg.activeCount()!=1){
                endmillis=System.nanoTime();
                System.out.println("Time elapsed: "+(((endmillis-startmillis)/10000)-100000)+"\t");
                startmillis=System.nanoTime();
                t.run();
            }
        }
    }
}
class UselessThread implements Runnable{

    @Override
    public void run() {
        Random r = new Random();

        long x = r.nextLong()*r.nextLong();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long y=x/20;
        System.out.println(y);

    }
}