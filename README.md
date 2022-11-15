# Approach/Libs ->
* Clean Architecture with Multi Modules i.e. UI (app), Data, Domain
    * Used widely by the android community, encourages SOLID and consistency for developers
* MVVM + Compose
* Hilt/Retrofit 
    * dependency injection
    * debug mock responses
* Coroutines with Flow - async. to gather and manipulate data as required 
* Repositories with Usecases and mappers - 
    * to usher data through the onion architecture 
    * move reusable logic into usecases if required
* Unit tests are based on mockito and Konveyor for random object creation
* Ui espresso test
