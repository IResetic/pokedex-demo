package dev.skybit.pokedex.main.typedetails.data.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.skybit.pokedex.main.typedetails.data.datasources.PokemonTypeDetailsLocalDataSource
import dev.skybit.pokedex.main.typedetails.data.datasources.PokemonTypeDetailsLocalDataSourceImpl
import dev.skybit.pokedex.main.typedetails.data.datasources.PokemonTypeDetailsRemoteDataSource
import dev.skybit.pokedex.main.typedetails.data.datasources.PokemonTypeDetailsRemoteDataSourceImpl
import dev.skybit.pokedex.main.typedetails.data.repository.PokemonTypeDetailsRepositoryImpl
import dev.skybit.pokedex.main.typedetails.domain.repository.PokemonTypeDetailsRepository

@Module
@InstallIn(SingletonComponent::class)
interface PokemonTypeDetailsDataModule {

    @Binds
    fun providePokemonTypeDetailsRepository(impl: PokemonTypeDetailsRepositoryImpl): PokemonTypeDetailsRepository

    @Binds
    fun providePokemonTypeDetailsRemoteDataSource(impl: PokemonTypeDetailsRemoteDataSourceImpl): PokemonTypeDetailsRemoteDataSource

    @Binds
    fun providePokemonTypeDetailsLocalDataSource(impl: PokemonTypeDetailsLocalDataSourceImpl): PokemonTypeDetailsLocalDataSource
}
