package com.example.ulsan.Model.Entity;

import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString(exclude = {"company","wharf"})
@Entity
@Builder
@Accessors(chain=true)
public class Usages {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int area;
    private LocalDate startDate;
    private LocalDate endDate;

    @ManyToOne
    private Company company;

    @ManyToOne
    private Wharf wharf;
}
