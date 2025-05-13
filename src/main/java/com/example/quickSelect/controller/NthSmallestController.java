package com.example.quickSelect.controller;

import com.example.quickSelect.service.NthSmallestService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NthSmallestController {

  private final NthSmallestService service;

  public NthSmallestController(NthSmallestService service) {
    this.service = service;
  }

  @Operation(summary = "Get N-th smallest number from Excel file", description = "Finds the N-th smallest number in a column of an Excel file")
  @GetMapping("/n-smallest-number")
  public double findNthSmallestNumber(
      @Parameter(
          description = "Path to the Excel file (without quotes)",
          example = "C:/files/data.xlsx",
          schema = @Schema(type = "string", format = "path"))
          @RequestParam("path") String path,

          @Parameter(
              description = "N-th smallest number to find (1-based index)",
              example = "3",
              required = true,
              schema = @Schema(type = "integer", minimum = "1")
          )
          @RequestParam("N") Integer n)
  {
    return service.findNthSmallestNumber(path, n);
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<String> handleGenericErrors(Exception ex) {
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
        .body(ex.getMessage());
  }
}
