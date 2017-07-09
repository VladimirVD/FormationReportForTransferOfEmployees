package kamnevr.Task;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

class ReadingAndWriting {
    private String line;
    private List<String> mass = new ArrayList<String>();

    /*
        Данный метод считывает строки из исходного файла и помещает их в возвращаемый массив
     */
    String[] reader(String string) {
        BufferedReader readFromFile = null;
        try {
            readFromFile = new BufferedReader(new FileReader(string));
            while ((line = readFromFile.readLine()) != null) {
                String str = line;
                String string1 = str.trim();    /*    Добавлен метод trim() для удобства дальнейшего редактирования */
                mass.add(string1);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (readFromFile != null)
                    try {
                        readFromFile.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
        }

        String[] arr = mass.toArray(new String[mass.size()]);

        return arr;
    }

    /*
        Днный метод считывает данные из переданного списка и заносит их в файл указанной директории
     */
    void methodForWriting(String str, List<String> list) {
        FileOutputStream output = null;
        try {
            try {
                output = new FileOutputStream(str);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            final String LINE_SEPARATOR = System.getProperty("line.separator");
            for (String line : list) {
                if (line != null) {
                        try {
                            output.write(line.getBytes());
                            output.write(LINE_SEPARATOR.getBytes());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                }
            }
        } finally {
            if (output != null) {
                    try {
                        output.close();
                    } catch (IOException ignore) {
                    }
            }
        }
    }
}
