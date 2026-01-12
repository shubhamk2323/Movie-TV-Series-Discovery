Unit Test Cases Document

1. Overview
  This document describes the unit testing strategy for the Movie & TV Show Discovery App.
  The focus is on testing core business logic rather than UI components.


2. Scope of Unit Testing
  Unit tests are focused on the following layers:
  - Repository layer.
  - ViewModel layer.

UI and navigation tests were excluded as the assignment focuses on logic and architecture.

3. Test Case 1: MovieRepository
   - Component Tested: MovieRepository.
   - Method Tested: fetchMoviesAndTv().
   - Purpose:
      - Verify that movies and TV shows are fetched simultaneously.
      - Ensure correct mapping of API responses.
    - Test Approach:
       - Mock or fake the Watchmode API responses.
       - Return predefined movie and TV show data.
       - Call fetchMoviesAndTv() and validate the result.
    - Expected Result:
      - Movies list is returned correctly.
      - TV shows list is returned correctly.
      - Both lists are non-null.

4. Test Case 2: HomeViewModel
  1. Component Tested: HomeViewModel.
  2. Purpose:
     - Verify correct UI state transitions.
  3. Test Scenarios:
     1. Success scenario:
        - Repository returns valid data.
        - ViewModel emits HomeUiState.Success.
     2. Error scenario:
        - Repository throws an error.
        - ViewModel emits HomeUiState.Error with an appropriate message.
  4. Expected Result:
     - UI state reflects the correct result based on repository response.

5. Notes
  1. Network calls are mocked or faked to ensure predictable test results.
  2. UI tests were not included to keep the scope focused on business logic.
  3. The testing approach ensures correctness of logic and architecture.
