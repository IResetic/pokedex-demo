package dev.skybit.pokedex.main.typedetails.domain.usecases

import dev.skybit.pokedex.main.core.utils.Resource
import dev.skybit.pokedex.main.typedetails.domain.model.PokemonBasicInfo
import dev.skybit.pokedex.main.typedetails.domain.repository.PokemonTypeDetailsRepository
import java.util.concurrent.CancellationException
import javax.inject.Inject

class GetPokemonsBasicInfoByTypeIdImpl @Inject constructor(
    private val pokemonTypeDetailsRepository: PokemonTypeDetailsRepository
) : GetPokemonsBasicInfoByTypeId {
    override suspend operator fun invoke(typeId: Int): Resource<List<PokemonBasicInfo>> {
        try {
            pokemonTypeDetailsRepository.populatePokemonTypeDetails(typeId)
            val pokemons = pokemonTypeDetailsRepository.getPokemonTypeDetails(typeId)

            return Resource.Success(data = pokemons)
        } catch (e: Exception) {
            if (e is CancellationException) {
                throw e
            } else {
                val pokemons = pokemonTypeDetailsRepository.getPokemonTypeDetails(typeId)
                return Resource.Error(
                    message = e.message ?: "An error occurred",
                    data = pokemons
                )
            }
        }
    }
}
