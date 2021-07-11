package uz.digid.myverdi.ui.login

import androidx.lifecycle.MutableLiveData
import uz.digid.myverdi.data.model.request.PhoneAuthRequest
import uz.digid.myverdi.rx.SchedulerProvider
import uz.digid.myverdi.source.DataRepository
import uz.digid.myverdi.ui.base.BaseViewModel
import uz.digid.myverdi.utils.util.SingleLiveEvent

class VerifyPhoneNumberViewModel(val dataSource: DataRepository, val scheduler: SchedulerProvider) :
    BaseViewModel() {
    val dataLoading = MutableLiveData(false)
    val showMessage = MutableLiveData<String>()
    val verifyPhoneNumber = SingleLiveEvent<Unit>()

    fun verifyPhoneNumber(number: String) {
        if(dataLoading.value == true) return
        dataLoading.value = true
        val disposable = dataSource.sendCode(PhoneAuthRequest(number), "ru")
            .subscribeOn(scheduler.io())
            .doFinally { dataLoading.postValue(false) }
            .subscribe({ response ->
                if (response.code == 112) {
                    verifyPhoneNumber.postValue(Unit)
                } else {
                    showMessage.postValue(response.message)
                }
            }, { error ->
                showMessage.postValue(error.message)
                error.printStackTrace()
            })
        composeDisposable.add(disposable)
    }

}