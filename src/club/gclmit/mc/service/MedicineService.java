package club.gclmit.mc.service;

import club.gclmit.mc.entity.Medicine;

import java.util.List;


/**
 * 药品管理层 接口
 */
public interface MedicineService {

    /**
     * 增加药品
     * @param medicine
     * @return
     */
    public boolean creatMedicine(Medicine medicine);

    /**
     * 通过Id删除药材接口
     * @param medicineId
     * @return
     */
    public boolean deleteMedicineByMedicineId(String medicineId);

    /**
     * 删除所有药材接口
     * @return
     */
    public boolean deleteAllMedicine();

    /**
     * 查找药材接口（药材名）
     * @param medicineName
     * @return
     */
    public String selectMedicineByMedicinename(String medicineName);

    /**
     * 查找药材接口（Id）
     * @param medicineId
     * @return
     */
    public String selectMedicineByMedicineId(String medicineId);

    /**
     * 查找药材接口（药性）
     * @param MedicineProperties
     * @return
     */
    public String selectMedicineByMedicineProperties(String MedicineProperties);

    /**
     * 修改药材接口
     * @param medicine
     * @return
     */
    public boolean updateMedicineByMedicineId(Medicine medicine);

    /**
     *所有药材列表接口
     * @return
     */
    public List<Medicine> getMedicines();


}
