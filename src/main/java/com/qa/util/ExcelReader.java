package com.qa.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.logging.Logger;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.qa.data.Users;
import com.qa.vo.User;

public class ExcelReader {
	
	private static Logger logger=Logger.getLogger(ExcelReader.class.getName());
	private static final String XLS="xls";
	private static final  String XLSX="xlsx";
	

	public static Workbook getWorkBook(InputStream inputStream,String fileType) throws IOException {
		Workbook workBook =null;
		if(fileType.equalsIgnoreCase(XLS)) {
			workBook =new HSSFWorkbook(inputStream);
		}else if(fileType.equalsIgnoreCase(XLSX)) {
			workBook = new XSSFWorkbook(inputStream);
		}
		return workBook;
		
	}
	
//	public static Row readExcel(String fileName,int rowIndex) {
//		Workbook workbook = null;
//        FileInputStream inputStream = null;
//        String fileType=fileName.substring(fileName.lastIndexOf(".")+1, fileName.length());
//        
//        //获取excel 文件
//        File excelFile=new File(fileName);
//        if(!excelFile.exists()) {
//        	 logger.warning("指定的文件不存在");
//        	 return null;
//        }
//        //获取excel工作簿
//        try {
//			inputStream = new FileInputStream(excelFile);
//			workbook = ExcelReader.getWorkBook(inputStream, fileType);
//			//读取excel文件中的数据
//			//List<ExcelDataVO> resultDataList = parseExcel(workbook, sheetIndex);
//			Row data=parseExcel(workbook, rowIndex); 
//			return  data ;
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//            try {
//                if (null != workbook) {
//                    workbook.close();
//                }
//                if (null != inputStream) {
//                    inputStream.close();
//                }
//            } catch (Exception e) {
//                logger.warning("关闭数据流出错！错误信息：" + e.getMessage());
//                return null;
//            }
//        }
//        
//		return null;
//	}
	public static Sheet readExcel(String fileName) {
		Workbook workbook = null;
		Sheet sheet = null;
        FileInputStream inputStream = null;
        String fileType=fileName.substring(fileName.lastIndexOf(".")+1, fileName.length());
        
        //获取excel 文件
        File excelFile=new File(fileName);
        if(!excelFile.exists()) {
        	 logger.warning("指定的文件不存在");
        	 return null;
        }
        //获取excel工作簿
        try {
			inputStream = new FileInputStream(excelFile);
			workbook = ExcelReader.getWorkBook(inputStream, fileType);
			//读取excel文件中的数据
			//List<ExcelDataVO> resultDataList = parseExcel(workbook, sheetIndex);
			sheet = workbook.getSheetAt(0);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
            try {
                if (null != workbook) {
                    workbook.close();
                }
                if (null != inputStream) {
                    inputStream.close();
                }
            } catch (Exception e) {
                logger.warning("关闭数据流出错！错误信息：" + e.getMessage());
                return null;
            }
        }
		
		return sheet;
		
	}
	
	/*private static Row parseExcel(Workbook workbook,int rowIndex) {
			Sheet sheet=workbook.getSheetAt(0);
			//int firstRowNum=sheet.getFirstRowNum();
			Row row=sheet.getRow(rowIndex);
			
			if(null == row) {
				logger.warning("解析excel失败,第一行没有读取到任何数据");
			}
			
			int rowStart=firstRowNum + 1;
			
			int rowEnd=sheet.getPhysicalNumberOfRows();
			
			Row row = null;
			for (int rowNum=rowStart;rowNum < rowEnd;rowNum ++) {
				 row=sheet.getRow(rowNum);
				if(null == row) {
					continue;
				}
				User resultData = convertRowToData(row);
				if (null == resultData) {
		           logger.warning("第 " + row.getRowNum() + "行数据不合法，已忽略！");
		           continue;
		        }
				String  key=row.getCell(0).getStringCellValue();
				data.put(key, resultData);
			}
				
		return row;
	}*/
	/*private static User convertRowToData(Row row) {
		User resultData = new User();
		Cell cell;
		int cellNum=1;
		cell = row.getCell(cellNum++);
		String username= convertCellValueToString(cell);
		resultData.setUsername(username);
		
		cell = row.getCell(cellNum++);
		String password = convertCellValueToString(cell);
	    resultData.setPassword(password);
	    
	    cell = row.getCell(cellNum++);
	    String code = convertCellValueToString(cell);
	    resultData.setCode(code);;
		return resultData;
	}*/
	
     
	private static String convertCellValueToString(Cell cell) {
		if(cell==null){
	         return null;
	     }
		String returnValue = null;
		switch (cell.getCellType()) {
		case NUMERIC:
			Double doubleValue=cell.getNumericCellValue();
			DecimalFormat df = new DecimalFormat("0");
            returnValue = df.format(doubleValue);
			break;
		case STRING:    
            returnValue = cell.getStringCellValue();
            break;
		case BOOLEAN:
			Boolean booleanValue = cell.getBooleanCellValue();
            returnValue = booleanValue.toString();
			break;
		case BLANK:
			break;
		case FORMULA:
			returnValue = cell.getCellFormula();
			break;
		case ERROR:
			break;
		default:
            break;
		}
		return returnValue;
	}
	
	public static void main(String args[]) {
		Sheet   sheet =	readExcel("E:\\webgoat\\test.xlsx");
		System.out.println(sheet.getRow(0).getCell(1));
		System.out.println(sheet.getRow(1).getCell(1));
		System.out.println(sheet.getRow(2).getCell(1));
	}
	
}
