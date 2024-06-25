@Dao
interface Plao {
  @Insert
  fun logPermissionEvent(): PermissionLog

  @Query("SELECT * FROM permissionlog")
  fun getAll(): List<PermissionLog>
}
