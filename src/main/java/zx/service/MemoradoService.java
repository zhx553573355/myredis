package zx.service;

import java.io.File;

import org.apache.commons.io.FileUtils;

import com.alibaba.fastjson.JSONObject;

/**
 * 处理memorado相关逻辑
 * 
 * @author zhangxing
 *
 */
public class MemoradoService {

	/**
	 * 随机取整 00到99
	 * 
	 * @return
	 */
	public String getRandomNumber_00_99() {
		int first_num =(int) Math.floor(Math.random() * 10);
		int second_num =(int) Math.floor(Math.random() * 10);
		return String.valueOf(first_num) + String.valueOf(second_num);
	}
	
	/**
	 * 将随机数字转换成随机词汇
	 * @return
	 */
	public String getRandomWord_00_99()throws Exception{
		String random_num=getRandomNumber_00_99();
		String file_path= this.getClass().getResource("/memorado/memoryNumber.js")
                .getPath();
		
		File file = new File(file_path);
		String content = FileUtils.readFileToString(file, "UTF-8");
		JSONObject jsonObject = JSONObject.parseObject(content);
		System.out.println(jsonObject.toString());
		String random_word = jsonObject.getString(random_num);
		return random_word;
	}
	
	/**
	 * 同时随机数字和词汇
	 * @return
	 * @throws Exception
	 */
	public JSONObject getRandomNumAndWord_00_99()throws Exception{
		String random_num=getRandomNumber_00_99();
		String file_path= this.getClass().getResource("/memorado/memoryNumber.js")
                .getPath();
		
		File file = new File(file_path);
		String content = FileUtils.readFileToString(file, "UTF-8");
		JSONObject jsonObject = JSONObject.parseObject(content);
		System.out.println(jsonObject.toString());
		String random_word = jsonObject.getString(random_num);
		
		JSONObject obj=new JSONObject();
		obj.put("num", random_num);
		obj.put("word",random_word );
		return obj;
	}
	
}