package com.changing.party.common.converter;

import com.changing.party.common.enums.StakeStatus;
import lombok.SneakyThrows;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class StakeStatusConverter implements AttributeConverter<StakeStatus, Integer> {
    @Override
    public Integer convertToDatabaseColumn(StakeStatus status) {
        return status.getStatus();
    }

    @SneakyThrows
    @Override
    public StakeStatus convertToEntityAttribute(Integer dbData) {
        return StakeStatus.convert(dbData);
    }
}
