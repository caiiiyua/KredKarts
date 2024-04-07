# KredKarts

KredKarts is a mobile application that allows users to manage their credit cards. The application is built using Kotlin, Jetpack Compose, and other modern Android development technologies.

## Considerations

1. A main screen which only contains a list of credit cards is considered for this project.
2. To expand the functionality, a user can click on a credit card to view more details.
3. The app is designed to be offline-first, but due to time constraints, the offline functionality is mocked use a in-memory cache.
4. Consider the `credit_card` endpoint supports pagination, the app is implemented to load more credit cards when the user scrolls to the bottom of the list instead of loading 100 at once.

## Design and Implementation

### Architecture

From high-level, the application is using Clean Architecture. As it's a simple enough application, `domain` layer is not necessary while
the `Repository` in `data` layer handles the business logic to fetch the data, on the other hand, presentation layer is responsible for displaying the data 
to the user.

### Implementation

- The application follows the MVVM (Model-View-ViewModel) in implementation of the presentation layer. 
  - Single Activity with Composables for the UI to simplify the UI development
  - ViewModel binds to screen to provide data and handle events from the UI
  - Unidirectional data flow using StateFlow to update UiState in the UI layer
- Although only one screen is required, the application is designed to be scalable and maintainable, it is divided into several packages vertically and horizontally.
- Each package divided vertically representing a different layer of the architecture, for example:
  - `ui`: This package contains the UI layer of the application, including the screens and view models.
  - `domain`: This optional package contains the domain layer of the application, including the use cases and domain models, it should be fully testable.
  - `data`: This module contains the data layer of the application, including the repository, data source, network service, etc,, it should be fully testable.
- Each package divided horizontally is designed to be independent and interchangeable screens. This makes the application scalable easily, it could become modules when the application grows.
- The application is designed to be offline-first, LocalDataSource to cache the data for offline use (mocked using in-memory cache) and also as the single source of truth of the data
- Misc
  - Network services to fetch data from the API using Retrofit and okhttp
  - Hilt for dependency injection
  - MockK for unit testing (Covers the ViewModel and entire data layer)
  - kotlinx-coroutines for handling asynchronous tasks

## Future Improvements

1. Offline caching could be implemented using Room or other sqlite database to store the data locally.
2. Error handling could be improved to provide more meaningful error messages to the user.
3. Some UI Tests could be added to test the UI components.

## Getting Started

To get started with the project, clone the repository and open it in Android Studio. Make sure you have the latest version of Kotlin and Java installed. Run the `gradle build` command to build the project.

## License

This project is licensed under the Apache License. See the `LICENSE` file for more details.