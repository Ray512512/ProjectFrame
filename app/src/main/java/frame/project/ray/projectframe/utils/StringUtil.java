package frame.project.ray.projectframe.utils;

import java.util.List;

/**
 * Created by Ray on 2017/5/5.
 * email：1452011874@qq.com
 */

public class StringUtil {

    /**
     * 判断String是否为空
     **/
    public static boolean isEmpty(String string) {
        return string == null || string.length() == 0 || string.equals("null");
    }

    public static String listToString(List list, String  separator) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            sb.append(list.get(i)).append(separator);
        }
        return sb.toString().substring(0,sb.toString().length()-1);}
}
