package com.editay.bsps.controllers;

import java.util.*;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.editay.bsps.models.AdminActivities;
import com.editay.bsps.repository.AdminActivitiesRepository;

@CrossOrigin(origins = { "*" }, maxAge = 3600L)
@RestController
@RequestMapping({ "/api/adminactivities" })
public class AdminActivitiesController {

	/*
	 * @Autowired private AdminActivitiesRepository adminActivitiesRepository;
	 * 
	 * @GetMapping("/adminactivities") public List getAllAdminactivities() { return
	 * adminActivitiesRepository.findAll(); }
	 * 
	 * @GetMapping("/adminactivities/{id}") public AdminActivities
	 * getAdminActivityByID(@PathVariable Long id) { Optional<AdminActivities>
	 * optAdminActivities = adminActivitiesRepository.findById(id); if
	 * (optAdminActivities.isPresent()) { return optAdminActivities.get(); } else {
	 * return null;
	 * 
	 * }
	 * 
	 * }
	 * 
	 * @PostMapping("/adminactivities") public AdminActivities
	 * createAdminActivities(@Valid @RequestBody AdminActivities adminActivities) {
	 * return adminActivitiesRepository.save(adminActivities); }
	 * 
	 * @PutMapping("/adminactivities/{id}") public AdminActivities
	 * updateAdminActivities(@PathVariable Long id, @Valid @RequestBody
	 * AdminActivities adminActivitiesUpdated) { return
	 * adminActivitiesRepository.findById(id).map(adminActivity -> {
	 * adminActivity.setNameOfDeletedItem(adminActivitiesUpdated.
	 * getNameOfDeletedItem());
	 * adminActivity.setItemDeletedId(adminActivitiesUpdated.getItemDeletedId());
	 * adminActivity.setDeletedBy(adminActivitiesUpdated.getDeletedBy());
	 * 
	 * return adminActivitiesRepository.save(adminActivity); }).orElseThrow(() ->
	 * new NotFoundException("Student not found with id " + id)); }
	 * 
	 * @DeleteMapping("/students/{id}") public String deleteStudent(@PathVariable
	 * Long id) { return studentRepository.findById(id).map(student -> {
	 * studentRepository.delete(student); return "Delete Successfully!";
	 * }).orElseThrow(() -> new NotFoundException("Student not found with id " +
	 * id)); }
	 */
}
