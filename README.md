# taichi carpet

A carpet extension made for [taichi SMP](https://discord.gg/6U6Y8c7HQ2). This includes the carpet rule that we thought our server needed.

## Rules

### optimizedDragonRespawn
Optimize dragon respawn method. Ported from carpet AMS addition.
> [!WARNING]
> Couldn’t ensure same behavior as vanilla Minecraft after enabling this rule.
- Type: `boolean`
- Default value: `false`
- Categories: `OPTIMIZATION`, `TAICHI`

### optimizedFallDamageRaycast
Disable fallDamageRaycast while the entity is moving upwards. Ported from chronos carpet by bread, pentasteve.
- Type: `boolean`
- Default value: `false`
- Categories: `OPTIMIZATION`, `TAICHI`

### commandView
Enable /view command to changes the view distance of the server without op.

`/carpet carpetCommandPermissionLevel` allows other rules to be changed, but this command only allows the viewDistance to be changed.
- Type: `String`
- Default value: `ops`
- Allowed options: `true`, `false`, `ops`, `0`, `1`, `2`, `3`, `4`
- Categories: `SURVIVAL`, `COMMAND`, `TAICHI`

### commandSimulation
Enable /view command to changes the simulation distance of the server without op.

`/carpet carpetCommandPermissionLevel` allows other rules to be changed, but this command only allows the simulationDistance to be changed.
- Type: `String`
- Default value: `ops`
- Allowed options: `true`, `false`, `ops`, `0`, `1`, `2`, `3`, `4`
- Categories: `SURVIVAL`, `COMMAND`, `TAICHI`

### commandNotice
Enable /notice command to change notice logger value.
- Type: `String`
- Default value: `ops`
- Allowed options: `true`, `false`, `ops`, `0`, `1`, `2`, `3`, `4`
- Categories: `SURVIVAL`, `COMMAND`, `TAICHI`

### disableNetherPortalCollisionCheck
Disable laggy nether portal collision checks introduced in 1.19.3.
Ported from chronos carpet.
- Type: `boolean`
- Default value: `false`
- Categories: `OPTIMIZATION`, `TAICHI`

## commands

### view

`/view` :  display current simulationDistance

`/view [<distance>]` : change the value of viewDistance in carpet without op

`[<distance>]` : must be between `0` to `32`

### simulation
`/simulation` :  display current simulationDistance

`/simulation [<distance>]` : change the value of simulationDistance in carpet without op

`[<distance>]` : must be between `0` to `32`

### notice
`/notice <text>` : change notice logger text

`<text>` : must be string and can be enclosed in `" "` to allow the input of a string including spaces


## Loggers

### notice
`/log notice`

Simple logger that notice to everyone something running or doing.
To change value, use `/notice`

### autosave
`/log autosave`

Simple logger that display when the server will autosave and how long ago the last autosave was.
This logger does not directly detect autosave, but rather a calculated value based on the time elapsed since the server started, which could be incorrect.
