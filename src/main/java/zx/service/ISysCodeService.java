package zx.service;

import java.util.List;

import zx.pojo.SysCode;

/**
 * 分类查询
 * @author zhangxing
 *
 */
public interface ISysCodeService {
	
	/**
	 * 获取所有的分类
	 * @return
	 */
	public List<SysCode> getAllSysCode();
	
	/**
	 * 开始查询单个分类
	 * @param code
	 * @return
	 */
	public List<SysCode> getSysCode(String code);
	
	
	/**
	 * 获取所有的syscode
	 * @return
	 */
	public List<SysCode> getAllSysCodeCache();
	
	/**
	 * 从缓存中去SYcode
	 * @param code
	 * @return
	 */
	public List<SysCode> getSysCodeCache(String code);
}
