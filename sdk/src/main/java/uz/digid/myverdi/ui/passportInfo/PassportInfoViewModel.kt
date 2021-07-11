package uz.digid.myverdi.ui.passportInfo

import androidx.lifecycle.MutableLiveData
import uz.digid.myverdi.api.ResponseData
import uz.digid.myverdi.data.apimodel.AuthorizationResponse
import uz.digid.myverdi.rx.SchedulerProvider
import uz.digid.myverdi.source.DataRepository
import uz.digid.myverdi.ui.base.BaseViewModel

class PassportInfoViewModel constructor(private val dataSource: DataRepository, private val scheduler: SchedulerProvider) : BaseViewModel() {
    val dataLoading = MutableLiveData(false)
    val showMessage = MutableLiveData<String>()
    val authorization = MutableLiveData<ResponseData<AuthorizationResponse>>()

}