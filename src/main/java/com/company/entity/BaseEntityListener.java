package com.company.entity;

import com.company.entity.common.UserPrincipal;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.time.LocalDateTime;
/*
 * 🖍️...
 * · @PrePersist: Used to mark a method in an entity class that should be executed just before the entity is persisted (saved) for the first time in the database.
 * · @PreUpdate: Used to mark a method in an entity class that should be executed just before an entity is updated in the database.
 * · SecurityContextHolder: is where Spring Security stores the details of who is authenticated.
 */
@Component
public class BaseEntityListener extends AuditingEntityListener {

    @PrePersist
    public void onePrePersist(BaseEntity baseEntity){ // Initializing the fields.
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        baseEntity.insertDateTime =  LocalDateTime.now();
        baseEntity.lastUpdateDateTime = LocalDateTime.now();
        if (authentication != null && !authentication.getName().equals("anonymousUser")){
            Object principal = authentication.getPrincipal();
            baseEntity.insertUserId = ((UserPrincipal) principal).getId();
            baseEntity.lastUpdateUserId = ((UserPrincipal) principal).getId();
        }
        /*
         * 🖍️...
         * · insertDateTime, insertUserId, lastUpdateDateTime, and lastUpdateUserId will be automatically created by Spring.
         * · Whenever we save new data on the database first time, @PrePersist annotated method will be executed.
         */
    }


    @PreUpdate // Whenever we update data on the database, @PreUpdate annotated method will be executed.
    public void onePreUpdate(BaseEntity baseEntity){
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        baseEntity.lastUpdateDateTime = LocalDateTime.now();
        if (authentication != null && !authentication.getName().equals("anonymousUser")){
            Object principal = authentication.getPrincipal();
            baseEntity.lastUpdateUserId = ((UserPrincipal) principal).getId();
        }
    }






}
