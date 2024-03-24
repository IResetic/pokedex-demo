package dev.skybit.pokedex.main.core.domain.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.skybit.pokedex.main.core.domain.usecases.GetPokemonTypeBasicInfo
import dev.skybit.pokedex.main.core.domain.usecases.GetPokemonTypeBasicInfoImpl

@Module
@InstallIn(SingletonComponent::class)
interface AppDomainModel {
    @Binds
    fun provideGetPokemonTypeBasicInfo(impl: GetPokemonTypeBasicInfoImpl): GetPokemonTypeBasicInfo
}
