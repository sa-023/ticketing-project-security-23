package com.company.converter;
import com.company.dto.RoleDTO;
import com.company.service.RoleService;
import org.springframework.boot.context.properties.ConfigurationPropertiesBinding;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
/*
 * 🖍️...
 * · We see the below error when HTML returns a value as a string, but the table is looking for a RoleDTO object.
 *   "IllegalStateException: Cannot convert value of type 'java.lang.String' to required type 'com.company.dto.RoleDTO' for property 'role'"
 * · To fix the issue, we use the converter interface which converts a source object of type S to a target of type T.
 *   Ex: We create a converter class and implement it from the Converter<S,T> Interface. Next we have to override convert(String source) method.
 * · @ConfigurationPropertiesBinding annotation will let Spring know that the class is a converter, so it can run the class and handle the situation.
 *
 * · @Lazy annotation will prevent creation of the beans until we refer to them.
 *
 */
@ConfigurationPropertiesBinding
@Component
public class RoleDtoConverter implements Converter<String,RoleDTO> {
    RoleService roleService;
    public RoleDtoConverter(@Lazy RoleService roleService) {
        this.roleService = roleService;
    }


    @Override
    public RoleDTO convert(String source) {
        if (source == null || source.equals("")) {
            return null;
        }
        return roleService.findById(Long.parseLong(source)); // RoleDTO ID type is Long, so we convert String to Long
    }


}
