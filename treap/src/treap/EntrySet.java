package treap;

import java.util.AbstractSet;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

import treap.Treap.EntryIterator;

class EntrySet extends AbstractSet<Map.Entry<K, V>> {
        public Iterator<java.util.Map.Entry<K, V>> iterator() {
            return new EntryIterator(getFirstNode());
        }

        public boolean add(Map.Entry<K, V> o) {
            throw new UnsupportedOperationException();
        }

        public boolean addAll(Collection<? extends Map.Entry<K, V>> c) {
            throw new UnsupportedOperationException();
        }

        public void clear() {
            Treap.this.clear();
        }

        public int size() {
            return Treap.this.size();
        }

        public boolean contains(Object o) {
            if (!(o instanceof Map.Entry))
                return false;
            @SuppressWarnings("unchecked")
            Map.Entry<K, V> entry = (Map.Entry<K, V>) o;
            V value = entry.getValue();
            TreapNode<K, V> p = getNode(entry.getKey());
            return p != null && valEquals(p.getValue(), value);
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