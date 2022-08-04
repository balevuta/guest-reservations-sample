# Guests reservations application example with Android Architecture MVVM components and Clean Architecture

Guests reservations app that shows how to architect an android app in a clean architecture with kotlin
coroutines. It shows a list of guests (have reservations and need reservations) that is fetched from local storage.

The data of guests can be changed in GuestSelectionViewModel.kt class: change the constant in saveGuests() method 
into SAMPLE_DATA_LONG or SAMPLE_DATA_SHORT (defined in Constants.kt class)

## Technical

* [Android Architecture MVVM](https://developer.android.com/jetpack/guide)
* [Clean Architecture](https://github.com/android10/Android-CleanArchitecture)
* [Dependency Injection](https://en.wikipedia.org/wiki/Dependency_injection)

## Libraries

### Android Jetpack

* [ViewBinding](https://developer.android.com/topic/libraries/view-binding) A feature that allows
  you to more easily write code that interacts with views.

* [LiveData](https://developer.android.com/topic/libraries/architecture/livedata) Build data objects
  that notify views when the underlying database changes.

* [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel) Store UI-related
  data that isn't destroyed on app rotations. Easily schedule asynchronous tasks for optimal
  execution.


### HTTP

* [Moshi](https://github.com/square/moshi) Moshi is a modern JSON library for Android, Java and Kotlin. 
  It makes it easy to parse JSON into Java and Kotlin classes.

### Coroutines

* [Kotlin Coroutines](https://github.com/Kotlin/kotlinx.coroutines) Coroutines is a rich library for
  coroutines developed by JetBrains. It contains a number of high-level coroutine-enabled primitives
  that this guide covers, including launch, async and others.

### DI

* [DaggerHilt](https://developer.android.com/training/dependency-injection/hilt-android) Hilt is a dependency injection library 
for Android that reduces the boilerplate of doing manual dependency injection in your project.

