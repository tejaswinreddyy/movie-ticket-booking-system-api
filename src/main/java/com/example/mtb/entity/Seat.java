package com.example.mtb.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
@ToString
public class Seat {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "seat_id")
    private String seatId;

    @Column(name = "name")
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    @JoinColumn(name = "screen_id")
    private Screen screen;

    @Column(name = "is_delete")
    private boolean isDelete;

    @Column(name = "deleted_at",  columnDefinition = "TIMESTAMP(6)")
    private Instant deletedAt;


    @CreatedDate
    @Column(name = "created_at",  columnDefinition = "TIMESTAMP(6)")
    private Instant createdAt;


}
