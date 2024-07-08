package com.mraethel.PermissionLogger

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.Instant

@Entity(tableName = "gpaba")
data class GrantPermissionActivityButtonActionsLog(
  @PrimaryKey(autoGenerate = true) val id: Int,
  val groupName: String,
//val uid: Int,
//val packageName: String,
  val presentedButtons: Int,
  val clickedButton: Int,
//val sessionId: Long,
//val targetSdkVersion: Int,
  val selectedPrecision: Int,
  val isPermissionRationaleShown: Boolean
) {
  val time: Long = Instant.now().toEpochMilli()
}
