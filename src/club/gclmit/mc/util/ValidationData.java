package club.gclmit.mc.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 输入的  用户名  密码  是否合法
 */
public class ValidationData {


    /**
     * 输入的medicineId是否是纯数字
     * @param medicineId
     * @return
     */
    public boolean isNumeric(String medicineId){
        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(medicineId);
        if( !isNum.matches() ){
            return false;
        }
        return true;
    }

    /**
     * 根据用户名长度验证是否合法
     * @param username 用户名
     * @return
     */
    public String validationUsername(String username){
        if (username.length() >=2 && username.length() <=8){
            return "success";
        }
        return "账号输入不合法，请重新输入 2 - 8 位的账号";
    }

    /**
     *，根据密码长度验证是否合法：
     * @param password 密码
     * @return
     */
    public String validationPassword(String password){
        if (password.length() >=6 && password.length() <=16){
            return "success";
        }
        return "密码输入不合法，请重新输入 6 - 16 位的密码";
    }

    /**
     * 账号密码效验
     * @param username
     * @param password
     * @return
     */
    public boolean validationUsernameAndPassword(String username,String password){

        /**
         * 定义用户名长度的验证消息，传入参数username，验证用户名是否合法
         */
        String usernameValidationMessage = validationUsername(username);

        /**
         * 定义密码长度的验证消息，传入参数password，验证密码是否合法
         */
        String passwordValidationMessage = validationPassword(password);

        /**
         * 进行判断分三种情况：1，账号长度不符合要求 2，密码长度不符合要求 3，均不符合要求
         */
        if (usernameValidationMessage.equals(passwordValidationMessage)){
            return  true;
        } else {
            if("success".equals(usernameValidationMessage)){
                System.out.println(passwordValidationMessage);
            }else if ("success".equals(passwordValidationMessage)){
                System.out.println(usernameValidationMessage);
            } else {

                /**
                 * 向StringBuffer对象增加其他内容:介绍了用append()方法追加子串的功能。
                 */
                System.out.println(new StringBuilder().append(usernameValidationMessage).append("/").append(passwordValidationMessage).toString());
            }

            /**
             *用户 登录 或 注册将失败
             */
            return false;

        }
    }
}