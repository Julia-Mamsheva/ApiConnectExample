package com.example.apiconnect.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.apiconnect.api.Repository
import kotlinx.coroutines.flow.MutableStateFlow
import com.example.apiconnect.model.Result
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

/** ViewModel - один из наиболее важных классов компонентов архитектуры Android Jetpack,
    поддерживающих данные для компонентов пользовательского интерфейса.
    Его назначение - хранить данные, связанные с пользовательским интерфейсом, и управлять ими.
    Более того, его основная функция заключается в поддержании целостности и позволяет обслуживать данные во время изменений
    конфигурации, таких как поворот экрана.
    Любое изменение конфигурации на устройствах Android приводит к воссозданию всей активности приложения.*/
class ViewModelMain(private val repository: Repository): ViewModel() {
    /** Поток изменяемых состояний (MutableStateFlow) - это поток, который может быть как собран, так и преобразован,
        и у него есть изменяемое состояние, которое может быть изменено сборщиком. Это означает,
        что вы можете использовать поток изменяемых состояний для представления состояния представления
        и обновления его на основе пользовательского ввода или других событий.*/
    private val _rickAndMorty = MutableStateFlow<List<Result>>(emptyList())
    /**
    StateFlow в Kotlin ведёт себя так же, как LiveData, за исключением того,
    что LiveData имеет осведомлённость о жизненном цикле и не требует начального значения.
    *
    Чтобы создать поток StateFlow, нужно создать экземпляр MutableStateFlow с обязательным начальным значением.
    Затем вызвать asStateFlow() на этом экземпляре, чтобы преобразовать его в StateFlow,
    из которого можно собирать изменения состояния.*/
    val rickAndMorty = _rickAndMorty.asStateFlow()

    /** Каналы (Channel) позволяют передавать потоки данных*/
    private val _showErrorToastChannel = Channel<Boolean>()
    val showErrorToastChannel = _showErrorToastChannel.receiveAsFlow()

    init {
        /** View ModelScope - это расширение Kotlin, предоставляемое библиотекой компонентов архитектуры Android.
            Оно позволяет разработчикам легко управлять жизненным циклом сопрограмм, запускаемых в рамках ViewModel.
            Это особенно полезно для длительно выполняющихся фоновых задач, которые должны быть отменены при уничтожении ViewModel,
            поскольку это гарантирует отмену сопрограмм и надлежащую очистку их ресурсов.
         *
            Launch — это построитель корутин в пакете kotlinx.coroutines.
            Он применяется, когда не нужно возвращать результат из корутины и когда её надо выполнять одновременно с другим кодом.*/
        viewModelScope.launch {
            //Далее мы с помощью ранее созданнхы метод, получаем данные и обробатываем их
            repository.getCharacter().collect { result ->
                when(result) {
                    /** Instanceof (is) - это оператор на языке java, здесь, в Kotlin, мы использовали is и !
                        это ключевые слова для выполнения операций, таких как instanceof,
                        то есть тип свойства доступен или нет, это функция, подобная способу проверки типа,
                        для проверки типа конкретного экземпляра или других различных переменных во время выполнения,
                        для разделения рабочего процесса.*/
                    is com.example.apiconnect.api.Result.Error -> {
                        _showErrorToastChannel.send(true)
                    }
                    is com.example.apiconnect.api.Result.Success -> {
                        result.data?.let { character ->
                            _rickAndMorty.update { character.results }
                        }
                    }
                }
            }
        }
    }
}