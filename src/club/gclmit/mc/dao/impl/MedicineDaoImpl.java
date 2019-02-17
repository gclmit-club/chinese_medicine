package club.gclmit.mc.dao.impl;

import club.gclmit.mc.dao.MedicineDao;
import club.gclmit.mc.entity.Medicine;
import club.gclmit.mc.io.IOHandler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

/**
 *  药材 dao层 实现类
 */
public class MedicineDaoImpl implements MedicineDao {


    /**
     * 声明一个IOHandler（泛型Medicine）的对象ioHandler
     */
    private IOHandler<Medicine> ioHandler = new IOHandler<Medicine>();


    /**
     * 声明一个静态字符常量代表文件名  统一资源定位
     */
    public static final String MEDICINE_DATA_FILE_URL = "resources/data.txt";

    /**
     * 从文本写入数据
     * @param medicine 药材对象
     * @return
     */
    @Override
    public boolean insertMedicine(Medicine medicine){
        try{
            boolean flag = false ;

            /**
             * 声明一个名为stringList的字符串泛型
             */
            List<String> stringList = ioHandler.select(MEDICINE_DATA_FILE_URL);

            /**
             * 疯狂Java   P91：foreach循环，遍历数组与集合
             * 优点：无需获得数组和集合长度，无需根据索引来访问数组元素和集合元素
             * 遍历集合，声明medicinename，将药材名分割出来，放入集合。如存在该药材名 返true，进入if 返回false
             */
            for(String medicineToString : stringList){
                String medicinename = medicineToString.split(",")[1];
                if(medicinename.equals(medicine.getName())){
                    flag=true;
                }
            }
            if(flag){
//                System.out.println("该药材已存在！");
                return false;
            }
            ioHandler.save(medicine,MEDICINE_DATA_FILE_URL);
            return true;
        }
        catch (IOException e) {
            System.out.println("插入药材对象失败");
            e.printStackTrace();
        }
        return false;
    }


    /**
     *  根据 药材id 删除药材
     * @param medicineId
     * @return
     */
    @Override
    public boolean deleteMedicineByMedicineId(String medicineId) {
        boolean flag = false;

        try {

            List<String> stringList = ioHandler.select(MEDICINE_DATA_FILE_URL);

            /**
             * 列表迭代器继承的接口是Iterator
             */
            ListIterator<String> iterator = stringList.listIterator();

            /**
             * 一个返回boolean，一个返回对象：
             *
             * hasNext():判断当前元素是否存在，并没有指向的移动 boolean
             *
             * next():返回当前元素， 并指向下一个元素
             */
            while (iterator.hasNext()) {

                String medicineToString = iterator.next();

                /**
                 * 将输入的药材Id与经分隔符分好的药材Id集合对比
                 */
                String id = medicineToString.split(",")[0];
                if(id.equals(medicineId)){
//                    System.out.println("查询到的药材为："+medicineToString);
                    iterator.remove();
                    flag = true;
                }
            }
            if (flag){
                ioHandler.update(stringList,MEDICINE_DATA_FILE_URL);
                return  true;
            }
            return  false;
        } catch (IOException e) {
            System.out.println("通过药材Id："+medicineId+"删除药材失败");
            e.printStackTrace();
        }
        return false;
    }

    /**
     *  删除所有药材,
     * @return
     */
    @Override
    public boolean deleteAllMedicine(){
        try {
            /**
             * 声明stringList，将读取到的信息存入集合
             */
            List<String> stringList = ioHandler.select(MEDICINE_DATA_FILE_URL);

            /**
             * 如果对象不为空  且  集合不为空
             */
            if(stringList !=null && !stringList.isEmpty()){

                /**
                 * 集合赋值为空
                 */
                stringList=null;
                /**
                 * 将空集合更新到文本
                 */
                ioHandler.update(stringList,MEDICINE_DATA_FILE_URL);
                return  true;
            }
               return false;
        } catch (IOException e) {
            System.out.println("删除全部药材失败");
            e.printStackTrace();
        }
        return false;
    }


    /**
     * 根据药材名查找药材
     * @param medicinename 药材名
     * @return
     */

    @Override
    public Medicine selectMedicineByMedicineName(String medicinename){
        try{
            List<String> stringList = ioHandler.select(MEDICINE_DATA_FILE_URL);
            for(String medicineToString:stringList){

                /**
                 * 将输入的药材名称与经分隔符分好的药材名称集合对比
                 */
                String Smedicinename = medicineToString.split(",")[1];
                if(Smedicinename.equals(medicinename)){
//                    System.out.println("查询到的药材为："+medicineToString);
                    return stringToMedicine(medicineToString);
                }
            }
        }
        catch (IOException e){
            System.out.println("该药材不存在，无法通过药材名: "+medicinename+"查找到该药材");
            e.printStackTrace();
        }
        return null;
    }

    /**
     *
     * @param medicineId
     * @return
     */
    @Override
    public Medicine selectMedicineById(String medicineId) {

        try{
            List<String> stringList = ioHandler.select(MEDICINE_DATA_FILE_URL);

            for(String medicineToString : stringList){

                if (medicineId.equals(medicineToString.split(",")[0])){
//                    System.out.println("查询到的药材为："+medicineToString);
                    return stringToMedicine(medicineToString);
                }
            }
        }catch (IOException e){
            System.out.println("该药材不存在，无法通过药材ID: "+medicineId+"查找到该药材");
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 根据药性查询药材
     * @param medicineProperties
     * @return
     */
    @Override
    public List<Medicine> selectMedicineByMedicineProperties(String medicineProperties){
        try{
            List<String> medicineList = new ArrayList<>();
            List<String> stringList = ioHandler.select(MEDICINE_DATA_FILE_URL);
            for(String medicineToString : stringList){

                /**
                 * 将输入的药材药性与经分隔符分好的药性集合对比
                 */
               String propertie = medicineToString.split(",")[3];
               if (propertie.equals(medicineProperties)){
                    medicineList.add(medicineToString);
               }
            }
            return stringToMedicineAll(medicineList);
        }
        catch (IOException e){
            System.out.println("该药材不存在，无法通过药性"+medicineProperties+"查到该药材");
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 调用stringToMedicineAll();药性会重复，选择批量
     *  查询所有药材
     * @return
     */
    @Override
    public List<Medicine> selectAllMedicine(){
        try{
            List<String> stringList = ioHandler.select(MEDICINE_DATA_FILE_URL);
            List<Medicine> medicineList = stringToMedicineAll(stringList);
            return  medicineList;
        }
        catch (IOException e){
            System.out.println("查询所有药材失败");
            e.printStackTrace();
        }
        return null;
    }


    /**
     *  根据 medicine 更新文本
     * @param medicine
     * @return
     */
    @Override
    public boolean updateMedicineByMedicineId(Medicine medicine){
        try{

            List<String> stringList = ioHandler.select(MEDICINE_DATA_FILE_URL);

            int listSize = stringList.size();

            /**
             * 列表迭代器继承的接口是Iterator
             */
            ListIterator<String> iterator = stringList.listIterator();

            /**
             * 一个返回boolean，一个返回对象：
             *
             * hasNext():判断当前元素是否存在，并没有指向的移动 boolean
             *
             * next():返回当前元素， 并指向下一个元素
             */
            while (iterator.hasNext()){
                String medicineToString = iterator.next();

                /**
                 * 切割字符串，判断进行 id 判断
                 */
                String id = medicineToString.split(",")[0];

                if (id.equals(medicine.getId()+"")){
                    iterator.remove();
                }

            }

            if (listSize > stringList.size()){

                ioHandler.update(stringList,MEDICINE_DATA_FILE_URL);

                /**
                 *  插入修改后的药材数据
                 */
                if (insertMedicine(medicine)){
                    return true;
                }
            }

            return  false;
        }
        catch (IOException e){
            System.out.println("更新药品失败");
            e.printStackTrace();
        }
        return false;
    }


    /**
     * 对象字符串 转换成 对象
     * @param medicineToString 对象字符串
     * @return
     */
    public Medicine stringToMedicine(String medicineToString){
        String id = medicineToString.split(",")[0];
        if(id!=null && !id.equals("") && !id.equals("null")){
            long medicineId = Long.parseLong(id);
            String name = medicineToString.split(",")[1];
            String taste = medicineToString.split(",")[2];
            String properties = medicineToString.split(",")[3];
            String color = medicineToString.split(",")[4];
            String clinical = medicineToString.split(",")[5];
            return new  Medicine(medicineId,name,taste, properties,color,clinical);
        }
        return null;
    }

    /**
     *  对象字符串批量转换成 对象
     * @param stringList 对象字符串集合
     * @return
     */
    public List<Medicine> stringToMedicineAll(List<String> stringList){
        List<Medicine> medicines = new ArrayList<>();

        /**
         * 数组的Lamada表达式（内部类）
         */
        stringList.forEach(stringToMedicine->{
            medicines.add(stringToMedicine(stringToMedicine));
        });
        return medicines;
    }
}