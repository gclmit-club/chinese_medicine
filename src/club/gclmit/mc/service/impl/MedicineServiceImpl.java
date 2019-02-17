package club.gclmit.mc.service.impl;

import club.gclmit.mc.dao.MedicineDao;
import club.gclmit.mc.dao.impl.MedicineDaoImpl;
import club.gclmit.mc.entity.Medicine;
import club.gclmit.mc.service.MedicineService;

import java.util.List;

public class MedicineServiceImpl implements MedicineService {

    /**
     * 多态
     * medicineDao不过就是一个指针（正式名称叫引用），它所指的对象是MedicineDaoImpl的实例
     * 接口本身是不可以直接实例化的，但是他可以声明一个指针变量（或者是函数的形参），指向他的子类的实例。
     */
    private MedicineDao medicineDao = new MedicineDaoImpl();

    /**
     * 添加药材
     * @param medicine
     * @return
     */
    @Override
    public boolean creatMedicine(Medicine medicine) {

        /**
         * 通过系统时间生成Id，唯一性
         */
        long id = System.currentTimeMillis();
        medicine.setId(id);
        if(medicineDao.insertMedicine(medicine)){
            return true;
        }
        return false;
    }

    /**
     * 通过Id删除药材
     * @param medicineId
     * @return
     */
    @Override
    public boolean deleteMedicineByMedicineId(String medicineId) {
        if(medicineDao.deleteMedicineByMedicineId(medicineId)){
            return true;
        }
        return false;
    }

    /**
     * 删除所有药材
     * @param
     * @return boolean
     */
    @Override
    public boolean deleteAllMedicine(){
        if(medicineDao.deleteAllMedicine()){
            return true;
        }
        return false;
    }

    /**
     * 通过药材名查找
     * @param medicineName
     * @return
     */
    @Override
    public String selectMedicineByMedicinename(String medicineName) {
        Medicine medicine = medicineDao.selectMedicineByMedicineName(medicineName);
        if(medicine !=null && medicineName.equals(medicine.getName())) {
            return "查找到该药材: \n" +"药材Id："+medicine.getId()+"\t药材名称："+medicine.getName() + "\t药材五味："+ medicine.getTaste() +"\t药材药性："+ medicine.getProperties()+"\t药材颜色："+ medicine.getColor() +"\t药材临床药用："+ medicine.getClinical()+"\n";        }
       return "未查到该药材";
    }

    /**
     * 通过Id查找
     * @param medicineId
     * @return
     */
    @Override
    public String selectMedicineByMedicineId(String medicineId) {
        Medicine medicine = medicineDao.selectMedicineById(medicineId);
        if(medicine != null){
            return "查找到该药材: \n" +"药材Id："+medicine.getId()+"\t药材名称："+medicine.getName() + "\t药材五味："+ medicine.getTaste() +"\t药材药性："+ medicine.getProperties()+"\t药材颜色："+ medicine.getColor() +"\t药材临床药用："+ medicine.getClinical()+"\n";
        }
        return "未查到该药材";
    }

    /**
     * 通过药性查找
     * @param MedicineProperties
     * @return
     */
    @Override
    public String selectMedicineByMedicineProperties(String MedicineProperties){
        List<Medicine> medicines = medicineDao.selectMedicineByMedicineProperties(MedicineProperties);

        StringBuilder sb  = new StringBuilder();

        /**
         * 集合对象是否为空，集合是否为空
         */
        if(medicines != null && !medicines.isEmpty()){
            sb.append("查找到药材为:\n");
            sb.append("\t ID\t\t\t 药名\t 五味\t 药性\t 颜色\t 临床药用\n");
            medicines.forEach(medicine -> {
                sb.append(medicine.getId()+"\t"+ medicine.getName() + "\t" + medicine.getTaste() + "\t" +  medicine.getProperties() + "\t" +  medicine.getColor() + "\t" +  medicine.getClinical()+"\n");

            });
            return  sb.toString();
        }
        return "未通过药性查到该药材";
    }

    /**
     * 修改药材
     * @param medicine
     * @return
     */
    @Override
    public boolean updateMedicineByMedicineId(Medicine medicine) {
        if(medicineDao.updateMedicineByMedicineId(medicine)){
            return true;
        }
        return false;
    }

    /**
     * 取得所有药材列表
     * @return 所有的药材集合
     */
    @Override
    public List<Medicine> getMedicines() {
        List<Medicine> medicines = medicineDao.selectAllMedicine();
        return medicines;
    }
}