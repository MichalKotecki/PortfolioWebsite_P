package com.portfolio.portfolio.portfolio.Repository;

import com.portfolio.portfolio.portfolio.Models.Project;
import com.portfolio.portfolio.portfolio.Models.Technology;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
}
