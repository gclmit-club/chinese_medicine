package club.gclmit.mc.dao.impl;

import club.gclmit.mc.dao.UserDao;
import club.gclmit.mc.entity.User;
import club.gclmit.mc.io.IOHandler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

/**
 *   用户 dao 层 实现类
 */
public class UserDaoImpl implements UserDao {

    /**
     * 声明一个IOHandler（泛型Medicine）的对象ioHandler
     */
    private IOHandler<User> ioHandler = new IOHandler<User>();

    /**
     * 声明一个静态字符常量代表文件名  统一资源定位
     */
    public static final String USER_DATA_FILE_URL = "resources/user.txt";

    /**
     *  从 文本中写入 数据
     * @param user 用户对象
     * @return
     */
    @Override
    public boolean insertUser(User user) {
        try {
            boolean flag = false ;

            /**
             * 声明一个名为stringList的字符串泛型
             */
            List<String> stringList = ioHandler.select(USER_DATA_FILE_URL);

            /**
             * 遍历数组，声明username，将用户名分割出来，如存在该用户名 返true；进入if ：返回false，已注册
             */
            for (String userToString : stringList) {
                String username = userToString.split(",")[1];
                if (username.equals(user.getUsername())) {
                    flag = true;
                }
            }
            if (flag){
                System.out.println("该账号已被注册");
                return  false;
            }
            ioHandler.save(user,USER_DATA_FILE_URL);
            return  true;
        } catch (IOException e) {
            System.out.println("创建对象失败");
            e.printStackTrace();
        }
        return false;
    }

    /**
     *  根据账号查找用户
     * @param username 账号
     * @return
     */
    @Override
    public User selectUserByUsername(String username){
        try {
            List<String> stringList = ioHandler.select(USER_DATA_FILE_URL);
            for (String userToString : stringList) {

                /**
                 * 将输入的用户名与经分隔符分好的用户名集合对比
                 */
                String Susername = userToString.split(",")[1];
                if (Susername.equals(username)) {
                    return stringToUser(userToString);
                }
            }
        } catch (IOException e){
            System.out.println("该用户不存在,无法通过账号"+username+"查找到该用户");
            e.printStackTrace();
        }
        return  null;
    }

    /**
     *  根据用户 id 查找用户
     * @param userId 账号
     * @return
     */
//    @Override
//    public User selectUserByUserId(String userId) {
//        try {
//            List<String> stringList = ioHandler.select(USER_DATA_FILE_URL);
//            for (String userToString : stringList){
//
//                /**
//                 * 查找指定字符或字符串在字符串中第一次出现地方的索引，未找到的情况返回 -1.
//                 */
//                if (userToString.split(",")[0].equals(userId)) {
////                    System.out.println("查询到该数据"+userToString);
//                    return stringToUser(userToString);
//                }
//            }
//        } catch (IOException e) {
//            System.out.println("该用户不存在,无法通过用户ID"+userId+"查找到该用户");
//            e.printStackTrace();
//        }
//        return null;
//    }

    /**
     *  查询所有用户
     * @return
     */
    @Override
    public List<User> selectAllUser() {
        List<User> users = new ArrayList<>();
        long id = System.currentTimeMillis();
        try {
            List<String> stringList = ioHandler.select(USER_DATA_FILE_URL);
            return stringToUserAll(stringList);
        } catch (IOException e) {
            System.out.println("查询所有用户失败");
            e.printStackTrace();
        }
        return null;
    }

    /**
     *  根据 user 更新文本
     * @param user
     * @return
     */
    @Override
    public boolean updateUserByUserId(User user) {

        try {
            List<String> stringList = ioHandler.select(USER_DATA_FILE_URL);

            int listSize = stringList.size();

            /**
             *
             */
            ListIterator<String> iterator = stringList.listIterator();
            while (iterator.hasNext()) {
                String userToString = iterator.next();

                /**
                 *
                 */
                if (userToString.split(",")[0].equals(user.getId()+"")) {
//                    System.out.println("修改数据为："+userToString);
                    iterator.remove();
                }
            }

            if (listSize > stringList.size()){

                ioHandler.update(stringList,USER_DATA_FILE_URL);

                /**
                 * 插入修改后的用户数据
                 */
                if (insertUser(user)){
                    return true;
                }
            }
            return  false;

        } catch (IOException e) {
            System.out.println("更新用户失败");
            e.printStackTrace();
        }
        return false;
    }

    /**
     *  根据 用户id 删除用户
     * @param userId
     * @return
     */
//    @Override
//    public boolean deleteUserByUserId(String userId) {
//        try {
//            List<String> stringList = ioHandler.select(USER_DATA_FILE_URL);
//            ListIterator<String> iterator = stringList.listIterator();
//            while (iterator.hasNext()) {
//                String userToString = iterator.next();
//                if (userToString.indexOf(userId) >= 0) {
////                    System.out.println("删除数据："+userToString);
//                    iterator.remove();
//                }
//            }
//            ioHandler.update(stringList,USER_DATA_FILE_URL);
//            return  true;
//        } catch (IOException e) {
//            System.out.println("通过userId："+userId+"删除用户失败");
//            e.printStackTrace();
//        }
//        return false;
//    }

    /**
     *  删除所有用户,传入的用户 id  是为了自己不删除自己
     * @param userId 用户 id
     * @return
     */
//    @Override
//    public boolean deleteAllUser(String userId){
//        try {
//            List<String> stringList = ioHandler.select(USER_DATA_FILE_URL);
//            ListIterator<String> iterator = stringList.listIterator();
//            while (iterator.hasNext()) {
//                String userToString = iterator.next();
//                if (userToString.indexOf(userId) == -1) {
////                    System.out.println("删除数据："+userToString);
//                    iterator.remove();
//                }
//            }
//            ioHandler.update(stringList,USER_DATA_FILE_URL);
//            return  true;
//        } catch (IOException e) {
//            System.out.println("全部用户删除失败");
//            e.printStackTrace();
//        }
//        return false;
//    }

    /**
     *  对象字符串批量转换成 对象
     * @param stringList 对象字符串集合
     * @return
     */
    public List<User> stringToUserAll(List<String> stringList){
        List<User> users = new ArrayList<>();
        stringList.forEach(stringToUser ->{ users.add(stringToUser(stringToUser)); });
        return  users;
    }
    /**
     * 对象字符串 转换成 对象
     * @param userToString 对象字符串
     * @return
     */
    public User stringToUser(String userToString){
        String id = userToString.split(",")[0];
        if(id != null && !id.equals("") && !id.equals("null")){
            long userId = Long.parseLong(id);
            String name  =  userToString.split(",")[1];
            String  password  =  userToString.split(",")[2];
            return  new User(userId,name,password);
        }
        return  null;
    }
}
