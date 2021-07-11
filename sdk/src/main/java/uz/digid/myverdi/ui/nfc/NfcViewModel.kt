package uz.digid.myverdi.ui.nfc

import uz.digid.myverdi.rx.SchedulerProvider
import uz.digid.myverdi.source.remote.RemoteDataRepository
import uz.digid.myverdi.ui.base.BaseViewModel

class NfcViewModel constructor(
    private val dataSource: RemoteDataRepository,
    private val scheduler: SchedulerProvider
) : BaseViewModel() {
}