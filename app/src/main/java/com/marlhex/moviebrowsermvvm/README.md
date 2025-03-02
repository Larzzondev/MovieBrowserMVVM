# MovieBrowserMVVM - Modern Android Movie Browser App

A modern Android app that displays popular movies using The Movie DB API. This project demonstrates Android development best practices using Jetpack Compose, MVVM architecture, and modern Android libraries.

## Features

- Display a paginated list of popular movies
- Auto-loading of additional movies when scrolling to the end of the list
- Detail view for each movie
- Modern UI using Jetpack Compose
- Navigation between screens
- Efficient image loading with Coil

## Architecture

The app follows the MVVM (Model-View-ViewModel) architecture pattern:

- **Model**: Represents the data layer with `Movie` data class and API handling
- **View**: Implemented with Jetpack Compose UI components (`MovieListScreen`, `MovieDetailScreen`)
- **ViewModel**: Manages UI-related data, handles business logic and communicates with the data layer

### Architecture Diagram

```mermaid
graph TD
    subgraph UI["Presentation Layer"]
        MA[MainActivity]
        MLS[MovieListScreen]
        MDS[MovieDetailScreen]
    end
    
    subgraph VM["Business Logic Layer"]
        MVM[MovieViewModel]
    end
    
    subgraph DL["Data Layer"]
        API[MovieApi]
        M[Movie Model]
    end
    
    subgraph EXT["External"]
        TMDB["TheMovieDB API"]
    end
    
    MA -->|Contains| MLS
    MA -->|Contains| MDS
    MLS -->|Observes| MVM
    MDS -->|Receives data from| MVM
    MVM -->|Fetches data from| API
    API -->|Creates| M
    API -->|Makes HTTP requests to| TMDB
    
    %% Flow of data
    TMDB -->|JSON Response| API
    API -->|Parsed Data| MVM
    MVM -->|State Updates| MLS
    MLS -->|Navigation with data| MDS
    
    %% Pagination trigger
    MLS -->|onMovieAppear| MVM
    MVM -->|loadMovies| API
    
    classDef presentation fill:#d0e0ff,stroke:#3080e0,stroke-width:2px
    classDef business fill:#ffe0d0,stroke:#e08030,stroke-width:2px
    classDef data fill:#d0ffe0,stroke:#30e080,stroke-width:2px
    classDef external fill:#e0d0ff,stroke:#8030e0,stroke-width:2px
    
    class MA,MLS,MDS presentation
    class MVM business
    class API,M data
    class TMDB external
```

## Technology Stack

- **UI**: Jetpack Compose
- **Navigation**: Jetpack Navigation Compose
- **Network**: Retrofit for API calls
- **Image Loading**: Coil (equivalent to Kingfisher in iOS)
- **Async**: Kotlin Coroutines and Flow for asynchronous operations
- **Architecture**: MVVM with Repository pattern

## Project Structure

- `MainActivity.kt` - Entry point for the application, sets up navigation
- `MovieListScreen.kt` - Displays the list of movies
- `MovieDetailScreen.kt` - Shows detailed information about a selected movie
- `Model.kt` - Data classes for Movie and API response
- `MovieApi.kt` - API interface and implementation for The Movie DB API
- `MovieViewModel.kt` - ViewModel that manages UI state and business logic

## Setup Instructions

1. Clone the repository
2. Open the project in Android Studio
3. Build and run the app on an emulator or physical device

## Requirements

- Android Studio Hedgehog (2023.1.1) or newer
- Min SDK: 24 (Android 7.0)
- Target SDK: 34 (Android 14)

## Permissions

The app requires the following permissions:
- `android.permission.INTERNET` - To fetch movie data from the API

## API Key

The app uses The Movie DB API. The API key is included in the code for demonstration purposes, but in a real-world application, it should be stored securely.

## Future Improvements

- Implement caching for offline support
- Add search functionality
- Create a favorites section
- Enhance UI with animations and transitions
- Implement dependency injection with Hilt

## License

This project is open-source and available under the MIT License.