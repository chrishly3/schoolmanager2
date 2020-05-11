package com.schoolmanager.service.impl;

import com.schoolmanager.dao.SchoolAccountInfoMapper;
import com.schoolmanager.dao.SchoolUserInfoMapper;
import com.schoolmanager.entity.SchoolAccountinfo;
import com.schoolmanager.entity.SchoolUserinfo;
import com.schoolmanager.service.SchoolAccountInfoService;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import utils.HttpCode.R;

@Service
public class SchoolAccountInfoServiceImpl implements SchoolAccountInfoService {

  @Autowired
  private SchoolUserInfoMapper schoolUserInfoMapper;

  @Autowired
  private SchoolAccountInfoMapper schoolAccountInfoMapper;

    /**
     * 添加用户属性列表
     * @param schoolUserinfo
     * @param schoolAccountinfo
     * @return
     */
    @Override
    public R insertUesrInfo(SchoolUserinfo schoolUserinfo, SchoolAccountinfo schoolAccountinfo) {
        //设置用户名
        schoolAccountinfo.setAccount(schoolUserinfo.getUserStunum());
        //用户关联id
        schoolAccountinfo.setUserId(schoolUserinfo.getAccountinfoId());
        //密码设置
        schoolAccountinfo.setPwd("root");
        int result = schoolUserInfoMapper.insertUserinfo(schoolAccountinfo);
        if(result == 1){
            return R.ok();
        }
            return R.error();
    }

    /**
     * 查询用户属性表
     * @param accountinfoId
     * @return
     */
    @Override
    public SchoolAccountinfo selectTokenById(String accountinfoId) {
        SchoolAccountinfo  schoolAccountinfo  =  schoolAccountInfoMapper.selectTokenById(accountinfoId);
        // 如果token不为空,token时间不为空

        return schoolAccountinfo;
    }
}
