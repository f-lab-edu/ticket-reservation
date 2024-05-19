package com.ticketing.solution.domain.member;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.io.Serializable;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Entity
@DynamicInsert
@EntityListeners(AuditingEntityListener.class)
@Table(name = "member")
public class Member implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "email", nullable = false, length = 255, unique = true)
    private String email;

    @Column(name = "password", nullable = false, length = 255)
    private String passwordHash;

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Column(name = "phone", nullable = false, length = 20)
    private String phone;

    @Column(name = "address", nullable = false, length = 255)
    private String address;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    @ColumnDefault("'USER'")
    private MemberType type;

    @CreatedDate
    private LocalDateTime createdDate;

    @LastModifiedDate
    private LocalDateTime modifiedDate;

    public void update(Member member) {
        this.name = member.getName();
        this.phone = member.getPhone();
        this.address = member.getAddress();
    }

    public void encodePassword(PasswordEncoder passwordEncoder) {
        this.passwordHash = passwordEncoder.encode(this.passwordHash);
    }
}