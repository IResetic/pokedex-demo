package dev.skybit.pokedex.main.core.domain.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.skybit.pokedex.main.core.domain.usecases.PopulatePokemonTypes
import dev.skybit.pokedex.main.core.domain.usecases.PopulatePokemonTypesImpl

@Module
@InstallIn(SingletonComponent::class)
interface PokedexDomainModel {

    @Binds
    fun providePopulatePokemonTypes(impl: PopulatePokemonTypesImpl): PopulatePokemonTypes
}
