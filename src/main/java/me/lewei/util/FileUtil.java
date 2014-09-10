package me.lewei.util;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class FileUtil {
	
	private static final Log log = LogFactory.getLog(FileUtil.class);

	/**
	 * get all files under some directory
	 * 
	 * @param folderName
	 * @param includeSubFolder
	 * @param fileList
	 * @return
	 */
	public static List<File> getAllFiles(String folderName, boolean includeSubFolder) {

		List<File> fileList = new ArrayList<File>();

		File folder = new File(folderName);
		if (!folder.exists())
			log.warn("The folder: " + folder + " doesn't exist!");

		if (!folder.isDirectory())
			log.warn("The folder: " + folder + " is not a Directory!");

		File[] files = folder.listFiles();
		if (files == null || files.length < 1)
			log.warn("The folder: " + folder + " is a Empty directory!");

		if (files != null) {
			for (File file : files) {

				if (file != null) {
					log.debug("\tLoop file [" + file.getAbsolutePath() + "]");
					if (!file.isFile() && !file.isDirectory()) {
						log.debug("The file [" + file.getAbsolutePath() + "] is not a File or Directory");
						continue;
					}
					if (file.isDirectory() && includeSubFolder) {
						getAllFiles(file.toString(), includeSubFolder, fileList);
						continue;
					}
					if (file.isFile()) {
						fileList.add(file);
					}
				}
			}
		}

		return fileList;
	}
	
	/**
	 * get all files under some directory
	 * 
	 * @param folderName
	 * @param includeSubFolder
	 * @param fileList
	 * @return
	 */
	public static List<File> getAllFiles(String folderName, boolean includeSubFolder, List<File> fileList) {

		if (fileList == null) {
			fileList = new ArrayList<File>();
		}

		File folder = new File(folderName);
		if (!folder.exists())
			log.warn("The folder: " + folder + " doesn't exist!");

		if (!folder.isDirectory())
			log.warn("The folder: " + folder + " is not a Directory!");

		File[] files = folder.listFiles();
		if (files == null || files.length < 1)
			log.warn("The folder: " + folder + " is a Empty directory!");

		if (files != null) {
			for (File file : files) {

				if (file != null) {
					log.debug("\tLoop file [" + file.getAbsolutePath() + "]");
					if (!file.isFile() && !file.isDirectory()) {
						log.debug("The file [" + file.getAbsolutePath() + "] is not a File or Directory");
						continue;
					}
					if (file.isDirectory() && includeSubFolder) {
						getAllFiles(file.toString(), includeSubFolder, fileList);
						continue;
					}
					if (file.isFile()) {
						fileList.add(file);
					}
				}
			}
		}

		return fileList;
	}
	
	public static File[] sortBy(File files[]) {
		try {
			if (files != null && files.length > 0) {
				for (int i = 0; i < files.length; i++) {
					File file1 = files[i];
					if (i != files.length - 1) {
						for (int j = i + 1; j < files.length; j++) {
							File file2 = files[j];
							String file1Name = file1.getName().substring(0, 20);
							String file2Name = file2.getName().substring(0, 20);
							if (file1Name.compareTo(file2Name) > 0) {
								File temp = file1;
								file1 = file2;
								file2 = temp;
							}
						}
					}
				}

			}
			return files;
		} catch (Exception e) {
			log.error("[MPA] error occurred:IEUtils sortBy method error!");
			return files;
		}

	}
	
	/**
	 * 
	 * @title monitorFolder
	 * @Description monitor the folder
	 * @param folderName
	 * @throws Exception
	 */
	public static List<File> scanFolder(String folderName) throws Exception {
		File folder = new File(folderName);
		List<File> resultFileList = new ArrayList<File>();

		if (!folder.exists()) {
			log.warn("The folder: " + folder + " doesn't exist!");
			return resultFileList;
		}

		if (!folder.isDirectory()) {
			log.warn("The folder: " + folder + " is not a Directory!");
			return resultFileList;
		}

		File[] files = folder.listFiles();
		if (files == null || files.length < 1) {
			log.warn("The folder: " + folder + " is a Empty directory!");
			return resultFileList;
		}

		if (files != null) {
			
			files = sortBy(files);
			for (File file : files) {
				
				if (file != null) {
					log.debug("\tLoop file [" + file.getAbsolutePath() + "]");
					if (!file.isFile()) {
						log.debug("The file [" + file.getAbsolutePath() + "] is not a File");
						continue;
					}
					long lastModifiedTime = file.lastModified();
					log.debug("The file [" + file.getAbsolutePath() + "] lastModifiedTime: " + lastModifiedTime);
					// if (System.currentTimeMillis() - lastModifiedTime < 2000)
					// continue;
					resultFileList.add(file);
				}
			}
		}
		log.debug("resultFileList size: " + resultFileList);
		return resultFileList;
	}
	
	/**
	 * move file to Directory
	 * 
	 * @param srcFile
	 * @param backupFolder
	 * @throws Exception
	 */
	public static File moveToDirectory(File srcFile, String backupFolder) throws Exception {
		// String errorFolder = fce.getErrorFolder();

		String fileAbPath = srcFile.getAbsolutePath();
		String fileDir = srcFile.getParent();
		String fileName = srcFile.getName();

		if (StringUtils.isEmpty(backupFolder)) {
			backupFolder = fileDir;
		}

		File destFile = new File(backupFolder);

		if(!destFile.exists()){
			destFile.mkdirs();
		}
		
		// String backupFileName = backupFolder + File.separator + fileName;
		File backupFile = new File(backupFolder, fileName);

		if (backupFile.exists()) {
			backupFile.delete();
		}

		log.info("--------------   fileDir: " + fileDir);
		log.info("--------------fileAbPath: " + fileAbPath);
		log.info("--------------  fileName: " + fileName);
		
		if(srcFile.exists()){
			FileUtils.moveToDirectory(srcFile, destFile, true);
		}
		
		log.info("Move " + srcFile + " to " + destFile);

		return new File(backupFolder, fileName);
	}

}
