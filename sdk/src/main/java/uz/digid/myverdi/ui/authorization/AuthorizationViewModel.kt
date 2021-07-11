package uz.digid.myverdi.ui.authorization

import androidx.lifecycle.MutableLiveData
import uz.digid.myverdi.data.model.info.ModelPersonAnswere
import uz.digid.myverdi.data.model.request.PassportInfoRequest
import uz.digid.myverdi.rx.SchedulerProvider
import uz.digid.myverdi.source.DataRepository
import uz.digid.myverdi.ui.base.BaseViewModel
import uz.digid.myverdi.utils.util.SingleLiveEvent

class AuthorizationViewModel constructor(
        private val dataSource: DataRepository,
        private val scheduler: SchedulerProvider
) : BaseViewModel() {
    val dataLoading = MutableLiveData(false)
    val showMessage = SingleLiveEvent<String>()
    val authorization = SingleLiveEvent<ModelPersonAnswere>()

    fun verification(data: PassportInfoRequest) {
        dataLoading.value = true

        val disposable = dataSource.verification(data)
            .subscribeOn(scheduler.io())
            .doFinally { dataLoading.postValue(false) }
            .subscribe({ data ->
                if (data.response != null) {
                    authorization.postValue(data.response!!)
                } else {
                    showMessage.postValue(data.message)
                }
            }, { error ->
                showMessage.postValue(error.message)
                error.printStackTrace()
            })
        composeDisposable.add(disposable)
    }

}