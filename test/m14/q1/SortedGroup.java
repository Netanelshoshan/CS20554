/**
 * Name : Netanel Shoshan
 * This class is a generic class that defines collection of sorted items and implements the Comparable collection
 */

import java.util.ArrayList;
import java.util.Iterator;

public class SortedGroup<T extends Comparable<T>> extends ArrayList<T> {

    //Empty constructor
    public SortedGroup() {
        super();
    }

    /* This function adds the given element to the correct location while
     * maintaining sorted order */
    public boolean add(T element) {
        int i = 0;
        for (i = 0; i < this.size(); i++) {
            if (this.get(i).compareTo(element) >= 0) {
                break;
            }
        }
        this.add(i, element);
        return true;
    }


    /* This function removes the element and returns the number of deletions */
    public int remove(T element) {
        int cnt = 0;
        Iterator<T> iterator = this.iterator();
        T compare;
        while (iterator.hasNext()) {
            compare = iterator.next();
            if (compare.equals(element)) {
                iterator.remove();
                cnt++;
            }
        }
        return cnt;
    }

    //The iterator requested.
    public Iterator<T> iterator() {
        return super.iterator();
    }

    @Override
    public String toString() {
        String str = "\n";
        for (int i = 0; i < this.size(); i++) {
            str += (i + 1) + ". " + this.get(i).toString() + ",\n";
        }
        str += "\n";
        return str;
    }
}
