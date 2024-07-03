package client.example_2

import client.DefaultGrpcClient
import com.czer.place.Coordinates
import com.czer.place.PlaceDetails
import com.czer.place.PlaceServiceGrpcKt.PlaceServiceCoroutineStub
import io.grpc.ManagedChannel
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking

class PlaceClient : DefaultGrpcClient<PlaceServiceCoroutineStub>() {

    override fun createStub(channel: ManagedChannel): PlaceServiceCoroutineStub {
        return PlaceServiceCoroutineStub(channel)
    }

    fun getPlace(request: Coordinates): PlaceDetails {
        return runBlocking { stub.getPlace(request) }
    }

    fun listPlaces(request: Coordinates): List<PlaceDetails> {
        return runBlocking { stub.listPlaces(request).toList() }
    }

}
