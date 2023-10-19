package com.msss.controller;

import com.msss.service.student.IStudentInfoService;
import com.msss.util.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author msss
 * @since 2023-09-27
 */
@Api(tags = {""})
@Controller
@RequestMapping("/url")
@CrossOrigin
public class TestController {

    @RequestMapping("/urlA")
    public String redirectToURLB() {
        // 在这里可以执行一些逻辑，然后重定向到URL B
        return "redirect:/urlB"; // 这将重定向到URL B
    }

    @RequestMapping("/urlB")
    public String urlB() {
        // 处理URL B的逻辑
        return "url_b_template"; // 返回URL B的视图模板
    }

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
