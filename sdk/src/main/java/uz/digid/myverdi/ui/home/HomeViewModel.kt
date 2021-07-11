package uz.digid.myverdi.ui.home

import androidx.databinding.ObservableBoolean
import androidx.lifecycle.MutableLiveData
import uz.digid.myverdi.rx.SchedulerProvider
import uz.digid.myverdi.source.DataRepository
import uz.digid.myverdi.ui.base.BaseViewModel

class HomeViewModel constructor(
    private val dataSource: DataRepository,
    private val scheduler: SchedulerProvider
) : BaseViewModel() {
    fun deleteUser() {
        dataSource.setSerialNumber("")
    }

    val dataLoading = ObservableBoolean(false)
    val showMessage = MutableLiveData<String>()
    val phoneNumber = dataSource.getUserPhoneNumber()

}