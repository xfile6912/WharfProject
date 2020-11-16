package com.example.ulsan.model.network.body;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Accessors(chain=true)
public class UsageBody {
    private Long id;

    private int area;
    private LocalDate startDate;
    private LocalDate endDate;
    private Long companyId;
    private Long wharfId;
    private String company;
    private String wharf;
    private String wharfKind;
}
