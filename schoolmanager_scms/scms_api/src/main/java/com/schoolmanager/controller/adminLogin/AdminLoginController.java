package com.schoolmanager.controller.adminLogin;


import com.schoolmanager.config.GetToken;
import com.schoolmanager.config.UserLoginToken;
import com.schoolmanager.entity.QueryAccount;
import com.schoolmanager.entity.QueryUser;
import com.schoolmanager.entity.SchoolAccountinfo;
import com.schoolmanager.entity.SchoolUserinfo;
import com.schoolmanager.service.SchoolAccountInfoService;
import com.schoolmanager.service.SchoolUserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import utils.HttpCode.R;

import java.util.Map;

@RequestMapping("/login")
@RestController
@CrossOrigin
public class AdminLoginController {

   @Autowired
   private SchoolUserInfoService userInfoService;


   @Autowired
   private SchoolAccountInfoService schoolAccountInfoService;

   @RequestMapping(value = "/login",method = RequestMethod.POST)
   /*public R login(@RequestParam String userName,
           @RequestParam String passWord) {*/
   public R login(@RequestBody(required = false) QueryAccount queryAccount) {
      System.out.println(queryAccount);
      String userName=queryAccount.getUsername();
      String passWord=queryAccount.getPassword();
      //先查询数据库根据用户
      if(userName == null && passWord == null){
         return R.error();
      }
      //String md5PassWord = Md5Utils.getMD5(passWord);
      SchoolUserinfo result = userInfoService.getPersonExist(userName,passWord);
      if(result != null){
         //密码判断
         if(!(passWord.equals(result.getUserPassword()))){
            //密码错误,你这里要加一些构造方法去把密码错误的msg发送上去
            return R.error();
         }
         GetToken setingToken = new GetToken();
         String token = setingToken.getToken(result);
         //这里申请创建token，然后登入成功,根据用户关联查询token
         SchoolAccountinfo schoolAccountinfo = schoolAccountInfoService.selectTokenById(result.getAccountinfoId());
         //return R.ok(token,schoolAccountinfo.getUserId(),schoolAccountinfo.getAccount(),result.getUserPassword());
         return R.ok().data("token",token).data("userId",schoolAccountinfo.getUserId()).data("account",schoolAccountinfo.getAccount()).data("password",result.getUserPassword());
      }
      return R.error();
   }


   @UserLoginToken
   @GetMapping("/getMessage")
   public String getMessage(){
      return "你已通过验证";
   }


   @RequestMapping(value = "/info",method = RequestMethod.GET)
   public R info(){
      return  R.ok().data("roles","['admin']").data("name","admin").data("avatar","https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif").data("introduction","我是超级管理员").data("name","Super Admin");
   }


}
