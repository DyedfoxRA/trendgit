package com.venture.favorite_repos.domain.mapper

import com.venture.favorite_repos.data.model.OwnerEntity
import com.venture.favorite_repos.data.model.RepoEntity
import com.venture.core.domain.mapper.Mapper
import com.venture.core.domain.results.model.Repo

class RepoToRepoEntityMapperImpl : RepoToRepoEntityMapper {
    override fun map(raw: com.venture.core.domain.results.model.Repo): RepoEntity {
        return RepoEntity(
            id = raw.id,
            name = raw.name,
            description = raw.description,
            htmlUrl = raw.htmlUrl,
            stargazersCount = raw.stargazersCount,
            fullName = raw.fullName,
            language = raw.language,
            forksCount = raw.forksCount,
            owner = OwnerEntity(
                login = raw.owner.login,
                avatarUrl = raw.owner.avatarUrl
            )
        )
    }
}

interface RepoToRepoEntityMapper :
    com.venture.core.domain.mapper.Mapper<com.venture.core.domain.results.model.Repo, RepoEntity>