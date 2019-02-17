package club.gclmit.mc.io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 *  IO 处理器
 * @param <T>
 */

public class IOHandler<T> {

    /**
     * 读取 txt 文件
     * @param filePath 文件路径
     * @return 返回List<String> 集合串
     * @throws IOException
     */
    public List<String> select(String filePath) throws IOException {

        File file = judgeFileExist(filePath);

        /**
         *   对象toString方法生成的字符串集合
         */
        List<String> tToStrings = new ArrayList<>();

        /**
         *  创建FileReader,使用BufferedReader 在控制台读取字符
         */
        FileReader fileReader = new FileReader(file);

        BufferedReader reader = new BufferedReader(fileReader);

        String temp;

        while ((temp=reader.readLine())!=null){
            tToStrings.add(temp);
        }

        fileReader.close();

         /**
         * 缓存流关闭
         */
        reader.close();
//        list.forEach(s-> System.out.println("读取的数据为："+s));
        return tToStrings;
    }


    /**
     * 将对象写入或者更新到 txt 文本中
     * @param t 对象
     * @param filePath 文本路径
     * @throws IOException
     */
    public void save(T t,String filePath) throws IOException {

        /**
         * 判断当前文件路径是否存在
         */
        File file = judgeFileExist(filePath);

//        System.out.println("当前操作的数据为："+t.toString());

        /**
         * file - 要写入数据的 File 对象
         *
         * append参数为 true，则将字节写入文件末尾处,相当于追加信息。(如果要清空原来的内容再写入，传false就可以)
         */
        FileWriter fileWriter = new FileWriter(file,true);
        fileWriter.write(t.toString());
        fileWriter.close();
    }


    /**
     *  批量保存数据
     * @param list  数据集合
     * @param filePath  文本路径
     * @throws IOException
     */
    public void batchSave(List<T> list,String filePath) throws IOException {
        File file = judgeFileExist(filePath);
        StringBuilder tToString = new StringBuilder();
        list.forEach(t ->{
            tToString.append(t.toString());
            if (!t.equals(list.get(list.size()-1))){
                tToString.append("\n");
            }
        });
//        System.out.println("当前操作的数据为："+tToString.toString());
        FileWriter fileWriter = new FileWriter(file,true);
        fileWriter.write(tToString.toString());
        fileWriter.close();
    }

    /**
     *  更新文本内容
     * @param list  文本数据集合
     * @param filePath 文本路径
     * @throws IOException
     */
    public void update(List<String> list,String filePath) throws IOException {
        File file = judgeFileExist(filePath);

        /**
         * 创建一个实例，实例化一个 StringBuilder 对象 tToStrings
         */
        StringBuilder tToStrings = new StringBuilder();

        String updateToString = "";

        /**
         * 遍历数组list
         */
        if(list!=null && !list.isEmpty()){
            list.forEach(tToString ->{
                tToStrings.append(tToString);
                tToStrings.append("\n");
            });
            updateToString = tToStrings.toString();
        }

//        System.out.println("当前更新的数据为："+tToString.toString());
        /**
         * append：清空原来内容
         */
        FileWriter fileWriter = new FileWriter(file,false);
        fileWriter.write(updateToString);
        fileWriter.close();
    }


    /**
     *  判断文件是否存在，如果文件不存在则创建文件
     * @param filePath 文件路径
     * @return
     * @throws IOException
     */
    public  File judgeFileExist(String filePath) throws IOException {
        File file = new File(filePath);
        if (!file.exists()){
            file.createNewFile();
        }
        return  file;
    }
}