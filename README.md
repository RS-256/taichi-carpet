# taichi carpet

A carpet extension made for [taichi SMP](https://discord.gg/6U6Y8c7HQ2). This includes the carpet rule that we thought our server needed.

## Rules

### disableWanderingOnVehicle
Disable wandering when riding a vehicle. Basically, dosen't effect anything.
- Type: `boolean`
- Default value: `false`
- Categories: `OPTIMIZATION`, `TAICHI`

### disableWanderingOutOfWorld
Disable wandering when entity is out of the world. Basically, dosen't effect anything.
- Type: `boolean`
- Default value: `false`
- Categories: `OPTIMIZATION`, `TAICHI`

### disablePushEntityOutOfWorld
Disable pushing when entity is out of world. Basically, dosen't effect anything.
- Type: `boolean`
- Default value: `false`
- Categories: `OPTIMIZATION`, `TAICHI`

### optimizedDragonRespawn
Optimize dragon respawn method. Ported from carpet AMS addition.
> [!WARNING]
> Couldn’t ensure same behavior as vanilla Minecraft after enabling this rule.
- Type: `boolean`
- Default value: `false`
- Categories: `OPTIMIZATION`, `TAICHI`

### optimizedFallDamageRaycast
Disable fallDamageRaycast while the entity is moving upwards.
Ported from chronos carpet by bread, pentasteve.
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

### commandHat
Allows you to equip items to your head slot using /hat.
Ported from EssentialAddons.
- Type: `String`
- Default value: `ops`
- Allowed options: `true`, `false`, `ops`, `0`, `1`, `2`, `3`, `4`
- Categories: `SURVIVAL`, `COMMAND`, `TAICHI`

### commandSit
Player can sit down when using /sit.
Ported from PCA.
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

### blockInventorySyncing
Enable sync of blockInventory on the server without op
(0: disable, -1: infinity)
- Type: `boolean`
- Default value: `false`
- Categories: `SURVIVAL`, `PROTOCOL`, `TAICHI`

### optimizedHypot
Changes MathHelper.hypot to a faster implementation. It gives nearly perfectly accurate results
Currently only effect chunks blending so will likely be unnoticeable. It is ~1.6x faster
recommended that you set this on permanently, and restart the server for best results
- Type: `boolean`
- Default value: `false`
- Categories: `OPTIMIZATION`, `TAICHI`

### optimizedRounding
Changes Math.round to a faster implementation. Although it does not give the exact same results
This does not affect many things and will most likely be unnoticeable. It is ~1.28x faster
- Type: `boolean`
- Default value: `false`
- Categories: `OPTIMIZATION`, `TAICHI`

### optimizedFurnaces
Optimise method of updating state for furnace, blast furnace, and smoker.
- Type: `boolean`
- Default value: `false`
- Categories: `OPTIMIZATION`, `TAICHI`

### defaultOpLevel
Set OP-permission when join to server.
- Type: `int`
- Default value: `0`
- Categories: `CREATIVE`, `TAICHI`

### deathNoticeDiscord
Death notification to discord chat over webhook.
(value type is URI)
- Type: `String`
- Default value: `#None`
- Categories: `SURVIVAL`, `TAICHI`

### deathNoticeDiscordType
deathNoticeDiscord Message Type
(embed or text)
- Type: `boolean`
- Default value: `embed`
- Categories: `SURVIVAL`, `TAICHI`

### serverName
set ServerName for deathNoticeDiscord
- Type: `boolean`
- Default value: `#None`
- Categories: `SURVIVAL`, `TAICHI`

## taichiPacketRateLimit
Set packet rate limit TaichiCarpet-Protocol
- Type: `int`
- Default value: `1`
- Categories: `PROTOCOL`, `TAICHI`


## commands

### view
`/view` :  display current simulationDistance

`/view [<distance>]` : change the value of viewDistance in carpet without op

`[<distance>]` : must be between `0` to `32`

### simulation
`/simulation` :  display current simulationDistance

`/simulation [<distance>]` : change the value of simulationDistance in carpet without op

`[<distance>]` : must be between `0` to `32`

### hat
`/hat` : equip the item you have on your own head


### sit
`/sit` : sit on the spot

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
Ported from essential addons.

## playerCommand

### fill
`/player <mcid> fill <boolean>`

`<boolean>` : switch auto fill container on/off.

### clean
`/player <mcid> clean <boolean>`

`<boolean>` : switch auto clean container on/off.
