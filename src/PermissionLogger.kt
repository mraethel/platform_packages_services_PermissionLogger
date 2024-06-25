private const val MSG_DEL = 0
private const val MSG_READ = 1

private const val MSG_LOG_APP_PERMISSION_FRAGMENT_ACTION_REPORTED = 2
private const val MSG_LOG_GRANT_PERMISSION_ACTIVITY_BUTTON_ACTIONS = 3

class PermissionLogger : Service() {
  private lateinit var mMessenger: Messenger
  
  internal class IncomingHandler(
    context: Context,
    private val applicationContext: Context = context.applicationContext
  ): Handler() {
    
    val db = Room.databaseBuilder(
      applicationContext,
      PermissionLogDB::class.java,
      "permissionlog"
    ).build()

    override fun handleMessage(msg: Message) {
      val plao = db.plao()
      val time = Instant.now()
      val pl = PermissionLog(time)
      
      when (msg.what) {
        MSG_LOG_APP_PERMISSION_FRAGMENT_ACTION_REPORTED ->
          pl.appPermissionFragmentActionReportedLog
            = msg.obj as AppPermissionFragmentActionReportedLog
        MSG_LOG_GRANT_PERMISSION_ACTIVITY_BUTTON_ACTIONS ->
          pl.grantPermissionActivityButtonActionsLog
            = msg.obj as GrantPermissionActivityButtonActionsLog
        MSG_READ -> plao.getAll()
        MSG_DEL -> plao.clearAllTables()
        else -> pl.unkown = msg
        
        plao.log(pl);
      }
    }

    override fun onBind(intent: Intent): IBinder? {
      mMessenger = Messenger(IncomingHandler(this))
      return mMessenger.binder
    }
  }
}
