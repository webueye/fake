package com.taoists.code.controller;

import static com.taoists.code.model.HistoryFileModel.OUT;
import static com.taoists.code.model.HistoryFileModel.STOCK_OUT;

import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;

import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.google.common.collect.Lists;
import com.google.common.collect.Multimap;
import com.google.common.collect.Sets;
import com.taoists.code.controller.path.ResultPath;
import com.taoists.code.model.DeliveryModel;
import com.taoists.code.model.HistoryFileModel;
import com.taoists.code.model.ImpResult;
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
	@SuppressWarnings("unchecked")
	public String list(HttpServletRequest request, Model model) throws Exception {
		File realFile = new File(getRealPath(request) + UNZIP);
		File[] files = realFile.listFiles(new FilenameFilter() {
			@Override
			public boolean accept(File dir, String name) {
				String lower = name.toLowerCase();
				if (lower.contains(STOCK_OUT) || lower.contains(OUT)) {
					return true;
				}
				return false;
			}
		});

		if (files != null) {
			List<HistoryFileModel> historyFiles = Lists.newArrayList();
			List<String> outFileNames = Lists.newArrayList();
			for (File file : files) {
				if (file.getName().toLowerCase().startsWith(STOCK_OUT)) {
					HistoryFileModel hf = new HistoryFileModel();
					hf.setStockOutFileName(file.getName());
					FileInputStream is = new FileInputStream(file);
					Set<String> lines = Sets.newHashSet();
					for (String line : (List<String>) IOUtils.readLines(is)) {
						String[] values = line.split(",");
						if (values != null && values.length > 0) {
							lines.add(values[0]);
						}
					}
					hf.setStockOutLines(lines);
					IOUtils.closeQuietly(is);
					historyFiles.add(hf);
				} else {
					outFileNames.add(file.getName());
				}
			}

			for (HistoryFileModel historyFile : historyFiles) {
				List<String> fileNames = Lists.newArrayList();
				for (String prefix : historyFile.getStockOutLines()) {
					for (String fileName : outFileNames) {
						if (fileName.toLowerCase().startsWith(OUT) && fileName.contains(prefix)) {
							fileNames.add(fileName);
						}
					}
				}
				historyFile.setOutFileNames(fileNames);
			}
			model.addAttribute("files", historyFiles);
		}

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

	@SuppressWarnings("unchecked")
	@RequestMapping("delete")
	public String delete(HttpServletRequest request, String stockOutFile) throws Exception {
		File stockFile = new File(getRealPath(request) + UNZIP + stockOutFile);
		if (!stockFile.exists()) {
			return redirect(ResultPath.deliveryHistory);
		}
		FileInputStream is = new FileInputStream(stockFile);
		final List<String> lines = IOUtils.readLines(is);
		File realFile = new File(getRealPath(request) + UNZIP);
		realFile.listFiles(new FileFilter() {

			@Override
			public boolean accept(File file) {
				String name = file.getName();
				for (String line : lines) {
					String[] values = line.split(",");
					if (values != null && values.length > 0) {
						String prefix = values[0];
						if (name != null && name.toLowerCase().startsWith(OUT) && name.contains(prefix)) {
							file.delete();
						}
					}
				}
				return false;
			}
		});
		IOUtils.closeQuietly(is);
		stockFile.delete();
		return redirect(ResultPath.deliveryHistory);
	}

	@RequestMapping("preview")
	public String preview(HttpServletRequest request, String stockOutFile, Model model) throws Exception {
		File stockFile = new File(getRealPath(request) + UNZIP + stockOutFile);
		List<String> stockFileLines = readFiles(Lists.newArrayList(stockFile));
		Set<DeliveryModel> deliveryModels = Sets.newHashSet();
		for (String line : stockFileLines) {
			String[] values = line.split(",");
			if (values != null && values.length == 5) {
				deliveryModels.add(new DeliveryModel(values[0], values[3]));
			}
		}

		for (DeliveryModel deliveryModel : deliveryModels) {
			List<File> outFiles = getOutFiles(request, deliveryModel.getDeliveryNo());
			deliveryModel.setCodes(Sets.newHashSet(readFiles(outFiles)));
		}
		deliveryHistoryService.preview(deliveryModels);
		Multimap<String, DeliveryModel> group = DeliveryModel.group(deliveryModels);
		model.addAttribute("illegals", group.get("illegals"));
		model.addAttribute("boxCodeExisted", group.get("existed"));
		model.addAttribute("boxCodeNotExisted", group.get("notExisted"));
		model.addAttribute("stockOutFile", stockOutFile);
		return "/code/history/delivery-preview-list";
	}

	@RequestMapping(value = "imp", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
	public @ResponseBody
	String imp(HttpServletRequest request, String[] fileNames, Model model) throws Exception {
		if (fileNames != null) {
			List<ImpResult> impResults = Lists.newArrayList();
			for (String stockOutFile : fileNames) {
				File stockFile = new File(getRealPath(request) + UNZIP + stockOutFile);
				List<String> stockFileLines = readFiles(Lists.newArrayList(stockFile));
				Set<DeliveryModel> deliveryModels = Sets.newHashSet();
				for (String line : stockFileLines) {
					String[] values = line.split(",");
					if (values != null && values.length == 5) {
						deliveryModels.add(new DeliveryModel(values[0], values[3]));
					}
				}

				for (DeliveryModel deliveryModel : deliveryModels) {
					List<File> outFiles = getOutFiles(request, deliveryModel.getDeliveryNo());
					deliveryModel.setCodes(Sets.newHashSet(readFiles(outFiles)));
				}
				impResults.addAll(deliveryHistoryService.imp(deliveryModels, getAccount(model)));
			}
			Collections.sort(impResults, new Comparator<ImpResult>() {

				@Override
				public int compare(ImpResult o1, ImpResult o2) {
					return o1.getType().compareTo(o2.getType());
				}

			});
			return JSONArray.fromObject(impResults).toString();
		}
		return null;
	}

	private List<File> getOutFiles(HttpServletRequest request, final String prefix) throws Exception {
		File realFile = new File(getRealPath(request) + UNZIP);
		File[] files = realFile.listFiles(new FileFilter() {

			@Override
			public boolean accept(File file) {
				String name = file.getName();
				if (name != null && name.toLowerCase().startsWith(OUT) && name.contains(prefix)) {
					return true;
				}
				return false;
			}
		});
		if (files == null) {
			return Lists.newArrayList();
		}
		return Lists.newArrayList(files);
	}

	@SuppressWarnings("unchecked")
	private List<String> readFiles(List<File> files) throws Exception {
		List<String> lines = Lists.newArrayList();
		if (files != null) {
			for (File file : files) {
				FileInputStream is = new FileInputStream(file);
				lines.addAll(IOUtils.readLines(is));
				IOUtils.closeQuietly(is);
			}
		}
		return lines;
	}

	private String getRealPath(HttpServletRequest request) {
		return request.getSession().getServletContext().getRealPath(HISTORY);
	}

}
