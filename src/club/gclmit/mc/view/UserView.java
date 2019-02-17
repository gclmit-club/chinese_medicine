package club.gclmit.mc.view;

import club.gclmit.mc.entity.User;
import club.gclmit.mc.service.UserService;
import club.gclmit.mc.service.impl.UserServiceImpl;
import club.gclmit.mc.util.ValidationData;
import club.gclmit.mc.util.VerificationCode;

import java.util.Scanner;

/**
 * 用户视图
 */
public class UserView {

    /**
     * 疯狂java P149
     * 多态
     * 接口UserService的子类UserServiceImpl在构造函数里要填一些东西，这个东西就是子类UserServiceImpl所定义的可执行代码块的入口
     */
    private UserService userService = new UserServiceImpl();

    /**
     * 声明私有的
     */
    private ValidationData validationData = new ValidationData();

    /**
     * 用户登录视图
     */
    public  boolean userLogin(){
        boolean flag = true ;

        while (flag) {

            VerificationCode verificationCode = new VerificationCode();
            int  vcode = verificationCode.getCode();

//            System.out.println(vcode);
            System.out.println("==================================");
            System.out.println("欢迎使用中药材管理系统 - 用户登录");
            Scanner scan = new Scanner(System.in);
            System.out.print("请输入账号: ");
            String username = scan.next();
            System.out.print("请输入密码: ");
            String password = scan.next();
            System.out.println("请输入验证码: " + vcode);

            if(scan.hasNextInt()){

                int code = scan.nextInt();

                if (code == vcode) {

                    if (validationData.validationUsernameAndPassword(username,password)){
                        String s = userService.userLogin(username, password);
                        if (!"用户登录失败，请检查账号和密码是否输入错误".equals(s)){
                            System.out.println(s);
                            return true;
                        }
                        System.out.println(s);
                    }
                } else {
                    System.out.println("验证码输入错误，请重新输入");
                }
            }else{
                System.out.println("验证码输入错误，请重新输入");
            }
        }
        return  false;
    }

    /**
     * 添加用户
     */
    public boolean  addUser() {
        boolean flag = true ;
        while (flag){
            System.out.println("欢迎使用中药材管理系统 - 用户注册");

            Scanner scan = new Scanner(System.in);
            System.out.print("请输入 2 - 8 位账号：");
            String username = scan.next();
            System.out.print("请输入 6 - 16 位密码：");
            String password1 = scan.next();
            System.out.print("请重复输入 6 - 16 位密码：");
            String password2 = scan.next();

            if(password1.equals(password2)){
                if (validationData.validationUsernameAndPassword(username,password1)){
                    boolean judgeUser = userService.createUser(new User(username, password1));
                    if (judgeUser){
                        flag = false ;
                        return  true;
                    }
                }
            } else {
                System.out.println("前后两次密码输入不一致，请重新输入");
            }
        }
        return  false;
    }

    /**
     * 修改密码
     */
    public boolean  updatePassword() {
        boolean flag = true ;
        while (flag){
            System.out.println("欢迎使用中药材管理系统 - 修改密码");

            Scanner scan = new Scanner(System.in);
            System.out.print("请输入账号：");
            String username = scan.next();
            System.out.print("请输入旧密码：");
            String oldPassword = scan.next();
            System.out.print("请输入 6 - 16 位的新密码：");
            String password1 = scan.next();
            System.out.print("请重复输入6 - 16 位的新密码：");
            String password2 = scan.next();

            if(password1.equals(password2)){
                if (validationData.validationUsernameAndPassword(username,password1)){
                    String s = userService.updatePassword(username, password1, oldPassword);
                    System.out.println(s);
                    return  true;
                }
            } else {
                System.out.println("前后两次新密码输入不一致，请重新输入");
            }
        }
        return  false;
    }

}