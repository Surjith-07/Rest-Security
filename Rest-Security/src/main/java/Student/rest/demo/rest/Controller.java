package Student.rest.demo.rest;

import Student.rest.demo.dao.StudentRespository;
import Student.rest.demo.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

public class Controller {

    private StudentRespository studentRespository;
    @Autowired
    public Controller(StudentRespository studentRespository){
        this.studentRespository=studentRespository;
    }

    @PutMapping("/springss")
    public Student updateEmployee(@RequestBody Student theStudent) {

        Student dbEmployee = studentRespository.save(theStudent);

        return dbEmployee;
    }
}
