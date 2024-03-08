package dev.skybit.pokedex.main.pokemontypes.domain.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.skybit.pokedex.main.pokemontypes.domain.usecases.PopulatePokemonTypes
import dev.skybit.pokedex.main.pokemontypes.domain.usecases.PopulatePokemonTypesImpl
import dev.skybit.pokedex.main.pokemontypes.domain.usecases.StartPokemonTypesListener
import dev.skybit.pokedex.main.pokemontypes.domain.usecases.StartPokemonTypesListenerImpl

@Module
@InstallIn(SingletonComponent::class)
interface PokemonTypeDomainModel {
    @Binds
    fun providePopulatePokemonTypes(impl: PopulatePokemonTypesImpl): PopulatePokemonTypes

    @Binds
    fun provideStartPokemonTypesListener(impl: StartPokemonTypesListenerImpl): StartPokemonTypesListener
}
