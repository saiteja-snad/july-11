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
//import com.example.LMS.Entity.Department;
//import com.example.LMS.Services.DepartmentService;
//
//@RestController
//@RequestMapping("/api/departments")
//public class DepartmentController {
//
//    @Autowired
//    private DepartmentService departmentService;
//    @PostMapping
//    @PreAuthorize("hasRole('ADMIN')")
//    public ResponseEntity<Department> createDepartment(
//            @RequestBody Department department) {
//        return ResponseEntity.ok(
//                departmentService.createDepartment(department));
//    }
//
//    // Any authenticated user may view departments
//    @GetMapping
//    public ResponseEntity<List<Department>> getAllDepartments() {
//        return ResponseEntity.ok(
//                departmentService.getAllDepartments());
//    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity<Department> getDepartment(@PathVariable Integer id) {
//        return ResponseEntity.ok(
//                departmentService.getDepartmentById(id));
//    }
//}
