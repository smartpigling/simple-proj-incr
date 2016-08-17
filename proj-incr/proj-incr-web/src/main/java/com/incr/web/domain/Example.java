package com.incr.web.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

@Entity
@Table(name = "T_EXAMPLE")
public class Example implements Serializable {

	public static Specification<Example> builderSearchWhereClause(final Map<String, Object> criteria) {
		return new Specification<Example>() {
			@Override
			public Predicate toPredicate(Root<Example> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicate = new ArrayList<Predicate>();

				if (!StringUtils.isEmpty(criteria.getOrDefault("name", ""))) {
					predicate.add(cb.like(root.get("name").as(String.class), "%" + criteria.get("name") + "%"));
				}

				// if(!StringUtils.isEmpty(criteria.getOrDefault("enabled","false"))){
				// predicate.add(cb.equal(root.get("enabled").as(Boolean.class),
				// Boolean.parseBoolean(criteria.getOrDefault("enabled","false").toString())));
				// }

				Predicate[] pre = new Predicate[predicate.size()];
				return query.where(predicate.toArray(pre)).getRestriction();
			}
		};
	}

	private static final long serialVersionUID = -4455004544801544612L;

	@Id
	@GeneratedValue(generator = "generator")
	@GenericGenerator(name = "generator", strategy = "uuid.hex")
	@Column(name = "ID", unique = true, nullable = false, length = 36)
	private String id;

	private String name;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
