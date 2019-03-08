package cn.qiuzhizhushou.wechat.util;

import org.apache.http.NameValuePair;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Map;

/**
 * Created by IDEA.
 * User: mc
 * Date: 19/3/8
 * Time: 下午4:34
 */
public class HttpUtil
{
    public static String doGet(String url, Map<String, String> args) throws IOException, URISyntaxException
    {

        URIBuilder uriBuilder = new URIBuilder(url);
        if (null != args) {
            ArrayList<NameValuePair> params = new ArrayList<>();
            args.forEach((key, value) -> {
                System.out.println(key + ":" + value);
                params.add(new BasicNameValuePair(key, value));
            });
            uriBuilder.setParameters(params);

        }
        URI uri = uriBuilder.build();
        HttpGet httpGet = new HttpGet(uri);
        String content = null;
        try (CloseableHttpClient httpClient = HttpClients.createDefault(); CloseableHttpResponse response = httpClient.execute(httpGet)) {

            if (response.getStatusLine().getStatusCode() == 200) {
                content = EntityUtils.toString(response.getEntity());
                System.out.println(content);
            }
        }
        return content;
    }
}
