# MNC Apps

[![mncapps.png](https://i.postimg.cc/xdHk4PpW/mncapps.png)](https://postimg.cc/SXSNXchr)

## Feature

Dashboard Feature
* Manage Apps
* Customize Layout
* Customize Apps Order
* Manage Click Behaviour

Android SDK
* Show All MNC Apps
* Managed Button Click Behaviour
* InApp WebView

## Gradle

```python
allprojects {
  repositories {
    ...
    maven { url 'https://jitpack.io' }
  }
}

dependencies {
  implementation 'com.github.mncinnovation:mnc-moreappsdk-android:1.0.0'
}
```
or use Maven
```python
<repositories>
  <repository>
    <id>jitpack.io</id>
    <url>https://jitpack.io</url>
  </repository>
</repositories>

<dependency>
  <groupId>com.github.mncinnovation</groupId>
  <artifactId>mnc-moreappsdk-android</artifactId>
  <version>1.0.0</version>
</dependency>
```
