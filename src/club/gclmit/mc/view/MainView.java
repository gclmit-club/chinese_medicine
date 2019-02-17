package club.gclmit.mc.view;

import java.util.Scanner;

/**
 * 主视图
 */
public class MainView {

    /**
     * 创建私有静态UserView的对象userView
     */
    private static  UserView userView = new UserView();

    /**
     * 创建私有静态MedicineVIew的对象medicineVIew
     */
    private static MedicineVIew medicineVIew = new MedicineVIew();

    /**
     * 登录注册界面
     */
    public void printMenu() {
        System.out.println("===========欢迎使用中药材系统=========");
        System.out.println("登录...........1");
        System.out.println("注册...........2");
        System.out.println("修改密码.......3");
        System.out.println("退出系统.......0");
        System.out.print("请输入您想办理的业务：");
    }

    /**
     * 启动类
     */
    public void  start() throws Exception{

        Scanner scan = new Scanner(System.in);

        int choice = 0;

        while (true) {

            printMenu();

            /**
             * 判断控制台接收是否为数字,如果不是数字，输入的数据有误
             */
            if(scan.hasNextInt()){
                choice = scan.nextInt();
                boolean flag = true;
                while (flag){
                    switch (choice) {
                        case 1:
                            if (userView.userLogin()){
                                System.out.println("用户登录成功，系统将在3秒后跳转到中药材管理主页面");

                                //Thread.Sleep()方法用于将当前线程暂停一定时间，时间单位是毫秒，1000毫秒= 1秒

                                Thread.sleep(3000);
                                // 中药材视图
                                medicineVIew.subMenu();
                            }
                            break;
                        case 2:
                            if (userView.addUser()){
                                System.out.println("用户注册成功，系统将在3秒后跳转到登录界面");

                                Thread.sleep(3000);

                                //进入登录界面
                                choice = 1;
                                //跳出当前循环
                                continue;
                            }
                            break;
                        case 3:
                            if (userView.updatePassword()){
                                System.out.println("用户密码修改成功，系统将在3秒后跳转到登录界面");
                                Thread.sleep(3000);
                                choice = 1;
                                continue;
                            }
                            break;
                        case 0:
                            System.out.println("成功退出系统，欢迎下次使用");
                            System.exit(0);
                            break;
                        default:
                            flag = false;
                            System.out.println("输入的数据有误");
                            break;
                    }
                }
            }else {
                System.out.println("输入的数据有误");
                //如果不存在start();则进入死循环
                start();
            }
        }
    }
}