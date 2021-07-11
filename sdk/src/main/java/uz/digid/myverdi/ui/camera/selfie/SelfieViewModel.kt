package uz.digid.myverdi.ui.camera.selfie

import androidx.databinding.ObservableBoolean
import uz.digid.myverdi.source.remote.RemoteDataRepository
import uz.digid.myverdi.rx.SchedulerProvider
import uz.digid.myverdi.ui.base.BaseViewModel

class SelfieViewModel constructor(private val dataSource: RemoteDataRepository, private val scheduler: SchedulerProvider) : BaseViewModel() {
    val dataLoading = ObservableBoolean(false)
   /* val listData = MutableLiveData<List<Car>>()
    val dataUpdate= MutableLiveData<Car>()

    init {
        loadData()
    }

    private fun loadData() {
        val disposable = dataSource.getActive()
            .subscribeOn(scheduler.io())
            .doFinally { dataLoading.set(false) }
            .subscribe({ response ->
                if (response.isNotEmpty()) {
                    listData.postValue(response)
                }
            }, { error ->
                error.printStackTrace()
            })
        composeDisposable.add(disposable)
    }

    fun update(data: Car) {
        data.isDeleted =false
        val disposable = dataSource.update(data)
            .subscribeOn(scheduler.io())
            .doFinally { dataLoading.set(false) }
            .subscribe({
                dataUpdate.postValue(data)
            }, { error ->
                error.printStackTrace()
            })
        composeDisposable.add(disposable)
    }*/


}