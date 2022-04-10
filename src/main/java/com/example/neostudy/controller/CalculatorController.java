package com.example.neostudy.controller;

import com.example.neostudy.exception.DivisionByZero;
import com.example.neostudy.exception.InvalidDataType;
import com.example.neostudy.exception.InvalidOperationType;
import com.example.neostudy.service.CalculatorService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(description = "Контроллер для калькулятора")
public class CalculatorController {

    @Autowired
    private CalculatorService calculatorService;

    @GetMapping("/")
    @ApiOperation("Получение типа операции и двух чисел")
    public ResponseEntity calculate(@Parameter(description = "Тип операции (+ || - || * || /)") String type_operation,
                                    @Parameter(description = "Первое число") String number_first,
                                    @Parameter(description = "Второе число") String number_second){
        try {
            return ResponseEntity.ok().body(calculatorService.calculation(type_operation, number_first, number_second));
        } catch (InvalidOperationType | DivisionByZero | InvalidDataType e){
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e){
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }
}
