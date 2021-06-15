package com.tasksApi.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tasksApi.repository.TaskRepository;
import com.tasksApi.entity.Task;

@RestController
public class TaskController {

	@Autowired
    private TaskRepository _taskRepository;
	
    @RequestMapping(value = "/pessoa", method = RequestMethod.GET)
    public List<Task> Get() {
        return _taskRepository.findAll();
    }

    @RequestMapping(value = "/pessoa/{id}", method = RequestMethod.GET)
    public ResponseEntity<Task> GetById(@PathVariable(value = "id") long id)
    {
        Optional<Task> task = _taskRepository.findById(id);
        if(task.isPresent())
            return new ResponseEntity<Task>(task.get(), HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/pessoa", method =  RequestMethod.POST)
    public Task Post(@Valid @RequestBody Task task)
    {
        return _taskRepository.save(task);
    }

    @RequestMapping(value = "/pessoa/{id}", method =  RequestMethod.PUT)
    public ResponseEntity<Task> Put(@PathVariable(value = "id") long id, @Valid @RequestBody Task newTask)
    {
        Optional<Task> oldTask = _taskRepository.findById(id);
        if(oldTask.isPresent()){
            Task task = oldTask.get();
            task.setDescription(newTask.getDescription());
            _taskRepository.save(task);
            return new ResponseEntity<Task>(task, HttpStatus.OK);
        }
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/pessoa/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> Delete(@PathVariable(value = "id") long id)
    {
        Optional<Task> task = _taskRepository.findById(id);
        if(task.isPresent()){
        	_taskRepository.delete(task.get());
            return new ResponseEntity<>(HttpStatus.OK);
        }
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
