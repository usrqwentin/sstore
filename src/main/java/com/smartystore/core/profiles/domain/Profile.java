package com.smartystore.core.profiles.domain;

import com.smartystore.core.common.api.Role;
import com.smartystore.core.common.domain.EntityStatus;
import lombok.*;

import javax.persistence.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "profiles")
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String login;
    private String password;
    private String fullname;
    private String email;
    private String phone;
    @Column(name = "icon_uri")
    private String iconUri;
    @Enumerated(EnumType.STRING)
    private Role role;
    @Enumerated(EnumType.STRING)
    private EntityStatus status;

}

