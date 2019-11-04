package com.cost168.costaudit.utils;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;



/**
 * 
 * ClassName: ExcelUtil 
 * @Description: TODO
 * @author lixiang
 * @date 2018-12-10上午9:54:42
 * @Company  广东华联软件科技有限公司
 */
public class ExcelUtil {  
	
	private static DecimalFormat df = new DecimalFormat("0.00"); 
	private static SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
    private static SimpleDateFormat time = new SimpleDateFormat("HH:mm");
    private static SimpleDateFormat dateTime = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    //标题字体大小
    private static final short TITLEFONTSIZE = 13;
    //内容字体大小
    private static final short CONTENTFONTSIZE = 11;
    private static final String TITLEFONTTYPE = "粗体";
    
    private ExcelUtil(){}
    
    /**
     * 
     * @Description: TODO
     * @param @param rowNum
     * @param @param filePath
     * @param @param sheetIndex
     * @param @return
     * @param @throws Exception   
     * @return HashMap<String,HashMap<Integer,ArrayList<Object>>>  
     * @throws
     * @author lixiang
     * @date 2018-12-10上午9:55:19
     * @Company  广东华联软件科技有限公司
     */
    
	public static HashMap<String,HashMap<Integer,ArrayList<Object>>> readExcel(int rowNum,String filePath,int sheetIndex) throws Exception{
    	Workbook wb = getWorkbook(filePath);
    	//所有sheet的数据的map
    	HashMap<String,HashMap<Integer,ArrayList<Object>>> sheetMap = new HashMap<String,HashMap<Integer,ArrayList<Object>>>();
			int sheetSize = wb.getNumberOfSheets();
			if(sheetIndex > -1 && sheetIndex <= sheetSize){
				sheetMap.put(wb.getSheetAt(sheetIndex).getSheetName(),  getSheetContent(rowNum,wb.getSheetAt(sheetIndex)));
				return sheetMap;
			}else{
				for(int i = 0;i<sheetSize;i++){
					sheetMap.put(wb.getSheetAt(i).getSheetName(),  getSheetContent(rowNum,wb.getSheetAt(i)));
				}
	    	return sheetMap;
    	}
    }
	
	/**
	 * 
	 * @Description: 生成excel文件
	 * @param @param data
	 * @param @param filePath
	 * @param @param colWidth
	 * @param @throws Exception   
	 * @return void  
	 * @throws
	 * @author lixiang
	 * @date 2018-12-10上午9:55:39
	 * @Company  广东华联软件科技有限公司
	 */
	
	public static void writeExcel(Map<String,List<List<String>>> data,String filePath,List<Integer> colWidth) throws Exception{
		Workbook wb = createExcel(data,colWidth);
		File file = new File(filePath);
		file.getParentFile().mkdirs();
		FileOutputStream fos = null;
		try{
			fos = new FileOutputStream(file);
			wb.write(fos);//开始写文件
		}catch(Exception e){
			throw new Exception("生成Excel异常",e);
		}finally {
			if(null != fos) {
				fos.flush();
				fos.close();
			}
		}
	}
	
	/**
	 * 
	 * @Description: 用于流下载文件
	 * @param @param data
	 * @param @param outputStream
	 * @param @param colWidth
	 * @param @throws Exception   
	 * @return void  
	 * @throws
	 * @author lixiang
	 * @date 2016-5-6下午5:31:22
	 * @Company  广东华联软件科技有限公司
	 */
	
	public static void writeExcel(Map<String,List<List<String>>> data,OutputStream outputStream,List<Integer> colWidth) throws Exception{
		Workbook wb = createExcel(data,colWidth);
		try{
			wb.write(outputStream);//开始写文件
		}catch(Exception e){
			throw new Exception("生成Excel异常",e);
		}finally {
			if(null != outputStream) {
				outputStream.flush();
				outputStream.close();
			}
		}
	}
	
	/**
	 * 
	 * @Description: 创建excel
	 * @param @param data
	 * @param @param colWidth
	 * @param @return
	 * @param @throws Exception   
	 * @return Workbook  
	 * @throws
	 * @author lixiang
	 * @date 2018-12-10上午9:56:25
	 * @Company  广东华联软件科技有限公司
	 */
	
	private static Workbook createExcel(Map<String,List<List<String>>> data,List<Integer> colWidth) throws Exception{
		Workbook wb = new XSSFWorkbook();//07及以上版本
		//标题的样式
		CellStyle titleStyle= wb.createCellStyle();
		//设置水平对齐方式
		titleStyle.setAlignment(CellStyle.ALIGN_CENTER);
		//设置垂直对齐方式
		titleStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
		//设置背景
		titleStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
		titleStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
		titleStyle.setBorderRight((short)1);
		Font titleFont = wb.createFont();
		//设置字体的大小
		titleFont.setFontHeightInPoints(TITLEFONTSIZE);
		//设置字体
		titleFont.setFontName(TITLEFONTTYPE);
		//设置字体颜色
		titleFont.setColor(HSSFColor.WHITE.index);
		//设置字体的粗细
		titleFont.setBoldweight(Font.BOLDWEIGHT_BOLD);
		titleStyle.setFont(titleFont);
		//内容的样式
		CellStyle contentStyle= wb.createCellStyle();
		contentStyle.setAlignment(CellStyle.ALIGN_LEFT);
		contentStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
		//设置边框
		//contentStyle.setBorderRight((short)1);
		//contentStyle.setBorderBottom((short)1);
		contentStyle.setBorderBottom(HSSFCellStyle.SOLID_FOREGROUND);  
		contentStyle.setBorderLeft(HSSFCellStyle.SOLID_FOREGROUND);  
		contentStyle.setBorderRight(HSSFCellStyle.SOLID_FOREGROUND);  
		contentStyle.setBorderTop(HSSFCellStyle.SOLID_FOREGROUND);  
		//设置自动换行
		contentStyle.setWrapText(true);
		Font contentFont = wb.createFont();
		contentFont.setFontHeightInPoints(CONTENTFONTSIZE);
		contentFont.setColor(HSSFColor.GREY_50_PERCENT.index);
		contentStyle.setFont(contentFont);
		for(Entry<String, List<List<String>>> sheetMap : data.entrySet()){
			Sheet sheet = wb.createSheet(sheetMap.getKey());
			List<List<String>> rowList = sheetMap.getValue();
			for (int i = 0;i<rowList.size();i++) {
				Row row = sheet.createRow(i);
				List<String> cellList = rowList.get(i);
				if(!(null != cellList && null != colWidth && cellList.size() == colWidth.size()))
					throw new Exception("列数与列宽度数组大小不一致");
				for (int j = 0;j<cellList.size();j++) {
					Cell cell = row.createCell(j);
					if(0 == i)
						cell.setCellStyle(titleStyle);
					else{
						cell.setCellStyle(contentStyle);
					}
					cell.setCellValue(String.valueOf(cellList.get(j)));
				}
			}
			if(null != colWidth && colWidth.size() > 0){
				for(int i = 0;i<colWidth.size();i++){
					sheet.setColumnWidth(i, colWidth.get(i)*10);
				}
			}
		}
		return wb;
	}
	
	/**
	 * 
	 * @Description: 获取sheet的内容
	 * @param @param rowNum
	 * @param @param sheet
	 * @param @return   
	 * @return HashMap<Integer,ArrayList<Object>>  
	 * @throws
	 * @author lixiang
	 * @date 2018-12-10上午9:56:29
	 * @Company  广东华联软件科技有限公司
	 */
	
    private static HashMap<Integer,ArrayList<Object>> getSheetContent(int rowNum,Sheet sheet){
    	 //每行所有单元格数据list
    	ArrayList<Object> cellList = null;   
    	 //一个sheet中所有的的行的map
    	HashMap<Integer,ArrayList<Object>> rowMap = new HashMap<Integer,ArrayList<Object>>();
    	//从非标题行开始读取数据
    	for(int i = rowNum;i<sheet.getPhysicalNumberOfRows();i++){
			Row row = sheet.getRow(i);
			cellList = new ArrayList<Object>();
			int rowSpace = 0;
			for(int j = row.getFirstCellNum();j<sheet.getRow(rowNum < 0 ? 0 : rowNum-1).getLastCellNum();j++){
				Object cellValue = getCellValue(row.getCell(j));
				cellList.add(cellValue);
				if(cleanSpace(cellValue.toString()).equals("")) ++rowSpace;
			}
			if(rowSpace < row.getLastCellNum()-row.getFirstCellNum()) rowMap.put(row.getRowNum(), cellList);
		}
    	return rowMap;
    }
    
    /**
     * 
     * @Description: 去除空格
     * @param @param param
     * @param @return   
     * @return String  
     * @throws
     * @author lixiang
     * @date 2018-12-10上午9:56:48
     * @Company  广东华联软件科技有限公司
     */
	public static String cleanSpace(String param){
		if(null == param || 0 == param.length()) return "";
		param = param.replaceAll(" ","").replaceAll(" ","");
		return param;
	}	
    /**
     * 
     * @Description: 获取cell的值
     * @param @param cell
     * @param @return   
     * @return Object  
     * @throws
     * @author lixiang
     * @date 2016-5-6下午5:30:21
     * @Company  广东华联软件科技有限公司
     */
     
	private static Object getCellValue(Cell cell) {
		Object cellValue = "";
		if(null != cell){
			switch (cell.getCellType()) {
			case Cell.CELL_TYPE_BLANK:
				cellValue = "";
				break;
			case Cell.CELL_TYPE_BOOLEAN:
				cellValue = cell.getBooleanCellValue();
				break;
			case Cell.CELL_TYPE_NUMERIC:
				double value = cell.getNumericCellValue(); 
				short format = cell.getCellStyle().getDataFormat();
				
			    if(format == 14 || format == 31 || format == 57 || format == 58){
			    	cellValue = date.format(DateUtil.getJavaDate(value));
			    }else if (format == 20 || format == 32) {  
			    	cellValue = time.format(DateUtil.getJavaDate(value));
			    }else if(format == 22 || format == 176/* || format == 178*/){
			    	cellValue = dateTime.format(DateUtil.getJavaDate(value));
			    }else{
			    	cellValue = df.format(value);
			    }
				break;
			case Cell.CELL_TYPE_STRING:
				cellValue = cell.getStringCellValue();
				break;
			case Cell.CELL_TYPE_FORMULA:
				cellValue = cell.getNumericCellValue();
				break;
			}
		}
		return cellValue;
	}
    
	/**
	 * 
	 * @Description: 获取Workbook
	 * @param @param filePath
	 * @param @return
	 * @param @throws Exception   
	 * @return Workbook  
	 * @throws
	 * @author lixiang
	 * @date 2018-12-10上午9:56:58
	 * @Company  广东华联软件科技有限公司
	 */
	 
    private static Workbook getWorkbook(String filePath) throws Exception{
    	FileInputStream fis = new FileInputStream(filePath);
    	String ext = filePath.substring(filePath.lastIndexOf(".")+1);
    	Workbook wb = null;
    	try{
	    	if(ext.equalsIgnoreCase("xls")){//03版本及以下
	    		wb = new HSSFWorkbook(fis);
	    	}else if(ext.equalsIgnoreCase("xlsx")){//07版本及以上
	    		wb = new XSSFWorkbook(fis);
	    	}
    	}catch(Exception e){
    		throw new Exception("系统未知的Excel版本",e);
    	}finally{
    		if(null != fis) fis.close();
    	}
    	return wb;
    }
    
    /**
     * 
     * @Description: 读取并生成excel文件
     * @param @param startRowNum
     * @param @param data
     * @param @param oldPath
     * @param @param newPath
     * @param @param request
     * @param @param response
     * @param @throws Exception   
     * @return void  
     * @throws
     * @author lixiang
     * @date 2018-12-10上午9:57:29
     * @Company  广东华联软件科技有限公司
     */
	public static void copyExcel(int startRowNum,Map<String,List<List<String>>> data,String oldPath,String newPath,HttpServletRequest request,HttpServletResponse response ) throws Exception{
		Workbook wb = getWorkbook(oldPath);
		//内容的样式
		CellStyle contentStyle= wb.createCellStyle();
		contentStyle.setAlignment(CellStyle.ALIGN_LEFT);
		contentStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
		//设置边框
		contentStyle.setBorderBottom(HSSFCellStyle.SOLID_FOREGROUND);  
		contentStyle.setBorderLeft(HSSFCellStyle.SOLID_FOREGROUND);  
		contentStyle.setBorderRight(HSSFCellStyle.SOLID_FOREGROUND);  
		contentStyle.setBorderTop(HSSFCellStyle.SOLID_FOREGROUND);  
		for(Entry<String, List<List<String>>> sheetMap : data.entrySet()){
			Sheet sheet = wb.getSheet(sheetMap.getKey()) != null ? wb.getSheet(sheetMap.getKey()) : wb.createSheet(sheetMap.getKey());
			List<List<String>> rowList = sheetMap.getValue();
			for (int i = 0;i<rowList.size();i++) {
				Row row = sheet.getRow(startRowNum+i) != null ? sheet.getRow(startRowNum+i) : sheet.createRow(startRowNum+i);
				List<String> cellList = rowList.get(i);
				for (int j = 0;j<cellList.size();j++) {

					String cellValue = cellList.get(j);
					Cell cell = row.getCell(j) != null ? row.getCell(j) : row.createCell(j);
					if(cell.getCellType()==Cell.CELL_TYPE_BLANK) {
						cell.setCellValue(cellValue);
					}
					if(0 == i){
						
					}
					else{
						cell.setCellStyle(contentStyle);
					}
				}
			}
		}
		File file = new File(newPath);
		file.getParentFile().mkdirs();
		FileOutputStream fos = null;
		try{
			fos = new FileOutputStream(file);
			wb.write(fos);//开始写文件
			downLoad(newPath,"",request,response);
		}catch(Exception e){
			throw new Exception("生成Excel异常",e);
		}finally {
			if(null != fos) {
				fos.flush();
				fos.close();
			}
		}
	}


    public static void copyExcel1(int startRowNum,int startRowNum1,int startRowNum2,Map<String,List<List<String>>> data,Map<String,List<List<String>>> data1,Map<String,List<List<String>>> data2,String oldPath,String newPath,HttpServletRequest request,HttpServletResponse response ) throws Exception{
        Workbook wb = getWorkbook(oldPath);
        //内容的样式
        CellStyle contentStyle= wb.createCellStyle();
        contentStyle.setAlignment(CellStyle.ALIGN_LEFT);
        contentStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
        //设置边框
        contentStyle.setBorderBottom(HSSFCellStyle.SOLID_FOREGROUND);
        contentStyle.setBorderLeft(HSSFCellStyle.SOLID_FOREGROUND);
        contentStyle.setBorderRight(HSSFCellStyle.SOLID_FOREGROUND);
        contentStyle.setBorderTop(HSSFCellStyle.SOLID_FOREGROUND);
        for(Entry<String, List<List<String>>> sheetMap : data.entrySet()){
            Sheet sheet = wb.getSheet(sheetMap.getKey()) != null ? wb.getSheet(sheetMap.getKey()) : wb.createSheet(sheetMap.getKey());
            List<List<String>> rowList = sheetMap.getValue();
            for (int i = 0;i<rowList.size();i++) {
                Row row = sheet.getRow(startRowNum+i) != null ? sheet.getRow(startRowNum+i) : sheet.createRow(startRowNum+i);
                List<String> cellList = rowList.get(i);
                for (int j = 0;j<cellList.size();j++) {

                    String cellValue = cellList.get(j);
                    Cell cell = row.getCell(j) != null ? row.getCell(j) : row.createCell(j);
                    if(cell.getCellType()==Cell.CELL_TYPE_BLANK) {
                        cell.setCellValue(cellValue);
                    }
                }
            }
        }
        for(Entry<String, List<List<String>>> sheetMap : data1.entrySet()){
            Sheet sheet = wb.getSheet(sheetMap.getKey()) != null ? wb.getSheet(sheetMap.getKey()) : wb.createSheet(sheetMap.getKey());
            List<List<String>> rowList = sheetMap.getValue();
            for (int i = 0;i<rowList.size();i++) {
                Row row = sheet.getRow(startRowNum1+i) != null ? sheet.getRow(startRowNum1+i) : sheet.createRow(startRowNum1+i);
                List<String> cellList = rowList.get(i);
                for (int j = 0;j<cellList.size();j++) {

                    String cellValue = cellList.get(j);
                    Cell cell = row.getCell(j) != null ? row.getCell(j) : row.createCell(j);
                    if(cell.getCellType()==Cell.CELL_TYPE_BLANK) {
                        cell.setCellValue(cellValue);
                    }
                }
            }
        }
		for(Entry<String, List<List<String>>> sheetMap : data2.entrySet()){
			Sheet sheet = wb.getSheet(sheetMap.getKey()) != null ? wb.getSheet(sheetMap.getKey()) : wb.createSheet(sheetMap.getKey());
			List<List<String>> rowList = sheetMap.getValue();
			for (int i = 0;i<rowList.size();i++) {
				Row row = sheet.getRow(startRowNum2+i) != null ? sheet.getRow(startRowNum2+i) : sheet.createRow(startRowNum2+i);
				List<String> cellList = rowList.get(i);
				for (int j = 0;j<cellList.size();j++) {

					String cellValue = cellList.get(j);
					Cell cell = row.getCell(j) != null ? row.getCell(j) : row.createCell(j);
					if(cell.getCellType()==Cell.CELL_TYPE_BLANK) {
						cell.setCellValue(cellValue);
					}
				}
			}
		}
        File file = new File(newPath);
        file.getParentFile().mkdirs();
        FileOutputStream fos = null;
        try{
            fos = new FileOutputStream(file);
            wb.write(fos);//开始写文件
        }catch(Exception e){
            throw new Exception("生成Excel异常",e);
        }finally {
            if(null != fos) {
                fos.flush();
                fos.close();
            }
        }
    }

	/**
	 * 
	 * @Description: 下载
	 * @param @param filePath
	 * @param @param fileName
	 * @param @param request
	 * @param @param response
	 * @param @throws Exception   
	 * @return void  
	 * @throws
	 * @author lixiang
	 * @date 2018-12-10上午9:57:47
	 * @Company  广东华联软件科技有限公司
	 */
	public static void downLoad(String filePath,String fileName,HttpServletRequest request,HttpServletResponse response ) throws Exception{
		filePath = filePath.replace("/", "\\").replace("\\", "/");
		FileInputStream fis = null;
		OutputStream os = null;
		try {
			File file = new File(filePath);
			if(!file.exists()){
				return;
			}
			String name = fileName != null && fileName.length() > 0 ? fileName : file.getName();  
	        String userAgent = request.getHeader("User-Agent");  
	        byte[] bytes = userAgent.contains("MSIE") ? name.getBytes() : name.getBytes("UTF-8");  
	        name = new String(bytes, "ISO-8859-1"); 
			response.setContentType("application/x-download");
			response.setHeader("Content-disposition", String.format("attachment; filename=\"%s\"", name));
			byte[] buf = new byte[2048];
			
			fis = new FileInputStream(file);
			os = response.getOutputStream();
			for(int i = 0;-1 != (i = fis.read(buf));){
				os.write(buf, 0, i);
			}
		} catch (Exception e) {
		}finally{
			 if(null != fis) fis.close();
			 if(null != os){
				 os.flush();
				 os.close();
			 }
		}
	}
	/**
	 * 
	 * @Description: 文件大小显示
	 * @param @param size
	 * @param @return   
	 * @return String  
	 * @throws
	 * @author lixiang
	 * @date 2018-12-10上午9:58:59
	 * @Company  广东华联软件科技有限公司
	 */
	public static String calculatedSize(long size){
		long kb=1024,mb=1024*1024;
		if(size>mb){
			return (size/mb)+"MB";
		}else if(size>kb){
			return (size/kb)+"KB";
		}else{
			return size+"B";
		}
	}
	public static Double getValue(Cell cell) {
		try {
			if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
				Double a = cell.getNumericCellValue();
				return a;
			} else if (cell.getCellType() == Cell.CELL_TYPE_STRING) {
				Double a = Double.valueOf(cell.getStringCellValue());
				return a;
			} else if (cell.getCellType() == Cell.CELL_TYPE_FORMULA) {
				cell.setCellType(Cell.CELL_TYPE_STRING);
				Double a = Double.valueOf(cell.getStringCellValue());
				return a;
			} else if (cell.getCellType() == Cell.CELL_TYPE_BLANK) {
				return Double.valueOf(0.0);
			} else {
				Double a = Double.valueOf(cell.getStringCellValue());
				return a;
			}
		} catch (NumberFormatException n) {
			return 0.0;
		}

	}
    
} 