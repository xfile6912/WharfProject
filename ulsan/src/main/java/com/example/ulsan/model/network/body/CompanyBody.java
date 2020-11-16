package com.example.ulsan.model.network.body;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Accessors(chain=true)
public class CompanyBody {
    private Long id;

    private String code;
    private String name;
    private int warnCount;
    private List<OrderBody> orderBodyList;
    private List<UsageBody> usageBodyList;
}
