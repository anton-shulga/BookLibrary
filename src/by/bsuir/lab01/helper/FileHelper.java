package by.bsuir.lab01.helper;

import by.bsuir.lab01.dao.DaoException;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Антон on 26.10.2015.
 */
public class FileHelper {
    public static void append(String fileName, String text) throws IOException {
        File file = new File(fileName);

        try {
            //проверяем, что если файл не существует то создаем его
            if(!file.exists()){
                file.createNewFile();
            }
            PrintWriter out = new PrintWriter(new OutputStreamWriter(new FileOutputStream(fileName, true)));

            try {
                out.write(text);
                out.write("\n");
            } finally {
                out.close();
            }
        } catch(IOException e) {
            throw new IOException(e);
        }
    }

    public static List<String> readAllFile(String fileName) throws IOException {

        StringBuilder sb = new StringBuilder();
        File file = new File(fileName);
        List<String> lines = new ArrayList<>();

        if(!file.exists())
            throw new FileNotFoundException("File not exist");

        try {
            BufferedReader in = new BufferedReader(new FileReader( file.getAbsoluteFile()));

            try {
                String line;
                while ((line = in.readLine()) != null)
                    lines.add(line);

            } finally {
                in.close();
            }
        } catch(IOException e) {
            throw new IOException("File reading exception");
        }

        return lines;
    }


}
