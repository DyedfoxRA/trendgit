package com.venture.favorite_repos.domain.mapper

import com.venture.core.domain.model.Owner
import com.venture.core.domain.model.Repo
import com.venture.favorite_repos.data.model.RepoEntity


class RepoEntityToRepoMapperImpl : RepoEntityToRepoMapper {
    override fun map(raw: RepoEntity): Repo {
        return Repo(
            id = raw.id,
            name = raw.name,
            description = raw.description,
            htmlUrl = raw.htmlUrl,
            stargazersCount = raw.stargazersCount,
            fullName = raw.fullName,
            language = raw.language,
            forksCount = raw.forksCount,
            owner = Owner(
                login = raw.owner.login,
                avatarUrl = raw.owner.avatarUrl
            )
        )
    }
}

interface RepoEntityToRepoMapper :
    com.venture.core.domain.mapper.Mapper<RepoEntity, Repo>