package com.company.controller;
import com.company.dto.UserDTO;
import com.company.service.RoleService;
import com.company.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@Controller
@RequestMapping("/user")
public class UserController {
    private final RoleService roleService;
    private final UserService userService;
    public UserController(RoleService roleService, UserService userService) {
        this.roleService = roleService;
        this.userService = userService;
    }


    @GetMapping("/create")
    public String createUser(Model model) {
        model.addAttribute("user", new UserDTO());// Empty UserDTO object that we bind to the view to get input from the client.
        model.addAttribute("roles", roleService.listAllRoles()); // List of RoleDTOs to bind to the view for Role dropdown. (Comes from DB).
        model.addAttribute("users", userService.listAllUsers()); // List of Users to bind to the view for User List table. (Comes from DB).
        return "/user/create";
    }

    @PostMapping("/create")
    public String insertUser(@Valid @ModelAttribute("user") UserDTO user, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("roles", roleService.listAllRoles());
            model.addAttribute("users", userService.listAllUsers());
            return "/user/create";
        }
        userService.save(user);
        return "redirect:/user/create";
        /*
         * 🖍️...
         * · @ModelAttribute("user") binds the value to the method argument that was exposed to the web view.
         * · return "/user/create"; Will return the view.
         * · return "redirect:/user/create"; Will return the @GetMapping("/user/create") endpoint.
         *
         * · @Valid: Triggers validation of nested objects or properties.
         * · Validation errors are captured in a BindingResult object.
         * · We are adding Model again after BindingResult in the method parameter because whenever we have an error, and we
         *   redirect to the same page, we need to fill out the drop-downs again, even though we keep the object as it is.
         */
    }

    @GetMapping("/update/{username}")
    public String editUser(@PathVariable("username") String username, Model model) {
        model.addAttribute("user", userService.findByUserName(username));
        model.addAttribute("roles", roleService.listAllRoles());
        model.addAttribute("users", userService.listAllUsers());
        return "/user/update";
    }

    @PostMapping("/update")
    public String updateUser(@ModelAttribute("user") UserDTO user, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("roles", roleService.listAllRoles());
            model.addAttribute("users", userService.listAllUsers());
            return "/user/update";
        }
        userService.update(user);
        return "redirect:/user/create";
        /*
         * 🖍️...
         * · @GetMapping("/update/{username}"): Passing the path variable to the endpoint to retrieve a specific user when we click on the update button.
         * · @PathVariable("username"): We have to specify an end-point variable name inside the @PathVariable() annotation and pass it into the method parameter.
         */
    }

    @GetMapping("/delete/{username}")
    public String deleteUser(@PathVariable("username") String username) {
//        userService.deleteByUserName(username);
        userService.delete(username);
        return "redirect:/user/create";
    }





}
