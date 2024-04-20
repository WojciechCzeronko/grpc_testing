package client

import com.czer.integer.IncrementIntegerRequest
import com.czer.integer.IncrementIntegerResponse
import com.czer.integer.IntegerServiceGrpcKt.IntegerServiceCoroutineStub
import com.czer.integer.NextValueRequest
import com.czer.integer.NextValueResponse
import io.grpc.ManagedChannel
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking

class IntegersClient : DefaultGrpcClient<IntegerServiceCoroutineStub>() {

    override fun createStub(channel: ManagedChannel): IntegerServiceCoroutineStub {
        return IntegerServiceCoroutineStub(channel)
    }

    fun incrementIntegers(request: IncrementIntegerRequest): List<IncrementIntegerResponse> {
        return runBlocking { stub.increment(request).toList() }
    }

    fun next(request: NextValueRequest): NextValueResponse{
        return runBlocking {  stub.next(request)}
    }

}
