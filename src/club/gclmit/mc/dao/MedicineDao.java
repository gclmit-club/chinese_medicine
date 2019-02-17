package club.gclmit.mc.dao;

import club.gclmit.mc.entity.Medicine;

import java.util.List;

/**
 * 药材 dao 层 接口
 */
public interface MedicineDao {

    public boolean insertMedicine(Medicine medicine);

    public boolean deleteMedicineByMedicineId(String medicineId);

    public boolean deleteAllMedicine();

    public Medicine selectMedicineByMedicineName(String medicineName);

    public Medicine selectMedicineById(String medicineId);

    public List<Medicine> selectMedicineByMedicineProperties(String medincineProperties);

    public List<Medicine> selectAllMedicine();

    public boolean updateMedicineByMedicineId(Medicine medicine);

}
