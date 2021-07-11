package uz.digid.myverdi.data.model.request

import uz.digid.myverdi.BuildConfig
import uz.digid.myverdi.data.model.info.ModelServiceInfo

data class PassportInfoRequest(
    val requestGuid: String,
    val modelPersonPassport: ModelPersonRequest?,
    val modelPersonPhoto: ModelPersonPhotoRequest?,
    val modelServiceInfo: ModelServiceInfo,
    val signString: String?,
    val clientPubKey: String,
    val modelMobileData: ModelMobileData,
    val appId: String = "uz.digid.myverdi"

)