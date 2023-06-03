package treap;

import java.util.AbstractSet;
import java.util.Collection;
import java.util.Iterator;

import treap.Treap.KeyIterator;

class KeySet extends AbstractSet<K> {
    public Iterator<K> iterator() {
        return new KeyIterator(getFirstNode());
    }

    public boolean add(Object o) {
        throw new UnsupportedOperationException();
    }

    public boolean addAll(Object o) {
        throw new UnsupportedOperationException();
    }

    public boolean contains(Object o) {
        return Treap.this.containsKey(o);
    }

    public void clear() {
        Treap.this.clear();
    }

    public int size() {
        return Treap.this.size();
    }

    public boolean equals(final Object other) {
        if (other == null)
            return false;
        int i = ((Collection<?>) other).size(), j = size();
        return ((Collection<?>) other).containsAll(this) && i == j;
    }

    public boolean remove(Object o) {
        throw new UnsupportedOperationException();
    }
}