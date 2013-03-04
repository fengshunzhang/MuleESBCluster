package com.github.fengshunzhang.utils;

import com.hazelcast.config.Config;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;

public class HazelcastUtil {
	private static HazelcastInstance instance;

	public synchronized static HazelcastInstance getInstance() {
		if (instance == null) {
			Config config = new Config();
			//when node is down,the other node constantly knows that
			config.setProperty("hazelcast.connection.monitor.max.faults", "0");
			config.setProperty("hazelcast.logging.type", "log4j");
			instance = Hazelcast.newHazelcastInstance(config);
		}
		return instance;
	}
}
