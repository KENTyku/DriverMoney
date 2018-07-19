/*
 * Use and copying for commercial purposes
 * only with the author's permission
 */
package ru.kentyku.drivermoney;

/**
 *Класс описывающий логику работы приложения
 * @author kentyku
 */
public class Logic {
    void ReadingToBaze(){
        //Создание класса обработчика соединений с БД. Содержит все методы для работы с базой
    }
    
    
    void checkFile(){
        //проверка корректности загружаемого файла
    }
    
    void loadingList(){        
        //Загружаем список водителей из файла в БД программы.
        checkFile();
    }
    
    void SaveList(){
        //Сохраняем список водителей из БД программы в файл.
    }
    
    
    
}
