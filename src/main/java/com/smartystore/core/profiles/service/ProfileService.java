package com.smartystore.core.profiles.service;

import com.smartystore.core.profiles.api.viewmodel.ProfileEditDto;
import com.smartystore.core.profiles.domain.Profile;
import com.smartystore.core.profiles.domain.validation.ProfileException;
import com.smartystore.core.profiles.repository.ProfileRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import static com.smartystore.core.common.domain.EntityStatus.ACTIVE;
import static com.smartystore.core.common.domain.EntityStatus.INACTIVE;

@Service
@AllArgsConstructor
public class ProfileService {

    private ProfileRepository profileRepository;

    public Page<Profile> getPagedProfileList(PageRequest pageRequest) {
        Profile qbe = Profile.builder()
                             .status(ACTIVE)
                             .build();
        return profileRepository.findAll(Example.of(qbe), pageRequest);
    }

    public Profile getProfileById(Long id) {
        Profile qbe = Profile.builder()
                             .id(id)
                             .status(ACTIVE)
                             .build();
        return profileRepository.findOne(Example.of(qbe))
                                .orElseThrow(() -> ProfileException.create("Profile was not found", 1));
    }

    public Profile createProfile(ProfileEditDto dto) {
        Profile qbe = Profile.builder()
                             .email(dto.getEmail())
                             .status(ACTIVE)
                             .build();
        if (profileRepository.exists(Example.of(qbe))) {
            throw ProfileException.create("A profile with the specified email address already exists", 2);
        }
        Profile profile = Profile.builder()
                                 .login(dto.getLogin())
                                 .password(dto.getPassword())
                                 .fullname(dto.getFullname())
                                 .email(dto.getEmail())
                                 .phone(dto.getPhone())
                                 .iconUri(dto.getIconUri())
                                 .role(dto.getRole())
                                 .status(ACTIVE)
                                 .build();
        return profileRepository.save(profile);
    }

    public Profile updateProfile(Long id, ProfileEditDto dto) {
        Profile qbe = Profile.builder()
                             .id(id)
                             .status(ACTIVE)
                             .build();
        profileRepository.findOne(Example.of(qbe)).orElseThrow(() -> ProfileException.create("Profile was not found", 3));
        Profile profile = Profile.builder()
                                 .id(id)
                                 .login(dto.getLogin())
                                 .password(dto.getPassword())
                                 .fullname(dto.getFullname())
                                 .email(dto.getEmail())
                                 .phone(dto.getPhone())
                                 .iconUri(dto.getIconUri())
                                 .role(dto.getRole())
                                 .status(ACTIVE)
                                 .build();
        return profileRepository.save(profile);
    }

    public void deleteProfile(Long id) {
        Profile qbe = Profile.builder()
                             .id(id)
                             .status(ACTIVE)
                             .build();
        profileRepository.save(profileRepository.findOne(Example.of(qbe))
                                                .map(p -> {
                                                    p.setStatus(INACTIVE);
                                                    return p;
                                                })
                                                .orElseThrow(() -> ProfileException.create("Profile was not found", 3)));

    }

}
