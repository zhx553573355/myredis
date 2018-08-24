package zx.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.sun.istack.internal.logging.Logger;

import zx.dao.SysCodeMapper;
import zx.pojo.SysCode;
import zx.service.ISysCodeService;

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
//		ISysCodeService service = new SysCodeServiceImpl();
//		List<SysCode> syscodes = service.getAllSysCode();
//		List<SysCode> codes = new ArrayList<SysCode>();
//		for (SysCode syscode : syscodes) {
//			if (syscode.getCode() == code) {
//				codes.add(syscode);
//			}
//		}
//		return codes;
	}

}
