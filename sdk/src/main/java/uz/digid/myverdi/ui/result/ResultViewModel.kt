package uz.digid.myverdi.ui.result

import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import uz.digid.myverdi.api.ResponseData
import uz.digid.myverdi.data.model.info.ModelPersonAnswere
import uz.digid.myverdi.data.model.request.PassportInfoRequest
import uz.digid.myverdi.rx.SchedulerProvider
import uz.digid.myverdi.source.DataRepository
import uz.digid.myverdi.ui.base.BaseViewModel
import uz.digid.myverdi.utils.util.SingleLiveEvent

class ResultViewModel constructor(private val dataSource: DataRepository, private val scheduler: SchedulerProvider) : BaseViewModel() {
    val dataLoading = MutableLiveData(false)
    val showMessage = SingleLiveEvent<String>()
    val registration = SingleLiveEvent<ModelPersonAnswere>()

    fun registration(data: PassportInfoRequest) {
        dataLoading.value = true
        val disposable = dataSource.registration(data)
            .subscribeOn(scheduler.io())
            .doFinally { dataLoading.postValue(false) }
            .subscribe({ data: ResponseData<ModelPersonAnswere> ->
                if (data.response != null) {
                    when (data.code) {
                        0 -> {
                            registration.postValue(data.response!!)
                        }
                        106 -> {
                            showMessage.postValue(data.message)
                        }
                        else -> {
                            showMessage.postValue(data.message)
                        }
                    }
                } else {
                    showMessage.postValue(data.message)
                }
            }, { error ->
                showMessage.postValue(error.message)
                error.printStackTrace()
            })
        composeDisposable.add(disposable)
    }

    fun setPublicKey(key: String) {
            dataSource.setPubKey(key)
    }

    fun setDeviceSerialNumber(serialNumber: String) {
            dataSource.setSerialNumber(serialNumber)
    }

}