### **baseline-android** - *examples of starting points for doing all things on Android*

**base·line** - a minimum or starting point used for comparisons<br>
**an·droid** - an open-source operating system used for smartphones and tablet computers

#### Libraries Used
**Networking** - [Fast-Android-Networking](https://github.com/amitshekhariitbhu/Fast-Android-Networking) 

````
compile 'com.amitshekhar.android:android-networking:0.4.0'
````

**Image Loading** - [Glide](https://github.com/bumptech/glide)
 
```` 
compile 'com.github.bumptech.glide:glide:3.7.0'
compile 'com.android.support:support-v4:19.1.0' 
```` 

**JSON Parsing** - [Jackson via Fast-Android-Networking](https://github.com/amitshekhariitbhu/Fast-Android-Networking)

```` 
// You may need to exclude META-INF files in the packagingOptions of build.gradle
compile 'com.amitshekhar.android:jackson-android-networking:0.4.0'
```` 

#### Android Manifest Permissions

If you're using all of the above libraries here are all the permissions to add to your manifest file:

```xml
<!-- Put these below the manifest tag and above the application tag! -->
<uses-permission android:name="android.permission.INTERNET" />
```

#### Notes
* Skeleton project created using Android Studio's "Navigation Drawer Activity" template
* API requests hit [JSONPlaceholder](https://jsonplaceholder.typicode.com/)

#### Todos
* Network request of images into scrolling ListView
* [Epoxy](https://github.com/airbnb/epoxy)
* Logging library
* [Android Annotations](https://github.com/androidannotations/androidannotations)
