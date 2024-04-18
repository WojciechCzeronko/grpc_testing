package server


import com.czer.hello.HelloReply
import com.czer.hello.HelloRequest
import com.czer.hello.HelloServiceGrpcKt
import org.slf4j.LoggerFactory

class HelloService : HelloServiceGrpcKt.HelloServiceCoroutineImplBase() {

    private val logger = LoggerFactory.getLogger(this.javaClass)
    override suspend fun hello(request: HelloRequest): HelloReply {
        logger.info("Obtained request with argument: ${request.name}")
        return HelloReply.newBuilder()
            .setMessage("Hello, ${request.name}")
            .build()
    }
}

