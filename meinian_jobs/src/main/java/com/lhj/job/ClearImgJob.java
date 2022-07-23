package com.lhj.job;

import com.lhj.constants.RedisConstant;
import com.lhj.utils.QiniuUtils;
import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.jedis.JedisPool;

import java.util.Iterator;
import java.util.Set;

/**
 * @author lhj
 * @creat 2022-03-28-21:05
 */
public class ClearImgJob {
    @Autowired
    private JedisPool jedisPool;

    public void clearImg(){
        Set<String> sdiff = jedisPool.getResource().sdiff(RedisConstant.SETMEAL_PIC_RESOURCES, RedisConstant.SETMEAL_PIC_DB_RESOURCES);
        Iterator<String> iterator = sdiff.iterator();
        if (iterator.hasNext()) {
            String pic = iterator.next();
            System.out.println("删除图片的名称是：" + pic);
            QiniuUtils.deleteFileFromQiniu(pic);
            jedisPool.getResource().srem(RedisConstant.SETMEAL_PIC_RESOURCES,pic);
        }
    }
}
