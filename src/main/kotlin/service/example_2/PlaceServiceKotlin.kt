package service.example_2


import com.czer.place.Coordinates
import com.czer.place.PlaceDetails
import com.czer.place.PlaceServiceGrpcKt
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.slf4j.LoggerFactory


class PlaceServiceKotlin : PlaceServiceGrpcKt.PlaceServiceCoroutineImplBase() {
    private val logger = LoggerFactory.getLogger(this.javaClass)

    override suspend fun getPlace(request: Coordinates): PlaceDetails {
        logger.info("Fetching place details at location $request")
        return getPlaceFromDB(request)
    }

    override fun listPlaces(request: Coordinates): Flow<PlaceDetails> {
        return flow {
            logger.info("Fetching place details at location $request")
            val firstPlace = getPlaceFromDB(request)
            emit(firstPlace)
            val nextCoordinates =
                Coordinates.newBuilder()
                    .setLatitude(request.latitude + 45.0)
                    .setLongitude(request.longitude + 90.0)
                    .build()
            val nextPlace = getPlaceFromDB(nextCoordinates)
            emit(nextPlace)
        }
    }

    override suspend fun placeAround(requests: Flow<Coordinates>): PlaceDetails {
        logger.info("Fetching place details at locations $requests")

        return super.placeAround(requests)
    }

    override fun placesAlong(requests: Flow<Coordinates>): Flow<PlaceDetails> {
        logger.info("Fetching place details at location $requests")

        return super.placesAlong(requests)
    }

    companion object {
        //test data
        fun getPlaceFromDB(coordinates: Coordinates): PlaceDetails {
            val latitude = coordinates.latitude
            val longitude = coordinates.longitude
            val place = PlaceDetails.newBuilder()
            val wrongCoordinates = PlaceDetails.newBuilder().setName("Wrong Coordinates").build()
            when (latitude) {
                in (-90.0..0.0) -> when (longitude) {
                    in (-180.0..0.0) -> return place.setName("Brasilia Lidl").build()
                    in (0.000001..180.0) -> return place.setName("Brisbane Coffee").build()
                    else -> return wrongCoordinates
                }

                in (0.000001..90.0) -> when (longitude) {
                    in (-180.0..0.0) -> return place.setName("Toronto Church").build()
                    in (0.000001..180.0) -> return place.setName("Polish Cinema").build()
                    else -> return wrongCoordinates
                }

                else -> return wrongCoordinates
            }
        }
    }


}

