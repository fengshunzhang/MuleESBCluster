package com.github.fengshunzhang.store;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.mule.api.store.ObjectStoreException;
import org.mule.api.store.QueueStore;

import com.github.fengshunzhang.utils.HazelcastUtil;

public class CustomQueueStore implements QueueStore<Serializable> {
	private String queue;
	private Map<Serializable, Serializable> shareMap = null;

	public void open() throws ObjectStoreException {
		if (shareMap == null) {
			shareMap = HazelcastUtil.getInstance().getMap(queue);

		}

	}

	public void close() throws ObjectStoreException {
		// to do nothing

	}

	public List<Serializable> allKeys() throws ObjectStoreException {
		List<Serializable> keys = new ArrayList<Serializable>();
		for (Serializable key : shareMap.keySet()) {
			keys.add(key);
		}
		return keys;
	}

	public boolean contains(Serializable key) throws ObjectStoreException {

		return shareMap.containsKey(key);
	}

	public boolean isPersistent() {

		return false;
	}

	public void store(Serializable key, Serializable value)
			throws ObjectStoreException {
		shareMap.put(key, value);

	}

	public Serializable retrieve(Serializable key) throws ObjectStoreException {

		return shareMap.get(key);
	}

	public Serializable remove(Serializable key) throws ObjectStoreException {

		return shareMap.remove(key);
	}

	public void setQueue(String queue) {
		this.queue = queue;
	}

}
