package Running;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.sl.usermodel.Hyperlink;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFHyperlink;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import Common.Information;



public class WriteExcelSheet implements Information{
	static XSSFWorkbook wb;
	static XSSFSheet sheet;
	static File f;
	static FileInputStream fis;
public WriteExcelSheet(String path){
	try {
		fetchWorkbook(path);
		/*
		 * f= new File(path); fis= new FileInputStream(f); wb= new XSSFWorkbook(fis);
		 * System.out.println("path"+f);
		 */
		
		//org.apache.poi.ss.usermodel.Workbook wb = WorkbookFactory.create(fis);
		
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
}
public static void fetchWorkbook(String path) throws IOException {
	if(fis==null) {
		f=new File(path);
		fis=new FileInputStream(f);
		wb=new XSSFWorkbook(fis);
	}
}
public void removeCellvalue(String index,int row, int cell){
	sheet=wb.getSheet(index);
	//org.apache.poi.ss.usermodel.Sheet sheet = wb.getSheet(index);
   Row r=sheet.getRow(row);
	Cell cell1= r.getCell(cell);
	if(cell1!=null){
		r.removeCell(cell1);
	}
}
public void writeData(String index,int row, int cell, String result, boolean status){
	try {
		CellStyle style=wb.createCellStyle();
		style.setWrapText(true);
		Font font=wb.createFont();
		font.setBold(true);
		style.setAlignment(XSSFCellStyle.ALIGN_CENTER);
		
		if(result.equalsIgnoreCase(Information.PASS)){
		font.setColor(IndexedColors.WHITE.getIndex());
		style.setFont(font);
		style.setFillForegroundColor(IndexedColors.GREEN.getIndex());
		style.setFillPattern(CellStyle.SOLID_FOREGROUND);
		System.out.println("This is pass");
		}
		else if(result.equalsIgnoreCase(Information.UNKNOWN) || result.equalsIgnoreCase(Information.WRONG)){
			font.setColor(IndexedColors.WHITE.getIndex());
			style.setFont(font);
			style.setFillForegroundColor(IndexedColors.BLACK.getIndex());
			style.setFillPattern(CellStyle.SOLID_FOREGROUND);
			System.out.println("This is unknown");
		}
		else if(result.equalsIgnoreCase(Information.WARNING)){
			font.setColor(IndexedColors.BLACK.getIndex());
			style.setFont(font);
			style.setFillForegroundColor(IndexedColors.ORANGE.getIndex());
			style.setFillPattern(CellStyle.SOLID_FOREGROUND);
		}
		else if(result.equalsIgnoreCase(Information.SKIP)){
			font.setColor(IndexedColors.BLACK.getIndex());
			style.setFont(font);
			style.setFillForegroundColor(IndexedColors.SKY_BLUE.getIndex());
			style.setFillPattern(CellStyle.SOLID_FOREGROUND);
		}
		else if(result.equalsIgnoreCase(Information.INFO)){
			style.setFont(font);
			style.setFillForegroundColor(IndexedColors.BLUE.getIndex());
			style.setFillPattern(CellStyle.SOLID_FOREGROUND);
		}
		else if(result.equalsIgnoreCase(Information.FAIL)){
			style.setFont(font);
			style.setFillForegroundColor(IndexedColors.RED.getIndex());
			style.setFillPattern(CellStyle.SOLID_FOREGROUND);
		}
		else{
			if(status){
				style.setFillForegroundColor(IndexedColors.AUTOMATIC.getIndex());
				font.setColor(IndexedColors.GREEN.getIndex());
				style.setFont(font);
			}
			else if(result.contains(Information.WARNING)){
				style.setFillForegroundColor(IndexedColors.AUTOMATIC.getIndex());
				font.setColor(IndexedColors.ORANGE.getIndex());
				style.setFont(font);
			}
			else if(result.contains(Information.INFO)){
				style.setFillForegroundColor(IndexedColors.AUTOMATIC.getIndex());
				font.setColor(IndexedColors.BLUE.getIndex());
				style.setFont(font);
			}
			else if(result.contains("Keyword")){
				style.setFillForegroundColor(IndexedColors.AUTOMATIC.getIndex());
				font.setColor(IndexedColors.PINK.getIndex());
				font.setUnderline(XSSFFont.U_SINGLE);
				style.setFont(font);
			}
			else{
				style.setFillForegroundColor(IndexedColors.AUTOMATIC.getIndex());
				font.setColor(IndexedColors.RED.getIndex());
				style.setFont(font);
				}
		}
		sheet=wb.getSheet(index);
		Row r=sheet.getRow(row);
		Cell cell1= r.createCell(cell);
		System.out.println("Result is "+result);
		cell1.setCellValue(result);
		cell1.setCellStyle(style);
		if(result.contains("Keyword")){
		CreationHelper createHelper = wb.getCreationHelper();
	    XSSFHyperlink hyperlink= (XSSFHyperlink) createHelper.createHyperlink(Hyperlink.LINK_DOCUMENT);
	    hyperlink.setLocation("'SUMMARY'!A1");
	    hyperlink.setTooltip("This is Invalid Keyword. Please click on LINK. Find correct KEYWORD in SUMMARY sheet. Thank you!!!");
	    cell1.setHyperlink(hyperlink);
		}
		FileOutputStream dest= new FileOutputStream(f);
		wb.write(dest);
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
}
public void countOfResult(String sheetName,int row, int cell, int count){
	try {
		sheet=wb.getSheet(sheetName);
		Cell cell1= sheet.getRow(row).createCell(cell);
		cell1.setCellValue(count);
		FileOutputStream destination= new FileOutputStream(f);
		wb.write(destination);
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
public void writeData(String sheetName, int row, int cell, int total,String result) {
	try {
		CellStyle style=wb.createCellStyle();
		Font font=wb.createFont();
		font.setBold(true);
		
		if(result.equalsIgnoreCase("pass")){
		font.setColor(IndexedColors.WHITE.getIndex());
		style.setFont(font);
		style.setFillForegroundColor(IndexedColors.GREEN.getIndex());
		}
		else if(result.equalsIgnoreCase("Warning")){
			style.setFont(font);
			style.setFillForegroundColor(IndexedColors.LIGHT_ORANGE.getIndex());
		}
		else if(result.equalsIgnoreCase("fail")){
			style.setFont(font);
			style.setFillForegroundColor(IndexedColors.RED.getIndex());
		}
		
		style.setFillPattern(CellStyle.SOLID_FOREGROUND);
		sheet=wb.getSheet(sheetName);
		Cell cell1= sheet.getRow(row).createCell(cell);
		cell1.setCellValue(total);
		cell1.setCellStyle(style);
		FileOutputStream dest= new FileOutputStream(f);
		wb.write(dest);
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}

//Vasu Updated
public void closeResources() {
	try {
		if(wb != null)
				wb.close();
		if(fis != null)
			fis.close();
		
	} catch (IOException e) {
		e.printStackTrace();
	}
}


}
