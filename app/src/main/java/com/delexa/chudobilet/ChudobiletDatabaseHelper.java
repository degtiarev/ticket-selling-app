package com.delexa.chudobilet;

import android.content.Context;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Base64;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class ChudobiletDatabaseHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "chudobilet"; // Имя базы данных
    private static final int DB_VERSION = 1; // Версия базы данных


    ChudobiletDatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        updateMyDatabase(db, 0, DB_VERSION);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        updateMyDatabase(db, oldVersion, newVersion);
    }

    private void updateMyDatabase(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion < 1) {
            createAllTables(db);

            //region photoBase64
            String photoBase64 = "/9j/4AAQSkZJRgABAQAAAQABAAD/2wBDAAYEBQYFBAYGBQYHBwYIChAKCgkJChQODwwQFxQYGBcU\n" +
                    "FhYaHSUfGhsjHBYWICwgIyYnKSopGR8tMC0oMCUoKSj/2wBDAQcHBwoIChMKChMoGhYaKCgoKCgo\n" +
                    "KCgoKCgoKCgoKCgoKCgoKCgoKCgoKCgoKCgoKCgoKCgoKCgoKCgoKCgoKCj/wAARCAJAAwADASIA\n" +
                    "AhEBAxEB/8QAHAABAQADAQEBAQAAAAAAAAAAAAECAwQFBgcI/8QAOxAAAgEDAgQEBQIFBAEFAQEA\n" +
                    "AAECAxEhBDEFEkFREyJhcQYyUoGRQqEVIzNisRQWksFyJEPR8PFTgv/EABkBAQEBAQEBAAAAAAAA\n" +
                    "AAAAAAABAgMEBf/EACIRAQEBAQACAgMBAAMAAAAAAAABEQIhMQNBEhNRIgQyYf/aAAwDAQACEQMR\n" +
                    "AD8A/moAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA\n" +
                    "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA\n" +
                    "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA\n" +
                    "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA\n" +
                    "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA\n" +
                    "AAAAAAAAAAAGSpyaVkXw5/SBgDPw5/SPDl2LgwBn4cvpHhy7DBgDPwp9h4U+wwYAz8KfYeFP6QMA\n" +
                    "Z+FLsPCn0iQYAz8Kf0jwp/SBgDPwp9h4U+wGAM/Cn2HhT7AYAz8Kf0jwp9gMAZ+FP6R4U/pKMAbF\n" +
                    "Sn9I8Kf0kGsGzwZ/SPBn9IwawbFQqP8ASx4E/pKNYNng1PpL4E/pGUagbPBn2J4U+xMGAM/Cn2Hh\n" +
                    "T7FwYAz8KfYeFP6SYMAbPCn9I8GfYYNYM/Cn2L4M/pYGsGzwp/SPCn9IwawbPBn9I8Kb6AawbPCn\n" +
                    "2Hgz+ko1gzdGf0jwproBgDZ4U/pJ4U29hgwBn4U/pHhT+kDAGbpTv8pfCn9IGsGzwZr9I8Gf0kwa\n" +
                    "wbPBnf5R4M/pA1g2OjPrEeBP6QNYNngzv8pfAqfSBqBt8Ca/SPAqfSwNQNngz+keDNfpA1g2+BU+\n" +
                    "kngzv8rKNYNngT+keDP6SYNYNng1H+keBU+ko1g2eDP6R4NT6QNYNngz+ljwZ/SMGsGxUan0jwan\n" +
                    "0kGsGzwZ9h4M/pA1g2eDP6R4M/pA1gz8Kf0jwp/SXBgDZ4U+zJ4U/pAwBs8Kf0k8Kf0gYAz8Kd/l\n" +
                    "HhT+kDAGfhTv8o8Kd/lAwBn4U1+keHLsMGAM/Dl9JPDl2JlHRD5I+xkYw+RexkdAA2HsUPsUfYhB\n" +
                    "ShIAQoyAI9yodcgC3uOoBAFuyIX/AAUTqULpgPoEQf4BUBQOoADb3AsA9rlRPUoFRXexEV7MDGxS\n" +
                    "bl6WAO/2MH9jKUkllmDlf2AW7lTCzuCikRbBED2LYgAAD/IFH3JgAPcW/BSEAAAAPRgoAACFQBAK\n" +
                    "QJAUfYov2KAdircWuQS1hazLv6F2XqBPwVbCwsAt3YvcblIMb9LEfcy/+oNYVyjEt79iPYttgFse\n" +
                    "hgt8me33MFe4GaD39S9SAPyT/ovoTr3AD9x9x/kBkPfAW4e4GI6bFf2IUPsR7F6BgYopFuZe+AAH\n" +
                    "UEAEQ/JQuEUAQMrIBA1jIDAmzMXszL0wR7MDGH9OPsZGNP5I+xlYRQW/AAFQt6EXsUIFIisKdAF3\n" +
                    "KBEC5sQAXIDIH+AAUAwAlASxUBQOoABbAAC47E/Bf3ApXt0IsdiSdwDl2MXNtMjdtjEoAADNF6hZ\n" +
                    "QIIVeg6BEAvcmxfYoXH+AAFrh+oDAL9yF+wdgJ7ixRmwAhWO21gIgNwA2BXi2wAdcB2b7Bb9S9bd\n" +
                    "QCt1LbsRZLs7WILbI69w9wvQCve467h+yFsbARvsQytfFh7gR9hv0HUP3sAZTEoEY6FDAjeDFbmT\n" +
                    "2MVuWDMMACApABCggLcMLcMDHrsB1BRAykYGK3MiFQAoAVAARAAFAMEAWIyggn7GL6mXtYxZYMYf\n" +
                    "JH2MiQ/px9ihVIAUVbgCxARSIyAhfYnQAUAZAAAghegBQAKEQIMICgFAAjKgoAAgzF4RkYy2AwAB\n" +
                    "VAAEbFsihDqRUCsUBE9ijqCAikRSgQAACkAAAAAEFABcIAAgdS2IVbAVFuiJX2LboBUEx1AFeNyL\n" +
                    "YWuOoF3RLiwAArJcAQoexBGAXoUYTdsmHP6FqK6NbwWDaqq6plU4vqaAXB0WHoaYya6m2MubcgrI\n" +
                    "AQZRZGVEYGIsAAIy4IyiIpijLoAAAAAAQoCAEKQgAP0BROmEYsyZi1h+wEh/Tj7FJD5I+xQqghSg\n" +
                    "gwCCr1GAigQoABgAgAFAgKCgACCdS5MSooy6AhQAAAAvYjAhjIyMZbiIhCshVAAEbOiAjsX2IAAu\n" +
                    "A3BRm4BgMMARl90LEC3cEe4tYoAFABIdS9MAY2tgFBBPYAFAqYKQEZJLcLuEBU0th64HoXoBAUEA\n" +
                    "hc2HuBBYyMeoE9x7CSyRsCWKW+DCT+xRhUecEWxFeT9A30KMehQSxVUyjsYmcSIziymK3wZEFRGy\n" +
                    "oj3yBiAABGV7EYGK3M+hityooApADADAFRCgGQpAiAWBFQxe3UyezMej9iiQ+SOehTGHyR9jL2Cr\n" +
                    "utwiP2HQC3sgEUAgEUgBgFBblCAAAEAAAAAVEYQC9QKEUBQAAAwAIYz3RmzGWwRgAUogAAziZxNS\n" +
                    "M4sDY4dUY9bdTZFlaT3RBrLgSg1tkiAdQAA/JChAGrLcx3L1yAIX3AAdQAAIUhA+wBQC9Cr7AoBe\n" +
                    "iKiBAZJF3IrlAtrBIWBAexDJ+xFsBOuwKYt23AjRHZLNiOV9l92YKLbu8lGTeMGtvmdlsZvGGY9c\n" +
                    "FVcJYNb3LJkKBChZAsUZolioiMoor3wWKRHuSix9SP8AcJBgY+w/YD/JQIzLFt7GLIItyoxRktwK\n" +
                    "CFKBBkEApCoAQpAIAwUHsYO5mzF7MDGn8kfYpIfJH2KFUE2KAKRACopCgPcAAAUAAAEAQoUAAECB\n" +
                    "QKAQIoAApY2uYljYAzB5RtZgwNYLJWfoQohUAFCrBAEbIvBmpGlMyTIN9/yMPc1qRkn2IDjnD/Ji\n" +
                    "1bczRegGslrbbmxxj2MXDOBoxf2YYs10uRp+pQ/JV+xEwBX+wIABbXyiF9iAkXBFgXAuGVWMUW6A\n" +
                    "pkkY7pFTsgMluZYvkx2F/S7AyaBIpvaJl4b6tL2IMf8ABG+iz7GxRit0VtJYQHPOTjtH8kUJTeTO\n" +
                    "cv5sWZORQUIx3yYzkksElO/uapywBjKTMQCqME6lKglcytgsVZF9iaCMoK7LGFzNrlWSCSxgwZlf\n" +
                    "vsS3ZgVEeehkr9iSAw6lF89BgAl+DGRk3cxYGK3M0YoyQAAjKKAAAJ0C3IKQpLblEAABmLyjJ7GL\n" +
                    "2Axh8kfYyMYfJH2MgqPARQAReoQAIMIoEKgigTqUEIKAAAAKAACDZOpWQDLYn+R1F/UAUnQt8gCp\n" +
                    "9yF2Azh2ZJJJkj6m2PmRBoksGtm+cbM1zWLlGBACiggAIqYIBsTKma0zJMitikZXNVzJN9BiNlxc\n" +
                    "xuEyDIXJcAV2ZOVPoLgByonIn1KjIDHw/UKn6mayiXAx8Pux4fqZXF/UCeGu5VBC4uBfDRVGKxYx\n" +
                    "5nfBW87EGS9kZXsjUmXmA2cwk7o1cwciiuRHLBg5GLllgSrK7RXN2Nc3dmLYVk5EbuQhQCBko39C\n" +
                    "iRV2ZxRlFYKk2RGKVzbThdmdOndmyVor1JowdomuTuyu/uY2AjAAFRH1LHYjAxXuACh0IykYERkY\n" +
                    "oyAB7AAACEFIUe4EAYAgAKI9jFmTI+oGMPkj7FJB/wAuOehfYKoIUAVEKgBUQoACwAFAIAAAACxU\n" +
                    "AAgoQrI9wKtijoNgh7gdxYC2ygl3J7F7dwMl07GSupIxRnHZko2NKav1NMo26G+mko4MpQUiSrjg\n" +
                    "nBrYwOuULXuaJx7I1qNbBWrEKoAAAuAEW5kmYC5BsuU1plTCs7luY3FwMr9yp2wYAiNilYvMa7mV\n" +
                    "32GDNMjZLkvYDK7sEzG4uBm3ZEcuxg2S4GfOOZvPQwvgl/UqtjZG7owuS+AjPm6EcjByMWwrJyI2\n" +
                    "QAS4ACALGLlsbIwt7hWMY9TYkZKPsbYU23sEa4wudEKaW5moKJjKfREoSkliKyappJ+bc38nLG6z\n" +
                    "c522rmd1rMRpPKaJhfq/AspejDi4vK36lRHK/S6LFRtdrHRdydckbu7lRle929zBlRGBO5ACgRlI\n" +
                    "wCKRFIADBRPYpAEOo7ABR+5LepepOoBsm5SAGYv0KyPr7ASn8i9jIxp/JH2KFEUgKKikRSAikKQC\n" +
                    "kAFIGCigAAUmxQBCk+4EYMmQIqDCKFAEAgiohQLEzirv0MVk3Rwv+yVY2U7Xu9lsSpK0sFhhXa6G\n" +
                    "qT81zDcjYpRmrMwnRxjKMG+huouU8F1mxxzh6Gtpo9KVNP3NNSi1fFzUrLhBtlSa2NbTW6KIACqB\n" +
                    "AAMDFggBRcgCLcqfoYpluQZXKYu1ggMkwmS4AtxcxuLjFW4uY3Fxgt/Ql+xAAbBABSAFQA3Mo029\n" +
                    "8EVisszjC+5sjCyVkbadNt3tkmowjHHY2wpt7I6KdDqzalGKBrVCljKM5NQ2/YyTbtZY7m6VOCgp\n" +
                    "QX/kZvUjU5325JRb+bbsiuKt0SsZSjy3RE7Jd+pi3WsSleUJxayjnms+p2U1yzduq2NFWLytuwl8\n" +
                    "l9OW5U2vVdg8boxecs6MDfoRAFRSMIP1AxAC9SgRlJICIyMUZIAAABCgioAUIhGUjKIAygR7GL2w\n" +
                    "Vkaw/YCU/kj7GRjD+nH2MgoARlFKRBkFKRC4BlQvgAUEKAAAFICkRAC3KIA9yMgyQQQKKPYe4AFI\n" +
                    "UKyRug+WC9TRE2yWF7Eo6Uk9O+9zlliWTdD+mjVUWcHP7bnprT+ZmzTz5byZos8o2QxCzNfSOlVH\n" +
                    "KyfRXx1M3eyvuzChHzq2X/g6atNJ+TZq7bM7jX465akPQ0yimz6rhFGjqKDhVgn6mWr+HITvLTz5\n" +
                    "W+jJPknqr+m5sfHSo9jVKDR7+p4VqtPfmpOUe8cnBOlZ5Ti/U6zr+Od5s9vNtYHZOjdmqVAuo0A2\n" +
                    "OkzFwksWGjEnUtmuhCignUoAAALsE6gCkuAAAABhAqjJ9CCAzVJ9TOFJdrjRqSb2RlGm2dUKM3tE\n" +
                    "3R0rfzMDkjTUTdClKe0WdkaVOC2uzfQo1a8uXT05SfoiE2+nLHTKNud5N9Knd8tODb9EfScN+FK9\n" +
                    "a0tZJU478q3PbhwrTaGyowXqzn18vPPp24/4/XXt8bHh1VQUqy5F26ipSp0uWpSSa+WSZ7nG2ot2\n" +
                    "lsfOxm7yxfBznyXpvr45x4jRKLpzkrLe5to1FCVpK8dpL0ZhZzbtkWThJW81i6xjW/LNwksXwY8l\n" +
                    "pNFrJvlklugnzcrt6MrLO2217GmSbd7XsdNk742NTso1MX6Arz5pc7XUwatuba8eWXbqanudY50I\n" +
                    "GCoEZUGBiAUohCkewBFIigAAwBCke+AAAIICsjZRGAAIzF7GTI9mAp/04+wJT+SPsZBUBQA2K/2I\n" +
                    "i/gCIyMSruBfwA8bj1AFIwBQABQQAUABEZCk6kGSZSIAAClAIDqBnHdGxvc1RNl8slVuovmVrbGd\n" +
                    "WKsml7mqjibSOhpuDXQ51qOCrHlqYymWKwzKS81nlr9zOMW5RSLq426RrmyuZJbHTVrRcbqPL0SO\n" +
                    "OLlTm7I6fDcqbc3+DF/rpy9n4fqeecfXofS023bofJcClbUteh9ZQZy69vT8XpqnWSk6Vayn0fc4\n" +
                    "Ndpqc4vxIJ4w0jr4jRnyNypqrS3upWlD2Pn6utnSvHTVKlb+2UDUnjwnWT24aumg21BNSRoenT2m\n" +
                    "jshCvxCparHw0uiVj0uG/DNDUztUruCf6ux053+vP1xvmR8/LSVUvkuvQ0yoTTzCX4ProcBlT4lD\n" +
                    "Sw11OnSnFyVSrhK3Q82pUdHUShNxkouyaWGbv5Sa55NeBKC6qxj4cWfSQqUpPz0oy+x00tJw+t/U\n" +
                    "oqPqmY/Z/Wv1b6fISor0MXRPuH8OcPqq9KpOLfZmEvhGm8w1TXo0X9nK/o7fEeDnYeA+h9k/g+v/\n" +
                    "AO3qYP3Rrl8I69fJOk/uX9nP9T9Pf8fI/wCnfQj077H1UvhTicdowf8A/o0y+G+Jp28GL9mX85/W\n" +
                    "f1d/x83/AKeX2KtNKx9E/h7iiWdM37ML4f4pbGlf5H5T+n6+v4+eWmZnHTY2Pe/gHElvRS+5jPg2\n" +
                    "sp/Oor7k/Of0/X1/HkR06M/ASPRXDaifmkkzL/QQj81T8D84n4V50aUF0Ni5Vsju/wBLRjNpeZpZ\n" +
                    "Ot8Oqw0C1kKC8Dm5VJ9zU3r1EvOe3lwVSo7Rg37I79JwfV6mSwoR7yZ61LSarQwjWqae9OXVI7qW\n" +
                    "up1EoVIcjfdbnPrvqfTv8Xwzr3WOg+FdNFqWqqOo1mywj6TS6WjQioUaUYR9Di0UXH5W2vU9ijBO\n" +
                    "17XPP13b7e7j4eefRyJRPP1sbRbVvc9HUOytujzddmk2/sc3XHyPGptzaPAbtVcYvC3PV4vOXNaO\n" +
                    "7PHfldo/ds7cPD8vtt00WpX7LJbK03+DChL5nfFi3fgs6OLXNt0Y+7JRS5knfcjb5IxNunj5ljJW\n" +
                    "GySupYWDVBZlfCsb545urNe0PVkV5+s+aOb4OZs6dZip6HMdufTlUewDIVFIyojAiAFigRlBBEUi\n" +
                    "KAAAEKRhBQABBkKQAQpHcoMxezKydGBIf04+xSU/kj7FCqACgUiKQQqIZIAh0AAoIy9AAACAAChQ\n" +
                    "CCEKydSoyQCyUAAAAAAyiZvdexgjPoiKt7Xa3OunK9NSX3OJm7T1OV8svlZmxZWVSnltD5V7G75W\n" +
                    "4vrszGcXbKyjDUSrZtOOPQtOUl1umapJ/kU7qyH01K9jh8lDUxaSzg+r09sHx+m5nU55PY+t0Mr0\n" +
                    "oPGTj09Xw36bNX4cqd68vIv07XOGNetPy6DR2j9UlZHqQpRnK81zdvQ1a7UVYpUtJC83hyeyNcun\n" +
                    "c+3j14a6lWjPUzoqHa9mdvDaqo6jzNeHPZ3wadRw2hSp+Nrpzr15fLBPdnIo19NGTrUY09P0bd2j\n" +
                    "U8OW/T6Piegp6/TWT/mR+VnyGs0up083GdKTt6HqabiNWlmjPng+50z4rKcbTpJs9HPczHm7+G27\n" +
                    "HztDTV6s78rhHuzr8OVFZmnY6a+rlO9lGKOCtOMrucr/AHOXdjfHx9T27NNq5J+WSSXc9PT6ltcy\n" +
                    "lc+eoU4yd749D2uGunGLg1ucXpnh69Cs7rLOynPmflaPOprllyp3XRnVSw79TFduenoSdkr5OepL\n" +
                    "O5nG7tmxthpVVyjLftqVWyXU1VdS+Wyvf0O6Gjs83ViS00W2owXq2It8R4erqVHHyO7fU8bVSq7S\n" +
                    "Tz+pn0PGtPHwIQoS5ZLLPmtU6/L4cp3V7mmfxnXtjS07lmTMdRRdKSko+R742MqEp0v7l6no0NVS\n" +
                    "a5asbej2O3x2a8vy/Hc8PBnKhKrzuDbR9PwatquI6TTcPjRjHRUqniydsyZu0um4RJqdRRuetT4p\n" +
                    "otLBU9HRcn2irI9U+SSeHknw92+nocTrRhoVTqKMacdsbs8SlpKdRRdSkm5ZV+xt8aWtn4urxBO0\n" +
                    "aa6HXQpeZv8AT09DxfJdr6nx8Tjnz7KVCNFYwuiOqMlyepqvd+gSWcP7HOrPKzaseXxKX8lq531G\n" +
                    "1DDx6nicSrYspfgy1XynEm/Fll4weRUSvZO6PU4km2+jZ5sIXdt7Hbh4Pk9tlOHLTS6yNs42Vl0W\n" +
                    "5KCc6l/0xGon5XFdXsbcnO06k1y7I6tOrYMKFPEm92bMQxm5WWNR4v6km0lBbYJe6wt9jGTbmQ15\n" +
                    "+sl/N3OY26mV6rNPU7c+nKqQAqKiMpAIAgUAy3IwIikRSA9wQpRAykCgAIgzFsyZOoDoToUFEMWs\n" +
                    "MyaMejKJT+SPsUlP5I+xWRQAICrYpCgCmPUoBFCAApCgPcAAUgAFBAgKY9TIxe+AKjIiARSFAApA\n" +
                    "BlG5l0MY7mRBJbhEY6hXVSmpx5Zbo2JzSabx6nHFu975NvivlV7WMWNStrd8LKJFvZX9zFSbyrW7\n" +
                    "GyE03tYzjUdtGVqNr2ufScIquWmjm7R81Tqc1klhI9rg81G8V7nKvR8dyvpKG13YVklFzb26IxoN\n" +
                    "NXjs9zcoqSs7YdyTw9Vnhzaell1aiTqP9l2R53EdM9frYU7/AMmmryXd9j1ppp2b/Bz0Iqi6k3u7\n" +
                    "s6a42PCqaaOo4hKmrwoUlby9WcWphOPEVQhOSja59PpdMlSlKSs5O7OLV6NfxOlUW3KRM8Pn9Rpq\n" +
                    "sdV4bm+RrByeDKULNvyysz6fU6dKcJ2vZ2NUdDatNcqcZWY1Ly46VBpJK9jrowdOavsz0IUFGG2S\n" +
                    "eBZ3YxqTW6lOV02enB81I8mL89rYPT07/l2xYw7SY2Ual3bf3Pa0SioOS/8A0+eptxm7dD2eHzfI\n" +
                    "3KyfQxZ5dZNjsnNSd7YOWtWlFy5cRZlWk7epzynzLpckavNryuKynPMVZ2PBrqTeVnqfWV6DnG9r\n" +
                    "vscOo0KlFtxSfqaY/Gx87TTbs07m+NN7vJ2T0vK9jZQoNtJK7HpHPS08pLCVzv0tCacXLp2OmhQa\n" +
                    "w4s9GjpsK6sNrTCnRU0kouNuvc6pS5IcpsVoRSikc1aV5N3IxbqQknJJdTp5ZJehzUI80src7VBR\n" +
                    "WH9mK1zHn6x2Tw/U8HWQk5OSR9LXu74PG1r5OZ8raXoZjfc8PjeIc8qzXLfuaqWlv+qKN+pqXqTd\n" +
                    "2lfODTPUclJqn92ztz6fN79rXnS08OVO8+nuctKHM0nlt5bNKcW3UqP2Rsp1L+baPY3HJ21FGK5b\n" +
                    "pJYucVRubai7+onVdSN+jeEZwSim77IIwT5W1vYxc1CE5S3tgO+WcmsmlaKd0aiVy1XeTMHsNwzr\n" +
                    "HKgHQXKAYDAx/BQAD9CMpCAikKAAAVAUhQDACIGGQAAOoB2J0ZX7E6Moxh8kfYpjT+SPsZEUAHsB\n" +
                    "UUhQIyrYnUoFBCgB0AAAFAFIAKQAgEZWTqUZLYERWABSbhFGP/0ADJFMVvuZdMkEYEtyFFWxnBrZ\n" +
                    "7M1pNib6Iitzjyvy5Rsi1JJS39Tnp1HFq/4OmM4tJrL9TFjUrdTbi7dj1OG11GtG7smeK5TvdHTo\n" +
                    "ZOVW0d1mxzsdeesfd6WadrnXCVpLseXoKnNSi7pnfCSfujk9/F2OiS5sI56kMO5ujLqzFyTlg1KX\n" +
                    "lpUrLHQwmlKopO/MlY3uKy7WNacXLFy6z+LHwVP5lg206EW8rKMZNL1v0M72s23jBdS8pUSXRJnP\n" +
                    "V9Hk6a2Xk0STvkza1zGuks3sdlOaUcxTNEbLGLh8zs77Earav6mLbns8P81Nq12jxINyauj29Bbk\n" +
                    "st7Errz6Z178rX+DljaN77+x21L52OaUV1VzLpL4ZQbv/wDJvdLy+aKd+pphi2EzojOT6L2RWenH\n" +
                    "W0ikn/8AbHHPTODTWLHrObbaw0jVNxv0DLjpTkrN3OqGo72bNMlzSlboYxpXeH7gxsda/wAubmUY\n" +
                    "ubTd3Yzp0U4pu3KdEaUYqL6bbhZIyow5U77FqSwjJ83Lj8M0VGvMmzNWRrqz6s8nis2tNVa7bnp1\n" +
                    "WnfJ4/GJ8ujn0b7GWe74fH6i/mu8s4G26bXWO53VuVQV/mb3Zw1I3k2pWZ25fN7asO13klSp5Hb2\n" +
                    "Eni8tzXHmlJNLC2Oscq7dJS5ks2SWTOa3SVkuoprlp46nPWrxhJpPC39R9nphqKnhrle7yefKTbb\n" +
                    "MqtXxJNu+TWmms3OnPLnbqXJkA0mgvgdQEAAUQoBFACMAUiKBB0KQAAAgAAIGCdSigjHQAyPZlZi\n" +
                    "9mBjT+SPsZGMPkj7GQVQQFBGRiUB7lRCoge5SFAAhQBSACgBAAAAI9ykYFRSIoAAMAAEEVGSMUZA\n" +
                    "RkKyAUxkUkgrFOzNsZYNS3M+gG2NSyyjr4ZVVPWU3+luzPPM4TcJpp7MzeWpX32kdnZbI9GnLHc8\n" +
                    "jh9VVaNOae6TZ6dNppWPL1Hv+K7HXiSRsisdEaKZst+CR3ZTjFqxonTivfozcn6mFSSSvb2NDW8J\n" +
                    "3/wY8+P7U8MSlaOUaJ1ErpuxNR2c8X9zGUXlnnS1kafW6OujW56bv17gYOLhJtyVmRVFLD6GVV3S\n" +
                    "ZrbTlhWI1HXQldq2WevoptL/ALPL0yyv8Hr6OFo3t9kG43OV1ncwUbtWW5m8vBFPkf8A9wZraxos\n" +
                    "2xi4N9zRW1DisM1x1qalnJGmc5SSSTSvnCNU3vfP2sJ1lKSaZJSTT3NaxjGPmauvsb1G3LzGFCMX\n" +
                    "K7WVsdkIp5s/RbjRYeaPczXlST27Fikle1u5Lq2CWhNpxzt6HJVinNNYSN95Wefwaal3vuZ1fTnr\n" +
                    "YTyeHxvmlRjSj+p2ParNXsmebq0r897OObljz/Jtj4jisuSq6ax4asedCfLJZbXU36xyr6mpPLTl\n" +
                    "1NapXWDvzMj5/Xmt7iqu25hCDjUta72uWHkjl3SJX1EYxVvyVmtmoqRpR5b80/8AB5dRxk3eTv2L\n" +
                    "UruV7yv9jTJ8yzY6TlztH6bEZEDaAewIVFQZAFXoQAgFRABbkAZQBEUAACIAAqhGUgRAAAAAAxZQ\n" +
                    "9mUYQ+SPsZGMPkj7GRFGAQC3AHsUUe4AGRPcXKQAAAKQAUIAAAABCkAqKRFCAAAe4AAplcxRUFRg\n" +
                    "MBAj2KRgYoyWxijJBWRP8l6EIPqfhuupaVwbzF2Po6DXKfEfD9dU9XyN4mfYUJYOHyTy9fw9eMdy\n" +
                    "a3ubFLHc0QldJGV7Pc5PXzW7HV4MKrX6Qnh5RhJ/ga3Giq31OPUVEt2dVe+bnl62T2RNL4c6cq1e\n" +
                    "MY5s8ntU5WXKefw2jy80pfMztSfN3NRnPt0xTnjoY8j5rG7TW5nc31qfK01sw3E018M9TTTcY8t3\n" +
                    "nLPPpXxY9TRUouLlJXv2I7Tnw3uPk2tc0y8sbrJ0SaSUVhL1PO1lRq6VwXw1VdReTjc49ZU5eWpD\n" +
                    "2ZthTbdyamlem13JVy4wo11K1jrpVeZ262PDjzU5creD0NNUX2M+md16tFvlX7s7KWFh7fuefQnz\n" +
                    "bvJ3QeEB0cztZ3t6GMmRt22MG77slprKUlY56rz7mdR2jfc5qjbTeCMdVqeZPODxeP6v/S6GpKPz\n" +
                    "S8quexOygfI/GVb+RTXRywdOZrzfL1nNfOeJltsxUm83wcrniyNcqmUr7HpnL5+u3xI8l1JJnLWm\n" +
                    "5O62NUpX2JzY7rqanLNujbeehgnZlIaRkCDqBWQAIAMgVR9gR+gFFyAqVQyAIFIihQe5AAYAAEZS\n" +
                    "AOoAAEA6ACPZlI9ngDGHyR9jIxh8kfYyCoUACFAAPYq2IUoqKYlIKCXKAAAAXAAoAAE6lMXuBkCL\n" +
                    "YoApAggAAKi4MUZAGQpAKYsoYGKMjFGQUKQIDbp6jpVoTXRn3OjqKpTjJfqV8HwSx7n1fAK0p6NR\n" +
                    "d+aL3Zy+SeHX4usr6CnJ26XN6as+pyU2m0+p0LzPCszz17ua2qziiYe6MbdL2K7ctiOsaaqWTzq8\n" +
                    "OaV7YO+pHmZqcM2EiW6w0ivDY6o8smk73NcY2XRmV4qO1n6Gllzw3w8s3bdG+U7pXsu5x892r7mc\n" +
                    "H6XXcOnPl30MtWR7VKy07UUk2eJQbx0PR0te2JGNeiRumrpKz9TQ6HNJucuVI2yqeX13OTU1Ksou\n" +
                    "NNpN9QldK8GOIvJzanlfNymilp5N81WtK/7HTKCUEkWwt8PJq0lL0ZKEeV2OqvDk6GqErys7JEcb\n" +
                    "4rtoJxs7JnfSd0cmk3Vs2O5bb/Yyukm2tzFq1s+ofcwnlbkS1jN3STNMsu3RbmydrGio1FtmpHLq\n" +
                    "tGsqNU2lu8I+M+N58n+mo4uldn10r1K+b2ifB/GWo8XirjfEFY68T/UjzfNf814TljsY3yGQ9LwI\n" +
                    "VAFBmPUpM3wUZIERSIAnuAoUgAAMmxRSBFCUGRcgQKRFCwAAUG5AwKQAIEAApLWDIBSPYB7Moxp/\n" +
                    "04+xSU/6cfYpFUBAoAAAUhUAABBQQpRQRFIHQLYn2KBSAoGLBWR+wGSBFsAKGQoAAERSmNylBgMg\n" +
                    "FJIpHsBijNPBijIKGyjTlVnaK932JQpSrVVCCvc9NxjQh4dNbbvuS3CNCpUqN7eeXc9XgVVupNPY\n" +
                    "8l5lk9Dg8lHVpd0cursdOZlfS0cNxe3Q6oO9sHHH5VLqjopy2w7M5V7Oa6k/uSbS3w/QiZKku6Mu\n" +
                    "0aqku+5itzZjmu8mM3H2LE1FJWeDB5VpbGdk02iKNum5V1jdPyv7Gynf1wYuFmdWnhzW5lsZrXN8\n" +
                    "ttObSvyuxY62KwZVFala55jTpz6WJjp+yx7FPUKS29zbKfNZKzPP0800unsdsVzxVg1Otb07O916\n" +
                    "mbmuW+L9jQ6ckkr/AGEY4y7WFa1rrybeFc5bXndb9jsrLlV07M5HK0s2b9A59Vt0uocZ2tg9eMvE\n" +
                    "hzWPD/X2fc9XR/JZmbCVtu1u7GEut217GcstprY1za7GWeqwq/LjBx6ieLN3OirJNYZyVfNFK+W7\n" +
                    "WNxy6pTUlTb/AEvJ8VxqlHW1KsoL+dBu3qfaaqXh6eSb+WL33PidFOVavVlT3i73OvHtw+f/AKyP\n" +
                    "mpXTs1ZkPa4/w6VBx1EI/wAurnHRniM9EuvD6UEFygQrMSjNAiAQGQyEBFIAoBsOpULAhQAA6ARF\n" +
                    "IAKCACglwBSMAodQwQAAADAI9mBIfJH2KSHyR9jIioUAoD/IAAAAABkCl9zEoFKYhAZEQY6AUE9g\n" +
                    "BSAjAyQCAFQIUiBOoKFVAhbhEABQI7WKR7EEW5mvTcwVj1vh7SLVa+POrwjlhXVptJLSaKM3ipUz\n" +
                    "7I0VO57/ABuPlg1hWPAq7HLpuTI0pbtm3Q1eTW0n0vY0t2RohP8A9RB/3Ikmr6fcQkbIXTsznpS5\n" +
                    "qcX6G2Luss5PVHdT8yXoK8WmmaqLWGzdKXMs7GXblLYNU3Hms3G5Hs7nNOjGU7tXA6VayuZxz1Rr\n" +
                    "dK0PI7ehyT1EqU7VabXqitSa9Bu2+TZRq+fY4adbmSaeDfGqk7tozXXnl0amu08I5K80+VtZNzrQ\n" +
                    "krONyt05K3I0Gvw1dNJYwepQu0rNXPMpTjF7HVR1UYRd7L1uTW+eHo8kuW7wzFxVm5fds4pa+LxG\n" +
                    "8n6GmpKtqbRfli+iHlucMdZqKleTp6bKWHPueVH/AFOlredcx9TotJCjTyk3Y5tXRjNtKKux6cPl\n" +
                    "kcWmreJusnsaZ4SsedTo8q2V/Q7qXlSSeSViOuTXXBzzknfqZuVkc1SeWJDqtU2+ZJbEp/M5PphE\n" +
                    "lhepbJJenQ19OU81ycSko6StJvKiz5P4di5vUvoux7XxLq40NDOne85nB8LQ/wDQVWllyX3OnxPP\n" +
                    "892yPodRoIavgToyjlK6t3Py6vT8OrOD3i7H7XRgv9PGVndKzVj8i+Iqap8X1EVtzHae3l6eaB0B\n" +
                    "tlGQr9CFRQELgOgYAAEDAAAAAAAJ0AFBAUUAgFBABSMBgAQoAAgAPZ+wD2AkP6cfYyMYfJH2MgqF\n" +
                    "WxABQQpABCgEAty9SiFAAAACkBQABP8AIFIx2DIKgEAioEAVWL4J0KEUEQAoBAqmMsFIwC3wfW/B\n" +
                    "1D+VUqcry7JnyK3sfoPwpS5ODwkk7t9RUZ8Xop6aUV80c3PlauG7n2HFY8trbWPktauWbl0OfTrP\n" +
                    "TgqSsma6fzJkqS55vsZ01b3HqEfU8NqqpRSbyduzT/J89oqrg1Y9inX5ops4V6eb4ehSnyysn5To\n" +
                    "Uu7djzadS+32OqE7qz3M10lxu2X/AETlutrMwRm+Xa4aMtehhUpxnC0smbSfykezDcuNFOEYO0ld\n" +
                    "Imoo0ptOC5V2TN3JdeZI0yoyTvD8IO3PUrXCnONknJR9DKcKj/VIyi3FZTuZ897WX2MvRJK5Xp5v\n" +
                    "dy/J0aTQOUr7+5shJ4ujpoSlfy+VMNTHoQ09KhRiotObWVY36ekr3fU5tPi1/Mzti27PoLWOup6j\n" +
                    "a35bW/JonHzXZnd3w8GM2r7kcOvLBpZYTXRCSztgOy9ERhalTliznV5eZio+edr4RhWqKMeW+5qO\n" +
                    "durFKVRye0cXOTX62np6Unc06rXRpw5YM+Y4lq5V5Wu7IubWb1kcXFNTPU1XKTufU/CFD/0Ki7Jz\n" +
                    "lvc+PUJVaqjvdn3Pw9Hk8CEcJSVjvx4eXrzdfUwSpwcZJ4VmfkHxPSq0+K1nVjZSk2muqP2fiEFC\n" +
                    "dXlsmz4f4t4W9ZoZVIJeLTzjqjc964dPzkFaaw1lENsowUxe5RV1KRAIqHsQgFbAAUIAUAAAuAAg\n" +
                    "AAAACAAAAAAAAoAABHsykewGMPlj7FJD5I+xkRVJkAodSkFwKgAAAABgAAAAABQAIUABcjApSIAU\n" +
                    "EAFD2IEARlcmwAoIACDAYEisn6h8PQS4LSjzXsrq6PzvhGnep1lOFrxWWfp/DIqGnjGNrbWM0ntq\n" +
                    "19HxNLZJ3XY+M4xSlGhNPeL2Pvn5Yyg+rPG4zoPEpuUeqs0luZvl0lfAwjb3NkNxODhNxe6wZQWT\n" +
                    "NakdNF2tk7qFVxx0OKmboHKukejCo1lM7tPVUsp5PFjJo3QquEk0Zble9HzLczS6XOLSaiNRZdv+\n" +
                    "jrur4yg3KzVrW6ox8zwxzO+wd5XshjcrJW64NkbJmhRfVlV7kxqXW+rFODsShTSawhT5c3yzdCPW\n" +
                    "2COkg6avczpQinsZwintsbqcLOy2DWY20opLbJsUk8LFjXCLu1+4jCSfcyVk5+a3UXl2MlDrISsM\n" +
                    "Zta7u973RqrT5VZbsyqSVOLbOSVRJOpNlxzvTZOp4cLytg8PXa7zOzNXEuIutJxpvyf5PKqSu9yy\n" +
                    "Od6+l1GolPqcNRm6byc83uajla7eD0PEqym1hYPtOA0V/q6KSvlM+X4TDlopvCeWfScGrcuppyli\n" +
                    "zsdOazfT6niTU6l0+vTsebXpqSyvLLuejqW5fLtsjyOK8QjpKfI7ObwkajhX5r8T8Jq6DWzlGLdG\n" +
                    "TbUl0PDP1Opz6nStV6cZ8ywmtj5jifw2nB1aUfDf7HWMPkgdWr0Oo0r/AJkHbusnL1LADYFgBGAA\n" +
                    "ABQAAEBSACgBAhSAAUAACAUAAAAEQAAAwQKQ+SPsUkPkj7FCgAApMgAUEAFAAAhRgAAAgP8AAAUA\n" +
                    "AAD7gAUgAoIUAAQCghYpt2SbYFYN9PS1Z7qy9TroaGMcy8zImvOjCUsRi37Hbp+HTnZ1XZdup6dG\n" +
                    "jGOySO3S0PEnZrCyyCcH0qo1E4x5Y2svU+s0f9FX6dUeIpwjOCTsexoppw8ruTWo7qkbx5lutzKN\n" +
                    "PnhLHMrbGNB9GbtO/Cm+zI0+X4z8PR1SlX07UKnb6j5KdGdGq4VIuMl0Z+v6jTQrUk6bUZ/5PmuM\n" +
                    "8HlqYPxI8laO0+/oS+WpXxUEbYozraarp6rp14OMl3IvQ5dTHSMkZoiRUYajOm3CXNHc9nR6hVYJ\n" +
                    "NJSW6PGRupydOalF5XQNS49tpN9TNKNsnPp66qxT69ja3zB05rLrkOyfoEkOVWyHSRsgl0N1PZpm\n" +
                    "un5Urm1f/bGXWN1KSi0v+zppySdjjjGPNzJ/k6IPqw1rffLsZwd3tk1c2cWNnMo5e4ZrKp9KsaZS\n" +
                    "5YZ3E6i36Hla7WwjfzYXTuRz6uNmp1EXmUrQieFr9dKu3GPlp/5Ner1NSs/NiHRHHJ3LI49dJJmu\n" +
                    "TLJmEmaYYSZzzvKVllvobaksWNnDIeJq02rqOSxivc0dLwtNDmWy/c9fhNKTrWvl5RwKMvK3Y7dL\n" +
                    "WdKpFxk+dGp7L6fRarUxoaWVSWLLCPinqZa3iblJ3tn2PS+I5VY0Ytt8ssM83g9K051Gbjjf4+i0\n" +
                    "8U6W1zn1NWg9PKnJ+dvbodmlt4bta585x7n8e8FfOUbYq1tPT8FuLg+6bPE1vAqdam6lG0JvONj0\n" +
                    "tJSdRrmjdHuabSRcbWaVtzUH5pquHanTq86blH6lscb7M/V6ugiqbiopp9WjxNf8P06tGU40ldbu\n" +
                    "KKj4MHs6ngk4N+DK77SPN1GlrUH/ADKcl6hGgEuuhQAAKoQfZlCIAVAAQoAAAAAAAAEYDCCAKRho\n" +
                    "D2BHsESHyR9jKxjD5Y+xkRQIAoDA6gCggAoIUB7gEAoAAAAAAQCgAAAVJvCVwImU3w0tWfTlXqbo\n" +
                    "aOKa5m2ybIOWlRnV+VY7m5aKb3Z6EKajG0VZGdrE1HJT0dNPN5M6IU4wxGKRtjE2Qj6EGuMMmzlf\n" +
                    "Q2JFjuBnSjZHpaGKVFzW7ODdJLdux7MKapUIx9CrHDXfVnqcFqKrTfm+U8uvZXfTqbuCz5Zzs/Kz\n" +
                    "H20+lg753ZtpvmbvsjTTV+VYulcuoqOlTuupR6VKslCPM1ZfqGpUa9Jea13h9DynWtBU5XaltY7e\n" +
                    "HSlODh9Lvkg87i/ClqaKp1Jwc0vJLt7nxuo09TTVXSqq0l+5+h6qXiVEnThjF9j5/wCLtK4+BqFd\n" +
                    "3XK8YRmuvF+nzKRUEW2DjXSKjbHNsGpX6G+KwRWUXKm1KJ3UNVz2UsM44pdSyh7hqPWi1fGxsWTy\n" +
                    "adeUbKTuv3O2lXji0kV157dyaSylboXNjTGoml1Eqis2uhl0/PG6F75dzfTmkzhhWTfb0MpVoLLk\n" +
                    "kvUH7I71VV7GTqqKbk0eRV4jThiPmZ52p1lSs7N2XYYz18jv4hxJyvClKy7nj1ajk83v3ZhJ+prl\n" +
                    "K5XDrq1W31NcnkybMJdQyxkzXJ5KzBlRg05ySSu2e/w3TRoQWLye7scHCdPzzdR7LCPah5Xl4No6\n" +
                    "p03KEZLMtrI3aSjyNSe/r0MKMo+HeTul0Rt0yqVKytFvNl6liVt+JY/6jQUZyvzU+2zPO4dDkoL1\n" +
                    "Pb+I4Sp8LpU5pJyd0r5PJow5aUdsGo5/b0tK04pN2fdHn8S01qt2rp9Tu0iadrp+6OyvpvHpWUVd\n" +
                    "ZNxivG0lFY8qsepShyK9rnPRoTpztaz9TvhHGzNIyhGMk0anRSbfPy98bm/k5VeN2zl1E7X3WCDy\n" +
                    "tTo6dXncZLfB4leh4cmqkbx9rnuKUrvBlWpwnSfNFN9GVHx2q4bpq12o8ku8f/g8rU8Ir01zU2qk\n" +
                    "ey3PrK+jqK8oxVjkm3T+ZW9R6R8dJOLaknF+pD63U6bT6uFqsV/5Lc8DU8OlTqSVGSqRQlHCDKdO\n" +
                    "cPmg19jAqqCXKUQoIEUEAFIygCIFARAB1CgIwBSAdAJD5I+xSQ+WPsZBUKQoAAoAjKAIigjAoIgB\n" +
                    "QAAACTeybAA3U9NUn0sjpp6GzXNkmjhSbdkmzbT01SSz5V6nq09JGOxtjRpxfml9jN6HnU9LBWcv\n" +
                    "MdVOnZeSB1Xor19LDxL4hCyIOe0u9iQTeWZVHJys3uZRVtiAkVLJWVGhklm72MjFb2M0vQIscm6E\n" +
                    "bGEYm1tKLbKrPRpVNbFNYWT2pKLwmkeRwSPiVKtXfoj0K9WcdorBFjDUaduLcfxY87TznptR1cbn\n" +
                    "p0NZfyzSs/Q0cQpRlHnppv2M2rH0Onlz8k0/LKOH2NmqbnCys2l0PL4JVbpK7bt07HsQt4yvG8bC\n" +
                    "VWhTvC66G/QVZKE5tcreDKnpYckryaT29CzjCFHkV7LqjSNlOjztuy5n3eDX8T6ZfwCTndcsk1bK\n" +
                    "ubKMpxa5Zc1+klk1/F85UuAxg8Oc1dLZmOvDfHt8Bi7sVbkTKjhfbtGcUm7G6LyjTF2e1zan6/Yj\n" +
                    "TbH0Mk8ZNUWr/wDyZZAydv8A9MGuxWycyuUZRnNKyk0iqo7fMzBS7bEbAzdWXSWPQ1ym36mMmjBy\n" +
                    "7AVsxb9SNmLfsBb9zFuxi5N7EbCK36mDDZjJphEexnS0863yrHcz01GVaaSWD3tHp1TSTSfoakZt\n" +
                    "YcMpKOm5bZjg6HQ2b67I7aUYRVuWyZsnpG7OOZPZmzXPplGNOzSyenwalKpqYxiuaN77nHQ0lWrU\n" +
                    "ceR818xR9BRdLhfDq0qq5a9sX6l9M2vL+IqkavEPD8vLTVrJ3ycMI3asYRk6k7vd5Z3aalZJuWGr\n" +
                    "vGwkc2/TU27W2/yerp0rKLsaaMUqWI/lG2k1CUZJ9c2NIzq6aDTbSt6dDllSUXdbLY9GbvfdJnPW\n" +
                    "ilFv/BdRyVnaPr3PK1E/M7p/Y7tRVisJnBVkm8FiNLS5ezNiScLJZEVd7Z9TdCm0k92Uc9S6puE0\n" +
                    "pJ9zyq2jXLKVubvY9ivTc0+WzORQ5YyuUfMarSSlFypXX/ZwUXK0+bdOx9HrasaVBpcrZ4KipNyS\n" +
                    "3Zn7RrksZsa6lKElmEXg6ZQySUGaxHDLSUnfy29jU9FTezaPQcfQxcSK86WhXSbMf9EvqPRaexg4\n" +
                    "k0edLSNfrMf9Pb9R3uODFRuxquTVUIUoU2r8z3OY7+KK0oeiOA1EUAFEAAEBbkADoB0AkPlj7FJD\n" +
                    "5YlIoUIFBDYAALgdABQsvCNkaM5Z29wNYOmOnXXJuhRS2gjP5DijTnPaLN0NLOW9kdV2sNWLztk/\n" +
                    "KjXT0ceufc6YU6UMJK5jFczyZqHZE0ZqcViMci7fp7CMEZpEE5X3YwgyK7YFirszl5Y+pYxtlmFW\n" +
                    "XNJRAxgru73ZtS9CLYzKIomaXoRKxmlc0IkZxQUcmyMclwEjTrJ8tOy3Z0yfKtzz60nUq36bIzf4\n" +
                    "r2fh5WoTvjJ3V0uhq4ZSdOgla2MnRNJrexCPOqU023e3sYrmja23U6Z0+xrso7tGarp4VWdHVKL+\n" +
                    "WfU9+fkmpR22wfKSqclnHo8H0uirLUaSFRb2z7kit0JtxSVzNxShGU2sdH1OfyuVnLlv2N9qUFCM\n" +
                    "3ztu2TWn27OFU46is5OTVtrr9jwvjziUatWnpKbjenmajlXNXxnr9TptXHTaaL09DlVnH9frc+S5\n" +
                    "5Sk5Sbcnu31Mdb7deZjYjJeuTXdlTuc22xOzwZxlZ5NSdkVEHUpX3LssM0xkZ81wrJy7onMn7GMp\n" +
                    "Y7mFwja/RmLdjXzYJzBWUmYP1YcvcxcvQItzFtX2MebBG/QuC3XsRvJi2YN79wmrKWTPT0pVaiil\n" +
                    "7smnoz1FVRisd+x9Dp9NDTwSVvc1OUtYafTqnGPQ79PTvFtvDOeL5ppLqepTpKCink3GdbJx5dPB\n" +
                    "pZFCpWStCN77M6alP/06S2WTKjVp6XTSr13yx6Lqyei16FJrQQp1tW4rn2a3PnuKcSnxHUtJWpR2\n" +
                    "Xc83Xa6rxDUupUk+T9K7I3aaLbVt2GHZpYOXN62iezRp5aSsm/2RxcOpN8srXTk2emoNRy7dDoy2\n" +
                    "UafNFq021hu91czlF+E72uvQwpzjGsrxjaSunexsnqFNuEbpPra5BI1UonNqdTaLs89TXUqckJJ7\n" +
                    "o8rUVnK+blRhq6972dmccdS72buyVItvJqcc7I1EdsK+cnRHUWWOnc8tNo11XNbAehV18YPzv7nB\n" +
                    "qeK0rNJ56Hn1qVat8zdi0NBGOVe4HLWhqdZJ8kLL1NFKlKCcZpcyeT6SjS5UsZR5GpjbV1r9xJhX\n" +
                    "O6eckcNzoSSuaqj8xplocbbmEkrm2Rpn6maMLK5hJK5sRi9yVWppGKVr2M2si25FZavSUtRbzuEl\n" +
                    "1PMr6CtSykpx7xPclyvE9jXyqOYSKj5zZ2eGD6CtpY1Y+eMZPusM86vw6UX/ACnf0ZrRwAyqU503\n" +
                    "acWmYAV7EK9iFFI3gMXAkPlj7FJD5I+xSKFIX2yyh7A2KjJ+hup0lG2M+pLRzxpylthepuhp1dXz\n" +
                    "7HVCmupuhBJGbRzwpdkkboU11NsYq+DK3oQFCNtkOQJ2Zsi0yDW6Stk1zpNbbHZZJZMXBS2QVy09\n" +
                    "7G+KMZU+XKMo7AZ2xsYP0LKVka3Jt4Ayy2ZwiuphBN9DoggjCpLliaYZ80uplqMyUUVIDJGSyyJG\n" +
                    "cSjONjOKvsYRNsUaiKkbIxwWKRt5cFHLXTtZbvCOnSaOMOVvMn1Gmp8+olJ7RPW0tJt3tj1IrOnT\n" +
                    "5YqKSZi48su//R08sujXJ3sc848zauml1I05qi59nZe2TnqQSTS9zvlBpnLUjh+xKRxTjY9Hgmp5\n" +
                    "KjoyulLZ+px1Y7mqEnCaadmtjn6afU6lRUIzSvZ5EHGdpWvHqYcP1ENTRza7VpR7GUYS0tSyzF5X\n" +
                    "qaR1a7S0uL6JUKr5akfklY+U13w5qdM/K1U9EfX0HCVnF8tz0KDSzKKnJLdhqdPyyrp6tF2qwlB+\n" +
                    "pilZn6nqeH0OLaWdOvCEav6ZJbH57xPhlfQV5U6sG4p7mbzvp0nWuBIqQ9tgc7MbZIzTwa0ZAGYu\n" +
                    "4bMZO6CVi3+ScxJGDKjJyJdmITAyvkjYRGQGZ6fTz1FTlj92KFGVapyxPoNHQp0KSjFXkbkS1lpt\n" +
                    "NGhSUElddTNp2817GbpyaTbKlKKyro0xayiqbS5bJo9CjJSgrnkzXLK6R1UKrjGzTv2GD2YzTpWZ\n" +
                    "5/xKl4Gmin9jq0knKCXhyd87HnccrKvqoxTvGCsSledRha+D0tIrThfornHTiro7aDScnvaLLGa9\n" +
                    "jQq1OHdRudV7tJ2SObT4hd7WSNillpWTsbZXUy8iklZxd9uhsjWpxpc27fW1jC/NCzeGsnNTk5Rd\n" +
                    "N2vHu+gSGs80XKL3PLVGUsntVIJUs9ThuozavYsHDUpWRolTxd/sdGpq+e1vwYQjzdWBo8NvZGca\n" +
                    "PV7nT4eMG2nTwna/saHJHTpu9jONGPZJnaoZeF9zLw1ZO1vuNHGqKX27nz3EWnrqqjsj6qpaMW+p\n" +
                    "8dWzqa//AJEntKPZZ/Joqm+2NjTUWTVRqbwapGx32NUlnsZol0YSMrEkiDFLLDW/csFeTMmu5PtW\n" +
                    "6cZTsrdC09PLN2dNOC5Ytq7sboRSWcGsSuKOlfNvYT08ou6sdkpWeGjlr6jl9y1GienlVxOEWcNf\n" +
                    "h1Nztbkf7HW9TUk8EnJ2vOTv2M6PIraGtSvZc0fQ55U5reDPaXNUa5nbsdNGh1efcs1XzPWzGD6P\n" +
                    "X6HTVoXgmp90jwNRRnRk4zXsymtUfkj7FJD5I+xeuAqrLR004JJGulDqddKN8EtFjAz5SqPKzYlg\n" +
                    "wMYK2DbEwcTbDIFSM7XQSNkVfoUaZQdrpGMdzsUcWOetTcXdDBlF5NljRB5RvjkgSiaJxsdkYmFa\n" +
                    "ndYKOJZZshC7HJZmdMgzhGxm7Ri2wjVXlZFGqPmm5GxEhG0UZrAAqIZIo2QN0TTHBtj0KN8Fc2te\n" +
                    "RtkpLA1D/kuxr6Rs4Q41HO+9z26aailhLoeHwLTNp1JSsmz3YYflMLFqc0U3KSi+1jXGNoXlu8md\n" +
                    "abaUeZu+6sRpvCZVapxTs0clWLva3c7ZbWsaakU5JepFeXJNP3NNRO53VI4x2OWUdzFistHqZaas\n" +
                    "pp46rufT0K0NVRTi1/a+zPkJYeDq4dq5aWrm7pvdElwfQyc6clKF7fqXY6tJq7ys3nsaITjWgpwa\n" +
                    "ldflGqrSd7x+beL/AOjQ9unVcZqSlb1OjX0Ia6h4sEpTS80X1PntPrOWahV2ezPW0uo5LOLM+iV8\n" +
                    "1xLgtOd5aa1OX09D53U0ammqctaLT/Y/RtdCEn4kIpc29u542s0lPUQcZpSXruX26TrHx/Ngjkeh\n" +
                    "ruGSpJypXaXTseVJ2w1Zo53nG/y1nfBjJ2Nbn3JzA1k/uYNjmFyGp1KrXMX9iNsIzbLTi6k1GOWz\n" +
                    "CKbdlls9/hWg8OKnUXmZqQtbNDpYUYpX33Z6EVBbYRjUUVhWZr5drXsbjnW6TurRwiRk1hI9DQcM\n" +
                    "r6hKU1yUu7PSg+GaD+oozqL7l8QeHR0Go1LvRpyb79D0qXDNRSs9Ry2RjrfiVqLjpKSgtr2PB1fE\n" +
                    "dTqG/Fqya7GdR6/FeL+GnR0tr2s5o8aF35pvL3NdKnfzS2N27t0INsFi/U6aDXLL2SOVvHLE69P0\n" +
                    "st2kaiV6cKiS+7NkZpt43ONS8sVvu/3Nqkr7bZZvGXTKbTf0mipLwq0KlrxeJGTk+Qwq3l5XezIM\n" +
                    "9VX56sYLCtk55pRcuYmmqqP9RXkv8GVWarpyimmiwcE6bqO6Z0aenyrLNtPez6m5U4rLvco1xipL\n" +
                    "r7mcIcrvb7maVvQqWdyDKMcbK5bWV7FW3VGEpW/7COPWXtJnx8pc2pq+59XxKsqenm90fFwqXrTf\n" +
                    "dhHXfuaam5XI1zdpI0MZK3oS2Cv5fUkdiDBxJKPlM3a5ZLyAa4Qs2GuiMol6kntXdGypQwtiSqJd\n" +
                    "TVKoo0or0OKtX3tYWp9tmp1Fnho45OVV9zGKdSR2UqFkmSeRjRpWs2iOPNNt2OuVoQbZzwedtzWD\n" +
                    "OlRTfQz1c1Cmox3e9jKGE5NWscU5OdVsaf8AjoobZbNGspQq0ZxlFXSbTN9HCNOpVoT9mEfPJNQj\n" +
                    "jojbSh1ZulRtToy6OC/wWCLfDSxib6KyjCJsh8yOY21o4TuZUsoymuak/Q1UmBttcRwyxV8leANs\n" +
                    "EZo1QybUsFG6n6itT5oOzMIyszpSvEo8pK0rG+m/2Ma8HGo7kgyDqizNq6NdNmxX6AaKlPBKcc9j\n" +
                    "olC6NduVlGSiktzkreaqk9jonUsmaKSvKUgrJK+weEZpZMXuMQRkiJGSQGUcvJvgljJqhE3wVrGh\n" +
                    "0U0rKxhrWo0WZw7HPxKX8tLrcX0R0cHqTlJpNqKR7tOScd2keNwunyUVd2b3PT8sVeNmutzKxs5u\n" +
                    "apa7aiupldPZnPSldOX1ZNsGum4Fkly2yYOOxsbt0NU52YVqcVf8nLUpvdJ7HYszXXInC8fswPIr\n" +
                    "Rung0J5tk9OvRzKyPOrQaZzsxXo8L1joTUG3yP8AY93njJJp4e3ofIQlaWNz3OH6ryqMsosR2aik\n" +
                    "prma3/ZmmjqKmknyzTlBflHcpRbd9maa0L7Za/dFxXq6bUU9TS8sk7nDqY8k3GTPL/m6Srz0Xje3\n" +
                    "RnrabXafW0+WraNX1J6HDUaimjy+IaSnXi2klLuj2dZpZQTdPzRPKq1OR2asyrr5nU0Z0ZNSWO5p\n" +
                    "TsfQalQqwamkeHqNO6cm4ZiYsblY8wvc5+YzjIitn2C+/oRb4Pa4PoL2r1lj9MSyaWsuE8PSSq1l\n" +
                    "ndLsenKTUrRZ16bRV9TLyQah3Z7Gk4ZQ0/nqrxJLob8T2xa8jR6WpqbWpSt3Pb0mg02kXiahqU+k\n" +
                    "RX4jyLkpJRXocDqzqSc5tu2ckt/jLbxfitRuNGjaMXvboeFXqeuX1Zlqq3NWkzhrVLyIE5X26Fo0\n" +
                    "3PzSwjCjBzld7HTJ2VlsAnJWsjKkzRfJ16am3a66iQbKUOZNvsdtGPLKNvqRhSjant0RtWGu/mf7\n" +
                    "HSeEbE+XlaXRGMpPzezJKL5or229jFp8m2LW/cI66T5ub0wSpHytWz0NUJqEr+rOpNThzJ7ZA4J+\n" +
                    "W1TF7u/sdFOqlFWW5hUimlG/ZGGkkoVHGXur9gNtlzrojfFJpdTCTjNeW1/Y2UrJ53CL98lRg1ad\n" +
                    "+psWwCTSWzOWvO2Ub6rSVrnn6ieHYYjyeOahyotL9j52grykz3eIx54s8fTpeJND7GUlg11E7I6X\n" +
                    "HBJRvFbGxz9jFYbNrjYxcdmTBrayZdCyV2VLBBrhuV2IsSK9xRhq52SWdji5nJnRrU3yP0MdPRcr\n" +
                    "GcVs0tNXTPRjF8uSaekkkdEklH0NyJXBqpbR9TXDcsrSqN3M0klcyjVqarUeRXya6UHhsn9Su2d9\n" +
                    "KinH0E8q0QRr1cbQn/4s65QSeGaNVH+TP/xZqo+XevqulCn5bQSSwYf6yp6HODnrTpWtqrt+C/66\n" +
                    "rfp+DlAHcuJ10reX8GC19VO6t+DkAHd/E6/dfgfxOu91H8HCAO9cU1C25fwX+L6n+38HngD0lxjU\n" +
                    "rPl/BsXHtWseT8HkgD0avGNTUd5ct/YwXFNQvp/BwgD0Y8Y1K25fwZrjmqX0fg8sAer/AB3Vf2fg\n" +
                    "xlxrVNZ5PweYAPQfFdQ1+n8BcW1EUrct/Y88Ael/GNTn5fwT+L6j+38HnAu0ej/F9T/b+CrjGpX0\n" +
                    "39jzQNo9Ncb1S+j8GS47q19H4PKA2j118QaxfR+DCrxzVVGnLkx6HlgbR7UPiTWwVlyfgyn8Ta+a\n" +
                    "tJwa9jwwTR7v+6NfZW8P8FXxTxBPDh+DwQNHvv4r4g//AOf4Mf8AdGvfWn+DwgXR73+6Nf3p/wDE\n" +
                    "q+KuIJf+32+U8AEHvv4p1738P/iaKnxDrJ7qH4PHAHqfxvVXv5L+xto/EWtpO8XD8HjAD6JfF/El\n" +
                    "bNL/AImX+8eJY/pY68p82APon8XcRatalb/xOep8Sa2cubyJ90jxQDX0lL4y4rTjy89N+8TVW+Kd\n" +
                    "dWd5xpX9IngAD1pce1clZ8n4NcuM6l/R+DzQF12S4hWk72ivZE/19X0/ByAmQ/Ku+hxWtSmpJQdu\n" +
                    "jWD0afxXr4fKqX/E+fBTa+sh8d8WhFKPhJLpyir8e8XqRcW6KXpE+TANfR/7v4jdv+Xn+0P4w4ly\n" +
                    "uP8AKV/7T5wA17T+I9dLdw/Bh/H9Y3nk/B5AGJr2o/EmtjFJciX/AIh/Emtf0P7HigLr218S61Pa\n" +
                    "n/xN0fiziEX5fC/4nzwCPol8XcRtZeF/xMv948Stb+V1/SfNgaPpP95cT5r/AMr/AIh/GXErW/lW\n" +
                    "7cp82Bo+kfxhxJpX8Lr+kyh8acUi8eF/xPmQXaPpn8Z8Tf8A/L/ian8XcR51NOnzL+0+eAH00PjT\n" +
                    "icNvC/4ma+N+Krfwn7xPlgQfUv434q1/7X/En+9+KdHS/wCJ8uAPppfGnFJLLpf8TVL4t4jLfw/+\n" +
                    "J88C6PZqfEetms8n/E0R4zqYzcly39jzQNHq/wAc1f8Ab+CPjmr/ALPweWBtHpPjOpv+j8D+M6r+\n" +
                    "38HmgbR6P8Y1P9v4C4vqV9P4POA0eh/FtRe/l/AfFtQ/p/B54GjvnxWvJJPlx6GUeMaiOyh+DzgN\n" +
                    "HrR49q47KH4E+Payat5PweSBtHoLi2oX0/gsuL6h/T+DzgQd0OKV4NtcufQ6Fx7VpWXJb2PJBdHp\n" +
                    "S41qm7txv7CpxjUzg4y5bNWeDzQNAAEAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA\n" +
                    "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA\n" +
                    "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA\n" +
                    "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA\n" +
                    "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA\n" +
                    "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAB//Z";
            //endregion SomeName
            byte[] photo = Base64.decode(photoBase64, 0);

            insertUser(db, new User(0, "Алексей", "Дегтярев", "Сергеевич", "delexa0@gmail.com", getDateTime("1993-04-02"),
                    "M", "0", "0", "0", "89608519623", "123456789", photo, new Date()));

        }
        if (oldVersion < 2) {


        }
    }


    private static void createAllTables(SQLiteDatabase db) {

        // таблица USER
        db.execSQL("CREATE TABLE USER (" +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "NAME TEXT, " +
                "FAMILY TEXT, " +
                "PATRONYMIC TEXT, " +
                "EMAIL TEXT, " +
                "DATE NUMERIC, " +
                "SEX TEXT, " +
                "NOTIFICATIONTOPAY TEXT, " +
                "EMAILNOTIFICATIONCHANGESTATUS TEXT, " +
                "NEWSSUBSCRIBER TEXT, " +
                "PHONE TEXT, " +
                "PASSWORD VARCHAR, " +
                "IMAGE BLOB) ," +
                "TIMESTAMP DATETIME);");

        // таблица ORDER
        db.execSQL("CREATE TABLE ORDER (" +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "_USERID INTEGER, " +
                "_ESTABLISHMENTID INTEGER, " +
                "_PURCHASEDATE NUMERIC, " +
                "STATUS TEXT, " +
                "SERIES TEXT, " +
                "NUMBER TEXT, " +
                "CODE TEXT," +
                "TIMESTAMP DATETIME);");

        // таблица ESTABLISHMENT
        db.execSQL("CREATE TABLE ESTABLISHMENT (" +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "TYPE TEXT, " +
                "NAME TEXT, " +
                "ADDRESS TEXT, " +
                "TIMESTAMP DATETIME);");

        // таблица EVENT
        db.execSQL("CREATE TABLE EVENT (" +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "_ESTABLISHMENTID INTEGER, " +
                "COUNTRY TEXT, " +
                "GENRE TEXT, " +
                "AMOUNTTIME TEXT, " +
                "FORAGE TEXT, " +
                "ROLES TEXT, " +
                "ABOUT TEXT, " +
                "COVER TEXT, " +
                "VIDEOLINK TEXT," +
                "TIMESTAMP DATETIME);");

        // таблица SEAT
        db.execSQL("CREATE TABLE SEAT (" +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "_EVENTID INTEGER, " +
                "TIMEDATE DATETIME, " +
                "PRICE REAL, " +
                "NAME TEXT, " +
                "SERVICEPRICE REAL), " +
                "TIMESTAMP DATETIME);");

        // таблица SUBSCRIPTION
        db.execSQL("CREATE TABLE SUBSCRIPTION (" +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "_EVENTID INTEGER, " +
                "AMOUNTSEATS INTEGER, " +
                "TIMESTAMP DATETIME);");
    }

    private static void insertUser(SQLiteDatabase db, User user) {
        ContentValues userValues = new ContentValues();

        String date = getDateTime(user.getDate());
        String timestamp = getDateTime(user.getTimeStamp());

        userValues.put("NAME", user.getName());
        userValues.put("FAMILY", user.getFamily());
        userValues.put("PATRONYMIC", user.getPatronymic());
        userValues.put("EMAIL", user.getEmail());
        userValues.put("DATE", user.getDate().toString());
        userValues.put("SEX", user.getSex());
        userValues.put("NOTIFICATIONTOPAY", user.getNotificationToPay());
        userValues.put("EMAILNOTIFICATIONCHANGESTATUS", user.getEmailNotificationChangeStatus());
        userValues.put("NEWSSUBSCRIBER", user.getNewsSubscriber());
        userValues.put("PHONE", user.getPhone());
        userValues.put("PASSWORD", user.getPassword());
        userValues.put("IMAGE", user.getImage().toString());
        userValues.put("TIMESTAMP", getDateTime(user.getTimeStamp()));

        db.insert("USER", null, userValues);
    }


    private static String getDateTime(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        return dateFormat.format(date);
    }

    private static Date getDateTime(String myDate) {

        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            date = format.parse(myDate);
            System.out.println(date);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return date;
    }
}

