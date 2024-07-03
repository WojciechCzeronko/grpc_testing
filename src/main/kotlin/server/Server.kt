package server

import io.grpc.ServerBuilder
import service.example_1.IntegerServiceJava
import service.example_2.PlaceServiceKotlin

private const val port = 8088

fun rpcServer() {
    val integerService = IntegerServiceJava() //java implementation of the service
//    val integerService2 = IntegerServiceKotlin() //kotlin implementation of the service
    val placeService = PlaceServiceKotlin()
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
    rpcServer()
}