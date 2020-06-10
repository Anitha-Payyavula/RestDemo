package com.anitha.util;

import org.redisson.Redisson;
import org.redisson.api.RMapCache;
import org.redisson.api.RedissonClient;

import com.anitha.model.Email;

public class RedisCache {
	public static  RMapCache<Integer, Email> redisMap() {
		RedissonClient redisson = Redisson.create();
	        RMapCache<Integer, Email> map = redisson.getMapCache("anyMap");
	        return map;
	}

}
