package com.taoists.code.util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.krysalis.barcode4j.impl.code39.Code39Bean;
import org.krysalis.barcode4j.output.bitmap.BitmapCanvasProvider;
import org.krysalis.barcode4j.tools.UnitConv;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.taoists.common.util.DateUtils;

/**
 * @author rubys@vip.qq.com
 * @since 2012-7-12
 */
public class BarCodeUtils {

	public static final Logger logger = LoggerFactory.getLogger(BarCodeUtils.class);

	public static final int dpi = 150;
	public static final String IMAGE_EXT_NAME = ".jpg";
	public static final String IMAGE_TYPE = "image/jpeg";

	public static String genBarCode(String parentFileDir, String plainCode) {
		StringBuilder sb = new StringBuilder();
		sb.append(parentFileDir).append("/");
		sb.append(DateUtils.todayToString()).append("/");
		sb.append(plainCode).append(IMAGE_EXT_NAME);

		Code39Bean bean = new Code39Bean();
		bean.setModuleWidth(UnitConv.in2mm(1.0f / dpi));
		bean.setWideFactor(3);
		bean.doQuietZone(false);
		OutputStream out = null;
		BitmapCanvasProvider canvas = null;
		try {
			File imageFile = new File(sb.toString());
			if (!imageFile.getParentFile().exists()) {
				imageFile.getParentFile().mkdirs();
			}
			logger.debug("Generation bar code image [{}]", imageFile.getAbsoluteFile());

			out = new FileOutputStream(imageFile);
			canvas = new BitmapCanvasProvider(out, IMAGE_TYPE, dpi, BufferedImage.TYPE_BYTE_BINARY, false, 0);
			bean.generateBarcode(canvas, plainCode);
			canvas.finish();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				out.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return sb.toString().replace(parentFileDir, "");
	}

}
