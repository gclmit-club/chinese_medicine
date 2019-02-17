package club.gclmit.mc.test;

import club.gclmit.mc.util.VerificationCode;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LIstTest {

    public static void main(String[] args) {
//            List<String> arrayList = new ArrayList<>();
//            arrayList.add("1548247769297,admin,123456");
//            arrayList.add("1548247888177,admin0,123456");
//            arrayList.add("1548247888178,admin1,123456");
//
//            arrayList.forEach(s1 ->{
//                System.out.println("原数据: "+s1);
//            });
//
//            ListIterator<String> iterator = arrayList.listIterator();
//            while (iterator.hasNext()) {
//                String next = iterator.next();
//                if (next.indexOf("admin1") >= 0) {
//                    System.out.println("去除数据"+next);
//                    iterator.remove();
//                }
//            }
//
//            arrayList.forEach(s ->{
//                System.out.println("1====="+s);
//            });

//        VerificationCode verificationCode = new VerificationCode();
//        Integer vcode = verificationCode.getCode();
//        System.out.println(vcode);
        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher("1548351378546");
        System.out.println(isNum.matches());
//        if( !isNum.matches() ){
//
//            return false;
//        }
    }

}
