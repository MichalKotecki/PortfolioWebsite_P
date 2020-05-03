package com.portfolio.portfolio.portfolio.Repository;

import com.portfolio.portfolio.portfolio.Models.Technology;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TechnologyRepository extends JpaRepository<Technology, Long> {
}
