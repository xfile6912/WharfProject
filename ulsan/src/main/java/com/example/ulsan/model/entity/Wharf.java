package com.example.ulsan.model.entity;

import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@ToString(exclude = {"ordersList","usagesList"})
@Builder
@Accessors(chain=true)
public class Wharf {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;//야적장 이름
    private String kind;//야적장 종류
    private int area;//야적장 면적
    private int exemptionArea;
    @OneToMany(fetch= FetchType.LAZY, mappedBy = "wharf")
    private List<Orders> ordersList;

    @OneToMany(fetch= FetchType.LAZY, mappedBy = "wharf")
    private List<Usages> usagesList;
}
