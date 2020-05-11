package com.schoolmanager.service;

import com.schoolmanager.entity.SchoolAccountinfo;
import com.schoolmanager.entity.SchoolUserinfo;
import utils.HttpCode.R;

public interface SchoolAccountInfoService {



    R insertUesrInfo(SchoolUserinfo schoolUserinfo, SchoolAccountinfo schoolAccountinfo);

    SchoolAccountinfo selectTokenById(String accountinfoId);

}
