package gb.lesson2;

import java.util.Iterator;

public interface List<E> extends Iterator {
    int size();
    boolean isEmpti();
    boolean add(E val);
    boolean remove(E val);
    boolean contains(E val);


}
