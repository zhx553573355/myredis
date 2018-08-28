package zx.conf;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;


/**
 * 获取系统配置文件
 * @author zhangxing
 *
 */
public class SystemConfig  {
	
	private static final Logger logger=Logger.getLogger(SystemConfig.class);
	//配置信息
	private static Properties properties = new Properties();
	//SYSCODE
	public static final String SYSCODE="SYSCODE";
	//Redis
	public static final String REDIS="REDIS";

	/**
	 * 初始化配置文件
	 * @param conf_name
	 * @throws Exception
	 */
	public SystemConfig(String conf_name){
		if (conf_name ==SystemConfig.REDIS){
			logger.info("读取redis.properties配置文件信息:");
			try {	
				InputStream in =this.getClass().getResourceAsStream("/redis.properties");
				properties.load(in);
			} catch (Exception e) {
				logger.error("读取redis.properties配置文件失败:"+e.getMessage());
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 获取服务器IP
	 * @return
	 */
	public String getRedisHost(){
		return properties.getProperty("redis.host");
	}
	
	/**
	 * 获取端口
	 * @return
	 */
	public Integer getRedisPort(){
		return Integer.parseInt(properties.getProperty("redis.port"));
	}
	
	/**
	 * 获取密码
	 * @return
	 */
	public String getRedisPass(){
		return properties.getProperty("redis.pass");
	}
	
	/**
	 * 超时时间
	 * @return
	 */
	public Integer getMaxWait(){
		return Integer.parseInt(properties.getProperty("redis.maxWait"));
	}

}
