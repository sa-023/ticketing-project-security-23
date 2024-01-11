package com.company.service.impl;

import com.company.dto.ProjectDTO;
import com.company.dto.TaskDTO;
import com.company.dto.UserDTO;
import com.company.entity.User;
import com.company.mapper.UserMapper;
import com.company.repository.UserRepository;
import com.company.service.ProjectService;
import com.company.service.TaskService;
import com.company.service.UserService;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
/*
 * 🖍️...
 * · @Lazy annotation will prevent creation of the beans until we refer to them.
 * · ProjectServiceImpl and UserServiceImpl are dependent on each other. Adding @Lazy to ProjectService to prevent circular dependency issues.
 */
@Service
//@Transactional
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final ProjectService projectService;
    private final TaskService taskService;
    private final PasswordEncoder passwordEncoder;
    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper, @Lazy ProjectService projectService, TaskService taskService, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.projectService = projectService;
        this.taskService = taskService;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public List<UserDTO> listAllUsers() {
        List<User> userList = userRepository.findAll(Sort.by("firstName"));
        return userList.stream().map(userMapper::convertToDto).collect(Collectors.toList());
//        return userList.stream().map(p-> userMapper.convertToDto(p)).collect(Collectors.toList());
    }

    @Override
    public UserDTO findByUserName(String username) {
//        User user = userRepository.findByUserName(username);
//        return userMapper.convertToDto(user);
        return userMapper.convertToDto(userRepository.findByUserName(username));
    }

    @Override
    public void save(UserDTO dto) {
//        userRepository.save(userMapper.convertToEntity(dto));
        dto.setEnabled(true);
        User obj =  userMapper.convertToEntity(dto);
        obj.setPassWord(passwordEncoder.encode(obj.getPassWord()));
        userRepository.save(obj);
    }

    @Override
    public UserDTO update(UserDTO dto) {
        /*
         * 🖍️...
         * 1. Find the current user to capture the ID. Getting the user ID from the Database.
         * 2. Map updated userDto to entity object. Convert dto, which has no ID, to an entity (In the User Create view, there is no id field).
         * 3. Set the ID to the converted object (Set "convertedUser" id to "user" id).
         * 4. Save updated user (Save "convertedUser" in DB).
         */
        User user = userRepository.findByUserName(dto.getUserName()); // 1.
        User convertedUser = userMapper.convertToEntity(dto); // 2.
        convertedUser.setId(user.getId()); // 3.
        userRepository.save(convertedUser); // 4.
        return findByUserName(dto.getUserName());
    }

    @Override
    public void deleteByUserName(String username) {
        userRepository.deleteByUserName(username);
    }

    @Override
    public void delete(String userName){ // The user will not be deleted from the database; only the flag (isDeleted) value will be changed.
        User user = userRepository.findByUserName(userName);
//        user.setIsDeleted(true);
//        userRepository.save(user);
        if(checkIfUserCanBeDeleted(user)){
            user.setIsDeleted(true);
            // While the flag of the userName changes in DB, we add the "-" sign at the end of the userName, so we can reuse it to create a new user.
            user.setUserName(user.getUserName() + "-" + user.getId());
            userRepository.save(user);
        }
    }

    private boolean checkIfUserCanBeDeleted(User user){
        switch (user.getRole().getDescription()){
            case "Manager":
                List<ProjectDTO> projectDTOList = projectService.readAllByAssignedManager(user);
                return projectDTOList.size() == 0;
            case "Employee":
                List<TaskDTO> taskDTOList = taskService.readAllByAssignedEmployee(user);
                return taskDTOList.size() == 0;
            default:
                return true;
        }
    }

    @Override
    public List<UserDTO> listAllByRole(String role) {
        List<User> users = userRepository.findAllByRoleDescriptionIgnoreCase(role);
        return users.stream().map(userMapper::convertToDto).collect(Collectors.toList());
    }





}
