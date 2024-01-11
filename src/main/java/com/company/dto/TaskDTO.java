package com.company.dto;
import com.company.enums.Status;
import com.sun.istack.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.util.UUID;

@NoArgsConstructor
@Data
public class TaskDTO {

    private Long id;

   @NotNull
    private ProjectDTO project;

    @NotNull
    private UserDTO assignedEmployee;

    @NotBlank
    private String taskSubject;

    @NotBlank
    private String taskDetail;

    private Status taskStatus;
    private LocalDate assignedDate;

    public TaskDTO(ProjectDTO project, UserDTO assignedEmployee, String taskSubject, String taskDetail, Status taskStatus, LocalDate assignedDate) {
        this.project = project;
        this.assignedEmployee = assignedEmployee;
        this.taskSubject = taskSubject;
        this.taskDetail = taskDetail;
        this.taskStatus = taskStatus;
        this.assignedDate = assignedDate;
        this.id = UUID.randomUUID().getMostSignificantBits(); // UUID assigns random digits to the id field;
        /*
         * 🖍️...
         * · The Task Create form doesn't have a unique ID field on the UI. When the client creates a new task and saves it,
         *   there will be no unique ID. So, it can cause the error of deleting or updating the task.
         *   Because of that, we create our constructor manually. We don't pass an ID in the constructor parameter.
         *   To populate unique IDs automatically, we assign UUID to the current ID instance of the method.
         * · Normally a unique ID (Primary key) will be created and assigned to the task automatically by Postgres.
         */
    }

}
