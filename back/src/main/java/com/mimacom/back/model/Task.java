package com.mimacom.back.model;

import javax.persistence.*;

@Entity
@Table(name = "Tasks")
public class Task {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    @Column(name = "finished", nullable = false)
    private Boolean finished;

    @Column(name = "title", nullable = false, length = 64)
    private String title;

    @Column(name = "description", nullable = false, length = 128)
    private String description;

    public Task() {
        title = "";
        finished = false;
    }

    @Override
    public String toString() {
        return "Task [id=" + id + "]";
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getFinished() {
        return finished;
    }

    public void setFinished(Boolean finished) {
        this.finished = finished;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
