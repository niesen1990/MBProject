package com.cmbb.smartkids.base;

/**
 * 项目名称：MBProject
 * 类描述：
 * 创建人：N.Sun
 * 创建时间：15/7/28 上午10:29
 */
public class Constants {


    private Constants() {

    }

    // Receiver
    // 请求失败Receiver

    public static final String RefreshFlag = "com.refresh";
    public static final String NETWORK_FAILURE = "network_failure";
    public static final String NETWORK_FLAG = "network_flag";
    //public static final String BASE_URL = "http://192.168.100.151:8089/spring";
    //public static final String BASE_IMAGE_URL = "http://192.168.100.151:8089/image";
    public static final String BASE_URL = "http://mengbaopai.smart-kids.com/iosAndroid";
    public static final String BASE_URL_TAG = "http://mengbaopai.smart-kids.com";
    public static final String BASE_IMAGE_URL = "http://mengbaopai.smart-kids.com/image";
    public static final String BASE_IMAGE_URL_OLD = "http://mengbaopai.smart-kids.com/smart/";// 判断upload字符串

    // Message
    public static final String FINDMESSAGE_URL = BASE_URL + "/message/findMessage";
    // 添加关注
    public static final String ADDATTENTION_URL = BASE_URL + "/attention/addAttention";
    public static final String DELETEATTENTION_URL = BASE_URL + "/attention/deleteAttention";
    // 举报
    public static final String ADDREPORT_URL = BASE_URL + "/report/addReport";
    // 分享
    public static final String SHARE_URL = BASE_URL + "/index.jsp";
    // 收藏
    public static final String ADDSTOREUP_URL = BASE_URL + "/storeUp/addStoreUp";
    // 取消收藏
    public static final String DELETESTOREUP_URL = BASE_URL + "/storeUp/deleteStoreUp";
    //点赞
    public static final String ADDSPOT_URL = BASE_URL + "/storeUp/deleteStoreUp";


    public static final class Search {
        private Search() {

        }

        // Account type id
        public static final String SEARCH_URL = BASE_URL + "/search/searchByName";

    }

    public static final class Acitive {
        private Acitive() {

        }

        // Account type id
        public static final String EXPERTFINDEXPERTCHAT_URL = BASE_URL + "/expert/expertFindExpertChat";

    }

    public static final class Post {
        private Post() {

        }

        public static final String POSTDETAIL_DATA = "post_detail_data";
        public static final String POSTDETAIL_DATA_INTENT = "com.cmbb.smartkids.postdetail";

    }

    public static final class Baby {
        private Baby() {

        }

        // Account type id
        public static final String FINDBYBABY_URL = BASE_URL + "/baby/findByBaby";
        public static final String DELETEBABY_URL = BASE_URL + "/baby/deleteBaby";
        public static final String ADDORUPDATEBABY_URL = BASE_URL + "/baby/addOrUpdateBaby";
        public static final String FINDBYBABYGROWING_URL = BASE_URL + "/babyGrowing/findByBabyGrowing";
        public static final String ADDBABYGROWING_URL = BASE_URL + "/babyGrowing/addBabyGrowing";
        public static final String DELETEBABYGROWING_URL = BASE_URL + "/babyGrowing/deleteBabyGrowing";

    }

    public static final class User {
        private User() {

        }

        public static final String REGISTER_URL = BASE_URL + "/login/register";
        public static final String VALIDPHONE_URL = BASE_URL + "/login/validPhone";
        public static final String WONDERFULPUBLISH_URL = BASE_URL + "/publish/findByWonderfulPublish";
        public static final String CITYPUBLISH_URL = BASE_URL + "/publish/findByCityPublish";
        public static final String STARPUBLISH_URL = BASE_URL + "/publish/findByStarPublish";
        public static final String FINDBYMYOTHERCASESTORE_URL = BASE_URL + "/storeUp/findByMyOtherCaseStore";
        public static final String FINDMYWONDERFULSTORE_URL = BASE_URL + "/storeUp/findMyWonderfulStore";
        public static final String FINDBYMYCITYSTORE_URL = BASE_URL + "/storeUp/findByMyCityStore";
        public static final String FINDBYMYSTARSTORE_URL = BASE_URL + "/storeUp/findByMyStarStore";
        public static final String UPDATEUSERBASIC_URL = BASE_URL + "/user/updateUserBasic";
        public static final String FINDUSERBASIC_URL = BASE_URL + "/user/findUserBasic";
        public static final String FINDATTENTIONUSER_URL = BASE_URL + "/attention/findAttentionUser";

        public static final String SINA_APP_KEY = "2019049338";
        public static final String SINA_APPSECRET = "96ae214825838c1fd26d9637ab39460d";
        public static final String WEIXIN_APP_KEY = "wx766b807ef51aa8da";
        public static final String WEIXIN_APPSECRET = "139fc6ccddba72262d94688368082312";

        public static final String QQ_APP_KEY = "1104000906";
        public static final String QQ_APPSECRET = "T73NH4Tz75dWsPdy";
        //feedback
        public static final String ADDFEEDBACK_URL = BASE_URL + "/feedBack/addFeedBack";
        // 注销
        public static final String LOGINOUT_URL = BASE_URL + "/login/loginOut";
        public static final String LOGINS_URL = BASE_URL + "/login/logins";

        public static final String FORGETPWD_URL = BASE_URL + "/login/forgetPWD";
        public static final String SUBMITPWD_URL = BASE_URL + "/login/submitPWD";
        public static final String UPDATEPWD_URL = BASE_URL + "/login/updatePWD";

        public static final String EREDARADDEREDAR_URL = BASE_URL + "/eredar/eredarAddEredar";


    }

    public static final class Share {
        public static final String WEIXIN_APP_KEY = "wx766b807ef51aa8da";
        public static final String WEIXIN_APPSECRET = "139fc6ccddba72262d94688368082312";

        public static final String QQ_APP_KEY = "1104000906";
        public static final String QQ_APPSECRET = "T73NH4Tz75dWsPdy";

        public static final String DESCRIPTOR = "com.cmbb.smartkids";
    }

    public static final class Home {

        private Home() {

        }

        // Banner
        public static final String BANNER_DATA = "banner_data";
        public static final String BANNER_DATA_INTENT = "com.cmbb.smartkids.banner";
        // User
        public static final String USERINFO_DATA = "userinfo_data";
        public static final String USERINFO_DATA_INTENT = "com.cmbb.smartkids.userinfo";


        public static final String AREATYPEPLATE_URL = BASE_URL + "/plate/findByAreaTypeFromPlate";
        public static final String FINDHOMEPAGE_URL = BASE_URL + "/home/findHomePage";
        public static final String FINDHOMEPAGEATTENTIONPLATE_URL = BASE_URL + "/home/findHomePageAttentionPlate";
        public static final String EXPERT_SCHEDULING = BASE_URL + "/expert/expertScheduling";

    }

    public static final class Master {

        private Master() {

        }

        public static final String EREDARFINDTYPE_URL = BASE_URL + "/eredar/eredarFindType";
        public static final String FINDEXPERT_URL = BASE_URL + "/expert/findExpert";
        public static final String EREDARFINDATTENTIONUSER_URL = BASE_URL + "/eredar/eredarFindAttentionUser";
        public static final String FINDATTENTIONEXPERT_URL = BASE_URL + "/expert/findAttentionExpert";
        public static final String EREDARFINDBYTYPEUSER_URL = BASE_URL + "/eredar/eredarFindByTypeUser";
        public static final String ADDATTENTION_URL = BASE_URL + "/attention/addAttention";
        public static final String DELETEATTENTION_URL = BASE_URL + "/attention/deleteAttention";

    }

    public static final class Case {
        private Case() {

        }

        public static final String FINDCASETYPE_URL = BASE_URL + "/caseThroughTrain/caseThroughTrainFindCaseType";
        public static final String FINDCASELIST_URL = BASE_URL + "/caseThroughTrain/caseThroughTrainFindCaseList";
        public static final String FINDBYCASEID_URL = BASE_URL + "/caseThroughTrain/caseThroughTrainFindByCaseId";
        public static final String FINDREPLYS_URL = BASE_URL + "/caseThroughTrain/caseThroughTrainFindReplys";
        public static final String ADDREPLYS_URL = BASE_URL + "/caseThroughTrain/caseThroughTrainAddReplys";
        public static final String DELETEREPLYS_URL = BASE_URL + "/caseThroughTrain/caseThroughTrainDeleteReplys";

    }

    public static final class SharePreference {
        private SharePreference() {

        }

        public static final String SCREEN_WIDTH = "screen_width";
        public static final String SCREEN_HEIGHT = "screen_height";
        public static final String IS_FIRST_INTO = "is_first_into";
        public static final String USER_TOKEN = "user_token";

    }
}

