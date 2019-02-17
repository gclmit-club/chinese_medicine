package club.gclmit.mc.dao;

import club.gclmit.mc.entity.User;
import java.util.List;

/**
 * 用户 dao 层 接口
 */
public interface UserDao {

    public boolean insertUser(User user);

    public User selectUserByUsername(String username);

//    public User selectUserByUserId(String userId);

    public List<User> selectAllUser();

    public boolean updateUserByUserId(User user);

//    public boolean deleteUserByUserId(String userId);
//
//    public boolean deleteAllUser(String userId);
}
