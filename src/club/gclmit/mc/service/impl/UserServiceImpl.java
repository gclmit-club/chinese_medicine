package club.gclmit.mc.service.impl;

import club.gclmit.mc.dao.UserDao;
import club.gclmit.mc.dao.impl.UserDaoImpl;
import club.gclmit.mc.entity.User;
import club.gclmit.mc.service.UserService;

import java.util.List;

/**
 *  用户管理 service 接口实现
 */
public class UserServiceImpl implements UserService {

    /**
     * userService不过就是一个指针（正式名称叫引用），它所指的对象是UserServiceImpl的实例
     * 接口本身是不可以直接实例化的，但是他可以声明一个指针变量（或者是函数的形参），指向他的子类的实例。
     */
    private UserDao userDao = new UserDaoImpl();

    /**
     * 注册用户
     * @param user 用户注册信息
     * @return
     */
    @Override
    public boolean createUser(User user) {
        long id = System.currentTimeMillis();
        user.setId(id);
        if (userDao.insertUser(user)){
            return  true;
        }
        return false;
    }

    /**
     * 用户登录
     * @param username 用户名
     * @param password 密码
     * @return
     */
    @Override
    public String userLogin(String username, String password) {
        User user = userDao.selectUserByUsername(username);
        if (user !=null && password.equals(user.getPassword())){
            return  "当前用户登录成功，用户ID: "+user.getId()+"\t账号: "+user.getUsername();
        }
        return "用户登录失败，请检查账号和密码是否输入错误";
    }

    /**
     * 修改密码
     * @param username 账号
     * @param password 用户新密码
     * @param oldPassword 用户旧密码
     * @return
     */
    @Override
    public String updatePassword(String username, String password, String oldPassword) {
        User user = userDao.selectUserByUsername(username);
//        System.out.println(user);
        if (user != null && oldPassword.equals(user.getPassword())){
            user.setPassword(password);
            if (userDao.updateUserByUserId(user)) {
                return  "密码修改成功";
            }
        }
        return "密码修改失败,账号或旧密码输入错误";
    }

    /**
     * 取得所有用户列表
     * @return 所有的用户集合
     */
    @Override
    public List<User> getUsers() {
       return userDao.selectAllUser();
    }
}