package com.example.tutorials.intern.controller;

import com.example.tutorials.intern.domain.Tutorial;
import com.example.tutorials.intern.repository.TutorialRepository;
import com.example.tutorials.intern.service.TutorialService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Tag(name = "Tutorial API", description = "Tutorial Data CRUD Sample")
//@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class TutorialController {

	@Autowired
    private TutorialRepository tutorialRepository;

	@Autowired
	private TutorialService tutorialService;

	@GetMapping("/tutorials")
	@Operation(summary = "Get All Tutorials", description = "모든 Tutorial을 조회한다.")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "성공"),
			@ApiResponse(responseCode = "204", description = "조회할 데이터가 없습니다."),
			@ApiResponse(responseCode = "500", description = "내부 서버 오류")
	})
	public ResponseEntity<List<Tutorial>> getAllTutorials(@RequestParam(required = false) String title) {
		try {
			List<Tutorial> tutorials = new ArrayList<Tutorial>();
			tutorials = tutorialService.getAllTutorials(title);

			if (tutorials.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(tutorials, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/tutorials/{id}")
	@Operation(summary = "Get Tutorial By ID", description = "ID로 Tutorial을 조회한다.")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "성공"),
			@ApiResponse(responseCode = "404", description = "데이터를 찾을 수 없습니다.")
	})
	public ResponseEntity<Tutorial> getTutorialById(@PathVariable("id") long id) {
		Tutorial tutorialData = new Tutorial();
		tutorialData = tutorialService.getTutorialById(id);

		if (tutorialData != null) {
			return new ResponseEntity<>(tutorialData, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/tutorials")
	@Operation(summary = "Create Tutorial", description = "Tutorial을 생성한다.")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "생성되었습니다."),
			@ApiResponse(responseCode = "500", description = "내부 서버 오류")
	})
	public ResponseEntity<Tutorial> createTutorial(@RequestBody Tutorial tutorial) {
		try {
			Tutorial _tutorial = tutorialService.createTutorial(tutorial);

			return new ResponseEntity<>(_tutorial, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/tutorials/{id}")
	@Operation(summary = "Update Tutorial", description = "Tutorial을 수정한다.")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "성공"),
			@ApiResponse(responseCode = "404", description = "데이터를 찾을 수 없습니다.")
	})
	public ResponseEntity<Tutorial> updateTutorial(@PathVariable("id") long id, @RequestBody Tutorial tutorial) {
		Tutorial _tutorial = tutorialService.updateTutorial(id, tutorial);

		if (_tutorial != null) {
			return new ResponseEntity<>(_tutorial, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/tutorials/{id}")
	@Operation(summary = "Delete Tutorial By ID", description = "ID로 Tutorial을 삭제한다.")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "204", description = "조회할 데이터가 없습니다."),
			@ApiResponse(responseCode = "500", description = "내부 서버 오류")
	})
	public ResponseEntity<HttpStatus> deleteTutorial(@PathVariable("id") long id) {
		try {
			tutorialService.deleteTutorial(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/tutorials")
	@Operation(summary = "Delete All Tutorial", description = "모든 Tutorial을 삭제한다.")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "204", description = "조회할 데이터가 없습니다."),
			@ApiResponse(responseCode = "500", description = "내부 서버 오류")
	})
	public ResponseEntity<HttpStatus> deleteAllTutorials() {
		try {
			tutorialService.deleteAllTutorials();
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@GetMapping("/tutorials/published")
	@Operation(summary = "Get Published Tutorial", description = "발행된 Tutorial을 조회한다.")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "성공"),
			@ApiResponse(responseCode = "204", description = "조회할 데이터가 없습니다."),
			@ApiResponse(responseCode = "500", description = "내부 서버 오류")
	})
	public ResponseEntity<List<Tutorial>> findByPublished() {
		try {
			List<Tutorial> tutorials = tutorialService.getPublishedTutorials();

			if (tutorials.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(tutorials, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
