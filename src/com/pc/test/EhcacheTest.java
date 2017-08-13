package com.pc.test;

import com.pc.model.Tuser;
import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;


public class EhcacheTest {
    public static void main(String[] args) {
        CacheManager cacheManager = CacheManager.create("classpath:ehcache.xml");
        Cache  user;
        user=cacheManager.getCache("loginCache");
        System.out.println();
    }
}
