package com.msss.service.student.impl;

import com.msss.pojo.StudentInfo;
import com.msss.mapper.StudentInfoMapper;
import com.msss.service.student.IStudentInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author msss
 * @since 2023-09-27
 */
@Service
public class StudentInfoServiceImpl extends ServiceImpl<StudentInfoMapper, StudentInfo> implements IStudentInfoService {
    @Autowired
    StudentInfoMapper studentInfoMapper;

    @Override
    public String register(String studentId){
        // 检查格式
        if (studentId.length()>12){
            return "请输入正确学号";
        }
        // 检查是否存在
        StudentInfo check=studentInfoMapper.checkInfo(studentId);

        if (check!=null) {
            SimpleDateFormat formatTime= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//HH是24小时制，12小时用hh

            String strDate = formatTime.format(check.getCreateTime());

            return "已经于"+strDate+"领取过！";
        }


        // 插入
        StudentInfo studentInfo=new StudentInfo();
        studentInfo.setStudentId(studentId);
        System.out.println("insert="+studentInfo);
          baseMapper.insert(studentInfo);
          return "登记成功！";
    }

    @Override
    public int add(StudentInfo studentInfo){
//        if studentInfo.getStudentId()
        return baseMapper.insert(studentInfo);
    }

    @Override
    public int delete(Long id){
        return baseMapper.deleteById(id);
    }

    @Override
    public int updateData(StudentInfo studentInfo){
        return baseMapper.updateById(studentInfo);
    }

    @Override
    public StudentInfo findById(Long id){
        return  baseMapper.selectById(id);
    }
}
