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
public class InfoBody {
    private Long id;
    private String name;
    private String content;
    private LocalDate updatedAt;
}
