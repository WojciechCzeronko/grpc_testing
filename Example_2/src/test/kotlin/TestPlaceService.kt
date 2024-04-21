import client.PlaceClient
import com.czer.place.Coordinates
import org.testng.Assert
import org.testng.annotations.DataProvider
import org.testng.annotations.Test

class TestPlaceService {

    private val placeClient = PlaceClient()

    @DataProvider(name = "geoCoordinates")
    fun geoCoordinatesDataProvider(): MutableIterator<Array<Any>> {
        val testData: ArrayList<Array<Any>> = arrayListOf()
        testData.add(arrayOf(-51.1, -50.9, "Brasilia Lidl"))
        testData.add(arrayOf(-50.3, 20.9, "Brisbane Coffee"))
        testData.add(arrayOf(40.0, -80.0, "Toronto Church"))
        testData.add(arrayOf(23.5, 21.38, "Polish Cinema"))
        testData.add(arrayOf(230.5, 21.38, "Wrong Coordinates"))
        return testData.iterator()
    }

    @Test(dataProvider = "geoCoordinates")
    fun testGetPlace(latitude: Double, longitude: Double, expectedName: String) {
        val request = Coordinates.newBuilder().setLatitude(latitude).setLongitude(longitude).build()
        val response = placeClient.getPlace(request)
        Assert.assertEquals(response.name, expectedName, "Wrong value of next integer")
    }

}