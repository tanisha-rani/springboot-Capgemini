package com.BasicsOfSpring.SpringBasics;

import jakarta.transaction.Transactional;
import org.jspecify.annotations.Nullable;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public interface EmployeeJPARepository extends JpaRepository<Employee, Integer> {

    public Employee getByName(String name);
    //select * from employee where name :name;
    //getBy, findby, readby

    public Employee getBySalary(double salary);
    public  Employee getByNameAndSalary(String name, double salary);


    @Modifying
    @Transactional
    @Query(value="update employee set salary=:newsalary where salary=:oldsalary",nativeQuery = true)
    public int updateBySalary(@Param("oldsalary")double oldsalary, @Param ("newsalary")double newsalary);

}
