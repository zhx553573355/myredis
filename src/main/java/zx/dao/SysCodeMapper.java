package zx.dao;

import java.util.List;

import zx.pojo.SysCode;

/**
 * 分类明细
 * @author zhangxing
 *
 */
public interface SysCodeMapper {
	/**
	 * 获取所有的分类明细
	 * @return
	 */
	public List<SysCode> getAllSysCode();
	
	/**
	 * 获取某一分类
	 * @param code
	 * @return
	 */
	public List<SysCode> getSysCode(String code);
	
}
