package com.incr.web.service;

import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.incr.web.domain.Example;
import com.incr.web.domain.ExampleRepository;

@Service
@Transactional
public class ExampleService {

	@Autowired
	private ExampleRepository exampleRepository;

	public Page<Example> findExamples(Map<String, Object> criteria, Pageable pageable) {
		return exampleRepository.findAll(Example.builderSearchWhereClause(criteria), pageable);
	}

	public void saveExample(Example e) {
		exampleRepository.save(e);
	}

	public Example getExample(String id) {
		return exampleRepository.getOne(id);
	}

	public void delExample(String id) {
		exampleRepository.delete(id);
	}

	@PersistenceContext
	private EntityManager em;

	/**
	 * 自定义查询
	 * 
	 * @param pageable
	 * @param params
	 * @return
	 */
	public Page<Example> findCustomSearch(Pageable pageable, String name) {
		StringBuffer hqlString = new StringBuffer();
		hqlString.append(" select u from Example u where 1=1 ");
		if (!StringUtils.isEmpty(name)) {
			hqlString.append(" u.name = '").append(name).append("'");
		}
		TypedQuery<Example> query = em.createQuery(hqlString.toString(), Example.class);
		query.setFirstResult(pageable.getOffset());
		query.setMaxResults(pageable.getPageSize());

		return new PageImpl<Example>(query.getResultList());
	}
}
