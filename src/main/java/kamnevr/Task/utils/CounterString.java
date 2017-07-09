package kamnevr.Task.utils;

/**
 * Created by rkamnev on 30.05.2017.
 */
public class CounterString {
    public static int counterString(String s, String subString) {
        int count = 0;
        int index = s.indexOf(subString, 0);
        while (index > 0) {
            index = s.indexOf(subString, index + 1);
            ++count;
        }
        return count;
    }
}
