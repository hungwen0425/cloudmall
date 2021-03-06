package io.renren.entity.mongo;

import io.renren.adaptor.MongoTableInfoAdaptor;
import org.apache.commons.collections.CollectionUtils;

import java.io.Serializable;
import java.util.*;


/**
 * 解析表之後得到的資料實體
 * 换句話說這個類就是一張mongo一張表的内容
 *
 * @author gxz 514190950@qq.com
 */

public class MongoDefinition implements Serializable {
    /***屬性名**/
    private String propertyName;
    /***屬性類型 對應mongodb api $type   如果没有類型 表示這是一個顶层實體  而不是内嵌屬性**/
    private Integer type;
    /***此屬性是否是數組**/
    private boolean array = false;
    /***如果此屬性是物件  那么他仍然有此類型的子類**/
    private List<MongoDefinition> child;


    public List<MongoGeneratorEntity> getChildrenInfo(String tableName) {
        List<MongoGeneratorEntity> result = new ArrayList<>();
        MongoGeneratorEntity info = new MongoGeneratorEntity();
        // 表資料
        Map<String, String> tableInfo = MongoTableInfoAdaptor.tableInfo(tableName);
        // 列名資料
        List<Map<String, String>> columnsInfo = new ArrayList<>();
        info.setColumns(columnsInfo);
        info.setTableInfo(tableInfo);
        result.add(info);
        List<MongoDefinition> child = this.getChild();
        for (MongoDefinition mongoDefinition : child) {
            Map<String, String> columnInfo = new HashMap<>(5);
            columnInfo.put("columnName", mongoDefinition.getPropertyName());
            columnInfo.put("dataType", Type.typeInfo(mongoDefinition.getType()));
            columnInfo.put("extra", mongoDefinition.isArray() ? "array" : "");
            columnsInfo.add(columnInfo);
            if (mongoDefinition.hasChild()) {
                result.addAll(mongoDefinition.getChildrenInfo(mongoDefinition.getPropertyName()));
            }
        }
        return result;
    }

    public boolean hasChild() {
        final int objectType = 3;
        return type == null || Objects.equals(type, objectType) || CollectionUtils.isNotEmpty(child);
    }


    public boolean primaryBean() {
        return type == null;
    }


    public MongoDefinition setType(Integer type) {
        this.type = type;
        return this;
    }

    public String getPropertyName() {
        return propertyName;
    }

    public MongoDefinition setPropertyName(String propertyName) {
        this.propertyName = propertyName;
        return this;
    }

    public Integer getType() {
        return type;
    }

    public boolean isArray() {
        return array;
    }

    public MongoDefinition setArray(boolean array) {
        this.array = array;
        return this;
    }

    public List<MongoDefinition> getChild() {
        return child;
    }

    public MongoDefinition setChild(List<MongoDefinition> child) {
        this.child = child;
        return this;
    }
}
