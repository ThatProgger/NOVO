package com.novo.model.entry;

import com.novo.model.entry.enums.EntryType;
import com.novo.model.responsible.Responsible;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;
import java.util.List;

/**
 * Describes the model of responsible employees for a particular job
 *
 * @author Mikhail Dedyukhin
 * @since 1.0
 */

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "entries")
@Data
@EntityListeners(AuditingEntityListener.class)
public class Entry {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    @Column(name = "data", nullable = false, columnDefinition = "varchar(1000)")
    private String data;

    @Column(name = "entry_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private EntryType entryType;


    @CreatedBy
    private String createdBy;


    @Temporal(TemporalType.DATE)
    @CreatedDate
    private Date created;

    @OneToMany
    @JoinTable(name = "entry_responsibles", joinColumns = @JoinColumn(name = "entry_id"), inverseJoinColumns = @JoinColumn(name = "responsibles_id"))
    private List<Responsible> responsibles;
}
