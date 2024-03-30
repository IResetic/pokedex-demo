package dev.skybit.pokedex.main.core.data.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dev.skybit.pokedex.main.core.data.DATABASE_NAME
import dev.skybit.pokedex.main.core.data.local.PokedexAppDatabase
import dev.skybit.pokedex.main.core.data.local.converters.ListConverter
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context,
        listConverter: ListConverter
    ): PokedexAppDatabase {
        return Room.databaseBuilder(context, PokedexAppDatabase::class.java, DATABASE_NAME)
            .addTypeConverter(listConverter)
            .build()
    }

    @Provides
    fun providePokemonTypeDao(database: PokedexAppDatabase) = database.pokemonTypeDao()

    @Provides
    fun providePokemonBasicInfoDao(database: PokedexAppDatabase) = database.pokemonBasicInfoDao()

    @Provides
    fun providePokemonDetailsDao(database: PokedexAppDatabase) = database.pokemonDetailsDao()
}
