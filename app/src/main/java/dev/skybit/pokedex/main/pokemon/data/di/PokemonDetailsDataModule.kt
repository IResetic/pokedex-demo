package dev.skybit.pokedex.main.pokemon.data.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.skybit.pokedex.main.pokemon.data.datasources.PokemonDetailsLocalDataSource
import dev.skybit.pokedex.main.pokemon.data.datasources.PokemonDetailsLocalDataSourceImpl
import dev.skybit.pokedex.main.pokemon.data.datasources.PokemonDetailsRemoteDataSource
import dev.skybit.pokedex.main.pokemon.data.datasources.PokemonDetailsRemoteDataSourceImpl
import dev.skybit.pokedex.main.pokemon.data.repository.PokemonDetailsRepositoryImpl
import dev.skybit.pokedex.main.pokemon.domain.repository.PokemonDetailsRepository

@Module
@InstallIn(SingletonComponent::class)
interface PokemonDetailsDataModule {

    @Binds
    fun providePokemonDetailsRemoteDataSource(impl: PokemonDetailsRemoteDataSourceImpl): PokemonDetailsRemoteDataSource

    @Binds
    fun providePokemonDetailsLocalDataSource(impl: PokemonDetailsLocalDataSourceImpl): PokemonDetailsLocalDataSource

    @Binds
    fun providePokemonDetailsRepository(impl: PokemonDetailsRepositoryImpl): PokemonDetailsRepository
}
