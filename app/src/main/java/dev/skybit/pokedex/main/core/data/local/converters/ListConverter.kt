package dev.skybit.pokedex.main.core.data.local.converters

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import javax.inject.Inject

@ProvidedTypeConverter
class ListConverter @Inject constructor() {
    @TypeConverter
    fun toStringList(value: String): List<String> {
        return value.split(";")
    }

    @TypeConverter
    fun fromStringList(value: List<String>?): String? {
        return value?.joinToString(separator = ";")
    }
}
