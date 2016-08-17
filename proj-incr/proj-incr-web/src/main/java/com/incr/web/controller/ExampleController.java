package com.incr.web.controller;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.incr.web.domain.Example;
import com.incr.web.service.ExampleService;
import com.incr.web.utils.PageWrapper;

@RestController
public class ExampleController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	ExampleService exampleService;

	@RequestMapping("example/new")
	public String newExample(Model model) {
		model.addAttribute("example", new Example());
		return "user/exampleform";
	}

	@RequestMapping(value = "example", method = RequestMethod.POST)
	public String saveExample(Example e) {
		exampleService.saveExample(e);
		return "redirect:/example/" + e.getId();
	}

	@RequestMapping("example/{id}")
	public String showExample(@PathVariable String id, Model model) {
		model.addAttribute("example", exampleService.getExample(id));
		return "example/exampleshow";
	}

	@RequestMapping(value = "/examples", method = RequestMethod.GET)
	public String listExample(@PageableDefault(sort = { "createdTime" }, direction = Direction.ASC) Pageable pageable,
			@RequestParam Map<String, Object> criteria, Model model) {
		logger.info(String.format("criteria is: %s", criteria));
		PageWrapper<Example> page = new PageWrapper<Example>(exampleService.findExamples(criteria, pageable),
				"/examples");
		model.addAttribute("page", page);
		model.addAttribute("criteria", criteria);
		return "example/examples";
	}

	@RequestMapping("example/edit/{id}")
	public String editExample(@PathVariable String id, Model model) {
		model.addAttribute("example", exampleService.getExample(id));
		return "example/exampleform";
	}

	@RequestMapping("example/delete/{id}")
	public String deleteUser(@PathVariable String id) {
		exampleService.delExample(id);
		return "redirect:/examples";
	}
}
