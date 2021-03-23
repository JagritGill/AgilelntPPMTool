package com.Jagritgill.ppmtool.services;

import com.Jagritgill.ppmtool.domain.Backlog;
import com.Jagritgill.ppmtool.domain.Project;
import com.Jagritgill.ppmtool.exceptions.ProjectIdException;
import com.Jagritgill.ppmtool.repositories.BacklogRepository;
import com.Jagritgill.ppmtool.repositories.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.Locale;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private BacklogRepository backlogRepository;

    public Project saveOrUpdateProject(Project project){

        //Logic
        try {
            project.setProjectIdentifier(project.getProjectIdentifier().toUpperCase());

            if(project.getId()==null){
                Backlog backlog = new Backlog();
                project.setBacklog(backlog);
                backlog.setProject(project);
                backlog.setProjectIdentifier(project.getProjectIdentifier().toUpperCase());
            }

            if(project.getId()!=null){
                project.setBacklog(backlogRepository.findByProjectIdentifier(project.getProjectIdentifier().toUpperCase()));
            }

            return projectRepository.save(project);

        }catch (Exception e){
            throw new ProjectIdException("Project ID '" +project.getProjectIdentifier().toUpperCase()+"' already exists");
        }

    }


    public Project findProjectByIdentifer(String projectId){

        Project project = projectRepository.findByProjectIdentifier(projectId.toUpperCase());

        if(project == null){
            throw new ProjectIdException("Project ID '" +projectId+"' does not exist");
        }

        return project;
    }

    public Iterable<Project> findAllProjects(){
        return projectRepository.findAll();
    }
    //this deletes all the project
    public void deleteProjectByIdentifier(String projectid){
        //first finding the project
        Project project = projectRepository.findByProjectIdentifier(projectid.toUpperCase());

        if(project == null){
            throw new ProjectIdException("Cannot Project with ID'"+projectid+"'. This Project does not exist");
        }

        projectRepository.delete(project);
    }
}
