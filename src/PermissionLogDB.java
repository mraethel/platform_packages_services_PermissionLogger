@Database(entities = {PermissionLog.class}, version = 1)
public abstract class PermissionLogDB extends RoomDatabase {
  public abstract Plao plao();
}
