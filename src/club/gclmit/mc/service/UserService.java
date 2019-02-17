package club.gclmit.mc.service;

import club.gclmit.mc.entity.User;

import java.util.List;

/**
 *  用户管理 service 接口
 */
public interface UserService {

    /**
     * 注册用户接口
     * @param user 用户
     * @return
     */
    public boolean createUser(User user);

    /**
     * 用户登录接口
     * @param username
     * @param password
     * @return
     */
    public String userLogin(String username,String password);

    /**
     * 修改密码（更新）接口
     * @param usernaem
     * @param password
     * @param oldPassword
     * @return
     */
    public String  updatePassword(String usernaem,String password,String oldPassword);

    /**
     *所有用户集合接口
     * @return
     */
    public List<User> getUsers();

}
