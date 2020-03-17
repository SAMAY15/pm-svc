package com.cts.am.pmsvc.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cts.am.pmsvc.model.ProjectWeekly;
import com.cts.am.pmsvc.repository.ProjectWeeklyRepository;

@Component
public class ProjectWeeklyServiceImpl implements ProjectWeeklyService{
	
	@Autowired
	private ProjectWeeklyRepository projectweeklyrepository;
	
	Calendar cal = Calendar.getInstance();
	
	@Override
	public void addActiveProjects(ProjectWeekly project) {
		String tablename = "WK"+(cal.get(Calendar.WEEK_OF_YEAR))+"Y"+(cal.get(Calendar.YEAR));
		project.setTableName(tablename);
		projectweeklyrepository.save(project);
	}
	
	@Override
	public List<ProjectWeekly> getAllProjects() {
		return (List<ProjectWeekly>)this.projectweeklyrepository.findAll();
	}
	
	@Override
	public List<ProjectWeekly> getAllActiveProjects() {
		List<ProjectWeekly> projectById = (List<ProjectWeekly>) this.projectweeklyrepository.findAll();	
		List<ProjectWeekly> activeProjects = new  ArrayList<>();
		
		projectById.stream()
			.forEach(project -> {
				if(project.getStatus().equals("active")) {
					activeProjects.add(project);
				}
			});
		return activeProjects;
	}
	
	@Override
	public List<ProjectWeekly> getActiveProjectsByPoc(Long pocID) {
		List<ProjectWeekly> projectById = (List<ProjectWeekly>) this.projectweeklyrepository.findAll();
		List<ProjectWeekly> activeProjects = new  ArrayList<>();
		
		projectById.stream()
			.forEach(project -> {
				if(project.getPocID().equals(pocID) && project.getStatus().equals("active")) {
					activeProjects.add(project);
				}
			});
		return activeProjects;
	}
	
	@Override
	public void updateActiveProject(ProjectWeekly project) {
			String tablename = "WK"+(cal.get(Calendar.WEEK_OF_YEAR))+"Y"+(cal.get(Calendar.YEAR));
			project.setTableName(tablename);
			projectweeklyrepository.save(project);
	}

	@Override
	public List<ProjectWeekly> findByProjectId(Long pid) {
		
		List<ProjectWeekly> projectById = (List<ProjectWeekly>) this.projectweeklyrepository.findAll();
		List<ProjectWeekly> availProjects = new  ArrayList<>();
		
		projectById.stream()
		.forEach(project -> {
			if(project.getProjectId().equals(pid)) {
				availProjects.add(project);
			}
		});
		return availProjects;
	}


}
