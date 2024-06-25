class AppPermissionFragmentActionReportedLog(
  val sessionId: Long,
  val changeId: Long,
  val uid: ?,
  val packageName: String,
  val permInfoName: String,
  val isGrantedIncludingAppOp: Boolean,
  val flags: Int,
  val buttonPressed: Int
)
