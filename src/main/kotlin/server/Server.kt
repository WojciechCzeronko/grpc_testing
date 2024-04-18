package server

import io.grpc.ServerBuilder


fun helloServer() {
    val helloService = HelloService()
    val integerService = IntegerService()
    val port = 8088
    val server = ServerBuilder
        .forPort(port)
        .addService(helloService)
        .addService(integerService)
        .build()

    Runtime.getRuntime().addShutdownHook(Thread {
        server.shutdown()
        server.awaitTermination()
    })

    server.start()
    println("Starting gRPC server for service/s: ${(server.services).map { it.serviceDescriptor.name }}")
    server.awaitTermination()
}

fun main() {
    helloServer()
}