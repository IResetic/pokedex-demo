package dev.skybit.pokedex.main.pokemontypes.domain.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.skybit.pokedex.main.pokemontypes.domain.usecases.GetPokemonTypes
import dev.skybit.pokedex.main.pokemontypes.domain.usecases.GetPokemonTypesImpl

@Module
@InstallIn(SingletonComponent::class)
interface PokemonTypeDomainModule {

    @Binds
    fun provideGetPokemonTypes(impl: GetPokemonTypesImpl): GetPokemonTypes
}
