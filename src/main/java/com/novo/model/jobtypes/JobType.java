package com.novo.model.jobtypes;

import com.novo.model.entry.Entry;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Describes a job type
 * @author Mikhail Dedyukhin
 * @since 1.o
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "job_type")
public class JobType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "job_type", nullable = false, length = 100)
    private String name;

}
