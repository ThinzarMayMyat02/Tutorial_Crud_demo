package com.sip.TutoriaCRUDTest.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.sip.TutoriaCRUDTest.entity.Tutorial;

@Service
public interface TutorialService {

    public Tutorial findById(Integer id);

    public List<Tutorial> findAll();

    public void saveTutorial(Tutorial tutorial);

    public void deleteById(Integer id);

    public void updatePublishedStatus(Integer id, boolean published);

    public List<Tutorial> findByTitleContainingIgnoreCase(String keyword);

    

}
