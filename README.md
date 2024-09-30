# GlobalWaves Project

## Overview

GlobalWaves is a simulation application inspired by music streaming services like Spotify. It allows users to perform various actions, including managing their music library, following artists and playlists, and interacting with hosts and their podcasts. The project is divided into two stages, each enhancing the previous stage's functionalities.

## Stage 1: Core Functionality

### Objectives

In Stage 1, the main focus was on building the foundational structure of the application. This included:

- **User Management:** Creation of normal users, artists, and hosts with specific roles and functionalities.
- **Music Library:** Implementation of a music library containing songs that users can search, play, and manage.
- **Playlists:** Normal users can create, manage, and share playlists.
- **Search Functionality:** Users can search for songs, artists, and playlists using various filters.
- **User Interaction:** Basic interaction between users, such as liking songs and following artists.

### Key Features

- **User Types:**
  - **Normal Users:** Can manage their playlists, like songs, and search the music library.
  - **Artists:** Can add songs and manage their music catalog.
  - **Hosts:** Can create and manage podcasts.

- **Commands:**
  - **AddUser:** Admin command to add new users.
  - **DeleteUser:** Admin command to remove users.
  - **Search:** Users can search for songs, artists, and playlists.

## Stage 2: Pagination and Enhanced Functionality

### Objectives

In Stage 2, the project introduced a pagination system and further enhanced user interactions. This stage focused on creating a minimal graphical interface through page segmentation, allowing users to navigate through different sections of the application seamlessly.

### Key Features

- **Pagination System:**
  - Users can navigate between different pages, such as:
    - **HomePage:** Displays user recommendations based on liked songs and followed playlists.
    - **LikedContentPage:** Shows all songs and playlists liked by the user.
    - **Artist Page:** Provides information about the artist's albums, merchandise, and events.
    - **Host Page:** Displays the host's podcasts and announcements.

- **New User Types:**
  - **Artists:** Can manage their albums and merchandise.
  - **Hosts:** Can manage their podcasts.

- **Enhanced Search Functionality:**
  - Users can now search by album name, artist, and description, improving the overall user experience.

- **Music Player Integration:**
  - Users can play albums with the same functionalities as playlists, including repeat and shuffle options.

- **Admin Commands:**
  - **ShowAlbums:** Displays all albums created by an artist.
  - **ChangePage:** Allows users to switch between different pages, ensuring they can access various functionalities easily.

### Notes on Implementation

- **Design Patterns:** Implemented at least one object-oriented design pattern to enhance code modularity and maintainability.
- **Error Handling:** Proper error messages and handling mechanisms were integrated to ensure a smooth user experience, especially when dealing with invalid inputs.

## Conclusion

The GlobalWaves project aims to create a comprehensive simulation of a music streaming platform, focusing on user experience and interactive features. With the implementation of a pagination system and additional functionalities in Stage 2, the applicati
