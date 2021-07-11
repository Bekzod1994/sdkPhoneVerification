package uz.digid.myverdi.source

import uz.digid.myverdi.source.pref.PrefDataRepository
import uz.digid.myverdi.source.remote.RemoteDataRepository

interface DataRepository: RemoteDataRepository, PrefDataRepository {
}