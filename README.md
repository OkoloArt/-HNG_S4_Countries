# Countries
> _What is the project?_ - The project is a project that displays a list of countries with associated details from an API.

> _What is the MVP ?_ - The minimal viable product is a list of country app that perform a network call using retrofit, parsing JSON data and showing results to user

> _What are the sprinkles?_ - The sprinkles involves styling the app, adding possible animations.

> Live demo [_countries_](). 

## Table of Contents
* [General Info](#general-information)
* [Built with](#built-with)
* [Features](#features)
* [Room for Improvement](#room-for-improvement)
* [Acknowledgements](#acknowledgements)
* [What I learned](#what-i-learned)
* [Contact](#contact)

## General Information
- The aim of this project is to provide country list and info to the user. From country list to country details.

## Built with
- [Kotlin](https://kotlinlang.org/) - First class and official programming language for Android development.
- [Coroutines](https://kotlinlang.org/docs/reference/coroutines-overview.html) - For asynchronous and more..
- [Android Architecture Components](https://developer.android.com/topic/libraries/architecture) - Collection of libraries that help you design robust, testable, and maintainable apps.
  - [Flow](https://kotlinlang.org/docs/reference/coroutines/flow.html) - A flow is an asynchronous version of a Sequence, a type of collection whose values are lazily produced.
  - [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel) - Stores UI-related data that isn't destroyed on UI changes. 
  - [Jetpack Navigation](https://developer.android.com/guide/navigation) - Navigation refers to the interactions that allow users to navigate across, into, and back out from the different pieces of content within your app
- [Material Components for Android](https://github.com/material-components/material-components-android) - Modular and customizable Material Design UI components for Android.
- [Dagger-Hilt](https://dagger.dev/hilt/) - A pragmatic and lightweight dependency injection framework for Kotlin developers.
- [Picasso](https://square.github.io/picasso/) - A powerful image downloading and caching library for Android 
- [Retofit](https://square.github.io/retrofit/) - A type-safe HTTP client for Android and Java.
- [SearchView](https://developer.android.com/) - A widget is a view that provides a search interface for users to search through data.
- [Modal Bottom Sheet](https://m2.material.io/develop/android/components/bottom-sheet-dialog-fragment) - A dialog that is displayed at the bottom of the screen. It is used to provide a quick and easy way for users to interact with your app.
- [Adapters](https://developer.android.com/) - An Android is a class that helps you bind data from a source, such as an array, list, or cursor, to a view.
- [RecyclerView](https://developer.android.com/) - An Android ViewGroup that allows you to display a large number of items in a vertically scrolling list.

## Features
- Light/Dark mode toggle
- Profile and Display name set Up
- Connecting to API 
- Searchable Functionalities
- Display data in to user in a recycler view using Adapter

## Room for Improvement
- Correctly filter country list by continents
- Filter List by Time zones
- Adding Landscape UI and UX
- Language Localization

## Acknowledgements
- Animations [Lottie](https://lottiefiles.com//)
- Image Loading [Picasso](https://square.github.io/picasso/)

## What i learned

There were many things that I got in touch for the first time and also becoming familiar with already known concept. Like:

- Sectioning RecyclerView, RecyclerView with Parent and Child viewHolder
- Coroutines, LiveData, ViewModel and Lifecycle
- Picasso and Retrofit

## Contact
Created by [Okolo](https://twitter.com/Okolo_Arthur) - feel free to contact me!


