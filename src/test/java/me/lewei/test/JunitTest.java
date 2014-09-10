package me.lewei.test;

import javax.annotation.Resource;

import me.lewei.app.BaseJobStart;
import me.lewei.logic.WriteService;
import me.lewei.logic.impl.ReadServiceImpl;
import me.lewei.obj.ReadContext;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext-Test.xml" })
public class JunitTest {

	@Resource(name = "logic.WriteService")
	private WriteService writeServiceImpl;

	@Resource(name = "logic.ReadService")
	private ReadServiceImpl readServiceImpl;

	// @Before
	// public void beforeTest() throws Exception {
	// System.setProperty("config",
	// "E:/Workspace/source_code/trunk/DEV/mpa-properties/env-resources/CDDEV/mpa.properties");
	// }

	@Test
	public void readTest() throws Exception {
		BaseJobStart.test(new String[] { "-r", "D:\\me.lewei.app_input" });
	}
	
	@Test
	public void readFileNameListTest() throws Exception {
		ReadContext rc = new ReadContext();
		rc.setInputPath("C:\\Users\\helewei\\Desktop\\properties.prod_1\\CV\\");
		readServiceImpl.readFileNameList(rc);
	}

	public WriteService getWriteServiceImpl() {
		return writeServiceImpl;
	}

	public void setWriteServiceImpl(WriteService writeServiceImpl) {
		this.writeServiceImpl = writeServiceImpl;
	}

	public ReadServiceImpl getReadServiceImpl() {
		return readServiceImpl;
	}

	public void setReadServiceImpl(ReadServiceImpl readServiceImpl) {
		this.readServiceImpl = readServiceImpl;
	}

}
