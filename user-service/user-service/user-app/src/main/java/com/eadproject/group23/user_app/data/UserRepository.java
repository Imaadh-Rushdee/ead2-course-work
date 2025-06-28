package com.eadproject.group23.user_app.data;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<userdata, Integer>{

    List<userdata> findByName(String name);
    List<userdata> findByGrade(String grade);
    Optional<userdata> findByNameAndPassword(String name, String password);
    @Query("SELECT DISTINCT u.grade FROM userdata u WHERE u.grade IS NOT NULL")
    List<String> findDistinctGrades();


}
