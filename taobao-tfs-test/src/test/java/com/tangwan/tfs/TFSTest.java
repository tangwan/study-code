package com.tangwan.tfs;

import java.io.IOException;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.taobao.common.tfs.TfsManager;
import com.taobao.common.tfs.packet.FileInfo;
import junit.framework.TestCase;

/**
 * TFS-Client测试代码(增,删,查,下载,隐藏)
 * @FileName:TFSTest.java
 * @Description: TODO()
 * @Author: tangwan
 * @Date: 2016年4月14日 下午3:07:40
 * @since: JDK 1.8
 */
public class TFSTest extends TestCase {
	ApplicationContext context;

	protected void setUp() throws Exception {
		super.setUp();
		context = new ClassPathXmlApplicationContext("tfs.xml");
	}

	public void testTemp() {
		TfsManager tfsMgr = (TfsManager) context.getBean("tfsManager");
		System.out.println(tfsMgr);
	}

	// 上传(可以传操作系统绝对路径和工程相对路径,也可以穿字节流,文件名和后缀可以为null)
	public void testSave() throws IOException {
		TfsManager tfsMgr = (TfsManager) context.getBean("tfsManager");
		String fileName1 = tfsMgr.saveFile("分布式系统工程实践.pdf", null, null);
		String fileName2 = tfsMgr.saveFile("女神.jpg", null, null);
		System.out.println("<<分布式系统工程实践.pdf>>在TFS中文件名: " + fileName1 + " 浏览器访问:http://172.29.1.101:81/v1/tfs/" + fileName1);
		System.out.println("女神.jpg在TFS中文件名: " + fileName1 + " 浏览器访问:http://172.29.1.101:81/v1/tfs/" + fileName2);

		// 存字节数组http://172.29.1.101:81/v1/tfs/T1qRJTByJT1RCvBVdK
		byte[] byteArray = FileUtils.toByteArray("山丘-李宗盛.mp3");
		String fileName3 = tfsMgr.saveFile(byteArray, null, null);
		System.out.println("山丘-李宗盛.mp3在TFS中文件名 "+fileName3 + " 浏览器访问:http://172.29.1.101:81/v1/tfs/" + fileName3);

		// 保存时指定文件名和校验重复(需要服务器支持);tair k-v系统
		
	}

	// 下载
	public void testFetch() {
		TfsManager tfsMgr = (TfsManager) context.getBean("tfsManager");
		boolean flag = tfsMgr.fetchFile("T1PaJTByJT1RCvBVdK", null, "陈乔恩.jpg");// T1PaJTByJT1RCvBVdK  女神.jpg
		System.out.println(flag);
	}

	// 删除(true,删除成功;删除不存在的文件返回false)
	public void testDelete() {
		TfsManager tfsMgr = (TfsManager) context.getBean("tfsManager");
		boolean flag = tfsMgr.unlinkFile("T1PaJTByJT1RCvBVdK", null);
		System.out.println(flag);
	}

	// 最后一个参数:1 隐藏；0显示(隐藏后不能浏览下载)
	public void testHide() {
		TfsManager tfsMgr = (TfsManager) context.getBean("tfsManager");
		boolean flag = tfsMgr.hideFile("T1WyJTByJT1RCvBVdK", null, 0);
		System.out.println(flag);
	}

	/**
	 * 文件信息
	 * fileid: 1 
	 * offset: 0 
	 * length: 1189393 
	 * occupyLength: 1189429 
	 * modifyTime:2016-04-14 16:08:21 
	 * createTime: 2016-04-14 15:58:34 
	 * status: 0 crc:
	 * 1041138349
	 */
	// stat一个tfs文件，成功返回FileInfo， 失败返回nul
	public void testState() {
		TfsManager tfsMgr = (TfsManager) context.getBean("tfsManager");
		FileInfo fileInfo = tfsMgr.statFile("T1WyJTByJT1RCvBVdK", null);
		int flag = fileInfo.getFlag();
		System.out.println("状态标识:" + flag);
	}
}
