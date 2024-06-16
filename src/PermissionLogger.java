package com.mraethel.PermissionLogger;

import androidx.room;

public class PermissionLogger extends Service {
  static PermissionLogDB db = Room.databaseBuilder(getApplicationContext(),
      PermissionLogDB.class, "permissionlog").build();

  static final int MSG_LOG  = 0;
  static final int MSG_READ = 1;
  static final int MSG_DEL  = 2;

  static class IncomingHandler extends Handler {
    private Context appContext;

    IncomingHandler(Context context) {
      appContext = context.getApplicationContext();
    }

    @Override
    public void handleMessage(Message msg) {
      Plao plao = db.plao();

      switch (msg.what) {
        case MSG_LOG: plao.logPermissionEvent((PermissionLog) msg.obj); break;
        case MSG_READ: plao.getAll(); break;
        case MSG_DEL: plao.clearAllTables(); break;
        default: break;
      }
    }
  }

  Messenger mMessenger;

  @Override
  public IBinder onBind(Intent intent) {
    mMessenger = new Messenger(new IncomingHandler(this));
    return mMessenger.getBinder();
  }
}
