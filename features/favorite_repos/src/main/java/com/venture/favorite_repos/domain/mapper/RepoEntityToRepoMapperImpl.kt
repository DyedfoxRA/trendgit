package com.venture.favorite_repos.domain.mapper

import com.venture.favorite_repos.data.model.RepoEntity
import com.venture.core.domain.mapper.Mapper
import com.venture.core.domain.results.model.Owner
import com.venture.core.domain.results.model.Repo

class RepoEntityToRepoMapperImpl : RepoEntityToRepoMapper {
    override fun map(raw: RepoEntity): com.venture.core.domain.results.model.Repo {
        return com.venture.core.domain.results.model.Repo(
            id = raw.id,
            name = raw.name,
            description = raw.description,
            htmlUrl = raw.htmlUrl,
            stargazersCount = raw.stargazersCount,
            fullName = raw.fullName,
            language = raw.language,
            forksCount = raw.forksCount,
            owner = com.venture.core.domain.results.model.Owner(
                login = raw.owner.login,
                avatarUrl = raw.owner.avatarUrl
            )
        )
    }
}

interface RepoEntityToRepoMapper :
    com.venture.core.domain.mapper.Mapper<RepoEntity, com.venture.core.domain.results.model.Repo>