package uz.digid.myverdi.api

import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path
import uz.digid.myverdi.data.model.info.ModelPersonAnswere
import uz.digid.myverdi.data.model.outhorization.PassportDataResponse
import uz.digid.myverdi.data.model.request.AllReadRequest
import uz.digid.myverdi.data.model.request.PassportInfoRequest
import uz.digid.myverdi.data.model.request.PhoneAuthCodeRequest
import uz.digid.myverdi.data.model.request.PhoneAuthRequest
import uz.digid.myverdi.data.model.response.AllReadResponse


interface ApiService {
    @POST("digid-service/phone/ru/send")
    fun sendCode (@Header("Authorization") token: String, @Body data : PhoneAuthRequest): Single<ResponseData<String>>

    @POST("digid-service/phone/ru/check")
    fun verifyCode (@Header("Authorization") token: String, @Body data : PhoneAuthCodeRequest): Single<ResponseData<String>>

    @POST("digid-service/pinpp/ru/registration")
    fun registration (@Header("Authorization") token: String, @Body data : PassportInfoRequest): Single<ResponseData<ModelPersonAnswere>>

    @POST("digid-service/pinpp/ru/verification")
    fun verification (@Header("Authorization") token: String, @Body data : PassportInfoRequest): Single<ResponseData<ModelPersonAnswere>>
}