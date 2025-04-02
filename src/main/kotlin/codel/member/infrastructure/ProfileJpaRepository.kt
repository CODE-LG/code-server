package codel.member.infrastructure

import codel.member.infrastructure.entity.ProfileEntity
import org.springframework.data.jpa.repository.JpaRepository

interface ProfileJpaRepository : JpaRepository<ProfileEntity, Long>
