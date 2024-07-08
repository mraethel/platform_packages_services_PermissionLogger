package com.mraethel.PermissionLogger

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [AppPermissionFragmentActionReportedLog::class], version = 1)
abstract class AppPermissionFragmentActionReportedDatabase: RoomDatabase() {
  abstract fun appPermissionFragmentActionReportedDao(): AppPermissionFragmentActionReportedDao
}
