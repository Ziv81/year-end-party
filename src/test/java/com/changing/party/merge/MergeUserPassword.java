package com.changing.party.merge;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class MergeUserPassword {

    public static void main(String[] args) {
        try {
            MergeUserPassword mergeUserPassword = new MergeUserPassword();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public MergeUserPassword() throws IOException {
        List<UserInfo> userInfos = new ObjectMapper().readValue("[\n" +
                "        {\n" +
                "            \"chineseName\": \"楊瑞明\",\n" +
                "            \"englishName\": \"Ray\",\n" +
                "            \"department\": \"總經理室\",\n" +
                "            \"group\": \"\",\n" +
                "            \"jobTitle\": \"董事長\",\n" +
                "            \"email\": \"ray.yang@changingtec.com\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"chineseName\": \"楊文和\",\n" +
                "            \"englishName\": \"Anderson\",\n" +
                "            \"department\": \"總經理室\",\n" +
                "            \"group\": \"\",\n" +
                "            \"jobTitle\": \"總經理\",\n" +
                "            \"email\": \"whyang@changingtec.com\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"chineseName\": \"許凱翔\",\n" +
                "            \"englishName\": \"Sky\",\n" +
                "            \"department\": \"專案辦公室\",\n" +
                "            \"group\": \"\",\n" +
                "            \"jobTitle\": \"\",\n" +
                "            \"email\": \"skyhsu@changingtec.com\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"chineseName\": \"楊皓廷\",\n" +
                "            \"englishName\": \"Tim\",\n" +
                "            \"department\": \"業務處\",\n" +
                "            \"group\": \"金融二組\",\n" +
                "            \"jobTitle\": \"\",\n" +
                "            \"email\": \"timyang@changingtec.com\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"chineseName\": \"陳盈璁\",\n" +
                "            \"englishName\": \"Yves\",\n" +
                "            \"department\": \"業務處\",\n" +
                "            \"group\": \"規劃組\",\n" +
                "            \"jobTitle\": \"\",\n" +
                "            \"email\": \"yves@changingtec.com\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"chineseName\": \"張世旻\",\n" +
                "            \"englishName\": \"Miller\",\n" +
                "            \"department\": \"專案處\",\n" +
                "            \"group\": \"\",\n" +
                "            \"jobTitle\": \"副總經理\",\n" +
                "            \"email\": \"miller@changingtec.com\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"chineseName\": \"黃勤偉\",\n" +
                "            \"englishName\": \"Kevin\",\n" +
                "            \"department\": \"專案處\",\n" +
                "            \"group\": \"二組\",\n" +
                "            \"jobTitle\": \"\",\n" +
                "            \"email\": \"kevinhuang@changingtec.com\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"chineseName\": \"陳昌港\",\n" +
                "            \"englishName\": \"Richard\",\n" +
                "            \"department\": \"專案處\",\n" +
                "            \"group\": \"四組\",\n" +
                "            \"jobTitle\": \"組長\",\n" +
                "            \"email\": \"cgchen@changingtec.com\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"chineseName\": \"陳怡伶\",\n" +
                "            \"englishName\": \"Candy\",\n" +
                "            \"department\": \"專案處\",\n" +
                "            \"group\": \"四組\",\n" +
                "            \"jobTitle\": \"\",\n" +
                "            \"email\": \"candy@changingtec.com\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"chineseName\": \"白家瑋\",\n" +
                "            \"englishName\": \"Chiawei\",\n" +
                "            \"department\": \"專案處\",\n" +
                "            \"group\": \"四組\",\n" +
                "            \"jobTitle\": \"\",\n" +
                "            \"email\": \"chiawei@changingtec.com\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"chineseName\": \"邱繼鴻\",\n" +
                "            \"englishName\": \"Mahone\",\n" +
                "            \"department\": \"專案處\",\n" +
                "            \"group\": \"四組\",\n" +
                "            \"jobTitle\": \"\",\n" +
                "            \"email\": \"mahone@changingtec.com\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"chineseName\": \"陳皓\",\n" +
                "            \"englishName\": \"Aaron\",\n" +
                "            \"department\": \"專案處\",\n" +
                "            \"group\": \"四組\",\n" +
                "            \"jobTitle\": \"\",\n" +
                "            \"email\": \"aaron@changingtec.com\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"chineseName\": \"洪偉嘉\",\n" +
                "            \"englishName\": \"Justin\",\n" +
                "            \"department\": \"專案處\",\n" +
                "            \"group\": \"四組\",\n" +
                "            \"jobTitle\": \"\",\n" +
                "            \"email\": \"justinhung@changingtec.com\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"chineseName\": \"傅崇瑋\",\n" +
                "            \"englishName\": \"MickFu\",\n" +
                "            \"department\": \"專案處\",\n" +
                "            \"group\": \"四組\",\n" +
                "            \"jobTitle\": \"\",\n" +
                "            \"email\": \"mickfu@changingtec.com\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"chineseName\": \"陳陽昇\",\n" +
                "            \"englishName\": \"SunChen\",\n" +
                "            \"department\": \"專案處\",\n" +
                "            \"group\": \"四組\",\n" +
                "            \"jobTitle\": \"\",\n" +
                "            \"email\": \"sunchen@changingtec.com\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"chineseName\": \"吳鈞華\",\n" +
                "            \"englishName\": \"DanielWu\",\n" +
                "            \"department\": \"專案處\",\n" +
                "            \"group\": \"四組\",\n" +
                "            \"jobTitle\": \"\",\n" +
                "            \"email\": \"danielwu@changingtec.com\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"chineseName\": \"林岱宏\",\n" +
                "            \"englishName\": \"Rusty\",\n" +
                "            \"department\": \"專案技術處\",\n" +
                "            \"group\": \"\",\n" +
                "            \"jobTitle\": \"資深協理\",\n" +
                "            \"email\": \"rusty@changingtec.com\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"chineseName\": \"張兆儀\",\n" +
                "            \"englishName\": \"Slash\",\n" +
                "            \"department\": \"專案技術處\",\n" +
                "            \"group\": \"\",\n" +
                "            \"jobTitle\": \"處長\",\n" +
                "            \"email\": \"slash@changingtec.com\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"chineseName\": \"賴柏志\",\n" +
                "            \"englishName\": \"Jack\",\n" +
                "            \"department\": \"專案技術處\",\n" +
                "            \"group\": \"三組\",\n" +
                "            \"jobTitle\": \"\",\n" +
                "            \"email\": \"jacklai@changingtec.com\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"chineseName\": \"林宗霖\",\n" +
                "            \"englishName\": \"Evan\",\n" +
                "            \"department\": \"專案技術處\",\n" +
                "            \"group\": \"三組\",\n" +
                "            \"jobTitle\": \"\",\n" +
                "            \"email\": \"evanlin@changingtec.com\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"chineseName\": \"杜宜霖\",\n" +
                "            \"englishName\": \"Ethan\",\n" +
                "            \"department\": \"專案技術處\",\n" +
                "            \"group\": \"三組\",\n" +
                "            \"jobTitle\": \"\",\n" +
                "            \"email\": \"ethantu@changingtec.com\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"chineseName\": \"謝秉叡\",\n" +
                "            \"englishName\": \"AndreHsieh\",\n" +
                "            \"department\": \"專案技術處\",\n" +
                "            \"group\": \"三組\",\n" +
                "            \"jobTitle\": \"\",\n" +
                "            \"email\": \"andrehsieh@changingtec.com\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"chineseName\": \"陳柏翔\",\n" +
                "            \"englishName\": \"NeilChen\",\n" +
                "            \"department\": \"專案技術處\",\n" +
                "            \"group\": \"三組\",\n" +
                "            \"jobTitle\": \"\",\n" +
                "            \"email\": \"neilchen@changingtec.com\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"chineseName\": \"蔡育燐\",\n" +
                "            \"englishName\": \"York\",\n" +
                "            \"department\": \"專案技術處\",\n" +
                "            \"group\": \"四組\",\n" +
                "            \"jobTitle\": \"副理\",\n" +
                "            \"email\": \"york@changingtec.com\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"chineseName\": \"陳玉菁\",\n" +
                "            \"englishName\": \"Mandy\",\n" +
                "            \"department\": \"專案技術處\",\n" +
                "            \"group\": \"四組\",\n" +
                "            \"jobTitle\": \"\",\n" +
                "            \"email\": \"mandychen@changingtec.com\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"chineseName\": \"林語婷\",\n" +
                "            \"englishName\": \"Doreen\",\n" +
                "            \"department\": \"專案技術處\",\n" +
                "            \"group\": \"四組\",\n" +
                "            \"jobTitle\": \"\",\n" +
                "            \"email\": \"doreen@changingtec.com\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"chineseName\": \"梁少鵬\",\n" +
                "            \"englishName\": \"Adam\",\n" +
                "            \"department\": \"專案技術處\",\n" +
                "            \"group\": \"四組\",\n" +
                "            \"jobTitle\": \"\",\n" +
                "            \"email\": \"adam@changingtec.com\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"chineseName\": \"吳致翰\",\n" +
                "            \"englishName\": \"Rossi\",\n" +
                "            \"department\": \"專案技術處\",\n" +
                "            \"group\": \"四組\",\n" +
                "            \"jobTitle\": \"\",\n" +
                "            \"email\": \"rossi@changingtec.com\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"chineseName\": \"李誌誠\",\n" +
                "            \"englishName\": \"Louis\",\n" +
                "            \"department\": \"專案技術處\",\n" +
                "            \"group\": \"四組\",\n" +
                "            \"jobTitle\": \"\",\n" +
                "            \"email\": \"louis@changingtec.com\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"chineseName\": \"吳佳翰\",\n" +
                "            \"englishName\": \"Ziv\",\n" +
                "            \"department\": \"專案技術處\",\n" +
                "            \"group\": \"四組\",\n" +
                "            \"jobTitle\": \"\",\n" +
                "            \"email\": \"ziv@changingtec.com\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"chineseName\": \"葉宗軒\",\n" +
                "            \"englishName\": \"Mark\",\n" +
                "            \"department\": \"專案技術處\",\n" +
                "            \"group\": \"四組\",\n" +
                "            \"jobTitle\": \"\",\n" +
                "            \"email\": \"mark@changingtec.com\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"chineseName\": \"吳凱媮\",\n" +
                "            \"englishName\": \"Jessica\",\n" +
                "            \"department\": \"專案技術處\",\n" +
                "            \"group\": \"四組\",\n" +
                "            \"jobTitle\": \"\",\n" +
                "            \"email\": \"jessicawu@changingtec.com\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"chineseName\": \"王宗儒\",\n" +
                "            \"englishName\": \"Eric\",\n" +
                "            \"department\": \"專案技術處\",\n" +
                "            \"group\": \"四組\",\n" +
                "            \"jobTitle\": \"\",\n" +
                "            \"email\": \"ericwang@changingtec.com\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"chineseName\": \"劉彥妤\",\n" +
                "            \"englishName\": \"Christy\",\n" +
                "            \"department\": \"專案技術處\",\n" +
                "            \"group\": \"四組\",\n" +
                "            \"jobTitle\": \"\",\n" +
                "            \"email\": \"christylow@changingtec.com\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"chineseName\": \"黎怡伶\",\n" +
                "            \"englishName\": \"EileenLi\",\n" +
                "            \"department\": \"專案技術處\",\n" +
                "            \"group\": \"四組\",\n" +
                "            \"jobTitle\": \"\",\n" +
                "            \"email\": \"eileenli@changingtec.com\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"chineseName\": \"陳祈勳\",\n" +
                "            \"englishName\": \"WillyChen\",\n" +
                "            \"department\": \"專案技術處\",\n" +
                "            \"group\": \"四組\",\n" +
                "            \"jobTitle\": \"\",\n" +
                "            \"email\": \"willychen@changingtec.com\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"chineseName\": \"丁旭賢\",\n" +
                "            \"englishName\": \"Martin\",\n" +
                "            \"department\": \"專案技術處\",\n" +
                "            \"group\": \"五組\",\n" +
                "            \"jobTitle\": \"組長\",\n" +
                "            \"email\": \"martin@changingtec.com\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"chineseName\": \"曾義凱\",\n" +
                "            \"englishName\": \"Leo\",\n" +
                "            \"department\": \"專案技術處\",\n" +
                "            \"group\": \"五組\",\n" +
                "            \"jobTitle\": \"\",\n" +
                "            \"email\": \"leotseng@changingtec.com\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"chineseName\": \"鄭朝隆\",\n" +
                "            \"englishName\": \"Calvin\",\n" +
                "            \"department\": \"專案技術處\",\n" +
                "            \"group\": \"五組\",\n" +
                "            \"jobTitle\": \"\",\n" +
                "            \"email\": \"calvinjheng@changingtec.com\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"chineseName\": \"陳奕明\",\n" +
                "            \"englishName\": \"Mike\",\n" +
                "            \"department\": \"專案技術處\",\n" +
                "            \"group\": \"五組\",\n" +
                "            \"jobTitle\": \"\",\n" +
                "            \"email\": \"mike@changingtec.com\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"chineseName\": \"潘逸倫\",\n" +
                "            \"englishName\": \"Roy\",\n" +
                "            \"department\": \"專案技術處\",\n" +
                "            \"group\": \"五組\",\n" +
                "            \"jobTitle\": \"\",\n" +
                "            \"email\": \"roypan@changingtec.com\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"chineseName\": \"柳永祥\",\n" +
                "            \"englishName\": \"Anddy\",\n" +
                "            \"department\": \"產品研發處\",\n" +
                "            \"group\": \"\",\n" +
                "            \"jobTitle\": \"資深協理\",\n" +
                "            \"email\": \"anddyliu@changingtec.com\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"chineseName\": \"莊博凱\",\n" +
                "            \"englishName\": \"Kai\",\n" +
                "            \"department\": \"產品研發處\",\n" +
                "            \"group\": \"產品開發組\",\n" +
                "            \"jobTitle\": \"組長\",\n" +
                "            \"email\": \"kai@changingtec.com\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"chineseName\": \"李一正\",\n" +
                "            \"englishName\": \"Alvin\",\n" +
                "            \"department\": \"產品研發處\",\n" +
                "            \"group\": \"產品開發組\",\n" +
                "            \"jobTitle\": \"\",\n" +
                "            \"email\": \"alvinlee@changingtec.com\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"chineseName\": \"周明邑\",\n" +
                "            \"englishName\": \"Danece\",\n" +
                "            \"department\": \"產品研發處\",\n" +
                "            \"group\": \"產品開發組\",\n" +
                "            \"jobTitle\": \"\",\n" +
                "            \"email\": \"danecechou@changingtec.com\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"chineseName\": \"阮柏森\",\n" +
                "            \"englishName\": \"Robinson\",\n" +
                "            \"department\": \"產品研發處\",\n" +
                "            \"group\": \"產品開發組\",\n" +
                "            \"jobTitle\": \"\",\n" +
                "            \"email\": \"robinsonjuan@changingtec.com\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"chineseName\": \"吳宗霖\",\n" +
                "            \"englishName\": \"TimoWu\",\n" +
                "            \"department\": \"產品研發處\",\n" +
                "            \"group\": \"產品開發組\",\n" +
                "            \"jobTitle\": \"\",\n" +
                "            \"email\": \"timowu@changingtec.com\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"chineseName\": \"劉文森\",\n" +
                "            \"englishName\": \"VincentLiu\",\n" +
                "            \"department\": \"產品研發處\",\n" +
                "            \"group\": \"產品開發組\",\n" +
                "            \"jobTitle\": \"\",\n" +
                "            \"email\": \"vincentliu@changingtec.com\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"chineseName\": \"洪語岑\",\n" +
                "            \"englishName\": \"SunnyHong\",\n" +
                "            \"department\": \"產品研發處\",\n" +
                "            \"group\": \"產品開發組\",\n" +
                "            \"jobTitle\": \"\",\n" +
                "            \"email\": \"sunnyhong@changingtec.com\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"chineseName\": \"黃柏榮\",\n" +
                "            \"englishName\": \"JosephHuang\",\n" +
                "            \"department\": \"產品研發處\",\n" +
                "            \"group\": \"產品開發組\",\n" +
                "            \"jobTitle\": \"\",\n" +
                "            \"email\": \"josephhuang@changingtec.com\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"chineseName\": \"梁煒樂\",\n" +
                "            \"englishName\": \"DerekLeung\",\n" +
                "            \"department\": \"產品研發處\",\n" +
                "            \"group\": \"產品開發組\",\n" +
                "            \"jobTitle\": \"\",\n" +
                "            \"email\": \"derekleung@changingtec.com\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"chineseName\": \"林益聖\",\n" +
                "            \"englishName\": \"Eason\",\n" +
                "            \"department\": \"產品研發處\",\n" +
                "            \"group\": \"應用組\",\n" +
                "            \"jobTitle\": \"\",\n" +
                "            \"email\": \"eason@changingtec.com\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"chineseName\": \"蘇聖益\",\n" +
                "            \"englishName\": \"Jason\",\n" +
                "            \"department\": \"產品研發處\",\n" +
                "            \"group\": \"應用組\",\n" +
                "            \"jobTitle\": \"\",\n" +
                "            \"email\": \"jason@changingtec.com\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"chineseName\": \"劉祐禎\",\n" +
                "            \"englishName\": \"Joe\",\n" +
                "            \"department\": \"產品研發處\",\n" +
                "            \"group\": \"應用組\",\n" +
                "            \"jobTitle\": \"\",\n" +
                "            \"email\": \"joeliu@changingtec.com\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"chineseName\": \"林家緯\",\n" +
                "            \"englishName\": \"IanLin\",\n" +
                "            \"department\": \"產品研發處\",\n" +
                "            \"group\": \"應用組\",\n" +
                "            \"jobTitle\": \"\",\n" +
                "            \"email\": \"ianlin@changingtec.com\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"chineseName\": \"何嘉倫\",\n" +
                "            \"englishName\": \"GlenHo\",\n" +
                "            \"department\": \"產品研發處\",\n" +
                "            \"group\": \"應用組\",\n" +
                "            \"jobTitle\": \"\",\n" +
                "            \"email\": \"glenho@changingtec.com\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"chineseName\": \"柯伊芳\",\n" +
                "            \"englishName\": \"Yvonne\",\n" +
                "            \"department\": \"行銷暨產品業務處\",\n" +
                "            \"group\": \"\",\n" +
                "            \"jobTitle\": \"處長\",\n" +
                "            \"email\": \"yvonneko@changingtec.com\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"chineseName\": \"吳彥儒\",\n" +
                "            \"englishName\": \"Ace\",\n" +
                "            \"department\": \"行銷暨產品業務處\",\n" +
                "            \"group\": \"業務組\",\n" +
                "            \"jobTitle\": \"組長\",\n" +
                "            \"email\": \"acewu@changingtec.com\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"chineseName\": \"湯登傑\",\n" +
                "            \"englishName\": \"Jay\",\n" +
                "            \"department\": \"行銷暨產品業務處\",\n" +
                "            \"group\": \"業務組\",\n" +
                "            \"jobTitle\": \"\",\n" +
                "            \"email\": \"jaytang@changingtec.com\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"chineseName\": \"張俊凱\",\n" +
                "            \"englishName\": \"LinoChang\",\n" +
                "            \"department\": \"行銷暨產品業務處\",\n" +
                "            \"group\": \"業務組\",\n" +
                "            \"jobTitle\": \"\",\n" +
                "            \"email\": \"linochang@changingtec.com\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"chineseName\": \"徐瑞蓮\",\n" +
                "            \"englishName\": \"Sonia\",\n" +
                "            \"department\": \"行銷暨產品業務處\",\n" +
                "            \"group\": \"行銷組\",\n" +
                "            \"jobTitle\": \"\",\n" +
                "            \"email\": \"soniahsu@changingtec.com\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"chineseName\": \"徐歷弘\",\n" +
                "            \"englishName\": \"LiHung\",\n" +
                "            \"department\": \"行銷暨產品業務處\",\n" +
                "            \"group\": \"行銷組\",\n" +
                "            \"jobTitle\": \"\",\n" +
                "            \"email\": \"lihung@changingtec.com\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"chineseName\": \"謝姈諺\",\n" +
                "            \"englishName\": \"Ling\",\n" +
                "            \"department\": \"行銷暨產品業務處\",\n" +
                "            \"group\": \"行銷組\",\n" +
                "            \"jobTitle\": \"\",\n" +
                "            \"email\": \"ling@changingtec.com\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"chineseName\": \"高才珉\",\n" +
                "            \"englishName\": \"Angus\",\n" +
                "            \"department\": \"行銷暨產品業務處\",\n" +
                "            \"group\": \"行銷組\",\n" +
                "            \"jobTitle\": \"\",\n" +
                "            \"email\": \"anguskao@changingtec.com\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"chineseName\": \"古佳書\",\n" +
                "            \"englishName\": \"Henry\",\n" +
                "            \"department\": \"資服部\",\n" +
                "            \"group\": \"系統組\",\n" +
                "            \"jobTitle\": \"\",\n" +
                "            \"email\": \"guw@changingtec.com\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"chineseName\": \"吳淑真\",\n" +
                "            \"englishName\": \"Dana\",\n" +
                "            \"department\": \"資服部\",\n" +
                "            \"group\": \"系統組\",\n" +
                "            \"jobTitle\": \"\",\n" +
                "            \"email\": \"dana@changingtec.com\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"chineseName\": \"卓佳樺\",\n" +
                "            \"englishName\": \"Anita\",\n" +
                "            \"department\": \"資服部\",\n" +
                "            \"group\": \"品保組\",\n" +
                "            \"jobTitle\": \"\",\n" +
                "            \"email\": \"anita@changingtec.com\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"chineseName\": \"李佩姍\",\n" +
                "            \"englishName\": \"Monica\",\n" +
                "            \"department\": \"資服部\",\n" +
                "            \"group\": \"品保組\",\n" +
                "            \"jobTitle\": \"\",\n" +
                "            \"email\": \"monica@changingtec.com\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"chineseName\": \"游凱群\",\n" +
                "            \"englishName\": \"ConnorYou\",\n" +
                "            \"department\": \"資服部\",\n" +
                "            \"group\": \"品保組\",\n" +
                "            \"jobTitle\": \"\",\n" +
                "            \"email\": \"connoryou@changingtec.com\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"chineseName\": \"胡言華\",\n" +
                "            \"englishName\": \"RockHu\",\n" +
                "            \"department\": \"資服部\",\n" +
                "            \"group\": \"品保組\",\n" +
                "            \"jobTitle\": \"\",\n" +
                "            \"email\": \"rockhu@changingtec.com\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"chineseName\": \"黃韵棋\",\n" +
                "            \"englishName\": \"Emma\",\n" +
                "            \"department\": \"財務部\",\n" +
                "            \"group\": \"\",\n" +
                "            \"jobTitle\": \"資深經理\",\n" +
                "            \"email\": \"emma@changingtec.com\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"chineseName\": \"宋宛倫\",\n" +
                "            \"englishName\": \"ElisSung\",\n" +
                "            \"department\": \"財務部\",\n" +
                "            \"group\": \"稽核組\",\n" +
                "            \"jobTitle\": \"\",\n" +
                "            \"email\": \"elissung@changingtec.com\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"chineseName\": \"鐘苑萍\",\n" +
                "            \"englishName\": \"Sandy\",\n" +
                "            \"department\": \"人資暨行政處\",\n" +
                "            \"group\": \"\",\n" +
                "            \"jobTitle\": \"處長\",\n" +
                "            \"email\": \"sandy@changingtec.com\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"chineseName\": \"曾瓊玉\",\n" +
                "            \"englishName\": \"Miya\",\n" +
                "            \"department\": \"人資暨行政處\",\n" +
                "            \"group\": \"人資組\",\n" +
                "            \"jobTitle\": \"\",\n" +
                "            \"email\": \"miya@changingtec.com\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"chineseName\": \"温修毅\",\n" +
                "            \"englishName\": \"MichaelWen\",\n" +
                "            \"department\": \"人資暨行政處\",\n" +
                "            \"group\": \"人資組\",\n" +
                "            \"jobTitle\": \"\",\n" +
                "            \"email\": \"michaelwen@changingtec.com\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"chineseName\": \"林郁舒\",\n" +
                "            \"englishName\": \"SumiLin\",\n" +
                "            \"department\": \"人資暨行政處\",\n" +
                "            \"group\": \"行政組\",\n" +
                "            \"jobTitle\": \"\",\n" +
                "            \"email\": \"sumilin@changingtec.com\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"chineseName\": \"宋順儀\",\n" +
                "            \"englishName\": \"Erin\",\n" +
                "            \"department\": \"總經理室\",\n" +
                "            \"group\": \"\",\n" +
                "            \"jobTitle\": \"執行秘書\",\n" +
                "            \"email\": \"erinsung@changingtec.com\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"chineseName\": \"邱志成\",\n" +
                "            \"englishName\": \"Frank\",\n" +
                "            \"department\": \"總經理室\",\n" +
                "            \"group\": \"\",\n" +
                "            \"jobTitle\": \"資深顧問\",\n" +
                "            \"email\": \"frankchiu@changingtec.com\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"chineseName\": \"廖玉雯\",\n" +
                "            \"englishName\": \"Naomi\",\n" +
                "            \"department\": \"總經理室\",\n" +
                "            \"group\": \"\",\n" +
                "            \"jobTitle\": \"\",\n" +
                "            \"email\": \"naomi@changingtec.com\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"chineseName\": \"陳俊良\",\n" +
                "            \"englishName\": \"Leon\",\n" +
                "            \"department\": \"業務處\",\n" +
                "            \"group\": \"\",\n" +
                "            \"jobTitle\": \"副總經理\",\n" +
                "            \"email\": \"leon@changingtec.com\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"chineseName\": \"秦彥琦\",\n" +
                "            \"englishName\": \"Katrina\",\n" +
                "            \"department\": \"業務處\",\n" +
                "            \"group\": \"金融一組\",\n" +
                "            \"jobTitle\": \"組長\",\n" +
                "            \"email\": \"katrina@changingtec.com\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"chineseName\": \"施于婷\",\n" +
                "            \"englishName\": \"Tina\",\n" +
                "            \"department\": \"業務處\",\n" +
                "            \"group\": \"金融一組\",\n" +
                "            \"jobTitle\": \"\",\n" +
                "            \"email\": \"tinashih@changingtec.com\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"chineseName\": \"郭俊德\",\n" +
                "            \"englishName\": \"Steven\",\n" +
                "            \"department\": \"業務處\",\n" +
                "            \"group\": \"金融二組\",\n" +
                "            \"jobTitle\": \"組長\",\n" +
                "            \"email\": \"steven@changingtec.com\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"chineseName\": \"謝佩倫\",\n" +
                "            \"englishName\": \"Lucy\",\n" +
                "            \"department\": \"業務處\",\n" +
                "            \"group\": \"金融二組\",\n" +
                "            \"jobTitle\": \"\",\n" +
                "            \"email\": \"lucy@changingtec.com\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"chineseName\": \"林昱任\",\n" +
                "            \"englishName\": \"JCLin\",\n" +
                "            \"department\": \"業務處\",\n" +
                "            \"group\": \"金融二組\",\n" +
                "            \"jobTitle\": \"\",\n" +
                "            \"email\": \"jclin@changingtec.com\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"chineseName\": \"許朕輔\",\n" +
                "            \"englishName\": \"Stanley\",\n" +
                "            \"department\": \"業務處\",\n" +
                "            \"group\": \"證券組\",\n" +
                "            \"jobTitle\": \"組長\",\n" +
                "            \"email\": \"stanley@changingtec.com\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"chineseName\": \"曹穎佳\",\n" +
                "            \"englishName\": \"TiffanyTsao\",\n" +
                "            \"department\": \"業務處\",\n" +
                "            \"group\": \"證券組\",\n" +
                "            \"jobTitle\": \"\",\n" +
                "            \"email\": \"tiffanytsao@changingtec.com\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"chineseName\": \"官玉蘭\",\n" +
                "            \"englishName\": \"Iris\",\n" +
                "            \"department\": \"業務處\",\n" +
                "            \"group\": \"政府一組\",\n" +
                "            \"jobTitle\": \"組長\",\n" +
                "            \"email\": \"iris@changingtec.com\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"chineseName\": \"張維真\",\n" +
                "            \"englishName\": \"Kara\",\n" +
                "            \"department\": \"業務處\",\n" +
                "            \"group\": \"政府一組\",\n" +
                "            \"jobTitle\": \"\",\n" +
                "            \"email\": \"karachang@changingtec.com\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"chineseName\": \"翁俊民\",\n" +
                "            \"englishName\": \"Hermann\",\n" +
                "            \"department\": \"業務處\",\n" +
                "            \"group\": \"政府二組\",\n" +
                "            \"jobTitle\": \"組長\",\n" +
                "            \"email\": \"hermann@changingtec.com\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"chineseName\": \"李翊溱\",\n" +
                "            \"englishName\": \"SunyaLee\",\n" +
                "            \"department\": \"業務處\",\n" +
                "            \"group\": \"政府二組\",\n" +
                "            \"jobTitle\": \"\",\n" +
                "            \"email\": \"sunyalee@changingtec.com\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"chineseName\": \"蔡學禹\",\n" +
                "            \"englishName\": \"Jimmy\",\n" +
                "            \"department\": \"業務處\",\n" +
                "            \"group\": \"規劃組\",\n" +
                "            \"jobTitle\": \"\",\n" +
                "            \"email\": \"jimmy@changingtec.com\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"chineseName\": \"陳建志\",\n" +
                "            \"englishName\": \"Frank\",\n" +
                "            \"department\": \"業務處\",\n" +
                "            \"group\": \"規劃組\",\n" +
                "            \"jobTitle\": \"\",\n" +
                "            \"email\": \"frankchen@changingtec.com\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"chineseName\": \"姚怡華\",\n" +
                "            \"englishName\": \"FloraYau\",\n" +
                "            \"department\": \"業務處\",\n" +
                "            \"group\": \"規劃組\",\n" +
                "            \"jobTitle\": \"\",\n" +
                "            \"email\": \"florayau@changingtec.com\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"chineseName\": \"王書政\",\n" +
                "            \"englishName\": \"Sean\",\n" +
                "            \"department\": \"專案處\",\n" +
                "            \"group\": \"\",\n" +
                "            \"jobTitle\": \"處長\",\n" +
                "            \"email\": \"sean@changingtec.com\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"chineseName\": \"蕭賜衡\",\n" +
                "            \"englishName\": \"Hank\",\n" +
                "            \"department\": \"專案處\",\n" +
                "            \"group\": \"專案顧問室\",\n" +
                "            \"jobTitle\": \"\",\n" +
                "            \"email\": \"hank@changingtec.com\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"chineseName\": \"林絹娟\",\n" +
                "            \"englishName\": \"Shirley\",\n" +
                "            \"department\": \"專案處\",\n" +
                "            \"group\": \"一組\",\n" +
                "            \"jobTitle\": \"組長\",\n" +
                "            \"email\": \"shirley@changingtec.com\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"chineseName\": \"鄭涵綾\",\n" +
                "            \"englishName\": \"Lynn\",\n" +
                "            \"department\": \"專案處\",\n" +
                "            \"group\": \"一組\",\n" +
                "            \"jobTitle\": \"\",\n" +
                "            \"email\": \"lynncheng@changingtec.com\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"chineseName\": \"何明建\",\n" +
                "            \"englishName\": \"Martin\",\n" +
                "            \"department\": \"專案處\",\n" +
                "            \"group\": \"一組\",\n" +
                "            \"jobTitle\": \"\",\n" +
                "            \"email\": \"martinho@changingtec.com\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"chineseName\": \"劉耆定\",\n" +
                "            \"englishName\": \"Mark\",\n" +
                "            \"department\": \"專案處\",\n" +
                "            \"group\": \"一組\",\n" +
                "            \"jobTitle\": \"\",\n" +
                "            \"email\": \"markliu@changingtec.com\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"chineseName\": \"李翊德\",\n" +
                "            \"englishName\": \"Eddy\",\n" +
                "            \"department\": \"專案處\",\n" +
                "            \"group\": \"一組\",\n" +
                "            \"jobTitle\": \"\",\n" +
                "            \"email\": \"eddylee@changingtec.com\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"chineseName\": \"宋鎬辰\",\n" +
                "            \"englishName\": \"Hao\",\n" +
                "            \"department\": \"專案處\",\n" +
                "            \"group\": \"一組\",\n" +
                "            \"jobTitle\": \"\",\n" +
                "            \"email\": \"haosung@changingtec.com\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"chineseName\": \"王泰翔\",\n" +
                "            \"englishName\": \"Jerry\",\n" +
                "            \"department\": \"專案處\",\n" +
                "            \"group\": \"二組\",\n" +
                "            \"jobTitle\": \"組長\",\n" +
                "            \"email\": \"jerry@changingtec.com\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"chineseName\": \"林杭昱\",\n" +
                "            \"englishName\": \"Hunter\",\n" +
                "            \"department\": \"專案處\",\n" +
                "            \"group\": \"二組\",\n" +
                "            \"jobTitle\": \"\",\n" +
                "            \"email\": \"hunter@changingtec.com\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"chineseName\": \"陳威秀\",\n" +
                "            \"englishName\": \"Amber\",\n" +
                "            \"department\": \"專案處\",\n" +
                "            \"group\": \"二組\",\n" +
                "            \"jobTitle\": \"\",\n" +
                "            \"email\": \"amber@changingtec.com\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"chineseName\": \"賴季賢\",\n" +
                "            \"englishName\": \"Jeremy\",\n" +
                "            \"department\": \"專案處\",\n" +
                "            \"group\": \"二組\",\n" +
                "            \"jobTitle\": \"\",\n" +
                "            \"email\": \"jeremylai@changingtec.com\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"chineseName\": \"涂筱琪\",\n" +
                "            \"englishName\": \"Linda\",\n" +
                "            \"department\": \"專案處\",\n" +
                "            \"group\": \"二組\",\n" +
                "            \"jobTitle\": \"\",\n" +
                "            \"email\": \"lindatu@changingtec.com\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"chineseName\": \"蘇詩涵\",\n" +
                "            \"englishName\": \"AnnaSu\",\n" +
                "            \"department\": \"專案處\",\n" +
                "            \"group\": \"二組\",\n" +
                "            \"jobTitle\": \"\",\n" +
                "            \"email\": \"annasu@changingtec.com\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"chineseName\": \"李旭騰\",\n" +
                "            \"englishName\": \"DanielLee\",\n" +
                "            \"department\": \"專案處\",\n" +
                "            \"group\": \"二組\",\n" +
                "            \"jobTitle\": \"\",\n" +
                "            \"email\": \"daniellee@changingtec.com\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"chineseName\": \"劉潔軒\",\n" +
                "            \"englishName\": \"Jeffrey\",\n" +
                "            \"department\": \"專案處\",\n" +
                "            \"group\": \"三組\",\n" +
                "            \"jobTitle\": \"副處長\",\n" +
                "            \"email\": \"jeffrey@changingtec.com\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"chineseName\": \"林秀芩\",\n" +
                "            \"englishName\": \"Jean\",\n" +
                "            \"department\": \"專案處\",\n" +
                "            \"group\": \"三組\",\n" +
                "            \"jobTitle\": \"\",\n" +
                "            \"email\": \"qinoo@changingtec.com\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"chineseName\": \"邱瑞霖\",\n" +
                "            \"englishName\": \"Ethan\",\n" +
                "            \"department\": \"專案處\",\n" +
                "            \"group\": \"三組\",\n" +
                "            \"jobTitle\": \"\",\n" +
                "            \"email\": \"ethan@changingtec.com\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"chineseName\": \"藍士閔\",\n" +
                "            \"englishName\": \"Blue\",\n" +
                "            \"department\": \"專案處\",\n" +
                "            \"group\": \"三組\",\n" +
                "            \"jobTitle\": \"\",\n" +
                "            \"email\": \"blue@changingtec.com\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"chineseName\": \"李佳翰\",\n" +
                "            \"englishName\": \"Terry\",\n" +
                "            \"department\": \"專案處\",\n" +
                "            \"group\": \"三組\",\n" +
                "            \"jobTitle\": \"\",\n" +
                "            \"email\": \"terry@changingtec.com\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"chineseName\": \"周逸晨\",\n" +
                "            \"englishName\": \"Juster\",\n" +
                "            \"department\": \"專案處\",\n" +
                "            \"group\": \"三組\",\n" +
                "            \"jobTitle\": \"\",\n" +
                "            \"email\": \"juster@changingtec.com\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"chineseName\": \"盧嘉偉\",\n" +
                "            \"englishName\": \"Leo\",\n" +
                "            \"department\": \"專案處\",\n" +
                "            \"group\": \"三組\",\n" +
                "            \"jobTitle\": \"\",\n" +
                "            \"email\": \"leolu@changingtec.com\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"chineseName\": \"李孟潔\",\n" +
                "            \"englishName\": \"Sharon\",\n" +
                "            \"department\": \"專案處\",\n" +
                "            \"group\": \"三組\",\n" +
                "            \"jobTitle\": \"\",\n" +
                "            \"email\": \"sharonlee@changingtec.com\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"chineseName\": \"周憶萱\",\n" +
                "            \"englishName\": \"Emily\",\n" +
                "            \"department\": \"專案處\",\n" +
                "            \"group\": \"三組\",\n" +
                "            \"jobTitle\": \"\",\n" +
                "            \"email\": \"emilychou@changingtec.com\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"chineseName\": \"王畇蓁\",\n" +
                "            \"englishName\": \"Jen\",\n" +
                "            \"department\": \"專案處\",\n" +
                "            \"group\": \"三組\",\n" +
                "            \"jobTitle\": \"\",\n" +
                "            \"email\": \"jenwang@changingtec.com\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"chineseName\": \"馮韋誠\",\n" +
                "            \"englishName\": \"JeterFeng\",\n" +
                "            \"department\": \"專案處\",\n" +
                "            \"group\": \"三組\",\n" +
                "            \"jobTitle\": \"\",\n" +
                "            \"email\": \"jeterfeng@changingtec.com\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"chineseName\": \"陳譽文\",\n" +
                "            \"englishName\": \"Angel\",\n" +
                "            \"department\": \"專案處\",\n" +
                "            \"group\": \"四組\",\n" +
                "            \"jobTitle\": \"\",\n" +
                "            \"email\": \"angelchen@changingtec.com\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"chineseName\": \"洪敬勇\",\n" +
                "            \"englishName\": \"Square\",\n" +
                "            \"department\": \"專案處\",\n" +
                "            \"group\": \"技術服務組\",\n" +
                "            \"jobTitle\": \"\",\n" +
                "            \"email\": \"square@changingtec.com\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"chineseName\": \"吳庭瑋\",\n" +
                "            \"englishName\": \"Denis\",\n" +
                "            \"department\": \"專案處\",\n" +
                "            \"group\": \"技術服務組\",\n" +
                "            \"jobTitle\": \"\",\n" +
                "            \"email\": \"deniswu@changingtec.com\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"chineseName\": \"陳彥宏\",\n" +
                "            \"englishName\": \"Robin\",\n" +
                "            \"department\": \"專案處\",\n" +
                "            \"group\": \"技術服務組\",\n" +
                "            \"jobTitle\": \"\",\n" +
                "            \"email\": \"robinchen@changingtec.com\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"chineseName\": \"周煥傑\",\n" +
                "            \"englishName\": \"ET\",\n" +
                "            \"department\": \"創新發展處\",\n" +
                "            \"group\": \"一組\",\n" +
                "            \"jobTitle\": \"組長\",\n" +
                "            \"email\": \"etchou@changingtec.com\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"chineseName\": \"陳笙綸\",\n" +
                "            \"englishName\": \"Alan\",\n" +
                "            \"department\": \"創新發展處\",\n" +
                "            \"group\": \"一組\",\n" +
                "            \"jobTitle\": \"\",\n" +
                "            \"email\": \"alanchen@changingtec.com\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"chineseName\": \"洪捷\",\n" +
                "            \"englishName\": \"Jeff\",\n" +
                "            \"department\": \"創新發展處\",\n" +
                "            \"group\": \"一組\",\n" +
                "            \"jobTitle\": \"\",\n" +
                "            \"email\": \"jeffhong@changingtec.com\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"chineseName\": \"劉冠甫\",\n" +
                "            \"englishName\": \"Tofu\",\n" +
                "            \"department\": \"創新發展處\",\n" +
                "            \"group\": \"一組\",\n" +
                "            \"jobTitle\": \"\",\n" +
                "            \"email\": \"tofu113@changingtec.com\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"chineseName\": \"張盛智\",\n" +
                "            \"englishName\": \"Steve\",\n" +
                "            \"department\": \"創新發展處\",\n" +
                "            \"group\": \"一組\",\n" +
                "            \"jobTitle\": \"\",\n" +
                "            \"email\": \"stevechang@changingtec.com\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"chineseName\": \"蘇建祥\",\n" +
                "            \"englishName\": \"Jensen\",\n" +
                "            \"department\": \"創新發展處\",\n" +
                "            \"group\": \"二組\",\n" +
                "            \"jobTitle\": \"\",\n" +
                "            \"email\": \"jensen@changingtec.com\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"chineseName\": \"蔡易霖\",\n" +
                "            \"englishName\": \"AddiTsai\",\n" +
                "            \"department\": \"創新發展處\",\n" +
                "            \"group\": \"二組\",\n" +
                "            \"jobTitle\": \"\",\n" +
                "            \"email\": \"additsai@changingtec.com\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"chineseName\": \"邱世欣\",\n" +
                "            \"englishName\": \"GavinChiu\",\n" +
                "            \"department\": \"創新發展處\",\n" +
                "            \"group\": \"二組\",\n" +
                "            \"jobTitle\": \"\",\n" +
                "            \"email\": \"gavinchiu@changingtec.com\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"chineseName\": \"李建賢\",\n" +
                "            \"englishName\": \"Sam\",\n" +
                "            \"department\": \"創新發展處\",\n" +
                "            \"group\": \"技術顧問室\",\n" +
                "            \"jobTitle\": \"技術顧問\",\n" +
                "            \"email\": \"samlee@changingtec.com\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"chineseName\": \"沈宗享\",\n" +
                "            \"englishName\": \"ZongShen\",\n" +
                "            \"department\": \"創新發展處\",\n" +
                "            \"group\": \"技術顧問室\",\n" +
                "            \"jobTitle\": \"資安顧問\",\n" +
                "            \"email\": \"zongshen@changingtec.com\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"chineseName\": \"鄭素欣\",\n" +
                "            \"englishName\": \"Jessica\",\n" +
                "            \"department\": \"創新發展處\",\n" +
                "            \"group\": \"技術顧問室\",\n" +
                "            \"jobTitle\": \"系統顧問\",\n" +
                "            \"email\": \"jessica@changingtec.com\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"chineseName\": \"翁仕全\",\n" +
                "            \"englishName\": \"James\",\n" +
                "            \"department\": \"專案技術處\",\n" +
                "            \"group\": \"一組\",\n" +
                "            \"jobTitle\": \"經理\",\n" +
                "            \"email\": \"jamesweng@changingtec.com\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"chineseName\": \"楊景淵\",\n" +
                "            \"englishName\": \"Luke\",\n" +
                "            \"department\": \"專案技術處\",\n" +
                "            \"group\": \"一組\",\n" +
                "            \"jobTitle\": \"\",\n" +
                "            \"email\": \"lukeyang@changingtec.com\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"chineseName\": \"劉子揚\",\n" +
                "            \"englishName\": \"Alex\",\n" +
                "            \"department\": \"專案技術處\",\n" +
                "            \"group\": \"一組\",\n" +
                "            \"jobTitle\": \"\",\n" +
                "            \"email\": \"alex@changingtec.com\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"chineseName\": \"周詠順\",\n" +
                "            \"englishName\": \"Chris\",\n" +
                "            \"department\": \"專案技術處\",\n" +
                "            \"group\": \"一組\",\n" +
                "            \"jobTitle\": \"\",\n" +
                "            \"email\": \"chrischou@changingtec.com\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"chineseName\": \"陳季坊\",\n" +
                "            \"englishName\": \"Jill\",\n" +
                "            \"department\": \"專案技術處\",\n" +
                "            \"group\": \"一組\",\n" +
                "            \"jobTitle\": \"\",\n" +
                "            \"email\": \"jillchen@changingtec.com\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"chineseName\": \"吳啟銘\",\n" +
                "            \"englishName\": \"Jason\",\n" +
                "            \"department\": \"專案技術處\",\n" +
                "            \"group\": \"一組\",\n" +
                "            \"jobTitle\": \"\",\n" +
                "            \"email\": \"jasonwu@changingtec.com\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"chineseName\": \"顏嘉佑\",\n" +
                "            \"englishName\": \"ClarkYen\",\n" +
                "            \"department\": \"專案技術處\",\n" +
                "            \"group\": \"一組\",\n" +
                "            \"jobTitle\": \"\",\n" +
                "            \"email\": \"clarkyen@changingtec.com\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"chineseName\": \"白家豪\",\n" +
                "            \"englishName\": \"White\",\n" +
                "            \"department\": \"專案技術處\",\n" +
                "            \"group\": \"二組\",\n" +
                "            \"jobTitle\": \"組長\",\n" +
                "            \"email\": \"white@changingtec.com\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"chineseName\": \"陳俊錡\",\n" +
                "            \"englishName\": \"Charles\",\n" +
                "            \"department\": \"專案技術處\",\n" +
                "            \"group\": \"二組\",\n" +
                "            \"jobTitle\": \"\",\n" +
                "            \"email\": \"charles@changingtec.com\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"chineseName\": \"何函栴\",\n" +
                "            \"englishName\": \"Alma\",\n" +
                "            \"department\": \"專案技術處\",\n" +
                "            \"group\": \"二組\",\n" +
                "            \"jobTitle\": \"\",\n" +
                "            \"email\": \"almaho@changingtec.com\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"chineseName\": \"江宛諭\",\n" +
                "            \"englishName\": \"Jacinda\",\n" +
                "            \"department\": \"專案技術處\",\n" +
                "            \"group\": \"二組\",\n" +
                "            \"jobTitle\": \"\",\n" +
                "            \"email\": \"jacindajiang@changingtec.com\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"chineseName\": \"劉旻妮\",\n" +
                "            \"englishName\": \"MinnieLiu\",\n" +
                "            \"department\": \"專案技術處\",\n" +
                "            \"group\": \"二組\",\n" +
                "            \"jobTitle\": \"\",\n" +
                "            \"email\": \"minnieliu@changingtec.com\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"chineseName\": \"劉家銘\",\n" +
                "            \"englishName\": \"Rex\",\n" +
                "            \"department\": \"資服部\",\n" +
                "            \"group\": \"系統組\",\n" +
                "            \"jobTitle\": \"\",\n" +
                "            \"email\": \"rexliu@changingtec.com\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"chineseName\": \"黃鈺芳\",\n" +
                "            \"englishName\": \"Flora\",\n" +
                "            \"department\": \"財務部\",\n" +
                "            \"group\": \"會計組\",\n" +
                "            \"jobTitle\": \"組長\",\n" +
                "            \"email\": \"flora@changingtec.com\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"chineseName\": \"林晨誼\",\n" +
                "            \"englishName\": \"Doris\",\n" +
                "            \"department\": \"財務部\",\n" +
                "            \"group\": \"會計組\",\n" +
                "            \"jobTitle\": \"\",\n" +
                "            \"email\": \"dorislin@changingtec.com\"\n" +
                "        }\n" +
                "    ]", new TypeReference<List<UserInfo>>() {
        });
        List<UserPassword> userPasswords = new ObjectMapper().readValue("[\n" +
                "        {\n" +
                "            \"userId\": 1,\n" +
                "            \"loginName\": \"ray.yang\",\n" +
                "            \"password\": \"zeo7n\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"userId\": 2,\n" +
                "            \"loginName\": \"whyang\",\n" +
                "            \"password\": \"s4wyX\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"userId\": 3,\n" +
                "            \"loginName\": \"skyhsu\",\n" +
                "            \"password\": \"lIWps\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"userId\": 4,\n" +
                "            \"loginName\": \"timyang\",\n" +
                "            \"password\": \"0XWHM\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"userId\": 5,\n" +
                "            \"loginName\": \"yves\",\n" +
                "            \"password\": \"TSrPI\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"userId\": 6,\n" +
                "            \"loginName\": \"miller\",\n" +
                "            \"password\": \"Zzitw\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"userId\": 7,\n" +
                "            \"loginName\": \"kevinhuang\",\n" +
                "            \"password\": \"3EeNV\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"userId\": 8,\n" +
                "            \"loginName\": \"cgchen\",\n" +
                "            \"password\": \"Gvn3k\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"userId\": 9,\n" +
                "            \"loginName\": \"candy\",\n" +
                "            \"password\": \"1yI3S\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"userId\": 10,\n" +
                "            \"loginName\": \"chiawei\",\n" +
                "            \"password\": \"IcRBp\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"userId\": 11,\n" +
                "            \"loginName\": \"mahone\",\n" +
                "            \"password\": \"QhqPg\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"userId\": 12,\n" +
                "            \"loginName\": \"aaron\",\n" +
                "            \"password\": \"LsdET\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"userId\": 13,\n" +
                "            \"loginName\": \"justinhung\",\n" +
                "            \"password\": \"8LOPc\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"userId\": 14,\n" +
                "            \"loginName\": \"mickfu\",\n" +
                "            \"password\": \"7hefe\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"userId\": 15,\n" +
                "            \"loginName\": \"sunchen\",\n" +
                "            \"password\": \"EBuRU\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"userId\": 16,\n" +
                "            \"loginName\": \"danielwu\",\n" +
                "            \"password\": \"ymKSO\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"userId\": 17,\n" +
                "            \"loginName\": \"rusty\",\n" +
                "            \"password\": \"8VWiP\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"userId\": 18,\n" +
                "            \"loginName\": \"slash\",\n" +
                "            \"password\": \"Mjz6c\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"userId\": 19,\n" +
                "            \"loginName\": \"jacklai\",\n" +
                "            \"password\": \"8161C\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"userId\": 20,\n" +
                "            \"loginName\": \"evanlin\",\n" +
                "            \"password\": \"No4m3\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"userId\": 21,\n" +
                "            \"loginName\": \"ethantu\",\n" +
                "            \"password\": \"iT4F3\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"userId\": 22,\n" +
                "            \"loginName\": \"andrehsieh\",\n" +
                "            \"password\": \"T4J7g\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"userId\": 23,\n" +
                "            \"loginName\": \"neilchen\",\n" +
                "            \"password\": \"kv8pE\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"userId\": 24,\n" +
                "            \"loginName\": \"york\",\n" +
                "            \"password\": \"9DHHO\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"userId\": 25,\n" +
                "            \"loginName\": \"mandychen\",\n" +
                "            \"password\": \"BuZPW\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"userId\": 26,\n" +
                "            \"loginName\": \"doreen\",\n" +
                "            \"password\": \"XEKGZ\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"userId\": 27,\n" +
                "            \"loginName\": \"adam\",\n" +
                "            \"password\": \"NdU7W\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"userId\": 28,\n" +
                "            \"loginName\": \"rossi\",\n" +
                "            \"password\": \"VsRwB\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"userId\": 29,\n" +
                "            \"loginName\": \"louis\",\n" +
                "            \"password\": \"fk3ja\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"userId\": 30,\n" +
                "            \"loginName\": \"ziv\",\n" +
                "            \"password\": \"DZ7UF\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"userId\": 31,\n" +
                "            \"loginName\": \"mark\",\n" +
                "            \"password\": \"D7U1o\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"userId\": 32,\n" +
                "            \"loginName\": \"jessicawu\",\n" +
                "            \"password\": \"Wd6dg\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"userId\": 33,\n" +
                "            \"loginName\": \"ericwang\",\n" +
                "            \"password\": \"8NQzb\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"userId\": 34,\n" +
                "            \"loginName\": \"christylow\",\n" +
                "            \"password\": \"JxDtw\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"userId\": 35,\n" +
                "            \"loginName\": \"eileenli\",\n" +
                "            \"password\": \"A3LG9\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"userId\": 36,\n" +
                "            \"loginName\": \"willychen\",\n" +
                "            \"password\": \"4jqWA\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"userId\": 37,\n" +
                "            \"loginName\": \"martin\",\n" +
                "            \"password\": \"4o4AW\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"userId\": 38,\n" +
                "            \"loginName\": \"leotseng\",\n" +
                "            \"password\": \"R2JaE\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"userId\": 39,\n" +
                "            \"loginName\": \"calvinjheng\",\n" +
                "            \"password\": \"4kS71\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"userId\": 40,\n" +
                "            \"loginName\": \"mike\",\n" +
                "            \"password\": \"sO8Ev\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"userId\": 41,\n" +
                "            \"loginName\": \"roypan\",\n" +
                "            \"password\": \"YVKh8\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"userId\": 42,\n" +
                "            \"loginName\": \"anddyliu\",\n" +
                "            \"password\": \"O3iWR\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"userId\": 43,\n" +
                "            \"loginName\": \"kai\",\n" +
                "            \"password\": \"szDdH\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"userId\": 44,\n" +
                "            \"loginName\": \"alvinlee\",\n" +
                "            \"password\": \"LO3UZ\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"userId\": 45,\n" +
                "            \"loginName\": \"danecechou\",\n" +
                "            \"password\": \"cjpS2\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"userId\": 46,\n" +
                "            \"loginName\": \"robinsonjuan\",\n" +
                "            \"password\": \"6fZtn\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"userId\": 47,\n" +
                "            \"loginName\": \"timowu\",\n" +
                "            \"password\": \"41WWS\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"userId\": 48,\n" +
                "            \"loginName\": \"vincentliu\",\n" +
                "            \"password\": \"bqELj\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"userId\": 49,\n" +
                "            \"loginName\": \"sunnyhong\",\n" +
                "            \"password\": \"iJIGx\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"userId\": 50,\n" +
                "            \"loginName\": \"josephhuang\",\n" +
                "            \"password\": \"gLMJZ\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"userId\": 51,\n" +
                "            \"loginName\": \"derekleung\",\n" +
                "            \"password\": \"KlyEe\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"userId\": 52,\n" +
                "            \"loginName\": \"eason\",\n" +
                "            \"password\": \"GWgHX\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"userId\": 53,\n" +
                "            \"loginName\": \"jason\",\n" +
                "            \"password\": \"HMF3y\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"userId\": 54,\n" +
                "            \"loginName\": \"joeliu\",\n" +
                "            \"password\": \"s1IBs\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"userId\": 55,\n" +
                "            \"loginName\": \"ianlin\",\n" +
                "            \"password\": \"21dqM\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"userId\": 56,\n" +
                "            \"loginName\": \"glenho\",\n" +
                "            \"password\": \"x4Lob\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"userId\": 57,\n" +
                "            \"loginName\": \"yvonneko\",\n" +
                "            \"password\": \"C3Ndt\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"userId\": 58,\n" +
                "            \"loginName\": \"acewu\",\n" +
                "            \"password\": \"DaKlb\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"userId\": 59,\n" +
                "            \"loginName\": \"jaytang\",\n" +
                "            \"password\": \"cHCh5\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"userId\": 60,\n" +
                "            \"loginName\": \"linochang\",\n" +
                "            \"password\": \"H9OMa\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"userId\": 61,\n" +
                "            \"loginName\": \"soniahsu\",\n" +
                "            \"password\": \"SkRc2\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"userId\": 62,\n" +
                "            \"loginName\": \"lihung\",\n" +
                "            \"password\": \"CtuGp\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"userId\": 63,\n" +
                "            \"loginName\": \"ling\",\n" +
                "            \"password\": \"7OqQz\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"userId\": 64,\n" +
                "            \"loginName\": \"anguskao\",\n" +
                "            \"password\": \"peLFq\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"userId\": 65,\n" +
                "            \"loginName\": \"guw\",\n" +
                "            \"password\": \"9gI3s\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"userId\": 66,\n" +
                "            \"loginName\": \"dana\",\n" +
                "            \"password\": \"MZaE9\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"userId\": 67,\n" +
                "            \"loginName\": \"anita\",\n" +
                "            \"password\": \"xNQa8\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"userId\": 68,\n" +
                "            \"loginName\": \"monica\",\n" +
                "            \"password\": \"Ess5t\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"userId\": 69,\n" +
                "            \"loginName\": \"connoryou\",\n" +
                "            \"password\": \"xWHme\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"userId\": 70,\n" +
                "            \"loginName\": \"rockhu\",\n" +
                "            \"password\": \"G3COi\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"userId\": 71,\n" +
                "            \"loginName\": \"emma\",\n" +
                "            \"password\": \"0TUCR\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"userId\": 72,\n" +
                "            \"loginName\": \"elissung\",\n" +
                "            \"password\": \"UooDE\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"userId\": 73,\n" +
                "            \"loginName\": \"sandy\",\n" +
                "            \"password\": \"3MFJG\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"userId\": 74,\n" +
                "            \"loginName\": \"miya\",\n" +
                "            \"password\": \"uKXq0\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"userId\": 75,\n" +
                "            \"loginName\": \"michaelwen\",\n" +
                "            \"password\": \"949vM\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"userId\": 76,\n" +
                "            \"loginName\": \"sumilin\",\n" +
                "            \"password\": \"IvGCZ\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"userId\": 77,\n" +
                "            \"loginName\": \"erinsung\",\n" +
                "            \"password\": \"6E88l\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"userId\": 78,\n" +
                "            \"loginName\": \"frankchiu\",\n" +
                "            \"password\": \"bnZar\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"userId\": 79,\n" +
                "            \"loginName\": \"naomi\",\n" +
                "            \"password\": \"0nMR6\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"userId\": 80,\n" +
                "            \"loginName\": \"leon\",\n" +
                "            \"password\": \"LT6jI\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"userId\": 81,\n" +
                "            \"loginName\": \"katrina\",\n" +
                "            \"password\": \"1Hk3W\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"userId\": 82,\n" +
                "            \"loginName\": \"tinashih\",\n" +
                "            \"password\": \"QAAWq\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"userId\": 83,\n" +
                "            \"loginName\": \"steven\",\n" +
                "            \"password\": \"jrZdy\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"userId\": 84,\n" +
                "            \"loginName\": \"lucy\",\n" +
                "            \"password\": \"x5PgI\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"userId\": 85,\n" +
                "            \"loginName\": \"jclin\",\n" +
                "            \"password\": \"8KMiA\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"userId\": 86,\n" +
                "            \"loginName\": \"stanley\",\n" +
                "            \"password\": \"RTWaZ\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"userId\": 87,\n" +
                "            \"loginName\": \"tiffanytsao\",\n" +
                "            \"password\": \"iUVX1\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"userId\": 88,\n" +
                "            \"loginName\": \"iris\",\n" +
                "            \"password\": \"bfF8h\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"userId\": 89,\n" +
                "            \"loginName\": \"karachang\",\n" +
                "            \"password\": \"orBC1\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"userId\": 90,\n" +
                "            \"loginName\": \"hermann\",\n" +
                "            \"password\": \"eoGhw\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"userId\": 91,\n" +
                "            \"loginName\": \"sunyalee\",\n" +
                "            \"password\": \"RsUfv\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"userId\": 92,\n" +
                "            \"loginName\": \"jimmy\",\n" +
                "            \"password\": \"O3NuX\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"userId\": 93,\n" +
                "            \"loginName\": \"frankchen\",\n" +
                "            \"password\": \"HWIHx\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"userId\": 94,\n" +
                "            \"loginName\": \"florayau\",\n" +
                "            \"password\": \"kV63z\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"userId\": 95,\n" +
                "            \"loginName\": \"sean\",\n" +
                "            \"password\": \"2LbPY\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"userId\": 96,\n" +
                "            \"loginName\": \"hank\",\n" +
                "            \"password\": \"HzBth\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"userId\": 97,\n" +
                "            \"loginName\": \"shirley\",\n" +
                "            \"password\": \"8UnQZ\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"userId\": 98,\n" +
                "            \"loginName\": \"lynncheng\",\n" +
                "            \"password\": \"PpqlJ\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"userId\": 99,\n" +
                "            \"loginName\": \"martinho\",\n" +
                "            \"password\": \"p9MiL\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"userId\": 100,\n" +
                "            \"loginName\": \"markliu\",\n" +
                "            \"password\": \"PbaSo\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"userId\": 101,\n" +
                "            \"loginName\": \"eddylee\",\n" +
                "            \"password\": \"R5Gd5\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"userId\": 102,\n" +
                "            \"loginName\": \"haosung\",\n" +
                "            \"password\": \"Fgsdk\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"userId\": 103,\n" +
                "            \"loginName\": \"jerry\",\n" +
                "            \"password\": \"6UZBf\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"userId\": 104,\n" +
                "            \"loginName\": \"hunter\",\n" +
                "            \"password\": \"6bznZ\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"userId\": 105,\n" +
                "            \"loginName\": \"amber\",\n" +
                "            \"password\": \"cEH4F\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"userId\": 106,\n" +
                "            \"loginName\": \"jeremylai\",\n" +
                "            \"password\": \"fTE1I\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"userId\": 107,\n" +
                "            \"loginName\": \"lindatu\",\n" +
                "            \"password\": \"HDM1h\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"userId\": 108,\n" +
                "            \"loginName\": \"annasu\",\n" +
                "            \"password\": \"HnODP\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"userId\": 109,\n" +
                "            \"loginName\": \"daniellee\",\n" +
                "            \"password\": \"CVKtY\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"userId\": 110,\n" +
                "            \"loginName\": \"jeffrey\",\n" +
                "            \"password\": \"oOLHv\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"userId\": 111,\n" +
                "            \"loginName\": \"qinoo\",\n" +
                "            \"password\": \"KOyvI\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"userId\": 112,\n" +
                "            \"loginName\": \"ethan\",\n" +
                "            \"password\": \"7AInL\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"userId\": 113,\n" +
                "            \"loginName\": \"blue\",\n" +
                "            \"password\": \"Oa760\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"userId\": 114,\n" +
                "            \"loginName\": \"terry\",\n" +
                "            \"password\": \"rmHBf\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"userId\": 115,\n" +
                "            \"loginName\": \"juster\",\n" +
                "            \"password\": \"pwjJf\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"userId\": 116,\n" +
                "            \"loginName\": \"leolu\",\n" +
                "            \"password\": \"3ebFu\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"userId\": 117,\n" +
                "            \"loginName\": \"sharonlee\",\n" +
                "            \"password\": \"XHYXH\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"userId\": 118,\n" +
                "            \"loginName\": \"emilychou\",\n" +
                "            \"password\": \"xiVmF\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"userId\": 119,\n" +
                "            \"loginName\": \"jenwang\",\n" +
                "            \"password\": \"zEV1r\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"userId\": 120,\n" +
                "            \"loginName\": \"jeterfeng\",\n" +
                "            \"password\": \"vxgNe\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"userId\": 121,\n" +
                "            \"loginName\": \"angelchen\",\n" +
                "            \"password\": \"nRpXO\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"userId\": 122,\n" +
                "            \"loginName\": \"square\",\n" +
                "            \"password\": \"xvuTn\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"userId\": 123,\n" +
                "            \"loginName\": \"deniswu\",\n" +
                "            \"password\": \"y70Xe\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"userId\": 124,\n" +
                "            \"loginName\": \"robinchen\",\n" +
                "            \"password\": \"Y5t29\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"userId\": 125,\n" +
                "            \"loginName\": \"etchou\",\n" +
                "            \"password\": \"GSHP6\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"userId\": 126,\n" +
                "            \"loginName\": \"alanchen\",\n" +
                "            \"password\": \"2Fi0m\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"userId\": 127,\n" +
                "            \"loginName\": \"jeffhong\",\n" +
                "            \"password\": \"G5bgq\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"userId\": 128,\n" +
                "            \"loginName\": \"tofu113\",\n" +
                "            \"password\": \"kxqLe\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"userId\": 129,\n" +
                "            \"loginName\": \"stevechang\",\n" +
                "            \"password\": \"JuIDF\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"userId\": 130,\n" +
                "            \"loginName\": \"jensen\",\n" +
                "            \"password\": \"Gwj96\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"userId\": 131,\n" +
                "            \"loginName\": \"additsai\",\n" +
                "            \"password\": \"olEar\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"userId\": 132,\n" +
                "            \"loginName\": \"gavinchiu\",\n" +
                "            \"password\": \"Vg9AH\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"userId\": 133,\n" +
                "            \"loginName\": \"samlee\",\n" +
                "            \"password\": \"ZbKzS\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"userId\": 134,\n" +
                "            \"loginName\": \"zongshen\",\n" +
                "            \"password\": \"XtSts\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"userId\": 135,\n" +
                "            \"loginName\": \"jessica\",\n" +
                "            \"password\": \"Laphx\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"userId\": 136,\n" +
                "            \"loginName\": \"jamesweng\",\n" +
                "            \"password\": \"8bOqq\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"userId\": 137,\n" +
                "            \"loginName\": \"lukeyang\",\n" +
                "            \"password\": \"3fIuA\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"userId\": 138,\n" +
                "            \"loginName\": \"alex\",\n" +
                "            \"password\": \"Z9y5M\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"userId\": 139,\n" +
                "            \"loginName\": \"chrischou\",\n" +
                "            \"password\": \"ATjDj\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"userId\": 140,\n" +
                "            \"loginName\": \"jillchen\",\n" +
                "            \"password\": \"Qlsz7\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"userId\": 141,\n" +
                "            \"loginName\": \"jasonwu\",\n" +
                "            \"password\": \"H05o6\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"userId\": 142,\n" +
                "            \"loginName\": \"clarkyen\",\n" +
                "            \"password\": \"YlePA\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"userId\": 143,\n" +
                "            \"loginName\": \"white\",\n" +
                "            \"password\": \"APlBZ\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"userId\": 144,\n" +
                "            \"loginName\": \"charles\",\n" +
                "            \"password\": \"42YEW\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"userId\": 145,\n" +
                "            \"loginName\": \"almaho\",\n" +
                "            \"password\": \"6zXea\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"userId\": 146,\n" +
                "            \"loginName\": \"jacindajiang\",\n" +
                "            \"password\": \"ucOGm\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"userId\": 147,\n" +
                "            \"loginName\": \"minnieliu\",\n" +
                "            \"password\": \"wt9RG\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"userId\": 148,\n" +
                "            \"loginName\": \"rexliu\",\n" +
                "            \"password\": \"s5QaZ\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"userId\": 149,\n" +
                "            \"loginName\": \"flora\",\n" +
                "            \"password\": \"7a3XX\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"userId\": 150,\n" +
                "            \"loginName\": \"dorislin\",\n" +
                "            \"password\": \"7mWBv\"\n" +
                "        }\n" +
                "    ]", new TypeReference<List<UserPassword>>() {
        });

        userInfos.forEach(x -> {
            String loginName = x.getEmail().replace("@changingtec.com", "");
            try {
                x.setPassword(
                        userPasswords.stream()
                                .filter(userPassword -> userPassword.getLoginName().equals(loginName))
                                .findFirst().orElseThrow(() -> new Exception("Login name not found"))
                                .getPassword());
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        new ObjectMapper().writeValue(new File("C:\\Users\\Ziv\\Desktop\\UserMergeOutput.json"), userInfos);
    }
}
