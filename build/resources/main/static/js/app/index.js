var index = {
    init : function() {
        var _this = this;
        $("#btn-save").on("click", function() {
            _this.save();
        });
        $("#btn-update").on("click", function() {
            _this.update();
        });
    },
    save : function() {
        var data = {
            title : $("#title").val()
          , content : $("#content").val()
          , author : $("#author").val()
        };

        $.ajax({
            type : 'POST'
          , url : '/api/v1/posts'
          , dataType : 'json'
          , contentType : 'application/json; charset=utf-8'
          , data : JSON.stringify(data)
        }).done(function() {
            alert('글이 등록 되었습니다.');
            window.location.href='/';
        }).fail(function(error) {
            alert('등록중 오류가 발생했습니다.\n 관리자에게 문의하세요.');
            console.log(JSON.stringify(error));
        });
    },
    update : function() {
        var data = {
            title : $("#title").val()
          , content : $("#content").val()
        };

        $.ajax({
            type : 'PUT'
          , url : '/api/v1/posts/' + $("#id").val()
          , dataType : 'json'
          , contentType : 'application/json; charset=utf-8'
          , data : JSON.stringify(data)
        }).done(function() {
            alert('글이 수정 되었습니다.');
            window.location.href='/';
        }).fail(function(error) {
            alert('수정중 오류가 발생했습니다.\n 관리자에게 문의하세요.');
            console.log(JSON.stringify(error));
        });
    }
};

index.init();