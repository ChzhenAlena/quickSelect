package com.example.quickSelect.service;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;

@Service
public class NthSmallestService {

  public double findNthSmallestNumber(String path, Integer n) {
    List<Double> numbers = new ArrayList<>();

    try (FileInputStream fis = new FileInputStream(path);
         Workbook workbook = path.endsWith(".xlsx")
             ? new XSSFWorkbook(fis)
             : new HSSFWorkbook(fis)) {

      Sheet sheet = workbook.getSheetAt(0);
      for (Row row : sheet) {
        Cell cell = row.getCell(0);
        if (cell != null && cell.getCellType() == CellType.NUMERIC) {
          numbers.add(cell.getNumericCellValue());
        }
      }
    } catch (FileNotFoundException e) {
      throw new RuntimeException("Файл не найден");
    } catch (IOException e) {
      throw new RuntimeException("Ошибка чтения файла");
    }

    List<Double> uniqueNumbers = new ArrayList<>(new LinkedHashSet<>(numbers)); // Удаляем дубликаты
    if (uniqueNumbers.size() < n) {
      throw new IllegalArgumentException("N превышает количество уникальных чисел в столбце");
    }

    return QuickSelectService.quickSelect(
        uniqueNumbers,
        0,
        uniqueNumbers.size() - 1,
        n - 1
    );
  }
}
