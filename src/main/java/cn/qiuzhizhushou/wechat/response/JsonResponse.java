package cn.qiuzhizhushou.wechat.response;

/**
 * Created by IDEA.
 * User: mc
 * Date: 19/3/4
 * Time: 上午10:43
 */
public class JsonResponse
{
    private int code;

    private String message;

    private Object data;

    public JsonResponse()
    {

    }

    public JsonResponse(int code, String message, Object data)
    {
        this.code = code;
        this.message = message;
        this.data = data;
    }


    public static JsonResponse create(int code, String message, Object data)
    {
        JsonResponse response = new JsonResponse(code, message, data);
        return response;
    }

    public static JsonResponse create(int code, String message)
    {
        JsonResponse response = new JsonResponse(code, message, null);
        return response;
    }

    public static JsonResponse success(Object data)
    {
        JsonResponse response = new JsonResponse(0, "success", data);
        return response;
    }

    public int getCode()
    {
        return code;
    }

    public void setCode(int code)
    {
        this.code = code;
    }

    public String getMessage()
    {
        return message;
    }

    public void setMessage(String message)
    {
        this.message = message;
    }

    public Object getData()
    {
        return data;
    }

    public void setData(Object data)
    {
        this.data = data;
    }
}
