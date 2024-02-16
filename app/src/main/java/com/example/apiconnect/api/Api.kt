package com.example.apiconnect.api

import com.example.apiconnect.model.RickAndMorty
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

/**
 Интерфейсы в Kotlin могут содержать объявления абстрактных методов, а также методы с реализацией.
 *
 Главное отличие интерфейсов от абстрактных классов заключается в невозможности хранения переменных экземпляров.
 Они могут иметь свойства, но те должны быть либо абстрактными, либо предоставлять реализацию методов доступа.
 */
interface Api {
    /**Kotlin suspend - это ключевое слово и концепция в языке программирования Kotlin,
     которая позволяет выполнять асинхронное программирование и упрощает работу с сопрограммами.
     Когда функция помечена ключевым словом suspend,
     ее можно приостановить и возобновить позже, не блокируя поток выполнения.*/
    @GET("api/character")
    suspend fun getCharacter(): RickAndMorty //В данно методе, мы получаем всю информацию из API, методом GET. В качестве ответа получаем структуру RickAndMorty

    @POST("api/SendCode")
    suspend fun sendCodeEmail(@Header("User-email") email: String):String
    //Константа, к которой можно обращаться из любой точки кода, не создавая объект класса (example: Api.BASE_URL)
    companion object {
        val BASE_URL = "https://rickandmortyapi.com/"
        val BASE_URL_SMARTLAB = "https://iis.ngknn.ru/NGKNN/МамшеваЮС/MedicMadlab/"
    }
}