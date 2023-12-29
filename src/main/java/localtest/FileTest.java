package localtest;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class FileTest {
    public static void main(String[] args) {
        try {
            InputStreamReader inputStream = new InputStreamReader(Files.newInputStream(Paths.get("D:/text.txt")));
            List<String> stringList = new ArrayList<>();
            stringList.add("1");
            stringList.add("2");
            stringList.add("3");
            stringList.add("4");
            stringList.add("5");
            System.out.println(String.join(",",stringList));
            BufferedReader bufferedReader = new BufferedReader(inputStream);
            String line;
            while ((line=bufferedReader.readLine())!=null){
                System.out.println(line);
            }
            bufferedReader.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
