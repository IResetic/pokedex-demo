package dev.skybit.pokedex.main.pokemontypes.data.datasources

import dev.skybit.pokedex.main.pokemontypes.data.local.model.PokemonTypeEntity

class FakePokemonTypesLocalDataSource : PokemonTypesLocalDataSource {
    var fakePokemonTypeEntities = mutableListOf<PokemonTypeEntity>()
    var isInserted = false

    override suspend fun insertOrUpdatePokemonType(types: List<PokemonTypeEntity>) {
        fakePokemonTypeEntities.addAll(types)
        isInserted = true
    }

    override suspend fun getPokemonTypes(): List<PokemonTypeEntity> {
        return fakePokemonTypeEntities
    }
}
