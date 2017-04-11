package cn.itcast.estore.utils;

import java.util.Random;

public class DirUtils {
	/**
	 * 此方法用来创建一个文件夹路径 <br/>
	 * random表示随机生成的文件夹名称<br/>
	 * level表示文件夹层级<br/>
	 */
	public static String createDirs(int random, int level){
		Random r = new Random();
		StringBuffer sb = new StringBuffer();
		for(int i=0; i<level; i++){
			sb.append("/"+r.nextInt(random));
		}
		return sb.toString();
	}
	/*public static void main(String[] args) {
		String path = createDirs(20,10);
		System.out.println(path);
	}*/
}
