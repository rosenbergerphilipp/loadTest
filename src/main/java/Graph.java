import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.util.Collection;
import java.util.stream.Collectors;

public class Graph {
    public static void main(String[] args) {
        File f = new File("/home/philipro/IdeaProjects/LoadTest/src/main/java/times.txt");
        try {
            int sum =0;

            Collection<String> c=Files.lines(f.toPath()).collect(Collectors.toList());
            int count =c.size();
            for (String line: c) {
                sum +=Integer.parseInt(line);
            }
            System.out.println((sum/count));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
