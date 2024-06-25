class GrantPermissionActivityButtonActionsLog(
  val groupName: String,
  val uid: Int,
  val packageName: String,
  val presentedButtons: Int,
  val clickedButton: Int,
  val sessionId: Long,
  val targetSdkVersion: Int,
  val selectedPrecision: Int,
  val isPermissionRationaleShown: Boolean
)
