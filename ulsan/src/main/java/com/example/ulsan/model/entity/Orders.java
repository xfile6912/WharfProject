package com.example.ulsan.model.entity;

import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Builder
@ToString(exclude = {"company","wharf"})
@Accessors(chain=true)
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String type;
    private String approvedStatus;
    private LocalDate wharfStartDate;
    private LocalDate wharfEndDate;
    private String applicantName;
    private String applicantPhoneNumber;
    private String applicantAddress;
    private String shipNumber;
    private int shipYear;
    private int shipInCount;
    private int shipApplyCount;
    private String shipName;
    private int shipWeight;
    private int shipLength;
    private int shipWidth;
    private String shipExpected;
    private int usageType;
    private int usageWork;
    private String facilityCode;
    private String approvedNumber;
    private int area;
    private String location;
    private String purpose;
    private String description;
    private String condition;
    private String downCargoOneKind;
    private String downCargoOneWeight;
    private String downCargoTwoKind;
    private String downCargoTwoWeight;
    private String downCargoThreeKind;
    private String downCargoThreeWeight;
    private String upCargoOneKind;
    private String upCargoOneWeight;
    private String upCargoTwoKind;
    private String upCargoTwoWeight;
    private String upCargoThreeKind;
    private String upCargoThreeWeight;
    private String deviceCargoOneKind;
    private String deviceCargoOneWeight;
    private String deviceCargoOneNumber;
    private String deviceCargoTwoKind;
    private String deviceCargoTwoWeight;
    private String deviceCargoTwoNumber;
    private String feeExemptionReason;
    private LocalDate feeExemptionStartDate;
    private LocalDate feeExemptionEndDate;
    private LocalDate feeUsageStartDate;
    private LocalDate feeUsageEndDate;
    private String calculatedDescription;
    private int partExemption;
    private String partExemptionReason;
    private int totalFee;
    private LocalDate collectionRequestDate;
    private LocalDate dateOfPayment;
    private String note;
    private String appliedStatus;
    private String division;
    private LocalDateTime registeredAt;

    @ManyToOne
    private Company company;

    @ManyToOne
    private Wharf wharf;
}
