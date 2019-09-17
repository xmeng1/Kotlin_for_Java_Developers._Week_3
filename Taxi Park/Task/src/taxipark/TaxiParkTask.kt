package taxipark

import kotlin.math.floor

/*
 * Task #1. Find all the drivers who performed no trips.
 */
fun TaxiPark.findFakeDrivers(): Set<Driver> =
    this.allDrivers.filter { driver ->
        !this.trips.any() {trip -> trip.driver == driver }
    }.toSet()


/*
 * Task #2. Find all the clients who completed at least the given number of trips.
 */
fun TaxiPark.findFaithfulPassengers(minTrips: Int): Set<Passenger> =
    this.allPassengers.filter { p ->
        this.trips.count() {trip -> trip.passengers.contains(p) } >= minTrips
    }.toSet()

/*
 * Task #3. Find all the passengers, who were taken by a given driver more than once.
 */
fun TaxiPark.findFrequentPassengers(driver: Driver): Set<Passenger> =
    this.allPassengers.filter { passenger ->
        this.trips.count() { trip -> trip.driver == driver && trip.passengers.contains(passenger)} > 1
    }.toSet()

/*
 * Task #4. Find the passengers who had a discount for majority of their trips.
 */
fun TaxiPark.findSmartPassengers(): Set<Passenger> =
    this.allPassengers.filter { passenger ->
        val pairOfType = this.trips.filter { trip -> trip.passengers.contains(passenger) }
                .partition { trip ->  trip.discount != null}
        pairOfType.first.size > pairOfType.second.size
    }.toSet()

/*
 * Task #5. Find the most frequent trip duration among minute periods 0..9, 10..19, 20..29, and so on.
 * Return any period if many are the most frequent, return `null` if there're no trips.
 */
fun TaxiPark.findTheMostFrequentTripDurationPeriod(): IntRange? {
    if (this.trips.isEmpty()) return null
    val max = this.trips.groupBy() { trip -> trip.duration/10}.maxBy { k -> k.value.size }?.key ?: return null
    return IntRange(max *10, max *10+9)
}

/*
 * Task #6.
 * Check whether 20% of the drivers contribute 80% of the income.
 */
fun TaxiPark.checkParetoPrinciple(): Boolean {
    val driverTripMap: Map<Driver, List<Trip>> = this.trips.groupBy { it.driver }
    val twentyPercentDrivers = floor(this.allDrivers.size * 0.2)

    val driverIncomeMap: List<Pair<Driver, Double>> = driverTripMap.flatMap { entry ->
           listOf(Pair(entry.key, entry.value.sumByDouble { it.cost }))
    }

    val listOfPairDriverIncome = driverIncomeMap.sortedByDescending { it.second }
    var sum:Double = 0.0
    var sumTwentyPercent:Double = 0.0
    val r = listOfPairDriverIncome.forEachIndexed { index, pair ->
        if (index < twentyPercentDrivers) {
            sumTwentyPercent += pair.second
        }
        sum+= pair.second
    }
    return sum != 0.0 && sumTwentyPercent>= sum*0.8
}