
package zx.controller;

import java.io.File;
 
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;

/**
 * memorado
 * 
 * @author zhangxing
 *
 */
 
@Controller
@RequestMapping("/main")
public class MainController {

	/**
	 * 
	 * 
	 * @return
	 */
	@RequestMapping(value = "index", method = RequestMethod.GET)
	public String numberMemoryPage() {
		return "/index";
	}

	

}