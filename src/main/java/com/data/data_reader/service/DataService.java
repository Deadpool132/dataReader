package com.data.data_reader.service;

import com.data.data_reader.entity.Employee;
import com.data.data_reader.repository.DataRepository;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@Service
public class DataService {

    @Autowired
    private DataRepository dataRepository;

    private static Employee employee;

    public List<Employee> getAllEmployee() {
        readEmployeeExcel();
        return (List<Employee>) dataRepository.findAll();
    }

    public void readEmployeeExcel() {
        File file = new File("C:/Users/vgarg9/Spring/Data.xlsx");
        try {
            FileInputStream inputStream = new FileInputStream(file);
            Workbook baeuldungWorkBook = new XSSFWorkbook(inputStream);
            for (Sheet sheet : baeuldungWorkBook) {
                int firstRow = sheet.getFirstRowNum();
                int lastRow = sheet.getLastRowNum();
                for (int index = firstRow; index <= lastRow; index++) {
                    Row row = sheet.getRow(index);
                    employee = new Employee();
                    for (int cellIndex = row.getFirstCellNum(); cellIndex < row.getLastCellNum(); cellIndex++) {
                        Cell cell = row.getCell(cellIndex, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                        getCellValue(cell, cellIndex);
                    }
                    dataRepository.save(employee);
                }
            }
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void getCellValue(Cell cell, int cellIndex) {
        CellType cellType = cell.getCellType().equals(CellType.FORMULA)
                ? cell.getCachedFormulaResultType() : cell.getCellType();
        if (cellType.equals(CellType.STRING)) {
            String cellValue = cell.getStringCellValue();
            setCellValue(cellValue, cellIndex);
        }
        if (cellType.equals(CellType.NUMERIC)) {
            double cellValue = cell.getNumericCellValue();
            setCellValue(cellValue, cellIndex);
        }
        if (cellType.equals(CellType.BOOLEAN)) {
            boolean cellValue = cell.getBooleanCellValue();
            setCellValue(cellValue, cellIndex);
        }
    }

    public static void setCellValue(Object cellValue, int cellIndex) {
        if (cellIndex == 0) {
            employee.setId((Double) cellValue);
        }
        if (cellIndex == 1) {
            employee.setName((String) cellValue);
        }
        if (cellIndex == 2) {
            employee.setSalary((Double) cellValue);
        }
    }

}
