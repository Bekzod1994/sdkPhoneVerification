package uz.digid.myverdi.ui.splash
import androidx.lifecycle.LiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import uz.digid.myverdi.source.DataRepository
import uz.digid.myverdi.ui.base.BaseViewModel
import uz.digid.myverdi.utils.util.SingleLiveEvent

class SplashViewModel constructor(private val dataSource: DataRepository) : BaseViewModel() {
    private val _deviceSerialNumber = SingleLiveEvent<String>()
    val deviceSerialNumber: LiveData<String> get() = _deviceSerialNumber

    private val _phoneNumber = SingleLiveEvent<String>()
    val phoneNumber: LiveData<String> get() = _phoneNumber

    fun getUserData() {
        CoroutineScope(Dispatchers.IO).launch {
            _deviceSerialNumber.postValue(dataSource.getSerialNumber())
            _phoneNumber.postValue(dataSource.getUserPhoneNumber())
        }
    }
}
