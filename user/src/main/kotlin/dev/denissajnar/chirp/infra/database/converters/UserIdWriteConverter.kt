package dev.denissajnar.chirp.infra.database.converters

import dev.denissajnar.chirp.domain.model.UserId
import org.springframework.core.convert.converter.Converter
import org.springframework.data.convert.WritingConverter
import java.util.*

@WritingConverter
class UserIdWriteConverter : Converter<UserId, UUID> {
    override fun convert(source: UserId): UUID = source.value
}
