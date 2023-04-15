package com.example.pikachoong.charge;

import com.example.pikachoong.Navigate;
import com.example.pikachoong.charge_entities.StationEntity_Res;
import com.skt.Tmap.TMapPoint;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.sql.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLEncoder;

class charging_station{
    String statName;
    String stationID;
    String address;
    String location, lat, lng, usetime, busild, stat, output;
}
public class Stations {
    private final String DataKey= "UhKarman7DgUATAB4EIurxt5ch40fMqqTm8MWt3CX%2Bna3%2BYttYFbg%2FayLNVgMB6%2FCXEITNP%2B36laZcUqY5wYDA%3D%3D";
    private StringBuilder urlBuilder;
    private String station_addr;
    private Navigate navi;
    private StationEntity_Res stations;
    public ArrayList<TMapPoint> list;
    private Navigate N;

    charging_station cs;

    public ArrayList<TMapPoint> Chargestation() throws IOException {

        navi = N.obj();
        for(int i=0;i<navi.p.size();i++){
            if(navi.mark.equals(navi.p.get(i).getName())){ 
                station_addr = navi.p.get(i).getMiddleAddrName();//입력한 장소명의 주소를 받아옴
            }
        }



        ///검색해서 따온 XML 파싱 시작할거임



        boolean initem = false, instatNm = false, instatId = false, inaddr = false,inlocation= false,
                inlat = false, inlng = false, inuseTime = false, inbusild= false, instat = false, inoutput = false;

        String statNm = null, statId= null , addr=null, location=null, lat=null, lng=null, useTime=null,
                stat = null, output = null ;



        try {
            URL url= new URL("https://apis.data.go.kr/B552584/EvCharger/getChargerInfo?" +
                    "serviceKey=UhKarman7DgUATAB4EIurxt5ch40fMqqTm8MWt3CX%2Bna3%2BYttYFbg%2FayLNVgMB6%2FCXEITNP%2B36laZcUqY5wYDA%3D%3D&" +
                    "pageNo=1&" +
                    "numOfRows=30&" +
                    "zcode=11");

            XmlPullParserFactory parserCreator = XmlPullParserFactory.newInstance();
            XmlPullParser parser = parserCreator.newPullParser();

            parser.setInput(url.openStream(), null);

            int parserEvent= parser.getEventType();

            while(parserEvent != XmlPullParser.END_DOCUMENT){
                switch(parserEvent)
                {

                    case XmlPullParser.START_TAG:
                        if(parser.getName().equals("item"))
                        {
                            cs= new charging_station();
                        }

                        if(parser.getName().equals("statNm") {
                            instatNm= true;
                    }
                    if(parser.getName().equals("statId"){
                        instatId= true;
                    }

                    if(parser.getName().equals("addr"){
                        inaddr= true;
                    }
                    if(parser.getName().equals("location"){
                        inlocation= true;
                    }
                    if(parser.getName().equals("lat")
                        {
                        inlat= true;
                    }
                    if(parser.getName().equals("lng"){
                        inlng= true;
                    }
                    if(parser.getName().equals("useTime"){
                        inuseTime= true;
                    }
                    if(parser.getName().equals("stat"){
                        instat= true;
                    }
                    if(parser.getName().equals("output"){
                        inoutput= true;
                    }
                    if(parser.getName().equals("message")){ //message 태그를 만나면 에러 출력
                        System.out.println("xml 데이터 불러오기 실패!");
                    }
                    break;


                    case XmlPullParser.TEXT:
                        if(instatNm){
                            statNm=parser.getText();
                            cs.statName=statNm;
                            instatNm=false;
                        }
                        if(instatId)
                        {
                            statId=parser.getText();
                            cs.stationID=statId;
                            instatId=false;
                        }
                        if(inaddr)
                        {
                            addr=parser.getText();
                            cs.stationID
                            inaddr=false;
                        }
                        if(inlocation)
                        {
                            location=parser.getText();
                            station.add(3,location);
                            inlocation=false;
                        }
                        if(inlat)
                        {
                            lat=parser.getText();
                            station.add(4,lat);
                            inlat=false;
                        }
                        if(inlng)
                        {
                            lng=parser.getText();
                            station.add(5,lng);
                            inlng=false;
                        }
                        if(inuseTime)
                        {
                            useTime=parser.getText();
                            station.add(6,useTime);
                            inuseTime=false;
                        }
                        if(instat)
                        {
                            stat=parser.getText();
                            station.add(7,stat);
                            instat=false;
                        }
                        if(inoutput)
                        {
                            output=parser.getText();
                            inoutput=false;
                        }
                        break;

                    case XmlPullParser.END_TAG:
                    {

                    }
                }
                parserEvent=parser.next();

            }
        } catch (XmlPullParserException e) {
            throw new RuntimeException(e);
        }


    }
}
