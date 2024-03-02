package dev.skybit.pokedex.main.core.utils

import dev.skybit.pokedex.main.core.utils.Resource.Error
import dev.skybit.pokedex.main.core.utils.Resource.Success

sealed class Resource<T>(val data: T? = null, val message: String? = null) {
    class Success<T>(data: T) : Resource<T>(data)
    class Error<T>(message: String, data: T? = null) : Resource<T>(data, message)
    class Loading<T> : Resource<T>()
}

inline fun <T> Resource<T>.onSuccess(action: (T) -> Unit): Resource<T> {
    if (this is Success) {
        this.data?.let {
            action(it)
            return this
        }
    }
    return this
}

inline fun <T> Resource<T>.onError(action: (String?, T?) -> Unit): Resource<T> {
    if (this is Error) {
        action(this.message, this.data)
        return this
    }
    return this
}

inline fun <T> Resource<T>.onLoading(action: (T) -> Unit): Resource<T> {
    if (this is Resource.Loading) {
        this.data?.let {
            action(it)
            return this
        }
    }
    return this
}
