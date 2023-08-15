package uz.pdp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Task {
    private Integer id;
    private String title;
    private String description;
    private boolean status;
    private LocalDateTime deadline;
    private LocalDateTime created_at = LocalDateTime.now();
    private LocalDateTime updated_at = LocalDateTime.now();
    private Integer userId;

    public Task(Integer id, String title, String description, boolean status, LocalDateTime deadline, LocalDateTime created_at, LocalDateTime updated_at) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.status = status;
        this.deadline = deadline;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }
}
