import java.util.*;

class HashTable<K, V> {
    private static final int DEFAULT_CAPACITY = 16;
    private ArrayList<LinkedList<Entry<K, V>>> table;
    private int capacity;

    public HashTable() {
        this(DEFAULT_CAPACITY);
    }

    public HashTable(int capacity) {
        this.capacity = capacity;
        this.table = new ArrayList<>(capacity);
        for (int i = 0; i < capacity; i++) {
            table.add(new LinkedList<>());
        }
    }

    public void put(K key, V value) {
        int hash = calculateHash(key);
        LinkedList<Entry<K, V>> bucket = table.get(hash);
        for (Entry<K, V> entry : bucket) {
            if (entry.getKey().equals(key)) {
                entry.setValue(value);
                return;
            }
        }
        bucket.add(new Entry<>(key, value));
    }

    public V get(K key) {
        int hash = calculateHash(key);
        LinkedList<Entry<K, V>> bucket = table.get(hash);
        for (Entry<K, V> entry : bucket) {
            if (entry.getKey().equals(key)) {
                return entry.getValue();
            }
        }
        return null;
    }

    private int calculateHash(K key) {
        int hashCode = key.hashCode();
        return Math.abs(hashCode) % capacity;
    }

    private static class Entry<K, V> {
        private K key;
        private V value;

        public Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }

        public void setValue(V value) {
            this.value = value;
        }
    }
}

public class HashTableExample {
    public static void main(String[] args) {
        HashTable<String, Integer> hashTable = new HashTable<>();

        // Insert key-value pairs
        hashTable.put("apple", 10);
        hashTable.put("banana", 20);
        hashTable.put("cherry", 30);

        // Retrieve values based on keys
        System.out.println("Value for key 'apple': " + hashTable.get("apple"));     // Output: 10
        System.out.println("Value for key 'banana': " + hashTable.get("banana"));   // Output: 20
        System.out.println("Value for key 'cherry': " + hashTable.get("cherry"));   // Output: 30

        // Try to retrieve a value for a key that doesn't exist
        System.out.println("Value for key 'grape': " + hashTable.get("grape"));     // Output: null
    }
}
