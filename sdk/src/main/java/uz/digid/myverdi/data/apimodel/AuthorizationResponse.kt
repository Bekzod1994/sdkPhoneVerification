package uz.digid.myverdi.data.apimodel

data class AuthorizationResponse(
    val livenessScore: LivenessScore?,
        val similarityScore: SimilarityScore?
)