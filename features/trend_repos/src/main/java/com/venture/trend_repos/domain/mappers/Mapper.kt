package com.venture.trend_repos.domain.mappers

interface Mapper<From, To> {
    fun map(raw: From): To
    fun mapList(rawList: List<From>): List<To> {
        return rawList.map(::map)
    }
}