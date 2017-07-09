package kamnevr.Task.utils;

import kamnevr.Task.model.Department;
import kamnevr.Task.model.Employee;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Created by rkamnev on 29.05.2017.
 */
public class SortStringByCountOfDep {
    private static Employee[] array;

    public static List<Employee> sortList(List<Employee> list) {
        array = new Employee[list.size()];
        array = list.toArray(array);
        sortArray(0, array.length - 1);

        List<Employee> finalList = new ArrayList<>(Arrays.asList(array));
        return finalList;
    }

    public static void sortArray(int start, int end) {
        if (start >= end) /* Если передан пустой массив - выходим из цикла */
            return;
        int i = start, j = end;
        int cur = i - (i - j) / 2;
        while (i < j) {
            while (i < cur && (array[i].getCountOfAvaliableDepartments() <= array[cur].getCountOfAvaliableDepartments())) {
                i++;  /*    Пока позиция левого курсора слева от позиции опорного элемента и значение элемента на которое указывает курсор
                              меньше значения опорного элемента  - смещаем позицию курсора влево на один пункт  */
            }
            while (j > cur && (array[cur].getCountOfAvaliableDepartments() <= array[j].getCountOfAvaliableDepartments())) {
                j--;  /*   Пока позиция правого курсора справа от позиции опорного элемента и значение элемента на которое указывает курсор
                             больше значения опорного элемента  - смещаем позицию курсора вправо на один пункт  */
            }
            if (i < j) { /*    Меняем между собой элементы на которых остановились курсоры i и j  */
                Employee emp = array[i];
                array[i] = array[j];
                array[j] =  emp;
                if (i == cur) /*    Если i = cur, курсор смещается на позицию j  */
                    cur = j;
                else if (j == cur) /*    Если j = cur, курсор смещается на позицию i  */
                    cur = i;
            }
        }
        sortArray(start, cur);
        sortArray(cur + 1, end);
    }
}
