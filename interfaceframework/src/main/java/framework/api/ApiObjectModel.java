package framework.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import framework.actions.ApiActionModel;
import lombok.Data;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

/**
 * @ClassName ApiObjectModel
 * @Description 读取yaml文件后转换成对象
 * @Author sfmewl
 * @Date 2021/8/19 10:31
 **/
@Data
public class ApiObjectModel {

    /**
     * yaml文件的name，两者必须一样
     */
    private String name;

    /**
     * 存放api中yaml文件中action数据(即执行的动作)
     */
    private HashMap<String , ApiActionModel> actions;

    /**
     * 存放action里的变量
     */
    private HashMap<String ,String> obVariables = new HashMap<>();


    /**
     * 加载yaml文件，然后反序列化为某个Class的类型
     * @param path
     * @return
     * @throws IOException
     */
    public static ApiObjectModel load(String path) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper(new YAMLFactory());
        return objectMapper.readValue(new File(path),ApiObjectModel.class);
    }

    public String toString(){
        return name + actions.toString() + obVariables.toString();
    }

}