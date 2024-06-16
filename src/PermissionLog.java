@Entity
public class PermissionLog {
  @PrimaryKey
  public int uid;

  @ColumnInfo(name = "time")
  public whatevertime time;

  @ColumnInfo(name = "etc")
}
