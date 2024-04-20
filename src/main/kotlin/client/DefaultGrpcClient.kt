package client

import io.grpc.ManagedChannel
import io.grpc.ManagedChannelBuilder
import io.grpc.kotlin.AbstractCoroutineStub
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.io.Closeable
import java.util.concurrent.TimeUnit

abstract class DefaultGrpcClient<STUB : AbstractCoroutineStub<STUB>> :Closeable{

    val logger: Logger = LoggerFactory.getLogger(this::class.java)

    protected  var stub: STUB
    private  var channel: ManagedChannel

    init{
        val port = 8088
        val host = "localhost"
        channel = ManagedChannelBuilder
            .forAddress(host, port)
            .usePlaintext()
            .build()
        stub = createStub(channel)
    }

    abstract fun createStub(channel: ManagedChannel): STUB

    override fun close() {
        channel.shutdown().awaitTermination(5, TimeUnit.SECONDS)
    }
}
