package dev.skybit.pokedex.main.pokemontypes.data.datasources

import dev.skybit.pokedex.main.pokemontypes.data.datasources.PokemonTypesLocalDataSource
import dev.skybit.pokedex.main.pokemontypes.data.local.model.PokemonTypeEntity
import dev.skybit.pokedex.main.pokemontypes.data.local.model.fakePokemonTypeEntityFire
import dev.skybit.pokedex.main.pokemontypes.data.local.model.fakePokemonTypeEntityGrass

class FakePokemonTypesLocalDataSource : PokemonTypesLocalDataSource {
    var fakePokemonTypeEntities = mutableListOf(fakePokemonTypeEntityGrass, fakePokemonTypeEntityFire)
    var isInserted = false

    override suspend fun insertOrUpdatePokemonType(types: List<PokemonTypeEntity>) {
        fakePokemonTypeEntities = types.toMutableList()
        isInserted = true
    }

    override suspend fun getPokemonTypes(): List<PokemonTypeEntity> {
        return fakePokemonTypeEntities
    }
}
