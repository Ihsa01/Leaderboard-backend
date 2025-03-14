package com.leaderboard.demo.controller;

import com.leaderboard.demo.entity.College;
import com.leaderboard.demo.service.CollegeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController

@RequestMapping("/api/colleges")
public class CollegeController {

    @Autowired
    private CollegeService collegeService;


    @GetMapping
    public ResponseEntity<List<College>> getAllColleges() {
        List<College> colleges = collegeService.getAllColleges();
        return new ResponseEntity<>(colleges, HttpStatus.OK);
    }


    @GetMapping("/{id}")
    public ResponseEntity<College> getCollegeById(@PathVariable UUID id) {
        College college = collegeService.getCollegeById(id);
        return college != null ? new ResponseEntity<>(college, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    @PostMapping
    public ResponseEntity<College> createCollege(@RequestBody College college) {
        College savedCollege = collegeService.saveCollege(college);
        return new ResponseEntity<>(savedCollege, HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<College> updateCollege(@PathVariable UUID id, @RequestBody College college) {
        College updatedCollege = collegeService.updateCollege(id, college);
        return updatedCollege != null ? new ResponseEntity<>(updatedCollege, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }



    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteCollege(@PathVariable UUID id) {
        boolean deleted = collegeService.softDeleteCollege(id); // ✅ Use instance method correctly

        Map<String, String> response = new HashMap<>();
        response.put("status", "200");

        if (deleted) {
            response.put("message", "College deleted successfully");
        } else {
            response.put("message", "No such college");
        }

        return ResponseEntity.ok(response);
    }


}
