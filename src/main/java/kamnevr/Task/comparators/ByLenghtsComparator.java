package kamnevr.Task.comparators;

import java.util.Comparator;

/**
 * Created by rkamnev on 28.05.2017.
 */
public class ByLenghtsComparator implements Comparator<String> {
    @Override
    public int compare(String o1, String o2) {
        Integer lenght1 = (Integer) o1.length();
        Integer lenght2 = (Integer) o2.length();
        return lenght1.compareTo(lenght2);
    }
}
