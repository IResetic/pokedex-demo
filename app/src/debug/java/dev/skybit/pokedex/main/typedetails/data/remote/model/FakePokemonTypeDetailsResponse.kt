package dev.skybit.pokedex.main.typedetails.data.remote.model

import dev.skybit.pokedex.main.core.data.remote.model.ResultDto

val fakePokemonBulbasaur = Pokemon(
    pokemon = ResultDto(
        name = "bulbasaur",
        url = "https://pokeapi.co/api/v2/pokemon/1/"
    )
)

val fakePokemonIvysaur = Pokemon(
    pokemon = ResultDto(
        name = "ivysaur",
        url = "https://pokeapi.co/api/v2/pokemon/2/"
    )
)

val fakePokemonTypeDetailsResponseGrass = PokemonTypeDetailsResponse(
    id = 1,
    name = "grass",
    pokemons = listOf(fakePokemonBulbasaur, fakePokemonIvysaur)
)
