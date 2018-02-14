Kakao.init('fe2b9e6e9dc19c730ad3d547e0772625');
      
      // 카카오 로그인 버튼을 생성합니다.
      Kakao.Auth.createLoginButton({
        container: '#kakao-login-btn',
        size: 'small',
        success: function(authObj) {
          
          // 로그인 성공시 API를 호출합니다.
          Kakao.API.request({
             
          url: '/v1/user/me',
          success: function(res) {
            var sData = JSON.stringify(res);
            //alert(sData);
            
            sData = JSON.parse(sData);
            var route = "kakao"
            var id = sData.id+"@"+route;             
            var nickname = sData.properties.nickname;
            var thumbnail_image = sData.properties.thumbnail_image;
            var profile_image = sData.properties.profile_image;
           
     
            var f = document.createElement("form");
          /*   f.setAttribute("method","get");
            f.setAttribute("action","afterlogin/"+i_id);
            f.setAttribute("target","_self");
            document.body.appendChild(f); */
            
            var i_id=document.createElement("input");
            i_id.setAttribute("type","hidden");
            i_id.setAttribute("name","kakao_id");
            i_id.setAttribute("value",id);
           // f.appendChild(i_id);
            
            var i_nick=document.createElement("input");
            i_nick.setAttribute("type","hidden");
            i_nick.setAttribute("name","kakao_nick");
            i_nick.setAttribute("value",nickname);
           // f.appendChild(i_nick);
            
            var i_thum=document.createElement("input");
            i_thum.setAttribute("type","hidden");
            i_thum.setAttribute("name","kakao_thumbnail_image");
            i_thum.setAttribute("value",thumbnail_image);
           // f.appendChild(i_thum);
            
            var i_profile=document.createElement("input");
            i_profile.setAttribute("type","hidden");
            i_profile.setAttribute("name","kakao_profile_image");
            i_profile.setAttribute("value",profile_image);
           // f.appendChild(i_profile);
            
            var i_accessToken=document.createElement("input");
            i_accessToken.setAttribute("type","hidden");
            i_accessToken.setAttribute("name","accessToken");
            i_accessToken.setAttribute("value",accessToken);
            //f.appendChild(i_accessToken);
            
            var i_refreshToken=document.createElement("input");
            i_refreshToken.setAttribute("type","hidden");
            i_refreshToken.setAttribute("name","refreshToken");
            i_refreshToken.setAttribute("value",refreshToken);
           // f.appendChild(i_refreshToken);
            
            
            f.setAttribute("method","post");
            f.setAttribute("action","afterlogin/"+i_id.value);
            f.setAttribute("target","_self");
            document.body.appendChild(f);
            
            f.appendChild(i_id);
            f.appendChild(i_nick);
            f.appendChild(i_thum);
            f.appendChild(i_profile);
            f.appendChild(i_accessToken);
            f.appendChild(i_refreshToken);
            
            
            f.submit();
          },   fail : function(error){
            console.log(JSON.stringify('api req fail'+error));
         }
          
          });
      var accessToken = Kakao.Auth.getAccessToken();
      var refreshToken = Kakao.Auth.getRefreshToken();
   
          //document.writeln("액세스 토큰 : "+accessToken);
          //document.writeln("<br>");
          //document.writeln("리프레시 토큰 : "+refreshToken);
          //document.writeln("<br>");
          
        },
        
        fail: function(err) {
            console.log('oauth fail'+JSON.stringify(err));
        }
      });
    