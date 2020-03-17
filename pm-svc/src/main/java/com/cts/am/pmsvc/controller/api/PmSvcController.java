package com.cts.am.pmsvc.controller.api;

import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.cts.am.pmsvc.model.ProjectWeekly;
import com.cts.am.pmsvc.service.ProjectWeeklyService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@CrossOrigin(origins = "http://localhost:8091", allowedHeaders = "*")
@RestController
@RequestMapping("/api/pm")
public class PmSvcController {
	
	@Autowired
	private ProjectWeeklyService projectweeklyservice;
	ObjectMapper objectMapper = new ObjectMapper();
	ProjectWeekly project = new ProjectWeekly();
	Calendar cal = Calendar.getInstance();
	
	//Add new project
	@CrossOrigin
	@PostMapping(value="/addWeeklyreporttable",consumes = MediaType.ALL_VALUE)
	public ResponseEntity<Object> addProject (@RequestBody String jsondata) throws JsonMappingException, JsonProcessingException {
		
		project = objectMapper.readValue(jsondata, ProjectWeekly.class);
		String tablename = "WK"+(cal.get(Calendar.WEEK_OF_YEAR))+"Y"+(cal.get(Calendar.YEAR));	
		project.setTableName(tablename);
		List<ProjectWeekly> availProject = projectweeklyservice.findByProjectId(project.getProjectId());
		
		if(!availProject.isEmpty()) {
			return new ResponseEntity<> ("Project already registered",HttpStatus.ALREADY_REPORTED);
		}
		projectweeklyservice.addActiveProjects(project);
		return new ResponseEntity<Object> ("Project successfully added",HttpStatus.CREATED);
	}
	
	//Search by poc Id
	@CrossOrigin
	@PostMapping(value="/getActiveProject", consumes = MediaType.ALL_VALUE)
	public ResponseEntity<Object> getAllActiveProjectDtlsByPOC (@RequestBody String jsondata) throws JsonMappingException, JsonProcessingException {
		
		project = objectMapper.readValue(jsondata, ProjectWeekly.class);
		List<ProjectWeekly> activeProject = projectweeklyservice.getActiveProjectsByPoc(project.getPocID());
		if(!activeProject.isEmpty()) {
			return new ResponseEntity<Object>(activeProject,HttpStatus.OK);
		}
		return new ResponseEntity<Object> (activeProject,HttpStatus.NO_CONTENT);
	}
	
	@CrossOrigin
	@PostMapping(value="/getProjectDetailsByPid", consumes = MediaType.ALL_VALUE)
	public ResponseEntity<Object> getAllActiveProjectDtlsByPid (@RequestBody String jsondata) throws JsonMappingException, JsonProcessingException {
		
		project = objectMapper.readValue(jsondata, ProjectWeekly.class);
		List<ProjectWeekly> activeProject = projectweeklyservice.findByProjectId(project.getProjectId());
		if(!activeProject.isEmpty()) {
			return new ResponseEntity<Object>(activeProject,HttpStatus.OK);
		}
		return new ResponseEntity<Object> (activeProject,HttpStatus.NO_CONTENT);
	}
	
	//Get all the projects
	@CrossOrigin
	@GetMapping(value="getAllProjects", produces="application/json")
	public ResponseEntity<Object> getAllProjectDtls () {
		
		List<ProjectWeekly> activeProject = projectweeklyservice.getAllProjects();

		if(!activeProject.isEmpty()) {
			return new ResponseEntity<Object>(activeProject,HttpStatus.OK);
		}
		return new ResponseEntity<Object> (activeProject,HttpStatus.NO_CONTENT);
	}
	
	//get all the active projects
	@CrossOrigin
	@GetMapping(value="getAllActiveProjects", produces="application/json")
	public ResponseEntity<Object> getAllActiveProjectDtls () {
		
		List<ProjectWeekly> activeProject = projectweeklyservice.getAllActiveProjects();
		
		if(!activeProject.isEmpty()) {
			return new ResponseEntity<Object>(activeProject,HttpStatus.OK);
		}
		return new ResponseEntity<Object> (activeProject,HttpStatus.NO_CONTENT);
	}
	
	@CrossOrigin
	@PostMapping(value="/updateweeklyreporttable", consumes = MediaType.ALL_VALUE)
	public ResponseEntity<Object> updateProject (@RequestBody String jsondata) throws JsonMappingException, JsonProcessingException {
		
		project = objectMapper.readValue(jsondata, ProjectWeekly.class);
		List<ProjectWeekly> availProject = projectweeklyservice.findByProjectId(project.getProjectId());
		
		if(!availProject.isEmpty() ) {
			 projectweeklyservice.updateActiveProject(project);
			 return new ResponseEntity<Object> ("project updated",HttpStatus.CREATED);
		}
		return new ResponseEntity<Object> ("project not availble",HttpStatus.NO_CONTENT);
	}
}