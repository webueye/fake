package com.taoists.code.controller;

import java.io.File;
import java.io.FileOutputStream;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.taoists.code.controller.path.ResultPath;
import com.taoists.code.util.FileUtils;
import com.taoists.common.controller.CommonController;

/**
 * @author rubys@vip.qq.com
 * @since 2012-7-25
 */
@Controller
@RequestMapping(ResultPath.deliveryHistory)
public class DeliveryHistoryController extends CommonController {

	public static final String HISTORY = "/history";
	public static final String ZIP = "/zip/";
	public static final String UNZIP = "/unzip/";

	@RequestMapping
	public String index() {
		return "/code/history/delivery-history-list";
	}

	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public String upload(HttpServletRequest request, @RequestParam MultipartFile file, Model model) throws Exception {
		File realFile = new File(getRealPath(request) + ZIP + file.getOriginalFilename());
		if (!realFile.getParentFile().exists()) {
			realFile.getParentFile().mkdirs();
		}
		FileOutputStream out = new FileOutputStream(realFile);
		IOUtils.write(file.getBytes(), out);
		IOUtils.closeQuietly(out);
		FileUtils.unZip(realFile, getRealPath(request) + UNZIP);
		realFile.delete();
		return redirect(ResultPath.deliveryHistory);
	}

	private String getRealPath(HttpServletRequest request) {
		return request.getSession().getServletContext().getRealPath(HISTORY);
	}

}
