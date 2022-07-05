package ru.oldjew.bankapp.model;

import lombok.Data;

/**
 * Класс для отправки ответов JSON
 */
@Data
public class ResponseJson {


    private final int value;  //значение или статус ответа (-1) - неудача, (1) - успешно
    private String message; //значение или текст ошибки

    public ResponseJson(int value) {
        this.value = value;
    }

    public ResponseJson(int value, String message) {
        this.value = value;
        this.message = message;
    }
}
