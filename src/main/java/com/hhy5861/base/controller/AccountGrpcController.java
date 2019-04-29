package com.hhy5861.base.controller;

import com.hhy5861.account.AccountGrpc;
import com.hhy5861.account.InfoRequest;
import com.hhy5861.account.ListRequest;
import com.hhy5861.common.tools.Response;
import com.hhy5861.grpc.client.BaseGrpcClient;
import com.hhy5861.grpc.proto.GrpcResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/account/grpc")
public class AccountGrpcController extends BaseGrpcClient {
    private AccountGrpc.AccountBlockingStub accountBlockingStub;

    public AccountGrpcController() {
        accountBlockingStub = AccountGrpc.newBlockingStub(channel);
    }

    @GetMapping(value = "/list")
    public Response<?> list(@RequestParam(required = false) String name) {
        ListRequest.Builder builder = ListRequest.newBuilder();
        if (name != null) {
            builder.setName(name);
        }

        GrpcResponse result = accountBlockingStub.list(builder.build());
        return new Response<>(getResponse(result).getData());
    }

    @GetMapping(value = "/info")
    public Response<?> info(int id) {
        InfoRequest infoRequest = InfoRequest.newBuilder().setId(id).build();

        Object res = getResponse(accountBlockingStub.info(infoRequest));
        return new Response<>(res);
    }
}
