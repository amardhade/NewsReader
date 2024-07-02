Please refer master branch
- Update API BASE_URL & NEWS_API_KEY into local.properties to run the application
NEWS_API_KEY=352ff9823522498189934090ba244d38
BASE_URL=https://newsapi.org/

Project details,
- Used MVVM + Clean architecture
- Jetpack compose UI framework is used to build the UI
- Divided into Data, Domain & Presentation layer
- commonUI package: All common UI components
- di package: Used Hilt as for dependency injection
- utilities package: All utility classes
- Use of UseCase for the re-usability and testability
- Single activity with single viewmodel as MainActivityViewModel

Functional details: Ref. news APIs https://newsapi.org/
- Used top-headlines endpoint to browse the list of news(NewsFeed Screen).
- Bookmark any news from NewsDetail screen.
- To read full content is restricted with free package. Please refer for more details https://newsapi.org/docs/endpoints/top-headlines
- Bookmarked news will be listed into BookmarkNews Screen
- Filtered news by using news category

Testing strategy:
- Added test cases for Repository & use-cases
