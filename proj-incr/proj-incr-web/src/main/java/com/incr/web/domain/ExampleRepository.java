package com.incr.web.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ExampleRepository extends JpaRepository<Example, String>, JpaSpecificationExecutor<Example> {

	// @Query(value = "select * from name=?1)", nativeQuery = true)
	// public List<Example> findExamplesByname(String name);
}
