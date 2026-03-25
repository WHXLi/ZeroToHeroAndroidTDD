package ru.easycode.zerotoheroandroidtdd.core

import androidx.lifecycle.ViewModel
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import ru.easycode.zerotoheroandroidtdd.core.viewmodel.ViewModelFactory
import ru.easycode.zerotoheroandroidtdd.core.viewmodel.ViewModelProvider

class ViewModelFactoryTest {

    private lateinit var provideViewModel: FakeProvideViewModel
    private lateinit var factory: ViewModelFactory

    @Before
    fun setup() {
        provideViewModel = FakeProvideViewModel.Base()
        factory = ViewModelFactory.Base(viewModelProvider = provideViewModel)
    }

    @Test
    fun test_cached_same() {
        factory.createViewModel(viewModelClass = FakeViewModelOne::class.java)
        provideViewModel.checkCalled(listOf(FakeViewModelOne::class.java))

        factory.createViewModel(viewModelClass = FakeViewModelOne::class.java)
        provideViewModel.checkCalled(listOf(FakeViewModelOne::class.java))
    }

    @Test
    fun test_called_other() {
        factory.createViewModel(viewModelClass = FakeViewModelOne::class.java)
        provideViewModel.checkCalled(listOf(FakeViewModelOne::class.java))

        factory.createViewModel(viewModelClass = FakeViewModelTwo::class.java)
        provideViewModel.checkCalled(
            listOf(
                FakeViewModelOne::class.java,
                FakeViewModelTwo::class.java
            )
        )
    }

    @Test
    fun test_clear_first() {
        factory.createViewModel(viewModelClass = FakeViewModelOne::class.java)
        provideViewModel.checkCalled(listOf(FakeViewModelOne::class.java))

        factory.createViewModel(viewModelClass = FakeViewModelTwo::class.java)
        provideViewModel.checkCalled(
            listOf(
                FakeViewModelOne::class.java,
                FakeViewModelTwo::class.java
            )
        )

        factory.removeViewModel(viewModelClass = FakeViewModelOne::class.java)
        provideViewModel.checkCalled(
            listOf(
                FakeViewModelOne::class.java,
                FakeViewModelTwo::class.java
            )
        )

        factory.createViewModel(viewModelClass = FakeViewModelOne::class.java)
        provideViewModel.checkCalled(
            listOf(
                FakeViewModelOne::class.java,
                FakeViewModelTwo::class.java,
                FakeViewModelOne::class.java,
            )
        )
    }

    @Test
    fun test_clear_second() {
        factory.createViewModel(viewModelClass = FakeViewModelOne::class.java)
        provideViewModel.checkCalled(listOf(FakeViewModelOne::class.java))

        factory.createViewModel(viewModelClass = FakeViewModelTwo::class.java)
        provideViewModel.checkCalled(
            listOf(
                FakeViewModelOne::class.java,
                FakeViewModelTwo::class.java
            )
        )

        factory.removeViewModel(viewModelClass = FakeViewModelTwo::class.java)
        provideViewModel.checkCalled(
            listOf(
                FakeViewModelOne::class.java,
                FakeViewModelTwo::class.java
            )
        )

        factory.createViewModel(viewModelClass = FakeViewModelTwo::class.java)
        provideViewModel.checkCalled(
            listOf(
                FakeViewModelOne::class.java,
                FakeViewModelTwo::class.java,
                FakeViewModelTwo::class.java,
            )
        )
    }
}

private interface FakeProvideViewModel : ViewModelProvider.Create {

    fun checkCalled(expected: List<Class<out ViewModel>>)

    class Base : FakeProvideViewModel {

        private val list = mutableListOf<Class<out ViewModel>>()

        override fun checkCalled(expected: List<Class<out ViewModel>>) {
            assertEquals(expected, list)
        }

        override fun <T : ViewModel> createViewModel(viewModelClass: Class<T>): T {
            list.add(viewModelClass)
            return viewModelClass.getDeclaredConstructor().newInstance()
        }
    }
}

private class FakeViewModelOne : ViewModel()

private class FakeViewModelTwo : ViewModel()