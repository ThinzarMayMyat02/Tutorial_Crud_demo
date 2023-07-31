package com.sip.TutoriaCRUDTest.repository;

import java.util.List;

import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sip.TutoriaCRUDTest.entity.Tutorial;

@Repository
@Transactional
public interface TutorialRepo extends JpaRepository<Tutorial,Integer>{
    
    List<Tutorial> findByTitleContainingIgnoreCase(String keyword) ;

    @Query("UPDATE Tutorial t SET t.published =:published where t.id=:id")
    @Modifying
    public void updatePublishedStatus(Integer id, Boolean published);
}
