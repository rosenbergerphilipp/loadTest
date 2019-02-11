import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collection;

public class RegressionTest {
    public static void main(String[] args) throws IOException {
        testRegressions();
        //System.out.println(""+poly3Regression(41.4));
    }

    public static void testRegressions() throws IOException {
        ArrayList<String> al = (ArrayList<String>) Files.readAllLines(new File("/home/philipro/IdeaProjects/LoadTest/src/main/java/times.csv").toPath());
        al.addAll(Files.readAllLines(new File("/home/philipro/IdeaProjects/LoadTest/src/main/java/timeslonger.csv").toPath()));
        double[][] deviations = new double[al.size()][3];
        int counter = 0;
        ArrayList<String> files=new ArrayList<>();
        double t0=0;
        for (String s : al) {
            String[] vals = s.split(";");
            double l = Double.parseDouble(vals[0]);
            if(l<2)t0=Double.parseDouble(vals[1]);
            double expectedT = Double.parseDouble(vals[1]) / t0;
            double linReg = linRegression(l);
            double poly2Reg = poly2Regression(l);
            double poly3Reg = poly3Regression(l);
            deviations[counter][0] = Math.pow((linReg - expectedT), 2);
            deviations[counter][1] = Math.pow((poly2Reg - expectedT), 2);
            deviations[counter][2] = Math.pow((poly3Reg - expectedT), 2);
            System.out.println("Deviation: " + "\t" + deviations[counter][0] + "\t" + deviations[counter][1] + "\t" + deviations[counter][2] + "\t");
            counter++;
        }
        double sumlin = 0;
        double sumpoly2 = 0;
        double sumpoly3 = 0;
        for (double[] d : deviations) {
            sumlin += d[0];
            sumpoly2 += d[1];
            sumpoly3 += d[2];
        }
        System.out.println("StdDeviation:\t" + Math.sqrt(sumlin / (deviations.length - 1)) + "\t" + Math.sqrt(sumpoly2 / (deviations.length - 1)) + "\t" + Math.sqrt(sumpoly3 / (deviations.length - 1)));
        //System.out.println("StdDeviation:\t" + (0.4619446256939238 - Math.sqrt(sumlin / (deviations.length - 1))) + "\t" + (0.23658801197656618 - Math.sqrt(sumpoly2 / (deviations.length - 1))) + "\t" + (0.17798635041223929 - Math.sqrt(sumpoly3 / (deviations.length - 1))));

    }
    private static double linRegression(double x){
        double a = 0.25148;
        double b=0.06745;
        return a*x+b;
    }
    private static double poly2Regression(double x){
        double a=0.00323;
        double b = 0.10969;
        double c = 0.8515;
        return a*Math.pow(x,2)+b*x+c;
    }
    private static double poly3Regression(double x){
        double a=0.00002;
        double b = 0.00202;
        double c = 0.12788;
        double d = 0.81315;
        return a*Math.pow(x,3)+b*Math.pow(x,2)+c*x+d;
    }

    /*private static double linRegression(double x){
        double a = 0.23188;
        double b=0.10875;
        return a*x+b;
    }
    private static double poly2Regression(double x){
        double a=0.00219;
        double b = 0.13835;
        double c = 0.72417;
        return a*Math.pow(x,2)+b*x+c;
    }
    private static double poly3Regression(double x){
        double a=-0.0001;
        double b = 0.00834;
        double c = 0.04142;
        double d = 0.93654;
        return a*Math.pow(x,3)+b*Math.pow(x,2)+c*x+d;
    }*/
    /*private static double linRegression(double x) {
        double a = 0.2319;
        double b = 0.1087;
        return a * x + b;
    }

    private static double poly2Regression(double x) {
        double a = 0.0022;
        double b = 0.1384;
        double c = 0.7242;
        return a * Math.pow(x, 2) + b * x + c;
    }

    private static double poly3Regression(double x) {
        double a = -0.0001;
        double b = 0.0083;
        double c = 0.0414;
        double d = 0.9365;
        return a * Math.pow(x, 3) + b * Math.pow(x, 2) + c * x + d;
    }*/
}
