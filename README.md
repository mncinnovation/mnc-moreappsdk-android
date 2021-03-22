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
  implementation 'com.github.mncinnovation:mnc-moreappsdk-android:1.0.1'
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
  <version>1.0.1</version>
</dependency>
```
## Step 2

Navigate to MNCApps Screen/Page
```python
button.setOnClickListener {
  val intent = Intent(this, MNCAppsActivity::class.java)
  intent.putExtra(Constant.userID, [USER_ID])
  intent.putExtra(Constant.packageName, [PACKAGE_NAME])
  intent.putExtra(Constant.platformType, [PLATFORM_TYPE])
  startActivity(intent)
}
```
or use in your Activity if you want to use the custom Toolbar
```python
button.setOnClickListener {
  val intent = Intent(this, YourTargetActivity::class.java)
  intent.putExtra(Constant.userID, [USER_ID])
  intent.putExtra(Constant.packageName, [PACKAGE_NAME])
  intent.putExtra(Constant.platformType, [PLATFORM_TYPE])
  startActivity(intent)
}
```
Then, use in YourTargetActivity.class
```python
MNCAppsActivity.showFragment(R.id.appsFrameLayout, this)
```

and add in activity_yourtarget.xml
```python
<FrameLayout
    android:id="@+id/appsFrameLayout"
    android:layout_width="match_parent"
    android:layout_height="match_parent"/>
```
