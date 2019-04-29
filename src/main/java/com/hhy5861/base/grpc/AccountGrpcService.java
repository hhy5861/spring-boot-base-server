package com.hhy5861.base.grpc;

import com.hhy5861.account.AccountGrpc;
import com.hhy5861.account.InfoRequest;
import com.hhy5861.account.ListRequest;
import com.hhy5861.base.model.Account;
import com.hhy5861.base.service.account.IAccountService;
import com.hhy5861.grpc.proto.GrpcResponse;
import com.hhy5861.grpc.service.impl.BaseGrpcResponse;
import io.grpc.stub.StreamObserver;
import lombok.extern.slf4j.Slf4j;
import org.lognet.springboot.grpc.GRpcService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Slf4j
@GRpcService
public class AccountGrpcService extends AccountGrpc.AccountImplBase {

    @Autowired
    private IAccountService accountService;

    @Override
    public void list(ListRequest request, StreamObserver<GrpcResponse> responseObserver) {
        List<Account> list = accountService.getAllAccount();

        responseObserver.onNext(BaseGrpcResponse.newBuilder(list).build().response());

        responseObserver.onCompleted();
    }

    @Override
    public void info(InfoRequest request, StreamObserver<GrpcResponse> responseObserver) {
        Account account = accountService.getAccountById(request.getId());

        if (account == null) {
            responseObserver.onNext(BaseGrpcResponse.newBuilder().build().paramsException(-100001, new Exception("can't find account")));
        } else {
            responseObserver.onNext(BaseGrpcResponse.newBuilder(account).build().response());
        }

        responseObserver.onCompleted();
    }
}
