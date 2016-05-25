package com.delexa.chudobilet.DBHelpClasses;

import android.content.Context;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.delexa.chudobilet.DBClasses.Establishment;
import com.delexa.chudobilet.DBClasses.Event;
import com.delexa.chudobilet.DBClasses.EventType;
import com.delexa.chudobilet.DBClasses.News;
import com.delexa.chudobilet.DBClasses.Seat;
import com.delexa.chudobilet.DBClasses.SeatName;
import com.delexa.chudobilet.DBClasses.Subscription;
import com.delexa.chudobilet.DBClasses.TicketOrder;
import com.delexa.chudobilet.DBClasses.User;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class ChudobiletDatabaseHelper extends SQLiteOpenHelper {

    private static ChudobiletDatabaseHelper sInstance;

    private static final String SHORT_DATE = "yyyy-MM-dd";
    private static final String LONG_DATE = "yyyy-MM-dd HH:mm:ss";

    private static final String DB_NAME = "chudobilet"; // Имя базы данных
    private static final int DB_VERSION = 1; // Версия базы данных


    public static synchronized ChudobiletDatabaseHelper getInstance(Context context) {

        // Use the application context, which will ensure that you
        // don't accidentally leak an Activity's context.
        // See this article for more information: http://bit.ly/6LRzfx
        if (sInstance == null) {
            sInstance = new ChudobiletDatabaseHelper(context.getApplicationContext());
        }
        return sInstance;
    }

    /**
     * Constructor should be private to prevent direct instantiation.
     * make call to static method "getInstance()" instead.
     */

    public ChudobiletDatabaseHelper(Context context) {
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

            User user = new User("Алексей", "Дегтярев", "Сергеевич", "delexa0@gmail.com", getDateTime("1993-02-04", SHORT_DATE),
                    "M", "11", "0", "11", "89608519623", "123456789", "http://kotvokoshke.by/sites/default/files/field/image/image_cat.jpg",
                    "фэнтези", null, new Date());
            Establishment cinemaEstablishment = new Establishment("Большое Кино", "ТРК Alimpic, ул. Боевая, 25, " +
                    "Астрахань, Астраханская обл., 414024", new Date(), "1 3 4, 3 4 8,");


            Establishment concertEstablishment = new Establishment("Театр Оперы и Балета", "ул. Максаковой, 2, " +
                    "Астрахань, Астраханская обл., 414024", new Date(), "1 3 4, 3 5 8,");
            Establishment theaterEstablishment1 = new Establishment("Астраханский ТЮЗ", "414000 г. Астрахань, ул. Мусы Джалиля, 4",
                    new Date(), "1 3 4, 3 4 5,");
            Establishment theaterEstablishment2 = new Establishment("Астраханский театр кукол", "г. Астрахань, ул. Розы Люксембург, 7",
                    new Date(), null);

            Event cinemaEvent1 = new Event("Книга джунглей", cinemaEstablishment, "США", "фэнтези, драма, приключения, семейный, ...", 2016, " 1 час 50 минут", "6+",
                    "Скарлетт Йоханссон, Идрис Эльба, Билл Мюррей, Лупита Нионго, Кристофер Уокен, Джанкарло Эспозито, Нил Сетхи, Бен Кингсли, Ралф Айнесон, " +
                            "Ханна Тойнтон, ...", "Непримиримая борьба с опасным и внушающим страх тигром Шерханом вынуждает Маугли покинуть волчью стаю и отправиться в " +
                    "захватывающее путешествие. На пути мальчика ждут удивительные открытия и запоминающиеся встречи с пантерой Багирой, медведем Балу, питоном Каа и " +
                    "другими обитателями дремучих джунглей.", "http://new.chudobilet.ru/media/images/events/c05f81e44660d05b3c20bcf76cc76973.jpg", "https://www.youtube.com/watch?v=TwNXOE2yxPU",
                    "http://new.chudobilet.ru/event/992/", new Date());
            Event cinemaEvent2 = new Event("Белоснежка и Охотник 2", cinemaEstablishment, "США", "фэнтези, боевик, драма, приключения, ...", 2016, "1 час 55 минут", "6+",
                    "Крис Хемсворт, Сэм Клафлин, Эмили Блант, Джессика Честейн, Шарлиз Терон, Софи Куксон, Ник Фрост, Колин Морган, Ралф Айнесон, Шеридан Смит, ...",
                    "Когда любовь уходит, сердце прекрасной девы обращается в лед. И даже сотни королевств не смогут сдержать поступь ее несметного воинства. Лишь Охотник не ведает страха." +
                            " Сквозь проклятый лес он идет навстречу своей судьбе.", "http://new.chudobilet.ru/media/images/events/dd0d82740c770242d151223691a2b2df.jpg",
                    "https://www.youtube.com/watch?v=TwNXOE2yxPU",
                    "http://new.chudobilet.ru/event/1008/", new Date());
            Event concertEvent1 = new Event("Балет Лебединое озерo", concertEstablishment, null, null, 0, "2 часа", "6+", null,
                    "«Лебединое озеро» - шедевр мирового балетного искусства. Спектакль Астраханского театра оперы и балета, бессмертное произведение " +
                            "бессмертного композитора Петра Ильича Чайковского с аншлагами проходил во многих городах Европы. Не обошла его своим вниманием" +
                            " и астраханская публика.  С первых дней жизни в репертуаре Астраханского государственного театра Оперы и Балета спектакль " +
                            "«Лебединое Озеро» сопровождают аншлаги. Это символ нашего балета и очень серьезный экзамен для молодой труппы, который она " +
                            "сдаёт на «отлично». Этот балет Чайковского - один из самых сложных спектаклей классического репертуара. Тем отрадней, что в" +
                            " нынешнем составе балетной труппы астраханского театра есть исполнители, способные пройти серьёзное «испытание классикой»," +
                            " причём не просто преодолеть технические трудности, а подчинить каждый элемент танца художественным целям. Скоро нежный " +
                            "романтичный принц Зигфрид, готовый жертвовать собой ради любимой, вновь появится в свете театральных софитов на Большой " +
                            "сцене. А исполняемое солистами астраханского балета па-де-труа из «Лебединого озера» вызовет бурю оваций.",
                    "http://new.chudobilet.ru/media/images/events/503fb582ae571c8fd8c359330e0eb79c.jpg",
                    null, "http://new.chudobilet.ru/event/404", new Date());

            Event theaterEvent1 = new Event("Жил-был Геракл", theaterEstablishment1, null, null, 0, "1 час 40 минут", "6+", "С. Мартемьянов, Е. Ревкова, С. Журавлёв, " +
                    "К. Хахлев, Д. Юницкий, В. Яхтина, ...", "Жил-был Геракл… Тот самый? Тот самый. И хоть родился он героем – и рост, и сила при нём – вынужден был " +
                    "служить рабом у царя Эврисфея. Так случается - бог Зевс повелел, что уж тут поделаешь? Но, что-то делать надо... Чтобы стать свободным, " +
                    "Гераклу необходимо совершить двенадцать великих поступков, каждый из которых - ПОДВИГ. Он справляется с самыми непредсказуемыми испытаниями, " +
                    "ведь впереди его ожидает заветная награда - СВОБОДА!",
                    "http://new.chudobilet.ru/media/images/events/d8ed4896e6507faca33a5199ab59003c.jpg",
                    null, "http://new.chudobilet.ru/event/669/", new Date());
            Event theaterEvent2 = new Event("Приключения в стране непослушания", theaterEstablishment1, null, null, 0, "40 минут", "6+", "Е. Ревкова, Е. Перова, Л. Альмяшева, О." +
                    " Перова, А. Казакова, Е. Егорова, А. Еремицкая, В. Сельнинова", "Невероятная история, придуманная студентами театрального отделения Астраханской консерватории. " +
                    " Эта добрая, весёлая, очень музыкально-танцевальная история учит правильно оценивать добро и зло, различать плохое и хорошее, учит любви и ответственности.  Её" +
                    " понимание легко и доступно всем возрастам - от самых маленьких до самых взрослых.  Ещё более привлекательной эту историю делает то, что большинство исполнителей " +
                    "очень молоды. Их юношеский задор заражает зрителей неподдельной искренностью.",
                    "http://new.chudobilet.ru/media/images/events/6029be218a6c189b4cd6bf6f64243760.jpg",
                    null, "http://new.chudobilet.ru/event/1067/", new Date());

            News news1 = new News("В Астрахань приедет с гастролями Липецкий театр драмы", "10,11 и 12 июня Липецкий государственный академический театр драмы им. Л. Толстого на сцене Астраханского Драматического театра покажет спектакли «Квадратура Круга» В. Катаева, «Ужасные родители - С Ума сойти!» Ж. Кокто и сказку для детей «Дюймовочка» Г. Андерсена, Н. Эрдмана. \n" +
                    "\n" +
                    "Это одни из самых лучших спектаклей Липецкого театра драмы. Музыкальный спектакль для детей \"Дюймовочка\" по известной сказке Ганса-Христиана Андерсена и сценарию Николая Эрдмана наполнен яркими танцами и песням, а также включает в себя удивительные и разнообразные элементы театрального экспириенса - теневой театр, фонтан из мыльных пузырей и много другого, что порадует как самых маленьких зрителей, так и их родителей.\n" +
                    "\n" +
                    "\"Квадратура Круга\" - одно из самых знаменитых произведений Валентина Катаева. Написанная в 1927 г. и через год представленная на сцене МХТ, она до сих пор остается актуальной и интеллигентной \"шуткой Катаева\", рассказывая о простых и вечных ценностях, о молодости и любви, о радости жизни. История двух молодоженов-комсомольцев и их необычная история, рассказанная на фоне обычной квартиры, наполнена искрометным юмором и трогательными поисками любви.\n" +
                    "\n" +
                    "Жемчужиной гастролей станет спектакль \"Ужасные родители - С Ума сойти!\" - спектакль, по определению московского режиссера Дмитрия Горника, на грани фарса. В основе сюжета - семейный конфликт, который довольно часто встречается в нашей жизни: властные своенравные родители возражают против того, чтобы сын начал самостоятельную взрослую жизнь с девушкой. Актуальная, яркая и безумно смешная комедия - потрясающий подарок для астраханских зрителей всех возрастов.",
                    "Три постановки в подарок астраханцам", "http://new.chudobilet.ru/media/images/news/16ef30eaa6942b835d874eddd5f6570a.jpg",
                    getDateTime("2016-05-23", SHORT_DATE), new Date());
            News news2 = new News("Cпортивная весна", "А вы уже начали активную подготовку к предстоящему пляжному сезону? Составьте свою программу тренировок, не выходя из дома, с помощью сайта ЧУДОБИЛЕТ! В рубрике «На здоровье» нашего сайта вы можете приобрести билеты на посещение разнообразных тренировок wellness-центра «Идеал» (ул. Куликова, 66, к. 2). Сеансы помогут избавиться от проблем с лишним весом и целлюлитом, восстановят обмен веществ, работу лимфосистемы, мышечный и жизненный тонус. Отличная возможность привести в порядок свое тело, ведь лето уже не за горами!\n" +
                    "\n" +
                    "Разработанный план тренировок включает в себя занятия на уникальных wellness-тренажерах нового поколения: Виброплатформа Evo, Тонусные столы, Роликовый массажер Body Roll, Вакуумный тренажер VacuStep, а также Прессотерапию SlimFigure. Вы сами выбираете уровень нагрузки и планируете количество посещений, приобретая билеты онлайн.\n" +
                    "\n" +
                    "Кроме того, с помощью сайта ЧУДОБИЛЕТ вы можете приобрести билеты на разовое посещение занятий по восточным танцам, аэробике, на йогу или детский фитнес в wellness-центре «Идеал». \n" +
                    " \n" +
                    "Добро пожаловать в мир здоровья и красоты!" +
                    " \n" +
                    "С нами каждый день с удовольствием!", "Открываем сезон вместе", "http://new.chudobilet.ru/media/images/news/6ac22a5695f52a4c139334aa855af4c6.jpg",
                    getDateTime("2016-05-20", SHORT_DATE), new Date());
            News news3 = new News("\"Ночь музеев-2016\"", "21 мая в Астраханской области пройдет ежегодная весенняя акция «Ночь музеев». Тема мероприятия в этом году — российский кинематограф.\n" +
                    "Испытать себя на знание советских кинолент, погрузиться в мир коммунального быта и не только, смогут участники многочисленных викторин и интерактивных мероприятий в Краеведческом музее.Парящие острова, межгалактические корабль, безумное чаепитие – всё это ждет посетителей акции «Ночь музеев» в Астраханском кремле. В Артиллерийском дворе развернется презентация театрализованного проекта «Астраханская свадьба». Здесь же будут действовать квесты «Машина времени» и «Азбука стрелецкого снаряжения». На плацу Гауптвахты в исполнении духового казачьего оркестра прозвучат марши из кинофильмов, пройдет развод караула, а около Красной башни развернется музейная обсерватория.\n" +
                    "В Музее боевой славы откроется выставка «Советская эпоха в лицах, событиях», где будет представлена скульптура, живопись, графика. Музей культуры пригласит на выставку астраханских дизайнеров «Единство контрастов. Дизайн и декор», также там пройдет встреча с В.К. Петрушкиным, краеведом, историком астраханских кинотеатров. Музей истории города предложит погрузиться в «золотой век» русской культуры. При участии Астраханского бального движения пройдет бал в стиле XIX века с котильоном, ручейками и хороводами.\n" +
                    "В картинной галерее им. П.М. Догадина посетителей ждут обзорные экскурсии, увлекательные квесты, театральные зарисовки, «Ожившие картины» в исполнении студентов колледжа культуры, классическая музыка, литературно-музыкальная композиция «Вначале было Слово» театральной студии под руководством Сергея Тараскина.\n" +
                    "Дом-музей Б.М. Кустодиева приглашает принять участие в вечерней программе «Весенняя история». Гостей вечера ждут экскурсии по экспозиции и выставке «Весеннее отражение», где представлены живописные произведения и работы в технике горячего батика волгоградской художницы Натальи Рухлиной.\n" +
                    "В Доме-музее Велимира Хлебникова для любителей нонконформистского искусства на знаменитой Хлебниковской веранде откроется выставка живописных работ Вячеслава Шмагина (г. Дубны, Московская область). Увлекающимся авангардной поэзией будет представлен моноспектакль «Друк другу», где в исполнении Григория Миляшкина прозвучат стихотворения Владимира Друка.\n" +
                    "Дом купца Г.В.Тетюшинова приглашает побывать на открытии выставки астраханского мастера ткачества и вышивки Натальи Максимовой, увидеть концерт народного ансамбля «Астраханская песня». Различные тематические кинопоказы будут осуществляться в музейных двориках и помещениях галереи: любимые фильмы советской эпохи, краеведческие и научно-популярные фильмы из цикла «Искусство и кино».",
                    "Какой мы ее увидим?", "http://new.chudobilet.ru/media/images/news/6ae7d32c409a8f6d8ad87355d0e6cf64.jpg", getDateTime("2016-05-19", SHORT_DATE), new Date());


            EventType eventTypeCinema1 = new EventType(cinemaEvent1, "Кино", new Date());
            EventType eventTypeCinema2 = new EventType(cinemaEvent2, "Кино", new Date());
            EventType eventTypeConcert1 = new EventType(concertEvent1, "Концерты", new Date());
            EventType eventTypeTheater1 = new EventType(theaterEvent1, "Театры", new Date());
            EventType eventTypeTheater2 = new EventType(theaterEvent2, "Театры", new Date());
            EventType eventTypeForChildren1 = new EventType(theaterEvent2, "Детям", new Date());

            insertUser(db, user);

            insertNews(db, news1);
            insertNews(db, news2);
            insertNews(db, news3);

            insertEstablishment(db, cinemaEstablishment);
            insertEstablishment(db, concertEstablishment);
            insertEstablishment(db, theaterEstablishment1);
            insertEstablishment(db, theaterEstablishment2);

            insertEvent(db, cinemaEvent1);
            insertEvent(db, cinemaEvent2);
            insertEvent(db, concertEvent1);
            insertEvent(db, theaterEvent1);
            insertEvent(db, theaterEvent2);

            insertEventType(db, eventTypeCinema1);
            insertEventType(db, eventTypeCinema2);
            insertEventType(db, eventTypeConcert1);
            insertEventType(db, eventTypeTheater1);
            insertEventType(db, eventTypeTheater2);
            insertEventType(db, eventTypeForChildren1);

            TicketOrder ticketOrder1;
            TicketOrder ticketOrder2;


            for (int i = 1; i < 21; i++) {
                Seat seat = new Seat(cinemaEvent1, "А" + i, getDateTime("2016-07-01 12:00:00", LONG_DATE), 400, 20, new Date());
                insertSeat(db, seat);

                if (i == 1) {
                    ticketOrder1 = new TicketOrder(user, seat, getDateTime("2016-06-01 13:00:00", LONG_DATE), "неоплачено", "RS", "001468", "123456789012", new Date());
                    insertTicketOrder(db, ticketOrder1);
                }
            }

            for (int i = 1; i < 21; i++) {
                Seat seat = new Seat(cinemaEvent2, "А" + i, getDateTime("2016-07-02 12:00:00", LONG_DATE), 400, 20, new Date());
                insertSeat(db, seat);

                if (i == 1) {
                    ticketOrder2 = new TicketOrder(user, seat, getDateTime("2016-06-01 13:48:00", LONG_DATE), "оплачено", "RS", "001468", "123456789012", new Date());
                    insertTicketOrder(db, ticketOrder2);
                }
            }

            Subscription subscription1 = new Subscription(cinemaEvent1, 2, new Date());
            insertSubscription(db, subscription1);

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
                "PASSWORD TEXT, " +
                "PHOTO TEXT, " +
                "INTERESTGENRE TEXT, " +
                "INTERESTROLES TEXT, " +
                "TIMESTAMP NUMERIC);");

        // таблица ESTABLISHMENT
        db.execSQL("CREATE TABLE ESTABLISHMENT (" +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "NAME TEXT, " +
                "ADDRESS TEXT, " +
                "FAVOURITESEATS TEXT, " +
                "TIMESTAMP NUMERIC);");

        // таблица EVENT
        db.execSQL("CREATE TABLE EVENT (" +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "_ESTABLISHMENTID INTEGER, " +
                "NAME TEXT, " +
                "COUNTRY TEXT, " +
                "GENRE TEXT, " +
                "YEAR INTEGER, " +
                "AMOUNTTIME TEXT, " +
                "FORAGE TEXT, " +
                "ROLES TEXT, " +
                "ABOUT TEXT, " +
                "COVER TEXT, " +
                "VIDEOLINK TEXT," +
                "LINK TEXT, " +
                "ISNOTIFIED INTEGER, " +
                "TIMESTAMP NUMERIC);");

        // таблица SEAT
        db.execSQL("CREATE TABLE SEAT (" +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "_EVENTID INTEGER, " +
                "NAME TEXT, " +
                "TIMEDATE NUMERIC, " +
                "PRICE REAL, " +
                "SERVICEPRICE REAL," +
                "ISFREE INTEGER, " +
                "TIMESTAMP NUMERIC);");

        // таблица TICKETORDER
        db.execSQL("CREATE TABLE TICKETORDER (" +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "_USERID INTEGER, " +
                "_SEATID INTEGER, " +
                "PURCHASEDATE NUMERIC, " +
                "STATUS TEXT, " +
                "SERIES TEXT, " +
                "NUMBER TEXT, " +
                "CODE TEXT, " +
                "TIMESTAMP NUMERIC);");

        // таблица SUBSCRIPTION
        db.execSQL("CREATE TABLE SUBSCRIPTION (" +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "_EVENTID INTEGER, " +
                "AMOUNTSEATS INTEGER, " +
                "ISNOTIFIED INTEGER, " +
                "TIMESTAMP NUMERIC);");


        // таблица EVENTTYPE
        db.execSQL("CREATE TABLE EVENTTYPE (" +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "_EVENTID INTEGER, " +
                "TYPE TEXT, " +
                "TIMESTAMP NUMERIC);");


        // таблица NEWS
        db.execSQL("CREATE TABLE NEWS (" +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "NAME TEXT, " +
                "ABOUT TEXT, " +
                "INSHORT TEXT, " +
                "COVER TEXT, " +
                "DATE NUMERIC, " +
                "TIMESTAMP NUMERIC);");

    }


    private static void insertUser(SQLiteDatabase db, User user) {
        ContentValues userValues = new ContentValues();

        userValues.put("NAME", user.getName());
        userValues.put("FAMILY", user.getFamily());
        userValues.put("PATRONYMIC", user.getPatronymic());
        userValues.put("EMAIL", user.getEmail());
        userValues.put("DATE", getDateTime(user.getDate(), SHORT_DATE));
        userValues.put("SEX", user.getSex());
        userValues.put("NOTIFICATIONTOPAY", user.getNotificationToPay());
        userValues.put("EMAILNOTIFICATIONCHANGESTATUS", user.getEmailNotificationChangeStatus());
        userValues.put("NEWSSUBSCRIBER", user.getNewsSubscriber());
        userValues.put("PHONE", user.getPhone());
        userValues.put("PASSWORD", user.getPassword());
        userValues.put("PHOTO", user.getImage());
        userValues.put("INTERESTGENRE", user.getInterestGenre());
        userValues.put("INTERESTROLES", user.getInterestRoles());
        userValues.put("TIMESTAMP", getDateTime(user.getTimeStamp(), LONG_DATE));

        db.insert("USER", null, userValues);
    }

    private static void insertEstablishment(SQLiteDatabase db, Establishment establishment) {
        ContentValues establishmentValues = new ContentValues();

        establishmentValues.put("NAME", establishment.getName());
        establishmentValues.put("ADDRESS", establishment.getAddress());
        establishmentValues.put("FAVOURITESEATS", establishment.getFavouriteSeats());
        establishmentValues.put("TIMESTAMP", getDateTime(establishment.getTimeStamp(), LONG_DATE));

        db.insert("ESTABLISHMENT", null, establishmentValues);
    }

    private static void insertEvent(SQLiteDatabase db, Event event) {

        int establishmentId = Integer.MAX_VALUE;

        try {

            Cursor newCursor = db.query("ESTABLISHMENT",
                    new String[]{"_id"},
                    "ADDRESS = ? AND NAME = ?",
                    new String[]{event.getEstablishment().getAddress(), event.getEstablishment().getName()},
                    null, null, null);

            if (newCursor.getCount() == 1) {
                if (newCursor.moveToFirst()) {
                    establishmentId = newCursor.getInt(0);
                }


                ContentValues eventValues = new ContentValues();

                eventValues.put("_ESTABLISHMENTID", establishmentId);
                eventValues.put("NAME", event.getName());
                eventValues.put("COUNTRY", event.getCountry());
                eventValues.put("GENRE", event.getGenre());
                eventValues.put("YEAR", event.getYear());
                eventValues.put("AMOUNTTIME", event.getAmountTime());
                eventValues.put("FORAGE", event.getForAge());
                eventValues.put("ROLES", event.getRoles());
                eventValues.put("ABOUT", event.getAbout());
                eventValues.put("COVER", event.getCover());
                eventValues.put("VIDEOLINK", event.getVideoLink());
                eventValues.put("LINK", event.getLink());
                eventValues.put("ISNOTIFIED", event.getIsNotified());
                eventValues.put("TIMESTAMP", getDateTime(event.getTimeStamp(), LONG_DATE));

                db.insert("EVENT", null, eventValues);
            }

        } catch (SQLiteException e) {
            Log.d("My Logs", "Ошибка доступа к БД!");
        }

    }

    private static void insertEventType(SQLiteDatabase db, EventType eventType) {

        int eventId = Integer.MAX_VALUE;

        try {

            Cursor newCursor = db.query("EVENT",
                    new String[]{"_id"},
                    "NAME = ? AND LINK = ?",
                    new String[]{eventType.getEvent().getName(), eventType.getEvent().getLink()},
                    null, null, null);


            if (newCursor.getCount() == 1) {
                if (newCursor.moveToFirst()) {
                    eventId = newCursor.getInt(0);
                }

                ContentValues eventTypeValues = new ContentValues();

                eventTypeValues.put("_EVENTID", eventId);
                eventTypeValues.put("TYPE", eventType.getType());
                eventTypeValues.put("TIMESTAMP", getDateTime(eventType.getTimeStamp(), LONG_DATE));

                db.insert("EVENTTYPE", null, eventTypeValues);
            }

        } catch (SQLiteException e) {
            Log.d("My Logs", "Ошибка доступа к БД!");
        }

    }

    private static void insertSeat(SQLiteDatabase db, Seat seat) {

        int eventId = Integer.MAX_VALUE;

        try {


            Cursor newCursor = db.query("EVENT",
                    new String[]{"_id"},
                    "NAME = ? AND LINK = ?",
                    new String[]{seat.getEvent().getName(), seat.getEvent().getLink()},
                    null, null, null);

            if (newCursor.getCount() == 1) {
                if (newCursor.moveToFirst()) {
                    eventId = newCursor.getInt(0);
                }

                ContentValues seatValues = new ContentValues();

                seatValues.put("_EVENTID", eventId);
                seatValues.put("NAME", seat.getName());
                seatValues.put("TIMEDATE", getDateTime(seat.getTimeDate(), LONG_DATE));
                seatValues.put("PRICE", seat.getPrice());
                seatValues.put("SERVICEPRICE", seat.getServicePrice());
                seatValues.put("ISFREE", seat.getIsFree());
                seatValues.put("TIMESTAMP", getDateTime(seat.getTimeStamp(), LONG_DATE));

                db.insert("SEAT", null, seatValues);
            }

        } catch (SQLiteException e) {
            Log.d("My Logs", "Ошибка доступа к БД!");
        }

    }

    private static void insertTicketOrder(SQLiteDatabase db, TicketOrder ticketOrder) {

        int seatId = Integer.MAX_VALUE;

        try {

            Cursor newCursor = db.query("SEAT",
                    new String[]{"_id"},
                    "NAME = ? AND TIMEDATE = ? AND PRICE = ? AND SERVICEPRICE = ?",
                    new String[]{ticketOrder.getSeat().getName(), getDateTime(ticketOrder.getSeat().getTimeDate(), LONG_DATE),
                            Double.toString(ticketOrder.getSeat().getPrice()),
                            Double.toString(ticketOrder.getSeat().getServicePrice())},
                    null, null, null);

            if (newCursor.getCount() == 1) {
                if (newCursor.moveToFirst()) {
                    seatId = newCursor.getInt(0);
                }

                ContentValues ticketOrderValues = new ContentValues();

                ticketOrderValues.put("_USERID", "1");
                ticketOrderValues.put("_SEATID", seatId);
                ticketOrderValues.put("PURCHASEDATE", getDateTime(ticketOrder.getPurchaseDate(), LONG_DATE));
                ticketOrderValues.put("STATUS", ticketOrder.getStatus());
                ticketOrderValues.put("SERIES", ticketOrder.getSeries());
                ticketOrderValues.put("NUMBER", ticketOrder.getNumber());
                ticketOrderValues.put("CODE", ticketOrder.getCode());
                ticketOrderValues.put("TIMESTAMP", getDateTime(ticketOrder.getTimeStamp(), LONG_DATE));

                db.insert("TICKETORDER", null, ticketOrderValues);
            }

        } catch (SQLiteException e) {
            Log.d("My Logs", "Ошибка доступа к БД!");
        }

    }

    private static void insertSubscription(SQLiteDatabase db, Subscription subscription) {

        int eventId = Integer.MAX_VALUE;

        try {

            Cursor newCursor = db.query("EVENT",
                    new String[]{"_id"},
                    "NAME = ? AND LINK = ?",
                    new String[]{subscription.getEvent().getName(), subscription.getEvent().getLink()},
                    null, null, null);


            if (newCursor.getCount() == 1) {
                if (newCursor.moveToFirst()) {
                    eventId = newCursor.getInt(0);
                }

                ContentValues subscriptionValues = new ContentValues();

                subscriptionValues.put("_EVENTID", eventId);
                subscriptionValues.put("AMOUNTSEATS", subscription.getAmountSeats());
                subscriptionValues.put("ISNOTIFIED", subscription.getIsNotified());
                subscriptionValues.put("TIMESTAMP", getDateTime(subscription.getTimeStamp(), LONG_DATE));

                db.insert("SUBSCRIPTION", null, subscriptionValues);
            }

        } catch (SQLiteException e) {
            Log.d("My Logs", "Ошибка доступа к БД!");
        }

    }

    private static void insertNews(SQLiteDatabase db, News news) {
        ContentValues newsValues = new ContentValues();

        newsValues.put("NAME", news.getName());
        newsValues.put("ABOUT", news.getAbout());
        newsValues.put("INSHORT", news.getInShort());
        newsValues.put("COVER", news.getCover());
        newsValues.put("DATE", getDateTime(news.getDate(), SHORT_DATE));
        newsValues.put("TIMESTAMP", getDateTime(news.getTimeStamp(), LONG_DATE));

        db.insert("NEWS", null, newsValues);
    }


    public static List<Event> getEvents(SQLiteDatabase db, String eventType) {

        List<Event> data = new ArrayList<>();

        try {


            Cursor newCursor = db.rawQuery("SELECT EVENT._id, EVENT.NAME, EVENT.COUNTRY, EVENT.GENRE, EVENT.YEAR, EVENT.AMOUNTTIME, EVENT._ESTABLISHMENTID, " +
                    "EVENT.FORAGE, EVENT.ROLES, EVENT.ABOUT, EVENT.COVER, EVENT.VIDEOLINK, EVENT.LINK, EVENT.ISNOTIFIED, EVENT.TIMESTAMP" +
                    " FROM EVENT, EVENTTYPE " +
                    "WHERE  (EVENT._id = EVENTTYPE._EVENTID AND EVENTTYPE.TYPE = '" + eventType + "') " +
                    "GROUP BY EVENT._id", null);
            data = cursorToListEvent(newCursor);

            db.close();

        } catch (SQLiteException e) {
        }


        return data;

    }

    public static Event getEvent(SQLiteDatabase db, int id) {

        List<Event> data = new ArrayList<>();

        try {
            Cursor newCursor = db.rawQuery("SELECT EVENT._id, EVENT.NAME, EVENT.COUNTRY, EVENT.GENRE, EVENT.YEAR, EVENT.AMOUNTTIME, EVENT._ESTABLISHMENTID, " +
                    "EVENT.FORAGE, EVENT.ROLES, EVENT.ABOUT, EVENT.COVER, EVENT.VIDEOLINK, EVENT.LINK, EVENT.ISNOTIFIED, EVENT.TIMESTAMP" +
                    " FROM EVENT" +
                    " WHERE EVENT._id = " + Integer.toString(id) +
                    " GROUP BY EVENT._id", null);

            data = cursorToListEvent(newCursor);

            db.close();


        } catch (SQLiteException e) {
        }

        return data.get(0);

    }

    public static List<Event> getEventsByEstablishment(SQLiteDatabase db, int establishmentId) {

        List<Event> data = new ArrayList<>();

        try {

            Cursor newCursor = db.rawQuery("SELECT EVENT._id, EVENT.NAME, EVENT.COUNTRY, EVENT.GENRE, EVENT.YEAR, EVENT.AMOUNTTIME, EVENT._ESTABLISHMENTID, " +
                    "EVENT.FORAGE, EVENT.ROLES, EVENT.ABOUT, EVENT.COVER, EVENT.VIDEOLINK, EVENT.LINK, EVENT.ISNOTIFIED, EVENT.TIMESTAMP" +
                    " FROM EVENT " +
                    "WHERE EVENT._ESTABLISHMENTID  = '" + establishmentId + "' " +
                    "GROUP BY EVENT._id", null);
            data = cursorToListEvent(newCursor);

            db.close();


        } catch (SQLiteException e) {
        }


        return data;

    }

    public static List<Establishment> getEstablishments(SQLiteDatabase db, String type) {

        List<Establishment> data = new ArrayList<>();

        try {

            Cursor newCursor = db.rawQuery("SELECT ESTABLISHMENT._id, ESTABLISHMENT.NAME, ESTABLISHMENT.ADDRESS," +
                    " ESTABLISHMENT.TIMESTAMP" +
                    " FROM EVENT, ESTABLISHMENT, EVENTTYPE " +
                    "WHERE  (EVENT._id = EVENTTYPE._EVENTID AND ESTABLISHMENT._ID = EVENT._ESTABLISHMENTID AND EVENTTYPE.TYPE = '" + type + "') " +
                    "GROUP BY ESTABLISHMENT._id", null);

            data = cursorToListEstablishment(newCursor);

            db.close();

        } catch (SQLiteException e) {
        }

        return data;

    }

    public static List<TicketOrder> getTicketOrders(SQLiteDatabase db) {

        List<TicketOrder> data = new ArrayList<>();

        try {

            Cursor newCursor = db.rawQuery("SELECT EVENT.NAME AS EVENTNAME, EVENT.COUNTRY, EVENT.GENRE, EVENT.YEAR, EVENT.AMOUNTTIME, \n" +
                    "EVENT.FORAGE, EVENT.ROLES, EVENT.ABOUT, EVENT.COVER, EVENT.VIDEOLINK, EVENT.LINK, EVENT.ISNOTIFIED, \n" +
                    "\n" +
                    "ESTABLISHMENT.NAME AS ESTABLISHMENTNAME, ESTABLISHMENT.ADDRESS, \n" +
                    "\n" +
                    "SEAT.NAME, SEAT.TIMEDATE, SEAT.PRICE, SEAT.SERVICEPRICE, SEAT.ISFREE, \n" +
                    "\n" +
                    "TICKETORDER._ID, TICKETORDER.PURCHASEDATE, TICKETORDER.STATUS, TICKETORDER.SERIES, \n" +
                    "TICKETORDER.NUMBER, TICKETORDER.CODE\n" +
                    "\n" +
                    "FROM EVENT, TICKETORDER, ESTABLISHMENT, SEAT\n" +
                    "\n" +
                    "WHERE ESTABLISHMENT._ID = EVENT._ESTABLISHMENTID AND EVENT._ID = SEAT._EVENTID AND TICKETORDER._USERID = '1' AND TICKETORDER._SEATID = SEAT._ID " +
                    "GROUP BY TICKETORDER._id", null);


            data = cursorToListTicketOrder(newCursor);

            db.close();


        } catch (SQLiteException e) {
        }


        return data;

    }

    public static List<String> getEstablishmentListNames(SQLiteDatabase db) {

        List<String> data = new ArrayList<>();

        try {

            Cursor newCursor = db.rawQuery("SELECT ESTABLISHMENT.NAME FROM ESTABLISHMENT GROUP BY ESTABLISHMENT.NAME", null);


            while (newCursor.moveToNext()) {

                data.add(newCursor.getString(0));
            }
            newCursor.close();

            db.close();

        } catch (SQLiteException e) {
        }


        return data;

    }

    public static List<Subscription> getSubscription(SQLiteDatabase db) {

        List<Subscription> data = new ArrayList<>();

        try {

            Cursor newCursor = db.rawQuery("SELECT SUBSCRIPTION._id, SUBSCRIPTION.AMOUNTSEATS, SUBSCRIPTION.ISNOTIFIED,\n" +
                    "EVENT.NAME AS EVENTNAME, EVENT.COUNTRY, EVENT.GENRE, EVENT.YEAR, EVENT.AMOUNTTIME, EVENT.FORAGE, EVENT.ROLES, EVENT.ABOUT, EVENT.COVER, EVENT.VIDEOLINK, EVENT.LINK,\n" +
                    "ESTABLISHMENT.ADDRESS, ESTABLISHMENT.NAME AS ESTABLISHMENTNAME\n" +
                    "FROM EVENT, ESTABLISHMENT, SUBSCRIPTION \n" +
                    "WHERE  (EVENT._id = SUBSCRIPTION._EVENTID AND ESTABLISHMENT._ID = EVENT._ESTABLISHMENTID)\n" +
                    "GROUP BY SUBSCRIPTION._id", null);

            data = cursorToListSubscription(newCursor);

            db.close();

        } catch (SQLiteException e) {
        }

        return data;

    }

    public static int getAmountOfFreeSeats(SQLiteDatabase db, int id) {

        int amount = 0;

        try {

            Cursor newCursor = db.rawQuery("SELECT SEAT._id, SEAT.NAME " +
                    "FROM SEAT WHERE SEAT._EVENTID = '" + id + "' AND SEAT.ISFREE = '0' GROUP BY SEAT._id", null);

            amount = newCursor.getCount();

            newCursor.close();

            db.close();

        } catch (SQLiteException e) {
        }

        return amount;

    }

    public static List<SeatName> getEstablishmentFavouriteSeatsbyEstablishmentid(SQLiteDatabase db, String establishmentName) {

        List<SeatName> data = new ArrayList<>();
        String seats = "";

        try {

            Cursor newCursor = db.rawQuery("SELECT ESTABLISHMENT.FAVOURITESEATS FROM ESTABLISHMENT ESTABLISHMENT WHERE " +
                    "ESTABLISHMENT.NAME = '" + establishmentName + "'", null);

            while (newCursor.moveToNext()) {
                seats = (newCursor.getString(newCursor.getColumnIndex("FAVOURITESEATS")));
            }

            newCursor.close();
            db.close();

        } catch (SQLiteException e) {
        }

        List<String> List1 = Arrays.asList(seats.split(","));

        for (String i : List1) {
            SeatName seatName = new SeatName();

            if (i.charAt(0) == ' ') i = i.substring(1);
            List<String> List2 = Arrays.asList(i.split("\\s"));

            seatName.setSector(List2.get(0));
            seatName.setRow(List2.get(1));
            seatName.setSeat(List2.get(2));

            data.add(seatName);

        }


        return data;


    }

    public static List<News> getNews(SQLiteDatabase db) {

        List<News> data = new ArrayList<>();

        try {

            Cursor newCursor = db.rawQuery("SELECT * FROM NEWS", null);
            while (newCursor.moveToNext()) {
                News news = new News();

                news.setId(newCursor.getInt(newCursor.getColumnIndex("_id")));
                news.setName(newCursor.getString(newCursor.getColumnIndex("NAME")));
                news.setCover(newCursor.getString(newCursor.getColumnIndex("COVER")));
                news.setInShort(newCursor.getString(newCursor.getColumnIndex("INSHORT")));
                news.setDate(getDateTime(newCursor.getString(newCursor.getColumnIndex("DATE")), SHORT_DATE));
                news.setTimeStamp(getDateTime(newCursor.getString(newCursor.getColumnIndex("TIMESTAMP")), LONG_DATE));

                data.add(news);
            }
            newCursor.close();

            db.close();


        } catch (SQLiteException e) {
        }


        return data;

    }


    private static String getDateTime(Date date, String dateFormat) {
        SimpleDateFormat myDateFormat = new SimpleDateFormat(
                dateFormat, Locale.getDefault());
        return myDateFormat.format(date);
    }

    private static Date getDateTime(String myDate, String dateFormat) {

        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat(dateFormat);
        try {
            date = format.parse(myDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }


    //   public static void changeinterestGenre

    public static void InsertSubscriptionbyEventid(SQLiteDatabase db, int id, int amountSeats) {

        ContentValues subscriptionValues = new ContentValues();

        subscriptionValues.put("_EVENTID", id);
        subscriptionValues.put("AMOUNTSEATS", amountSeats);
        subscriptionValues.put("ISNOTIFIED", 0);
        subscriptionValues.put("TIMESTAMP", getDateTime(new Date(), LONG_DATE));

        db.insert("SUBSCRIPTION", null, subscriptionValues);

    }


    private static List<Event> cursorToListEvent(Cursor newCursor) {

        List<Event> data = new ArrayList<>();
        while (newCursor.moveToNext()) {
            Event curEvent = new Event();

            curEvent.setId(newCursor.getInt(newCursor.getColumnIndex("_id")));
            curEvent.setName(newCursor.getString(newCursor.getColumnIndex("NAME")));
            curEvent.setCountry(newCursor.getString(newCursor.getColumnIndex("COUNTRY")));
            curEvent.setGenre(newCursor.getString(newCursor.getColumnIndex("GENRE")));
            curEvent.setYear(newCursor.getInt(newCursor.getColumnIndex("YEAR")));
            curEvent.setAmountTime(newCursor.getString(newCursor.getColumnIndex("AMOUNTTIME")));
            curEvent.setForAge(newCursor.getString(newCursor.getColumnIndex("FORAGE")));
            curEvent.setRoles(newCursor.getString(newCursor.getColumnIndex("ROLES")));
            curEvent.setAbout(newCursor.getString(newCursor.getColumnIndex("ABOUT")));
            curEvent.setCover(newCursor.getString(newCursor.getColumnIndex("COVER")));
            curEvent.setVideoLink(newCursor.getString(newCursor.getColumnIndex("VIDEOLINK")));
            curEvent.setLink(newCursor.getString(newCursor.getColumnIndex("LINK")));
            curEvent.setIsNotified(newCursor.getInt(newCursor.getColumnIndex("ISNOTIFIED")));
            curEvent.setTimeStamp(getDateTime(newCursor.getString(newCursor.getColumnIndex("TIMESTAMP")), LONG_DATE));

            data.add(curEvent);
        }
        newCursor.close();

        return data;

    }

    private static List<Establishment> cursorToListEstablishment(Cursor newCursor) {

        List<Establishment> data = new ArrayList<>();
        while (newCursor.moveToNext()) {
            Establishment curEstablishment = new Establishment();

            curEstablishment.setId(newCursor.getInt(newCursor.getColumnIndex("_id")));
            curEstablishment.setName(newCursor.getString(newCursor.getColumnIndex("NAME")));
            curEstablishment.setAddress(newCursor.getString(newCursor.getColumnIndex("ADDRESS")));
            curEstablishment.setTimeStamp(getDateTime(newCursor.getString(newCursor.getColumnIndex("TIMESTAMP")), LONG_DATE));

            data.add(curEstablishment);
        }
        newCursor.close();

        return data;

    }

    private static List<TicketOrder> cursorToListTicketOrder(Cursor newCursor) {

        List<TicketOrder> data = new ArrayList<>();
        while (newCursor.moveToNext()) {
            TicketOrder ticketOrder = new TicketOrder();
            Seat seat = new Seat();
            Event event = new Event();
            Establishment establishment = new Establishment();

            event.setName(newCursor.getString(newCursor.getColumnIndex("EVENTNAME")));
            event.setCountry(newCursor.getString(newCursor.getColumnIndex("COUNTRY")));
            event.setGenre(newCursor.getString(newCursor.getColumnIndex("GENRE")));
            event.setYear(newCursor.getInt(newCursor.getColumnIndex("YEAR")));
            event.setAmountTime(newCursor.getString(newCursor.getColumnIndex("AMOUNTTIME")));
            event.setForAge(newCursor.getString(newCursor.getColumnIndex("FORAGE")));
            event.setRoles(newCursor.getString(newCursor.getColumnIndex("ROLES")));
            event.setAbout(newCursor.getString(newCursor.getColumnIndex("ABOUT")));
            event.setCover(newCursor.getString(newCursor.getColumnIndex("COVER")));
            event.setVideoLink(newCursor.getString(newCursor.getColumnIndex("VIDEOLINK")));
            event.setLink(newCursor.getString(newCursor.getColumnIndex("LINK")));
            event.setIsNotified(newCursor.getInt(newCursor.getColumnIndex("ISNOTIFIED")));

            establishment.setName(newCursor.getString(newCursor.getColumnIndex("ESTABLISHMENTNAME")));
            establishment.setAddress(newCursor.getString(newCursor.getColumnIndex("ADDRESS")));

            seat.setName(newCursor.getString(newCursor.getColumnIndex("NAME")));
            // seat.setTimeDate(newCursor.getString(newCursor.getColumnIndex(getDateTime("NAME", LONG_DATE))));
            // seat.setPrice(newCursor.getString(newCursor.getColumnIndex("PRICE")));
//            seat.setServicePrice(newCursor.getString(newCursor.getColumnIndex("SEVICEPRICE")));
            seat.setIsFree(newCursor.getInt(newCursor.getColumnIndex("ISFREE")));

            event.setEstablishment(establishment);
            seat.setEvent(event);
            ticketOrder.setSeat(seat);

            ticketOrder.setId(newCursor.getInt(newCursor.getColumnIndex("_id")));
            ticketOrder.setSeries(newCursor.getString(newCursor.getColumnIndex("PURCHASEDATE")));
            ticketOrder.setStatus(newCursor.getString(newCursor.getColumnIndex("STATUS")));
            ticketOrder.setSeries(newCursor.getString(newCursor.getColumnIndex("SERIES")));
            ticketOrder.setNumber(newCursor.getString(newCursor.getColumnIndex("NUMBER")));
            ticketOrder.setCode(newCursor.getString(newCursor.getColumnIndex("CODE")));


            data.add(ticketOrder);
        }
        newCursor.close();

        return data;

    }


    private static List<Subscription> cursorToListSubscription(Cursor newCursor) {

        List<Subscription> data = new ArrayList<>();
        while (newCursor.moveToNext()) {
            Subscription subscription = new Subscription();
            Event event = new Event();
            Establishment establishment = new Establishment();

            event.setName(newCursor.getString(newCursor.getColumnIndex("EVENTNAME")));
            event.setCountry(newCursor.getString(newCursor.getColumnIndex("COUNTRY")));
            event.setGenre(newCursor.getString(newCursor.getColumnIndex("GENRE")));
            event.setYear(newCursor.getInt(newCursor.getColumnIndex("YEAR")));
            event.setAmountTime(newCursor.getString(newCursor.getColumnIndex("AMOUNTTIME")));
            event.setForAge(newCursor.getString(newCursor.getColumnIndex("FORAGE")));
            event.setRoles(newCursor.getString(newCursor.getColumnIndex("ROLES")));
            event.setAbout(newCursor.getString(newCursor.getColumnIndex("ABOUT")));
            event.setCover(newCursor.getString(newCursor.getColumnIndex("COVER")));
            event.setVideoLink(newCursor.getString(newCursor.getColumnIndex("VIDEOLINK")));
            event.setLink(newCursor.getString(newCursor.getColumnIndex("LINK")));
            event.setIsNotified(newCursor.getInt(newCursor.getColumnIndex("ISNOTIFIED")));

            establishment.setName(newCursor.getString(newCursor.getColumnIndex("ESTABLISHMENTNAME")));
            establishment.setAddress(newCursor.getString(newCursor.getColumnIndex("ADDRESS")));

            subscription.setId(newCursor.getInt(newCursor.getColumnIndex("_id")));
            subscription.setAmountSeats(newCursor.getInt(newCursor.getColumnIndex("AMOUNTSEATS")));
            subscription.setIsNotified(newCursor.getInt(newCursor.getColumnIndex("ISNOTIFIED")));

            event.setEstablishment(establishment);
            subscription.setEvent(event);

            data.add(subscription);
        }
        newCursor.close();

        return data;

    }


}
