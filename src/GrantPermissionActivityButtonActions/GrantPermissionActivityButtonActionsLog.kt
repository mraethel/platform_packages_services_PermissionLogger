package com.mraethel.PermissionLogger

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.Instant

@Entity(tableName = "gpaba")
data class GrantPermissionActivityButtonActionsLog(
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
  @PrimaryKey(autoGenerate = true)
  var id: Int = 0
  var time: Long = Instant.now().toEpochMilli()
}
