package me.lewei.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import me.lewei.core.ProcerConstants;
import me.lewei.obj.ReadContext;
import me.lewei.obj.WriteContext;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

public class CommonExcelUtil {
	private POIFSFileSystem fs;
	private HSSFWorkbook wb;
	private HSSFSheet sheet;
	private HSSFRow row;

	private int fromColumn;
	private int toColumn;

	public List<Map<String, File>> readExcelFileMaps(InputStream is, String path) {
		List<Map<String, File>> fileMaps = new ArrayList<Map<String, File>>();
		Map<String, File> content = new HashMap<String, File>();
		try {
			fs = new POIFSFileSystem(is);
			wb = new HSSFWorkbook(fs);
		} catch (IOException e) {
			e.printStackTrace();
		}
		sheet = wb.getSheetAt(0);
		int rowNum = sheet.getLastRowNum();
		row = sheet.getRow(0);
		int colNum = row.getPhysicalNumberOfCells();
		for (int i = 1; i <= rowNum; i++) {
			row = sheet.getRow(i);
			int j = 0;
			while (j < colNum) {
				String fromValue = "";
				if (j == fromColumn) {
					fromValue = getCellFormatValue(row.getCell(j)).trim();
				}
				File f = new File(path + fromValue);
				if(f.exists())
					content.put(fromValue, f);
				else
					
				
				j++;
			}
			fileMaps.add(content);
		}
		return fileMaps;
	}
	
	public Map<String, String> readExcelFileNewName(InputStream is) {
		Map<String, String> content = new HashMap<String, String>();
		try {
			fs = new POIFSFileSystem(is);
			wb = new HSSFWorkbook(fs);
		} catch (IOException e) {
			e.printStackTrace();
		}
		sheet = wb.getSheetAt(0);
		int rowNum = sheet.getLastRowNum();
		row = sheet.getRow(0);
		int colNum = row.getPhysicalNumberOfCells();
		for (int i = 1; i <= rowNum; i++) {
			row = sheet.getRow(i);
			int j = 0;
			while (j < colNum) {
				String fromValue = "";
				String toValue = "";
				if(j == fromColumn){
					fromValue = getCellFormatValue(row.getCell(j)).trim();
				}
				if(j == toColumn){
					toValue = getCellFormatValue(row.getCell(j)).trim();
				}
				content.put(fromValue, toValue);
				
				j++;
			}
		}
		return content;
	}
	
	private List<String> getColumnContex(File f, int i){
		String seq = "" + i;
		String prefix = "";
		String fullName = f.getName();
		String space = "";
		String suffix = fullName.substring(fullName.lastIndexOf(ProcerConstants.SYMBOL.DOT));
		String midName = fullName.replace(suffix, "");
		String toFullName = "D" + (i + 2) + "&E" + (i + 2) + "&F" + (i + 2);
		
		List<String> result = new ArrayList<String>();
		result.add(seq);
		result.add(fullName);
		result.add(space);
		result.add(prefix);
		result.add(midName);
		result.add(suffix);
		result.add(toFullName);
		
		return result;
	}
	
	public void creatExcel4ReadedFile(ReadContext readContex) throws Exception {
		List<File> files = readContex.getFiles();
		
		HSSFWorkbook wb = new HSSFWorkbook();
		HSSFSheet sheet = wb.createSheet();
		sheet.setDefaultColumnWidth(15);
		
		row = sheet.createRow(0);
		HSSFRow header = sheet.createRow(0);
		HSSFCell cell0 = header.createCell(0);
		HSSFCell cell1 = header.createCell(1);
		HSSFCell cell2 = header.createCell(2);
		HSSFCell cell3 = header.createCell(3);
		HSSFCell cell4 = header.createCell(4);
		HSSFCell cell5 = header.createCell(5);
		HSSFCell cell6 = header.createCell(6);
		
		cell0.setCellValue("Seq");
		cell1.setCellValue("'Full_Name");
		this.setFromColumn(1);
		cell2.setCellValue("(From >> To)");
		cell3.setCellValue("Prefix");
		cell4.setCellValue("Mid_Name");
		cell5.setCellValue("Suffix");
		cell6.setCellValue("'Full_Name");
		this.setToColumn(6);
		
		for (int i = 0; i < files.size(); i++) {
			
			HSSFRow row = sheet.createRow(i + 1);
			File f = files.get(i);
			List<String> columns = getColumnContex(f, i);
			
			for (int j = 0; j < columns.size(); j++) {
				HSSFCell cell = row.createCell(j);
				cell.setCellValue(columns.get(j));
				
				if(j == columns.size() - 1){
					cell.setCellFormula(columns.get(j));
				}
			}
		}

		FileOutputStream fout = new FileOutputStream(readContex.getInputPath() + ProcerConstants.WORKING_FILE_NAME);
		wb.write(fout);
		fout.close();
	}
	
	public void writeExcel(WriteContext writeContex) throws Exception {
		List<Map<String, File>> dateList = writeContex.getFileMaps();
		HSSFWorkbook wb = new HSSFWorkbook();
		HSSFSheet sheet = wb.createSheet();
		HSSFCellStyle style = wb.createCellStyle();
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		style.setBorderBottom(HSSFCellStyle.BORDER_MEDIUM);
		style.setBorderTop(HSSFCellStyle.BORDER_MEDIUM);
		style.setBorderLeft(HSSFCellStyle.BORDER_MEDIUM);
		style.setBorderRight(HSSFCellStyle.BORDER_MEDIUM);

//		for (int i = 0; i < dateList.size(); i++) {
//			HSSFRow row = sheet.createRow(i);
//			List<String> list = dateList.get(i);
//			for (int j = 0; j < dateList.size(); j++) {
//				HSSFCell cell = row.createCell(j);
//				cell.setCellValue(dateList.get(j));
//				cell.setCellStyle(style);
//			}
//		}

		String filePath = writeContex.getTargetPath();
		FileOutputStream fout = new FileOutputStream(filePath);
		fout.write(wb.getBytes());
		fout.close();
	}
	
	public String getStringCellValue(HSSFCell cell) {
		String strCell = "";
		switch (cell.getCellType()) {
		case HSSFCell.CELL_TYPE_STRING:
			strCell = cell.getStringCellValue();
			break;
		case HSSFCell.CELL_TYPE_NUMERIC:
			strCell = String.valueOf(cell.getNumericCellValue());
			break;
		case HSSFCell.CELL_TYPE_BOOLEAN:
			strCell = String.valueOf(cell.getBooleanCellValue());
			break;
		case HSSFCell.CELL_TYPE_BLANK:
			strCell = "";
			break;
		default:
			strCell = "";
			break;
		}
		if (strCell.equals("") || strCell == null) {
			return "";
		}
		return strCell;
	}

	public String getCellFormatValue(HSSFCell cell) {
		String cellvalue = "";
		if (cell != null) {
			switch (cell.getCellType()) {
			case HSSFCell.CELL_TYPE_NUMERIC:
			case HSSFCell.CELL_TYPE_FORMULA: {
				if (HSSFDateUtil.isCellDateFormatted(cell)) {
					Date date = cell.getDateCellValue();
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					cellvalue = sdf.format(date);

				} else {
					cellvalue = String.valueOf(cell.getNumericCellValue());
				}
				break;
			}
			case HSSFCell.CELL_TYPE_STRING:
				cellvalue = cell.getRichStringCellValue().getString();
				break;
			default:
				cellvalue = " ";
			}
		} else {
			cellvalue = "";
		}
		return cellvalue;
	}

	public int getFromColumn() {
		return fromColumn;
	}

	public void setFromColumn(int fromColumn) {
		this.fromColumn = fromColumn;
	}

	public int getToColumn() {
		return toColumn;
	}

	public void setToColumn(int toColumn) {
		this.toColumn = toColumn;
	}
	
	

}