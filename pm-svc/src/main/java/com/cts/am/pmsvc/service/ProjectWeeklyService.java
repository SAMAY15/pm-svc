package com.cts.am.pmsvc.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cts.am.pmsvc.model.ProjectWeekly;

@Service
public interface ProjectWeeklyService {
	
	void addActiveProjects(ProjectWeekly project);
	void updateActiveProject(ProjectWeekly project);
	
	List<ProjectWeekly> getAllActiveProjects();
	List<ProjectWeekly> getAllProjects();
	List<ProjectWeekly> getActiveProjectsByPoc(Long pocID);
	List<ProjectWeekly> findByProjectId(Long pid);
}
