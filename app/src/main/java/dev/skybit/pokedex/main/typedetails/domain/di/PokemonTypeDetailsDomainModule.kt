package dev.skybit.pokedex.main.typedetails.domain.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.skybit.pokedex.main.typedetails.domain.usecases.GetPokemonsBasicInfoByTypeId
import dev.skybit.pokedex.main.typedetails.domain.usecases.GetPokemonsBasicInfoByTypeIdImpl

@Module
@InstallIn(SingletonComponent::class)
interface PokemonTypeDetailsDomainModule {

    @Binds
    fun provideGetPokemonsBasicInfoByTypeId(impl: GetPokemonsBasicInfoByTypeIdImpl): GetPokemonsBasicInfoByTypeId
}
