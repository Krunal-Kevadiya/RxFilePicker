[![API](https://img.shields.io/badge/API-16%2B-red.svg?style=flat)](https://android-arsenal.com/api?level=16)
[![Build Status](https://travis-ci.org/wupdigital/android-maven-publish.svg?branch=master)](https://github.com/Krunal-Kevadiya/RxFilePicker)
[ ![Download](https://api.bintray.com/packages/kevadiyakrunalk/MyFramework/rxfilepicker/images/download.svg) ](https://bintray.com/kevadiyakrunalk/MyFramework/rxfilepicker/_latestVersion) 
[![License](https://img.shields.io/badge/License-Apache%202.0-orange.svg)](https://opensource.org/licenses/Apache-2.0)

# RxFilePicker

* you can pick file from particular DIR and also apply a group of file extension for example you will pick PPT{ppt, pptx}, DOC{doc, docx, dot, dotx}.
* you can select/pick multiple files from particular DIR with max file counter limit.
* Code :-
```xml
<style name="FilePickerTheme" parent="Theme.AppCompat.Light.DarkActionBar">
    <!-- Customize your theme here. -->
    <item name="colorPrimary">@color/colorPrimary</item>
    <item name="colorPrimaryDark">@color/colorPrimaryDark</item>
    <item name="colorAccent">@color/colorAccent</item>
</style>
```    
```java
   private int MAX_ATTACHMENT_COUNT = 5;
   private ArrayList<FileType> fileTypes;
   fileTypes = new ArrayList<>();
   FileType fileType = new FileType();
   fileType.setGroupTitle("PDF");
   fileType.setGroupIcon(R.drawable.ic_pdf);
   fileType.setGroupExtension("pdf");
   fileTypes.add(fileType);

   fileType = new FileType();
   fileType.setGroupTitle("PPT");
   fileType.setGroupIcon(R.drawable.icon_ppt);
   fileType.setGroupExtension("ppt,pptx");
   fileTypes.add(fileType);

   fileType = new FileType();
   fileType.setGroupTitle("DOC");
   fileType.setGroupIcon(R.drawable.ic_doc);
   fileType.setGroupExtension("doc,docx,dot,dotx");
   fileTypes.add(fileType);

   fileType = new FileType();
   fileType.setGroupTitle("XLS");
   fileType.setGroupIcon(R.drawable.ic_xls);
   fileType.setGroupExtension("xls,xlsx");
   fileTypes.add(fileType);

   fileType = new FileType();
   fileType.setGroupTitle("TXT");
   fileType.setGroupIcon(R.drawable.ic_txt);
   fileType.setGroupExtension("txt");
   fileTypes.add(fileType);
        
   RxFilePicker
    .getInstance(this)
    .setMaxCount(MAX_ATTACHMENT_COUNT)
    .setFileType(fileTypes)
    .setDirectory(Environment.getExternalStorageDirectory())
    .setActivityTheme(R.style.FilePickerTheme)
    .pickDocument(this, new RxFilePicker.FileResult() {
        @Override
        public void PickFileList(ArrayList<String> list) {
             Log.e("Files", list.toString());
        }
    });
```
-> Gradle
```groovy
//add dependencies for app level build.gradle
repositories {
    jcenter()
}
dependencies {
  compile 'com.kevadiyakrunalk:rxfilepicker:1.0@aar'
}
```
-> Maven
```xml
<dependency>
  <groupId>com.kevadiyakrunalk</groupId>
  <artifactId>rxfilepicker</artifactId>
  <version>1.0</version>
  <type>pom</type>
</dependency>
```
