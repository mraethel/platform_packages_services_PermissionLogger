package com.mraethel.PermissionLogger

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.Instant

@Entity(tableName = "apfar")
data class AppPermissionFragmentActionReportedLog @JvmOverloads constructor(
  val sessionId: Long,
  val changeId: Long,
  val uid: Int,
  val packageName: String,
  val permInfoName: String,
  val isGrantedIncludingAppOp: Boolean,
  val flags: Int,
  val buttonPressed: Int,
  val time: Long = Instant.now().toEpochMilli(),
  @PrimaryKey(autoGenerate = true) val id: Int = 0,
)

