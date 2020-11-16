package com.example.ulsan.model.network.body;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Accessors(chain=true)
public class FeeBody {
    private Long id;

    private String name;
    private int fee;
    private String kind;
}
