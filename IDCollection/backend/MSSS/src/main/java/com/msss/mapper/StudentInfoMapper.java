package com.msss.mapper;

import com.msss.pojo.StudentInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author msss
 * @since 2023-09-27
 */
@Repository
public interface StudentInfoMapper extends BaseMapper<StudentInfo> {
    StudentInfo checkInfo(String student_id);
}
