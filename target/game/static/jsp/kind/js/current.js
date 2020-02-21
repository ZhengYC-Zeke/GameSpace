//初始化
function SelectTag(t) {
    this.child = t.child || ".default",
        this.over = t.over || "on",
        this.all = t.all || ".all",
        this.init()
}

$.extend(SelectTag.prototype, {
    init: function() {
        var t = this;
        t._bindEvent(),
            t._select()
    },

    _bindEvent: function() {
        var t = this;
        //点击单个超链接的跳转
        $(t.child).click(function(e) {
            e.preventDefault();
            //i为要跳转的连接页面
            var i = '/game/gamelist/kindShow'
                , n = $(this).attr("rel")
                , r = $(this).attr("name");
            //点击单个超链接跳转页面
            $(this).hasClass(t.over) || (window.location.href = t._changeURLPar(i, r, n))
        }),

            //点击全部的跳转
            $(t.all).click(function(e) {
                e.preventDefault();
                //i为要跳转的连接页面
                var i = '/game/gamelist/kindShow'
                    , n = $(this).attr("name");
                $("[name=" + n + "]").removeClass(t.over),
                    $(this).addClass(t.over),
                    //跳转页面
                    window.location.href = t._delUrlPar(i, n)
            })
    },

    _delUrlPar: function(t, e) {
        var n = "";
        if (t.indexOf("?") == -1)
            return t;
        n = t.substr(t.indexOf("?") + 1);
        var r = ""
            , a = "";
        if (n.indexOf("&") != -1) {
            r = n.split("&");
            for (i in r)
                r[i].split("=")[0] != e && (a = a + r[i].split("=")[0] + "=" + r[i].split("=")[1] + "&");
            return t.substr(0, t.indexOf("?")) + "?" + a.substr(0, a.length - 1)
        }
        return r = n.split("="),
            r[0] == e ? t.substr(0, t.indexOf("?")) : t
    },

    _changeURLPar: function(t, e, i) {
        var n = this
            , r = e + "=([^&]*)"
            , a = e + "=" + i
            , s = encodeURI(n._getQueryString(e));
        return t.match(r) ? t = t.replace(s, i) : t.match("[?]") ? t + "&" + a : t + "?" + a
    },

    _getQueryString: function(t) {
        var e = new RegExp("(^|&)" + t + "=([^&]*)(&|$)","i")
            , i = window.location.search.substr(1).match(e);
        return null != i ? decodeURI(i[2]) : null
    },

    _select: function() {
        var t = this
            , e = window.location.href;
        $(t.child).each(function() {
            var i = $(this).attr("name") + "=" + $(this).attr("rel")
                , n = new RegExp(encodeURI(i),"g");
            if (n.test(e)) {
                $(this).addClass(t.over);
                var r = $(this).attr("name");
                $("[name=" + r + "]").eq(0).removeClass(t.over)
            } else
                $(this).removeClass(t.over)
        })
    }
});
