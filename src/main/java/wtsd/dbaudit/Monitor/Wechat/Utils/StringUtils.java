package wtsd.dbaudit.Monitor.Wechat.Utils;

import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

public class StringUtils {

    /*
     * 遍历数组，以特定的符号连接
     * @param list
     * @param symbol
     * @return
     */
    public static String connectBySymbol(List<String> list, String symbol){
        StringBuffer sb = new StringBuffer();
        String touser = "";
        if(!CollectionUtils.isEmpty(list)){
            for (String s: list) {
                sb.append(s).append(symbol);
            }
            //去除最后面的symbol
            touser =  sb.substring(0,sb.length() - symbol.length());
        }
        return touser;
    }


    public static void main(String[] args) {
        List<String> list =  new ArrayList<String>();
        list.add("aaa");
        list.add("bbb");
        list.add("ccc");
        System.out.println(connectBySymbol(list, "|"));
    }
}