package com.taoists.code.controller;

import static com.taoists.code.model.HistoryFileModel.BATCH;
import static com.taoists.code.model.HistoryFileModel.WS;

import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.InputStream;
import java.util.Collection;
import java.util.List;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;

import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Multimap;
import com.taoists.code.controller.path.ResultPath;
import com.taoists.code.model.HistoryCodeModel;
import com.taoists.code.model.HistoryFileModel;
import com.taoists.code.model.ImpResult;
import com.taoists.code.model.ImpResult.Type;
import com.taoists.code.util.FileUtils;
import com.taoists.common.controller.CommonController;

/**
 * @author rubys@vip.qq.com
 * @since 2012-7-22
 */
@Controller
@RequestMapping(ResultPath.boxCodeHistory)
public class BoxCodeHistoryController extends CommonController {

	public static final String HISTORY = "/history";
	public static final String ZIP = "/zip/";
	public static final String UNZIP = "/unzip/";

	@RequestMapping
	public String list(HttpServletRequest request, Model model) {
		File realFile = new File(getRealPath(request) + UNZIP);
		String[] files = realFile.list(new FilenameFilter() {
			@Override
			public boolean accept(File dir, String name) {
				String lower = name.toLowerCase();
				if (lower.contains(WS) || lower.contains(BATCH)) {
					return true;
				}
				return false;
			}
		});

		Multimap<String, String> group = HashMultimap.create();
		if (files != null) {
			for (String name : files) {
				String lowerName = name.toLowerCase();
				lowerName = lowerName.replace(WS, "");
				lowerName = lowerName.replace(BATCH, "");
				group.put(lowerName.substring(0, lowerName.length() - 5), name);
			}

			List<HistoryFileModel> models = Lists.newArrayList();
			for (Entry<String, Collection<String>> entry : group.asMap().entrySet()) {
				models.add(new HistoryFileModel(entry.getKey(), entry.getValue()));
			}

			model.addAttribute("files", models);
		}
		return "/code/history/box-code-history-list";
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
		return redirect(ResultPath.boxCodeHistory);
	}

	@RequestMapping(value = "imp", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
	public @ResponseBody
	String imp(HttpServletRequest request, String[] suffix, Model model) {
		if (suffix != null) {
			final List<ImpResult> results = Lists.newArrayList();
			handle(request, suffix, new Execute() {
				@Override
				public void invoke(String suffix, File batchFile, File wsFile) {
					if (wsFile == null || batchFile == null) {
						results.add(new ImpResult(Type.other, suffix, "file not found"));
					} else {
						List<String> wsLines = readFile(wsFile);
						List<String> batchLines = readFile(batchFile);
						results.addAll(codeHistoryService.imp(wsLines, batchLines.get(0)));
					}
				}
			});
			return JSONArray.fromObject(results).toString();
		}
		return null;
	}

	@RequestMapping("preview")
	public String preview(HttpServletRequest request, String[] suffix, Model model) {
		final List<HistoryCodeModel> results = Lists.newArrayList();
		handle(request, suffix, new Execute() {
			@Override
			public void invoke(String suffix, File batchFile, File wsFile) {
				HistoryCodeModel model = new HistoryCodeModel();
				if (wsFile == null || batchFile == null) {
					model.setResult(new ImpResult(Type.other, suffix, "file not found"));
				} else {
					List<String> wsLines = readFile(wsFile);
					List<String> batchLines = readFile(batchFile);
					results.add(codeHistoryService.prehandle(batchLines.get(0), wsLines));
				}
			}
		});
		model.addAttribute("results", results);
		model.addAttribute("suffixes", suffix);
		return "/code/history/preview-list";
	}

	@RequestMapping("delete/{suffix}")
	public String delete(HttpServletRequest request, @PathVariable final String suffix) {
		File realFile = new File(getRealPath(request) + UNZIP);
		realFile.listFiles(new FileFilter() {

			@Override
			public boolean accept(File file) {
				String name = file.getName();
				if (name != null && name.contains(suffix)) {
					file.delete();
				}
				return false;
			}
		});
		return redirect(ResultPath.boxCodeHistory);
	}

	@SuppressWarnings("unchecked")
	private List<String> readFile(File file) {
		List<String> lines = Lists.newArrayList();
		InputStream is = null;
		try {
			is = new FileInputStream(file);
			lines.addAll(IOUtils.readLines(is));
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			IOUtils.closeQuietly(is);
		}
		return lines;
	}

	private void handle(HttpServletRequest request, String[] suffix, Execute execute) {
		for (String suff : suffix) {
			List<File> files = FileUtils.search(getRealPath(request) + UNZIP, suff);
			if (files != null && files.size() >= 2) {
				File wsFile = null;
				File batchFile = null;
				for (File file : files) {
					if (file.getName().toLowerCase().contains(WS)) {
						wsFile = file;
					} else if (file.getName().toLowerCase().contains(BATCH)) {
						batchFile = file;
					}
				}
				execute.invoke(suff, batchFile, wsFile);

			}
		}
	}

	static interface Execute {
		void invoke(String suffix, File batchFile, File wsFile);
	}

	private String getRealPath(HttpServletRequest request) {
		return request.getSession().getServletContext().getRealPath(HISTORY);
	}

}
