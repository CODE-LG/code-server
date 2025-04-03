package codel.member.presentation.response

import codel.member.domain.MemberStatus

data class MemberLoginResponse(
    val memberStatus: MemberStatus,
)
