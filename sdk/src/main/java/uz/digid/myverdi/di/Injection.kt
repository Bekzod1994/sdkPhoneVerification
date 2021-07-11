package uz.digid.myverdi.di


import android.content.Context
import uz.digid.myverdi.api.ApiClient
import uz.digid.myverdi.api.ApiService
import uz.digid.myverdi.source.DataManager
import uz.digid.myverdi.source.DataRepository
import uz.digid.myverdi.source.pref.PrefDataManager
import uz.digid.myverdi.source.remote.RemoteDataManager
import uz.digid.myverdi.source.remote.RemoteDataRepository
import uz.digid.myverdi.rx.AppSchedulerProvider
import uz.digid.myverdi.rx.SchedulerProvider

object Injection {
    private val apiService: ApiService = ApiClient.build()
    private val remoteDataRepository: RemoteDataRepository = RemoteDataManager(apiService)
    private val scheduler: SchedulerProvider = AppSchedulerProvider()
    private lateinit var prefDataRepository: PrefDataManager
    private val dataRepository : DataRepository by lazy {  DataManager(remoteDataRepository,
        prefDataRepository)
    }
    fun providerApiService(): ApiService {
        return apiService
    }
    fun providerRepository(): DataRepository {
        return dataRepository
    }
    fun providerSchedulerProvider(): SchedulerProvider {
        return scheduler
    }

    fun providerDataRepository(context: Context) : PrefDataManager
    {
        return if (::prefDataRepository.isInitialized )
            prefDataRepository
        else {
            prefDataRepository = PrefDataManager(context)
            prefDataRepository
        }
    }


}