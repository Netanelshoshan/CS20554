/**
 * M14 q1b.
 * This class contains the reduce generic method that was required in the question.
 */
public class Other {

    public static <T extends Comparable<T>> SortedGroup<T> reduce(SortedGroup<T> sGroup, T x) {
        SortedGroup<T> newSortedGroup = new SortedGroup<T>();
        int i = 0;
        while (i < sGroup.size()) {
            if (sGroup.get(i).compareTo(x) > 0)
                break;
            i++;
        }
        while (i < sGroup.size()) {
            newSortedGroup.add(sGroup.get(i));
            i++;
        }
        return newSortedGroup;
    }
}
