package zx.controller;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import zx.pojo.SysCode;
import zx.pojo.User;
import zx.service.ISysCodeService;
import zx.service.IUserService;

/**
 * user控制器
 * 
 * @author YaoQi
 */

@Controller
@RequestMapping("/syscode")
public class SysCodeController {

	private static final Logger logger = Logger.getLogger(SysCodeController.class);

	@Resource
	private ISysCodeService sysCodeService;

	/**
	 * 显示所有的分类信息
	 * @return
	 */
	@RequestMapping(value = "/showAllSysCode", method = RequestMethod.GET)
	public String showAllSysCode() {
		List<SysCode> codes= sysCodeService.getAllSysCode();
		logger.info("syscodes:"+codes.size());
		ModelMap map = new ModelMap();
		map.addAttribute("result", "添加成功");
		return "/redis/redis";
	}
	
	/**
	 * 显示单个分类
	 * @param code
	 * @return
	 */
	@RequestMapping(value="/showSysCode",method=RequestMethod.GET)
	public String showSysCode(String code){
		List<SysCode> codes=sysCodeService.getSysCode(code);
		if(codes !=null){
			logger.info("syscodes:"+codes.toString());
		}
		ModelMap map = new ModelMap();
		map.addAttribute("result", "添加成功");
		return "/redis/redis";
	}
	
	@RequestMapping(value="/showSysCodeCache",method=RequestMethod.GET)
	public String showSysCodeCache(String code){
		List<SysCode> codes=sysCodeService.getSysCodeCache(code);
		if(codes !=null){
			logger.info("syscodes:"+codes.toString());
		}
		ModelMap map = new ModelMap();
		map.addAttribute("result", "添加成功");
		return "/redis/redis";
	}

}
