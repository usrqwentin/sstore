package com.smartystore.core.profiles.api.viewmodel;

import com.smartystore.core.common.api.ApiData;
import com.smartystore.core.common.api.Role;
import com.smartystore.core.common.domain.EntityStatus;
import com.smartystore.core.profiles.domain.Profile;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class ProfileReadDto extends ApiData {
    private String login;
    private String password;
    private String fullname;
    private String email;
    private String phone;
    private String iconUri;
    private EntityStatus status;
    private Role role;

    public static ProfileReadDto fromEntity(Profile profile){
        ProfileReadDto vm = new ProfileReadDto();
        vm.setId(profile.getId());
        vm.setLogin(profile.getLogin());
        vm.setPassword(profile.getPassword());
        vm.setFullname(profile.getFullname());
        vm.setEmail(profile.getEmail());
        vm.setPhone(profile.getPhone());
        vm.setIconUri(profile.getIconUri());
        vm.setRole(profile.getRole());
        vm.setStatus(profile.getStatus());
        return vm;
    }
}
