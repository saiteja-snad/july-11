//package com.example.LMS.Controllers;
//
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.prepost.PreAuthorize;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.example.LMS.Entity.ClassEntity;
//import com.example.LMS.Services.ClassService;
//
//@RestController
//@RequestMapping("/api/classes")
//public class ClassController {
//
//    @Autowired
//    private ClassService classService;
//
//    @PostMapping
//    @PreAuthorize("hasRole('ADMIN')")
//    public ResponseEntity<ClassEntity> createClass(
//            @RequestBody ClassEntity classEntity) {
//        return ResponseEntity.ok(
//                classService.createClass(classEntity));
//    }
//
//    // Any authenticated user may view classes/sections
//    @GetMapping
//    public ResponseEntity<List<ClassEntity>> getAllClasses() {
//        return ResponseEntity.ok(
//                classService.getAllClasses());
//    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity<ClassEntity> getClass(@PathVariable Integer id) {
//        return ResponseEntity.ok(
//                classService.getClassById(id));
//    }
//}
