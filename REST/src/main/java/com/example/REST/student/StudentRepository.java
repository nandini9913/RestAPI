package com.example.REST.student;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
//JPA Repository class that reads students from a database.
//JpaRepository extends CrudRepository and PagingAndSortingRepository so it has access to all its methods
@Repository
public interface StudentRepository extends JpaRepository<Student,Long>{

}
