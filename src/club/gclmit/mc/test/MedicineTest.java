package club.gclmit.mc.test;

import club.gclmit.mc.entity.Medicine;
import club.gclmit.mc.service.MedicineService;
import club.gclmit.mc.service.impl.MedicineServiceImpl;

import java.util.List;

public class MedicineTest {
    public static void main(String[] args) {

        MedicineService medicineService = new MedicineServiceImpl();

        boolean b = medicineService.creatMedicine(new Medicine(System.currentTimeMillis(), "山药", "味甘", "性温", "土黄色", "活血化瘀"));
        System.out.println("添加药材判断"+b);

        System.out.println("添加药材判断："+medicineService.creatMedicine(new Medicine(System.currentTimeMillis(), "牡丹23", "味辛","性温","蓝色","活血化瘀")));

//        List<Medicine> medicines = medicineService.getMedicines();
//
//        medicines.forEach(medicine -> {
//            System.out.println("当前药材s: "+medicine);
//        });
//
//        String s = medicineService.selectMedicineByMedicineId("1548233162766");
//        System.out.println("id："+s);
//
//        String s1 = medicineService.selectMedicineByMedicinename("山药1");
//
//        System.out.println("name "+s1);
//
//        String s2 = medicineService.selectMedicineByMedicineProperties("性寒");
//
//        System.out.println("pro\n"+s2);
//
//        Medicine medicine = new Medicine(1548233162765L, "山药", "味苦", "性温", "土黄色", "活血化瘀");
//        String s = medicineService.updateMedicineByMedicineId(medicine);
//        System.out.println(s);

//        String s1 = medicineService.deleteMedicineByMedicineId("1548336527037");
//        System.out.println(s1);

//        String s = medicineService.deleteAllMedicine();
//        System.out.println(s);
    }
}


