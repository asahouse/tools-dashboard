package org.codeworks.web.tools.dashboard.myspace;

import org.codeworks.web.tools.dashboard.myspace.model.dto.AboDto;
import org.codeworks.web.tools.dashboard.myspace.model.dto.DateRangeDto;
import org.codeworks.web.tools.dashboard.myspace.service.MyspaceDataService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ToolsDashboardMyspaceApplicationTests {

	@Autowired
	MyspaceDataService myspaceDataService;

	@Test
	public void contextLoads() {
		String st = "2017-11-01 00:00:00";
		String et = "2017-11-01 23:59:59";
		myspaceDataService.templateData(st,et,1,10);
	}

}
