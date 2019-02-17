package club.gclmit.mc.view;

import club.gclmit.mc.entity.Medicine;
import club.gclmit.mc.service.MedicineService;
import club.gclmit.mc.service.impl.MedicineServiceImpl;
import club.gclmit.mc.util.ValidationData;

import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 药材视图
 */
public class MedicineVIew {

    /**
     * 创建私有静态MedicineService的对象medicineService
     */
    private MedicineService medicineService = new MedicineServiceImpl();
    
    private ValidationData validationData = new ValidationData();

    /**
     * 中药材管理系统主界面
     */
    public void subMenu() {
        System.out.println("======中药材管理系统======");
        System.out.println("增加中药材...1");
        System.out.println("删除中药材...2");
        System.out.println("修改中药材...3");
        System.out.println("查询中药材...4");
        System.out.println("退出系统.....0");
        System.out.print("请输入您想办理的业务：");
        boolean flag = true;
        Scanner scan = new Scanner(System.in);
        while (flag) {

            /**
             * 输入字符，经if判断后跳至else，提示输入非法
             * 输入大于4的数字，经if判断，但在default范围内，提示输入非法
             */
            if (scan.hasNextInt()){
                int chooses = scan.nextInt();
                switch (chooses) {
                    case 1:
                        addMedicine();
                        break;
                    case 2:
                        deleteMedicine();
                        break;
                    case 3:
                        updateMedicine();
                        break;
                    case 4:
                        selectMedicine();
                        break;
                    case 0:
                        System.out.println("成功退出系统，欢迎再次光临！");
                        System.exit(0);
                        break;
                    default:
                        System.out.println("输入错误，请重新输入！");
                        break;
                }
            }else {
                System.out.println("输入错误，请重新输入！");
            }
            subMenu();
        }
    }

    /**
     * 增加药材
     */
    public void addMedicine() {
        boolean flag = true;
        while (flag) {
            System.out.println("=======================================");
            System.out.println("欢迎使用中药材管理系统 - 增加药材");
            Scanner scan = new Scanner(System.in);
            System.out.print("请输入药材名称：");
            String medicinename = scan.next();
            System.out.print("请输入药材五味：");
            String medicinetaste = scan.next();
            System.out.print("请输入药材药性：");
            String medicineproperties = scan.next();
            System.out.print("请输入药材颜色：");
            String medicinecolor = scan.next();
            System.out.print("请输入药材临床药用：");
            String medicineclinical = scan.next();

            /**
             * 定义boolean类型的变量，实现增加药材的接口，传入对象medicine
             */
            boolean isSuccess = medicineService.creatMedicine(new Medicine(medicinename, medicinetaste, medicineproperties, medicinecolor, medicineclinical));

            if (isSuccess) {
                System.out.println("增加药材成功");
            } else {
                System.out.println("已存在该药材，增加药材失败");
            }

            /**
             * 无论操作成功与否，均提示：1，继续 2，返回上一级菜单 3，退出系统
             */
            info("add");

            /**
             * 获取所有列表集合
             */
//            medicineService.getMedicines();
        }
    }

    /**
     * 用户操作完当前操作的提示部分
     *  @param type add,delete,select,update
     */
    public void info(String type){

        while (true){
            System.out.println();
            System.out.println("=======================================");
            System.out.println("继续当前操作请输入........1");
            System.out.println("返回上一级菜单请输入......2");
            System.out.println("退出系统请输入............0");
            System.out.print("请输入你想办理的业务：");
            Scanner scan = new Scanner(System.in);
            if (scan.hasNextInt()){
                int i = scan.nextInt();
                switch (i){
                    case 1:
                        if ("add".equals(type)){
                            addMedicine();
                        } else if ("select".equals(type)){
                            selectMedicine();
                        } else if ("update".equals(type)){
                            updateMedicine();
                        } else {
                            deleteMedicine();
                        }
                        break;
                    case 2:
                        subMenu();
                        break;
                    case 0:
                        System.exit(0);
                        break;
                    default:
                        System.out.println("输入错误，请重新输入！");
//                        break;
                }
            } else {
                System.out.println("输入错误，请重新输入！");
                continue;
            }
        }
    }

    /**
     * 删除药材
     * 1，根据药材Id删除
     * 2，删除所有药材
     */
    public void deleteMedicine() {
        boolean flag = true;
        while (flag) {
            System.out.println("=======================================");
            System.out.println("欢迎使用中药材管理系统 - 删除中药材");
            System.out.println("1.根据药材Id删除药材");
            System.out.println("2.删除所有药材");
            System.out.print("请输入您想办理的业务：");
            Scanner scan = new Scanner(System.in);
            if(scan.hasNextInt()){
                int choice = scan.nextInt();
                switch (choice){
                    case 1:
                        getMedicines();
                        System.out.print("请输入想删除的药材Id：");
                        String medicineId = scan.next();
                        if(validationData.isNumeric(medicineId) && medicineId.length() == 13){
                            if(medicineService.deleteMedicineByMedicineId(medicineId)){
                                System.out.println("删除药材成功");
                            } else {
                                System.out.println("删除药材失败,不存在该药材！");
                            }
                        } else {
                            System.out.println("输入有误!");
                        }
                        break;
                    case 2:
                        if(medicineService.deleteAllMedicine()){
                            System.out.println("删除所有药材成功");
                        } else {
                            System.out.println("无药材信息，删除所有药材失败");
                        }
                        break;
                    default:
                        System.out.println("输入有误，请重新输入!");
                        break;
                }
                info("delete");
            }else{
                System.out.println("输入有误，请重新输入！");
            }
        }
    }

    /**
     * 查询药材
     * 1，查询所有药材
     * 2，根据药材Id查询
     * 3，根据药材名字查询
     * 4，根据药材药性查询
     */
    public void selectMedicine(){

        while (true) {
            System.out.println("=======================================");
            System.out.println("欢迎使用中药材管理系统 - 查询中药材");
            System.out.println("1.查询所有药材");
            System.out.println("2.根据药材Id查询");
            System.out.println("3.根据药材名字查询");
            System.out.println("4.根据药材药性查询");
            System.out.println("5.返回上一级菜单");
            System.out.print("请输入您想办理的业务：");
            Scanner scan = new Scanner(System.in);
            if (scan.hasNextInt()){
                int choice = scan.nextInt();
                switch (choice) {
                    case 1:
                        getMedicines();
                        break;
                    case 2:
                        System.out.print("请输入查询的药材Id：");
                        String medicineId = scan.next();
                        if(validationData.isNumeric(medicineId) && medicineId.length() == 13){
                            String s = medicineService.selectMedicineByMedicineId(medicineId);
                            System.out.println(s);
                        } else {
                            System.out.println("输入Id错误！");
                        }
                        break;
                    case 3:
                        System.out.print("请输入查询的药品名称：");
                        String medicineName = scan.next();
                        String s1 = medicineService.selectMedicineByMedicinename(medicineName);
                        System.out.println(s1);
                        break;
                    case 4:
                        System.out.print("请输入查询的药性：");
                        String medicineProperties = scan.next();
                        String s2 = medicineService.selectMedicineByMedicineProperties(medicineProperties);
                        System.out.println(s2);
                        break;
                    case 5:
                        subMenu();
                        break;
                    default:
                        System.out.println("输入错误，请重新输入！");
                        break;
                }
            } else {
                System.out.println("输入错误，请重新输入！");
            }
        }
    }

    /**
     * 获取药材信息
     */
    public void getMedicines(){
        System.out.println("所有药材为: ");
        System.out.println("\t ID\t\t\t药名\t 五味\t 药性\t 颜色\t 临床药用");

        /**
         * 将获取到的数据放入medicines集合中，使用Lamada表达式（内部迭代）
         */
        List<Medicine> medicines = medicineService.getMedicines();
        medicines.forEach(medicine -> {
            System.out.println(medicine.getId()+"\t"+ medicine.getName() + "\t" + medicine.getTaste() + "\t" +  medicine.getProperties() + "\t" +  medicine.getColor() + "\t" +  medicine.getClinical());
        });
    }

    /**
     * 修改药材（根据Id修改）
     */
    public void updateMedicine() {
        boolean flag = true;
        while (flag) {
            System.out.println("=======================================");
            System.out.println("欢迎使用中药材管理系统 - 修改中药材");

            /**
             * 显示出所有药材信息
             */
            getMedicines();

            System.out.print("请输入你所需要修改中药材Id：");
            Scanner scan = new Scanner(System.in);
            String medicineId = scan.next();
            if(validationData.isNumeric(medicineId) && medicineId.length() == 13){

                System.out.print("请输入修改后药材名称：");
                String medicinename = scan.next();
                System.out.print("请输入修改后药材五味：");
                String medicinetaste = scan.next();
                System.out.print("请输入修改后药材四性：");
                String medicineproperties = scan.next();
                System.out.print("请输入修改后药材颜色：");
                String medicinecolor = scan.next();
                System.out.print("请输入修改后药材临床药用：");
                String medicineclinical = scan.next();

                boolean isSuccess = medicineService.updateMedicineByMedicineId(new Medicine(Long.parseLong(medicineId), medicinename, medicinetaste, medicineproperties, medicinecolor, medicineclinical));
                if (isSuccess){
                    System.out.println("药材修改成功");
                } else {
                    System.out.println("药材修改失败");
                }
                flag = false ;
            } else {
                System.out.println("当前药材Id输入有误!");
            }
            info("update");
            continue;
        }
    }
}