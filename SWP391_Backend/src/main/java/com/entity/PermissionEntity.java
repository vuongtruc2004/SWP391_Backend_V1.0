package com.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.util.enums.ApiMethodEnum;
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
@Table(name = "permissions")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PermissionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "permission_id")
    Long permissionId;

    @Column(name = "api_path")
    String apiPath;

    @Enumerated(EnumType.STRING)
    @Column(name = "api_method")
    ApiMethodEnum apiMethod;

    @ManyToMany(mappedBy = "permissions")
    @JsonManagedReference
    List<RoleEntity> roles;

    public PermissionEntity(String apiPath, ApiMethodEnum apiMethod) {
        this.apiPath = apiPath;
        this.apiMethod = apiMethod;
    }
}
