package zx.init;

import java.util.List;

import javax.annotation.Resource;
 
import org.apache.log4j.Logger;

import zx.pojo.SysCode;
import zx.service.ISysCodeService;
import zx.serviceImpl.SysCodeServiceImpl;

public class SysCodeCache {
	
	private static final Logger logger = Logger.getLogger(SysCodeCache.class);
	
	@Resource
	ISysCodeService syscodeService;

	public SysCodeCache() {
		// TODO Auto-generated constructor stub
	}

	public void init() {
		logger.info("SysCode缓存加载开始");
		List<SysCode> syscodes= syscodeService.getAllSysCode();
		
		logger.info("SysCode缓存加载完成");

	}

}
