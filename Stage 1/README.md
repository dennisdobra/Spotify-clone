# GlobalWaves - Stage 1 - Audio Player

---

## Project Overview

The **GlobalWaves** project aims to implement a simulated audio player application akin to Spotify, allowing users to perform various actions through command inputs. The system functions from the perspective of an admin, tracking user interactions and generating reports.

## Objectives

- Familiarization with Java and basic Object-Oriented Programming (OOP) concepts.
- Practical understanding of constructors, aggregation, and inheritance.
- Development of basic organization and object-oriented design skills.
- Writing generic code to facilitate future functionality additions.
- Adherence to a consistent coding and commenting style.
- Enhancement of debugging skills.

## Functionalities

### Entities

1. **Audio File Types:**
   - **Song**
   - **Podcast Episode**

2. **Audio File Collections:**
   - **Library:** The complete collection of songs available on the platform, accessible to all users.
   - **Playlist:** A user-created collection of songs, which can be public or private.
   - **Podcast:** A collection of related episodes, specified in the input file and accessible from the start of the simulation.

3. **Search Bar:** Used to search for songs, playlists, or podcasts based on various filters.

### Search Filters
- **Songs:**
  - By name
  - By album
  - By tags
  - By lyrics
  - By genre
  - By release year
  - By artist
- **Playlists:**
  - By name
  - By owner
- **Podcasts:**
  - By name
  - By owner

### Music Player Functions

- **Load:** Loads a selected song, playlist, or podcast for playback.
- **Play/Pause:** Toggles between play and pause states.
- **Repeat:** Cycles through repeat modes based on the currently playing content.
- **Shuffle:** Randomizes the playback order of songs in a playlist.

### User Interaction

Users can interact with the search bar, player, and create or modify playlists. Each user has a unique username for their commands.

### Timestamps

Commands are executed based on a common timestamp, ensuring that all users' actions are synchronized in time.

---
