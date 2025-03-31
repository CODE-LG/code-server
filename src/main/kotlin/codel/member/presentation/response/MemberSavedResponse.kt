package codel.member.presentation.response

import codel.member.domain.MemberStatus

data class MemberSavedResponse(
    val memberStatus: MemberStatus,
)
