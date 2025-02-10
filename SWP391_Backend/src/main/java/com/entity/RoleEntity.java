package com.entity;

import com.util.enums.RoleNameEnum;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "roles")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RoleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    Long roleId;

    @Enumerated(EnumType.STRING)
    @Column(name = "role_name")
    RoleNameEnum roleName;

    @ManyToMany
    @JoinTable(name = "role_permission", joinColumns = @JoinColumn(name = "role_id"), inverseJoinColumns = @JoinColumn(name = "permission_id"))
    List<PermissionEntity> permissions;

    @OneToMany(mappedBy = "role")
    List<UserEntity> users;

    public RoleEntity(RoleNameEnum roleName, List<PermissionEntity> permissions) {
        this.roleName = roleName;
        this.permissions = permissions;
    }
}
