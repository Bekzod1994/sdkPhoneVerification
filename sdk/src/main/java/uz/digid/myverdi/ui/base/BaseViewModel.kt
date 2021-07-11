package uz.digid.myverdi.ui.base

import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable

abstract class BaseViewModel : ViewModel() {
    protected val composeDisposable = CompositeDisposable()

    override fun onCleared() {
        composeDisposable.dispose()
        super.onCleared()
    }

}