package zx.init;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;
import zx.conf.SystemConfig;
import zx.pojo.SysCode;
import zx.service.ISysCodeService;
import zx.utils.SerializeUtil;

/**
 * 启动redis
 * @author zhangxing
 *
 */
public class RedisStart {

	public static Jedis jedis;// 非切片额客户端连接
	private JedisPool jedisPool;// 非切片连接池
	private ShardedJedis shardedJedis;// 切片额客户端连接
	private ShardedJedisPool shardedJedisPool;// 切片连接池
	
	SystemConfig redisConf=new SystemConfig(SystemConfig.REDIS);
	
	private  final Logger logger=Logger.getLogger(RedisStart.class);
	
	@Resource
	ISysCodeService sysCodeService;
	
	public RedisStart(){
		
	}
	
	public void init() {
		logger.info("redis缓存Syscode启动：");
		//加载redis配置信息
		initialPool();
		initialShardedPool();
		jedis = jedisPool.getResource();
		//获取所有的缓存信息
		List<SysCode> syscodes= sysCodeService.getAllSysCodeCache();
		
		//将syscode序列化并存入redis
		for (SysCode syscode :  syscodes){
			jedis.sadd(syscode.getCode().getBytes(), SerializeUtil.serialize(syscode));
		}
		logger.info("redis缓存Syscode结束：");
	}
	
	/**
	 * 初始化非切片池
	 */
	private void initialPool() {
		// 池基本配置
		JedisPoolConfig config = new JedisPoolConfig();
		config.setMaxIdle(5);
		config.setTestOnBorrow(false);
		
		jedisPool = new JedisPool(config, redisConf.getRedisHost(), redisConf.getRedisPort(),redisConf.getMaxWait(),redisConf.getRedisPass());
	}

	/**
	 * 初始化切片池
	 */
	private void initialShardedPool() {
		// 池基本配置
		JedisPoolConfig config = new JedisPoolConfig();
		config.setMaxIdle(5);
		config.setTestOnBorrow(false);
		// slave链接
		List<JedisShardInfo> shards = new ArrayList<JedisShardInfo>();
		JedisShardInfo jedisInfo=new JedisShardInfo(redisConf.getRedisHost(), redisConf.getRedisPort());
		jedisInfo.setPassword(redisConf.getRedisPass());
		shards.add(jedisInfo);
		// 构造池
		shardedJedisPool = new ShardedJedisPool(config, shards);
	}
	
	

}
