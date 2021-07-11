package uz.digid.myverdi.data.model.request

import uz.digid.myverdi.data.model.info.ModelServiceInfo

class ModelPersonAnswere (
    val requestGuid: String,
    val modelServiceInfo: ModelServiceInfo,
    val modelMobileData: ModelMobileData,
    val signString: String
        )