package com.example.tutorials.intern.service.impl;

import com.example.tutorials.intern.domain.Tutorial;
import com.example.tutorials.intern.repository.TutorialRepository;
import com.example.tutorials.intern.service.TutorialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TutorialServiceImpl implements TutorialService {

    @Autowired
    private TutorialRepository tutorialRepository;

//    public List<Tutorial> getAllTutorialsWithoutTitle() {
////        tutorialRepository.findAll().forEach(tutorials::add);
//        List<Tutorial> tutorials = tutorialRepository.findAll();
//
//        return tutorials;
//    }
//
//    public List<Tutorial> getAllTutorialsWithTitle(String title) {
////        tutorialRepository.findByTitleContaining(title).forEach(tutorials::add);
//        List<Tutorial> tutorials = tutorialRepository.findByTitleContaining(title);
//
//        return tutorials;
//    }

    public List<Tutorial> getAllTutorials(String title) {
        List<Tutorial> tutorials = new ArrayList<Tutorial>();

        if (title == null) {
            tutorials = tutorialRepository.findAll();
        } else {
            tutorials = tutorialRepository.findByTitleContaining(title);
        }

        return tutorials;
    }

    public Tutorial getTutorialById(long id) {
        Optional<Tutorial> tutorialData = tutorialRepository.findById(id);

        if (tutorialData.isPresent()) {
            return tutorialData.get();
        } else {
            return null;
        }
    }

    public Tutorial createTutorial(Tutorial tutorial) {
        Tutorial _tutorial = tutorialRepository.save(new Tutorial(tutorial.getTitle(), tutorial.getDescription(), false));

        return _tutorial;
    }

    public Tutorial updateTutorial(long id, Tutorial tutorial) {

        Optional<Tutorial> tutorialData = tutorialRepository.findById(id);

        if (tutorialData.isPresent()) {
            Tutorial _tutorial = tutorialData.get();

            _tutorial.setTitle(tutorial.getTitle());
            _tutorial.setDescription(tutorial.getDescription());
            _tutorial.setPublished(tutorial.isPublished());

            return tutorialRepository.save(_tutorial);
        } else {
            return null;
        }
    }

    public void deleteTutorial(long id) {
        tutorialRepository.deleteById(id);
    }

    public void deleteAllTutorials() {
        tutorialRepository.deleteAll();
    }

    public List<Tutorial> getPublishedTutorials() {
        return tutorialRepository.findByPublished(true);
    }

}
