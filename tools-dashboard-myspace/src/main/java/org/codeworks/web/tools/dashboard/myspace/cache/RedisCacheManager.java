package org.codeworks.web.tools.dashboard.myspace.cache;

import org.apache.commons.lang3.SerializationUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.Pipeline;

import java.io.IOException;
import java.io.Serializable;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * Redis管理类
 */
@Component
public class RedisCacheManager {

    private final JedisPool jedisPool;

    public RedisCacheManager(JedisPool jedisPool) {
        this.jedisPool = jedisPool;
    }

    public Queue getCache(){
        return new Queue(jedisPool.getResource());
    }

    public Queue getCache(String namespace){
        return new Queue(namespace,jedisPool.getResource());
    }

    public class Queue{

        private String namespace = "";

        Queue(Jedis jedis){
            this.jedis = jedis;
            this.pipeline = this.jedis.pipelined();
        }

        Queue(String namespace,Jedis jedis){
            this.namespace = namespace+"_";
            this.jedis = jedis;
            this.pipeline = this.jedis.pipelined();
        }

        public void clear(){
            if(StringUtils.isEmpty(this.namespace)){
                return;
            }
            Set<String> keys = jedis.keys(this.namespace+"*");
            Iterator<String> i = keys.iterator();
            while(i.hasNext()){
                pipeline.del(i.next());
            }
            pipeline.sync();
            this.close();
        }

        public Queue doDel(String key){
            this.pipeline.del(namespace+key);
            return this;
        }

        public Queue doDelObject(String key){
            this.pipeline.del((namespace+key).getBytes());
            return this;
        }

        public Queue doSet(String key,String val){
            this.pipeline.set(namespace+key,val);
            return this;
        }

        public Queue doSetEx(String key,int seconds,String val){
            this.pipeline.setex(namespace+key,seconds,val);
            return this;
        }

        public Queue doSet(String key,Serializable val){
            this.pipeline.set((namespace+key).getBytes(), SerializationUtils.serialize(val));
            return this;
        }

        public Queue doSetEx(String key,int seconds,Serializable val){
            this.pipeline.setex((namespace+key).getBytes(),seconds,SerializationUtils.serialize(val));
            return this;
        }

        public Queue doGet(String key){
            this.pipeline.get(namespace+key);
            return this;
        }

        public Queue doGetObject(String key){
            this.pipeline.get((namespace+key).getBytes());
            return this;
        }

        public Queue doAdd(String key,String val){
            this.pipeline.sadd(namespace+key,val);
            return this;
        }

        public Queue doAdd(String key,Serializable val){
            this.pipeline.sadd((namespace+key).getBytes(),SerializationUtils.serialize(val));
            return this;
        }

        public Queue doGetList(String key){
            this.pipeline.smembers(namespace+key);
            return this;
        }

        public Queue doIncr(String key){
            this.pipeline.incr(namespace+key);
            return this;
        }

        public Queue doDecr(String key){
            this.pipeline.decr(namespace+key);
            return this;
        }

        public List<Object> exec(){
            List<Object> rst = pipeline.syncAndReturnAll();
            this.close();
            return rst;
        }

        public void close(){
            if(pipeline!=null){
                try {
                    pipeline.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(jedis!=null){
                jedis.close();
            }
        }

        Jedis jedis;
        Pipeline pipeline;

    }

}
