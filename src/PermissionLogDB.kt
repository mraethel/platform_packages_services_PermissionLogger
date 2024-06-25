@Database(entities = [PermissionLog::class], version = 1)
abstract class PermissionLogDB : RoomDatabase() {
  abstract fun plao(): Plao
}
