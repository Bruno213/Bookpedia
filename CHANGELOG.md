# Changelog

## [1.3.0] - 2026-04-22

### Added

* Set up Room database for storing favorite books
  * Add DAOs, models, and mappers
  * Add String-to-List converter
  * Generate schema

### Fixed

* Fix KSP dependency setup

## [1.3.0] - 2026-04-10

### Added

* Set up navigation with Jetpack Compose Navigation
* Implement book details UI
* Connect API Data to book details UI
  * Custom serializer for getting book description data due to API design

### Changed

* Applied code formatting to standardize style across project files

## [1.2.0] - 2026-04-08

### Added

* Set up dependency injection using Koin
  * Define modules and initialize Koin in each platform entry point
* Adopt semantic versioning for the changelog

### Changed

* Move dummy book data to BookListScreenPreview

## [1.1.0] - 2026-04-08

### Added

* Integrate OpenLibrary API to fetch and display real book data in the app
  * Define Domain and Data models
  * Implement network layer (Ktor)
  * Structure data access via repository pattern
  * Configure platform-specific HttpClient engine (KMP expect/actual)
  * Connect API data to book list UI

### Fixed

* Fix missing Kotlin serialization dependency

## [1.0.0] - 2026-04-07

### Added

* Set up project dependencies and initial structure
* Add core Domain and Presentation utilities
* Implement UI for book list feature