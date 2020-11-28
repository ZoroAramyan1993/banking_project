package com.example.demo.chronology;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.Instant;

@MappedSuperclass
@JsonIgnoreProperties(value = {"created"})
@EntityListeners(AuditingEntityListener.class)

public class CreatedChronology {

    @Column(nullable = false,updatable = false)
    @CreatedDate
    private Instant created;


    public Instant getCreated() {
        return created;
    }

    public void setCreated(Instant created) {
        this.created = created;
    }
}
