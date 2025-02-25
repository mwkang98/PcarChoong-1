package com.example.pikachoong.charge;



import static androidx.core.content.PackageManagerCompat.LOG_TAG;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.app.Fragment;

import com.example.pikachoong.R;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;


class charging_station{
    String statName,stationID,address, location, lat, lng, usetime, stat, output;
}
public class Stations {
    charging_station cs;
    ArrayList<charging_station> cs_list = new ArrayList<charging_station> ();
    @SuppressLint("RestrictedApi")


    public ArrayList<charging_station> get_charge_station() throws IOException {

        ///검색해서 따온 XML 파싱 시작할거

        boolean initem = false, instatNm = false, instatId = false, inaddr = false,inlocation= false,
                inlat = false, inlng = false, inuseTime = false, inbusild= false, instat = false, inoutput = false;

        String statNm = null, statId= null , addr=null, location=null, lat=null, lng=null, useTime=null,
                stat = null, output = null ;




        try {
            URL url= new URL("https://apis.data.go.kr/B552584/EvCharger/getChargerInfo?serviceKey=UhKarman7DgUATAB4EIurxt5ch40fMqqTm8MWt3CX%2Bna3%2BYttYFbg%2FayLNVgMB6%2FCXEITNP%2B36laZcUqY5wYDA%3D%3D&pageNo=1&numOfRows=10&zcode=11");

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

                        if(parser.getName().equals("statNm"))
                        {
                            instatNm= true;
                        }
                        if(parser.getName().equals("statId"))
                        {
                            instatId= true;
                        }

                        if(parser.getName().equals("addr"))
                        {
                            inaddr= true;
                        }
                        if(parser.getName().equals("location"))
                        {
                            inlocation= true;
                        }
                        if(parser.getName().equals("lat"))
                        {
                            inlat= true;
                        }
                        if(parser.getName().equals("lng"))
                        {
                            inlng= true;
                        }
                        if(parser.getName().equals("useTime"))
                        {
                            inuseTime= true;
                        }
                        if(parser.getName().equals("stat"))
                        {
                            instat= true;
                        }
                        if(parser.getName().equals("output"))
                        {
                            inoutput= true;
                        }
                        if(parser.getName().equals("message"))
                        { //message 태그를 만나면 에러 출력
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
                            cs.address=addr;
                            inaddr=false;
                        }
                        if(inlocation)
                        {
                            location=parser.getText();
                            cs.location=location;
                            inlocation=false;
                        }
                        if(inlat)
                        {
                            lat=parser.getText();
                            cs.lat=lat;
                            inlat=false;
                        }
                        if(inlng)
                        {
                            lng=parser.getText();
                            cs.lng=lng;
                            inlng=false;
                        }
                        if(inuseTime)
                        {
                            useTime=parser.getText();
                            cs.usetime=useTime;
                            inuseTime=false;
                        }
                        if(instat)
                        {
                            stat=parser.getText();
                            cs.stat=stat;
                            instat=false;
                        }
                        if(inoutput)
                        {
                            output=parser.getText();
                            cs.output=output;
                            inoutput=false;
                        }
                        break;

                    case XmlPullParser.END_TAG:
                    {
                        if(parser.getName().equals("item"))
                        {
                            cs_list.add(cs);
                        }
                    }
                }
                parserEvent=parser.next();

            }
        } catch (XmlPullParserException e) {
            throw new RuntimeException(e);
        }


        return cs_list;
    }
}
