package bto.Entities;

import java.util.ArrayList;
import java.util.List;
import bto.Enums.*;
import bto.EntitiesProjectRelated.*;

public class HDBOfficer extends User {
	 private List<Project> assignedProjects;
	 private List<OfficerRegistration> registrations;
	 private Applicant applicantRole; // Composition - HDBOfficer has an Applicant role
	 
	 // Constructors
	 public HDBOfficer() {
	     super();
	     assignedProjects = new ArrayList<>();
	     registrations = new ArrayList<>();
	     // Create the applicant role with the same user details
	     applicantRole = new Applicant();
	 }
	 
	 public HDBOfficer(String nric, String password, int age, MaritalStatus maritalStatus, String name) {
	     super(nric, password, age, maritalStatus, name);
	     assignedProjects = new ArrayList<>();
	     registrations = new ArrayList<>();
	     // Create the applicant role with the same user details
	     applicantRole = new Applicant(nric, password, age, maritalStatus, name);
	 }
	 
	 // Officer-specific Methods
	 public boolean registerForProject(Project project) {
	     // Check if the officer has applied for this project as an applicant
	     if (applicantRole.getAppliedProject() != null && 
	         applicantRole.getAppliedProject().getProject().equals(project)) {
	         // Cannot register for a project that the officer has applied for
	         return false;
	     }
	     
	     // Implementation for registering for a project
	     return false; // Placeholder
	 }
	 
	 public String viewRegistrationStatus() {
	     // Implementation to view registration status
	     return ""; // Placeholder
	 }
	 
	 public boolean updateFlatAvailability(FlatType flatType) {
	     // Implementation to update flat availability
	     return false; // Placeholder
	 }
	 
	 public ProjectApplication retrieveApplication(String nric) {
	     // Check if the officer is trying to access their own application
	     if (nric.equals(this.getNric())) {
	         // Not allowed to process their own application
	         return null;
	     }
	     
	     // Implementation to retrieve application by NRIC
	     return null; // Placeholder
	 }
	 
	 public boolean updateBookingStatus(ProjectApplication application, FlatType flatType) {
	     // Check if the application belongs to the officer
	     if (application.getApplicant().getNric().equals(this.getNric())) {
	         // Not allowed to update their own application
	         return false;
	     }
	     
	     // Implementation to update booking status
	     return false; // Placeholder
	 }
	 
	 public String generateReceipt(ProjectApplication application) {
	     // Check if the application belongs to the officer
	     if (application.getApplicant().getNric().equals(this.getNric())) {
	         // Not allowed to generate receipt for their own application
	         return null;
	     }
	     
	     // Implementation to generate receipt
	     return ""; // Placeholder
	 }
	 
	 public boolean respondToEnquiry(Enquiry enquiry) {
	     // Check if the enquiry belongs to the officer
	     if (enquiry.getApplicant().getNric().equals(this.getNric())) {
	         // Not allowed to respond to their own enquiry
	         return false;
	     }
	     
	     // Implementation to respond to enquiry
	     return false; // Placeholder
	 }
	 
	 public String displayProfile() {
	     // Implementation to display profile
	     StringBuilder profile = new StringBuilder(getDetails());
	     
	     // Add officer-specific details
	     profile.append("\nOFFICER ROLE");
	     profile.append("\nAssigned Projects: ").append(assignedProjects.size());
	     
	     // Add applicant-specific details
	     profile.append("\n\nAPPLICANT ROLE");
	     if (applicantRole.getAppliedProject() != null) {
	         profile.append("\nCurrent Application: ").append(applicantRole.getAppliedProject().getProject().getProjectName());
	         profile.append("\nApplication Status: ").append(applicantRole.getAppliedProject().getStatus());
	     } else {
	         profile.append("\nNo current applications");
	     }
	     
	     return profile.toString();
	 }
	 
	 public List<Project> getAssignedProjectDetails() {
	     return assignedProjects;
	 }
	 
	 // Applicant role delegation methods
	 public boolean applyForProject(Project project) {
	     // Check if the officer is assigned to this project
	     for (Project assignedProject : assignedProjects) {
	         if (assignedProject.equals(project)) {
	             // Cannot apply for a project that the officer is assigned to
	             return false;
	         }
	     }
	     
	     // Delegate to the applicant role
	     return applicantRole.applyForProject(project);
	 }
	 
	 
	 public ApplicationStatus viewApplicationStatus() {
	     // Delegate to the applicant role
	     return applicantRole.viewApplicationStatus();
	 }
	 
	 public boolean requestWithdrawal() {
	     // Delegate to the applicant role
	     return applicantRole.requestWithdrawal();
	 }

	 // Getters and Setters
	 public List<Project> getAssignedProjects() {
	     return assignedProjects;
	 }
	
	 public void setAssignedProjects(List<Project> assignedProjects) {
	     this.assignedProjects = assignedProjects;
	 }
	
	 public List<OfficerRegistration> getRegistrations() {
	     return registrations;
	 }
	
	 public void setRegistrations(List<OfficerRegistration> registrations) {
	     this.registrations = registrations;
	 }
	 
	 public Applicant getApplicantRole() {
	     return applicantRole;
	 }
	 
	 public void setApplicantRole(Applicant applicantRole) {
	     this.applicantRole = applicantRole;
	 }
	 
	 // Helper methods
	 public void addAssignedProject(Project project) {
	     if (!assignedProjects.contains(project)) {
	         assignedProjects.add(project);
	     }
	 }
	 
	 public void addRegistration(OfficerRegistration registration) {
	     if (!registrations.contains(registration)) {
	         registrations.add(registration);
	     }
	 }
}