package dev.skybit.pokedex.main.pokemontypes.data.local.dao

import dev.skybit.pokedex.main.pokemontypes.data.local.dao.PokemonTypeDao
import dev.skybit.pokedex.main.pokemontypes.data.local.model.PokemonTypeEntity

class FakePokemonTypeDao : PokemonTypeDao {
    override suspend fun insertOrUpdatePokemonTypes(pokemonTypes: List<PokemonTypeEntity>) {
        TODO("Not yet implemented")
    }

    override fun getAllPokemonTypes(): List<PokemonTypeEntity> {
        TODO("Not yet implemented")
    }
}
