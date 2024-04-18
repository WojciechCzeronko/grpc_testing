package client

import io.grpc.ManagedChannel
import io.grpc.ManagedChannelBuilder
import io.grpc.kotlin.AbstractCoroutineStub
import org.slf4j.Logger
import org.slf4j.LoggerFactory

abstract class DefaultGrpcClient<STUB : AbstractCoroutineStub<STUB>> {

    val logger: Logger = LoggerFactory.getLogger(this::class.java)

    protected lateinit var stub: STUB
    private lateinit var channel: ManagedChannel

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
}
