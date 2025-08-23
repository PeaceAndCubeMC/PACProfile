# PACProfile
A plugin providing an interface to see many PeaceAndCube features.

## Configuration

### General
- **date_format**: The date format displayed in various places.
- **first_time_advancement_name**: The name of the advancement granted when opening the profile for the first time.
- **default_home_color**: The default color of home items. One of the 16 vanilla values.
- **head_tickets_scoreboard**: The name of the score displayed in head tickets item.

### `commands_on_click`
- **coins**: The command executed when left-clicking the coins item.
- **head_tickets**: The command executed when left-clicking the head tickets item.
- **mails**: The command executed when left-clicking the mails item.
- **rules**: The command executed when left-clicking the rules item.
- **links**: The command executed when left-clicking the links item.
- **dynmap**: The command executed when left-clicking the dynmap item.

### `warps`
- _warp name_
  - **name**: The displayed name of the warp.
  - **icon**: The displayed item of the warp. Defaults to `ender_pearl`.
  - **category**: The displayed category of the warp.

### `homes`
- **enable_teleportation**: Toggles teleport action when left-clicking a home. Defaults to `true`.
- **enable_deletion**: Toggles delete action when right-clicking a home. Defaults to `true`.

### `online_players`
- **enable_teleportation**: Toggles teleport request when left-clicking a player. Defaults to `true`.

### `statistics`
- _stat name_
  - **enabled**: Toggles whether this stat is displayed. Defaults to `true`.
