package client

import com.czer.hello.HelloReply
import com.czer.hello.HelloRequest
import com.czer.hello.HelloServiceGrpcKt.HelloServiceCoroutineStub
import io.grpc.ManagedChannel
import io.grpc.Metadata
import kotlinx.coroutines.runBlocking

class HelloClient : DefaultGrpcClient<HelloServiceCoroutineStub>() {

    override fun createStub(channel: ManagedChannel): HelloServiceCoroutineStub {
        return HelloServiceCoroutineStub(channel)
    }

    fun hello(request: HelloRequest): HelloReply {
        val response = runBlocking{stub.hello(request, Metadata())}
        logger.info(response.message)
        return response
    }

}

fun main() {
    val request = HelloRequest.newBuilder().setName("Czero oro").build()
    val helloClient = HelloClient()
    helloClient.hello(request)
}