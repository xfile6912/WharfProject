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
public class WharfBody {
    private Long id;

    private String name;//야적장 이름
    private String kind;//야적장 종류
    private int area;//야적장 면적
    private int exemptionArea;
    private List<OrderBody> orderBodyList;
    private List<UsageBody> usageBodyList;
}
