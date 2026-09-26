<h2>Change Logs</h2>

<h4>9/25/26</h4>
Added Post entity
Created table on PostgreSQL
Created data flow with dummmy user 
HTTP POST -> PostController -> CreatePostRequest -> PostService -> PostRepository.save() -> PostgreSQL

TODO:
Add idempotency 
Integrate Firebase Auth
Test it manually
Create Retrofit interface in Android
Replace Firestore in Android with backend
More integration tests