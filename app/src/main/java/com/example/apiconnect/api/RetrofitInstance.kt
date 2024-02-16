package com.example.apiconnect.api

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object RetrofitInstance {

    /** Библиотека HttpLoggingInterceptor является частью OkHttp, но поставляется отдельно от неё.
    Перехватчик следует использовать в том случае, когда вам действительно нужно изучать логи ответов сервера.
    По сути библиотека является сетевым аналогом привычного LogCat.*/
        private val interceptor: HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
            // Подключаем перехватчик к веб-клиенту. Добавляйте его после других перехватчиков,
            // чтобы ловить все сообщения. Существует несколько уровней перехвата данных: NONE, BASIC, HEADERS, BODY.
            // Последний вариант самый информативный, пользуйтесь им осторожно.
            // При больших потоках данных информация забьёт весь экран. Используйте промежуточные варианты.
        level = HttpLoggingInterceptor.Level.BODY
    }

    /** OkHttp предоставляет простой, легкий в использовании API для выполнения HTTP‑запросов,
    включая поддержку протоколов HTTP/1.1 и HTTP/2. Библиотека поддерживает все стандартные методы HTTP
    и может легко обрабатывать несколько одновременных запросов, а также предоставляет расширенные возможности:
    кэширование запросов/ответов, объединение подключений в пул (connection pooling), аутентификация и др.*/
    private val client: OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(interceptor)
        .build()

    // Объект для запроса к серверу
    val api: Api = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(Api.BASE_URL)
        .client(client)
        .build()
        .create(Api::class.java)
    // В итоге мы получили объект Retrofit,
    // содержащий базовый URL и способность преобразовывать JSON-данные с помощью указанного конвертера Gson
    // В методе create() указываем наш класс интерфейса с запросами к сайту


    val apiSmartLab: Api = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(Api.BASE_URL_SMARTLAB)
        .client(client)
        .build()
        .create(Api::class.java)
}