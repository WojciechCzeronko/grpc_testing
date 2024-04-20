package server


import com.czer.integer.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.slf4j.LoggerFactory


class IntegerServiceKotlin : IntegerServiceGrpcKt.IntegerServiceCoroutineImplBase() {
    private val logger = LoggerFactory.getLogger(this.javaClass)

    override fun increment(request: IncrementIntegerRequest): Flow<IncrementIntegerResponse> {
        return flow{
            logger.info("We are going to increment number ${request.initial}, ${request.noOfIncrements} times")
            val startValue = request.initial + 1

            // it should be  val endValue = startValue + request.noOfIncrements -1 || error for testing
            val endValue = startValue + request.noOfIncrements

            (startValue..endValue).map { it ->
                val response = IncrementIntegerResponse.newBuilder().setNextInteger(it).build()
                emit(response)
                logger.info("sending $response")
                delay(500)
            }
        }
    }

    override suspend fun next(request: NextValueRequest): NextValueResponse {
        return NextValueResponse.newBuilder().setNext(request.initial+1).build()
    }
}

