package com.taoists.code.service.impl;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taoists.code.entity.BoxCode;
import com.taoists.code.entity.CodeIssue;
import com.taoists.code.entity.FakeCode;
import com.taoists.code.service.BoxCodeService;
import com.taoists.code.service.ExcelService;
import com.taoists.code.service.FakeCodeService;
import com.taoists.common.bean.Page;
import com.taoists.common.util.DateUtils;

/**
 * @author rubys@vip.qq.com
 * @since 2012-6-19
 */
@Service
public class ExcelServiceImpl implements ExcelService {

	private Logger logger = LoggerFactory.getLogger(getClass());

	private int pageSize = 1000;
	public static String[] boxCodeHeader = { "箱码", "包装箱规格", "箱容量", "生成日期" };
	public static String[] fakeCodeHeader = { "明码", "防伪码", "包装箱规格", "生成日期" };
	public static String boxCodeSheetName = "箱码";
	public static String fakeCodeSheetName = "防伪码";
	public static int singleSheetMaxRows = 65001;

	@Override
	public byte[] exportBoxCodes(CodeIssue codeIssue) {
		int row = 1;
		int sheetIndex = 0;
		Page page = new Page();
		page.setPageSize(pageSize);

		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

		WritableWorkbook writableWorkbook = createWritableWorkbook(outputStream);
		try {
			WritableSheet sheet = createWritableSheet(writableWorkbook, boxCodeHeader, boxCodeSheetName, sheetIndex);

			logger.debug("Starting export box codes");
			while (true) {
				List<BoxCode> boxCodes = boxCodeService.findDatas("codeIssue.id", codeIssue.getId(), page);
				if (CollectionUtils.isEmpty(boxCodes)) {
					break;
				}
				row = writeBoxCodeContent(boxCodes, sheet, row);

				if (page.getPageNum() == page.getTotalPages()) {
					break;
				}

				page.setPageNum(page.getPageNum() + 1);
				if (row + page.getPageSize() > singleSheetMaxRows) {
					sheet = createWritableSheet(writableWorkbook, boxCodeHeader, boxCodeSheetName, ++sheetIndex);
					row = 1;
				}
			}
			logger.debug("Ending export box codes");
		} catch (Exception e) {
			throw new IllegalStateException(e);
		} finally {
			close(writableWorkbook);
			IOUtils.closeQuietly(outputStream);
		}
		return outputStream.toByteArray();
	}

	@Override
	public byte[] exportFakeCodes(CodeIssue codeIssue) {
		int row = 1;
		int sheetIndex = 0;
		Page page = new Page();
		page.setPageSize(pageSize);

		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

		WritableWorkbook writableWorkbook = createWritableWorkbook(outputStream);
		try {
			WritableSheet sheet = createWritableSheet(writableWorkbook, fakeCodeHeader, fakeCodeSheetName, sheetIndex);

			logger.debug("Starting export fake codes");
			while (true) {
				List<FakeCode> fakeCodes = fakeCodeService.findDatas("codeIssue.id", codeIssue.getId(), page);
				if (CollectionUtils.isEmpty(fakeCodes)) {
					break;
				}
				row = writeFakeCodeContent(fakeCodes, sheet, row);

				if (page.getPageNum() == page.getTotalPages()) {
					break;
				}

				page.setPageNum(page.getPageNum() + 1);
				if (row + page.getPageSize() > singleSheetMaxRows) {
					sheet = createWritableSheet(writableWorkbook, fakeCodeHeader, fakeCodeSheetName, ++sheetIndex);
					row = 1;
				}
			}
			logger.debug("Ending export fake codes");
		} catch (Exception e) {
			throw new IllegalStateException(e);
		} finally {
			close(writableWorkbook);
			IOUtils.closeQuietly(outputStream);
		}
		return outputStream.toByteArray();
	}

	public void writeHeader(WritableSheet writableSheet, String[] headers) throws Exception, WriteException {
		for (int column = 0; column < headers.length; column++) {
			Label label = new Label(column, 0, headers[column]);
			writableSheet.addCell(label);
		}
	}

	public int writeBoxCodeContent(List<BoxCode> boxCodes, WritableSheet writableSheet, int row) throws Exception {
		for (BoxCode boxCode : boxCodes) {
			writableSheet.addCell(new Label(0, row, boxCode.getBoxCode()));
			writableSheet.addCell(new Label(1, row, boxCode.getBoxSpec().getSpecName()));
			writableSheet.addCell(new Label(2, row, String.valueOf(boxCode.getBoxSpec().getCapacity())));
			writableSheet.addCell(new Label(3, row, DateUtils.toString(boxCode.getCreateDateTime())));
			row += 1;
		}
		return row;
	}

	public int writeFakeCodeContent(List<FakeCode> fakeCodes, WritableSheet writableSheet, int row) throws Exception {
		for (FakeCode fakeCode : fakeCodes) {
			writableSheet.addCell(new Label(0, row, fakeCode.getPlainCode()));
			writableSheet.addCell(new Label(1, row, fakeCode.getFakeCode()));
			writableSheet.addCell(new Label(2, row, fakeCode.getBoxSpec().getSpecName()));
			writableSheet.addCell(new Label(3, row, DateUtils.toString(fakeCode.getCreateDateTime())));
			row += 1;
		}
		return row;
	}

	public WritableSheet createWritableSheet(WritableWorkbook writableWorkbook, String[] headers, String sheetName, int sheetIndex) throws Exception {
		WritableSheet writableSheet = writableWorkbook.createSheet(sheetName + "_" + (sheetIndex + 1), sheetIndex);
		writeHeader(writableSheet, headers);
		logger.debug("Creating sheet name[{}], sheet index[{}]", sheetName, sheetIndex);
		return writableSheet;
	}

	public WritableWorkbook createWritableWorkbook(OutputStream outputStream) {
		try {
			return Workbook.createWorkbook(outputStream);
		} catch (IOException e) {
			throw new IllegalStateException(e);
		}
	}

	public void close(WritableWorkbook writableWorkbook) {
		try {
			writableWorkbook.write();
			writableWorkbook.close();
		} catch (Exception e) {
			throw new IllegalStateException(e);
		}
	}

	@Autowired
	private BoxCodeService boxCodeService;
	@Autowired
	private FakeCodeService fakeCodeService;

}
