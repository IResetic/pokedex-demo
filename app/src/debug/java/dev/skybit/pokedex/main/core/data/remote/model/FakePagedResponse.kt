package dev.skybit.pokedex.main.core.data.remote.model

val fakePokemonTypesPagedResponse = PagedResponse(
    count = 2,
    next = null,
    previous = null,
    results = listOf(
        fakeResultDtoGrass,
        fakeResultDtoFire
    )
)
