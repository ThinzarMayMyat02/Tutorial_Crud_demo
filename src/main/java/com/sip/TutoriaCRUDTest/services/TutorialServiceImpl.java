package com.sip.TutoriaCRUDTest.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sip.TutoriaCRUDTest.entity.Tutorial;
import com.sip.TutoriaCRUDTest.repository.TutorialRepo;

@Service
public class TutorialServiceImpl implements TutorialService {

   @Autowired
   private TutorialRepo tutorialRepo;

   @Override
   public List<Tutorial> findAll() {
      List<Tutorial> tutorials = tutorialRepo.findAll();
      return tutorials;
   }

   @Override
   public void saveTutorial(Tutorial tutorial) {
      tutorialRepo.save(tutorial);
   }

   @Override
   public void deleteById(Integer id) {
      tutorialRepo.deleteById(id);
   }

   @Override
   public void updatePublishedStatus(Integer id, boolean published) {
      tutorialRepo.updatePublishedStatus(id, published);
   }

   @Override
   public Tutorial findById(Integer id) {
      Tutorial tutorial = tutorialRepo.findById(id).get();
      return tutorial;
   }

   @Override
   public List<Tutorial> findByTitleContainingIgnoreCase(String keyword) {
      List<Tutorial> findTutorials = tutorialRepo.findByTitleContainingIgnoreCase(keyword);
      return findTutorials;
   }

}
