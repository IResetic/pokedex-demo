package dev.skybit.pokedex.main.typedetails.data.local.model

const val fakeGrassPokemonTypeId = 1
const val fakeFirePokemonTypeId = 2

val fakePokemonBasicInfoEntityGrassOne = PokemonBasicInfoEntity(
    id = 1,
    name = "bulbasaur",
    pokemonTypeId = fakeGrassPokemonTypeId,
    pokemonTypeName = "grass"
)

val fakePokemonBasicInfoEntityGrassTwp = PokemonBasicInfoEntity(
    id = 2,
    name = "ivysaur",
    pokemonTypeId = 1,
    pokemonTypeName = "grass"
)

val fakePokemonBasicInfEntityFireOne = PokemonBasicInfoEntity(
    id = 3,
    name = "charmander",
    pokemonTypeId = fakeFirePokemonTypeId,
    pokemonTypeName = "fire"
)
