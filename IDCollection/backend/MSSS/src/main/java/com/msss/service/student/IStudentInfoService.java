package com.msss.service.student;

import com.msss.pojo.StudentInfo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.core.metadata.IPage;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author msss
 * @since 2023-09-27
 */
public interface IStudentInfoService extends IService<StudentInfo> {

//    public StudentInfo checkInfo(String student_id);
    /**
     * 添加
     *
     * @param studentInfo 
     * @return int
     */
    int add(StudentInfo studentInfo);

    String register(String studentId);
    /**
     * 删除
     *
     * @param id 主键
     * @return int
     */
    int delete(Long id);

    /**
     * 修改
     *
     * @param studentInfo 
     * @return int
     */
    int updateData(StudentInfo studentInfo);

    /**
     * id查询数据
     *
     * @param id id
     * @return StudentInfo
     */
    StudentInfo findById(Long id);
}
