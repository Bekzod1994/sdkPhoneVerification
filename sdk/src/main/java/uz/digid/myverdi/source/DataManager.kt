package uz.digid.myverdi.source

import io.reactivex.Single
import kotlinx.coroutines.flow.Flow
import uz.digid.myverdi.api.ResponseData
import uz.digid.myverdi.data.model.info.ModelPersonAnswere
import uz.digid.myverdi.data.model.outhorization.PassportDataResponse
import uz.digid.myverdi.data.model.request.AllReadRequest
import uz.digid.myverdi.data.model.request.PassportInfoRequest
import uz.digid.myverdi.data.model.request.PhoneAuthCodeRequest
import uz.digid.myverdi.data.model.request.PhoneAuthRequest
import uz.digid.myverdi.data.model.response.AllReadResponse
import uz.digid.myverdi.source.pref.PrefDataRepository
import uz.digid.myverdi.source.remote.RemoteDataRepository

class DataManager constructor(
        private val remoteDataRepo: RemoteDataRepository,
        private val pref: PrefDataRepository
) : DataRepository {

    override fun sendCode(data: PhoneAuthRequest, lang: String): Single<ResponseData<String>> =
            remoteDataRepo.sendCode(data, lang)

    override fun verifyCode(data: PhoneAuthCodeRequest, lang: String): Single<ResponseData<String>> =
            remoteDataRepo.verifyCode(data, lang)


    override fun registration(data: PassportInfoRequest): Single<ResponseData<ModelPersonAnswere>> {
        return remoteDataRepo.registration(data)
    }

    override fun verification(data: PassportInfoRequest): Single<ResponseData<ModelPersonAnswere>> {
        return remoteDataRepo.verification(data)
    }

    override fun setUserPhoneNumber(number: String): String {
       return pref.setUserPhoneNumber(number)
    }

    override fun setPubKey(key: String): String {
        return pref.setPubKey(key)
    }

    override fun setSerialNumber(serialNumber: String): String {
        return pref.setSerialNumber(serialNumber)
    }

    override fun setUserEmail(email: String): String {
        return pref.setUserEmail(email)
    }

    override fun getUserPhoneNumber(): String {
       return pref.getUserPhoneNumber()
    }

    override fun getPubKey(): String {
       return pref.getPubKey()
    }

    override fun getSerialNumber(): String {
        return pref.getSerialNumber()
    }

    override fun getUserEmail(): String {
       return pref.getUserEmail()
    }
}