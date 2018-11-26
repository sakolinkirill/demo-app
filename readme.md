        docker run --name mongo -p 27017:27017 -d mongo
        docker run --name redis -p 6379:6379 -d redis
        docker run --name ng -p8080:8080 ng

        db.createCollection("logs", {capped: true, size: 4096, max:5})


Web is Nginx HTTP server:
1. Caching of static content
2. Authorization (list of API and roles) using JWT tokens
3. Reverse proxy

        keytool -genkeypair -alias jwt -keyalg RSA -keypass 123456 -keystore jwt.jks -storepass 123456

        rate(http_server_requests_seconds_sum{uri="/test2"}[5m]) / rate(http_server_requests_seconds_count{uri="/test2"}[5m])

        Bearer eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE1NDE4MTc4ODYsInVzZXJfbmFtZSI6ImxvZ2luIiwiYXV0aG9yaXRpZXMiOlsiVEVTVF9ST0xFIl0sImp0aSI6IjBkMjE1MTU5LTk0YzktNDMxZi05YWFhLTExYTJhYmQ2NjUyOSIsImNsaWVudF9pZCI6ImJyb3dzZXIiLCJzY29wZSI6WyJhcGkiXX0.RP8f8mwcp43hqeVuF9uKbpqluCFioo6AsRGpz2KTSbabJajhDdU5BL6GpyFD8KbNFDad44upKZ4xmcz10Gjg6O-nH0En9d5NRAzqJYZEZ3oX8jcMRlImflH5j3aYTCjjFDEzlmpXv6WX7OWbc0jcGuNLoS2HNaMim1h329FWV4KP2Jy28HC_9Wgv9Tz1jMixx4EeEhxsWzr3EZv9RqGJ4x627rGcC9xKi1wcwYq_ZKCSrZRvRZ-gnFkKC6MgrqpBRVGA0cjeReJS56nMgYXF-Z4FnElzSsyjG4FVZqldfXt0wA0pWKZgXoFSW5Qa2s54SmwpDENSdzqDk1ueYFn_Sg