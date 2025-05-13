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
import java.util.ArrayList;
import java.util.List;

@Service
public class QuickSelectService {

  public static double quickSelect(List<Double> nums, int left, int right, int k) {
    if (left == right) return nums.get(left);

    int pivotIndex = partition(nums, left, right);

    if (k == pivotIndex) {
      return nums.get(k);
    } else if (k < pivotIndex) {
      return quickSelect(nums, left, pivotIndex - 1, k);
    } else {
      return quickSelect(nums, pivotIndex + 1, right, k);
    }
  }

  private static int partition(List<Double> nums, int left, int right) {
    double pivot = nums.get(right);
    int i = left;
    for (int j = left; j < right; j++) {
      if (nums.get(j) <= pivot) {
        swap(nums, i, j);
        i++;
      }
    }
    swap(nums, i, right);
    return i;
  }

  private static void swap(List<Double> nums, int i, int j) {
    double temp = nums.get(i);
    nums.set(i, nums.get(j));
    nums.set(j, temp);
  }
}
