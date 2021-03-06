package com.smartystore.core.users.api;

import com.smartystore.core.common.api.DataContainer;
import com.smartystore.core.common.util.Paging;
import com.smartystore.core.users.api.viewmodel.UserEditDto;
import com.smartystore.core.users.domain.User;
import com.smartystore.core.users.service.StaffService;
import com.smartystore.core.users.service.UserService;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("owner/users")
@PreAuthorize("hasRole('OWNER')")
public class UserController {
  private UserService userService;
  private StaffService staffService;

  /*@PostMapping
  public ResponseEntity<UserModel> createUser(@RequestBody @Valid DataContainer<UserEditDto> viewModel) {
    User user = userService.createUser(viewModel.getData());
    return new ResponseEntity<>(UserModel.fromEntity(user), HttpStatus.CREATED);
  }*/


  @GetMapping
  public StaffPage listUsers(
      @RequestParam(value = "page", required = false, defaultValue = "1") Integer page,
      @RequestParam(value = "rows", required = false, defaultValue = "20") Integer rows,
      @RequestParam(value = "sort", required = false, defaultValue = "idDesc") String sort) {
    PageRequest pageRequest = PageRequest.of(page - 1, rows, Paging.of(sort));
    return new StaffPage(staffService.findStaffOfCurrentOwner(pageRequest), pageRequest);
  }

  @GetMapping("/{id}")
  public StaffModel getUser(@PathVariable("id") Long userId) {
    return StaffModel.fromEntity(staffService.findByIdOfCurrentOwner(userId));
  }

 /* @PutMapping("/{id}")
  public ResponseEntity<UserModel> updateUser(
      @PathVariable("id") Long id,
      @RequestBody @Valid DataContainer<UserEditDto> viewModel) {
    User user = userService.updateUser(id, viewModel.getData());
    return new ResponseEntity<>(UserModel.fromEntity(user), HttpStatus.OK);
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void deleteUser(@PathVariable("id") Long id) {
    userService.deleteById(id);
  }*/

}
