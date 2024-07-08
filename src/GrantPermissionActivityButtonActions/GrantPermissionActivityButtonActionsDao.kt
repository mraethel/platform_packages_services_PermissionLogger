package com.mraethel.PermissionLogger

import androidx.room.*

@Dao
interface GrantPermissionActivityButtonActionsDao {
  @Insert
  fun log(l: GrantPermissionActivityButtonActionsLog)

  @Query("SELECT * FROM gpaba")
  fun getAll(): List<GrantPermissionActivityButtonActionsLog>
}
