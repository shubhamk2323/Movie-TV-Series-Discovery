Movie & TV Show Discovery App

1. Overview
  This Android application allows users to discover movies and TV shows using the Watchmode API.
  The app is built using Jetpack Compose and focuses on clean architecture, proper API handling, and smooth user experience.

2. Features Implemented
  1. Home screen displaying a list of movies and TV shows.
  2. Tabs to toggle between Movies and TV Shows.
  3. Parallel API calls to fetch movies and TV shows.
  4. Pull-to-refresh support on the home screen.
  5. Shimmer placeholders shown while data is loading.
  6. Details screen displaying title, release year, description, and poster image.
  7. Navigation from home screen to details screen.
  8. Graceful error handling using Snackbar messages.

4. Architecture
  The app follows the MVVM architecture pattern.
  1. Model layer handles API responses and data mapping.
  2. ViewModel layer manages business logic and UI state.
  3. View layer is built using Jetpack Compose and reacts to state changes.

5. API Handling
  1. Watchmode API is used to fetch movie and TV show data.
  2. Movies and TV shows are fetched simultaneously using Single.zip.
  3. Detailed information is fetched separately when navigating to the details screen.
  4. API calls are optimized to avoid unnecessary refetching when switching tabs.

6. Error Handling
  1. Network and API errors are handled gracefully.
  2. Snackbar messages are shown to inform users about errors.
  3. A custom message is shown when there is no internet connection.

7. Challenges Faced
  1. Handling API responses with different data structures.
  2. Managing ViewModel dependencies and lifecycle.
  3. Ensuring shimmer placeholders were visible during loading.
  4. Handling navigation and passing data between screens.
  5. Avoiding runtime crashes due to missing dependencies and permissions.

8. Assumptions
  1. Release year is used as the release date as provided by the API.
  2. UI design is kept simple to focus on logic and functionality.
  3. Unit tests are documented rather than fully implemented.
