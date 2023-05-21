package org.example;


import org.json.simple.parser.ParseException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.Arrays;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) throws ParseException {

        //원본 문자열
        String orginStr = "<p><img alt=\"test.png\" arattid=\"302290601\" arentryid=\"000000000000502\" arschema=\"RKM:HowToTemplate\" rkmalt=\"test.png\" rkmtitle=\"test.png\" src=\"\" style=\"width: 126px; height: 190px;\" title=\"test.png\" />";


        //문자열 쪼개기 ( <img 태그 기준으로 3등분 )
        String[] strArr = orginStr.split("<img |/>",3);

//        for(int i = 0; i<strArr.length; i++){
//            System.out.println(i+1+"번째 : "+strArr[i]);
//        }

        //원본 img 태그의 필요한 값 얻기
        Document doc = Jsoup.parse(orginStr);
        Element orginBody = doc.body();
        Elements imgElements= orginBody.getElementsByTag("img");

        String[] style = imgElements.attr("style").replace("px","").replace(" ","").split(":|;");
        HashMap<String,String> styleMap = new HashMap();

        styleMap.put(style[0].toString(),style[1].toString());
        styleMap.put(style[2].toString(),style[3].toString());


        String title = " title=\""+imgElements.attr("title")+"\"";
        String height = " height=\""+styleMap.get("height")+"\"";
        String width = " width=\""+styleMap.get("width")+"\"";

        String alt = " alt=\""+imgElements.attr("alt")+"\"";

        //문자열 넣기 ex) src = "https://developer.servicenow.com/dev.do#!/home"
        String src = "https://developer.servicenow.com/dev.do#!/home";
        String srcStr = " src= \"" + src+"\"";
        strArr[1]="<img"+title+srcStr+alt+height+width+" />";

        System.out.println(strArr[1].toString());


    }
}

