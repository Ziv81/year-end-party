package com.changing.party.common.converter;

import com.changing.party.common.enums.LogType;
import lombok.SneakyThrows;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class LogTypeConverter implements AttributeConverter<LogType, Integer> {
    @Override
    public Integer convertToDatabaseColumn(LogType attribute) {
        return attribute.getCode();
    }

    @SneakyThrows
    @Override
    public LogType convertToEntityAttribute(Integer dbData) {
        return LogType.convert(dbData);
    }
}
