package com.example.ulsan.model.entity;

import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Builder
@ToString(exclude = {"ordersList", "usagesList"})
@Accessors(chain=true)
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String code;
    private String name;
    private int warnCount;

    @OneToMany(fetch= FetchType.LAZY, mappedBy = "company")
    private List<Orders> ordersList;

    @OneToMany(fetch= FetchType.LAZY, mappedBy = "company")
    private List<Usages> usagesList;
}
