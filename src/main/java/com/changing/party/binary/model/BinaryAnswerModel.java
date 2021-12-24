package com.changing.party.binary.model;

import com.changing.party.user.model.UserModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@NoArgsConstructor
@Data
@Builder
@AllArgsConstructor
@Entity
@Table(name = "BinaryAnswer")
public class BinaryAnswerModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "binaryAnswerId")
    Integer binaryAnswerId;

    @OneToOne
    @JoinColumn(name = "answeredUserId", referencedColumnName = "userId")
    UserModel answeredUser;

    @OneToMany(mappedBy = "binaryAnswerId")
    private Set<BinaryAnswerDetailModel> binaryAnswerDetails;

    @Column(name = "answeredTime")
    Date answeredTime;
}