package uz.digid.myverdi.ui.login

import io.reactivex.disposables.CompositeDisposable
import uz.digid.myverdi.data.model.request.PhoneAuthRequest
import uz.digid.myverdi.di.Injection

class PhoneVerification(private val callBack: SmsVerificationCallBack) {
    private val dataSource = Injection.providerRepository()
    private val scheduler = Injection.providerSchedulerProvider()
    private val composeDisposable = CompositeDisposable()

    fun onCleared() {
        composeDisposable.dispose()
    }
    fun verifyPhoneNumber(number: String) {
        val disposable = dataSource.sendCode(PhoneAuthRequest(number), "ru")
            .subscribeOn(scheduler.io())
            .subscribe({ response ->
                if (response.code == 112) {
                    callBack.onSuccess(response.message)
                } else {
                    callBack.failedPhoneNumber(response.message)
                }
            }, { error ->
                error.message?.let { callBack.failedPhoneNumber(it) }
                error.printStackTrace()
            })
        composeDisposable.add(disposable)
    }
    interface SmsVerificationCallBack {
        fun onSuccess(message: String)
        fun failedPhoneNumber(message: String)
    }
}