package com.novo.model.responsible;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Mikhail Dedyukhin
 * @since 1.o
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "responsible")
public class Responsible {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "fist_name", nullable = false)
    private String first_name;
    @Column(name = "middle_name", nullable = true)
    private String middle_name;
    @Column(name = "last_name", nullable = false)
    private String last_name;
}
