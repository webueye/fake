package com.taoists;

import java.io.File;
import java.util.List;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.taoists.code.entity.BoxCode;
import com.taoists.code.service.BoxCodeService;
import com.taoists.common.bean.Page;
import com.taoists.common.util.DateUtils;

/**
 * @author rubys@vip.qq.com
 * @since 2012-5-28
 */
public class MainClass {

	static Logger logger = LoggerFactory.getLogger(MainClass.class);

	public static void main(String[] args) throws Exception {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("app.xml");
		BoxCodeService boxCodeService = (BoxCodeService) ctx.getBean("boxCodeService");

		Page page = new Page();
		page.setPageSize(1000);

		File file = new File("test.xls");
		WritableWorkbook writableWorkbook = Workbook.createWorkbook(file);
		WritableSheet sheet = writableWorkbook.createSheet("箱码", 0);

		writeHeader(sheet);
		
		

		int row = 1;
		int sheetIndex = 1;
		while (true) {
			
			List<BoxCode> boxCodes = boxCodeService.findPage(page);
			if (CollectionUtils.isEmpty(boxCodes)) {
				break;
			}
			row = writeContent(boxCodes, sheet, row);
			
			if(page.getPageNum() == page.getTotalPages()){
				break;
			}
			
			pl(page.getPageNum()+"<------>"+row);
			
			page.setPageNum(page.getPageNum() + 1);
			if (row+page.getPageSize() > 65001) {
				sheetIndex++;
				sheet = writableWorkbook.createSheet("箱码_"+sheetIndex, sheetIndex);
				writeHeader(sheet);
				row = 1;
			}
		}

		writableWorkbook.write();
		writableWorkbook.close();

	}

	static void writeHeader(WritableSheet writableSheet) throws Exception, WriteException {
		for (int column = 0; column < boxCodeHeader.length; column++) {
			Label label = new Label(column, 0, boxCodeHeader[column]);
			writableSheet.addCell(label);
		}
	}

	static int writeContent(List<BoxCode> boxCodes, WritableSheet writableSheet, int row) throws Exception {
		for (BoxCode boxCode : boxCodes) {
			writableSheet.addCell(new Label(0, row, boxCode.getBoxCode()));
			writableSheet.addCell(new Label(1, row, boxCode.getBoxSpec().getSpecName()));
			writableSheet.addCell(new Label(2, row, String.valueOf(boxCode.getBoxSpec().getCapacity())));
			writableSheet.addCell(new Label(3, row, DateUtils.toString(boxCode.getCreateDateTime())));
			row += 1;
		}
		return row;
	}

	static String[] boxCodeHeader = { "箱码", "包装箱规格", "箱容量", "生成日期" };
	static String[] fakeCodeHeader = { "箱码", "包装箱规格", "箱容量", "生成日期" };

	static void pl(Object value) {
		System.err.println(value);
	}

}
