package com.cmbb.smartkids.fragment.record;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * 项目名称：MBProject
 * 类描述：
 * 创建人：N.Sun
 * 创建时间：15/9/18 下午1:07
 */
public class RecordModel implements Parcelable {

    /**
     * code : 1
     * context : [{"context":"回复帖子","date":"2015-09-18","goldCount":1,"id":70024,"title":""},{"context":"回复帖子","date":"2015-09-18","goldCount":1,"id":70023,"title":""},{"context":"回复帖子","date":"2015-09-18","goldCount":1,"id":70022,"title":""},{"context":"发布帖子","date":"2015-09-18","goldCount":3,"id":70020,"title":""},{"context":"发布帖子","date":"2015-09-16","goldCount":3,"id":67966,"title":""},{"context":"回复帖子","date":"2015-09-16","goldCount":1,"id":67961,"title":""},{"context":"回复帖子","date":"2015-09-16","goldCount":1,"id":67960,"title":""},{"context":"回复帖子","date":"2015-09-16","goldCount":1,"id":67959,"title":""},{"context":"回复帖子","date":"2015-09-16","goldCount":1,"id":67958,"title":""},{"context":"回复帖子","date":"2015-09-16","goldCount":1,"id":67957,"title":""},{"context":"回复帖子","date":"2015-09-16","goldCount":1,"id":67956,"title":""},{"context":"发布帖子","date":"2015-09-16","goldCount":3,"id":67955,"title":""},{"context":"回复帖子","date":"2015-09-15","goldCount":1,"id":67568,"title":""},{"context":"发布帖子","date":"2015-09-15","goldCount":3,"id":67564,"title":""},{"context":"回复帖子","date":"2015-09-10","goldCount":1,"id":60840,"title":""},{"context":"回复帖子","date":"2015-09-10","goldCount":1,"id":60839,"title":""},{"context":"发布帖子","date":"2015-09-10","goldCount":3,"id":60838,"title":""},{"context":"回复帖子","date":"2015-09-10","goldCount":1,"id":60837,"title":""},{"context":"回复帖子","date":"2015-09-10","goldCount":1,"id":60836,"title":""},{"context":"发布帖子","date":"2015-09-10","goldCount":3,"id":60835,"title":""},{"context":"回复帖子","date":"2015-09-10","goldCount":1,"id":60833,"title":""},{"context":"发布帖子","date":"2015-09-10","goldCount":3,"id":60832,"title":""},{"context":"回复帖子","date":"2015-09-10","goldCount":1,"id":60831,"title":""},{"context":"回复帖子","date":"2015-09-10","goldCount":1,"id":60830,"title":""},{"context":"回复帖子","date":"2015-09-10","goldCount":1,"id":60829,"title":""},{"context":"发布帖子","date":"2015-09-10","goldCount":3,"id":60828,"title":""},{"context":"回复帖子","date":"2015-09-10","goldCount":1,"id":60826,"title":""},{"context":"发布帖子","date":"2015-09-10","goldCount":3,"id":60825,"title":""},{"context":"发布帖子","date":"2015-09-10","goldCount":3,"id":60817,"title":""},{"context":"回复帖子","date":"2015-09-10","goldCount":1,"id":60815,"title":""},{"context":"回复帖子","date":"2015-09-10","goldCount":1,"id":60814,"title":""},{"context":"回复帖子","date":"2015-09-10","goldCount":1,"id":60812,"title":""},{"context":"发布帖子","date":"2015-09-10","goldCount":3,"id":60810,"title":""},{"context":"发布帖子","date":"2015-09-10","goldCount":3,"id":60779,"title":""},{"context":"回复帖子","date":"2015-09-10","goldCount":1,"id":60776,"title":""},{"context":"发布帖子","date":"2015-09-10","goldCount":3,"id":60775,"title":""},{"context":"回复帖子","date":"2015-09-10","goldCount":1,"id":60774,"title":""},{"context":"发布帖子","date":"2015-09-10","goldCount":3,"id":60773,"title":""},{"context":"回复帖子","date":"2015-09-10","goldCount":1,"id":60772,"title":""},{"context":"回复帖子","date":"2015-09-09","goldCount":1,"id":58773,"title":""},{"context":"发布帖子","date":"2015-09-09","goldCount":3,"id":58772,"title":""},{"context":"回复帖子","date":"2015-09-09","goldCount":1,"id":58770,"title":""},{"context":"发布帖子","date":"2015-09-09","goldCount":3,"id":58769,"title":""},{"context":"回复帖子","date":"2015-09-09","goldCount":1,"id":58768,"title":""},{"context":"回复帖子","date":"2015-09-09","goldCount":1,"id":58767,"title":""},{"context":"回复帖子","date":"2015-09-09","goldCount":1,"id":58754,"title":""},{"context":"发布帖子","date":"2015-09-09","goldCount":3,"id":58753,"title":""},{"context":"回复帖子","date":"2015-09-09","goldCount":1,"id":58752,"title":""},{"context":"回复帖子","date":"2015-09-09","goldCount":1,"id":58750,"title":""},{"context":"回复帖子","date":"2015-09-09","goldCount":1,"id":58749,"title":""},{"context":"发布帖子","date":"2015-09-09","goldCount":3,"id":58748,"title":""},{"context":"回复帖子","date":"2015-09-09","goldCount":1,"id":58746,"title":""},{"context":"发布帖子","date":"2015-09-09","goldCount":3,"id":58744,"title":""},{"context":"回复帖子","date":"2015-09-09","goldCount":1,"id":58743,"title":""},{"context":"回复帖子","date":"2015-09-09","goldCount":1,"id":58742,"title":""},{"context":"回复帖子","date":"2015-09-09","goldCount":1,"id":58741,"title":""},{"context":"发布帖子","date":"2015-09-09","goldCount":3,"id":58740,"title":""},{"context":"发布帖子","date":"2015-09-09","goldCount":3,"id":58739,"title":""},{"context":"发布帖子","date":"2015-09-09","goldCount":3,"id":58736,"title":""},{"context":"发布帖子","date":"2015-09-09","goldCount":3,"id":58734,"title":""},{"context":"发布帖子","date":"2015-09-09","goldCount":3,"id":58591,"title":""},{"context":"回复帖子","date":"2015-09-09","goldCount":1,"id":58557,"title":""},{"context":"回复帖子","date":"2015-09-09","goldCount":1,"id":58554,"title":""},{"context":"回复帖子","date":"2015-09-09","goldCount":1,"id":58552,"title":""},{"context":"回复帖子","date":"2015-09-09","goldCount":1,"id":58547,"title":""},{"context":"回复帖子","date":"2015-09-09","goldCount":1,"id":58545,"title":""},{"context":"回复帖子","date":"2015-09-09","goldCount":1,"id":58544,"title":""},{"context":"回复帖子","date":"2015-09-09","goldCount":1,"id":58541,"title":""},{"context":"发布帖子","date":"2015-09-09","goldCount":3,"id":58539,"title":""},{"context":"回复帖子","date":"2015-09-09","goldCount":1,"id":58537,"title":""},{"context":"回复帖子","date":"2015-09-09","goldCount":1,"id":58534,"title":""},{"context":"回复帖子","date":"2015-09-08","goldCount":1,"id":56132,"title":""},{"context":"回复帖子","date":"2015-09-08","goldCount":1,"id":56125,"title":""},{"context":"回复帖子","date":"2015-09-08","goldCount":1,"id":56121,"title":""},{"context":"回复帖子","date":"2015-09-08","goldCount":1,"id":56120,"title":""},{"context":"回复帖子","date":"2015-09-08","goldCount":1,"id":56119,"title":""},{"context":"回复帖子","date":"2015-09-08","goldCount":1,"id":55815,"title":""},{"context":"回复帖子","date":"2015-09-08","goldCount":1,"id":55809,"title":""},{"context":"回复帖子","date":"2015-09-08","goldCount":1,"id":55803,"title":""},{"context":"回复帖子","date":"2015-09-08","goldCount":1,"id":55785,"title":""},{"context":"回复帖子","date":"2015-09-08","goldCount":1,"id":55779,"title":""},{"context":"回复帖子","date":"2015-09-08","goldCount":1,"id":55599,"title":""},{"context":"回复帖子","date":"2015-09-08","goldCount":1,"id":55598,"title":""},{"context":"回复帖子","date":"2015-09-08","goldCount":1,"id":55597,"title":""},{"context":"回复帖子","date":"2015-09-08","goldCount":1,"id":55557,"title":""},{"context":"回复帖子","date":"2015-09-08","goldCount":1,"id":55556,"title":""},{"context":"回复帖子","date":"2015-09-08","goldCount":1,"id":55554,"title":""},{"context":"回复帖子","date":"2015-09-08","goldCount":1,"id":55550,"title":""},{"context":"回复帖子","date":"2015-09-08","goldCount":1,"id":55548,"title":""},{"context":"回复帖子","date":"2015-09-08","goldCount":1,"id":55456,"title":""},{"context":"回复帖子","date":"2015-09-08","goldCount":1,"id":55454,"title":""},{"context":"回复帖子","date":"2015-09-08","goldCount":1,"id":55451,"title":""},{"context":"回复帖子","date":"2015-09-08","goldCount":1,"id":55425,"title":""},{"context":"回复帖子","date":"2015-09-08","goldCount":1,"id":55420,"title":""},{"context":"回复帖子","date":"2015-09-08","goldCount":1,"id":55419,"title":""},{"context":"回复帖子","date":"2015-09-08","goldCount":1,"id":55418,"title":""},{"context":"回复帖子","date":"2015-09-08","goldCount":1,"id":55415,"title":""},{"context":"回复帖子","date":"2015-09-08","goldCount":1,"id":55400,"title":""},{"context":"回复帖子","date":"2015-09-08","goldCount":1,"id":55398,"title":""},{"context":"回复帖子","date":"2015-09-08","goldCount":1,"id":55393,"title":""},{"context":"回复帖子","date":"2015-09-08","goldCount":1,"id":55369,"title":""},{"context":"回复帖子","date":"2015-09-08","goldCount":1,"id":55366,"title":""},{"context":"回复帖子","date":"2015-09-08","goldCount":1,"id":55361,"title":""},{"context":"发布帖子","date":"2015-09-08","goldCount":3,"id":55357,"title":""},{"context":"回复帖子","date":"2015-09-08","goldCount":1,"id":55344,"title":""},{"context":"回复帖子","date":"2015-09-08","goldCount":1,"id":55319,"title":""},{"context":"回复帖子","date":"2015-09-08","goldCount":1,"id":55313,"title":""},{"context":"回复帖子","date":"2015-09-08","goldCount":1,"id":55310,"title":""},{"context":"回复帖子","date":"2015-09-08","goldCount":1,"id":55309,"title":""},{"context":"回复帖子","date":"2015-09-08","goldCount":1,"id":55306,"title":""},{"context":"回复帖子","date":"2015-09-08","goldCount":1,"id":55303,"title":""},{"context":"回复帖子","date":"2015-09-08","goldCount":1,"id":55284,"title":""},{"context":"回复帖子","date":"2015-09-08","goldCount":1,"id":55282,"title":""},{"context":"回复帖子","date":"2015-09-08","goldCount":1,"id":55268,"title":""},{"context":"回复帖子","date":"2015-09-08","goldCount":1,"id":55262,"title":""},{"context":"回复帖子","date":"2015-09-08","goldCount":1,"id":55259,"title":""},{"context":"回复帖子","date":"2015-09-08","goldCount":1,"id":55256,"title":""},{"context":"回复帖子","date":"2015-09-08","goldCount":1,"id":55243,"title":""},{"context":"回复帖子","date":"2015-09-08","goldCount":1,"id":55242,"title":""},{"context":"回复帖子","date":"2015-09-08","goldCount":1,"id":55240,"title":""},{"context":"回复帖子","date":"2015-09-08","goldCount":1,"id":55171,"title":""},{"context":"发布帖子","date":"2015-09-08","goldCount":3,"id":55113,"title":""},{"context":"回复帖子","date":"2015-09-07","goldCount":1,"id":53546,"title":""},{"context":"回复帖子","date":"2015-09-07","goldCount":1,"id":53543,"title":""},{"context":"回复帖子","date":"2015-09-07","goldCount":1,"id":53542,"title":""},{"context":"回复帖子","date":"2015-09-07","goldCount":1,"id":53535,"title":""},{"context":"回复帖子","date":"2015-09-07","goldCount":1,"id":53531,"title":""},{"context":"回复帖子","date":"2015-09-07","goldCount":1,"id":53513,"title":""},{"context":"发布帖子","date":"2015-09-07","goldCount":3,"id":53507,"title":""},{"context":"发布帖子","date":"2015-09-07","goldCount":3,"id":53506,"title":""},{"context":"发布帖子","date":"2015-09-07","goldCount":3,"id":53499,"title":""},{"context":"回复帖子","date":"2015-09-07","goldCount":1,"id":53495,"title":""},{"context":"回复帖子","date":"2015-09-07","goldCount":1,"id":53494,"title":""},{"context":"回复帖子","date":"2015-09-07","goldCount":1,"id":53493,"title":""},{"context":"回复帖子","date":"2015-09-07","goldCount":1,"id":53489,"title":""},{"context":"回复帖子","date":"2015-09-07","goldCount":1,"id":53488,"title":""},{"context":"回复帖子","date":"2015-09-07","goldCount":1,"id":53476,"title":""},{"context":"回复帖子","date":"2015-09-07","goldCount":1,"id":53475,"title":""},{"context":"回复帖子","date":"2015-09-07","goldCount":1,"id":53338,"title":""},{"context":"回复帖子","date":"2015-09-07","goldCount":1,"id":53333,"title":""},{"context":"回复帖子","date":"2015-09-07","goldCount":1,"id":53332,"title":""},{"context":"回复帖子","date":"2015-09-07","goldCount":1,"id":53331,"title":""},{"context":"回复帖子","date":"2015-09-07","goldCount":1,"id":53330,"title":""},{"context":"回复帖子","date":"2015-09-07","goldCount":1,"id":53328,"title":""},{"context":"回复帖子","date":"2015-09-07","goldCount":1,"id":53327,"title":""},{"context":"回复帖子","date":"2015-09-07","goldCount":1,"id":53326,"title":""},{"context":"回复帖子","date":"2015-09-07","goldCount":1,"id":53325,"title":""},{"context":"回复帖子","date":"2015-09-07","goldCount":1,"id":53324,"title":""},{"context":"发布帖子","date":"2015-09-07","goldCount":3,"id":53323,"title":""},{"context":"回复帖子","date":"2015-09-07","goldCount":1,"id":53322,"title":""},{"context":"回复帖子","date":"2015-09-07","goldCount":1,"id":53291,"title":""},{"context":"回复帖子","date":"2015-09-07","goldCount":1,"id":53289,"title":""},{"context":"回复帖子","date":"2015-09-07","goldCount":1,"id":53287,"title":""},{"context":"回复帖子","date":"2015-09-07","goldCount":1,"id":53285,"title":""},{"context":"回复帖子","date":"2015-09-07","goldCount":1,"id":53283,"title":""},{"context":"回复帖子","date":"2015-09-07","goldCount":1,"id":53270,"title":""},{"context":"回复帖子","date":"2015-09-07","goldCount":1,"id":53268,"title":""},{"context":"回复帖子","date":"2015-09-07","goldCount":1,"id":53264,"title":""},{"context":"回复帖子","date":"2015-09-07","goldCount":1,"id":53261,"title":""},{"context":"回复帖子","date":"2015-09-07","goldCount":1,"id":53260,"title":""},{"context":"回复帖子","date":"2015-09-07","goldCount":1,"id":53259,"title":""},{"context":"回复帖子","date":"2015-09-07","goldCount":1,"id":53258,"title":""},{"context":"回复帖子","date":"2015-09-07","goldCount":1,"id":53257,"title":""},{"context":"回复帖子","date":"2015-09-07","goldCount":1,"id":53256,"title":""},{"context":"回复帖子","date":"2015-09-07","goldCount":1,"id":53253,"title":""},{"context":"回复帖子","date":"2015-09-07","goldCount":1,"id":53250,"title":""},{"context":"回复帖子","date":"2015-09-07","goldCount":1,"id":53249,"title":""},{"context":"发布帖子","date":"2015-09-07","goldCount":3,"id":53248,"title":""},{"context":"回复帖子","date":"2015-09-06","goldCount":1,"id":52529,"title":""},{"context":"回复帖子","date":"2015-09-06","goldCount":1,"id":52525,"title":""},{"context":"回复帖子","date":"2015-09-06","goldCount":1,"id":52523,"title":""},{"context":"回复帖子","date":"2015-09-06","goldCount":1,"id":52522,"title":""},{"context":"回复帖子","date":"2015-09-06","goldCount":1,"id":52520,"title":""},{"context":"回复帖子","date":"2015-09-06","goldCount":1,"id":52519,"title":""},{"context":"回复帖子","date":"2015-09-06","goldCount":1,"id":52516,"title":""},{"context":"回复帖子","date":"2015-09-06","goldCount":1,"id":52511,"title":""},{"context":"回复帖子","date":"2015-09-06","goldCount":1,"id":52508,"title":""},{"context":"回复帖子","date":"2015-09-06","goldCount":1,"id":52495,"title":""},{"context":"回复帖子","date":"2015-09-06","goldCount":1,"id":52253,"title":""},{"context":"回复帖子","date":"2015-09-06","goldCount":1,"id":52252,"title":""},{"context":"回复帖子","date":"2015-09-06","goldCount":1,"id":52249,"title":""},{"context":"回复帖子","date":"2015-09-06","goldCount":1,"id":52248,"title":""},{"context":"回复帖子","date":"2015-09-06","goldCount":1,"id":52247,"title":""},{"context":"回复帖子","date":"2015-09-06","goldCount":1,"id":52246,"title":""},{"context":"回复帖子","date":"2015-09-06","goldCount":1,"id":52245,"title":""},{"context":"回复帖子","date":"2015-09-06","goldCount":1,"id":52244,"title":""},{"context":"回复帖子","date":"2015-09-06","goldCount":1,"id":52243,"title":""},{"context":"回复帖子","date":"2015-09-06","goldCount":1,"id":52242,"title":""},{"context":"回复帖子","date":"2015-09-06","goldCount":1,"id":52241,"title":""},{"context":"回复帖子","date":"2015-09-06","goldCount":1,"id":52240,"title":""},{"context":"回复帖子","date":"2015-09-06","goldCount":1,"id":52239,"title":""},{"context":"发布帖子","date":"2015-09-06","goldCount":3,"id":52238,"title":""},{"context":"发布帖子","date":"2015-09-06","goldCount":3,"id":52155,"title":""},{"context":"发布帖子","date":"2015-09-06","goldCount":3,"id":52153,"title":""},{"context":"发布帖子","date":"2015-09-06","goldCount":3,"id":52152,"title":""},{"context":"发布帖子","date":"2015-09-06","goldCount":3,"id":52149,"title":""},{"context":"发布帖子","date":"2015-09-06","goldCount":3,"id":52147,"title":""},{"context":"发布帖子","date":"2015-09-06","goldCount":3,"id":52135,"title":""},{"context":"发布帖子","date":"2015-09-06","goldCount":3,"id":52124,"title":""},{"context":"发布帖子","date":"2015-09-06","goldCount":3,"id":52119,"title":""},{"context":"发布帖子","date":"2015-09-06","goldCount":3,"id":52118,"title":""},{"context":"发布帖子","date":"2015-09-06","goldCount":3,"id":52116,"title":""},{"context":"发布帖子","date":"2015-09-06","goldCount":3,"id":52115,"title":""},{"context":"回复帖子","date":"2015-09-06","goldCount":1,"id":52088,"title":""},{"context":"发布帖子","date":"2015-09-06","goldCount":3,"id":52087,"title":""},{"context":"发布帖子","date":"2015-09-06","goldCount":3,"id":51909,"title":""},{"context":"发布帖子","date":"2015-09-06","goldCount":3,"id":51880,"title":""},{"context":"回复帖子","date":"2015-08-31","goldCount":1,"id":42280,"title":""},{"context":"发布帖子","date":"2015-08-31","goldCount":3,"id":42279,"title":""},{"context":"回复帖子","date":"2015-08-31","goldCount":1,"id":42275,"title":""},{"context":"发布帖子","date":"2015-08-31","goldCount":3,"id":42274,"title":""},{"context":"回复帖子","date":"2015-08-26","goldCount":1,"id":33149,"title":""},{"context":"回复帖子","date":"2015-08-26","goldCount":1,"id":33148,"title":""},{"context":"发布宝宝日记","date":"2015-08-19","goldCount":10,"id":24702,"title":""},{"context":"发布宝宝日记","date":"2015-08-19","goldCount":10,"id":24666,"title":""},{"context":"发布宝宝日记","date":"2015-08-19","goldCount":10,"id":24660,"title":""},{"context":"首次创建宝宝","date":"2015-08-19","goldCount":10,"id":24596,"title":""},{"context":"首次创建宝宝","date":"2015-08-19","goldCount":10,"id":24595,"title":""},{"context":"回复帖子","date":"2015-08-18","goldCount":1,"id":23024,"title":""},{"context":"回复帖子","date":"2015-08-18","goldCount":1,"id":22466,"title":""},{"context":"回复帖子","date":"2015-08-18","goldCount":1,"id":22465,"title":""},{"context":"回复帖子","date":"2015-08-18","goldCount":1,"id":22447,"title":""},{"context":"回复帖子","date":"2015-08-18","goldCount":1,"id":22443,"title":""},{"context":"发布帖子","date":"2015-08-11","goldCount":3,"id":17513,"title":""},{"context":"发布帖子","date":"2015-08-07","goldCount":3,"id":14539,"title":""},{"context":"发布帖子","date":"2015-08-07","goldCount":3,"id":14536,"title":""},{"context":"发布帖子","date":"2015-08-07","goldCount":3,"id":14528,"title":""},{"context":"发布帖子","date":"2015-08-07","goldCount":3,"id":14525,"title":""},{"context":"发布帖子","date":"2015-08-07","goldCount":3,"id":14507,"title":""},{"context":"发布帖子","date":"2015-08-07","goldCount":3,"id":14506,"title":""},{"context":"发布帖子","date":"2015-08-07","goldCount":3,"id":14505,"title":""},{"context":"发布帖子","date":"2015-08-07","goldCount":3,"id":14504,"title":""},{"context":"发布帖子","date":"2015-08-07","goldCount":3,"id":14502,"title":""},{"context":"发布帖子","date":"2015-08-07","goldCount":3,"id":14501,"title":""},{"context":"发布帖子","date":"2015-08-07","goldCount":3,"id":14500,"title":""},{"context":"发布帖子","date":"2015-08-07","goldCount":3,"id":14499,"title":""},{"context":"发布帖子","date":"2015-08-07","goldCount":3,"id":14498,"title":""},{"context":"发布帖子","date":"2015-08-07","goldCount":3,"id":14497,"title":""},{"context":"发布帖子","date":"2015-08-07","goldCount":3,"id":14493,"title":""},{"context":"发布帖子","date":"2015-08-07","goldCount":3,"id":14492,"title":""},{"context":"发布帖子","date":"2015-08-07","goldCount":3,"id":14491,"title":""},{"context":"发布帖子","date":"2015-08-07","goldCount":3,"id":14490,"title":""},{"context":"关注10个专区","date":"2015-08-06","goldCount":40,"id":13616,"title":""},{"context":"关注10个专区","date":"2015-08-06","goldCount":40,"id":13615,"title":""},{"context":"关注10个专区","date":"2015-08-06","goldCount":40,"id":13614,"title":""},{"context":"关注10个专区","date":"2015-08-06","goldCount":40,"id":13613,"title":""},{"context":"关注10个专区","date":"2015-08-06","goldCount":40,"id":13612,"title":""},{"context":"关注10个专区","date":"2015-08-06","goldCount":40,"id":13611,"title":""},{"context":"关注10个专区","date":"2015-08-06","goldCount":40,"id":13610,"title":""},{"context":"关注10个专区","date":"2015-08-06","goldCount":40,"id":13609,"title":""},{"context":"关注10个专区","date":"2015-08-06","goldCount":40,"id":13565,"title":""},{"context":"关注10个专区","date":"2015-08-06","goldCount":40,"id":13564,"title":""},{"context":"关注10个专区","date":"2015-08-06","goldCount":40,"id":13563,"title":""},{"context":"关注10个专区","date":"2015-08-06","goldCount":40,"id":13562,"title":""},{"context":"关注10个专区","date":"2015-08-06","goldCount":40,"id":13560,"title":""},{"context":"关注10个专区","date":"2015-08-06","goldCount":40,"id":13556,"title":""},{"context":"关注10个专区","date":"2015-08-06","goldCount":40,"id":13557,"title":""},{"context":"关注10个专区","date":"2015-08-06","goldCount":40,"id":13555,"title":""},{"context":"关注10个专区","date":"2015-08-06","goldCount":40,"id":13554,"title":""},{"context":"关注10个专区","date":"2015-08-06","goldCount":40,"id":13553,"title":""},{"context":"关注10个专区","date":"2015-08-06","goldCount":40,"id":13552,"title":""},{"context":"回复帖子","date":"2015-07-27","goldCount":1,"id":10207,"title":""},{"context":"回复帖子","date":"2015-07-27","goldCount":1,"id":10206,"title":""},{"context":"回复帖子","date":"2015-07-03","goldCount":1,"id":6473,"title":""},{"context":"回复帖子","date":"2015-06-23","goldCount":1,"id":4432,"title":""},{"context":"发布帖子","date":"2015-06-12","goldCount":3,"id":3289,"title":""},{"context":"发布宝宝日记","date":"2015-06-12","goldCount":10,"id":3287,"title":""},{"context":"发布宝宝日记","date":"2015-06-12","goldCount":10,"id":3286,"title":""},{"context":"回复帖子","date":"2015-06-11","goldCount":1,"id":3171,"title":""},{"context":"回复帖子","date":"2015-06-11","goldCount":1,"id":3170,"title":""},{"context":"回复帖子","date":"2015-06-11","goldCount":1,"id":3169,"title":""},{"context":"发布帖子","date":"2015-06-11","goldCount":3,"id":3168,"title":""},{"context":"发布帖子","date":"2015-06-10","goldCount":3,"id":3122,"title":""},{"context":"发布帖子","date":"2015-06-10","goldCount":3,"id":3108,"title":""},{"context":"发布帖子","date":"2015-06-10","goldCount":3,"id":3107,"title":""},{"context":"发布帖子","date":"2015-06-10","goldCount":3,"id":3106,"title":""},{"context":"发布帖子","date":"2015-06-10","goldCount":3,"id":3105,"title":""},{"context":"回复帖子","date":"2015-06-10","goldCount":1,"id":3104,"title":""},{"context":"发布帖子","date":"2015-06-10","goldCount":3,"id":3103,"title":""},{"context":"发布帖子","date":"2015-06-09","goldCount":3,"id":3056,"title":""},{"context":"回复帖子","date":"2015-06-09","goldCount":1,"id":2985,"title":""},{"context":"回复帖子","date":"2015-06-09","goldCount":1,"id":2983,"title":""},{"context":"回复帖子","date":"2015-06-09","goldCount":1,"id":2982,"title":""},{"context":"发布帖子","date":"2015-06-09","goldCount":3,"id":2979,"title":""},{"context":"发布帖子","date":"2015-06-09","goldCount":3,"id":2969,"title":""},{"context":"发布帖子","date":"2015-06-09","goldCount":3,"id":2956,"title":""},{"context":"发布帖子","date":"2015-06-09","goldCount":3,"id":2952,"title":""},{"context":"回复帖子","date":"2015-06-09","goldCount":1,"id":2931,"title":""},{"context":"回复帖子","date":"2015-06-09","goldCount":1,"id":2923,"title":""},{"context":"回复帖子","date":"2015-06-09","goldCount":1,"id":2922,"title":""},{"context":"回复帖子","date":"2015-06-09","goldCount":1,"id":2921,"title":""},{"context":"回复帖子","date":"2015-06-09","goldCount":1,"id":2920,"title":""},{"context":"回复帖子","date":"2015-06-09","goldCount":1,"id":2919,"title":""},{"context":"回复帖子","date":"2015-06-09","goldCount":1,"id":2918,"title":""},{"context":"发布帖子","date":"2015-06-09","goldCount":3,"id":2917,"title":""},{"context":"回复帖子","date":"2015-06-04","goldCount":1,"id":2643,"title":""},{"context":"回复帖子","date":"2015-06-04","goldCount":1,"id":2642,"title":""},{"context":"回复帖子","date":"2015-06-04","goldCount":1,"id":2641,"title":""},{"context":"回复帖子","date":"2015-06-04","goldCount":1,"id":2640,"title":""},{"context":"发布帖子","date":"2015-06-04","goldCount":3,"id":2639,"title":""},{"context":"发布帖子","date":"2015-05-29","goldCount":3,"id":2169,"title":""},{"context":"发布帖子","date":"2015-05-29","goldCount":3,"id":2168,"title":""},{"context":"回复帖子","date":"2015-05-19","goldCount":1,"id":1384,"title":""},{"context":"资料补全","date":"2015-05-18","goldCount":30,"id":1354,"title":""}]
     */

    private String code;
    private List<ContextEntity> context;

    public void setCode(String code) {
        this.code = code;
    }

    public void setContext(List<ContextEntity> context) {
        this.context = context;
    }

    public String getCode() {
        return code;
    }

    public List<ContextEntity> getContext() {
        return context;
    }

    @Override
    public String toString() {
        return "RecordModel{" +
                "code='" + code + '\'' +
                ", context=" + context +
                '}';
    }



    public static class ContextEntity implements Parcelable {
        /**
         * context : 回复帖子
         * date : 2015-09-18
         * goldCount : 1
         * id : 70024
         * title :
         */

        private String context;
        private String date;
        private int goldCount;
        private int id;
        private String title;

        public void setContext(String context) {
            this.context = context;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public void setGoldCount(int goldCount) {
            this.goldCount = goldCount;
        }

        public void setId(int id) {
            this.id = id;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getContext() {
            return context;
        }

        public String getDate() {
            return date;
        }

        public int getGoldCount() {
            return goldCount;
        }

        public int getId() {
            return id;
        }

        public String getTitle() {
            return title;
        }

        @Override
        public String toString() {
            return "ContextEntity{" +
                    "context='" + context + '\'' +
                    ", date='" + date + '\'' +
                    ", goldCount=" + goldCount +
                    ", id=" + id +
                    ", title='" + title + '\'' +
                    '}';
        }


        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.context);
            dest.writeString(this.date);
            dest.writeInt(this.goldCount);
            dest.writeInt(this.id);
            dest.writeString(this.title);
        }

        public ContextEntity() {
        }

        protected ContextEntity(Parcel in) {
            this.context = in.readString();
            this.date = in.readString();
            this.goldCount = in.readInt();
            this.id = in.readInt();
            this.title = in.readString();
        }

        public static final Creator<ContextEntity> CREATOR = new Creator<ContextEntity>() {
            public ContextEntity createFromParcel(Parcel source) {
                return new ContextEntity(source);
            }

            public ContextEntity[] newArray(int size) {
                return new ContextEntity[size];
            }
        };
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.code);
        dest.writeList(this.context);
    }

    public RecordModel() {
    }

    protected RecordModel(Parcel in) {
        this.code = in.readString();
        this.context = new ArrayList<ContextEntity>();
        in.readList(this.context, List.class.getClassLoader());
    }

    public static final Parcelable.Creator<RecordModel> CREATOR = new Parcelable.Creator<RecordModel>() {
        public RecordModel createFromParcel(Parcel source) {
            return new RecordModel(source);
        }

        public RecordModel[] newArray(int size) {
            return new RecordModel[size];
        }
    };
}
