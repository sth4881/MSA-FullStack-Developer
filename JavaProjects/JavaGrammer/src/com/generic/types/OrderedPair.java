package com.generic.types;

public class OrderedPair<K, V> implements Pair<K, V> {
	private K key;
	private V value;
	public OrderedPair(K key, V value) {
		this.key = key;
		this.value = value;
	}
	@Override
	public K getKey() {
		return null;
	}
	@Override
	public V getValue() {
		return null;
	}
}
