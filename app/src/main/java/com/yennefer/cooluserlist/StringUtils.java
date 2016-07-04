package com.yennefer.cooluserlist;

/**
 * Created by Yennefer on 03.07.2016.
 * Класс, содержащий методы для работы со строками
 */
public class StringUtils {

    // Метод возвращает строку, начинающуюся с заглавной буквы
    public static String fromCapitalLetter(String text) {
        return text.substring(0, 1).toUpperCase() + text.substring(1);
    }

    // Метод возвращает строку, отмеченную маркером "текст-слева-направо"
    // для корректного отображения текста, содержащего арабские шрифты
    public static String leftToRightText(String text) {
        return String.format("\u200E%1$s", text);
    }
}
