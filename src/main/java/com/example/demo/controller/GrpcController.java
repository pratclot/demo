package com.example.demo.controller;

import com.example.demo.Tune;
import com.example.demo.TuneProtoGrpc;
import com.example.demo.gTune;
import com.example.demo.gTuneName;
import com.example.demo.service.TuneService;
import com.google.protobuf.Empty;
import io.grpc.stub.StreamObserver;
import org.lognet.springboot.grpc.GRpcService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

@GRpcService
public class GrpcController extends TuneProtoGrpc.TuneProtoImplBase {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private TuneService tuneService;

    @Override
    public void getAllTunes(Empty Null, StreamObserver<gTune> responseObserver) {
        Iterable<Tune> allTunes = tuneService.getAllTunes();

        for (Tune tune : allTunes) {
            responseObserver.onNext(gTune.newBuilder().setName(tune.getName()).build());
        }

        responseObserver.onCompleted();
    }

    @Override
    public void getSomeTunes(gTuneName name, StreamObserver<gTune> responseObserver) {
        Iterable<Tune> someTunes = tuneService.getSomeTunes(name.getName());
        logger.info("Client came9");
        for (Tune tune : someTunes) {
            responseObserver.onNext(gTune.newBuilder().setName(tune.getName()).build());
        }

        responseObserver.onCompleted();
    }
}
