package com.smartystore.core.profiles.api.viewmodel;

import com.smartystore.core.common.api.ApiData;
import com.smartystore.core.common.api.Role;
import com.smartystore.core.common.domain.EntityStatus;
import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class ProfileEditDto extends ApiData {

    @NotBlank(message = "Login must be specified")
    private String login;
    @NotBlank(message = "Password must be specified")
    private String password;
    private String fullname;
    @NotBlank(message = "Email must be specified")
    @Email
    private String email;
    @NotBlank(message = "Phone must be specified")
    private String phone;
    private String iconUri;
    private EntityStatus status;
    private Role role;

}
