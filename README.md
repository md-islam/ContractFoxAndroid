# ContractFoxAndroid

An Android app that helps homeowners get connected to reliable nearby contractors for home improvement projects. Homeowners sign up with multiple addresses, marking one of them as a primary address. Homeowners search for a contractor either by typing in keywords in our search bar or by tapping on specific areas on our image renderings of a regular home (interior, exterior and backyard). Not only this app helps homeowners get connected to contractors but also helps contractors get connected to sub-contractors.

Link to prototype: https://drive.google.com/file/d/0B0dbwQ7fJaBrdVU1R0tOdGJ5ZTA/view?usp=sharing

## Problem Statement

Helping users (both homeowners and contractors) find reliable and cost-effective contractors with just a few clicks away unlike apps such as Angie's List or homeadvisor which is cumbersome to use and also involves a lot of redundant steps to sign up or even look for home improvement services. 

## Getting started 

* Clone the repository
* Install Android studio
* Set up Firebase for Android - [Link](https://firebase.google.com/docs/android/setup) <br>
* Set up Emulator/device for android
* Run the application

## Features (prototype stage)
 
1. Users can sign up either as contractors or homeowners. Contractors select their services/areas of specialization online.
2. Homeowners can set up appointments with contractors. Contractors have the option to CRUD his schedule every three weeks which can be viewed by homeowners to set up appointments.
3. Homeowners can select multiple contractors and send an estimate request about a particular service to multiple contractors.
4. Implemented in-app chat feature so that users (homeowners and contractors) can stay in touch regarding a service/contract.
5. Contractors have the option to edit his profile as well as add images of their past services.
6. Homeowners have the option to leave a rating on a contractor’s profile only if they have been serviced by that contractor. 

## Tech Stack Description (FYI): 

1. Designed and architected an Android application utilizing Google APIs (Maps, Location services), Java, XML, Firebase NoSQL cloud based backend to store ratings, user profiles, images, chat messages, etc. 
2. Designed and developed adaptive UI components (Navigation Drawer, Recycler views, Fragments, View Pagers, search bars, etc), using Google’s modern Material Design language. 
3. Wrote Java Model classes to be used with Firebase to help with serializing/deserializing JSON payload to and from Firebase by using Hashmaps helping to populate UI components following an MVC like pattern.
4. Defined backend data structure schema, denormalized data structure wherever necessary to increase query efficiency. 


## Future work before pushing app to Google Play Store: 

1. Index backend data to improve query efficiency as well as define security rules. 
2. Spread out Firebase data structure to multiple paths to reduce downloaded data size. 
3. Fix some known bugs related to image storage and retrieval with Firebase. 
4. Implement Flashlight - an elasticSearch library


