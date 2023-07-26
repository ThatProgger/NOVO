package com.novo.model.entry;

import com.novo.model.entry.enums.EntryType;
import com.novo.model.responsible.Responsible;
import com.novo.model.user.User;
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
 * @author Mikhail Dedyukhin
 * @since 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "entries")
@EntityListeners(AuditingEntityListener.class)
public class Entry {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "text", nullable = false)
    @Lob
    private String text;

    @Column(name = "entry_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private EntryType entryType;
    @Column(name = "created_by", nullable = false)
    @CreatedBy
    private String createdBy;

    @Temporal(TemporalType.DATE)
    @CreatedDate
    private Date created;

    @OneToMany
    @JoinColumn(name = "entry_id")
    private List<Responsible> responsibles;
}
