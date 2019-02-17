package club.gclmit.mc.test;

import club.gclmit.mc.io.IOHandler;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class IOHandlerTest {

    IOHandler ioHandler = new IOHandler();

    public static void main(String[] args) throws IOException {
        File file = new File("resources/data.txt");
        if (!file.exists()){
            file.createNewFile();
        }

//         public Medicine(Integer id, String name, String taste, String properties, String color, String clinical) {
//        long id = System.currentTimeMillis();
//        Medicine medicine = new Medicine(id,"山药","味甘","性温","土黄色","活血化瘀");
//
//        System.out.println(medicine.toString());
//        FileWriter fileWriter=new FileWriter(file,true);
//        fileWriter.write(medicine.toString());
//        fileWriter.close();

//        StringBuilder medicinesToString = new StringBuilder();

        List<String> list = new ArrayList<>();
        FileReader fileReader = new FileReader(file);
        BufferedReader reader = new BufferedReader(fileReader);
        String temp;
        while ((temp=reader.readLine())!=null){
             list.add(temp);
        }
        fileReader.close();
        reader.close();

        list.forEach(s-> System.out.println(s));
    }
}
