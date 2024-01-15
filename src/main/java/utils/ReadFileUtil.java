package utils;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import org.apache.poi.hwpf.extractor.WordExtractor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

import static org.apache.logging.log4j.util.Strings.isBlank;

public class ReadFileUtil {
    private static final List<String> docTypeList = new ArrayList<>(2);
    private static final List<String> excelTypeList = new ArrayList<>(2);
    private static final List<String> textTypeList = new ArrayList<>(1);

    static {
        docTypeList.add(".doc");
        docTypeList.add(".docx");
        excelTypeList.add(".xls");
        excelTypeList.add(".xlsx");
        textTypeList.add(".txt");
    }

    public static List<String> readFile(InputStream inputStream,String fileName) throws IOException {
        if (isBlank(fileName)) {
            throw new RuntimeException("文件名为空，上传文件异常");
        }
        String fileSuffix = fileName.substring(fileName.indexOf("."));

        if (docTypeList.contains(fileSuffix)){
            return readDoc(inputStream,fileSuffix);
        }
        else if (excelTypeList.contains(fileSuffix)){
            return readExcel(inputStream,fileSuffix);
        }
        else if (textTypeList.contains(fileSuffix)){
            return readText(inputStream);
        }
        throw new RuntimeException("当前文件上传支持doc,docx,xls,xlsx,txt");
    }

    public static List<String> readText(InputStream inputStream){
        System.out.println("read txt");
        List<String> fileAccount = new ArrayList<>();
        try {
            InputStreamReader reader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(reader);
            String line;
            while ((line=bufferedReader.readLine())!=null){
                fileAccount.add(line);
            }
            bufferedReader.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return fileAccount;
    }


    /**
     * 读取doc文件
     * @param inputStream 文件流
     * @return 读取内容
     * @throws IOException 读取异常
     */
    public static List<String> readDoc(InputStream inputStream,String suffix) throws IOException {
            String result = "";
            // 判断文件后缀是 doc 还是 docx
            if (".doc".equals(suffix)) {
                WordExtractor extractor = new WordExtractor(inputStream);
                String text = extractor.getText();
                String[] line = text.split("\\r?\\n");
                inputStream.close();
                return Arrays.asList(line);
            } else if (".docx".equals(suffix)) {
                WordExtractor wordExtractor = new WordExtractor(inputStream);
                result = wordExtractor.getText();
                String[] line = result.split("\\r?\\n");
                wordExtractor.close();
                inputStream.close();
                return Arrays.asList(line);
            } else {
                return Collections.singletonList(result);
            }
    }

    public static List<String> readExcel(InputStream inputStream,String suffix) throws IOException {
        ExcelReader reader = ExcelUtil.getReader(inputStream);
        List<List<Object>> read = reader.read();
        List<String> stringList = new ArrayList<>();
        if (CollectionUtil.isNotEmpty(read)){
            read.forEach(c-> stringList.add(String.valueOf(c.get(0))));
        }
        return stringList;
    }

    public static void main(String[] args) {
        try (InputStream inputStream = Files.newInputStream(Paths.get(String.valueOf(Paths.get("D:/test.txt"))))) {
            List<String> stringList = readFile(inputStream, "test.txt");
            System.out.println(stringList);
            System.out.println(stringList.size());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

