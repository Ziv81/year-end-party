package com.changing.party.model;

import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@NoArgsConstructor
@Data
@Builder
@AllArgsConstructor
@Entity
@Table(name = "BinaryAnswer", indexes = {
        @Index(name = "idx_binary_answer_user", columnList = "answeredUserId")
}, uniqueConstraints = {
        @UniqueConstraint(name = "uc_binary_answer_user", columnNames = {"answeredUserId"})
})
@EqualsAndHashCode(exclude = {"answeredUser", "binaryAnswerDetails"})
public class BinaryAnswerModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "binaryAnswerId")
    Integer binaryAnswerId;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "answeredUserId", referencedColumnName = "userId")
    UserModel answeredUser;

    @OneToMany(mappedBy = "binaryAnswerId", fetch = FetchType.LAZY)
    private Set<BinaryAnswerDetailModel> binaryAnswerDetails;

    @Column(name = "answeredTime")
    Date answeredTime;
}
