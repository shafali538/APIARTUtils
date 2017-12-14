# restassured-assignment-solution
This project is a solution for the following assignment:-

Automate the following 4 test cases for the Google APIs with the given framework:-

1. APIs Discovery Service - Get the version and title of all available Google APIs(discovery.apis.list). Verify that the count is 193.
2. Drive API - Create 3 files (get file details from CSV) on Google Drive(drive.files.create)
3. Books API - Search for Chetan Bhagat’s books from the books.volumes.list API.Get the ID of the first book. Add it to the ‘Reading Now’ shelf (books.mylibrary. bookshelves.addVolume)
4. Youtube API - Search for all videos matching the keyword "dhinchak pooja"(youtube.search.list) and verify that none of them are having less than 1 million views

## Framework Reference:-
https://github.com/kohli-harshit/restassured-testng-template


## Google API Reference:-
https://developers.google.com/apis-explorer

## Get Client ID & Secret from:-
https://console.developers.google.com

## Access Token from:-
https://developers.google.com/oauthplayground
