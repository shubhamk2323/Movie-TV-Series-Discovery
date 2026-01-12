Movie & TV Show Discovery App

Overview
  This Android application allows users to discover movies and TV shows using the Watchmode API.
  The app is built using Jetpack Compose and focuses on clean architecture, proper API handling, and smooth user experience.

Features Implemented
  Home screen displaying a list of movies and TV shows
  Tabs to toggle between Movies and TV Shows
  Parallel API calls to fetch movies and TV shows
  Pull-to-refresh support on the home screen
  Shimmer placeholders shown while data is loading
  Details screen displaying title, release year, description, and poster image
  Navigation from home screen to details screen
  Graceful error handling using Snackbar messages

Architecture
  The app follows the MVVM architecture pattern.
  Model layer handles API responses and data mapping
  ViewModel layer manages business logic and UI state
  View layer is built using Jetpack Compose and reacts to state changes

API Handling
  Watchmode API is used to fetch movie and TV show data
  Movies and TV shows are fetched simultaneously using Single.zip
  Detailed information is fetched separately when navigating to the details screen
  API calls are optimized to avoid unnecessary refetching when switching tabs

Error Handling
  Network and API errors are handled
  Snackbar messages are shown to inform users about errors
  A custom message is shown when there is no internet connection

Challenges Faced
  Handling API responses with different data structures
  Managing ViewModel dependencies and lifecycle
  Ensuring shimmer placeholders were visible during loading
  Handling navigation and passing data between screens
  Avoiding runtime crashes due to missing dependencies and permissions

Assumptions
  Release year is used as the release date as provided by the API
  UI design is kept simple to focus on logic and functionality
  Unit tests are documented rather than fully implemented
