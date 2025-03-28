package codel.member.presentation.request

import codel.member.domain.OauthType

data class MemberSavedRequest(
    val oauthType: OauthType,
    val oauthId: String,
)
