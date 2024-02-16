package com.example.apiconnect.api

import com.example.apiconnect.model.RickAndMorty
import kotlinx.coroutines.flow.Flow

/**
Интерфейсы в Kotlin могут содержать объявления абстрактных методов, а также методы с реализацией.
 *
Главное отличие интерфейсов от абстрактных классов заключается в невозможности хранения переменных экземпляров.
Они могут иметь свойства, но те должны быть либо абстрактными, либо предоставлять реализацию методов доступа.
 * */
interface Repository {
    /**Kotlin suspend - это ключевое слово и концепция в языке программирования Kotlin,
    которая позволяет выполнять асинхронное программирование и упрощает работу с сопрограммами.
    Когда функция помечена ключевым словом suspend,
    ее можно приостановить и возобновить позже, не блокируя поток выполнения.*/


    /** Kotlin Flow - это библиотека реактивных потоков в Kotlin, которая предоставляет комплексную платформу для асинхронного
    и неблокирующего программирования. Он предназначен для простой и эффективной обработки сложных потоков данных и событий,
    что делает его идеальным инструментом для создания реактивных приложений,
    обработки пользовательского ввода и реализации обработки данных в режиме реального времени.*/

    //Метод для получения данных из API. "Завернутых" в пользоватлеьский класс обработки данных
    suspend fun getCharacter():Flow<Result<RickAndMorty>>
    suspend fun sendCodeEmail(email:String):Flow<Result<String>>
}