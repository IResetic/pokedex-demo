package dev.skybit.pokedex.main.pokemon.domain.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.skybit.pokedex.main.pokemon.domain.usecases.GetPokemonBasicInfo
import dev.skybit.pokedex.main.pokemon.domain.usecases.GetPokemonBasicInfoImpl

@Module
@InstallIn(SingletonComponent::class)
interface PokemonDomainModule {

    @Binds
    fun provideGetPokemonDetails(impl: GetPokemonBasicInfoImpl): GetPokemonBasicInfo
}
