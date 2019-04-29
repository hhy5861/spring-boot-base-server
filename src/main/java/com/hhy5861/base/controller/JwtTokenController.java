package com.hhy5861.base.controller;


import com.hhy5861.common.tools.Response;
import com.hhy5861.jwt.ISecurityJwtService;
import com.hhy5861.jwt.model.JwtModel;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ServerWebExchange;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping(value = "/demo/jwt")
public class JwtTokenController {

    @Autowired
    private ISecurityJwtService securityJwtService;

    @PostMapping(value = "/create")
    public Response<Map<String, String>> create() {
        JwtModel jwtModel = new JwtModel();

        jwtModel.setId(36395);
        jwtModel.setUuid("a5b05f4f-04da-4204-99bf-c563abe08c73");
        jwtModel.setOpenId("oIWNs5eruGyEMbiBlBfYrsKpGTSs");
        jwtModel.setUnionId("oxzc70aK6bCw32v3PiiXExYnw978");

        String token = securityJwtService.create(jwtModel);

        Map<String, String> response = new HashMap<>();
        response.put("token", token);

        return new Response<>(response);
    }

    @GetMapping(value = "/parse")
    public Response<?> parseToken(ServerWebExchange serverWebExchange) {
        Claims claims = securityJwtService.getAuthTokenInfo(serverWebExchange);

        log.debug("id: {}", securityJwtService.getAuthTokenById(serverWebExchange));
        log.debug("uuid: {}", securityJwtService.getAuthTokenByUuid(serverWebExchange));
        log.debug("openId: {}", securityJwtService.getAuthTokenByOpenId(serverWebExchange));
        log.debug("unionId: {}", securityJwtService.getAuthTokenByUnionId(serverWebExchange));

        return new Response<>(claims);
    }

    /**
     * jwt parse exclude contains common key
     *
     * @param serverWebExchange
     * @return
     */
    @GetMapping(value = "/common/parse")
    public Response<?> parseCommonToken(ServerWebExchange serverWebExchange) {
        Claims claims = securityJwtService.getAuthTokenInfo(serverWebExchange);

        return new Response<>(claims);
    }
}

