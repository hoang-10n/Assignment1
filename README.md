# Assignment1
Assignment 1 for Android Development
 
## Introduction
This app is created for students to share their review about the course with other students. Users can login, sign up into the application, view their own reviews and all reviews from other accounts, create and delete reviews, view account info and delete accounts.

## Component
`Activity` Folder: 5 Java files
- `AddActivity.java`: for adding reviews to the `review.json` file.
- `HomeActivity.java`: containing 3 fragments: **AccountFragment**, **MyReviewFragment** and **AllReviewFragment**.
- `MainActivity.java`: for the login and sign up purposes.
- `MajorListViewActivity.java`: displaying the list view of majors when clicking on the major input in `activity_add.xml`
- `ReviewActivity.java`: displaying the information of a review when clicked.

`Fragment` Folder: 3 Java files
- `AccountFragment.java`: containing the info of the account and logout and delete account options.
- `AllReviewFragment.java`: showing all reviews of all accounts.
- `MyReviewFragment.java`: showing all reviews of the current accounts.

`Helper` Folder: 4 Java files
- `DatabaseHelper.java`: handing all database functions for the `account.db` file.
- `FileHelper.java`: handling reading and reading from `review.json` file.
- `JsonHelper.java`: convert Json to **Review** objects and store them.
- `NotificationHelper.java`: handling notifications.
