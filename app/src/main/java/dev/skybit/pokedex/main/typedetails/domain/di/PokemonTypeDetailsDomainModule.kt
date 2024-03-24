package dev.skybit.pokedex.main.typedetails.domain.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.skybit.pokedex.main.typedetails.domain.usecases.GetPokemonsBasicInfoByTypeIdPaged
import dev.skybit.pokedex.main.typedetails.domain.usecases.GetPokemonsBasicInfoByTypeIdPagedImpl
import dev.skybit.pokedex.main.typedetails.domain.usecases.PopulatePokemonTypeDetails
import dev.skybit.pokedex.main.typedetails.domain.usecases.PopulatePokemonTypeDetailsImpl

@Module
@InstallIn(SingletonComponent::class)
interface PokemonTypeDetailsDomainModule {

    @Binds
    fun provideGetPokemonsBasicInfoByTypeIdPaged(impl: GetPokemonsBasicInfoByTypeIdPagedImpl): GetPokemonsBasicInfoByTypeIdPaged

    @Binds
    fun providePopulatePokemonTypeDetails(impl: PopulatePokemonTypeDetailsImpl): PopulatePokemonTypeDetails
}
