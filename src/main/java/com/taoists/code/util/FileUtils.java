package com.taoists.code.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;

import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipFile;
import org.apache.commons.io.IOUtils;

/**
 * @author rubys@vip.qq.com
 * @since 2012-7-22
 */
public class FileUtils {

	public static final String GBK = "GBK";

	public static void unZip(File zip, String unZipPath) {
		ZipFile zipFile = null;
		try {
			zipFile = new ZipFile(zip, GBK);
			Enumeration<ZipArchiveEntry> e = zipFile.getEntries();
			while (e.hasMoreElements()) {
				ZipArchiveEntry entry = e.nextElement();
				File file = new File(unZipPath + entry.getName());
				if (entry.isDirectory()) {
					file.mkdirs();
					continue;
				} else if (!file.getParentFile().exists()) {
					file.getParentFile().mkdirs();
				}
				InputStream is = null;
				OutputStream out = null;
				try {
					is = zipFile.getInputStream(entry);
					out = new FileOutputStream(file);
					IOUtils.copy(is, out);
				} catch (Exception ee) {
					ee.printStackTrace();
				} finally {
					IOUtils.closeQuietly(is);
					IOUtils.closeQuietly(out);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (zipFile != null) {
				try {
					zipFile.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
