package server


import com.czer.place.Coordinates
import com.czer.place.PlaceDetails
import com.czer.place.PlaceServiceGrpcKt
import org.slf4j.LoggerFactory


class PlaceServiceKotlin : PlaceServiceGrpcKt.PlaceServiceCoroutineImplBase() {
    private val logger = LoggerFactory.getLogger(this.javaClass)

    override suspend fun getPlace(request: Coordinates): PlaceDetails {
        return super.getPlace(request)
    }

}

