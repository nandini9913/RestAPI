package com.example.REST.student;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import jakarta.validation.Valid;

@RestController

public class StudentResource {

	@Autowired
	private StudentRepository studentRepository; 
	
	@GetMapping("/students")
	public List<Student> retrieveAllStudent()
	{
		return studentRepository.findAll();
	}
	@GetMapping("/students/{Id}")
	public ResponseEntity<Object> retrieveStudentById(@PathVariable long Id)
	{
		Optional<Student> student = studentRepository.findById(Id);
		if(student.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
					
		return ResponseEntity.ok(student);
	}
	@DeleteMapping("/students/{Id}")
	public ResponseEntity<Object> deleteStudentById(@PathVariable long Id){
		Optional<Student> student = studentRepository.findById(Id);
		if(student.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		else
		{
		studentRepository.deleteById(Id);
		return ResponseEntity.ok("Record Deleted");
		}
	}
	
	@PostMapping("/students")
	public ResponseEntity<Object> addNewStudent(@Valid @RequestBody Student student) {
		Student NewStudent = studentRepository.save(student);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(NewStudent.getId()).toUri();
		return ResponseEntity.created(location).build();
	}
	@PutMapping("/students/{id}")
	public ResponseEntity<Object> UpdateStudent(@PathVariable Long id,@RequestBody Student student){
		Optional<Student> UpdateStudent = studentRepository.findById(id);
		if(UpdateStudent.isEmpty())
			return ResponseEntity.notFound().build();
		student.setId(id);
		studentRepository.save(student);
		return ResponseEntity.noContent().build();
	}
	
}
