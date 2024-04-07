package dev.skybit.pokedex.main.pokemon.domain.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.skybit.pokedex.main.pokemon.domain.usecases.GetPokemonBasicInfo
import dev.skybit.pokedex.main.pokemon.domain.usecases.GetPokemonBasicInfoImpl
import dev.skybit.pokedex.main.pokemon.domain.usecases.GetPokemonDetails
import dev.skybit.pokedex.main.pokemon.domain.usecases.GetPokemonDetailsById
import dev.skybit.pokedex.main.pokemon.domain.usecases.GetPokemonDetailsByIdImpl
import dev.skybit.pokedex.main.pokemon.domain.usecases.GetPokemonDetailsImpl
import dev.skybit.pokedex.main.pokemon.domain.usecases.PopulatePokemonDetails
import dev.skybit.pokedex.main.pokemon.domain.usecases.PopulatePokemonDetailsImpl

@Module
@InstallIn(SingletonComponent::class)
interface PokemonDomainModule {

    @Binds
    fun provideGetPokemonDetailsBasicInfo(impl: GetPokemonBasicInfoImpl): GetPokemonBasicInfo

    @Binds
    fun providePopulatePokemonDetails(impl: PopulatePokemonDetailsImpl): PopulatePokemonDetails

    @Binds
    fun provideGetPokemonDetailsById(impl: GetPokemonDetailsByIdImpl): GetPokemonDetailsById

    @Binds
    fun provideGetPokemonDetails(impl: GetPokemonDetailsImpl): GetPokemonDetails
}
