package com.changing.party.common.converter;

import com.changing.party.model.MissionAnswerModel;
import lombok.SneakyThrows;

import javax.persistence.AttributeConverter;

public class MissionAnswerStatusConverter implements AttributeConverter<MissionAnswerModel.AnswerReviewStatus, Integer> {
    @Override
    public Integer convertToDatabaseColumn(MissionAnswerModel.AnswerReviewStatus attribute) {
        return attribute.getStatus();
    }

    @SneakyThrows
    @Override
    public MissionAnswerModel.AnswerReviewStatus convertToEntityAttribute(Integer dbData) {
        return MissionAnswerModel.AnswerReviewStatus.convert(dbData);
    }
}
