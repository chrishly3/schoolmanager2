package utils.HttpCode;





import lombok.Data;
import org.apache.catalina.User;

import java.util.HashMap;
import java.util.Map;

//定义具体的数据返回格式
@Data
public class R {
//链式编程
    private Boolean success;
    private Integer code;
    private String message;
    private Map<String,Object> data = new HashMap<>();

    private R(){}

    //操作成功，调用这个方法，返回成功的数据
    public static R ok(){
        R r = new R();
        r.setSuccess(true);
        r.setCode(ResultCode.SUCCESS);
        r.setMessage("操作成功");
        return r;
    }

    public static R ok(String token , String keyId ,String userName ,String Pwd){
        Map<String,Object> data = new HashMap<>();
        R r = new R();
        r.setSuccess(true);
        r.setCode(ResultCode.SUCCESS);
        r.setMessage("操作成功");
        data.put("token", token);
        data.put("keyId", keyId);
        data.put("userName", userName);
        data.put("Pwd", Pwd);
        r.setData(data);
        return r;
    }




    //操作失败，调用这个方法，返回失败的数据
    public static R error(){
        R r = new R();
        r.setSuccess(false);
        r.setCode(ResultCode.ERROR);
        r.setMessage("操作失败");
        return r;
    }

    //使用链式编程
    public R success(Boolean success){
        this.setSuccess(success);
        return this;
    }

    public R message(String message){
        this.setMessage(message);
        return this;
    }

    public R code(Integer code){
        this.setCode(code);
        return this;
    }

    public R data(String key, Object value){
        this.data.put(key, value);
        return this;
    }

    public R data(Map<String, Object> map){
        this.setData(map);
        return this;
    }
}
