package server


import com.czer.integer.*
import io.grpc.stub.StreamObserver
import org.slf4j.LoggerFactory


class IntegerServiceJava : IntegerServiceGrpc.IntegerServiceImplBase() {
    private val logger = LoggerFactory.getLogger(this.javaClass)

    override fun increment(
        request: IncrementIntegerRequest,
        responseObserver: StreamObserver<IncrementIntegerResponse>
    ) {
        logger.info("We are going to increment number ${request.initial}, ${request.noOfIncrements} times")
        val startValue = request.initial + 1

        // it should be  val endValue = startValue + request.noOfIncrements -1 || error for testing
        val endValue = startValue + request.noOfIncrements

        (startValue..endValue).map { it ->
            val response = IncrementIntegerResponse.newBuilder().setNextInteger(it).build()

            responseObserver.onNext(response)
            logger.info("sending $response")
            Thread.sleep(500)
        }
        //notify there will be no more messages
        responseObserver.onCompleted()
    }

    override fun next(request: NextValueRequest, responseObserver: StreamObserver<NextValueResponse>) {
        responseObserver.onNext(NextValueResponse.newBuilder().setNext(request.initial+1).build())
        responseObserver.onCompleted()
    }
}

