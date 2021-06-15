package com.tasksApi.entity;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.boot.autoconfigure.domain.EntityScan;

@EntityScan
public class Task {
    
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    
	@Column(nullable = false)
    private String description;
    
	@Column(nullable = false)
    private Boolean isDone;
	
    public String getDescription() {
        return description;
    }
    
    public Boolean getIsDone() {
        return isDone;
    }
    
    public void setDescription(String description) {
    	this.description = description;
    }
    
    public void setIsDone() {
    	this.isDone = true;
    }

}
