@Dao
interface Plao {
  @Insert
  fun log(pl: PermissionLog)

  @Query("SELECT * FROM permissionlog")
  fun getAll(): List<PermissionLog>
}
