# Album

Android application displaying a list of items received via the following webservice:

http://jsonplaceholder.typicode.com/photos

The project is to be realized on the Android platform (minimum API 14).

Downloaded data must be available offline after restarting the application.

------------------------------------------------------------------------------------------------------------------------------------------

Architectures & Patterns : Clean Architecture, Android MVVM

Language : Java

Frameworks & Libraries : Android Data Binding, Live Data, RxJava, RxAndroid, Dagger, Retrofit, Room, Room-RxJava, Picasso

------------------------------------------------------------------------------------------------------------------------------------------

To be continued to optimize performance in the offline mode when load photo from local database. Idea is to don't load all photos from local database once(such as the online mode) by using Android Paging Library and query photo data from Room with the quantity fixed for each time.
