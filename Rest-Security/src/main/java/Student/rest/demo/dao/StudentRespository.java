package Student.rest.demo.dao;

import Student.rest.demo.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path="spring")
public interface StudentRespository extends JpaRepository<Student,Integer> {

}