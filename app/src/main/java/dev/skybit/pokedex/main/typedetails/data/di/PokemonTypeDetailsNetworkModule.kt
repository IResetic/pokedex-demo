package dev.skybit.pokedex.main.typedetails.data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.skybit.pokedex.main.typedetails.data.remote.PokemonTypeDetailsApi
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PokemonTypeDetailsNetworkModule {

    @Singleton
    @Provides
    fun providePokemonTypeDetailsApi(retrofit: Retrofit): PokemonTypeDetailsApi {
        return retrofit.create(PokemonTypeDetailsApi::class.java)
    }
}
