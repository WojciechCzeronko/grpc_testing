package client

import com.czer.integer.IncrementIntegerRequest
import com.czer.integer.IncrementIntegerResponse
import com.czer.integer.IntegerServiceGrpcKt.IntegerServiceCoroutineStub
import io.grpc.ManagedChannel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.runBlocking

class IntegersClient : DefaultGrpcClient<IntegerServiceCoroutineStub>() {

    override fun createStub(channel: ManagedChannel): IntegerServiceCoroutineStub {
        return IntegerServiceCoroutineStub(channel)
    }


    fun incrementIntegers(request: IncrementIntegerRequest): Flow<IncrementIntegerResponse> {
        return runBlocking { stub.increment(request) }
    }

}
