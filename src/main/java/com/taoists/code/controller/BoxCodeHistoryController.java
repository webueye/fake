package com.taoists.code.controller;

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
import com.taoists.code.model.HistoryFileModel;
import com.taoists.code.model.ImpResult;
import com.taoists.code.util.FileUtils;
import com.taoists.common.ViewName;
import com.taoists.common.controller.CommonController;
import com.taoists.common.controller.Module;

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
				if (lower.contains(WS)) {
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
				group.put(lowerName, name);
			}

			List<HistoryFileModel> models = Lists.newArrayList();
			for (Entry<String, Collection<String>> entry : group.asMap().entrySet()) {
				models.add(new HistoryFileModel(entry.getKey(), entry.getValue()));
			}

			model.addAttribute("files", models);
		}
		return forword(ViewName.list);
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
			List<String> lines = Lists.newArrayList();
			for (String suff : suffix) {
				readFile(lines, request, suff);
			}
			List<ImpResult> results = codeHistoryService.imp(lines);
			return JSONArray.fromObject(results).toString();
		}
		return null;
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
	private void readFile(List<String> lines, HttpServletRequest request, String suffix) {
		String path = getRealPath(request) + UNZIP;
		File ws = new File(path + "WS" + suffix);
		InputStream is = null;
		try {
			is = new FileInputStream(ws);
			lines.addAll(IOUtils.readLines(is));
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			IOUtils.closeQuietly(is);
		}
	}

	private String getRealPath(HttpServletRequest request) {
		return request.getSession().getServletContext().getRealPath(HISTORY);
	}

	private String forword(ViewName viewName) {
		return forward(Module.code, ResultPath.boxCodeHistory, viewName);
	}

}
