package com.changing.party.common.converter;

import com.changing.party.common.enums.AnswerReviewStatus;
import lombok.SneakyThrows;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class MissionAnswerStatusConverter implements AttributeConverter<AnswerReviewStatus, Integer> {
    @Override
    public Integer convertToDatabaseColumn(AnswerReviewStatus attribute) {
        return attribute.getStatus();
    }

    @SneakyThrows
    @Override
    public AnswerReviewStatus convertToEntityAttribute(Integer dbData) {
        return AnswerReviewStatus.convert(dbData);
    }
}
