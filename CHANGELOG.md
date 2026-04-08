# Changelog

## - 2026-04-08

### Added

* Integrate OpenLibrary API to fetch and display real book data in the app
  * Define Domain and Data models
  * Implement network layer (Ktor)
  * Structure data access via repository pattern
  * Configure platform-specific HttpClient engine (KMP expect/actual)
  * Connect API data to book list UI

### Fixed

* Fix missing kotlin serialization dependency

## - 2026-04-07

### Added

* Set up project dependencies and initial structure
* Add core Domain and Presentation utilities
* Implement UI for book list feature