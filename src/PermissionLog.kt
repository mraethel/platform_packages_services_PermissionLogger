@Entity
data class PermissionLog(
  @PrimaryKey(autoGenerate = true) val id: Int,
  val time: Instant,
  var appPermissionFragmentActionReportedLog: AppPermissionFragmentActionReportedLog?,
  var grantPermissionActivityButtonActionsLog: GrantPermissionActivityButtonActionsLog?,
  var unknown: Message?
)
