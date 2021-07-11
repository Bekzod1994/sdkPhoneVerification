package uz.digid.myverdi.source.remote

import io.reactivex.Single
import uz.digid.myverdi.api.ResponseData
import uz.digid.myverdi.data.model.info.ModelPersonAnswere
import uz.digid.myverdi.data.model.outhorization.PassportDataResponse
import uz.digid.myverdi.data.model.request.AllReadRequest
import uz.digid.myverdi.data.model.request.PassportInfoRequest
import uz.digid.myverdi.data.model.request.PhoneAuthCodeRequest
import uz.digid.myverdi.data.model.request.PhoneAuthRequest
import uz.digid.myverdi.data.model.response.AllReadResponse


interface RemoteDataRepository {
    fun sendCode (data : PhoneAuthRequest, lang: String): Single<ResponseData<String>>
    fun verifyCode (data : PhoneAuthCodeRequest, lang: String): Single<ResponseData<String>>

    fun registration ( data : PassportInfoRequest): Single<ResponseData<ModelPersonAnswere>>
    fun verification ( data : PassportInfoRequest): Single<ResponseData<ModelPersonAnswere>>

}