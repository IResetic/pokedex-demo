package dev.skybit.pokedex.main.pokemon.data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.skybit.pokedex.main.pokemon.data.remote.api.PokemonDetailsApi
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PokemonDetailsNetworkModule {

    @Singleton
    @Provides
    fun providePokemonDetailsApi(retrofit: Retrofit): PokemonDetailsApi {
        return retrofit.create(PokemonDetailsApi::class.java)
    }
}
