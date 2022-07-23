package com.lhj.test;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author lhj
 * @creat 2022-04-12-14:42
 */
public class TestPOI {
    @Test
    public void testPOI() throws IOException {
        //1.创建工作表
        XSSFWorkbook workbook = new XSSFWorkbook("D:\\hello.xlsx");
        //2.获取工作簿
        XSSFSheet sheet = workbook.getSheetAt(0);
        //3.获取行
        for (Row cells : sheet) {
            //4.获取列
            for (Cell cell : cells) {
                System.out.print(cell.getStringCellValue() + "\t");
            }
            System.out.println();
        }
        //5.关闭工作表
        workbook.close();
    }

    @Test
    public void testImportExcel() throws IOException {
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("lihongjain");

        XSSFRow row0 = sheet.createRow(0);
        row0.createCell(0).setCellValue("姓名");
        row0.createCell(1).setCellValue("年龄");
        row0.createCell(2).setCellValue("地址");

        XSSFRow row1 = sheet.createRow(1);
        row1.createCell(0).setCellValue("赵翔");
        row1.createCell(1).setCellValue("19");
        row1.createCell(2).setCellValue("西安");

        XSSFRow row2 = sheet.createRow(2);
        row2.createCell(0).setCellValue("环东");
        row2.createCell(1).setCellValue("34");
        row2.createCell(2).setCellValue("北京");

        XSSFRow row3 = sheet.createRow(3);
        row3.createCell(0).setCellValue("秦思");
        row3.createCell(1).setCellValue("25");
        row3.createCell(2).setCellValue("天津");

        FileOutputStream fileOutputStream = new FileOutputStream("D:\\lihongjian.xlsx");
        workbook.write(fileOutputStream);
        fileOutputStream.flush();
        fileOutputStream.close();
        workbook.close();

    }
}
