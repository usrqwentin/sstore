package com.smartystore.core.profiles.api;

import com.smartystore.core.common.api.DataContainer;
import com.smartystore.core.common.utils.Paging;
import com.smartystore.core.profiles.api.viewmodel.ProfileEditDto;
import com.smartystore.core.profiles.domain.Profile;
import com.smartystore.core.profiles.service.ProfileService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@AllArgsConstructor
@RequestMapping("/profiles")
public class ProfileController {
    private ProfileService profileService;

    @PostMapping
    public ResponseEntity<ProfileModel> createProfile(@RequestBody @Valid DataContainer<ProfileEditDto> viewModel) {
        Profile profile = profileService.createProfile(viewModel.getData());
        return new ResponseEntity<>(ProfileModel.fromEntity(profile), HttpStatus.CREATED);
    }

    @GetMapping
    public ProfilePage listProfiles(
            @RequestParam(value = "page", required = false, defaultValue = "1") Integer page,
            @RequestParam(value = "rows", required = false, defaultValue = "20") Integer rows,
            @RequestParam(value = "sort", required = false, defaultValue = "idDesc") String sort) {
        PageRequest pageRequest = PageRequest.of(page - 1, rows, Paging.of(sort));
        return new ProfilePage(profileService.getPagedProfileList(pageRequest), pageRequest);
    }

    @GetMapping("/{id}")
    public ProfileModel getProfile(@PathVariable("id") Long profileId) {
        return ProfileModel.fromEntity(profileService.getProfileById(profileId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProfileModel> updateProfile(
            @PathVariable("id") Long id,
            @RequestBody @Valid DataContainer<ProfileEditDto> viewModel) {
        Profile profile = profileService.updateProfile(id, viewModel.getData());
        return new ResponseEntity<>(ProfileModel.fromEntity(profile), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTruck(@PathVariable("id") Long id) {
        profileService.deleteProfile(id);
    }

}
