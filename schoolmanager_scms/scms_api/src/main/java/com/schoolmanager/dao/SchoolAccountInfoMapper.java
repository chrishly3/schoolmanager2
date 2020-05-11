package com.schoolmanager.dao;

import com.schoolmanager.entity.SchoolAccountinfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface SchoolAccountInfoMapper {

    @Select("select * from school_accountinfo where user_id = #{accountinfoId}")
    public SchoolAccountinfo selectTokenById(String accountinfoId);
}
