
package uz.pdp.model;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
@AllArgsConstructor
@NoArgsConstructor
public class Task {

    @Expose
    private Boolean completed;
    @Expose
    private Long id;
    @Expose
    private String title;
    @Expose
    private Long userId;

    public Boolean getCompleted() {
        return completed;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public Long getUserId() {
        return userId;
    }

    public static class Builder {

        private Boolean completed;
        private Long id;
        private String title;
        private Long userId;

        public Task.Builder withCompleted(Boolean completed) {
            this.completed = completed;
            return this;
        }

        public Task.Builder withId(Long id) {
            this.id = id;
            return this;
        }

        public Task.Builder withTitle(String title) {
            this.title = title;
            return this;
        }

        public Task.Builder withUserId(Long userId) {
            this.userId = userId;
            return this;
        }

        public Task build() {
            Task task = new Task();
            task.completed = completed;
            task.id = id;
            task.title = title;
            task.userId = userId;
            return task;
        }

    }

    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
