package com.example.demo.client;

import com.example.demo.TuneProtoGrpc;
import com.example.demo.gTune;
import com.example.demo.gTuneName;
import com.google.protobuf.Empty;
import io.grpc.Channel;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusRuntimeException;
import org.springframework.beans.factory.annotation.Value;

import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GrpcClient {
    private final ManagedChannel channel;
    private String host = "localhost";
    @Value("${gprc.port}")
    private int port;
    private TuneProtoGrpc.TuneProtoBlockingStub blockingStub;
    private Logger logger = Logger.getLogger(GrpcClient.class.getName());

    public GrpcClient(String host, int port) {
        this(ManagedChannelBuilder.forAddress(host, port).usePlaintext());
    }

    public GrpcClient(ManagedChannelBuilder<?> channelBuilder) {
        channel = channelBuilder.build();
        blockingStub = TuneProtoGrpc.newBlockingStub(channel);
    }

//    public GrpcClient() {
//        host = this.host;
//        port = this.port;
//        this(host, port);
//    }

    public void query1(String name) {
        gTuneName tuneName = gTuneName.newBuilder().setName(name).build();
        Iterator<gTune> result;
        try {
            result = blockingStub.getAllTunes(Empty.newBuilder().build());
            while (result.hasNext()) {
                logger.info(result.next().getName());

            }
        } catch (StatusRuntimeException e) {
            logger.log(Level.WARNING, "RPC failed: {0}", e.getStatus());
            return;
        }
    }

    public static void main(String[] args) throws Exception {
        String name = args[0];
        String host = "localhost";
        int port = 6666;

        try {
            GrpcClient client = new GrpcClient(host, port);
            client.logger.setLevel(Level.INFO);
            client.logger.info("ssddddddddddddddddddddddd" + name);
            client.query1(name);
        } finally {

        }

    }
}
