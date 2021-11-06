# Assignment1
Assignment 1 for Android Development
 
## Introduction
This app is created for students to share their review about the course with other students. Users can login, sign up into the application, view their own reviews and all reviews from other accounts, create and delete reviews, view account info and delete accounts.

## Components
`Activity` Folder: 5 JAVA files.
- `AddActivity.java`: for adding reviews to the `review.json` file.
- `HomeActivity.java`: containing 3 fragments: **AccountFragment**, **MyReviewFragment** and **AllReviewFragment** and navigating buttons.
- `MainActivity.java`: for the login and sign up purposes.
- `MajorListViewActivity.java`: displaying the list view of majors when clicking on the major input in `activity_add.xml`.
- `ReviewActivity.java`: displaying the information of a review when clicked. Have *delete* option when viewed in `activity_my_review.xml`.

`Fragment` Folder: 3 JAVA files.
- `AccountFragment.java`: containing the info of the account and logout and delete account options.
- `AllReviewFragment.java`: showing all reviews of all accounts.
- `MyReviewFragment.java`: showing all reviews of the current accounts.

`Helper` Folder: 4 JAVA files.
- `DatabaseHelper.java`: handing all database functions for the `account.db` file.
- `FileHelper.java`: handling reading and reading from `review.json` file.
- `JsonHelper.java`: convert Json to **Review** objects and store them.
- `NotificationHelper.java`: handling notifications.

`Model` Folder: 2 Java files: `Account.java` and `Review.java`

## Resources
`drawable`: 6 files `anonymous.png`, `custom_border.xml`, `gradient_background.xml`, `ic_launcher_background.xml`, `ic_launcher_foreground.xml`, `logo.png`.

`layout`: 10 XML files.
- Activities: `activity_add.xml`, `activity_home.xml`, `activity_main.xml`, `activity_major_list_view.xml`, `activity_review.xml`.
- Fragments: `fragment_account.xml`, `fragment_my_review.xml`, `fragment_all_review.xml`.
- Others: `review_block.xml`, `major_list_view.xml`

## Set up and running
- Make sure all the files above are in the right directory.
- JAVA files:

![image](https://user-images.githubusercontent.com/56622316/140617288-9898f00b-c2e1-4b95-b5e1-b262a93ffd89.png)
- XML files and other resources:

![image](https://user-images.githubusercontent.com/56622316/140617320-01136b84-6e40-4051-815d-d91fa8a86d30.png)
- `AndroidManifest.xml` must be in the manifests folder.
- GSON must be included in `build.gradle`:

```implementation 'com.google.code.gson:gson:2.8.9'```
- Device used for the demo: Pixel 2 API 30.
- Link to the demo(https://rmiteduau-my.sharepoint.com/:v:/r/personal/minh_vuthanh_rmit_edu_vn/Documents/A1_Demo_Videos/s3749795_DoanLuongHoang_A1.wmv?csf=1&web=1&e=S12l2r)
