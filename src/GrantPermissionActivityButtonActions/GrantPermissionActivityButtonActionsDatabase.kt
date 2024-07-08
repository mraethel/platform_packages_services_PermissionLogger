package com.mraethel.PermissionLogger

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [GrantPermissionActivityButtonActionsLog::class], version = 1)
abstract class GrantPermissionActivityButtonActionsDatabase: RoomDatabase() {
  abstract fun grantPermissionActivityButtonActionsDao(): GrantPermissionActivityButtonActionsDao
}
