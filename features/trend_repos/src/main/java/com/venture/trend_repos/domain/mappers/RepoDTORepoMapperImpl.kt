package com.venture.trend_repos.domain.mappers

import com.venture.network.model.RepoDTO
import com.venture.trend_repos.domain.model.Owner
import com.venture.trend_repos.domain.model.Repo

class RepoDTORepoMapperImpl : RepoDTORepoMapper {
    override fun map(raw: RepoDTO): Repo {
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
                login = raw.ownerDTO.login,
                avatarUrl = raw.ownerDTO.avatarUrl
            )
        )
    }
}

interface  RepoDTORepoMapper: Mapper<RepoDTO, Repo>