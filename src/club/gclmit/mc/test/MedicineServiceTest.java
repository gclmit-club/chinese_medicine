package club.gclmit.mc.test;


import club.gclmit.mc.entity.Medicine;
import club.gclmit.mc.service.MedicineService;
import club.gclmit.mc.service.impl.MedicineServiceImpl;

import java.util.List;

public class MedicineServiceTest {

    public static void main(String[] args) {

        MedicineService medicineService = new MedicineServiceImpl();

        List<Medicine> medicines = medicineService.getMedicines();

        medicines.forEach(medicine1 ->{
            System.out.println("原先的药品:"+medicine1);
        });

        Medicine medicine = new Medicine(1548492094307L,"白芷1","味辛","性温","白色","散风除湿");

        boolean b = medicineService.updateMedicineByMedicineId(medicine);

        System.out.println("修改数据："+b);

        List<Medicine> medicines1 = medicineService.getMedicines();

        medicines1.forEach(medicine1 ->{
            System.out.println("修改后的药品:"+medicine1);
        });
    }
}
