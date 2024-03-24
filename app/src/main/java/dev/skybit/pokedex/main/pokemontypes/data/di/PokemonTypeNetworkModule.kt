package dev.skybit.pokedex.main.pokemontypes.data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.skybit.pokedex.main.pokemontypes.data.remote.PokemonTypeApi
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PokemonTypeNetworkModule {

    @Singleton
    @Provides
    fun providePokemonTypeApi(retrofit: Retrofit): PokemonTypeApi {
        return retrofit.create(PokemonTypeApi::class.java)
    }
}
