package com.msss.controller;

import com.msss.util.R;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import org.springframework.web.bind.annotation.*;
import com.msss.service.student.IStudentInfoService;
import com.msss.pojo.StudentInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.baomidou.mybatisplus.core.metadata.IPage;

import javax.annotation.Resource;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author msss
 * @since 2023-09-27
 */
@Api(tags = {""})
@RestController
@RequestMapping("/mooncake")
@CrossOrigin
public class StudentInfoController {



    @Resource
    private IStudentInfoService studentInfoService;

    @GetMapping("/test")
    public String test(String studentId){
//        String msg=studentInfoService.register(studentId);
        return "hello";
    }

    @ApiOperation(value = "register")
    @PostMapping("/register")
    public R register(String studentId){
        String msg=studentInfoService.register(studentId);
        return R.ok(msg,null);
    }
//    @ApiOperation(value = "新增")
//    @PostMapping("/add")
//    public int add(@RequestBody StudentInfo studentInfo){
//        return studentInfoService.add(studentInfo);
//    }
//
//    @ApiOperation(value = "删除")
//    @DeleteMapping("{id}")
//    public int delete(@PathVariable("id") Long id){
//        return studentInfoService.delete(id);
//    }
//
//    @ApiOperation(value = "更新")
//    @PutMapping()
//    public int update(@RequestBody StudentInfo studentInfo){
//        return studentInfoService.updateData(studentInfo);
//    }
//
//
//
//    @ApiOperation(value = "id查询")
//    @GetMapping("{id}")
//    public StudentInfo findById(@PathVariable Long id){
//        return studentInfoService.findById(id);
//    }

}
