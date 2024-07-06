package com.example.tutorials.intern.service;

import com.example.tutorials.intern.domain.Tutorial;

import java.util.List;

public interface TutorialService {

//    List<Tutorial> getAllTutorialsWithoutTitle();
//    List<Tutorial> getAllTutorialsWithTitle(String title);
    List<Tutorial> getAllTutorials(String title);
    Tutorial getTutorialById(long id);
    Tutorial createTutorial(Tutorial tutorial);
    Tutorial updateTutorial(long id, Tutorial tutorial);
    void deleteTutorial(long id);
    void deleteAllTutorials();
    List<Tutorial> getPublishedTutorials();
}
