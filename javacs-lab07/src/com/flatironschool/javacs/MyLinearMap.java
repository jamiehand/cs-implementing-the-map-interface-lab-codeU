/**
 *
 */
package com.flatironschool.javacs;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Implementation of a Map using a List of entries, so most
 * operations are linear time.
 *
 * @author downey
 * @param <K>
 * @param <V>
 *
 */
public class MyLinearMap<K, V> implements Map<K, V> {

	private List<Entry> entries = new ArrayList<Entry>();

	public class Entry implements Map.Entry<K, V> {
		private K key;
		private V value;

		public Entry(K key, V value) {
			this.key = key;
			this.value = value;
		}

		@Override
		public K getKey() {
			return key;
		}
		@Override
		public V getValue() {
			return value;
		}
		@Override
		public V setValue(V newValue) {
			value = newValue;
			return value;
		}
	}

	@Override
	public void clear() {
		entries.clear();
	}

	@Override
	public boolean containsKey(Object target) {
		return findEntry(target) != null;
	}

	/**
	 * Returns the entry that contains the target key, or null if there is none.
	 *
	 * @param target
	 */
	private Entry findEntry(Object target) {
        // TODO: fill this in
		for (Entry entry : entries) {
				if (equals(entry.getKey(), target)) {
					return entry;
				}
		}
		return null;
	}

	/**
	 * Compares two keys or two values, handling null correctly.
	 *
	 * @param target
	 * @param obj
	 * @return
	 */
	private boolean equals(Object target, Object obj) {
		if (target == null) {
			return obj == null;
		}
		return target.equals(obj);
	}

	@Override
	public boolean containsValue(Object target) {
		for (Map.Entry<K, V> entry: entries) {
			if (equals(target, entry.getValue())) {
				return true;
			}
		}
		return false;
	}

	@Override
	public Set<Map.Entry<K, V>> entrySet() {
		throw new UnsupportedOperationException();
	}

	/**
	 * Returns the value of the entry that contains key, or null if there is none.
	 *
	 * @param key
	 */
	@Override
	public V get(Object key) {
        // TODO: fill this in.
		Entry entry = findEntry(key);
		if (entry == null) {
			return null;
		} else {
			return entry.getValue();
		}
	}

	@Override
	public boolean isEmpty() {
		return entries.isEmpty();
	}

	@Override
	public Set<K> keySet() {
		Set<K> set = new HashSet<K>();
		for (Entry entry: entries) {
			set.add(entry.getKey());
		}
		return set;
	}

	@Override
	public V put(K key, V value) {
        // TODO: fill this in.
				Entry prevEntry = findEntry(key);

				/* add entry if there isn't a previous entry with this key;
				 * otherwise change the entry */
				if (prevEntry == null) {
					Entry entry = new Entry(key, value);
					entries.add(entry);
					return null;
				} else {
					V prevValue = prevEntry.getValue();
					prevEntry.setValue(value);
					return prevValue;
				}
	}

	@Override
	public void putAll(Map<? extends K, ? extends V> map) {
		for (Map.Entry<? extends K, ? extends V> entry: map.entrySet()) {
			put(entry.getKey(), entry.getValue());
		}
	}

	/**
	 * Removes the mapping for key from map, if present.
	 * Returns value to which map previously associated key,
	 * or null if map contained no mapping for key.
	 *
	 * @param key
	 */
	@Override
	public V remove(Object key) {
        // TODO: fill this in.
				Entry prevEntry = findEntry(key);

				/* remove entry iff there is a previous entry with this key */
				if (prevEntry == null) {
					return null;
				} else {
					V prevValue = prevEntry.getValue();
					entries.remove(prevEntry);
					return prevValue;
				}
	}

	@Override
	public int size() {
		return entries.size();
	}

	@Override
	public Collection<V> values() {
		Set<V> set = new HashSet<V>();
		for (Entry entry: entries) {
			set.add(entry.getValue());
		}
		return set;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Map<String, Integer> map = new MyLinearMap<String, Integer>();
		map.put("Word1", 1);
		map.put("Word2", 2);
		Integer value = map.get("Word1");
		System.out.println(value);

		for (String key: map.keySet()) {
			System.out.println(key + ", " + map.get(key));
		}
	}

	/**
	 * Returns a reference to `entries`.
	 *
	 * This is not part of the Map interface; it is here to provide the functionality
	 * of `entrySet` in a way that is substantially simpler than the "right" way.
	 *
	 * @return
	 */
	protected Collection<? extends java.util.Map.Entry<K, V>> getEntries() {
		return entries;
	}
}
