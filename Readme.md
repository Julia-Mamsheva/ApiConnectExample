<h1 align="center">Пример подключения API к проекту </h1>


1.  1 этапом, после создания проекта. Вам необходимо подключить библиотеки в `build.gradle`:
```
    // Retrofit
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation("com.squareup.okhttp3:okhttp:4.11.0")
    implementation("com.squareup.okhttp3:logging-interceptor:4.10.0")
    // Coil
    implementation("io.coil-kt:coil-compose:2.4.0")
```
<br> И подключить интернет в файле `AndroidManifest`:
```
     <uses-permission android:name="android.permission.INTERNET"/>
```
2. Далее нужно создать модель подключаемой API. Сделать это можно с помощью `JSON To Kotlin Class ​(JsonToKotlinClass)​`
![image](https://github.com/Julia-Mamsheva/ApiConnectExample/assets/129171406/59a36b16-ea9d-4e19-861a-e06cca992354) <br> <br> <br>
Нужно вставить необходимый объект Json, который необходимо пероброзовать в классы kotlin. И дать наименование модели.  <br> <br> <br>
![image](https://github.com/Julia-Mamsheva/ApiConnectExample/assets/129171406/6ffe380f-9d60-4b93-95bb-c65f320fc46d)
3. Далее нужно создать <a href = "https://github.com/Julia-Mamsheva/ApiConnectExample/blob/master/app/src/main/java/com/example/apiconnect/api/Api.kt">interface<a>, в котором будут отписываться все методы со с константной ссылкой на данную API 
4. Следующим этапом создать interface <a href = "https://github.com/Julia-Mamsheva/ApiConnectExample/blob/master/app/src/main/java/com/example/apiconnect/api/Repository.kt">Repository</a>, в котором необходимо объявить методы и переопрделить их в <a href = "https://github.com/Julia-Mamsheva/ApiConnectExample/blob/master/app/src/main/java/com/example/apiconnect/api/RepositoryImpl.kt">RepositoryImpl</a>
5. В качестве статической переменной создадим универсальное подключение к API в <a href = "https://github.com/Julia-Mamsheva/ApiConnectExample/blob/master/app/src/main/java/com/example/apiconnect/api/RetrofitInstance.kt">RetrofitInstance </a>
6. Далее создадим<a href ="https://github.com/Julia-Mamsheva/ApiConnectExample/blob/master/app/src/main/java/com/example/apiconnect/viewmodel/ViewModelMain.kt"> ViewModel</a>, в которой будут соданы переменные для получения данных и в дальнейшим их использование в UI и метод подключения к API
7. И последним этам получение данных на стороне UI и распрасивание данных на странице в <a href = "https://github.com/Julia-Mamsheva/ApiConnectExample/blob/master/app/src/main/java/com/example/apiconnect/MainActivity.kt">MainActivity</a>. (Также необходимо объявить provider ViewModel)
