
package zx.controller;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import zx.service.MemoradoService;

/**
 * memorado相关程序
 * 
 * @author zhangxing
 *
 */

@Controller
@RequestMapping("/memorado")
public class MemoradoController {

	/**
	 * 跳转到数字记忆页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "memory_number", method = RequestMethod.GET)
	public String numberMemoryPage() {
		return "/memorado/number_memory";
	}

	/**
	 * 获取下一个两位数字
	 * 
	 * @return
	 */
	@RequestMapping(value = "get_random_number", method = RequestMethod.POST)
	public @ResponseBody String getRandomNumber() {
		String random_num = new MemoradoService().getRandomNumber_00_99();
		return random_num;
	}

	/**
	 * 获取下一个随机词汇
	 * 
	 * @return
	 */
	@RequestMapping(value = "get_random_word", method = RequestMethod.POST)
	public @ResponseBody String getRandomWord() throws Exception {
		String random_word = new MemoradoService().getRandomWord_00_99();
		return random_word;
	}

	/**
	 * 获取下一个随机数字和词汇
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "get_random_num_and_word", method = RequestMethod.POST)
	public @ResponseBody JSONObject getRandomNumAndWord() throws Exception {
		JSONObject obj = new MemoradoService().getRandomNumAndWord_00_99();
		return obj;
	}
	
	@RequestMapping(value="number_list",method=RequestMethod.GET)
	public String numberListPage(){
		return "/memorado/number_list";
	}

}