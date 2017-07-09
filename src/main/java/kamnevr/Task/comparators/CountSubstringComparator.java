package kamnevr.Task.comparators;

import kamnevr.Task.General;
import kamnevr.Task.utils.CounterString;

import java.util.Comparator;

/**
 * Created by rkamnev on 28.05.2017.
 */
public class CountSubstringComparator implements Comparator<String> {
    @Override
    public int compare(String o1, String o2) {
        Integer int1 = CounterString.counterString(o1, "Отдел");
        Integer int2 = CounterString.counterString(o2, "Отдел");
        return int1.compareTo(int2);
    }
}
