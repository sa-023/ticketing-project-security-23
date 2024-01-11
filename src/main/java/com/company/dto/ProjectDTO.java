package com.company.dto;
import com.company.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProjectDTO {

    private Long id;

    @NotBlank
    private String projectName;

    @NotBlank
    private String projectCode;

    @NotNull
    private UserDTO assignedManager;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull
    private LocalDate startDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull
    private LocalDate endDate;

    @NotBlank
    private String projectDetail;

    private Status projectStatus;

    private int completeTaskCounts;
    private int unfinishedTaskCounts;

    public ProjectDTO(String projectName, String projectCode, UserDTO assignedManager, LocalDate startDate, LocalDate endDate, String projectDetail, Status projectStatus) {
        this.projectName = projectName;
        this.projectCode = projectCode;
        this.assignedManager = assignedManager;
        this.startDate = startDate;
        this.endDate = endDate;
        this.projectDetail = projectDetail;
        this.projectStatus = projectStatus;
        /*
         * 🖍️...
         * · We create the custom constructor without the "completeTaskCounts" and "unfinishedTaskCounts" fields because we don't have those fields
         *   on the  Administration > Project Create page. If we add them to the constructor, we will get an error while saving the new project.
         * · We need custom constructor for Administration > Project Create page.
         *
         * · We need "completeTaskCounts" and "unfinishedTaskCounts" fields to be used for the Manager > Project Status page, so we have @AllArgsConstructor.
         */
    }

}
