package com.Jagritgill.ppmtool.web;

import com.Jagritgill.ppmtool.domain.Project;
import com.Jagritgill.ppmtool.services.MapValidationErrorService;
import com.Jagritgill.ppmtool.services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/api/project")   //endpoint

public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @Autowired
    private MapValidationErrorService mapValidationErrorService;

    //Posting to the server
    @PostMapping("")
    public ResponseEntity<?> createNewProject(@Valid @RequestBody Project project, BindingResult result) {
        //this calls the method from MapValidationErrorService called errorMap
        ResponseEntity<?> errorMap = mapValidationErrorService.MapValidationService(result);
        // if it contains errors the return errorMap
        if(errorMap!=null) return errorMap;

        // this saves it to the database
        Project project1 = projectService.saveOrUpdateProject(project);
        return new ResponseEntity<Project>(project, HttpStatus.CREATED);
    }

    //Getting from the server
    @GetMapping("/{projectId}")
    public ResponseEntity<?> getProjectById(@PathVariable String projectId){

        Project project = projectService.findProjectByIdentifer(projectId);

        return new ResponseEntity<Project>(project, HttpStatus.OK);
    }
}
