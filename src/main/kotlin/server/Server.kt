package server

import io.grpc.ServerBuilder
import service.IntegerServiceJava
import service.IntegerServiceKotlin
import service.PlaceServiceKotlin


fun helloServer() {
    val integerService = IntegerServiceJava()
    val integerService2 = IntegerServiceKotlin()
    val placeService = PlaceServiceKotlin()
    val port = 8088
    val server = ServerBuilder
        .forPort(port)
        .addService(integerService)
        .addService(placeService)
        .build()

    Runtime.getRuntime().addShutdownHook(Thread {
        server.shutdown()
        server.awaitTermination()
    })

    server.start()
    println("Starting gRPC server for service/s: ${(server.services).map { it.serviceDescriptor.name }}")
    println("Listening on port: $port")
    server.awaitTermination()
}

fun main() {
    helloServer()
}