package codel.presentation.request

import codel.domain.OauthType

data class MemberSavedRequest(
    val oauthType: OauthType,
    val oauthId: String,
)
