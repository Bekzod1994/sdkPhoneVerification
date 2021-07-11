package uz.digid.myverdi.source.remote

import io.reactivex.Single
import uz.digid.myverdi.api.ApiClient
import uz.digid.myverdi.api.ApiService
import uz.digid.myverdi.api.ResponseData
import uz.digid.myverdi.data.model.info.ModelPersonAnswere
import uz.digid.myverdi.data.model.outhorization.PassportDataResponse
import uz.digid.myverdi.data.model.request.AllReadRequest
import uz.digid.myverdi.data.model.request.PassportInfoRequest
import uz.digid.myverdi.data.model.request.PhoneAuthCodeRequest
import uz.digid.myverdi.data.model.request.PhoneAuthRequest
import uz.digid.myverdi.data.model.response.AllReadResponse

class RemoteDataManager (private val service: ApiService)
 : RemoteDataRepository {
    override fun sendCode(
        data: PhoneAuthRequest,
        lang: String
    ): Single<ResponseData<String>> {
        return service.sendCode("Basic ZGlnaWQ6ZGlnaWQyMDE5", data)
    }
    override fun verifyCode(
        data: PhoneAuthCodeRequest,
        lang: String
    ): Single<ResponseData<String>> {
        return service.verifyCode("Basic ZGlnaWQ6ZGlnaWQyMDE5", data)
    }

    override fun registration(data: PassportInfoRequest): Single<ResponseData<ModelPersonAnswere>> {
     return service.registration( "Basic aXBvdGVrYV9tb2JpbGU6UmxtX2lwb3Rla2E=",data)
    }
    override fun verification(data: PassportInfoRequest): Single<ResponseData<ModelPersonAnswere>> {
     return service.verification( "Basic aXBvdGVrYV9tb2JpbGU6UmxtX2lwb3Rla2E=",data)
    }
 }

