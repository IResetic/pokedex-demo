package dev.skybit.pokedex.main.pokemontypes.data.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.skybit.pokedex.main.pokemontypes.data.datasources.PokemonTypesRemoteDataSource
import dev.skybit.pokedex.main.pokemontypes.data.datasources.PokemonTypesRemoteDataSourceImpl
import dev.skybit.pokedex.main.pokemontypes.data.repository.PokemonTypesRepositoryImpl
import dev.skybit.pokedex.main.pokemontypes.domain.repository.PokemonTypesRepository

@Module
@InstallIn(SingletonComponent::class)
interface PokemonTypeDataModule {

    @Binds
    fun providePokemonTypeRemoteDataSource(impl: PokemonTypesRemoteDataSourceImpl): PokemonTypesRemoteDataSource

    @Binds
    fun providePokemonTypeRepository(impl: PokemonTypesRepositoryImpl): PokemonTypesRepository
}
