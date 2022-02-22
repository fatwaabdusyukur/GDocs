package com.made.core.utils

import com.made.core.data.source.local.entity.GameEntity
import com.made.core.data.source.remote.response.Games
import com.made.core.domain.model.Game
import java.lang.StringBuilder

object DataMapper {

    fun mapResponsesToEntities(list : List<Games>) : List<GameEntity> {
        val entities = ArrayList<GameEntity>()

        list.map {
            val (platform, genre, publisher, developer) = convertListToString(it)
            val game = GameEntity(
                id = it.id,
                name = it.name,
                description = "",
                released = it.released ?: "No Data",
                image = it.backgroundImage,
                metaScore = it.metacritic ?: 0,
                platforms = platform,
                genres = genre,
                publisher = publisher,
                developer = developer,
                isFavorite = false
            )
            entities.add(game)
        }

        return entities
    }

    fun mapEntitiesToDomain(list : List<GameEntity>) : List<Game> {
        val domain = ArrayList<Game>()

        list.map {
            val game = Game(
                id = it.id,
                name = it.name,
                description = it.description,
                released = it.released,
                bgImage = it.image.toString(),
                metascore = it.metaScore,
                platforms = it.platforms,
                genres = it.genres,
                publisher = it.publisher,
                developers = it.developer,
                isFavorite = it.isFavorite
            )

            domain.add(game)
        }

        return domain
    }

    fun mapResponseToEntity(data : Games) : GameEntity {
        val (platform, genre, publisher, developer) = convertListToString(data)

        return GameEntity(
            id = data.id,
            name = data.name,
            description = data.descriptionRaw,
            released = data.released ?: "No Data",
            image = data.backgroundImage,
            metaScore = data.metacritic ?: 0,
            platforms = platform,
            genres = genre,
            publisher = publisher,
            developer = developer,
            isFavorite = false
        )
    }

    fun mapEntityToDomain(data : GameEntity) : Game = Game(
        id = data.id,
        name = data.name,
        description = data.description,
        released = data.released,
        bgImage = data.image.toString(),
        metascore = data.metaScore,
        platforms = data.platforms,
        genres = data.genres,
        publisher = data.publisher,
        developers = data.developer,
        isFavorite = data.isFavorite
    )

    fun mapDomainToEntity(data : Game) : GameEntity = GameEntity(
        id = data.id,
        name = data.name,
        description = data.description,
        released = data.released,
        image = data.bgImage,
        metaScore = data.metascore,
        platforms = data.platforms,
        genres = data.genres,
        publisher = data.publisher,
        developer = data.developers,
        isFavorite = data.isFavorite
    )

    private fun convertListToString(data : Games) : List<String> {
        val platform = StringBuilder().append("")
        val genre = StringBuilder().append("")
        val publisher = StringBuilder().append("")
        val developer = StringBuilder().append("")

        if (data.platforms != null) {
            data.platforms.forEach {
                platform.append(it.platform.name).append(",")
            }
        } else platform.append("None")

        if (data.publishers != null) {
            data.publishers.forEach {
                publisher.append(it.name).append(",")
            }
        } else publisher.append("None")

        if (data.genres != null) {
            data.genres.forEach {
                genre.append(it.name).append(",")
            }
        } else genre.append("None")

        if (data.developers != null) {
            data.developers.forEach {
                developer.append(it.name).append(",")
            }
        } else developer.append("None")

        return listOf(platform.removeSuffix(",").toString(), genre.removeSuffix(",").toString(), publisher.removeSuffix(",").toString(), developer.removeSuffix(",").toString())
    }

    fun filterPlatform(game : List<GameEntity>, keyword : String) : List<Game> {
        val games = mapEntitiesToDomain(game)
        val results = ArrayList<Game>()
        games.forEach { g ->
            g.platforms.split(",").toList().filter { it.contains(keyword) }.map {
                results.add(g)
            }
        }
        return results
    }

}