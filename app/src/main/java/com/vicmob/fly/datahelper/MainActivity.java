package com.vicmob.fly.datahelper;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.os.Build;
import android.Manifest;
import android.support.v4.app.ActivityCompat;
import android.content.pm.PackageManager;
import com.vicmob.fly.datahelper.http.GetDataService;
import com.vicmob.fly.datahelper.utils.Base64;
import com.vicmob.fly.datahelper.utils.LogUtil;
import com.vicmob.fly.datahelper.utils.MD5;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import java.util.List;
import java.util.HashMap;
import java.util.Map;


public class MainActivity extends AppCompatActivity {
//    Map<String, String> connectionInfo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
        requestPermissions();
//  v1_ 28ece52d469ee65b4eb49c7592eb83f3 66eb0c51f2ee0136105c26674fadd11a 50ecc73cd4983c6207e4f8812fec5a53 @stranger
//        wxid_w737xand1gkg22     薇恩
        String  varString  ="v1_1a12cc9c8a7b3a3b6434e34dc9a89d7ff7caf72d51c25c81699f0a5af02afa4aa930e7bc55e9663066ead8e5e7a05d7d@stranger";
        String WxString = "wxid_w737xand1gkg22";
        String  wxMd5 = MD5.getMD5("wxid_w737xand1gkg22");
        String  wxMd = MD5.getMD5("薇恩");
        LogUtil.i("WxMd5:"+wxMd5 +"  ##  "+wxMd);

        String encode = Base64.base64Encode(WxString.getBytes());
        String encode1 = Base64.base64Encode(wxMd5.getBytes());
        LogUtil.i("WxMd51:"+encode+"  ##  "+encode1);

        String decode = new String(Base64.base64Decode(encode));
        LogUtil.i("WxMd52:"+decode+"  ##  ");
        String mMsg = "<msg fromusername=\"v1_cff496075b4717e108ae8b43a5c47617c403a7f4ed9fefcb4ef0ae22fd12b146467ad2ef88bcb7f217040df050a3fac1@stranger\" fromnickname=\"\uE035圣哥\uE035\" source=\"来自手机通讯录好友\" fullpy=\"?shengge?\" shortpy=\"?SG?\" imagestatus=\"3\" scene=\"10\" extnickname=\"\" mobileidentify=\"e90f4aafeacee64cee0e73ac4b29070f\" mobilelongidentify=\"e90f4aafeacee64cee0e73ac4b29070f\" qqnum=\"0\" qqnickname=\"\" qqremark=\"\" country=\"中国\" province=\"黑龙江\" city=\"哈尔滨\" sign=\"为目标努力奋斗\" percard=\"1\" sex=\"1\" weibo=\"\" weibonickname=\"\" albumflag=\"0\" albumstyle=\"0\" albumbgimgid=\"916103639334912_916103639334912\" snsflag=\"49\" snsbgimgid=\"http://mmsns.qpic.cn/mmsns/JZNr4hQ6pJjWxsuaWqDHJNz3icLcTUtHa4ia9icgzb7ibhQ/0\" snsbgobjectid=\"11353921336138199045\" bigheadimgurl=\"http://wx.qlogo.cn/mmhead/ver_1/aw1K6v7VOva8RfGMHLTYDPZkiaKwROlRa4X8HtbibmluickRfB97CHZiaojjQIImwl4mjhLQKjkgUicXNEFlIqQZ6kKwWnEIDFV9VBFdZwdZGujc/0\" smallheadimgurl=\"http://wx.qlogo.cn/mmhead/ver_1/aw1K6v7VOva8RfGMHLTYDPZkiaKwROlRa4X8HtbibmluickRfB97CHZiaojjQIImwl4mjhLQKjkgUicXNEFlIqQZ6kKwWnEIDFV9VBFdZwdZGujc/96\" antispamticket=\"v2_d5013983f5973a3227292e84b8450534e0d6a8897e20e9d43d36f3eb6c1f960f701dd7b94f17ed708640ef23dd8201ec@stranger\" />\n";

        LogUtil.i(XmlToString(mMsg).get("country")+"&&&"+XmlToString(mMsg).get("province")+"&&&"+XmlToString(mMsg).get("city"));

        startService(new Intent(MainActivity.this, GetDataService.class));

    }
    public Map<String, String> XmlToString(String mMsg){
        Document doc = null;
        Map<String, String> connectionInfo = null;
        try {
            doc = DocumentHelper.parseText(mMsg);
            Element rootElt = doc.getRootElement(); // 获取根节点
            System.out.println("根节点：" + rootElt.getName()); // 拿到根节点的名称
             connectionInfo = new HashMap<String, String>();
            List<Attribute> attributes = rootElt.attributes();
            for (int i = 0; i < attributes.size(); ++i) { // 添加节点属性
                connectionInfo.put(attributes.get(i).getName(), attributes.get(i).getValue());
            }

        } catch (DocumentException e) {
            e.printStackTrace();
        }

//        String country =connectionInfo.get("country") ;
//        String province =connectionInfo.get("province")  ;
//        String city = connectionInfo.get("city") ;
//        LogUtil.i(country+"&&&"+province+"&&&"+city);


        return connectionInfo;
    }


    private void requestPermissions(){
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                int permission = ActivityCompat.checkSelfPermission(this,
                        Manifest.permission.READ_PHONE_STATE);
                if(permission!= PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(this,new String[] {
                            Manifest.permission.READ_PHONE_STATE, Manifest.permission.WRITE_EXTERNAL_STORAGE,
                            Manifest.permission.LOCATION_HARDWARE,
                            Manifest.permission.WRITE_SETTINGS,Manifest.permission.READ_EXTERNAL_STORAGE,
                            Manifest.permission.RECORD_AUDIO,Manifest.permission.READ_CONTACTS},0x0010);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

}
