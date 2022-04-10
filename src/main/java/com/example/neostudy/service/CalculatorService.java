package com.example.neostudy.service;

import com.example.neostudy.exception.DivisionByZero;
import com.example.neostudy.exception.InvalidDataType;
import com.example.neostudy.exception.InvalidOperationType;
import org.springframework.stereotype.Service;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Service
public class CalculatorService {
    public float calculation(String type_operation, String number_first, String number_second) throws InvalidOperationType, DivisionByZero, InvalidDataType {
        // Попытка введенных чисел конвертировать в float, при неудаче выброс исключения о неккоректном типе данных.
        float number_first_converted;
        float number_second_converted;
        try {
            number_first_converted = Float.parseFloat(number_first);
            number_second_converted = Float.parseFloat(number_second);
        } catch (NumberFormatException e){
            throw new InvalidDataType("Некорректный тип данных");
        }
        // Если отправка операции не в Url encode
        if (!type_operation.equals("/") && !type_operation.equals("+")){
            type_operation = URLEncoder.encode(type_operation, StandardCharsets.UTF_8);
        }
        switch (type_operation){
            case "+":
                return number_first_converted + number_second_converted;
            case "-":
                return number_first_converted - number_second_converted;
            case "*":
                return number_first_converted * number_second_converted;
            case "/":
                if (number_first_converted == 0 || number_second_converted == 0){
                    throw new DivisionByZero("Деление на ноль невозможно");
                }
                return number_first_converted / number_second_converted;
            default:
                throw new InvalidOperationType("Неправильный тип операции");
        }
    }
}
