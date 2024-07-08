package com.mraethel.PermissionLogger

import androidx.room.*

@Dao
interface AppPermissionFragmentActionReportedDao {
  @Insert
  fun log(l: AppPermissionFragmentActionReportedLog)

  @Query("SELECT * FROM apfar")
  fun getAll(): List<AppPermissionFragmentActionReportedLog>
}
