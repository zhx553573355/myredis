package zx.serviceImpl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import zx.dao.SysCodeMapper;
import zx.init.RedisStart;
import zx.pojo.SysCode;
import zx.service.ISysCodeService;
import zx.utils.SerializeUtil;

@Service("sysCodeService")
public class SysCodeServiceImpl implements ISysCodeService {

	private static Logger logger = Logger.getLogger(SysCodeServiceImpl.class);

	@Resource
	private SysCodeMapper iSysCodeDao;

	@Cacheable("allSysCode")
	@Override
	public List<SysCode> getAllSysCode() {
		logger.info("开始查询数据库信息：SysCodes");
		return iSysCodeDao.getAllSysCode();
	}

	/**
	 * 获取单个分类
	 * 
	 * @param code
	 * @return
	 */
	@Cacheable("SysCode")
	@Override
	public List<SysCode> getSysCode(String code) {
		logger.info("开始查询单个分类：SysCode");
		return iSysCodeDao.getSysCode(code);
		// ISysCodeService service = new SysCodeServiceImpl();
		// List<SysCode> syscodes = service.getAllSysCode();
		// List<SysCode> codes = new ArrayList<SysCode>();
		// for (SysCode syscode : syscodes) {
		// if (syscode.getCode() == code) {
		// codes.add(syscode);
		// }
		// }
		// return codes;
	}

	/**
	 * 获取所有的syscode
	 * 
	 * @return
	 */
	public List<SysCode> getAllSysCodeCache() {
		logger.info("开始查询数据库信息：SysCodes");
		return iSysCodeDao.getAllSysCode();
	}

	/**
	 * 从缓存中取syscode
	 */
	public List<SysCode> getSysCodeCache(String code) {
		List<SysCode> list = new ArrayList<SysCode>();
		//获取缓存中的syscode
		Set<byte[]> syscode = RedisStart.jedis.smembers("YES_NO".getBytes());
		Iterator<byte[]> it = syscode.iterator();
		while (it.hasNext()) {
			Object str=(Object)SerializeUtil.unSerialize(it.next());
			System.out.println(str);
			SysCode sc = (SysCode)str;
			list.add(sc);
		}
		return list;
	}

}
