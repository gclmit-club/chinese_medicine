package club.gclmit.mc.entity;

/**
 * 用户实体类
 */
public class User {

    /**
     *  用户ID
     */
    private long id;

    /**
     *  账号
     */
    private String username;

    /**
     *  密码
     */
    private String password;

    @Override
    public String toString() {
        String userToString = "%s,%s,%s\n";
        return  String.format(userToString,id,username,password);
    }

    public User() {
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public User(long id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }



    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
