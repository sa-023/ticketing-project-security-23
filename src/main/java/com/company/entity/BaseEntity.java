package com.company.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
/*
 * 🖍️...
 * · @Column(nullable = false,updatable = false): It will prevent overriding (updating) the values in the database when we perform the update.
 *   Ex: To prevent an override of "insertDateTime" and "insertUserId" that becomes null, when we update the data.
 * · @Column(nullable = false): It should not be null.
 * · @EntityListeners(BaseEntityListener.class): Specifies the callback listener classes to be used for an entity or mapped superclass.
 *
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@MappedSuperclass
@EntityListeners(BaseEntityListener.class)
public class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false,updatable = false)
    public LocalDateTime insertDateTime;
    @Column(nullable = false,updatable = false)
    public Long insertUserId;
    @Column(nullable = false)
    public LocalDateTime lastUpdateDateTime;
    @Column(nullable = false)
    public Long lastUpdateUserId;
    /*
     * 🖍️...
     * · The isDeleted field is for flagging data. When data will be deleted from the UI but still be available in the database.
     * · private Boolean isDeleted = false; will be used with @Where(clause = "is_deleted=false") in child entity classes.
     */
    private Boolean isDeleted = false;






}
