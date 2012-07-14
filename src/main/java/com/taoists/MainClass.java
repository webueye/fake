//package com.taoists;
//
//import java.awt.image.BufferedImage;
//import java.io.File;
//import java.io.FileNotFoundException;
//import java.io.FileOutputStream;
//import java.io.OutputStream;
//import java.util.List;
//
//import jxl.write.Label;
//import jxl.write.WritableSheet;
//import jxl.write.WriteException;
//
//import net.sf.json.JSONArray;
//import net.sf.json.JSONObject;
//
//import org.krysalis.barcode4j.impl.code39.Code39;
//import org.krysalis.barcode4j.impl.code39.Code39Bean;
//import org.krysalis.barcode4j.impl.code39.Code39LogicImpl;
//import org.krysalis.barcode4j.output.bitmap.BitmapCanvasProvider;
//import org.krysalis.barcode4j.tools.UnitConv;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.support.ClassPathXmlApplicationContext;
//
//import com.taoists.code.entity.BoxCode;
//import com.taoists.code.service.FakeCodeService;
//import com.taoists.code.service.BoxCodeService;
//import com.taoists.code.util.BarCodeUtils;
//import com.taoists.common.util.CodeUtils;
//import com.taoists.common.util.DateUtils;
//
///**
// * @author rubys@vip.qq.com
// * @since 2012-5-28
// */
//public class MainClass {
//
//	static Logger logger = LoggerFactory.getLogger(MainClass.class);
//
//	public static void main(String[] args) throws Exception {
//		ApplicationContext ctx = new ClassPathXmlApplicationContext("app.xml");
//		BoxCodeService boxCodeService = (BoxCodeService) ctx.getBean("boxCodeService");
//		FakeCodeService fakeCodeService = (FakeCodeService) ctx.getBean("fakeCodeService");
//
//		// List<FakeCode> fakeCodes = fakeCodeService.findAll();
//		// long start = System.currentTimeMillis();
//		// for (FakeCode fakeCode : fakeCodes) {
//		// genBarCode(fakeCode.getPlainCode());
//		// }
//		// logger.error("----------[{}]", System.currentTimeMillis() - start);
//
//		BarCodeUtils.genBarCode("", "123456789012");
//		
//		pl(UnitConv.in2mm(1.0f / 250));
//		
//		
//	}
//
//	static void genBarCode(String barCode) {
//		Code39Bean bean = new Code39Bean();
//		int dpi = 150;
//		bean.setModuleWidth(UnitConv.in2mm(1.0f / dpi));
//		bean.setWideFactor(3);
//		bean.doQuietZone(false);
//		File outputFile = new File("F:/code/" + barCode + ".jpg");
//		OutputStream out = null;
//		BitmapCanvasProvider canvas = null;
//		try {
//			out = new FileOutputStream(outputFile);
//			canvas = new BitmapCanvasProvider(out, "image/jpeg", dpi, BufferedImage.TYPE_BYTE_BINARY, false, 0);
//			bean.generateBarcode(canvas, barCode);
//
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		} finally {
//			try {
//				if (canvas != null) {
//					canvas.finish();
//				}
//				if (out != null) {
//					out.close();
//				}
//			} catch (Exception e) {
//
//			}
//		}
//	}
//
//	static void writeHeader(WritableSheet writableSheet) throws Exception, WriteException {
//		for (int column = 0; column < boxCodeHeader.length; column++) {
//			Label label = new Label(column, 0, boxCodeHeader[column]);
//			writableSheet.addCell(label);
//		}
//	}
//
//	static int writeContent(List<BoxCode> boxCodes, WritableSheet writableSheet, int row) throws Exception {
//		for (BoxCode boxCode : boxCodes) {
//			writableSheet.addCell(new Label(0, row, boxCode.getBoxCode()));
//			writableSheet.addCell(new Label(1, row, boxCode.getBoxSpec().getSpecName()));
//			writableSheet.addCell(new Label(2, row, String.valueOf(boxCode.getBoxSpec().getCapacity())));
//			writableSheet.addCell(new Label(3, row, DateUtils.toString(boxCode.getCreateDateTime())));
//			row += 1;
//		}
//		return row;
//	}
//
//	static String[] boxCodeHeader = { "箱码", "包装箱规格", "箱容量", "生成日期" };
//	static String[] fakeCodeHeader = { "箱码", "包装箱规格", "箱容量", "生成日期" };
//
//	static void pl(Object value) {
//		System.err.println(value);
//	}
//
//}
