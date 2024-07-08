package com.mraethel.PermissionLogger

import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.Handler
import android.os.IBinder
import android.os.Message
import android.os.Messenger
import androidx.room.Room

private const val MSG_LOG_APP_PERMISSION_FRAGMENT_ACTION_REPORTED = 0
private const val MSG_READ_APP_PERMISSION_FRAGMENT_ACTION_REPORTED = 2
private const val MSG_DEL_APP_PERMISSION_FRAGMENT_ACTION_REPORTED = 4

private const val MSG_LOG_GRANT_PERMISSION_ACTIVITY_BUTTON_ACTIONS = 1
private const val MSG_READ_GRANT_PERMISSION_ACTIVITY_BUTTON_ACTIONS = 3
private const val MSG_DEL_GRANT_PERMISSION_ACTIVITY_BUTTON_ACTIONS = 5

class PermissionLogger : Service() {
  private lateinit var mMessenger: Messenger
  
  internal class IncomingHandler(
    context: Context,
    private val applicationContext: Context = context.applicationContext
  ): Handler() {
    
    val appPermissionFragmentActionReportedDatabase = Room.databaseBuilder(
      applicationContext,
      AppPermissionFragmentActionReportedDatabase::class.java,
      "apfar"
    ).build()

    val grantPermissionActivityButtonActionsDatabase = Room.databaseBuilder(
      applicationContext,
      GrantPermissionActivityButtonActionsDatabase::class.java,
      "gpaba"
    ).build()

    override fun handleMessage(msg: Message) {
      val appPermissionFragmentActionReportedDao
        = appPermissionFragmentActionReportedDatabase.appPermissionFragmentActionReportedDao()

      val grantPermissionActivityButtonActionsDao
        = grantPermissionActivityButtonActionsDatabase.grantPermissionActivityButtonActionsDao()
      
      when (msg.what) {
        MSG_LOG_APP_PERMISSION_FRAGMENT_ACTION_REPORTED -> {
          val appPermissionFragmentActionReportedLog
            = msg.obj as AppPermissionFragmentActionReportedLog
          appPermissionFragmentActionReportedDao.log(appPermissionFragmentActionReportedLog) }
        
        MSG_LOG_GRANT_PERMISSION_ACTIVITY_BUTTON_ACTIONS -> {
          val grantPermissionActivityButtonActionsLog
            = msg.obj as GrantPermissionActivityButtonActionsLog
          grantPermissionActivityButtonActionsDao.log(grantPermissionActivityButtonActionsLog) }
        
        MSG_READ_APP_PERMISSION_FRAGMENT_ACTION_REPORTED -> {
          val appPermissionFragmentActionReportedLogs = appPermissionFragmentActionReportedDao.getAll()
          rpl = Message.obtain(null,
              MSG_READ_APP_PERMISSION_FRAGMENT_ACTION_REPORTED,
              appPermissionFragmentActionReportedLogs)
          
          try {
            msg.replyTo.send(rpl)
          } catch (e: RemoteException) {
            e.printStackTrace();
          }
        }
        
        MSG_READ_GRANT_PERMISSION_ACTIVITY_BUTTON_ACTIONS -> {
          val grantPermissionActivityButtonActionsLogs = grantPermissionActivityButtonActionsDao.getAll()
          
          rpl = Message.obtain(null,
              MSG_READ_GRANT_PERMISSION_ACTIVITY_BUTTON_ACTIONS,
              grantPermissionActivityButtonActionsLogs)
          
          try {
            msg.replyTo.send(rpl)
          } catch (e: RemoteException) {
            e.printStackTrace();
          }
        }

        MSG_DEL_APP_PERMISSION_FRAGMENT_ACTION_REPORTED -> appPermissionFragmentActionReportedDao.clearAllTables()
        
        MSG_DEL_GRANT_PERMISSION_ACTIVITY_BUTTON_ACTIONS -> grantPermissionActivityButtonActionsDao.clearAllTables()
      }
    }
  }

  override fun onBind(intent: Intent): IBinder? {
    mMessenger = Messenger(IncomingHandler(this))
    return mMessenger.binder
  }
}
