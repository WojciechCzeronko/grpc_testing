package server


import com.czer.integer.IncrementIntegerRequest
import com.czer.integer.IncrementIntegerResponse
import com.czer.integer.IntegerServiceGrpcKt
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import org.slf4j.LoggerFactory


class IntegerService : IntegerServiceGrpcKt.IntegerServiceCoroutineImplBase() {
    private val logger = LoggerFactory.getLogger(this.javaClass)
    override fun increment(request: IncrementIntegerRequest): Flow<IncrementIntegerResponse> {
        logger.info("We are going to increment number ${request.initial}, ${request.noOfIncrements} times")
        val startValue = request.initial + 1
        // it should be  val endValue = startValue + request.noOfIncrements -1 || error for testing
        val endValue = startValue + request.noOfIncrements
        return (startValue..endValue).map { it ->
            IncrementIntegerResponse.newBuilder().setNextInteger(it).build()
        }.asFlow()
    }
}

