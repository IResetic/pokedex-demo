package dev.skybit.pokedex.main.core.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import dev.skybit.pokedex.main.core.data.DATABASE_VERSION
import dev.skybit.pokedex.main.pokemontypes.data.local.dao.PokemonTypeDao
import dev.skybit.pokedex.main.pokemontypes.data.local.model.PokemonTypeEntity

@Database(
    entities = [PokemonTypeEntity::class],
    version = DATABASE_VERSION
)
abstract class PokedexAppDatabase : RoomDatabase() {
    abstract fun pokemonTypeDao(): PokemonTypeDao
}
