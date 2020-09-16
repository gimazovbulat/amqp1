package testing;

import java.util.ArrayList;
import java.util.List;

public class Utils {
    public <T> T[] filter(T[] array, Filter<T> filter) {
        List<T> newList = new ArrayList<>();
        for (T t : array) {
            boolean fits = filter.apply(t);
            if (fits){
                newList.add(t);
            }
        }
        return (T[]) newList.toArray();
    }
}
