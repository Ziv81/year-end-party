package com.changing.party.user;

import com.changing.party.model.OnlyNameAndPointModel;
import lombok.Builder;

@Builder
public class OnlyNameAndPointStub implements OnlyNameAndPointModel {

    int userPoint;
    String englishName;

    @Override
    public int getUserPoint() {
        return this.userPoint;
    }

    @Override
    public String getEnglishName() {
        return this.englishName;
    }
}
