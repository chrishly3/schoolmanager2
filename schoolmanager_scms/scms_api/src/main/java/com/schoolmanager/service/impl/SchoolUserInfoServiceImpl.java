package com.schoolmanager.service.impl;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.schoolmanager.controller.SchoolUserInfoController;
import com.schoolmanager.dao.SchoolUserInfoMapper;
import com.schoolmanager.entity.SchoolAccountinfo;
import com.schoolmanager.entity.SchoolToken;
import com.schoolmanager.entity.SchoolUserinfo;
import com.schoolmanager.service.SchoolUserInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import utils.HttpCode.R;
import utils.JwtTokenUtil.CreateTokenUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SchoolUserInfoServiceImpl implements SchoolUserInfoService {
    @Autowired
    private SchoolUserInfoMapper userInfoMapper;
    private final Logger logger = LoggerFactory.getLogger(SchoolUserInfoServiceImpl.class);


    @Override
    public int editUser(SchoolUserinfo schoolUserinfo) {
        return userInfoMapper.editUser(schoolUserinfo);
    }

    /**
     * 根据用户名密码查询
     * @param userName
     * @param passWord
     * @return
     */
    @Override
    public SchoolUserinfo getPersonExist(String userStunum, String passWord) {
        logger.info("SchoolUserInfoServiceImpl-getPersonExist userStunum:{},passWord:{}",userStunum,passWord);
        SchoolUserinfo resultList = userInfoMapper.selectUserInfobyNameAndPwd(userStunum,passWord);
        //如果为空
        //将对象传入map
        //这里是生成token 存入了id,名字，还有时间，base64
//        String accessToken = CreateTokenUtils
//                .createJWT(userName,audience.getClientId(), audience.getName(),audience.getExpiresSecond() * 1000, audience.getBase64Secret());
//        SchoolToken accessTokenEntity = new SchoolToken();
//        accessTokenEntity.setAccessToken(accessToken);
//        accessTokenEntity.setBuildTime(audience.getExpiresSecond());
//        accessTokenEntity.setTokenType("bearer");
//        map.put("accessToken",accessTokenEntity);
        return resultList;
    }

    @Override
    public SchoolUserinfo getUserById(String id) {
        return userInfoMapper.getUserById(id);
    }

    /**
     * 添加用户和用户内部属性
     * @param userinfo
     * @param
     * @return
     */
    @Override
    public int saveUser(SchoolUserinfo userinfo) {
        //生成关联codeid
        String resultId = codeNumSave((int) userinfo.getId());
        userinfo.setAccountinfoId(resultId);
        return userInfoMapper.saveUser(userinfo);
    }

    //随机生成codeid
    public String codeNumSave(int id){
        int num = (int)((Math.random()*9+1)*100000);
        char c=(char)(int)(Math.random()*26+97);
        return  c+"_"+num;
    }

    @Override
    public List<SchoolUserinfo> listUser() {
        return userInfoMapper.listUser();
    }

    /**
     * 逻辑删除用户
     * @param id
     * @return
     */
    @Override
    public Integer removeById(String id) {
        return userInfoMapper.removeById(id);
    }


    /**
     * 查询所有人员
     * @param page
     * @param pageSize
     * @return
     */
    @Override
    public PageInfo<SchoolUserinfo> showUsers(Integer page, Integer pageSize) {
        page = page == null ? 1 : page;
        pageSize = pageSize == null ? 10: pageSize;
        System.out.println(page + "--"+pageSize);
        //在帮助类中传入分页参数
        PageHelper.startPage(page, pageSize);
        List<SchoolUserinfo> list = userInfoMapper.listUser();
        System.out.println("list  :  "+list);
        PageInfo<SchoolUserinfo> pageList = new PageInfo<SchoolUserinfo>(list);
        return pageList;
    }

    /**
     * 模糊查询
     * @param userName
     * @param page
     * @param pageSize
     * @return
     */
    @Override
    public PageInfo<SchoolUserinfo> selectUserLike(String userName, Integer page, Integer pageSize,String userType) {
        page = page == null ? 1 : page;
        pageSize = pageSize == null ? 10: pageSize;
        System.out.println(page + "--"+pageSize);
        //在帮助类中传入分页参数
        PageHelper.startPage(page, pageSize);
        List<SchoolUserinfo> list =userInfoMapper.selectUserLike("%"+userName+"%","%"+userType+"%");
        System.out.println("list  :  "+list);
        PageInfo<SchoolUserinfo> pageList = new PageInfo<SchoolUserinfo>(list);
        return pageList;
    }


    @Override
    public Long countUser() {
        return userInfoMapper.countUser();
    }

    /**
     * 根据关联id查询信息
     * @param userId
     * @return
     */
    @Override
    public SchoolUserinfo findUserById(String userId) {
        SchoolUserinfo schoolUserinfo  = userInfoMapper.selectUserByKeyId(userId);
        return schoolUserinfo;
    }

}
