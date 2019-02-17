package club.gclmit.mc.util;

/**
 * 任何情况下都不要在多个线程间共享一个Random实例，而该把它放入ThreadLocal之中
 * java7在所有情形下都更推荐使用ThreadLocalRandom，它向下兼容已有的代码且运营成本更低
 */
import java.util.concurrent.ThreadLocalRandom;

/**
 *  生成验证码的工具类
 */
public class VerificationCode {

    public static  final  int SIZE = 6;

    /**
     * 生成6位随机验证码
     * @return
     */
    public Integer getCode(){

        /**
         * Integer有缓存机制,比int多两个类
         */
        Integer min = (int) Math.pow(10, SIZE-1);
        ThreadLocalRandom localRandom = ThreadLocalRandom.current();
        return localRandom.nextInt(min , min * 10);
    }
}
