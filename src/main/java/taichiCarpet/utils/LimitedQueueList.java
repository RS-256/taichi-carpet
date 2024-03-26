package taichiCarpet.utils;

import org.jetbrains.annotations.NotNull;

import java.util.*;

public class LimitedQueueList<V> implements List<V> {

    private final int maxSize;
    private final List<V> list;

    public LimitedQueueList(int maxSize) {
        if (maxSize <= 0) {
            throw new IllegalArgumentException("maxSize must be greater than zero");
        }
        this.maxSize = maxSize;
        this.list = new ArrayList<V>();
    }

    @Override
    public int size() {
        return list.size();
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return list.contains(o);
    }

    @NotNull
    @Override
    public Iterator<V> iterator() {
        return list.iterator();
    }

    @NotNull
    @Override
    public Object[] toArray() {
        return list.toArray();
    }

    @NotNull
    @Override
    public <T> T[] toArray(@NotNull T[] a) {
        return list.toArray(a);
    }

    @Override
    public boolean add(V v) {
        boolean result = list.add(v);
        while (true) {
            if (list.size() >= maxSize) {
                list.remove(0);
            } else {
                break;
            }
        }
        return result;
    }

    @Override
    public boolean remove(Object o) {
        return list.remove(o);
    }

    @Override
    public boolean containsAll(@NotNull Collection<?> c) {
        return list.containsAll(c);
    }

    @Override
    public boolean addAll(@NotNull Collection<? extends V> c) {
        boolean result = list.addAll(c);
        while (true) {
            if (list.size() >= maxSize) {
                list.remove(0);
            } else {
                break;
            }
        }
        return result;
    }

    @Override
    public boolean addAll(int index, @NotNull Collection<? extends V> c) {
        boolean result = list.addAll(index, c);
        while (true) {
            if (list.size() >= maxSize) {
                list.remove(0);
            } else {
                break;
            }
        }
        return result;
    }

    @Override
    public boolean removeAll(@NotNull Collection<?> c) {
        return list.removeAll(c);
    }

    @Override
    public boolean retainAll(@NotNull Collection<?> c) {
        return list.retainAll(c);
    }

    @Override
    public void clear() {
        list.clear();
    }

    @Override
    public V get(int index) {
        return list.get(index);
    }

    @Override
    public V set(int index, V element) {
        V result = list.set(index, element);
        while (true) {
            if (list.size() >= maxSize) {
                list.remove(0);
            } else {
                break;
            }
        }
        return result;
    }

    @Override
    public void add(int index, V element) {
        list.add(index, element);
        while (true) {
            if (list.size() >= maxSize) {
                list.remove(0);
            } else {
                break;
            }
        }
    }

    @Override
    public V remove(int index) {
        return list.remove(index);
    }

    @Override
    public int indexOf(Object o) {
        return list.indexOf(o);
    }

    @Override
    public int lastIndexOf(Object o) {
        return list.lastIndexOf(o);
    }

    @NotNull
    @Override
    public ListIterator<V> listIterator() {
        return list.listIterator();
    }

    @NotNull
    @Override
    public ListIterator<V> listIterator(int index) {
        return list.listIterator(index);
    }

    @NotNull
    @Override
    public List<V> subList(int fromIndex, int toIndex) {
        return list.subList(fromIndex, toIndex);
    }
}
