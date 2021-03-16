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

## How To Use

## Step 1

Gradle
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
or Maven
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
## Step 2

Navigate to MNCApps Screen/Page
```python
MNCAppsActivity.mainStartActivity(
  this,
  userID = [USER_ID],
  packageName = [PACKAGE_NAME],
  platformType = "android")
```
or use in your Activity
```python
button.setOnClickListener {
  val intent = Intent(this, YourTargetActivity::class.java)
  intent.putExtra(Constant.userID, [USER_ID])
  intent.putExtra(Constant.packageName, [PACKAGE_NAME])
  intent.putExtra(Constant.platformType, "android")
  startActivity(intent)
}
```
and use in YourTargetActivity.class
```python
MNCAppsActivity.showFragment(R.id.appsFrameLayout, this)
```
