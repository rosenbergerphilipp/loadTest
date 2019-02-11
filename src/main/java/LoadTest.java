import java.lang.management.ManagementFactory;
import java.util.Random;

import com.sun.management.OperatingSystemMXBean;
import sun.misc.ThreadGroupUtils;

public class LoadTest {
    public static void main(String[] args) throws InterruptedException {
        final int timeout = 1000;
        int count =0;
        com.sun.management.OperatingSystemMXBean operatingSystemMXBean = (OperatingSystemMXBean)ManagementFactory.getOperatingSystemMXBean();
        long startmillis = 0;
        long endmillis=0;
        Random r = new Random();
        for (int j= 0;j<10000;j++) {
            startmillis = System.nanoTime();
            for (int i = 0; i < 10000000; i++) {
                long x = r.nextLong();
                long y = r.nextLong();
                long z = x * y;
                x = z / (x - y);
                long a= x*z/(x+y)-(z+y*y)+(long)Math.sqrt(x)*(long)Math.pow(z,x);
                double d= r.nextDouble();
                double b=d*z;
                d=b;
            }
            endmillis = System.nanoTime();
            System.out.println((endmillis - startmillis) / 1000000);
        }
        }
}