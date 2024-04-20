package client

import com.czer.integer.IncrementIntegerRequest
import com.czer.integer.NextValueRequest
import org.testng.Assert
import org.testng.annotations.DataProvider
import org.testng.annotations.Test
import org.testng.asserts.SoftAssert

class TestIntegerService {

    private val integerClient = IntegersClient()

    @DataProvider(name = "nextValues")
    fun nextValuesDataProvider(): MutableIterator<Array<Int>>{
        val testData: ArrayList<Array<Int>> = arrayListOf()
        testData.add(arrayOf(-51,-50))
        testData.add(arrayOf(1,2))
        testData.add(arrayOf(1000,1001))
        return testData.iterator()
    }

    @Test(dataProvider = "nextValues")
    fun testNextValue(initialValue: Int, expectedValue: Int) {
        val request = NextValueRequest.newBuilder().setInitial(initialValue).build()
        val response = integerClient.next(request)
        Assert.assertEquals(response.next, expectedValue, "Wrong value of next integer")
    }

    @Test(enabled = false)
    fun testIncrementSize() {
        val initialValue = 10
        val increment = 4
        val request = IncrementIntegerRequest.newBuilder().setInitial(initialValue).setNoOfIncrements(increment).build()
        val response = integerClient.incrementIntegers(request)
        Assert.assertEquals(response.size, increment, "Wrong number of returned increments")
    }

    @Test
    fun testIncrementValues() {
        val initialValue = 12
        val increment = 5
        val request = IncrementIntegerRequest.newBuilder().setInitial(initialValue).setNoOfIncrements(increment).build()
        val response = integerClient.incrementIntegers(request)
        val assert = SoftAssert()
        var expectedValue = initialValue
        response.forEach { it ->
            expectedValue += 1
            assert.assertEquals(it.nextInteger, expectedValue, "Wrong value of next integer")
        }
        assert.assertAll()
    }
}