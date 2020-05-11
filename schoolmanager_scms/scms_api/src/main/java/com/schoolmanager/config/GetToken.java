package com.schoolmanager.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.schoolmanager.entity.SchoolUserinfo;

public class GetToken {

    public String getToken(SchoolUserinfo user) {
        String token="";
        token= JWT.create().withAudience(user.getAccountinfoId())
                .sign(Algorithm.HMAC256(user.getUserPassword()));
        return token;
    }
}
