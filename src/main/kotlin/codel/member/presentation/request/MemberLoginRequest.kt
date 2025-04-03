package codel.member.presentation.request

import codel.member.domain.OauthType

data class MemberLoginRequest(
    val oauthType: OauthType,
    val oauthId: String,
)
