# ChargeStationApp


Android application to fecth ChargeStation details from api (every 30 seconds) and display it in google map view. Onclick of marker icon it will display the charging station details.

#Architecture:

MVVM architecture with usecase, repository layers.
UI design with Jetpack compose


#Components used :

Programming language - Kotlin

Coroutines - Is light wight threads for asynchronous programming and polling

Flow - Handle the stream of data asynchronously that executes sequentially.

Dependency Injection - Hilt-android Hilt provides a standard way to incorporate Dagger dependency injection into an Android application.

Network - Retrofit, OkHttp library used

Moshi - Java serialization/deserialization library to convert Java Objects into JSON and back

Testing - Espresso, Hilt, compose testing components

Poller - custom util class for polling
