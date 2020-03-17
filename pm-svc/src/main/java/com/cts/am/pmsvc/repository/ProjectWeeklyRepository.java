package com.cts.am.pmsvc.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cts.am.pmsvc.model.ProjectWeekly;

@Repository
@Transactional
public interface ProjectWeeklyRepository extends CrudRepository<ProjectWeekly, Long> {}
