package com.mraethel.PermissionLogger;

import androidx.room;

@Dao
public interace Plao {
  @Insert
  void logPermissionEvent(PermissionLog log);

  @Query("SELECT * FROM permissionlog")
  List<PermissionLog> getAll();
}
