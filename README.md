**Car Rental App**


📌 **Overview**

This is a **Car Rental App** built using **Java** and **Firebase**, featuring:

- **User Authentication** (Anonymous Sign-In)

- **Car Listings & Booking** (Firestore)

- **Speed Limit Monitoring** (GPS)

- **Speed Alerts** (Firebase Cloud Messaging)


🏗 **Tech Stack**

- **Android** (Java, Jetpack Libraries)

- **Firebase** (Auth, Firestore, Cloud Messaging, RTDB)

- **Google Play Services** (Location Services)


📦 **Setup Instructions**

1️⃣ **Clone the Repository**

  git clone https://github.com/YasaswiniY96/car_rental_monitoring.git  
  cd car_rental_monitoring

2️⃣ **Setup Firebase**

- Go to Firebase Console

- Create a new project & enable Authentication, Firestore, and Cloud Messaging

- Download google-services.json and place it inside app/


3️⃣ **Add Dependencies**

Update app/build.gradle:

dependencies {  
    implementation 'com.google.firebase:firebase-auth:21.0.1'  
    implementation 'com.google.firebase:firebase-firestore:24.0.2'  
    implementation 'com.google.firebase:firebase-messaging:23.0.0'  
    implementation 'com.google.android.gms:play-services-location:21.0.1'  
}

4️⃣ **Run the App**

- Connect a physical device (since GPS tracking doesn’t work well on emulators)

- Grant Location Permissions

- Run:(sh)

    ./gradlew assembleDebug

⚡ **Features**


✅ User Login (Anonymous)

✅ Car Rental Management (Firestore)

✅ Speed Limit Settings

✅ Real-time Speed Monitoring

✅ Firebase Cloud Messaging (Speed Alerts)


🛠 **Future Improvements**
- UI implementation
- Firebase APIs implementation
- User profile creation and authentication
- Car details implementation 
- Booking details implementation
- Mapping User profiles and car details

