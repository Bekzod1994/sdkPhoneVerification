package uz.digid.myverdi.ui.identification

import uz.digid.myverdi.rx.SchedulerProvider
import uz.digid.myverdi.source.DataRepository
import uz.digid.myverdi.ui.base.BaseViewModel

class IdentificationViewModel constructor(
        private val dataSource: DataRepository,
        private val scheduler: SchedulerProvider
) : BaseViewModel() {

    fun setPhoneNumber(phoneNumber: String) {
        dataSource.setUserPhoneNumber(phoneNumber)
    }
    fun deleteUser(){
        setPhoneNumber("")
        setEmail("")
        dataSource.setSerialNumber("")
    }

    fun setEmail(email: String) {
        dataSource.setUserEmail(email)
    }
}