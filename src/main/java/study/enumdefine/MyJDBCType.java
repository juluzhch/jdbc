package study.enumdefine;

import java.sql.Types;

public enum MyJDBCType implements SQLType{

    BIT(java.sql.Types.BIT),
    /**
     * Identifies the generic SQL type {@code TINYINT}.
     */
    TINYINT(Types.TINYINT);

    private int type;

    MyJDBCType(int type) {
        this.type=type;
    }

    @Override
    public String getName() {
        return name();
    }

    @Override
    public String getVendor() {
        return "java.sql";
    }

    @Override
    public Integer getVendorTypeNumber() {
        return type;
    }
}
