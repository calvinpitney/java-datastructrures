package treap;

import java.util.Comparator;
import java.util.Map;

import org.w3c.dom.Element;
import org.w3c.dom.Node;



public class TreapNode<K, V> implements Map.Entry<K, V> {
    public K key;
    public V value;
    public int priority;
    public TreapNode<K, V> left = null;
    public TreapNode<K, V> right = null;
    public TreapNode<K, V> parent = null;
    Comparator<? super K> comparator;
    
    TreapNode(K key, V value, Comparator<? super K> comp) {
        this.key = key;
        this.value = value;
        this.parent = null;
        this.comparator = comp;
        priority = Treap.rand.nextInt();
    }

    public V add(TreapNode<K, V> node) {
        int cmp = compare(node.key, this.key);
        if (cmp < 0) {
            if (left == null) {
                left = node;
                left.parent = this;
                return null;
            } else {
                V ret = this.left.add(node);
                return ret;
            }
        } else if (cmp > 0) {
            if (right == null) {
                right = node;
                right.parent = this;
                return null;
            } else {
                V ret = this.right.add(node);
                return ret;
            }
        } else {
            return this.setValue(node.value);
        }
    }

    public int hashCode() {
        int keyHash = (key == null ? 0 : key.hashCode());
        int valueHash = (value == null ? 0 : value.hashCode());
        return keyHash ^ valueHash;
    }

    public boolean equals(Object o) {
        if (!(o instanceof Map.Entry))
            return false;
        Map.Entry<?, ?> e = (Map.Entry<?, ?>) o;

        return valEquals(key, e.getKey()) && valEquals(value, e.getValue());
    }

    public String toString() {
        return key + "=" + value;
    }

    public K getKey() {
        return key;
    }

    public V getValue() {
        return value;
    }

    public V setValue(V value) {
        V oldValue = this.value;
        this.value = value;
        return oldValue;
    }

    public boolean isLeaf() {
        return left == null && right == null;
    }

    @SuppressWarnings({ "unchecked" })
    private int compare(Object k1, Object k2) {
        return comparator == null ? ((Comparable<? super K>) k1)
                .compareTo((K) k2) : comparator.compare((K) k1, (K) k2);
    }

    public Node buildXmlNode(final Node parent) {
        final Element e = parent.getOwnerDocument().createElement("node");
        e.setAttribute("key", key.toString());
        e.setAttribute("value", value.toString());
        e.setAttribute("priority", Integer.toString(priority));

        if (left != null) {
            e.appendChild(left.buildXmlNode(e));
        } else {
            e.appendChild(e.getOwnerDocument().createElement("emptyChild"));
        }

        if (right != null) {
            e.appendChild(right.buildXmlNode(e));
        } else {
            e.appendChild(e.getOwnerDocument().createElement("emptyChild"));
        }
        return e;
    }
    
}