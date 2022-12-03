# Gotify Android Enhanced
Based on the fantastic work of [gotify/android](https://github.com/gotify/android), with my own personalizations.

## Added features:

- Ability to override Android notification's LargeIcon using `extras` . `client::notification` . `imageUrl`.
    - The new icon is shown also in notifications list inside the app.
    - If `imageUrl` and `bigImageUrl` are the same, when the picture is expanded, the LargeIcon is hidden.
- Ability to open another application at notification click using `extras` . `client::notification` . `click` . `package`.
    - It takes precedence over `url`, but uses it as a fallback in case an application with the submitted package is not found.
    - It was necessary to add `QUERY_ALL_PACKAGES` permissions, as there's no possibility to know which packages are used.
- Ability to open the notification intent also in the messages list inside the application.
