package dev.denissajnar.user.infra.database.converters

import dev.denissajnar.user.domain.model.UserId
import org.springframework.core.convert.converter.Converter
import org.springframework.data.convert.ReadingConverter
import java.util.*

@ReadingConverter
class UserIdReadConverter : Converter<UUID, UserId> {
    override fun convert(source: UUID): UserId = UserId(source)
}
